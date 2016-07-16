<!-- 为团友省钱的团长们 -->
<div class="tc_container_1 mb20">
    <div class="tc_title_wrap clearfix">
        <h2 class="tc_title tc_t_bb">为团友省钱的团长们</h2>
    </div>
    <div class="top_tuaner_wrap">
    	<div class="top_tuaner tuaner_slide">
            <ul class="clearfix <#if groupbuy_chiefs_city_contentList?exists && groupbuy_chiefs_city_contentList?size gt 4> slides</#if>">
            <#if groupbuy_chiefs_city_contentList?exists>
	            <#list groupbuy_chiefs_city_contentList as user>
	            	<#if user?exists>
		                <li>
		                	<div class="tuaner_info clearfix">
		                        <div class="tuaner_pic">
		                        	<img src="${staticFile}/public/images/blank.gif" alt="" width="118px" height="118px" data-url="${user.headUrl}" class="scrollLoading" />
		                        	<!--[if IE]><i class="head_circle"></i><![endif]-->
		                        </div>
		                        <div class="tuaner_grade">
		                            <p class="info_name">团长：${user.empName?substring(0,1)}团</p>
		                            <p>带团期数：${user.getGroup}</p>
		                            <p>购车总数：${user.sellNum}</p>
		                            <p>累计人数：${user.activityNum}</p>
		                        </div>
		                    </div>
		                    <#assign userbrands=user.brands![]>
		                    <div class="tuaner_brand clearfix">
		                    	<span>带领品牌：</span>
		                    	<#list userbrands as brand>
		                    		<#if brand_index <4 >
			                    		<#if brand_index != 0>
			                    			<ins>|</ins>
			                    		</#if>
			                    		  <a pl="pg=cityIndex&pl=leader&empNo=${user.empNo}" target="_blank" href="http://${city.py}.${oldHost}/b${brand.id}/tuan/"><img src="${staticFile}/public/images/blank.gif" alt="" width="27" height="27" data-url="${brand.logo}" class="scrollLoading" /></a>
		                    		</#if>
		                    	</#list>
		                    </div>
		                </li>
	                </#if>
                 </#list>
           	 </#if>
            </ul>
        </div>
    </div>
</div>