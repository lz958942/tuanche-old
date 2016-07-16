<!--  团友说 -->
<div class="ta_say_wrap ta_say_slide">
    <ul class="slides clearfix">
    	<#if groupfriend_say_city_contentList?exists>
    		<#list groupfriend_say_city_contentList as content>
    			<#if content_index%2 == 0>
    			  <li class="clearfix">
    			</#if>
		            <div class="ta_say <#if content_index%2 == 0>fl</#if><#if content_index%2 != 0>fr</#if> clearfix">
		                <div class="ta_head_pic">
		                    <a target="_blank" href="${content.hyperlink}">
		                     <#if content_index lt groupfriend_say_city_contentList?size -2>
		                      <img src="${staticFile}/public/images/blank.gif" alt="" width="60" height="60" data-url="${content.imagUrl!''}" class="scrollLoading" />
		                     </#if>
		                     <#if content_index gte groupfriend_say_city_contentList?size -2>
		                    	 <img src="${content.imagUrl!''}" alt="" width="60" height="60" data-url="${content.imagUrl!''}" class="scrollLoading" />
		                     </#if>
		                   	</a>
		                    <!--[if IE]><i class="head_circle"></i><![endif]-->
		                </div>
		                <div class="ta_info">
		                 	<a target="_blank" href="${content.hyperlink}">
			                    <p class="info_a">${content.title}</p>
			                    <p>${content.expand}</p>
		                    </a>
		                </div>
		            </div>
		        <#if content_index%2 == 1>
		       	 </li>
		        </#if>
    		</#list>
    	</#if>
    </ul>
</div>