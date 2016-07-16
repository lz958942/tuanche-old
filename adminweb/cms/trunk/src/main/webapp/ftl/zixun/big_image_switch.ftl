<!-- 大图切换 -->
<#if big_image_switch_contentList?exists>
	<div class="slide_img flexslider_wrap_focus">
	    <ul class="clearfix <#if big_image_switch_contentList?size gt 1>slides</#if>">
	    	<#list big_image_switch_contentList as big_image>
	    		<li>
	    			<a target="_blank" href="${big_image.hyperlink!''}"><img src="${big_image.imagUrl!''}" data-url="${big_image.imagUrl!''}" alt="" width="617" height="368"  class="scrollLoading" /></a>
	    			<a target="_blank" href="${big_image.hyperlink!''}" class="slider_title">${big_image.title}</a>
	    		</li>
	    	
	    	</#list>
	    </ul>
	</div>
</#if>