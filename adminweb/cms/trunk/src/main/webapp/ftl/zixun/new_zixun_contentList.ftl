<!-- 精选讯息 -->
<#macro newZixunContents plate contentList pl>
	<div class="container_1">
		<#if plate?exists && contentList?exists>
			<div class="container_1 border_b_a">
				<h2 class="title_b title_other">
					<span class="sepa_l">
						<#if plate.hyperlink?exists>
				    		<a pl=${pl} target="_blank"  href="${plate.hyperlink}">
					    </#if>
					   	${plate.title}
					  	<#if plate.hyperlink?exists>
					    	</a>
				    	</#if>
					</span>
				</h2>
			</div>
			<div class="container_1">
				<ul class="content_list">
					<#list contentList as content>
	        			<#if content_index < 6>
	        				<li><a pl=${pl} target="_blank" href="${content.hyperlink}">${content.title}</a></li>
	        			</#if>
	        		</#list>
				</ul>
			</div>
		</#if>
	</div>
</#macro>