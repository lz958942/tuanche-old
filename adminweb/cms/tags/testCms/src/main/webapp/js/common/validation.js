/**
 * Form Validation engine, jQuery plugin
 * @declaration
 *		本验证引擎的设计理念来源于：http://www.position-relative.net/creation/formValidator/
 *		用法很相近,但基本上全部重写了构架和方法, 优化功能并增加扩展性,如：
 *		·优化required必选，可选项无须在最左边声明optional,
 *		·隐藏(不显示)的元素，不验证，显示后恢复验证，适用于动态表单
 *		·添加初始化引擎，如提示帮助信息 help(helpRule)，事件调用 keyup(funCall), 初始器和验证器均可扩展
 *		·ajax验证规则添加开关，验证过了，表单提交时不重复请求验证
 *		·验证提示信息支持格式化参量，如 "恭喜，{0}可以使用" ,{0}代表输入框的值
 *		·优化验证字段提取方法，增加验证表达式中空格和逗号的容错性
 *      ·带参数的验证器，支持同时验证多个规则，如 ajax( ajax1, ajax2[,...] )
 *		·输入控件可脱离表单，单独验证结果
 *
 * @author stone58@qq.com[QQ:31474361]
 * @version 1.2.0
 * @overrides 
 *		1.修改验证标志className 值为元素属性 validation
 *		2.用户验证器支持写入多规则，如 regex( rule1, rule2[,...] )
 *		3.引入js即可自动引入CSS, 不需要页面中引入CSS, 需要jQuery.validationEngine.fileName 属性支持
 *
 * jQuery.fn 主要方法扩展：
 *		validateable(settings)  			//可验证构造器 e.g. $('#myform').validateable();
 *		validate(settings)					//执行并返回验证结果  $('#myform').validate();  $('input').validate();
 *		onValidated(callback)				//input方法，验证后事件回调，根据验证结果执行处理代码
 *												$('input').onValidated(function(result, msg){  ...  } );
 *		validatePromtp(msg,type,settings) 	//弹出验证提示框
 *		forceSubmit()						//form方法，忽略验证结果，强制提交
 *
 * jQuery.validationEngine 验证引擎重点属性和方法
 *	属性：
 *		defaults			//默认配置
 *		rules				//缺省规则定义
 *		initiators			//缺省初始安装器定义
 *		validators			//缺省验证器定义
 *	方法：
 *		validate(elm)		//验证input控件方法
 *		addRules(rules)		//添加新验证规则，规则定义视调用该规则的验证器而定
 *
 * 验证元素扩展属性：
 * 		isGlobalValidation {Boolean} 表单执行全局验证时为true，单独验证时为false
 *		validationSettings {Dictionary} 验证设置
 *		validationPrompts {Array} 绑定的验证提示框引用集
 *
 */
(function ($) {
    $.validationEngine = {
		fileName: "validation.js", 	///文件名更改时,需要修改该项,以便能正确引入CSS
        // 默认设置
        defaults: {
            addRules: {}, 				// 添加临时使用的验证规则
            validateOn: "blur", 		// <events> 触发验证的事件
            showHelpOn: "focus", 		// <event> 触发提示帮助信息的事件，推荐保留“focus”
            clickToClosePrompt: true, 	// 是否点击关闭提示
            liveEvent: true, 			// 事件live
            focusClosePrompt: true,
            promptPosition: "topRight", // 弹出信息窗口位置，可取值 "topLeft", "topRight", "bottomLeft", "centerRight", "bottomRight"
            promptZIndex: 200,
			validateHiddens: false,		// 验证隐藏元素
			allowSubmit:true,			// 允许验证后提交
			showPromptOnValidate:true,	// 验证时弹出提示框, false:只返回验证结果
            validateOnSubmit:true, 		// 提交时执行验证
			invalidClass:'invalid',		// 无效时控件类样式
			validClass:'valid',			// 有效时控件类样式
            promptClasses: {			// 提示框架样式配置，建议使用默认设置
                'error': 'validateError',
                'pass': 'validatePass',
                'load': 'validateLoad',
                'help': 'validateHelp',
                'debug': 'validationDebug'
            }
        },
        promptTypes: { 'error': 'error', 'pass': 'pass', 'load': 'load', 'help': 'help', 'debug': 'debug' },
        // 缺省规则定义
        rules: {
            /// 通用格式参量: {0}:this.value
            "required": {
                //提示文本
                alertText: "* 该项是必填项",
                //至少选一项
                alertTextCheckboxMultiple: "* 请选择一个选项",
                //必选项
                alertTextCheckbox: "* 请确认您已经把它勾选了"
            },
            "length": {
                /// 格式参量: {0}:value {1}:minLength, {2}:maxLength, {3}:currentLength
                lessText: "* 您输入的字符太短, 请输入 {1}-{2} 个字符",
                moreText: "* 您输入的字符太长, 请输入 {1}-{2} 个字符"
            },
            "bytes": {
                /// 格式参量: {0}:value {1}:minBytes, {2}:maxBytes, {3}:min/2, {4}:max/2, {5}:currentBytes, {6}:currentLength
                lessText: "* 您输入的字符太短<br/>* 请输入{1}-{2}个英文或{3}-{4}个汉字",
                moreText: "* 您输入的字符太长<br/>* 请输入{1}-{2}个英文或{3}-{4}个汉字"
            },
			/// repeat 范例
            "pwdRepeat": {
				repeat: '#myPassword', //绑定repeat元素，jQuery selector
				alertText: "* 输入的密码不一致"
			},
            /// ajax范例 
            "ajaxDemo": {
                url: "ajaxDemo.aspx?value={0}",
                data: "username={0}",
                dataType: 'json',
                result: function (response) {
                    /// this -> Pointed to input Element
                    return response.isPass	//retrun [Boolean] true:pass, false:error
                },
                /// errorText 及 successText可使用字符串形式，也可使用函数返回字符串的形式
                /// response: ajax请求结果对象
                errorText: function (response) {
                    return "* " + response.errorMsg + "{0}"; // String
                },
                successText: "* 恭喜，{0}可以使用"
            },
            /// ajax范例2 
            "ajaxDemo2": {
                url: "ajaxDemo.aspx?",
                data: function (name) {
                    /// data 可使用原ajax形式，也可使用返回data数据函数回调
                    /// this -> Pointed to input Element
                    return {
                        username: this.value //这里可以使用this指定输入控件
                    }
                },
                dataType: 'text', // 返回的是文本
                result: function (response) {
                    return response == "true" //retrun [Boolean] true:pass, false:error
                },
                errorText: function (response) {
                    return "* " + this.value + "已被占用"; // String
                },
                successText: "* 恭喜，{0}可以使用"
            }
        }
    };
    $.extend($.fn, {
        //表单验证构造器
        validateable: function (settings) {
            /// <sumary>
            ///		表单验证构造器
            /// </sumary>
            /// <param name="settings" type="Object">
            ///		可选，验证器配置参数
            /// </param>
            /// <returns name="jquery" type="jQueryObject" />
            var engine = $.validationEngine;
            var _toString = Object.prototype.toString;
            settings = _toString.apply(settings) === "[object Object]" ? settings : {};
            settings = $.extend({}, engine.defaults, settings);
            return this.each(function () {
                this.validationSettings = $.extend({}, settings);
				var isFrom = false;
                if ($(this).isForm()) {
					isForm = true;
					$(this).submit(_onsubmit);
                }
                var form = this;
                this.validateInputs = [];
                
                $(this).find('[validation][type!=checkbox]').live(settings.validateOn, _validate).each(_initialize).live('focus', _focusHandle)
                $(this).find('[validation][type=checkbox]').live("click", _validate).each(_initialize)
                function _focusHandle() {
                    if (settings.focusClosePrompt) { $(this).closeValidationPrompt('all') }
					$(this).removeClass([settings.validClass,settings.invalidClass].join(' '));
                }
                function _validate(e) {
                    engine.validate(this, this.validationSettings)
                }
                function _initialize(i) {
                    form.validateInputs.push(this);
                    this.validationPrompts = this.validationPrompts || [];
                    this.validationSettings = $.extend({}, settings); // clone settings as element property
                    engine.init(this, this.validationSettings)
                }
				// 提交时处理
                function _onsubmit(e) {
                    return settings.allowSubmit? (settings.validateOnSubmit ? $(this).validate() : true):false;
                }
            });
        },
        //输入控件验证方法
        validate: function (settings) {
            /// <sumary>
            ///		输入控件验证器
            /// </sumary>
            /// <param name="settings" type="Object">
            ///		可选，验证器配置参数
            /// </param>
            /// <returns name="isPass" type="Boolean" />
            var isPass = true;
            var engine = $.validationEngine;
            this.each(function () {
				this.validationSettings = this.validationSettings||$.extend({},engine.defaults);
                if ($(this).find('input,select,textarea').size()) {
                    $.each(this.validateInputs, function (i) {
                        $.extend(this.validationSettings, settings || {});
						this.isGlobalValidation = true;
                        isPass = engine.validate(this) && isPass;
                    });
					this.validationResult = isPass;
                } else {
                    $.extend(this.validationSettings, settings || {});
					this.isGlobalValidation = false;
                    isPass = isPass && engine.validate(this);
                }
            });
            return isPass;
        },
        //验证后调用
		onValidated:function(callback){
			return this.each(function(){
				this.callbackAfterValidated = $.isFunction(callback)?callback:function(){};
			});
		},
		//验证信息弹出框
        validationPrompt: function (msg, type, settings) {
            /// <sumary>
            ///		弹出验证信息
            /// </sumary>
            /// <param name="message" type="String">
            ///		消息内容，必须
            /// </param>
            /// <param name="type" type="String">
            ///		1: "error" - 错误信息
            ///		2: "help" - 帮助信息
            ///		3: "load" - 加载信息
            /// </param>
            /// <param name="settings" type="Object">
            ///		可选，扩展设置信息
            /// </param>
            return this.each(function () {
                var engine = $.validationEngine;
				this.validationSettings = this.validationSettings||$.extend({},engine.defaults);
                settings = $.extend(this.validationSettings, settings || {});
                engine.buildPrompt(this, msg, type, settings);
            });
        },
        validationPromptError: function (msg) {
            /// <sumary>
            ///		弹出错误信息
            /// </sumary>
            /// <param name="helpMsg" type="String">
            ///		帮助消息内容
            /// </param>
            return this.each(function () {
                $(this).validationPrompt(msg, 'error')
            });
        },
        validationPromptHelp: function (msg) {
            /// <sumary>
            ///		弹出帮助信息
            /// </sumary>
            /// <param name="helpMsg" type="String">
            ///		帮助消息内容
            /// </param>
            return this.each(function () {
                $(this).validationPrompt(msg, 'help')
            })
        },
        closeValidationPrompt: function (type) {
            /// <sumary>
            ///		清除弹出信息
            /// </sumary>
            return this.each(function () {
                if ($(this).isForm()) {
                    $.each(this.validateInputs, function (i, input) {
                        $.validationEngine.removePrompt(input, type)
                    })
                } else {
                    $.validationEngine.removePrompt(this, type)
                }

            })
        },
        validationDebug: function (msg) {
            return this.each(function () {
                $(this).validationPrompt("错误:<br/>" + msg, 'debug')
            });
        },
        //强制表单提交，忽略验证，直接提交
        forceSubmit: function () {
            /// <sumary>
            ///		强制提交表单，将忽略验证
            /// </sumary>
            /// <param name="helpMsg" type="String">
            ///		帮助消息内容
            /// </param>
            return this.each(function () {
                if (!$(this).isForm) return false;
                this.submit();
            })
        },
        //控件判断
        isForm: function () { return /form/i.test($(this).attr('tagName')) },
        isCheckbox: function () { return $(this).is('input[type=checkbox]') }
    })
    $.extend($.validationEngine, {
		prompts:{},
        loadValidation: function (caller) {		// GET VALIDATIONS TO BE EXECUTED
            rulesParsing = $(caller).attr('class');
            rulesRegExp = /\[(.*)\]/;
            getRules = rulesRegExp.exec(rulesParsing);
            if (getRules == null) return false;
            str = getRules[1];
            pattern = /\[|,|\]/;
            result = str.split(pattern);
            var validateCalll = $.validationEngine.validateCall(caller, result);
            return validateCalll;
        },
        // 控件启动器定义(初始化启动器)
        initiators: {
            "help": function (rule, engine, settings) {
                $(this).unbind(settings.showHelpOn, _helpHandle).bind(settings.showHelpOn, _helpHandle).
					unbind('keyup', _keyupHandle).bind('keyup', _keyupHandle).unbind('blur', _focusOutHandle).bind('blur', _focusOutHandle);
                var _this = this;
                var timer = null,
					timeout = 2500;
                function _helpHandle(e) {
                    _clearHelp()
                    if (this.value) timer = setTimeout(_showHelp, timeout)
                    else _showHelp();
                }
                function _keyupHandle(e) {
                    _clearHelp()
                    if (!this.value) { _showHelp() }
                    else { timer = setTimeout(_showHelp, timeout) }
                }
                function _showHelp() {
					if(rule.helpText) $(_this).validationPromptHelp(rule.helpText)
                }
                function _focusOutHandle() { _clearHelp() }
                function _clearHelp() { clearTimeout(timer); $(_this).closeValidationPrompt('help') }
            },
            "keydown": function (rule, engine, settings) {
                var SF = engine.stringFormat;
                var isPass = true;
                var alertText = "";
                var pattern = engine.evalRegExp(rule.regex);
                if (pattern && !pattern.test(this.value)) {
                    isPass = isPass && false;
                    alertText += SF(rule.alertText, this.value) + '<br/>';
                }
                if ($.isFunction(rule.func)) {
                    var fnResult = rule.func.call(this, rule);
                    isPass = isPass && fnResult[0];
                    alertText += fnResult[1];
                }
                if (!isPass) {
                    $(this).validationPrompt('error', alertText);
                }
            }
        },
        // 验证器定义
        validators: {
            /// <sumary>
            ///		验证器定义
            /// </sumary>
            /// <param name="rule" type="Object">
            ///		规则对象
            /// </param>
            /// <returns name="validationResult" type="Array">
            ///		验证结果 [isPass, alertText ]
            /// </param>
			// required规则验证器，直接使用required规则验证
            "required": function (rule, engine) {
                rule = engine.rules["required"];
                var SF = engine.stringFormat;
                var isPass = true;
                var alertText = "";
                var callerType = $(this).attr("type");
                if (/text|password|textarea/i.test(callerType)) {
                    if (!this.value) {
                        isPass = false;
                        alertText += SF(rule.alertText, this.value) + '<br/>';
                    }
                }
                if (/radio|checkbox|textarea/i.test(callerType)) {
                    var callerName = $(this).attr("name");

                    if ($("input[name='" + callerName + "']:checked").size() == 0) {
                        isPass = false;
                        if ($("input[name='" + callerName + "']").size() == 1) {
                            alertText += rule.alertTextCheckbox + "<br />";
                        } else {
                            alertText += rule.alertTextCheckboxMultiple + "<br />";
                        }
                    }
                }
                if (callerType == "select-one") {
                    if (!$(this).val()) {
                        isPass = false;
                        alertText += rule.alertSelectOne + "<br />";
                    }
                }
                if (callerType == "select-multiple") {
                    if (!$(this).find("option:selected").val()) {
                        isPass = false;
                        alertText += rule.alertSelectOne + "<br />";
                    }
                }
                return [isPass, alertText]
            },
			// 正则 验证器
            "regex": function(){
				return $.validationEngine.regValidator.apply(this, arguments)
			},
			// 验证函数 验证器 regex 与 vfun 规则可合并为一个规则中定义	
			"vfun": function(){
				return $.validationEngine.regValidator.apply(this, arguments)
			},		
			// 长度 规则验证器
            "length": function (rule, engine) {
                // RPs : range points[min,max]
                var RPs = rule.name.split(/\s*[-,]\s*/);
                rule = engine.rules["length"]; // 重置规则为 length
                var min = parseInt(RPs[0]),
					max = parseInt(RPs[1])||min;
                var vLength = this.value.length;
                var SF = engine.stringFormat;
                if (vLength < min) return [false, SF(rule.lessText, this.value, min, max, this.value.length)];
                if (vLength > max) return [false, SF(rule.moreText, this.value, min, max, this.value.length)];
                return [true]
            },
			// 字节 规则验证器
            "bytes": function (rule, engine) {
                // RPs : range points[min,max]
                var RPs = rule.name.split(/\s*[-,]\s*/);
                rule = engine.rules["bytes"]; // 重置规则为 bytes
                var min = parseInt(RPs[0]),
					max = parseInt(RPs[1])||min;
                var length = this.value.length;
                var bytes = this.value.replace(/[^\x00-\xff]/g, "**").length;
                var SF = engine.stringFormat;
                if (bytes < min) return [false, SF(rule.lessText, this.value, min, max, min / 2, max / 2, this.value.length)];
                if (bytes > max) return [false, SF(rule.moreText, this.value, min, max, min / 2, max / 2, this.value.length)];
                return [true]
            },
			// 调用函数执行验证， 被调用的函数必须是全局函数
			// 仅返回 [isPass, alertText] 数据规则时有效
            "call": function (fn) {
                return eval($.trim(fn.name)).call(this, this.value)
            },
			// ajax 规则验证器
            "ajax": function (rule, engine) {
                if (!this.validationResult || (this.isGlobalValidation&&!rule.validateOnSubmit)) return [true];
                var caller = this;
                var SF = engine.stringFormat;
                var isPass = true;
                var data = {};
                var alertText = "";
                if (rule.loadText) { $(this).validationPrompt(rule.loadText, 'load') }
                $.ajax($.extend({
                    dataType: 'json',
                    type: 'get'
                }, (function (ru) {
                    ru.url = SF(rule.url, caller.value);
                    ru.data = $.isFunction(rule.data) ?
							rule.data.call(caller, caller.value) :
							typeof rule.data == "string" ? SF(rule.data, caller.value) : $.extend({}, rule.data);

                    data = ru.data;
                    return $.extend({},rule,ru);
                })({}), {
                    async: false,
                    data: $.extend(data, { validateValue: caller.value }),
                    success: function (response) {
                        isPass = rule.result.call(caller, response);
                        alertText = isPass ? rule.successText : rule.errorText;
                        alertText = SF($.isFunction(alertText) ? alertText.call(caller, response) : alertText, caller.value);
                        if (isPass && !!rule.successText) {
                            clearTimeout(caller.validatePassTimer || null);
                            $(caller).validationPrompt(alertText, 'pass')
                            caller.validatePassTimer = setTimeout(function () { $(caller).closeValidationPrompt('pass') }, 3000);
                        }
                    }
                }
				));
                $(this).closeValidationPrompt('load')
                return [isPass, alertText]
            },
			// 重复确认 验证器
            "repeat": function (rule, engine) {
                var $os = $(rule.repeat);
                if ($os.size() < 1) $(this).validationDebug('repeat: CSS选择器 "' + rule.repeat + '" 无法找到元素！');
                if ($os.size() > 1) $(this).validationDebug('repeat: CSS选择器 "' + rule.repeat + '" 返回元素过多，只能返回一个！');

                var isPass = $os.val() == this.value;
                return [isPass, $.validationEngine.stringFormat(rule.alertText, this.value)]
            }
        },
		// regex 与 vfun 验证器
		regValidator:function (rule, engine) {
			var SF = engine.stringFormat;
			var isPass = true;
			var alertText = "";
			if(Object.prototype.toString.apply(rule.regex) === '[object RegExp]' && !rule.regex.test(this.value)){
				isPass = isPass && false;
				alertText += rule.alertText+'<br/>';
			}
			if ($.isFunction(rule.vfun)) {
				var r = rule.vfun.call(this, rule);
				isPass = isPass && r[0];
				alertText += r[0]?'':(r[1]+'<br/>');
			}
			return [isPass, SF(alertText, this.value)]
		},
        validate: function (caller) {		
            var engine = $.validationEngine;
            var settings = caller.validationSettings;
			if( !$(caller).filter(":visible").size() && !settings.validateHiddens ) return true; //如果元素隐藏，跳过验证
            var validators = $(caller).attr('validation').match(/\w+\s*(\(.*?\))?/g);
            var results = [];
            caller.validationResult = true;
            $.each(validators, function (i, validator) {
                var valiName = validator.replace(/(\w+)(\(.*\))?/, '$1');
                var ruleNs = validator.replace(/(\w+)(\(.*\))?/, '$2').replace(/\(\s*(.*\S)\s*\)/, '$1').split(/\s*,\s*/);
				$.each(ruleNs, function (i, rn, re) {
                    re = (function (cr, rs) {
                        rs.push(cr[0] === false ? false : cr[0])
                        rs.push(cr[1] || '')
                        rs.push(cr[2] || '')
                        return rs
                    })(engine.validatorCall(caller, valiName, $.extend({}, engine.rules[rn] || {}, { name: rn }), settings), []);
                    results.push(re);
                    if (!re[0]) caller.validationResult = false;
                });
            });
            var isPass = true;
            var alertText = [];
            $.each(results, function (i, r) {
                if (r[0] == 'optional') {
                    isPass = true;
                    errorText = passText = '';
                    return false
                }
                alertText.push(r[0] ? '' : r[1])
                isPass = isPass && r[0];
            });
            alertText = alertText.join('<br/>').replace(/(<br\s*\/>){2,}/ig, '<br/>').replace(/(<br\s*\/>)*$/i, '').replace(/^(<br\s*\/>)*/i, '');
			if(settings.showPromptOnValidate){
				if (!isPass) {
					$(caller).validationPromptError(alertText);
				} else {
					$(caller).closeValidationPrompt('error');
				}
			}
			var cF = settings.invalidClass, cV = settings.validClass;
			$(caller).removeClass(isPass?cF:cV).addClass(isPass?(caller.value?cV:""):cF);
			//验证后事件回调
			( caller.callbackAfterValidated||function(){} ).call( caller, isPass, alertText );
            return isPass
        },
        validatorCall: function (caller, validator, rule, settings) {	// 执行验证器
			var engine = $.validationEngine;
            return (validator == "required" || engine.validators[validator] && !!caller.value) ? engine.validators[validator].call(caller, rule, engine, settings) : [true];
        },
        init: function (caller, settings) {
            var engine = $.validationEngine;
            var initiators = $(caller).attr('validation').match(/\w+\s*(\(.*?\))?/g);
            $.each(initiators, function (i, initiator) {
                var initName = initiator.replace(/(\w+)\s*(\(.*\))?/, '$1');
                var ruleNs = initiator.replace(/(\w+)\s*(\(.*\))?/, '$2').replace(/\(\s*(.*\S)\s*\)/, '$1').split(/\s*,\s*/);
                $.each(ruleNs, function (i, rn) {
                    engine.initiatorCall(caller, initName, $.extend({}, engine.rules[rn] || {}, { name: rn }), settings)
                });

            });
        },
        initiatorCall: function (caller, initiator, rule, settings) {		// 执行启动器
            var engine = $.validationEngine;
            return engine.initiators[initiator] ? engine.initiators[initiator].call(caller, rule, engine, settings) : true
        },
        buildPrompt: function (caller, promptText, type) {			// ERROR PROMPT CREATION AND DISPLAY WHEN AN ERROR OCCUR
            var engine = $.validationEngine;
            var settings = caller.validationSettings;
            var vPos = settings.promptPosition.match(/top|bottom/i)[0].toLowerCase();
            var hPos = settings.promptPosition.match(/left|right/i)[0].toLowerCase();
            type = engine.promptTypes[type] || 'error';
            var prompt;
            caller.validationPrompts = caller.validationPrompts || [];
            $.each(caller.validationPrompts, function (i, p) {
                if (p.promptType == type) $(p).remove()
            })
            // 提示框对象
            $([
			'<div class="validationPrompt ', engine.defaults.promptClasses[type] || '', ' ' + settings.promptPosition, '" >',
				'<div class="validationPromptWrap">',
					'<div class="validationPromptContent">', promptText, '</div>',
					'<div class="validationPromptArrow ', , '">',
						'<div class="validationPromptArrowWrap">',
							_getArrowHtml(),
						'</div>',
					'</div>',
				'</div>',
			'</div>'].join('')).each(function () {
			    prompt = this;
				prompt.caller = caller;
			    caller.validationPrompts.push(this);
			    this.validateCaller = caller;
			    this.promptType = type;
			    $(this).css({ opacity: 0.85, zIndex: settings.promptZIndex })
			}).appendTo('body');
            engine.updatePromptPosition(prompt);
            // 取箭头HTML
            function _getArrowHtml() {
                return (function (lns) {
                    for (var i = 0; i < 10; lns.push('<div class="line' + (++i) + '" style="z-index:' + (100 - i) + '; top:' + (vPos != "top" ? i - 1 : 10 - i) + 'px"></div>')) { }
                    if (vPos == "top") lns.reverse(); // 如果顶部，反转三角 if top, reverse Arrow html
                    return lns.join('')
                })([])
            }

        },
        updatePromptPosition: function (prompt) {
            var caller = prompt.validateCaller; //get prompt caller
            var $prompt = $(prompt), $caller = $(caller);
            var cpos = $caller.offset();
            var cw = $caller.width(),
				ch = $caller.height();
            var pw = $prompt.width(),
				ph = $prompt.height();
            var engine = $.validationEngine;
            var settings = caller.validationSettings;
            var position = settings.promptPosition,
				vPos = position.match(/top|bottom/i)[0].toLowerCase(),
				hPos = position.match(/left|right/i)[0].toLowerCase();
            var top, left;
            if (vPos == "top") top = cpos.top - ph - 2;
            else top = cpos.top + ch;
            if (hPos == "left") left = cpos.left;
            else left = cpos.left + cw - 25;
            $prompt.css({
                top: top + 'px',
                left: left + 'px'
            });
        },
        removePrompt: function (caller, type) {
            var engine = $.validationEngine;
            var prompts = caller.validationPrompts||[];
            var newPrompts = [];
            var types = type ? type.split(/\s*[\s,]\s*/) : ['all'];
            var filters = [];
            $.each(types, function (i, t) {
                filters.push({ 'all': 'all'}[t] || engine.promptTypes[t] || '')
            });
            filters = filters.join('');
            var isAll = /all/.test(filters);
            $.each(prompts, function (i, p) {
                if (isAll || filters.indexOf(p.promptType) >= 0) { $(p).remove() }
                else newPrompts.push(p)
            })
            caller.validationPrompts = newPrompts;
			
        },
        addRules: function (rules) {
            var engine = $.validationEngine;
            if (Object.prototype.toString.apply(rules) != '[object Object]') {
                throw new Error("addRules() : requires an Object argument!")
            }
            $.extend(engine.rules, rules);
        },
        stringFormat: function () {
            /// <sumary>
            ///		字符串参数格式化
            /// </sumary>
            /// <param name="string" type="String">
            ///		要格式化的字符串
            /// </param>
            /// <param name="values" type="Array">
            ///		参数序列
            /// </param>
            var args = arguments;
            return args[0].replace(/\{\d+\}/g, function () {
                var index = parseInt(arguments[0].replace(/\{(\d+)\}/, '$1'));
                return (args[index + 1] || '').toString()
            });
        },
        evalRegExp: function (regStr) {
            return /^\/.*\/\w*$/.test(regStr) ? eval(regStr) : '';
        }
    });
    
	$('.validationPrompt').live('click', function (e) {
        $(this).fadeOut(300, function () {
            $(this).remove()
        });
    }).live('mouseenter', function () {
        $(this).animate({ opacity: 1 }, 500).css({ "z-index": 9999 })
    }).live('mouseleave', function () {
        $(this).animate({ opacity: .85 }, 500)
        if (this.style.removeAttribute) {
            this.style.removeAttribute("z-index");
        } else {
            this.style.removeProperty("z-index");
        }
    });
	
	//
	// 获取js引入地址，进而引入验证器CSS
	//
	var $js = $("script"), _egn = $.validationEngine;
	$js.each(function(i,j,x){
		x = this.src.toLowerCase().indexOf(_egn.fileName.toLowerCase());
		if( x >=0 ){
			document.write(_egn.stringFormat('<link type="text/css" rel="stylesheet" href="{0}" />',
				[this.src.substring(0,x),"css/validation.css"].join("")
			));
		}
	});
	
	// 添加常用验证规则(可选代码段)
	$.validationEngine.addRules({
		/// 通用格式参量: {0}:this.value
		"required": {
			//提示文本
			alertText: "* 该项是必填项",
			//select 选择有效项
			alertSelectOne: "* 请选择一个有效的选项",
			//至少选一项
			alertTextCheckboxMultiple: "* 请选择一个选项",
			//必选项
			alertTextCheckbox: "* 请确认您已经把它勾选了"
		},
		"length": {
			// 格式参量: {0}:value {1}:minLength, {2}:maxLength, {3}:currentLength
			lessText: "* 您输入的字符太短, 请输入 {1}-{2} 个字符",
			moreText: "* 您输入的字符太长, 请输入 {1}-{2} 个字符"
		},
		"bytes": {
			// 格式参量: {0}:value {1}:minBytes, {2}:maxBytes, {3}:min/2, {4}:max/2, {5}:currentBytes, {6}:currentLength
			lessText: "* 您输入的字符太短<br/>* 请输入{1}-{2}个英文或{3}-{4}个汉字",
			moreText: "* 您输入的字符太长<br/>* 请输入{1}-{2}个英文或{3}-{4}个汉字"
		},
		// repeat 规则
		"pwdRepeat":{ repeat:'#myPassword', alertText:"* 输入的密码不一致" },
		// 电话
		"telephone": {
			regex: /^[0-9\-\(\)\ ]+$/,
			alertText: "* 您输入了一个无效的电话号码"
		},
		// 手机
		"mobile": {
			regex: /^1[3|4|5|8][0-9]\d{8}$/,
			alertText: "* 您输入了一个无效的手机号码"
		},
		// 邮件地址
		"email": {
			regex: /^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/,
			alertText: "* 您输入了一个无效的邮件地址"
		},
		// 日期
		"date": {
			regex: /^[0-9]{4}\-\[0-9]{1,2}\-\[0-9]{1,2}$/,
			alertText: "* 无效的时间格式，请输入 YYYY-MM-DD 格式的时间"
		},
		// 只允许数字
		"onlyNumber": {
			regex: /^\d+$/,
			alertText: "* 只可以输入数字"
		},
		// 邮编
		"zipCode":{
			regex: /^[0-9]{6}$/,
			alertText: "* 请输入6位数字邮编"
		},
		// 禁止特殊符号
		"noSpecialCaracters": {
			regex: /^[0-9a-zA-Z]+$/,
			alertText: "* 请不要输入特殊符号"
		},
		"chineseCharaters":{
			regex: /^[\u4e00-\u9fa5]+$/,
			alertText:"* 请使用中文汉字输入"
		},
		// 只允许字母
		"onlyLetter": {
			regex: /^[a-zA-Z\ \']+$/,
			alertText: "* 只可以输入字母"
		},
		// msn 账号
		"MSN": {
			regex: /^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/,
			alertText: "* 您输入了一个无效的MSN账号"
		},
		// QQ 账号
		"QQ": {
			vfun:function(){ 
				return [
					/^\d{5,11}$/.test(this.value) || /^[a-zA-Z0-9_\.\-]+\@(vip.)?qq.com$/.test(this.value),
					["* 您输入了一个无效的QQ账号","* QQ账号为 5-11 位数字或以下邮箱账号：","&nbsp;&nbsp;name@qq.com","&nbsp;&nbsp;name@vip.qq.com"].join('<br/>')
				]
			}
		}
	});

})(jQuery);	
