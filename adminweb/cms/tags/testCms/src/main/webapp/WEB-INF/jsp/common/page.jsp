<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" id="page.pageSize" name="page.pageSize" value="${page.pageSize}" />
<input type="hidden" id="page.pageNo" name="page.pageNo" value="1" />
<input type="hidden" id="currentNo" name="currentNo" value="${page.pageNo }" />
<script type="text/javascript">

function getForm() {
	var e = document.getElementById("page.pageNo");
		while(e=e.parentNode){
			if(e.tagName=="FORM"){
				return e;
			}
			if(e.tagName=="BODY"){
				return null;
			}
		}
		
	}

function openPage(pagerNo) {
		
		var pageNo = document.getElementById("page.pageNo");
		var form = getForm();
			pageNo.value = pagerNo;
		if(form!=null){
			form.submit();
		}
	}
function changePageSize(pageSize) {
		var page_size = document.getElementById("page.pageSize");
		var pageNo = document.getElementById("page.pageNo");
		pageNo.value = 1;
		var form = getForm();
		page_size.value = pageSize;
		if(form!=null){
			form.submit();
		}
	}
</script>
页数：${page.pageNo}/${page.totalPage} 总记录数：${page.totalCount }


<c:if test="${page.totalPage > 1}">
&nbsp;&nbsp; <a href="javascript:openPage(1);">首页</a>&nbsp;&nbsp;
<c:choose>
	<c:when test="${page.pageNo==1}">
		<a href="#">上一页</a>
	</c:when>
	<c:otherwise>
		<a href="javascript:openPage(${page.pageNo-1});">上一页</a>
	</c:otherwise>
</c:choose>


<c:if test="${page.pageNo>5}">
<c:forEach begin="${page.pageNo-5}" end="${page.pageNo+5>=page.totalPage?page.totalPage:page.pageNo+2}" var="index">
	&nbsp;
	<c:choose>

	<c:when test='${page.pageNo==index }'>
		<a href="javascript:openPage(${index});">${index}</a>
	</c:when>

	<c:otherwise>
		<a href="javascript:openPage(${index});">[${index}]</a>
	</c:otherwise>

	</c:choose>
</c:forEach>

</c:if>
<c:if test="${page.pageNo<=5&&page.totalPage>1}">
<c:forEach begin="1" end="${page.totalPage>=8?8:page.totalPage}" var="index">
&nbsp;
	<c:choose>

	<c:when test='${page.pageNo==index }'>
		<a href="javascript:openPage(${index});">${index}</a>
	</c:when>

	<c:otherwise>
		<a href="javascript:openPage(${index});">[${index}]</a>
	</c:otherwise>

	</c:choose>
	
</c:forEach>

</c:if>
<c:choose>

	<c:when test='${page.pageNo==page.totalPage }'>
		<a href="#">下一页</a>
	</c:when>

	<c:otherwise>
		<a href="javascript:openPage(${page.pageNo+1});">下一页</a>
	</c:otherwise>

</c:choose>
&nbsp;&nbsp; <a href="javascript:openPage(${page.totalPage});">末页</a>&nbsp;
</c:if>
每页
<select onchange="changePageSize(this.value)">
	<option value="5" <c:if test="${page.pageSize==5 }"> selected="selected"</c:if>>5</option>
	<option value="10" <c:if test="${page.pageSize==10 }"> selected="selected"</c:if>>10</option>
	<option value="20" <c:if test="${page.pageSize==20 }"> selected="selected"</c:if>>20</option>
	<option value="30" <c:if test="${page.pageSize==30 }"> selected="selected"</c:if>>30</option>
	<option value="50" <c:if test="${page.pageSize==50 }"> selected="selected"</c:if>>50</option>
	<option value="100" <c:if test="${page.pageSize==100 }"> selected="selected"</c:if>>100</option>
</select>
条
