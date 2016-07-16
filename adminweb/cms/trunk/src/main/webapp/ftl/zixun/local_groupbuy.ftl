<!-- 地区团车会 -->
<#if local_groupbuy?exists && local_groupbuy_contentList?exists>

	<div class="container_1 content_space">
		<h2 class="title_d">
			<#if local_groupbuy.hyperlink?exists>
				<a target="_blank"  href="${local_groupbuy.hyperlink}">
			</#if>
			${local_groupbuy.title}
			<#if local_groupbuy.hyperlink?exists>
			</a>
			</#if>
		</h2>
	    <div class="container_1">
	        <ul class="content_list content_list_s">
		        <#list local_groupbuy_contentList as content >
	    			<#if content_index < 5>
	    				<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
	    			</#if>
	    		</#list>
	        </ul>
	    </div>
	</div>
	
</#if>