<!-- 地方政策 -->
<#if local_policy?exists>
	<div class="container_1 content_space">
		<div class="container_1 border_b_a"><h2 class="title_b title_other">
			<#if local_policy.hyperlink?exists>
			<a target="_blank"  href="${local_policy.hyperlink}">
			</#if>
				<!-- 板块名称 -->
				<#assign bigNum=local_policy.title?length/2>
			    <#assign lanLen=bigNum?round>
				<span class="sepa_l">${local_policy.title?substring(0,lanLen)}</span>${local_policy.title?substring(lanLen)}
			<#if local_policy.hyperlink?exists>
			</a>
			</#if>
		</h2></div>
	    <div class="container_1">
	    	<ul class="content_list">
	    		<#if local_policy_contentList?exists>
		    		<#list local_policy_contentList as content >
		    			<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
		    		</#list>
	    		</#if>
	        </ul>
	    </div>
	</div>
</#if>