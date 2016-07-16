/*
* 说明：团车网-结算中心
* 时间：2013-06-03
* 开发：辉
*
* 修改：
* js开发是在$ v1.8.2框架基础之下工作
* 
*/


$(function(){
	
	_hAuto();
	var loadStr = '<p class="tcenter loading">加载中，请稍候！</p>';
	
	//分页
	$('.page li:not(.record)').live('click', function() {
		var page = $(this).attr('page');
	    $(":hidden[name='nowpage']").val(page);
	    $('#search-form').submit();
		return false;
	});
	
	$(".backcompany").live('change',function(){
		var index = $(this).val();
		$('select[name=bankAccount] option').attr('disabled','disabled').attr('style','color:#ccc');
		$('select[name=bankAccount]').val('');
		if(index == 1) {
			$('select[name=bankAccount] option:eq(1)').removeAttr('disabled').removeAttr('style');
		} else {
			$('select[name=bankAccount] option:gt(1)').removeAttr('disabled').removeAttr('style');
		}
	});
	
	/*-----------二期功能--------------*/
	$('#invalid').live('click', function() {
		id = $(':radio[name=id]:checked').val();
		$.get('/SearchBill/invalid',{id:id},function(data) {
			data = eval("("+data+")");
			alert(data.info);
			if(data.status == 1) {
				$("#search-form").submit();
			}
		});
	});
	$('#reversal').live('click', function() {
		id = $(':radio[name=id]:checked').val();
		$.get('/SearchBill/reversal',{id:id},function(data) {
			data = eval("("+data+")");
			alert(data.info);
			if(data.status == 1) {
				$("#search-form").submit();
			}
		});
	});
	
	$("#addform :input[name=order_sn]").live('blur',function() {
		var $this = $(this);
		if($(this).val()) {
			$.get('/Js/issetOrder',{sn:$(this).val(),id:$(':input[name=agreementid]').val()},function(msg) {
				if(msg) {
					$this.after('<strong><label class="error" for="order_sn" generated="true" style="display: block;">'+msg+'.</label></strong>');
					$this.val('');
				} else {
					$.get('/Js/getOrderPeople',{sn:$this.val()},function(msg) {
						$('#sn-msg').html(msg);
					});
				}
			});
		}
	}).live('click',function() {
		autocom(":input[name=order_sn]",'/Js/autocomplete',{model:'Cw_order',attribute:'sn',where:'agreementid='+$('#addform #agreementid').val()});
	});
	
	$('#addform input[name=back_time]').live('click',function() {
		WdatePicker({
			isShowClear:false,
			qsEnabled:false,
			dateFmt:'yyyy-MM-dd',
			maxDate:'%y-%M-%d'
		});
	});
	
	/**
	 * 报表生成
	 */
	$('#create-report').live('click',function() {
		var $this = $(this);
		$.ajax({
	        url: $this.attr('url'),               // 提交的页面
	        data: $('#'+$this.attr('form')).serialize(), // 从表单中获取数据
	        type: "GET",                   // 设置请求类型为"POST"，默认为"GET"
	        success: function(data) {
	        	$('#'+$this.attr('form')).submit();
	        }
	    });
	    return false;
	});
	$('.create-report').live('click',function() {
		$.get($(this).attr('href'));
		$("#search-form").submit();
		return false;
	});
	
	/**
	 * 字段唯一验证
	 */
	$('.unique').live('blur',function() {
		model = $(this).attr('model');
		attribute = $(this).attr('attr');
		max = $(this).attr('count') ? $(this).attr('count') : 0;
		value = $(this).val();
		var $this = $(this);
		$.get('/Js/unique',{model:model,attribute:attribute,value:value,max:max},function(msg) {
			if(msg) {
				$this.after('<strong><label class="error" for="'+attribute+'" generated="true" style="display: block;">'+msg+'.</label></strong>');
				$this.val('');
			}
		});
	});
	
	// 左侧导航隐藏展开按钮功能
	$(".toggleBtn").live("click",function(){
		$(this).parent().toggleClass("hide-left-bar");
	});
	
	// 报表页面按钮加载页面
	$(".jump-link-btn").live("click", function(e){
		e.preventDefault();
		var wrap = $(this).attr("data-wrap");
		var url = $(this).attr("href");
		$("."+wrap).html(loadStr);
		delayLoad();
		$("."+wrap).load(url,function(){
			_hAuto();
		});
	});
	
	// 全选 反选
	$(".selectAll").live("click", function(){
		var trues = $(this).attr("checked");
		var wtable = $(this).attr("data-type");
		if(trues){
			$("."+wtable+" tbody tr td").find('input[type="checkbox"]').attr("checked",true);
		}else{
			$("."+wtable+" tr td").find('input[type="checkbox"]').removeAttr("checked");
		}
	});
	
	// 管理科目里展开收起
	$("em.ico-plus").live("click", function(){
		var num = $(this).attr("data-num");
		$(this).toggleClass("ico-minus");
		if($('.sub_'+num).hasClass("subTr")){
			// 一级
			
			if($('.sub_'+num).hasClass("hide")){
				$('.sub_'+num).removeClass("hide");	
			}else{
				$('.sub_'+num).addClass("hide");
				$('.sub_'+num).next(".subTr-sub").addClass("hide");
				//for(var i = 1; i <= $('.sub_'+num).size(); i++){
				//	$('.sub_'+num+"_"+i).addClass("hide");
				//}
				$('.sub_'+num+" .tNum .ico-plus").removeClass("ico-minus");
			}
		}else{
			// 二级
			if($('.sub_'+num).hasClass("hide")){
				$('.sub_'+num).removeClass("hide");
			}else{
				$('.sub_'+num).addClass("hide");
			}
		}
		_hAuto();
	});


	$(".inptime").live("click",function(){
		WdatePicker({
			isShowClear:true,
			qsEnabled:false,
			readOnly:true
		})
	});
// 日期范围限制
	$(".inptimeRand1").live("click",function(){
		WdatePicker({
			isShowClear:true,
			qsEnabled:false,
			readOnly:true,
			maxDate:$(".inptimeRand2").val()
			});
	});
	$(".inptimeRand2").live("click",function(){
		WdatePicker({
			isShowClear:true,
			qsEnabled:false,
			readOnly:true,
			minDate:$(".inptimeRand1").val()
			});
	});
	
	// 添加协议日期范围限制
	$("#inptimeRand1").focus(function(){
		WdatePicker({
			isShowClear:true,
			qsEnabled:false,
			readOnly:true
			});
	});
	$("#inptimeRand2").focus(function(){
		var data1=$("#inptimeRand1").val().replace(/-/g,"/");
		var data2;
		var val=$('select[name=cooperationType]').val();
		if(val==3){
			data1=(new Date(data1).valueOf())+(30*24*3600*1000);
			data2=data1+(150*24*3600*1000);
			data1=new Date(data1);
			data1=data1.getFullYear()+"-"+(data1.getMonth()+1)+"-"+data1.getDate();
			data2=new Date(data2);
			data2=data2.getFullYear()+"-"+(data2.getMonth()+1)+"-"+data2.getDate();
			
		}else{
			data2=(new Date(data1).valueOf())+(365*24*3600*1000);
			data2=new Date(data2);
			data2=data2.getFullYear()+"-"+(data2.getMonth()+1)+"-"+data2.getDate();
		}
		WdatePicker({
			isShowClear:true,
			qsEnabled:false,
			readOnly:true,
			maxDate:data2,
			minDate:data1
	});

	});
	
	// 添加二级科目
	$(".addNextItem").live("click", function(){
		var key = parseInt($(".fstItemWrap").attr("data-scdNum"));
		var fst_num = $(".fst-num").val();
		var str2 = '<div class="scdItemWrap" data-thdnum="0">'
				  +'<div class="a-row scdItem" data-scdNum="'+key+'">'
				  +'	<span class="item_t">二级科目编码：<em class="fst-num-scd">'+fst_num+'</em><input type="text" class="w40 inpt scd-num {required:true}" name="child['+key+'][sn]"/></span>'
				  +'	<span class="item_t">科目描述：<input type="text" class="w100 inpt {required:true}" name="child['+key+'][name]"/></span>'
				  +'	<span>'
				  +'		<a href="javascript:;" class="a-btn addEndItem"><em>添加下级科目</em></a>'
				  +'	    <a href="javascript:;" class="a-btn delItem"><em>删除</em></a>'
				  +'	</span>'
				  +'</div></div>'
		$(".fstItemWrap").append(str2);	
		$(".fstItemWrap").attr("data-scdNum",key+1);	
	});
	
	// 添加三级科目
	$(".addEndItem").live("click", function(){
		var scd_num = $(".fst-num").val()+$(this).parent().parent().find(".scd-num").val();
		var key = $(this).parent().parent().attr("data-scdNum");
		
		var keys = parseInt($(this).parent().parent().parent().attr("data-thdnum"));
		
		var str2 = '<div class="a-row thdItem">'
					  +'	<span class="item_t">三级科目编码：<em class="scd-num-thd">'+scd_num+'</em><input type="text" class="w40 inpt scd-num {required:true}" name="child['+key+'][child]['+keys+'][sn]"/></span>'
					  +'	<span class="item_t">科目描述：<input type="text" class="w100 inpt {required:true}" name="child['+key+'][child]['+keys+'][name]"/></span>'
					  +'	<span>'
					  +'	    <a href="javascript:;" class="a-btn delItem"><em>删除</em></a>'
					  +'	</span>'
					  +'</div>'
		
		if($(this).parent().parent().next().hasClass("thdItemWrap")){
			$(this).parent().parent().next(".thdItemWrap").append(str2);
		}else{
			$(this).parent().parent().parent().append('<div class="thdItemWrap"></div>');
			$(this).parent().parent().next(".thdItemWrap").append(str2);
		}
		$(this).parent().parent().parent().attr("data-thdnum",keys+1);
	});
	
	// 一级科目编码改变时二级三级跟着改变
	$(".fst-num").live("keyup", function(){
		var num1 = $(".fst-num").val(); 
		var num2 = num1+$(".scd-num").val();
//		alert($(".fst-num-scd").size());
		if($(".fst-num-scd").size() > 0){
			$(".fst-num-scd").html(num1);
		}
		if($(".scd-num-thd").size() > 0){
			$(".scd-num-thd").html(num2);
		}
	});
	// 二级科目编码改变时三级跟着改变
	$(".scd-num").live("keyup", function(){
		var num2 = $(".fst-num").val()+$(this).val(); 
		if($(this).parent().parent().next(".thdItemWrap").size() > 0){
			$(this).parent().parent().nextAll(".thdItemWrap").find(".scd-num-thd").html(num2);
		};
	});
	
	
	// 删除科目
	$(".delItem").live("click", function(){
		// 点击一级科目删除
		if($(this).parent().parent().hasClass("fstItem")){
			// 寻找二级三级科目
			$(this).parent().parent().nextAll(".scdItemWrap").remove();
			
			// 将一级科目里input里的值清空
			$(this).parent().parent().find("input").val('');
		}
		
		// 点击二级科目删除
		if($(this).parent().parent().hasClass("scdItem")){
			// 寻找三级科目并删除
			if($(this).parent().parent().next().hasClass("thdItemWrap")){
				$(this).parent().parent().next().remove();
			};
			// 删除二级科目
			if($(this).parent().parent().parent().find(".thdItemWrap").size() == 0){
				$(this).parent().parent().parent().remove();
			}else{
				$(this).parent().parent().remove();
			};
		};
		// 点击三级科目删除
		if($(this).parent().parent().hasClass("thdItem")){
			$(this).parent().parent().remove();
		};
		
	});
	

	
	// 弹出层
	$(".show-box-btn").live("click", function(e){
		e.preventDefault();
		var url = $(this).attr("href");
		// 记账弹出层
		if($(this).hasClass("charge-show-box")){
			$(".show-box").css({"width":"90%"});
			var val=$('.chargeTable tbody input:radio[name=id]:checked').val();
			if(!val){
				alert("请先选择一条记录！");
				return false;
			};
			url = url+'?id='+val;
		};
		
		if($(this).hasClass("addCustomer")){
			url+="?cityCode="+$('select[name=cityCode]').val()+"&brandId="+$('select[name=brandId]').val();
		}
		if($(this).hasClass("addLinkman")){
				url+="?cityCode="+
									$('select[name=cityCode]').val()+
					"&brandId="+$('select[name=brandId]').val()+"&customerId="+$('select[name=customerId] :eq(0)').val();
		}
		// 录入回款 搜索按钮
		if($(this).hasClass("tsearch")){
			var id = $(this).parent().prev().attr("id");
			$(".abTable").attr("data-id",id);
		};
		
		// 录入费用 搜索按钮
		if($(this).hasClass("agreement-search")){
			var url = '/Bill/searchAgreement/did/'+$(':hidden[name=did]').val();
			url += '/agreementType/'+$(this).parent().parent().parent().find('.agreement-type :selected').val();
		};
		if($(this).hasClass("wid-box")){
			$(".show-box").css({"width":"90%"});
		};
		if($(this).hasClass("confirmDetail")){
			$.ajax({
				url: url,
				timeout:10000,
				beforeSend:function(){
						$(".show-box").html('<div style="padding:20px 0;"><p class="text-center" style="margin-bottom:20px;"><img class="semload" src="/images/1.gif" /></p><p class="text-center">努力加载中...</p></div>');
						_setPosition();
				},
				success:function(data){
					$(".show-box").html(data);
					_setPosition();
				},
				error:function(){
					$(".show-box").html('<div style="padding:20px 0;"><p class="text-center">加载失败！ 2秒后自动关闭！<a href="javascript:;" class="closeMarkBtn">&times</a></p></div>');
					_setPosition();
					setTimeout(function(){
						$(".closeMarkBtn").click();
					},2000);
				}
			});
			return false;
		}else{
			$(".show-box").load(url,function(){
				_setPosition();
			});
		}
		
	});
	
	// 弹出层
	$("#search-bill-add").live("click", function(e){
		e.preventDefault();
		var val=$('.chargeTable tbody input:radio[name=id]:checked').val();
		type = $('.chargeTable tbody input:radio[name=id]:checked').attr('agreement_type');
		id = $('.chargeTable tbody input:radio[name=id]:checked').attr('agreement_id');
		var url = type == 1 ? '/Accounting/add' : '/Accounting/addFee';
		// 记账弹出层
		if($(this).hasClass("charge-show-box")){
			$(".show-box").css({"width":"90%"});
			if(!val){
				alert("请先选择一条记录！");
				return false;
			};
			url = url+'?id='+id;
		};
		$(".show-box").load(url,function(){
			_setPosition();
		});
	});
	
	$(".closeMarkBtn").live("click", function(){
		if($(this).hasClass("confirmBtn")){
			if($("#sure").is(":hidden")){
				_closeLay();
			}else if($(this).hasClass("cb_1")){
				if(confirm('回款信息还未发送给站长，是否关闭？')){
					_closeLay();	
				};
			}else{
				if(confirm('记账信息还未保存，确认要关闭吗？')){
				_closeLay();	
			}};
		}else if($(this).hasClass("redirectBtn")){
			jumpLink($(this).attr('url'),"rightBarCon");
		}else{
			_closeLay();
		}
	});
	
	// 财务记账 提交
	$(".chargeAdd").live("click", function(){
		var num0 = $('em[name="num0"]').text(); // 协议号
		var num1 = $('input[name="num1"]').val(); // 团次订车确认单号
		var money = $('input[name="money"]').val(); // 金额
		var backpeople = $('select[name="backpeople"] option:selected').text(); // 实际回款人
		var proper = $('select[name="proper"] option:selected').text(); // 科目性质
		var num2 = $('select[name="num2"] option:selected').text(); // 科目编码
		var num2name = $('em[name="num2name"]').text(); // 科目描述
		var backway = $('select[name="backway"] option:selected').text(); // 回款方式
		var backcompany = $('select[name="backcompany"] option:selected').text(); // 公司名称
		var backnum = $('select[name="backnum"] option:selected').text(); // 银行账号
		var backtime = $('input[name="backtime"]').val(); // 回款时间
		var backremark = $('input[name="backremark"]').val(); // 回款备注
		var moneysum = parseFloat($(".chse .moneysum").html());
		if(moneysum){
			moneysum += parseFloat(money);
		}else{
			moneysum = parseFloat(money);
		};
		moneysum = moneysum.toFixed(2);
		
		//alert(num0+"\n"+num1+"\n"+money+"\n"+backpeople+"\n"+proper+"\n"+num2+"\n"+num2name+"\n"+backway+"\n"+backcompany+"\n"+backnum+"\n"+backtime+"\n"+backremark);
		var serial = parseInt($(".chse").attr("data-itemnum"));
		var addStr = '<tr>'
					+'	  <td><input type="checkbox" name="bb" /></td>'
					+'	  <td>'+serial+'</td>'
					+'	  <td>'+num0+'</td>'
					+'	  <td>'+num1+'</td>'
					+'	  <td class="money">'+money+'</td>'
					+'	  <td>'+num2+'</td>'
					+'	  <td>'+num2name+'</td>'
					+'	  <td>'+backpeople+'</td>'
					+'	  <td>'+backway+'</td>'
					+'	  <td>'+backcompany+'</td>'
					+'	  <td>'+backnum+'</td>'
					+'	  <td>'+backtime+'</td>'
					+'	  <td>'+backremark+'</td>'
					+'	  <td>录入人</td>'
					+'	  <td>'+new Date().toLocaleString()+'</td>'
					+'</tr>';
		$(".chse tbody").append(addStr);
		$(".chse .moneysum").html(moneysum);
		$(".chse").attr("data-itemnum",serial+1);
		$(".restForm label").each(function(){
			$("input").val('');
			//alert($("select option").eq(0).val());
			$("select[name='backpeople'] option:eq(0),select[name='proper'] option:eq(0),select[name='num2'] option:eq(0),select[name='backway'] option:eq(0)").attr("selected", "selected");
			$('em[name="num2name"],select[name="backcompany"],select[name="backnum"]').parent().addClass("hide");
		})
	});
	
	// 删除 财务记账 提交的内容
	$(".del").live("click",function(e){
		e.preventDefault();
		var moneysum = parseFloat($(".chse .moneysum").html());
		$('.chse tbody [type="checkbox"]').each(function(){
			if($(this).attr("checked")){
				moneysum -= parseFloat($(this).parent().nextAll(".money").html());
				$(this).parent().parent().remove();
			};
		});
		$(".chse .moneysum").html(moneysum.toFixed(2));
		if(moneysum == 0 ){
			$(".selectAll").removeAttr("checked");
		};
	});
	
	// 复选框与全选给合
	$('.chse tbody [type="checkbox"]').live("click", function(){
		var checkNum = $('.chse tbody [type="checkbox"]').size();
		var checkedNum = 0;
		$('.chse tbody [type="checkbox"]').each(function(){
			if($(this).attr("checked")){
				checkedNum += 1;
			};
		});
		if(checkedNum == checkNum){
			$(".selectAll").attr("checked","checked");
		}else{
			$(".selectAll").removeAttr("checked");
		};
	});
	
	// 查询 查看协议正文
	/*$(".dealBtn").live("click", function(){
		$(this).find(".ico-plus").toggleClass("ico-minus");
		$(".dealConWrap").stop().toggle(function(){
			_resizeMark()
		});
	});*/
	// 查询 开团统计
	$(".orderBtn").live("click", function(){
		$(this).find(".ico-plus").toggleClass("ico-minus");
		$(".orderConWrap").stop().toggle(function(){
			_resizeMark()
		});
	});

	// 弹出层内加载内容展开
	// 过账 过账 未过账表单协议号
	$(".slide-box-btn").live("click",function(e){
		e.preventDefault();
		var url = $(this).attr("href");
		var name = $(this).attr("name");
		$("."+name).parent().removeClass("hide");
		$("."+name).load(url,function(){
			$("."+name+" .closeMarkBtn").hide();
		});
	});

	// 过账 退回过账 显示退回原因 及 取消
	$(".showReasonBtn").live("click",function(){
		$(this).parent().addClass("hide");
		$(".hideReasonBtn").parent().removeClass("hide");
	});
	$(".hideReasonBtn").live("click",function(){
		$(this).parent().addClass("hide");
		$(".showReasonBtn").parent().removeClass("hide");
	});

	
	
	$(".toSubmit").live("click",function(e){
		e.preventDefault();
		if(getSth() == 1){
			alert("亲，您申请的多了！")
		};
		if(getSth() == 2){
			alert("亲，您申请的少了！")
		};
		$(".accountNum").text(getSum());
		if(getSth() == 0) {
			$(this).attr('disabled', true);
			$.ajax({
		        url: $('#save-form').attr('action'),               // 提交的页面
		        data: $('#save-form').serialize(), // 从表单中获取数据
		        type: "POST",                   // 设置请求类型为"POST"，默认为"GET"
		        success: function(data) {
		        	$('.closeMarkBtn').click();
		            $('.rightBarCon').load('/Posting/marketPostBack');
		        }
		    });
		    return false;
		}
		
	});
	
	
	
	//录入费用
	$('#bill-form .agreementsn').live('blur', function() {
		var $this = $(this);
		attr = $(this).attr('attr');
		obj = $(this).attr('model');
		if($(this).val()) {
			$.get('/Js/issetAttr',{val:$(this).val(),attr:attr,obj:obj},function(msg) {
				if(msg && !$this.hasClass("aError")) {
					$this.after('<li class="error"><label class="error" for="order_sn" generated="true" style="display: block;">'+msg+'.</label></li>');
					$this.select();
					$this.addClass("aError");
				} else {
					$('li[class=error]').remove();
					$this.removeClass("aError");
				}
			});
		}
		$.getJSON('/Bill/getAgreementMsg',{sn:$(this).val()},function(data) {
			var obj = $this.parent().parent();
			obj.find('.4s_name').html(data.name);
			if(data.users) {
				$.each(data.users, function(i,item){
					obj.find('.users select').append("<option value='"+i+"'>"+item+"</option>");
				});
			}
			
		});
		$.get('/Bill/getOrderSns',{sn:$(this).val()},function(data) {
			var obj = $this.parent().parent();
			obj.find('.order-select').html(data);
		});
		$.get('/Bill/getUserid',{sn:$(this).val()},function(data) {
			var obj = $this.parent().parent();
			obj.find('.user-select').html(data);
		});
	});
	$('#bill-form .agreementsn').live('click',function() {
		autocom('#bill-form .agreementsn','/Js/autocomplete',{model:$(this).attr('model'),attribute:'sn'});
	});

	//录入费用录入 添加按钮
	$(".addFeeBtn").live("click", function(){
		var sum = (parseInt($(this).attr("data-sum"))+1);
		var user = $('#users').html();
		var subject = $('#subject').html();
		var str = '<tr>'
				 +'	<td><input type="radio" name="a" /></td>'
				 +'	<td>'
				 +'		<select name="data['+sum+'][agreement_type]" class="agreement-type sel {required:true}">'
				 +'         <option value="">请选择</option>'
				 +'			<option value="1">销售协议</option>'
				 +'			<option value="2">管理协议</option>'
				 +'		</select>'
				 +'	</td>'
				 +'	<td class="ap"><input type="text" name="data['+sum+'][agreementsn]" class="fee-sn agreementsn {required:true} inpt stext" model="Cw_agreement" attr="sn" id="dealNum_'+sum+'" /><li class="tsearchli"><a href="javascript:;" class="agreement-search tsearch show-box-btn fw100">s</a></li></td>'
				 +'	<td><select name="data['+sum+'][order_sn]" class="order-select {required:true}"><option value="">请选择</option></select></td>'
				 +'	<td>'
				 +'		<select name="data['+sum+'][userid]" class="{required:true} sel">'
				 +			user
				 +'		</select>'
				 +'	</td>'
				 +'	<td>'
				 +'		<select name="data['+sum+'][subjectid]" class="{required:true} sel subject-select">'
				 +		subject
				 +'		</select>'
				 +'	</td>'
				 +'	<td><input type="text" name="data['+sum+'][money]" class="{required:true,number:true} inpt"/></td>'
				 +' <td><input type="text" class="{required:true} inptime inpt" name="data['+sum+'][related_time]"/></td>'
				 +'	<td><input type="text" name="data['+sum+'][remark]" class="inpt" /></td>'				
				 +'</tr>';
		$(".abTable tbody").append(str);
		$(this).attr("data-sum",sum);
	});
	$('.agreement-type').live('change', function() {
		model = $(this).val() == 1 ? 'Cw_agreement' : 'Cw_manage_agreement';
		$obj = $(this).parent().parent();
		$obj.find('.fee-sn').attr('model', model);
		$obj.find('.order-select').attr('class', $(this).val() == 1 ? 'order-select {required:true}' : 'order-select');
		$.get('/Js/getFeeSubject',{pid: $(this).val() == 1 ? 29 : 38},function(data) {
			$obj.find('.subject-select').html(data);
		});
	});
	

});

$(window).resize(function(){
	_hAuto();
	if(document.all && $("#man_zone").size() > 0){
		var windowHeight = $(window).height()-100;
		$("#man_zone").css({"height":windowHeight});
	}
});

// 弹出层
function _setPosition(){
	var top=($(window).height()-$(".show-box").height())/2 + $(document).scrollTop();
	var left=($(window).width()-$(".show-box").width())/2;
	var height = document.body.scrollHeight;
	if(top < 0){top = 0;};
	$(".show-box").css({"top":top, "left":left});
	$(".mark").css({"height":height});
	$(".show-box, .mark").show();
};
// 关闭弹出层
function _closeLay(){
	$(".show-box, .mark").hide();
};

// 页面改变
function _resizeMark(){
	$(".mark").css({"height":"0"});
	var height = document.body.scrollHeight;
	$(".mark").css({"height":height});
};

// 跳转
function jumpLink(url,wrap){
	//$("."+wrap).html(loadStr);
	$("."+wrap).load(url,function(){
		_closeLay();
	});
};

// 一屏显示
function _hAuto(){
	$(".contents .rightBarWrap").css({"height":"auto"});
	var headHeight = $(".header").height();
	var contentHeight = $(".contents").height();
	var footHeight = $(".footer").height();
	var sumHeight = headHeight + contentHeight + footHeight;
	var wHeight = $(window).height();
	if(wHeight > sumHeight){
		$(".contents .rightBarWrap").css({"height":wHeight - headHeight - footHeight - 70});
	};
};
// 显示加载动画
function delayLoad(){
	setTimeout(function(){
		if($(".loading").size() > 0){
			$(".loading").show();
		};
	},500);
};
function autocom(cla,url,extraParams){
	$(cla).autocomplete(url, {
		width: 200,
		max: 10,
		highlight: false,
		scroll: true,
		scrollHeight: 300,
		extraParams:extraParams
	});
}
//回款方式
var anArray = ['银行转账', '支票'];
var anArrays = [
 ['团车互联网信息服务（北京）有限公司', '团圆网络科技（北京）有限公司 '],
 ['团车互联网信息服务（北京）有限公司', '团圆网络科技（北京）有限公司 ']
 ];
var GC = [
 [
	 ['工商银行北京西单支行0200013309200051789'],
	 ['平安银行花园路支行 11014493026602', '工商银行北京西单支行 0200013309200055947'],
 ],
 [
	 ['工商银行北京西单支行0200013309200051789'],
	 ['平安银行花园路支行 11014493026602', '工商银行北京西单支行 0200013309200055947'],
	
 ]];

function changeBrand(index){
	if($('select[name=brandId]:eq('+(index?index:0)+')').size()>0||$('select[name=bid]').size()>0 ||$('select[name=brandIds]').size()>0){
		var value=[];
		var selected=[];
		var str='<option value="">请选择</option>';
		var obj=$('select[name=bid]').size()?$('select[name=bid]') :$('select[name=brandIds]');
		(obj.size()>0?obj.children('option:gt(0)'):$('select[name=brandId]:eq('+(index?index:0)+') option:gt(0)'))['each'](function(){
			if($(this).attr("selected")){
				selected.push($(this).val());
			}
			value.push($(this).text()+'-'+$(this).val());
		});
		value=value.sort();
		for(var i=0;i<value.length;i++){
			var iv=value[i].split('-');
			str+='<option value="'+iv[1]+'" '+(in_array(iv[1],selected)?"selected":"")+'>'+iv[0]+'</option>'
		}
		(obj.size()>0?obj:$('select[name=brandId]:eq('+(index?index:0)+')'))['html'](str);
	}
}

function in_array(id,array){
	for(var i=0;i<array.length;i++){
		if(id==array[i]) return true;
	}
	return false;
}

$("#dateR1").focus(function(){
	var data=$("#dateR2").val();
	if(data){
		data=data.replace(/-/g,"/");
		data=(new Date(data).valueOf())-(24*3600*1000);
		data=new Date(data);
		data=data.getFullYear()+"-"+(data.getMonth()+1)+"-"+data.getDate();
	}
	var obj={
			isShowClear:true,
			qsEnabled:false,
			readOnly:true
			};
	data?(obj.maxDate=data):"";
	WdatePicker(obj);
});
$("#dateR2").focus(function(){
	var data=$("#dateR1").val();
	if(data){
		data=data.replace(/-/g,"/");
		data=(new Date(data).valueOf())+(24*3600*1000);
		data=new Date(data);
		data=data.getFullYear()+"-"+(data.getMonth()+1)+"-"+data.getDate();
	}
	var obj={
			isShowClear:true,
			qsEnabled:false,
			readOnly:true

	};
	data?(obj.minDate=data):"";
	WdatePicker(obj);

});
