<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/specialSubject/specialSubject.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
</head>
<body>
<form action="/specialSubject/searchTemp" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="template" id="searchTemplateForm">
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/specialSubject/tempHome">模板列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/specialSubject/toAddTemp">新建模板</a></li>
					</ul>
			   </div>
			</td>
		</tr>

			<table>
				<tr class="lh28">
					<td class="ti">模板编号:</td>
					<td><input type="text" class="clearError" id="id" name="id" style="width:300px;" value="${template.id }" maxlength="20" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "  /></td>
					<td class="ti">模板名称:</td>
					<td><input type="text" name="tpName" id="tpName" maxlength="40" style="width:300px;" value="${template.tpName }"/></td>
				</tr>
				<tr class="lh28">
					<td class="ti">模板描述:</td>
					<td><input type="text" id="tpDesc" name="tpDesc" style="width:300px;" value="${template.tpDesc }" maxlength="100"/></td>
					<td class="ti">创建人:</td>
					<td align="left">
						<select name="createUserId">
						    <option value="" >--请选择--</option>
							<c:forEach items="${createUser }" var="createUser">
								<option value="${createUser.createUserId }"<c:if test="${createUser.createUserId==template.createUserId }">selected</c:if>>${createUser.createUserName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr class="lh28">
					<td class="ti">创建时间:</td>
					<td>
						<input class="dateCss" type="text" id="createTime" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss','')" readonly="readonly" name="createTime" style="width:300px;" value="${template.createTime }"/>
					</td>
				</tr>
			</table>
			<div align="left">
			<input type="button" value="搜索"  class="btn" onclick="searchSubimt(2);"/>
			<a  id="aForSearchAll" href="/specialSubject/tempHome" >所有模板</a> <font color="red" id="errorFont"></font>
			</div>
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr">
							<th style="white-space:nowrap;">模板编号</th>
							<th style="white-space:nowrap;">模板名称</th>
							<th style="white-space:nowrap;">模板描述</th>
							<th style="white-space:nowrap;">创建时间</th>
							<th style="white-space:nowrap;">更新时间</th>
							<th style="white-space:nowrap;">操作人员</th>
							<th style="white-space:nowrap;">操作</th>
						</tr>
					</thead>
					<tbody align="center" >
					<c:choose>
						<c:when test="${not empty tempByPage }">
							<c:forEach items="${tempByPage }" var="template">
															
									<tr class="" id="sp_${template.id }">
										<td>${template.id }</td>
										<td>${template.tpName }</td>
										<td>${template.tpDesc }</td>
										<td>${template.createDate }</td>
										<td>${template.updateTime }</td>
										<td>${template.createUserName }</td>
										<td>
											<a href="/specialSubject/preUpdateTemp?id=${template.id }">修改</a>
											<a href="javascript:deleteTp(${template.id })">删除</a> 
										</td>
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>											
								<tr class="main_info">
									<td colspan="14">没有相关数据</td>
								</tr>
						</c:otherwise>					
					</c:choose>													
					</tbody>
				</table>
			</div>
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