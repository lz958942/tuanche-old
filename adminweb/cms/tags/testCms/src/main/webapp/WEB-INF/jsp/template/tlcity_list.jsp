<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<script type="text/javascript" src="/js/template/tlcity.js"></script>
</head>
<body>
<form id="pagerForm" target="manFrame" name="tlcity" action="/tlcity/toList" method="post">
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	
		<input type="hidden" name="pageNum" value="1" /> 
		<input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							城市：
						</td>
						<td>
						<input type="text" name = "cityName" value="${tlcity.cityName}"/>
						</td>
						<td align="center">
							<div class="subBar">
								<ul>
									<li>
										<div class="buttonActive" id="display_search">
											<div class="buttonContent">
												<button type="submit">搜索</button>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	
</div>
<div class="panelBar">
	<ul class="toolBar">
		<li class=""><a class="add" href="/tlcity/toAdd" target="manFrame"> <span>添加城市模板</span> </a></li>
		<li class="line">line</li>
		<li><a class="edit updateTlcity" target="manFrame" ><span>修改</span></a></li>
		<li class="line">line</li>
		<li><a class="edit viewHtml" target="_blank" id="viewHtml"><span>预览城市模板</span></a></li>
		<li class="line">line</li>
		<li><a class="edit makeHtml" target="manFrame"  ><span>生成城市首页</span></a></li>
		<li class="line">line</li>
		<li><a class="" ><span></span></a></li>
		<li><a class="" ><span></span></a></li>
		<li><a class="delete deleteTlcity" ><span>删除</span></a></li>
	</ul>
</div>

<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid; OVERFLOW: auto;">
	<div layoutH="70" style="OVERFLOW: auto; height:400px">
	<table class="list" width="100%">
		<thead>
			<tr align="center">
				<th>序号</th>
				<th>城市</th>
				<th>城市类型</th>
				<th>模板名称</th>
				<th>创建用户</th>
				<th>创建时间</th>
			</tr>
			</thead>
			<tbody id="tlcity">
			<c:forEach items="${tlcityList }" var="item" varStatus="var">
				<tr align="center" target="sid_obj"  rel="${item.id}" class='trColor<c:if test="${var.index%2==0}"> trbg</c:if>'>
					<td>${var.index + 1}</td>
					<td>${item.cityName}</td>
					<td>
						<c:choose>
							<c:when test="${item.tlcitytype==1}">资讯页面</c:when>
							<c:when test="${item.tlcitytype==2}">城市页面</c:when>
							<c:when test="${item.tlcitytype==3}">摇号页面</c:when>
						</c:choose>
					</td>
					<td>${item.tname}</td>
					<td>${item.createUserName}</td>
					<td><fmt:formatDate value="${item.createTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	
	<%@include file="/WEB-INF/jsp/common/page.jsp"%>
	
</div>
</form>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

</body>
</html>