<!-- 摇号标准6条内容 +1图 -->
<#if car_groupbuy_yh?exists>
<div class="content_ll clearfix">
	<div class="content_left">
    	<div class="container_1">
        	<div class="title_wrap relative clearfix">
                <span class="border_l_a"></span>
                <#if car_groupbuy_yh.hyperlink?exists && car_groupbuy_yh.hyperlink!=''>
		       	  <a target="_blank"  href="${car_groupbuy_yh.hyperlink}">
		        </#if>
               		 <h3 class="title_d fl">${car_groupbuy_yh.title}</h3>
                 <#if car_groupbuy_yh.hyperlink?exists>
		       	  </a>
		        </#if>
            </div>
            <div class="container_1">
                <ul class="content_list">
                	<#list car_groupbuy_yh_contentList as content>
                		<#if content_index !=0>
                 	   		<!-- <li><span>有<i>1253</i>人参与</span> -->
                 	   		<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
                   		</#if>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
    <div class="content_rimg">
    	<div class="container_1">
        	<div class="con_img">
        		<#list car_groupbuy_yh_contentList as content>
                	<#if content_index == 0>
            			<a target="_blank" href="${content.hyperlink}"><img src="${staticFile}/public/images/blank.gif" alt="" width="250" height="170" data-url="${content.imagUrl!''}" class="scrollLoading" /></a>
           			</#if>
           		</#list>
            </div>
        </div>
    </div>
</div>
</#if>