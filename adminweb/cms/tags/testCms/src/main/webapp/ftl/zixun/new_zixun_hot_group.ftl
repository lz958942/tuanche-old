<div class="container_2">
	<div class="container_1">
		<div class="title_wrap relative clearfix">
			<span class="border_l_a"></span>
			<h3 class="title_d fl">热门团购</h3>
		</div>
	</div>
	<div class="borderlbr">
		<ul class="clearfix recommend">
			<#if hot_groupbuy?exists && hotGroupBuy?exists>
				<#list hotGroupBuy as carStyle>
					<#if carStyle_index lt 8 && carStyle?exists>
						<li class="fl">
							<div class="hotpic">
								<a href="${carStyle.buyUrl}" target="_blank" pl="pg=zixunIndex&amp;pl=bottom">
									<img alt="${carStyle.title}" title="${carStyle.title}" src="${carStyle.spic}"></a>
							</div>
							<div class="hottit">
								<a href="${carStyle.buyUrl}" target="_blank" title="${carStyle.title}" pl="pg=zixunIndex&amp;pl=bottom">${carStyle.title}</a>
								-
								<a href="${carStyle.buyUrl}" target="_blank" rel="nofollow" pl="pg=zixunIndex&amp;pl=bottom">${carStyle.dateStr}</a>
							</div>
							<div class="clearfix hotinfo">
								<span class="fl gray">
									本期报名:
									<em class="red">${carStyle.manNum!'84'}</em>
									人
								</span>
								<span class="fr w95-wapper">
									<b class="btn w95">${carStyle.evaluaterTotal!0}人评价</b>
								</span>
							</div>
						</li>
					</#if>
				</#list>
			</#if>
		</ul>
	</div>
</div>
