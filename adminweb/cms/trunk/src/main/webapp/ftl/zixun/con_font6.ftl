<!--内容  纯文字 6条 （例：开团计划）  -->
<#macro con_font6 plate contentList>
<#if plate?exists && contentList?exists>
	<div class="container_1 content_space">
    	<div class="container_1 border_b_a"><h2 class="title_b title_other">
	    	<#if plate.hyperlink?exists>
	    	<a target="_blank"  href="${plate.hyperlink}">
	    	</#if>
	    	<#assign bigNum=plate.title?length/2>
	    	<#assign lanLen=bigNum?round>
	    	<span class="sepa_l">${plate.title?substring(0,lanLen)}</span>${plate.title?substring(lanLen)}
	       	<#if plate.hyperlink?exists>
	    	</a>
	    	</#if>
    	</h2></div>
        <div class="container_1">
        	<ul class="content_list">
        		<#list contentList as content>
        			<#if content_index < 6>
        				<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
        			</#if>
        		</#list>
            </ul>
        </div>
    </div>
</#if>
</#macro>