<!-- 汽车改装 -->
<div class="container_1 content_space">
	<#if car_modify.hyperlink?exists>
		<a target="_blank" href="${car_modify.hyperlink}">
	</#if>
	<h2 class="title_d">汽车改装</h2></a>
    <div class="slide_img slide_img_s flexslider_wrap">
        <ul class="clearfix">
        	<#list car_modify_contentList as content>
        		<#if content?exists && content.imagUrl?exists>
        			<#assign ac = content.title>
		            <li>
		            	<a target="_blank" href="${content.hyperlink}"><img src="${staticFile}/public/images/blank.gif" alt="" width="221" height="147" data-url="${content.imagUrl!''}" class="scrollLoading" /></a>
		                <a target="_blank" href="${content.hyperlink}" class="slide_tit">${content.title}</a>
		            </li>
		            <#break>
	            </#if>
            </#list>
        </ul>
    </div>
    <div class="clearfix"></div>
    <div class="container_1">
        <ul class="content_list content_list_s">
       	 <#list car_modify_contentList as content>
       	 	<#if content_index lt 4 && content.title!=ac> 
         	   <li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
            </#if>
          </#list>
        </ul>
    </div>
</div>