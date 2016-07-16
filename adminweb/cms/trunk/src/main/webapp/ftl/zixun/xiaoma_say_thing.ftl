<!-- 小马哥说事 -->
<#if xiaoma_say_thing?exists >

 <div class="container_1 content_space">
	<div class="ma_say"><h2 class="title_d">
		<#if xiaoma_say_thing.hyperlink?exists>
			<a target="_blank" href="${xiaoma_say_thing.hyperlink}">
		</#if>
    		${xiaoma_say_thing.title}
    	<#if xiaoma_say_thing.hyperlink?exists>
			</a>
		</#if>
   	 </h2><div class="slide_img slide_img_s flexslider_wrap">
        <ul class="clearfix">
        	<#if xiaoma_say_thing_contentList?exists>
            	<#list xiaoma_say_thing_contentList as content>
            		<#if content.imagUrl?exists && content_index == 0>
	            		<li style="background:;">
		                	<a target="_blank" href="${content.hyperlink}"><img src="${content.imagUrl!""}" alt="" width="221" height="147" data-url="${content.imagUrl!""}" class="scrollLoading" /></a>
		                    <a target="_blank" href="${content.hyperlink}" class="slide_tit">${content.title}</a>
		                </li>
            		</#if>
            	</#list>
        	</#if>
        </ul>
        </div>
        <div class="clearfix"></div>
        <div class="container_1">
            <ul class="content_list content_list_s">
           		<#if xiaoma_say_thing_contentList?exists>
	           		 <#list xiaoma_say_thing_contentList as content1>
	            		<#if content1_index != 0>
		            		<li><a target="_blank" href="${content1.hyperlink}">${content1.title}</a></li>
	            		</#if>
	            	</#list>
            	</#if>
            </ul>
        </div>
    </div>
</div>


</#if>