<!-- 内容中间 -->
<div class="main_mid">
	<div class="main_slide scheme_slide">
    	<ul class="slides clearfix">
    		<#list 1..5 as num>
        		<#assign ad_='inrurn_image_ad'+num+''>
    			<#assign ad=ad_?eval!''>
    			<#if ad!=''>
	    			<li>
						<a href="${ad.adLink!""}" target="_blank">
							<img src="${ad.picName}" data-url='${ad.picName}'  alt="${ad.adTitle}" width="760" height="321" />
						</a>
					</li>
	    		</#if>
    		</#list>
       	</ul>
    </div>
    <div class="tc_container_1">
    	<dl class="tc_a_pic clearfix">
    		<#list 1..3 as num>
    			<#assign ad_='small_image_ad'+num+''>
    			<#assign ad=ad_?eval!''>
    			<#if ad!=''>
	    			<dd>
	    				<a href="${ad.adLink!''}" target="_blank">
	    				<img src="${ad.picName}" alt="${ad.adTitle}" width="250" height="161" class="scrollLoading" data-url='${ad.picName}' />
	    				</a>
	    			</dd>
	    		</#if>
    		</#list>
        </dl>
    </div>
</div>
<!-- 优势 -->
<div class="tc_advantage">
	<dl>
    	<dt>团车网服务保障</dt>
    	<dd class="color_1">
        	<div class="tc_advantage_wrap">
            	<p class="p_t"><i></i>低价</p>
                <p>开团前，团长会在本地区众多4S店之间逐一询价合作，保证价格竞争力</p>
            </div>
        </dd>
        <dd class="color_2">
        	<div class="tc_advantage_wrap">
            	<p class="p_t"><i></i>安全</p>
                <p>团车网会甄选本地优质正规4S店，以保证车源质量与数量</p>
            </div>
        </dd>
        <dd class="color_3">
        	<div class="tc_advantage_wrap">
            	<p class="p_t"><i></i>全程免费</p>
                <p>团车网给您提供全程免费服务，购车过程中不收取任何费用。 </p>
            </div>
        </dd>
    </dl>
</div>