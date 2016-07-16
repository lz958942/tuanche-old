<!--  -->
<#if user_car_character?exists && user_car_character_contentList?exists>
	<#list user_car_character_contentList as content>
		<#if content_index < 7>
			<li>
				<div class="thing_img">
			    	<a target="_blank" href="${content.hyperlink}"><img src="${content.imagUrl}" alt="" width="235" height="156" data-url="${content.imagUrl}" class="scrollLoading" /></a>
			    </div>
			    <div class="thing_info">
			    	<p><a target="_blank" href="${content.hyperlink}">${content.title}</a></p>
			        <p>1本期报名<span>634</span>人 累计节省<span>1434.8</span>万</p>
			    </div>
			</li>	
		</#if>
	</#list>
</#if>