<!--行业新闻-->
<#macro trade_news  plate contentList>
<dd>
	<h3 class="title_d">
		<#if plate.hyperlink?exists>
			<a target="_blank"  href="${plate.hyperlink}">
		</#if>
		${plate.title}
		<#if plate.hyperlink?exists>
			</a>
		</#if>
	</h3>
	<#list contentList as content>
		<#if content.imagUrl?exists && content_index == 0 >
			<div class="car_new_img">
		    	<a target="_blank" href="${content.hyperlink}"><img src="${content.imagUrl}" alt="" width="235" height="157" data-url="${content.imagUrl}" class="scrollLoading" /></a>
		    	<a target="_blank" href="${content.hyperlink}" class="img_tit">${content.title}</a>
		    </div>
		</#if>
     </#list>
    
    <div class="container_1">
    	<ul class="content_list content_list_s clearfix">
    		<#list contentList as content1>
				<#if content1_index!= 0 && content1_index < 5 >
					 <li><a target="_blank"  href="${content1.hyperlink}">${content1.title}</a></li>
				</#if>
		     </#list>
        </ul>
    </div>
</dd>
</#macro>