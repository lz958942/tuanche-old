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
	    		<#list car_style_top?sort_by('passNum')?reverse as carStyle>
	    			<#if carStyle_index == 0>
			        	<dt class="clearfix">
			            	<i class="rank_ico rank_1">${carStyle_index}</i>
			                <div class="rank_pic">
			                	<a pl="pg=cityIndex&pl=top" target="_blank" href="${carStyle.url}"><img src="${staticFile}/public/images/blank.gif" alt="" width="160" height="108" data-url="${carStyle.spic!''}" class="scrollLoading" /></a>
			                </div>
			                <div class="rank_info clearfix">
			                    <div class="rank_car"><a pl="pg=cityIndex&pl=top" target="_blank" href="${carStyle.url}">${carStyle.title}</a></div>
			                    <div class="rank_num">${carStyle.passNum}</div>
			                </div>
			            </dt>
	           		</#if>
	           		<#if carStyle_index gt 0>
			            <dd class="clearfix">
			            	<i class="rank_ico rank_${carStyle_index+1}">${carStyle_index}</i>
			                <div class="rank_info clearfix">
			                    <div class="rank_car"><a pl="pg=cityIndex&pl=top" target="_blank" href="${carStyle.url}">${carStyle.title}</a></div>
			                    <div class="rank_num">${carStyle.passNum}</div>
			                </div>
			            </dd>
		            </#if>
	             </#list>
            </#if>
        </dl>
    </div>
</div>