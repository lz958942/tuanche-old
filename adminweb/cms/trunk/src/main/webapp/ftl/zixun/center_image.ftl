<!-- 长内容中间的图片 比如  初步海选  右 -->
<#macro center_image plate contentList>
<div class="container_1">
	<div class="con_img">
		<#list contentList as content>
			<#if content_index < 1>
		    	<a target="_blank" href="${content.hyperlink}"><img src="${content.imagUrl!''}" alt="" width="250" height="170" data-url="${content.imagUrl!''}" class="scrollLoading" /></a>
		        <a target="_blank" href="${content.hyperlink}" class="img_tit">${content.title}</a>
	        </#if>
        </#list>
    </div>
</div>
</#macro>