<!-- 底部 -->
<div class="tc_footer_p">    
    <dl class="clearfix wrap1200 mc">
        <dd class="first_p">
            <i class="footer_ico footer_ico_1"></i>
            <h3>车源保证</h3>
            <p>甄选本地优质正规4S店</p>
        </dd>
        <dd>
            <i class="footer_ico footer_ico_2"></i>
            <h3>低价保证</h3>
            <p>4S店挑最低价格合作</p>
        </dd>
        <dd>
            <i class="footer_ico footer_ico_3"></i>
            <h3>订金可退</h3>
            <p>规定的时间内可退还订金</p>
        </dd>
        <dd>
            <i class="footer_ico footer_ico_4"></i>
            <h3>优先提车</h3>
            <p>享有优先提车权</p>
        </dd>
        <dd>
            <i class="footer_ico footer_ico_5"></i>
            <h3>售后维权</h3>
            <p>质量问题，团车网协助解决</p>
        </dd>
        <dd>
            <i class="footer_ico footer_ico_6"></i>
            <h3>全国联保</h3>
            <p>正规4S店均可维修保养</p>
        </dd>
    </dl>   
</div>   

<div class="wrap1200 mc footer_link">
    <ul class="clearfix">
    	<#if flLink?exists>
        	<#list flLink as fl>
        		<#if fl_index!=0>
        			<li><span>|</span></li> 
        		</#if>
        		<li><a pl="${yqliPl}" href="${fl.flUrl}" target="_blank">${fl.flName}</a></li>
        	</#list>
    	</#if>
    </ul>
</div>
        
<div class="footer-p">
    <em class="icon"></em>
    <div class="interior_link">
        <a pl="${tcliPl}" href="http://bj.${oldHost}/" target="_blank">北京团车网</a>
        <span>|</span>
        <a pl="${tcliPl}" href="http://${oldHost}/about/" target="_blank">关于团车</a>
        <span>|</span>
        <a pl="${tcliPl}" href="http://${oldHost}/about_3.shtml" target="_blank">联系我们</a>
        <span>|</span>
        <a pl="${tcliPl}" href="http://${oldHost}/cst/index" target="_blank">申请合作</a>
        <span>|</span>
        <a pl="${tcliPl}" href="http://${oldHost}/about_zhaopin.html" target="_blank">加入团车</a>
        <span>|</span>
        <a pl="${tcliPl}" href="http://${oldHost}/huizong/" target="_blank">车型汇总</a>
        <span>|</span>
        <a pl="${tcliPl}" href="javascript:void(0);" onclick="AddFavorite('','团车网')">收藏本站</a>
        <span>|</span>
        <a pl="${tcliPl}" href="http://${oldHost}/about_tousu.shtml" target="_blank">我要投诉</a>
        <span>|</span>
        <a pl="${tcliPl}" href="http://${oldHost}/about_weiquan.html" target="_blank">维权声明</a>    
    </div>
   <p class="tc_copyright">团车网电话：400-6969-123团车互联网信息服务（北京）有限公司&copy;2014 翻版必究 | 京ICP备12044574号-2</p>
    <p class="icpDetailBottom"><a pl="${tcliPl}" href="${wwwUrl}/jingyingxuke.html" target="_blank">电信与信息服务业务经营许可证：京ICP证130329</a></p> 
</div>
