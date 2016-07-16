<!-- 汽车摇号  -->
<#if car_yaohao?exists && car_yaohao_contentList?exists>
	<div class="container_1 content_space">
		<div class="container_1 border_b_a"><h2 class="title_b title_other">
			<#if car_yaohao.hyperlink?exists && car_yaohao.hyperlink!=''>
			<a target="_blank" href="${car_yaohao.hyperlink}">
			</#if>
			<!-- 板块名称 -->
			<#assign bigNum=car_yaohao.title?length/2>
	    	<#assign lanLen=bigNum?round>
			<span class="sepa_l">${car_yaohao.title?substring(0,lanLen)}</span>${car_yaohao.title?substring(lanLen)}
			
			<#if car_yaohao.hyperlink?exists && car_yaohao.hyperlink!=''>
			</a>
			</#if>
		</h2></div>
	    <div class="container_1">
	    	<ul class="content_list">
	    		<#if car_yaohao_contentList?exists>
		    		<#list car_yaohao_contentList as content >
		    			<#if content_index < 2>
		    				<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
		    			</#if>
		    		</#list>
	    		</#if>
	        </ul>
	    </div>
	</div>
</#if>