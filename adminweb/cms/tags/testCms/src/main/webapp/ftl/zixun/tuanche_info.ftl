<#macro tuanche_info plate contentList>
<dd>
	<#if plate!="">
		<#if plate.hyperlink?exists >
			<a target="_blank"  href="${city.py}${plate.hyperlink}">
		</#if>
			<h3 class="title_d">${plate.title}</h3>
		<#if plate.hyperlink?exists >
			</a>
		</#if>
	</#if>
	<#list contentList as content>
		<#if content.imagUrl?exists && content_index == 0 >
			<div class="car_new_img">
		    	<a target="_blank" href="${content.hyperlink}"><img src="${content.imagUrl}" alt="" width="267" height="140" data-url="${content.imagUrl}" class="scrollLoading" /></a>
		    	<a target="_blank" href="${content.hyperlink}" class="img_tit">${content.title}</a>
			</div>
		</#if>
     </#list>
	 
    <div class="tc_container_1">
    	<ul class="content_list content_list_s clearfix">
    		<#list contentList as content1>
				<#if content1_index!= 0 && content1_index < 5 >
           			<li><a target="_blank"  href="${content1.hyperlink}">${content1.title}</a></li>
            	</#if>
		    </#list>
        </ul>
    </div>
</dd>
</#macro >