 <!-- 热门团购 -->
 <#assign plate_num = 0>
<#macro hot_c_g contentList num>
	<#if contentList?size != 0>
	<#assign plate_num = plate_num+1>
		<dl class="hot_con clearfix" <#if plate_num=1>style="display:block;"</#if>>
			<#list contentList as content>
				<#if content?exists && content_index<6>
					<dd>
		            	<div class="hot_pic">
		                	<a pl="pg=cityIndex&pl=hot" target="_blank" href="${content.buyUrl!''}">
		                		<img src="${staticFile}/public/images/blank.gif" alt="" width="300" height="200" data-url="${content.spic!''}" class="scrollLoading" />
		                	</a>
		                </div>
		                <p class="hot_info"><a pl="pg=cityIndex&pl=hot" target="_blank" href="${content.buyUrl!''}"> ${content.allTitle}</a></p>
		                <p class="hot_num">本期报名<span>${content.manNum!''}</span>人<ins>|</ins>
		                <span class="m_star_bg"><em style="width:${content.commentTotal/5*100!0}%"></em></span><span class="co">${content.evaluaterTotal!0}人评价</span></p>
		            </dd>
	            </#if>
	        </#list>
	    </dl>
	 </#if>
</#macro>

<!-- 开始 -->
<div class="hot_slide">
    <div class="tc_title_wrap clearfix">
        <h2 class="tc_title tc_t_bb">热门汽车团购</h2>
        <dl class="clearfix tc_nav tc_tit_nav fr tc_nav_tab">
        	<i class="current" style="left:53px;"></i>
        	<#list 1..5 as num>
        		<#assign plate_ = 'hot_car_tem'+num+'_city'>
        		<#assign plate = plate_?eval!"">
        		<#if plate!="">
        			<dd <#if num == 1>class="cur"</#if>><a href="javascript:void(0)" data-con="tc_nav_tab_car">${plate.title}</a></dd>
        		</#if>
        	</#list>
        </dl>
    </div>
    <div class="hot_con_wrap tc_nav_tab_car">
        <@hot_c_g contentList=hot_car_tem1_city_contentList![] num=1></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem2_city_contentList![] num=2></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem3_city_contentList![] num=3></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem4_city_contentList![] num=4></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem5_city_contentList![] num=5></@hot_c_g>
    </div>
</div>