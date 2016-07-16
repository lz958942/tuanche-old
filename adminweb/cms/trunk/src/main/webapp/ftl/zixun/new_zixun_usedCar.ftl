<div class="fl buyCarImg" style="padding:0px 30px;">
    <div class="container_1">
        <div class="con_img">
        	 <#if  ramble_auto_market?exists && ramble_auto_market_contentList?exists>
	        	 <div>
	                <div class="container_1 border_b_a">
	                    <h2 class="title_b title_other">
	                        <span class="sepa_l">
						    	<#if ramble_auto_market.hyperlink?exists>
						    		<a pl="pg=zixuIndex&pl=mantan" style="font-size:18px;font-weight:700" target="_blank"  href="${ramble_auto_market.hyperlink}">
							    </#if>
							   	${ramble_auto_market.title}
							  	<#if ramble_auto_market.hyperlink?exists>
							    	</a>
						    	</#if>	
	                        </span>
	                    </h2>
	                </div>
	                <div class="container_1">
	                    <ul class="content_list">
	                    	 <#list ramble_auto_market_contentList as content>
			        			<#if content_index < 6>
			        				<li><a pl="pg=zixuIndex&pl=mantan" target="_blank" href="${content.hyperlink!''}">${content.title!''}</a></li>
			        			</#if>
			        		</#list>
	                    </ul>
	                </div>
	            </div>
            </#if>
        </div>
    </div>
</div>
<div class="fl buyCarImg" <#if city.id==10>style="padding:0px 30px;"</#if>>
    <div class="container_1">
        <div class="con_img">
        	<#if city.id==10>
        		 <#if  group_car_and_sell_car?exists && group_car_and_sell_car_contentList?exists>
		        	 <div>
		                <div class="container_1 border_b_a">
		                    <h2 class="title_b title_other">
		                        <span class="sepa_l">
							    	<#if group_car_and_sell_car.hyperlink?exists>
							    		<a pl="pg=zixuIndex&pl=ershouche" style="font-size:18px;font-weight:700" target="_blank"  href="${group_car_and_sell_car.hyperlink}">
								    </#if>
								   	${group_car_and_sell_car.title}
								  	<#if group_car_and_sell_car.hyperlink?exists>
								    	</a>
							    	</#if>	
		                        </span>
		                    </h2>
		                </div>
		                <div class="container_1">
		                    <ul class="content_list">
		                    	 <#list group_car_and_sell_car_contentList as content>
				        			<#if content_index < 6>
				        				<li><a pl="pg=zixuIndex&pl=ershouche" target="_blank" href="${content.hyperlink!''}">${content.title!''}</a></li>
				        			</#if>
				        		</#list>
		                    </ul>
		                </div>
		            </div>
	            </#if>
            </#if>
            <#if city.id!=10>
	           <#if  zx_weeks_button_contentList?exists>
				 	<#list zx_weeks_button_contentList as content>
						<#if content_index ==0>
							<a href="${content.hyperlink!''}" target="_blank">
								 <img src="${staticFile}/public/images/blank.gif" data-url="${content.imagUrl!''}" width="330" height="195"  class="scrollLoading"/>
							</a>
							<a href="${content.hyperlink!''}" class="img_tit">${content.title!''}</a>
						</#if>
					</#list>
				</#if>
			</#if>
        </div>
    </div>
</div>