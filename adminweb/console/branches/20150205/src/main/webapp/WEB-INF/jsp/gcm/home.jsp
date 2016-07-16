<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>团车网品牌管理</title>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery.tipsy.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
 <!-- ztree ue-->
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/sites/sites.js"></script>
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/gcm/gcm.js"></script>
</head>
<body>
<form action="/gcm/toList" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="gmsCity" id="gmsCity">
	<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active<c:if test="${isList == null}">_no</c:if>" ><a href="/gcm/toList">团车会列表</a></li>
						<li  ><a href="/gcm/toAdd">添加团车会</a></li>
					</ul>
			   </div>
			</td>
		</tr>
	</table>
	<table>
		<tr class="lh28">
			<td class="ti">城市:</td>
			<td>
				<select  name="cityId" >
				<option value="">全国</option>
				<c:forEach var="city" items="${citys}">
					<option value="${city.value.id}" ${sCityId==city.value.id?"selected":"" }>${city.value.orderName}</option>
				</c:forEach>
				</select>
				<input type="submit" value="搜索"  class="btn"/>
			</td>
		</tr>
		<tr class="lh28">
		</tr>
	</table>
	<div style="text-align:left;line-height:25px;">
		<input name="hyperlink" id="hyperlink" type="hidden" value=""/>
	</div>
	<div style="height: 380px;OVERFLOW: auto;">
		<table class="table_style table table-bordered" >
			<thead>
				<tr class="attr">
					<th style="white-space:nowrap;">团车会编号</th>
					<th style="white-space:nowrap;">城市</th>
					<th style="white-space:nowrap;">期数</th>
					<th style="white-space:nowrap;">标题</th>
					<th style="white-space:nowrap;">链接</th>
					<th style="white-space:nowrap;">开始时间</th>
					<th style="white-space:nowrap;">结束时间</th>
					<th style="white-space:nowrap;">隐藏</th>
					<th style="white-space:nowrap;">添加时间</th>
					<th style="white-space:nowrap;">添加人</th>
					<th style="white-space:nowrap;">修改时间</th>
					<th style="white-space:nowrap;">修改人</th>
					<th style="white-space:nowrap;">操作</th>
				</tr>
			</thead>
			<c:if test="${list!=null }">
				<c:forEach items="${list }" var="blist">
				<tr>
					<td>${blist.id}</td>
					<td>${func:getallCity(blist.cityId)}</td>
					<td>第${blist.periodsNum}期</td>
					<td>${blist.title}</td>
					<td>
						<c:if test="${func:getCityPY(blist.cityId)!=''}">
							http://${func:getCityPY(blist.cityId)}.tuanche.com/tch/${blist.periodsNum}/
						</c:if>
					</td>
					<td>${blist.beginTimeStr}</td>
					<td>${blist.endTimeStr}</td>
					<td>
						<c:choose>
							<c:when test="${blist.isShow == 2}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${blist.createTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${blist.createUserName}</td>
					<td><fmt:formatDate value="${blist.updateTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${blist.updateUserName}</td>
					<td>
						<a href="/gcm/toUpdate?id=${blist.id}"  target="manFrame">编辑 </a> 
						<c:choose>
							<c:when test="${blist.online == 1}">
								<a href="javascript:void(0)" onclick="goline(${blist.id},2,this);">上线</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)" onclick="goline(${blist.id},1,this);">下线</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${list.size()<1} ">
				<tr class="main_info">
					<td colspan="14">没有相关数据</td>
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