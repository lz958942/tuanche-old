<!-- 新车速递 -->
<#if new_car_express?exists && new_car_express_contentList?exists>
		<div class="container_1 content_space"><h2 class="title_d">
			<#if new_car_express.hyperlink?exists> 
			<a target="_blank"  href="${new_car_express.hyperlink}">
			</#if>
			${new_car_express.title}
			<#if new_car_express.hyperlink?exists> 
			</a>
			</#if>
	    </h2><div class="container_1">
	        <ul class="content_list content_list_s">
	        	<#list new_car_express_contentList as content>
	        		<#if content_index < 3>
	        			 <li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
	        		</#if>
	        	</#list>
	        </ul>
	    </div>
	</div>
</#if>