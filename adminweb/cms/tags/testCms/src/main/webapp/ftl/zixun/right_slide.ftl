<!-- 侧边工具栏 -->
<div class="tc_fixed_slide">
	<dl class="slide_ico_wrap">
    	<dd class="click_sub">
        	<a href="javascript:;" class="has_sub"><em class="slide_ico_1"></em>团购报名</a>
            <div class="fixed_subnav apply_form">
        	    <a class="close_apply" href="javascript:;"></a>
            	<div class="tc_container_1">
                    <div class="tc_title_wrap clearfix">
                        <h2 class="tc_title">快捷报名</h2>
                    </div>
                    <div class="tc_form">
                 		<form method="post"  class="form_2" id="indexForm_fixed" action="http://tuanche.com/service/signup/formapply">
                            <input type="text" name="city" id="cityId" class="inpt required hide" value="${city.id?c}">
                            <div class="form_control">
                                <div class="controls">
                                    <input type="text" name="brand" class="inpt required hide" autocomplete="off" />
                                    <span class="help-inline"></span>
                                    <div class="selects inpt click_selects" id="brand_wrap">
                                        <span class="select_val">请选择品牌</span>
                                        <i class="down_arr"></i>
                                        <div class="select_con select_brand">
                                            <div id="brand_2"></div>
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
                                        <div class="select_con select_brand" id="style_2">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form_control">
                                <div class="controls">
                                    <input type="text" name="user" class="inpt" value="" maxlength="30" placeholder="姓名" autocomplete="off" />
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
            	    				<input  type="hidden" id="url" name="url"  value="${urlEncoder(cityStr)}?sourceId=fastapply"/>
            	    				<input type="hidden" name="bmtype" value="index"/>
            	    				<input type="hidden" name="position" value="5"/>
            	    				<button class="app_btn submit_btn">提交报名</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </dd>
        <dd><a href="tencent://message/?Menu=yes&uin=938060437&Service=58&SigT=A7F6FEA02730C98844DD5F7A4313382BD14C7A3F7C080101A2A2BE924F16760A3F52A75FD78516CE7CEFDCFF87A5B45CF6A328D95ABF7FE936AA4C42F8D4D22C0E547206EDFF8A8461342209D70013EF166E434F52F756BEB6099E8699F412D157EC3B1AD7694B87EA23BA52D70B134ECB9CA9C7ACCD2419&SigU=30E5D5233A443AB200557F2F7B70D32561266139A081E88BA549E55D56CCDE1B0F0A3518D48D7ABCC6E9AEF2042E41B2C8D9B4A7A3EF179E11803C1A5C3261FE8D33E574EC9E8794" id="online"><em class="slide_ico_2"></em>在线客服</a></dd>
        <dd>
        	<a href="javascript:;" class="has_sub"><em class="slide_ico_3"></em>关注微信</a>
        	<div class="fixed_subnav wechat_wrap"><img src="${staticFile}/public/images/wechat_s.png" alt="" width="90" height="90" /></div>
        </dd>
        <dd><a href="javascript:;" id="backtop"><em class="slide_ico_4"></em>返回顶部</a></dd>
    </dl>
</div>