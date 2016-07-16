<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="tabs" class="tabs">  
	<ul>
		<li <c:if test="${currentPage == 'zixunManage'}">class="tabs_active"</c:if> <c:if test="${currentPage != 'zixunManage'}">  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" </c:if> ><a href="/zixun/home">管理资讯</a></li>
		<li <c:if test="${currentPage == 'newZixunProperties'}">class="tabs_active"</c:if> <c:if test="${currentPage != 'newZixunProperties'}"> style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%"</c:if> ><a href="/zixun/newZixunProperties">编辑资讯</a></li>
	</ul>
</div>
