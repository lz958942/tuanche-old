 <!-- 团购车型排行榜 -->
<div class="tc_container_1">
	<div class="tc_title_wrap tc_rank_tit clearfix">
		<a target="_blank"  href="">
   			<h2 class="tc_title">团购车型排行榜</h2>
   		</a>
    </div>
    <div class="tc_rank">
    	<dl>
    		<#if car_style_top?exists>
	    		<#list car_style_top as carStyle>
	    			<#if carStyle_index == 0>
			        	<dt class="clearfix">
			            	<i class="rank_ico rank_1">1</i>
			                <div class="rank_pic">
			                	<a href="##"><img src="${carStyle.spic}" alt="" width="160" height="108" data-url="../public/images/blank.gif" class="scrollLoading" /></a>
			                </div>
			                <div class="rank_info clearfix">
			                    <div class="rank_car"><a target="_blank" href="${carStyle.url}">${carStyle.carstyle}</a></div>
			                    <div class="rank_num">${carStyle.passNum}</div>
			                </div>
			            </dt>
	           		</#if>
	           		<#if carStyle_index gt 0>
			            <dd class="clearfix">
			            	<i class="rank_ico rank_2">${carStyle_index}</i>
			                <div class="rank_info clearfix">
			                    <div class="rank_car"><a target="_blank" href="${carStyle.url}">福克斯</a></div>
			                    <div class="rank_num">5678</div>
			                </div>
			            </dd>
		            </#if>
	             </#list>
            </#if>
        </dl>
    </div>
</div>