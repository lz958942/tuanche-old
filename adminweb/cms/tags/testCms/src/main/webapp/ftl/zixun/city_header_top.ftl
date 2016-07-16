<!-- 头部顶上部分 全站通用 -->
<div class="tc_header">
	<div class="tc_container_2 clearfix">
        <div class="fl city_wrap clearfix">
            <div class="current_city cur_city fl"><a href="http://${city.py}.tuanche.com/" data-cur="${city.id}">${city.dname}</a></div>
            <div class="switch_city fl" id="switch_city">[<a href="javascript:;">选择城市</a>]
            	<div class="switch_city_wrap" id="moreCity">
                	<div class="s_city_con">
                        <ul>
                           <#if changeCity?exists>
                           		<#list changeCity?keys as key>
                            	   <#assign citys=changeCity[key]>
		                           <li>
		                                <h4>${key}</h4>
		                                <div class="s_ul">
		                                	<#list citys as city>
		                                    		<a title="${city.name}汽车团购" <#if city.openStatus == 3>class="ready"</#if>  href="http://${city.py!""}.tuanche.com/" target="_blank">   ${city.name}</a>
		                                    </#list>
		                                </div>
		                                <div style="clear:both"></div>
		                            </li>
		                         </#list>
                           </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="fr login_wrap">
        	<!-- 
        	<a href="##">注册</a>
            <span>|</span>
            <a href="##">登录</a>
            -->
            <div class="tc_focus tc_focusyiyi">
                <a href="##" class="a_transition">诚信保障金<i class="ico ico_down"></i></a>
                <div class="tc_focus_wrap tc_focusyiyi_wrap">
                    <div class="tc_focus_con">
                        <div class="tc_focusyiyi_wrap-tit">一亿元诚信保障金</div>
                        <div class="clearfix tc_focusyiyi_wrap-img">
                            <a href="http://tuanche.com/zx-wh/xw/558046.html" target="_blank"><img src="http://static.tuanche.com/public/images/yiyi.png"/></a>
                        </div>
                    </div>
                </div>
            </div> 
            <span>|</span> 
             <div class="tc_focus">
            	<a href="##" class="a_transition">关注<i class="ico ico_down"></i></a>
                <div class="tc_focus_wrap">
                	<div class="tc_focus_con">
                		<p class="bold">团车网官方微博</p>
                        <p><wb:follow-button uid="2445730697" type="red_2" width="136" height="24" ></wb:follow-button></p>
                        <p class="line"></p>
                        <p class="bold">团车网官方微信</p>
                        <p><img src="${staticFile}/public/images/wechat_s.png" alt="" width="90" height="90" /></p>
                    </div>
                </div>
            </div>
            <span>|</span>
       	    <a href="##" id="addFavorite">收藏</a>
        </div>
    </div>
</div>