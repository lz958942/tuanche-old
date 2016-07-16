<div id="tabs" class="tabs">  
	<ul>
		<li <c:if test="${currentPage == 'topicManage'}">class="tabs_active"</c:if> <c:if test="${currentPage != 'topicManage'}">  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" </c:if> ><a href="/topic/home">专题管理</a></li>
		<li <c:if test="${currentPage == 'newTopicProperties'}">class="tabs_active"</c:if> <c:if test="${currentPage != 'newTopicProperties'}"> style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%"</c:if> ><a href="/topic/newTopicProperties">新建专题</a></li>
		<li <c:if test="${currentPage == 'plateManage'}">class="tabs_active"</c:if> <c:if test="${currentPage != 'plateManage'}"> style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" </c:if>><a href="/topic/plateManage">模板管理</a></li>
		<li <c:if test="${currentPage == 'tagManage'}">class="tabs_active"</c:if> <c:if test="${currentPage != 'tagManage'}"> style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%"</c:if> ><a href="/topic/tag/newResaleTag/1">标签管理</a></li>
	</ul>
</div>
