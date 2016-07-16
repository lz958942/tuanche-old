<!-- 带数字编号 -- flay第五条是否显示 详细链接>
<#macro havaNumber plate contentList flay>
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
            	<dl class="content_list content_article">
	            	<#list contentList as content>
						<#if content_index != 0 >
		        			<dd>
		        			<span>
		        			<i <#if content_index lt 4>class="top3"</#if>>${content_index}</i>
		        			<!-- 车牌申请及竞拍 -->
	        				<#if content_index==5 && flay==1 && content.hyperlink?exists>
	        					<#assign tit = content.title!''>
								<#if tit?length gt 13><#assign tit = tit?substring(0,13)></#if>
	        					${tit}<a target="_blank" href="${content.hyperlink}">[详细]</a>
	        				<#else>
	        					 ${content.title}
	        				</#if>
		        			</span></dd>
		      			</#if>
		      		</#list>
                </dl>
            </div>
        </div>
    </div>
</#if>
</#macro>