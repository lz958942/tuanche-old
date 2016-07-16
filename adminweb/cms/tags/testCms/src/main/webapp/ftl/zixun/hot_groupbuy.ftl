<!-- 热门团购 -->
<#if hot_groupbuy?exists && hotGroupBuy?exists>
    <div class="container_2">
    	<div class="container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">热门</span>团购</div>
            </div>
        </div>
        <div class="container_1 clearfix">
        	<ul class="car_thing">
				<#list hotGroupBuy as carStyle>
					<#if carStyle_index lt 8 && carStyle?exists>
						<li>
							<div class="thing_img">
						    	<a target="_blank" href="${carStyle.buyUrl}"><img src="${carStyle.spic}" alt="" width="235" height="156" data-url="${carStyle.spic}" class="scrollLoading" /></a>
						    </div>
						    <div class="thing_info">
						    	<p><a target="_blank" href="${carStyle.buyUrl}">${carStyle.title} - ${carStyle.dateStr}</a></p>
						       <p class="hot_num">本期报名<span>${carStyle.manNum!''}</span>人 |  ${carStyle.evaluaterTotal!0}人评价               				  
               				  </p>
						    </div>
						</li>	
					</#if>
				</#list>
            </ul>
        </div>
    </div>
</#if>