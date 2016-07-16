<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>团车网二手车车型管理</title>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/usedCarstyle.js"></script>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
</head>
<body>
<form action="/used/toListFtype" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="specialSubject" id="searchSpecialForm">
	<div id="tabs" class="tabs">  
		<ul>
			<li class="tabs_active"><a href="/used/toListFtype">二手车车型列表</a></li>
			<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/used/toAdd?modelstr=type">添加二手车车型</a></li>
		</ul>
   </div>
   <table>
		<tr class="lh28">
			<td class="ti" >品牌名称:</td>
			<td>
			<select name="bid" >
				<option value="">请选择品牌</option>
				<c:forEach items="${pBrands}" var="pb">
				<option value="${pb.id }"  <c:if test="${pb.id==usedCarstyle.bid }"> selected="selected"</c:if>>${pb.typepinyI }${pb.name }</option>
				</c:forEach>
			</select>
			车型名称： <input type="text" name="cname" id="cname" maxlength="20" style="width:300px;" value="${usedCarstyle.cname}"/>
			<input type="submit" value="搜索"  class="btn btn-info" /> 
			</td>
		</tr>
		<tr class="lh28">
		</tr>
	</table>
	<div style="text-align:left;line-height:25px;">
		<input name="hyperlink" id="hyperlink" type="hidden" value=""/>
	</div>
	<div>
		<table class="table_style table table-bordered" >
			<thead>
				<tr class="attr">
					<th style="white-space:nowrap;">车型编号</th>
					<th style="white-space:nowrap;">所属品牌</th>
					<th style="white-space:nowrap;">关联新车车型</th>
					<th style="white-space:nowrap;">车型名称</th>
					<th style="white-space:nowrap;">添加时间</th>
					<th style="white-space:nowrap;">添加人</th>
					<th style="white-space:nowrap;">修改时间</th>
					<th style="white-space:nowrap;">修改人</th>
					<th style="white-space:nowrap;">操作</th>
				</tr>
			</thead>
			<c:if test="${list != null }">
				<c:forEach items="${list }" var="obj">
					<tr>
						<td>${obj.id }</td>																																																												</td>
						<td>${func:getBrandName(obj.bid)} </td>																																																												</td>
						<td>${obj.srname} </td>																																																												</td>
						<td>${obj.cname}</td>																																																												</td>
						<td><fmt:formatDate value="${obj.createTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>																																																												</td>
						<td>${obj.createUserName}</td>																																																												</td>
						<td><fmt:formatDate value="${obj.updateTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>																																																												</td>
						<td>${obj.updateUserName}</td>																																																												</td>
						<td>
							<a href="/used/toUpdate?modelstr=type&id=${obj.id }">修改</a>
							<a  id="delete" onclick="deletec(${obj.id },'type')" >删除</a>
						</td>																																																												</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(list)==0}">
				<tr class="main_info">
					<td colspan="7">没有相关数据</td>
				</tr>
			</c:if>
		</table>
	</div>
	<div class="page_and_btn" style="text-align:center;">
		<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
</body>
</html>