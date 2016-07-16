<!-- 中间广告位 -->
<#macro ad adContent>
	<#if adContent!="">
	    <div class="tc_container_1 ad_place_a mb20">
	        <a href="${adContent.adLink!""}" target="_blank">
				<img src="${adContent.picName}" data-url='${adContent.picName}'  alt="${adContent.adTitle}" width="1200px" height="120px" />
			</a>
	    </div>
    </#if>
</#macro> 