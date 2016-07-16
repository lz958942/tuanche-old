<!-- 摇号 标准5条内容 -->
<#macro yh_content5 plate contentList>
<#if plate!='' && contentList?size gt 0>
	<div class="yh_con_half fl">
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
	        	<ul class="content_list">
		        	<#list contentList as content>
						<#if content_index != 0 >
		        			<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
		      			</#if>
		      		</#list>
	            </ul>
	        </div>
	    </div>
	</div>
</#if>
</#macro>