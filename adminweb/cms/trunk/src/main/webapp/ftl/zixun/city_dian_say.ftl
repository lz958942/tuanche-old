 <!-- 4s店说 -->
 <div class="ta_say_wrap dian_info" style="display:none;">
    <ul class="slides clearfix">
    	<#if dian_say_city_contentList?exists>
    		<#list dian_say_city_contentList as content>
    			<!-- 截取-->
		        <#assign first = content.expand?index_of('&')>
		        <#assign last = content.expand?last_index_of('&')>
		        <#if first!=-1>
		        	<#assign dian_cont = content.expand?substring(0,first)>
		        	<#if dian_cont?length gt 95>
		        		<#assign dian_cont = dian_cont?substring(0,95)>
		        	</#if>
		        </#if>
		        <#if last!=-1 && first != last>
		        	<#assign dian_name = content.expand?substring(first+1,last)>
		        	<#assign per_name = content.expand?substring(last+1)>
		        </#if>
		        
		        
    			<#if content_index%2 == 0>  <li class="clearfix"></#if>
		            <div class="ta_say <#if content_index%2 == 0>fl</#if><#if content_index%2 != 0>fr</#if> clearfix">
		            	<div class="dian_name">${dian_name!''}</div>
                        <div class="dian_manager_name">${per_name!''}</div>
                        <i class="n_ico"></i>
		                <div class="ta_head_pic">
		                    <img src="${staticFile}/public/images/blank.gif" alt="" width="60" height="60" data-url="${content.imagUrl!''}" class="scrollLoading" />
		                    <!--[if IE]><i class="head_circle"></i><![endif]-->
		                </div>
		                <div class="ta_info">
		                    <p class="info_a">${content.title}</p>
	                    	<p>${dian_cont!''}</p>
		                </div>
		            </div>
		        <#if content_index%2 == 1></li></#if>
		    </#list>
	    </#if>
    </ul>
</div>