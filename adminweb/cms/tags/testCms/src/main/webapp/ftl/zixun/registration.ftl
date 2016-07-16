<!-- 报名      团购买车入口 -->
<div class="container_1">
	<div class="buy_car_access <#if !isCountry?exists>access_big</#if>">
		<#if isCountry?exists>
			<#if nation_zixun_ad_r?exists>
		        <a href="${nation_zixun_ad_r.adLink!""}" target="_blank">
					<img src="${nation_zixun_ad_r.picName}" data-url='${nation_zixun_ad_r.picName}'  alt="${nation_zixun_ad_r.adTitle}" width="221px" height="193px" />
				</a>
	  		</#if>
		</#if>
		<#if !isCountry?exists>
			 <#if zixun_city_right_ad?exists>
		        <a href="${zixun_city_right_ad.adLink!""}" target="_blank">
					<img src="${zixun_city_right_ad.picName}" data-url='${zixun_city_right_ad.picName}'  alt="${zixun_city_right_ad.adTitle}" width="221px" height="260px" />
				</a>
	  		  </#if>
		</#if>
	</div>
</div>

