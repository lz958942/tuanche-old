  <!-- 带详情内容 -->
<#macro LongContent plate contentList>
<#if plate!='' && contentList?size gt 0> 
  
  <div class="yh_con_half fr">
    <div class="title_wrap relative clearfix">
       <span class="border_l_a"></span>
        <#if plate.hyperlink?exists && plate.hyperlink!=''>
       	  <a target="_blank"  href="${plate.hyperlink}">
        </#if>
        <h3 class="title_d fl">${plate.title}</h3>
        <#if plate.hyperlink?exists>
       	  </a>
        </#if>
    </div>	
    <div class="tc_container_1 clearfix">
    	<div class="yh_con_img fl">
        	<#list contentList as content>
				<#if content.imagUrl?exists && content_index == 0 >
        			<a target="_blank" href="${content.hyperlink}"><img src="${staticFile}/public/images/blank.gif" alt="" width="206" height="137" data-url="${content.imagUrl}" class="scrollLoading" /></a>
      				<#break>
      			</#if>
      		</#list>
        </div>
        <div class="yh_con_list">
       		<div class="content_article">
	        	<#list contentList as content>
					<#if content.imagUrl?exists && content_index ==0  >
						<#assign exp = content.expand!''>
						<#if exp?length gt 76><#assign exp = exp?substring(0,75)></#if>
	        			&nbsp;&nbsp;&nbsp;&nbsp;${exp}...<a target="_blank" href="${content.hyperlink}">[详细]</a>
	      			</#if>
	      		</#list>
            </div>
        </div>
    </div>
</div>

</#if>
</#macro>