<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>二级品牌列表</title>
<script type="text/javascript" src="/js/sites/sites.js"></script>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function seelTo(){
		var ages="";
		var pid=$("#pid").val();
		alert(pid);
		var oneBrand=$("#oneBrand").val();
		var twoBrand=$("#toBrand").val();
		if(oneBrand!=null&&!oneBrand==""){
			ages+="&oneBrand="+oneBrand;
		}
		if(twoBrand!=null&&!twoBrand==""){
			ages+="&twoBrand="+twoBrand;
		}
		ages+="&pid="+pid;
		window.location.href="/sites/brand/seelTwoBrand?1=1"+ages;
	}
	function addTwoBrand(){
		var pid=$("#pid").val();
		openWindow("/sites/brand/addTwoBrandSaveBefore?pid="+pid,"yes",700,600)
	}
</script>
</head>
<body>
<form action="">
<font>当前位置>二级品牌管理</font>
<input type="hidden" id="oneBrandId" value="{}">
<table>
	<tr>
		<td>品牌：</td>
		<td>
		
			<select name="oneBrand" id="oneBrand">
				<option  value="" selected="selected">请选择</option>
				<c:forEach items="${pBrands}" var="brands">
					<option  value="${brands.id}">${brands.name}</option>
				</c:forEach>
			</select>
		</td>
		<td>关键词(二级品牌名称)：</td>
		<td><input type="text" name="name" id="toBrand" value="${twoBrand }"></td>
		<td><input type="button" value="搜索" onclick="seelTo()"></td>
	</tr>
	<tr><td><input id="asd" type="button" value="添加二级品牌" onclick="addTwoBrand()"></td></tr>
	
</table>
<input type="hidden" id="pid" value="${pid}">
<table>
	<tr>
		<td>品牌</td>
		<td>生产厂家性质</td>
		<td>操作</td>
	</tr>
	<c:if test="${bListTo!=null }">
		<c:forEach items="${bListTo }" var="bt">
		<tr>
			<td>${bt.name }</td>
			<td> <c:if test="${bt.vender==1}">合资</c:if>
				<c:if test="${bt.vender==2}">进口</c:if>
				<c:if test="${bt.vender==3}">国产</c:if>
			</td>
			<td><a href="/sites/brand/twoBrandUpdateBefore?id=${bt.id}">编辑</a>|<a href="/sites/brand/">管理车型</a></td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${bListTo==null }">
	暂无数据！
	</c:if>
</table>
</form>
</body>
</html>