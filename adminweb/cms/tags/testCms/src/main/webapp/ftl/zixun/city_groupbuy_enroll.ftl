<!-- 团购报名 -->
<div class="tc_container_1">
	<div class="tc_title_wrap clearfix">
   		<h2 class="tc_title">团购报名</h2>
    </div>
    <div class="tc_form">
    	<form class="form_2" id="indexForm" action="http://tuanche.com/service/signup/formapply">
        	<input type="text" name="city" id="cityId" class="inpt required hide" value="${city.id?c}">
        	<div class="form_control">
            	<div class="controls">
					<input type="text" name="brand" class="inpt required hide" autocomplete="off" />
                    <span class="help-inline"></span>
                    <div class="selects inpt click_selects" id="brand_wrap">
                        <span class="select_val">请选择品牌</span>
                        <i class="down_arr"></i>
                        <div class="select_con select_brand">
                            <div id="brand_1"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form_control">
            	<div class="controls">
                	<input type="text" name="carstyle" class="inpt required hide" autocomplete="off" />
                    <span class="help-inline"></span>
                    <div class="selects inpt click_selects" id="style_wrap">
                        <span class="select_val">请选择车型</span>
                        <i class="down_arr"></i>
                        <div class="select_con select_brand" id="style_1">
                        </div>
                    </div>
                </div>
            </div>
            <div class="form_control">
            	<div class="controls">
                	<input type="text" name="user" class="inpt" value="" placeholder="姓名" autocomplete="off" maxlength="30" />
                    <span class="help-inline"></span>
                </div>
            </div>
            <div class="form_control">
            	<div class="controls">
                	<input type="text" name="phone" class="inpt" value="" placeholder="手机号" autocomplete="off" />
                    <span class="help-inline"></span>
                </div>
            </div>
            <div class="form_control">
            	<div class="controls">
            		<#assign cityStr = 'http://'+city.py+'.tuanche.com/'>
            	    <input  type="hidden" id="url" name="url"  value="${urlEncoder(cityStr)}"/>
            	    <input  type="hidden" name="bmtype"  value="index"/>
            	    <input type="hidden" name="position" value="4"/>
            	    <button class="app_btn submit_btn">提交报名</button>
                </div>
            </div>
        </form>
    </div>
</div>