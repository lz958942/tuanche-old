<div class="gui">
	<ul class="list1">
		<li <c:if test="${tagType == 'resaleTag'}">class="up"</c:if>><a class="aforseo" href="/topic/tag/newResaleTag/1">二手房</a></li>
		<li <c:if test="${tagType == 'rentTag'}">class="up"</c:if>><a class="aforseo" href="/topic/tag/newRentTag/1">租房</a></li>
		<li <c:if test="${tagType == 'estateTag'}">class="up"</c:if>><a class="aforseo" href="/topic/tag/newEstateTag/1">小区</a></li>
		<!-- <li <c:if test="${tagType == 'agentTag'}">class="up"</c:if>><a class="aforseo" href="/topic/tag/newAgentTag">经纪人</a></li> -->
		<li <c:if test="${tagType == 'savedTag'}">class="up"</c:if>><a class="aforseo" href="/topic/tag/savedTag">已保存的标签</a></li>
		<%-- <li <c:if test="${tagType == 'tagList'}">class="up"</c:if>><a class="aforseo" href="/topic/tag/tagList">标签模板列表</a></li> --%>
	</ul>
</div>
