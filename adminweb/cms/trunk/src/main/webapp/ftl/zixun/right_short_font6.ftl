<!-- 右侧短标题纯文字  6个 -->
<#macro right_short_font6  plate contentList>
<#if plate?exists && contentList?exists>
		<div class="container_1 content_space"><h2 class="title_d">
		<#if plate.hyperlink?exists>
			<a target="_blank" href="${plate.hyperlink}"> 
		</#if>
		 ${plate.title}
		 <#if plate.hyperlink?exists>
			</a>
		</#if> 
	   </h2><div class="container_1">
	        <ul class="content_list content_list_s">
	        		<#list contentList as content>
	        			<#if content_index < 6>
	        				<li><a target="_blank" href="${content.hyperlink!""}">${content.title!""}</a></li>
	        			</#if>
	        		</#list>
	        </ul>
	    </div>
	</div>
</#if>
</#macro>