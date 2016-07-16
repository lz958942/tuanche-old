<!-- 本地行情 -->
<#if local_quotation?exists && local_quotation_contentList?exists>

 <div class="container_1">
	<div class="title_wrap relative clearfix">
        <span class="border_l_a"></span>
        <#if local_quotation.hyperlink?exists>
        <a target="_blank"  href="${local_quotation.hyperlink}">
        </#if>
        <h3 class="title_d fl">${local_quotation.title}</h3>
        <#if local_quotation.hyperlink?exists>
        </a>
        <!--  <a target="_blank" href="" class="fr">更多&gt;&gt;</a> -->
        </#if>
    </div>
    <div class="container_1">
        <ul class="content_list">
        	<#list local_quotation_contentList as content >
	    			<#if content_index < 2>
	    			 	<li>
	    			 		<!-- <span>有<i>1253</i>人参与</span> -->
	    			 		<a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
	    			</#if>
	    	</#list>
        </ul>
    </div>
</div>

</#if>