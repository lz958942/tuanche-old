<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>团车网专题管理</title>
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
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
</head>
<body>
<form action="/specialSubject/search" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="specialSubject" id="searchSpecialForm">
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/specialSubject/home">一级品牌列表</a></li>
<!-- 						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/specialSubject/toAdd">添加</a></li>
 -->					</ul>
			   </div>
			</td>
		</tr>

			<table>
				<tr class="lh28">
					<td class="ti">一级品牌编号:</td>
					<td><input type="text" class="clearError" id="specialId" name="id" style="width:300px;" value="${specialSubject.id }" maxlength="20" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " /></td>
					<td class="ti">一级品牌名称:</td>
					<td><input type="text" name="spName" id="title" maxlength="40" style="width:300px;" value="${specialSubject.spName }"/></td>
				</tr>
				<tr class="lh28">
					<td class="ti">:</td>
					<td><input type="text"  id="keywords" name="keywords" style="width:300px;" value="${specialSubject.keywords }" maxlength="25"/></td>
					<td class="ti">专题描述:</td>
					<td><input type="text" id="spDesc" name="spDesc" style="width:300px;" value="${specialSubject.spDesc }" maxlength="100"/></td>
				</tr>
				
				<tr class="lh28">
					<td class="ti">上线时间:</td>
					<td>
						<input id="onlineDate" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss','起始日期')" readonly="readonly" name="onlineDate" style="width:300px;" value="${specialSubject.onlineDate }"/>
					</td>
				</tr>
				<tr class="lh28">
				</tr>
			</table>
			<div align="left">
			<input type="button" value="搜索"  class="btn" onclick="addBrand();"/> 
			<input type="button" value="添加一级品牌"  class="btn" onclick="addBrand();"/>
			<a  id="aForSearchAll" href="/specialSubject/home">所有品牌</a> <font color="red" id="errorFont"></font>
			</div>
			
			<div style="text-align:left;line-height:25px;">
				<input name="hyperlink" id="hyperlink" type="hidden" value=""/>
			</div>
  			<div>
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr">
							<!-- <th><input type="checkbox" id="allCheck" onclick="selectAll()"/></th> -->
							<th style="white-space:nowrap;">一级品牌品牌编号</th>
							<th style="white-space:nowrap;">品牌名称</th>
							<th style="white-space:nowrap;">英文名称</th>
								<th style="white-space:nowrap;">拼音</th>
							<th style="white-space:nowrap;">logo图片</th>
							<th style="white-space:nowrap;">添加时间</th>
							<th style="white-space:nowrap;">添加人</th>
							<th style="white-space:nowrap;">修改时间</th>
							<th style="white-space:nowrap;">修改人</th>
							<th style="white-space:nowrap;">系别</th>
							<th style="white-space:nowrap;">操作</th>
							
						</tr>
					</thead>
					<c:if test="${bList!=null }">
						<c:forEach items="${bList }" var="blist">
						<tr>
						<%-- ${func:getEditName(zixun.editorId)} --%>
							<%-- <th><input name="idBox" type="checkbox"value="${blist.id}" /></th> --%>
							<td>${blist.id}</td>
							<td>${blist.name}</td>
							<td>${blist.enname}</td>
							<td>${blist.pinyin}</td>
							<td><img src="${blist.logo}"> </td>
							<td>${blist.createTime}</td>
							<td>${blist.createUserId}</td>
							<td>${blist.updateTime}</td>
							<td>${blist.updateUserId}</td>
							<td>
							<c:if test="${blist.newSeries==1}">德系</c:if> 
							<c:if test="${blist.newSeries==2}">日系</c:if> 
							<c:if test="${blist.newSeries==3}">美系</c:if> 
							<c:if test="${blist.newSeries==4}">欧系</c:if> 
							<c:if test="${blist.newSeries==5}">韩系</c:if> 
							<c:if test="${blist.newSeries==6}">法系</c:if> 
							<c:if test="${blist.newSeries==7}">其他</c:if> 
							</td>
							<td><a href="/sites/brand/AlertBefore?id=${blist.id}">编辑 </a> <a href="/sites/brand/selectToBrandList?id=${blist.id}">品牌管理 </a></td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${bList==null }">
					<tr class="main_info">
						<td colspan="14">没有相关数据</td>
					</tr>
					</c:if>
				</table>
			</div>
			<!-- <div style="text-align:left;">
				<input type="button" value="删除" class="chop" onclick="deleteSelect(-1)"/>
				<input type="button" value="上线" class="chop" onclick="deleteSelect(1)"/>
				<input type="button" value="下线" class="chop" onclick="deleteSelect(2)"/>
				<input type="button" value="同步线上" class="chop" onclick="createHtmlBySelect()"/>
				<input type="button" value="测试过后" class="chop" onclick="byValue()"/>
			</div> -->
			<div class="page_and_btn" style="text-align:center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
</table>
	</form>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
</body>
</html>