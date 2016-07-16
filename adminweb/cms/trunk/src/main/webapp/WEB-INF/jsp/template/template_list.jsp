<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/fmt.tld"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/common/import.jsp" %>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery.tipsy.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
<link href="/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
 <!-- ztree ue-->
 
  <script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
  
  <script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script>
  
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />

<script type="text/javascript" src="/js/template/template.js"></script>
</head>
<body>
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
</div>
<div class="panelBar">
	<ul class="toolBar">
		<li class=""><a class="add" href="/template/toAdd" target="manFrame" width="800" height="550"> <span>添加基础模板</span> </a></li>
		<li class="line">line</li>
		<li><a class="edit toUpdate" target="manFrame"  width="800" height="550" mask="true"><span>修改</span></a></li>
		<li class="line">line</li>
		<li><a class="" ><span></span></a></li>
		<li><a class="" ><span></span></a></li>
		<li><a class="delete deleteButton" ><span>删除</span></a></li>
	</ul>
</div>
<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid; OVERFLOW: auto; height: 430px" >
	<div layoutH="70">
		<table class="list" width="100%">
		<thead>
			<tr align="center">
				<th>序号</th>
				<th>模板名称</th>
				<th>标签</th>
				<th>模板备注</th>
				<th>创建时间</th>
			</tr>
			</thead>
			<tbody id="temList">
			<c:forEach items="${templateList }" var="item" varStatus="var">
				<tr align="center" target="sid_obj"  rel="${item.id}" class='trColor<c:if test="${var.index%2==0}"> trbg</c:if>'>
					<td>${var.index + 1}</td>
					<td>${item.name}</td>
					<td>${item.pic}</td>
					<td>${item.memo }</td>
					<td><fmt:formatDate value="${item.createTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>
<form action="/template/toList" target="manFrame">
	<%@include file="/WEB-INF/jsp/common/page.jsp"%>
</form>


</body>
</html>