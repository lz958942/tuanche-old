<!-- 摇号查询 -->
<div class="tc_container_1">
	<div class="tc_container_1">
    	<div class="big_title border_b_b">
        	<div class="title_text title_c border_b_c">
        		<#if yh_weight?exists><h1 style="display: none">摇号结果查询</h1></#if>
        		<span class="sepa_l">摇号结果</span>查询
        	</div>
        </div>
    </div>
    <div class="tc_container_1 clearfix">
    	<div class="yh_query fl">
        	<div class="title_wrap relative clearfix">
                <span class="border_l_a"></span><h3 class="title_d fl">摇号指标查询系统</h3>
            </div>
            <div class="tc_container_1">
            	<form class="form_3 form_yh" id="yhForm" method="post">
                    <div class="form_control">
                        <label class="f_label">申请编号：</label>
                        <div class="controls">
                            <input type="text" id="yhcode" name="code" class="inpt" placeholder="请输入申请编号" maxlength="18"/>
                            <span class="help-inline"></span>
                            <span class="special_help"></span>
                        </div>
                    </div>
                    <div class="form_control">
                        <label class="f_label">姓&#12288;&#12288;名：</label>
                        <div class="controls inpt_m">
                            <input type="text" id="yhname" name="name" class="inpt" value="" placeholder="请输入姓名" maxlength="30"/>
                            <span class="help-inline"></span>
                        </div>
                    </div>
                    <div class="form_control">
                            <label class="f_label">联系方式：</label>
                            <div class="controls inpt_m">
                                <input type="text" name="phone" id="yhphone" class="inpt" value="" maxlength="11" placeholder="请输入您的手机号" />
                                <span class="help-inline"></span>
                            </div>
                     </div>
                    <div class="form_control">
                        <a href="javascript:;" class="app_btn submit_btn">摇号查询</a>
                    </div>    
                </form>  
            </div>
        </div>
        <div class="yh_flow fr">
        	<div class="title_wrap relative clearfix">
                <span class="border_l_a"></span><h3 class="title_d fl">摇号指标查询系统</h3>
            </div>
            <div class="tc_container_1">
            	<dl class="yh_flow_img">
                	<dd>1.网上/窗口提交申请</dd>
                    <dd>2.获得申请编码</dd>
                    <dd>3.查询审核结果</dd>
                    <dd>4.摇号并查询结果</dd>
                    <dd>5.打印/领取确认通知</dd>
                    <dd>6.办理车务手续</dd>
                </dl>
            </div>
        </div>
    </div>
</div>

