<!-- 图片切换左侧 特殊板块 -->
<#macro te_shu plate contentList>
<#if contentList?exists>
	<dt><h2>
	<#list contentList as content>
		<#if content_index == 0>
			<a target="_blank" href="${content.hyperlink}" class="title_a">${content.title}</a>
		</#if>
	</#list>
	</h2></dt>
	<dd>
	<#list contentList as content1>
		<#if content1_index gt 0>
			<a target="_blank" href="${content1.hyperlink}">${content1.title}</a>
	   		<span>|</span>
	   	</#if>
	</#list>
	</dd>
</#if>
</#macro>