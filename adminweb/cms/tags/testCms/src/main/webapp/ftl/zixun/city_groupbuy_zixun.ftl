<#macro groupbuy_zixun plate contentList>
	<#if plate!="">
	<dd>
		<h3 class="title_d"><a target="_blank"  href="${plate.hyperlink!''}">${plate.title}</a></h3>
			<#list contentList as content>
				<#if content.imagUrl?exists>
				    <div class="car_new_img">
				    	<a target="_blank"  href="${content.hyperlink!""}"><img src="${staticFile}/public/images/blank.gif" alt="" width="267" height="140" data-url="${content.imagUrl}" class="scrollLoading" /></a>
				    	<a target="_blank"  href="${content.hyperlink!""}" class="img_tit">${content.title}</a>
				    </div>
				    <#assign wasContentId =content.id>
				    <#break>
				</#if>
				<#if !wasContentId?exists>
					<#assign wasContentId =0>
				</#if>
			</#list>
		    <div class="tc_container_1">
		    	<ul class="content_list content_list_s clearfix">
		    		<#list contentList as content>
		    			<#if content.id!=wasContentId && content_index < 5>
		    				<li><a target="_blank"  href="${content.hyperlink}">${content.title}</a></li>
		    			</#if>
		            </#list>
		        </ul>
		    </div>
	</dd>
	</#if>
</#macro>

<!-- 团车资讯 -->
<#if groupbuy_former_city_contentList?exists && groupbuy_former_city_contentList?size gt 0>
	<div class="tc_container_1 mb20">
		    <div class="tc_title_wrap clearfix">
		        <h2 class="tc_title tc_t_bb">团车资讯</h2>
		    </div>
		    <div class="tc_container_1 clearfix">
		    	<dl class="car_news clearfix">
		        	<@groupbuy_zixun plate=groupbuy_former_city!"" contentList=groupbuy_former_city_contentList!""></@groupbuy_zixun>
		        	<@groupbuy_zixun plate=tuanche_interview_city!"" contentList=tuanche_interview_city_contentList!""></@groupbuy_zixun>
		        	<@groupbuy_zixun plate=car_style_guide_city!"" contentList=car_style_guide_city_contentList!""></@groupbuy_zixun>
		        	<@groupbuy_zixun plate=groupbuy_notice_city!"" contentList=groupbuy_notice_city_contentList!""></@groupbuy_zixun>
		       </dl>
		    </div>
	</div>
 </#if>