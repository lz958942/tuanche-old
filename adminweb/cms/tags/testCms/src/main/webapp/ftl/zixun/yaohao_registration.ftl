 <!-- 快捷报名 -->
 <div class="content_r">
	<div class="quick_apply">
    	<h2 class="title_d">快捷报名</h2>
        <form class="form_3" id="quickForm" action="http://tuanche.com/service/signup/formapply">
            <input type="hidden" name="city" value="${city.id}" autocomplete="off" />
            <div class="form_control">
                <label class="f_label">品牌</label>
                <div class="controls">
                    <input type="text" name="brand" class="inpt ihide" autocomplete="off" />
                    <span class="help-inline"></span>
                    <div class="selects inpt click_selects">
                        <span class="select_val">请选择品牌</span>
                        <i class="down_arr"></i>
                        <div class="select_con select_brand">
                            <div id="brand_1"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form_control">
                <label class="f_label">车型</label>
                <div class="controls">
                    <input type="text" name="carstyle" class="inpt ihide" autocomplete="off" />
                    <span class="help-inline"></span>
                    <div class="selects inpt click_selects">
                        <span class="select_val">请选择车型</span>
                        <i class="down_arr"></i>
                        <div class="select_con select_brand" id="style_1"></div>
                    </div>
                </div>
            </div>
            <div class="form_control">
                <label class="f_label">手机</label>
                <div class="controls">
                    <input type="text" name="phone" class="inpt" value="" placeholder="请输入手机号">
                    <span class="help-inline"></span>
                </div>
            </div>
            <div class="form_control">
                <label class="f_label">&nbsp;</label>
                <div class="controls">
               		<!-- 引入URLEncoder 函数 -->
					<#assign urlEncoder= "com.tuanche.cms.util.FreeURLEncoder"?new() >
               		<#assign cityStr = 'http://'+city.py+'.tuanche.com/yh/'>
               		<input  type="hidden" id="url" name="url"  value="${urlEncoder(cityStr)}"/>
               		<input type="hidden" name="bmtype" value="yaohao" />
               		<input type="hidden" name="position" value="5"/>
                    <a href="javascript:;" class="app_btn submit_btn">团购报名</a>
                </div>
            </div>
        </form>
    </div>
</div>