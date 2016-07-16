<#if xiaoma_say_thing?exists >
<div class="container_2 ad_place_a">
	<div class="title_wrap relative clearfix">
		<span class="border_l_a"></span>
		<h3 class="title_d fl">
		 <a pl="pg=zixuIndex&pl=tuanzhang" href="http://${oldHost}/zx-xmg/">团长说事</a>
		</h3>
	</div>
	 <a pl="pg=zixuIndex&pl=tuanzhang" target="_blank" href="http://${oldHost}/zx-xmg/"><img src="${staticFile}/tuanchepc/news/image/xmg.png" alt="" /></a>
    <div class="clearfix tuanzhang-wrap">
    	<#if xiaoma_say_thing_contentList?exists>
    		<#list xiaoma_say_thing_contentList as content>
            	<#if content_index%3 == 0>
			        <div class="fl tuanzhang-ul">
			            <ul>
			    </#if>
		    	<li>
                    <a pl="pg=zixuIndex&pl=tuanzhang" href="${content.hyperlink!'##'}" target="_blank">${content.title}</a>
                </li>
	            <#if content_index%3 == 2>
			            </ul>
			        </div>
			  	</#if>
			</#list>
			 <#if xiaoma_say_thing_contentList?size%3 !=0>
		            </ul>
		        </div>
		  	</#if>
	    </#if>
    </div>
</div>
</#if>

