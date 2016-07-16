<!-- 商家资讯 -->
<#if seller_info?exists && seller_info_contentList?exists>

 <div class="container_1">
	<div class="title_wrap relative clearfix">
        <span class="border_l_a"></span>
        <#if seller_info.hyperlink?exists>
        	 <a target="_blank" href="${seller_info.hyperlink}"> 
        </#if>
       <h3 class="title_d fl">${seller_info.title}</h3>
       <#if seller_info.hyperlink?exists>
        	 </a>
        	 <!-- <a target="_blank" href="${seller_info.hyperlink}" class="fr">更多&gt;&gt;</a> -->
        </#if>
    </div>
    <div class="container_1">
        <ul class="content_list">
        	<#list seller_info_contentList as content >
    			<#if content_index < 3>
    				<li>
    					<!--	<span>有<i>1253</i>人参与</span> -->
    					<a target="_blank" href="${content.hyperlink}">${content.title}</a>
    				</li>
    			</#if>
    		</#list>
        </ul>
    </div>
</div>


</#if>