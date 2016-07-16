<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="/js/template/plate.js"></script>
</head>
<body>
<form id="pagerForm" target="manFrame" name="plate" action="/plate/toList" method="post">
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	
		<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							城市：
						</td>
						<td>
							<input type="text" name = "cityName" value="${plate.cityName}"/>
						</td>
						<td>
							板块名称：
						</td>
						<td>
							<input type="text" name="plateName" value="${plate.plateName}"/>
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
		<li class=""><a class="add" href="/plate/toAdd" target="manFrame"> <span>添加新板块</span> </a></li>
		<li class="line">line</li>
		<li><a class="edit updatePlate" target="manFrame" ><span>修改</span></a></li>
		<li class="line">line</li>
		<li><a class="" ><span></span></a></li>
		<li><a class="" ><span></span></a></li>
		<li><a class="delete deletePlate" ><span>删除</span></a></li>
	</ul>
</div>
<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid; OVERFLOW: auto; ">
	<div layoutH="70" style="OVERFLOW: auto; height: 360px">
	<table class="list" width="100%">
		<thead>
			<tr align="center">
				<th>序号</th>
				<th>城市</th>
				<th>名称</th>
				<th>板块类型</th>
				<th>标签</th>
				<th>展示类型</th>
				<th>数据类型</th>
				<th>标题</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="tlcity">
			<c:forEach items="${plateList }" var="item" varStatus="var">
				<tr align="center" target="sid_obj"  rel="${item.id}" class='trColor<c:if test="${var.index%2==0}"> trbg</c:if>'>
					<td>${var.index + 1}</td>
					<td>${item.cityName}</td>
					<td>${item.plateName}</td>
					<td>
						<c:choose>
							<c:when test="${item.ptype==1}">
							资讯板块
							</c:when>
							<c:when test="${item.ptype==2}">
							城市板块
							</c:when>
							<c:when test="${item.ptype==3}">
							摇号板块
							</c:when>
						</c:choose>
					</td>
					<td>${item.label}</td>
					<td>
						<c:choose>
							<c:when test="${item.type==1}">
							图文
							</c:when>
							<c:when test="${item.type==2}">
							图片
							</c:when>
							<c:when test="${item.type==3}">
							文字
							</c:when>
							<c:otherwise>
							特殊
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:if test="${item.type!= 4}">
							<c:choose>
								<c:when test="${item.dataType==1}">
								资讯
								</c:when>
								<c:when test="${item.dataType==2}">
								团购
								</c:when>
								<c:when test="${item.dataType==3}">
								专题
								</c:when>
								<c:when test="${item.dataType==4}">
								团长
								</c:when>
								<c:when test="${item.dataType==5}">
								车型
								</c:when>
								<c:when test="${item.dataType==9}">
								其它
								</c:when>
							</c:choose>
						</c:if>
					</td>
					<td>${item.title}</td>
					<td>
						<c:if test="${item.dataFillMode ==2 ||item.type ==4}"><a href="/content/toList?plateId=${item.id}" style="font-size:14px;" target="manFrame">内容管理</a></c:if>
					</td>
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