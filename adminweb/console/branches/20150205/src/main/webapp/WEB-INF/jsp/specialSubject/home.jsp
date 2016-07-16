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

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/specialSubject/specialSubject.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
</head>
<body>
<form action="/specialSubject/search" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="specialSubject" id="searchSpecialForm">
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/specialSubject/home">专题列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/specialSubject/toAdd">新建专题</a></li>
					</ul>
			   </div>
			</td>
		</tr>

			<table>
				<tr class="lh28">
					<td class="ti">专题编号:</td>
					<td><input type="text" class="clearError" id="specialId" name="id" style="width:300px;" value="${specialSubject.id }" maxlength="20" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " /></td>
					<td class="ti">专题名称:</td>
					<td><input type="text" name="spName" id="title" maxlength="40" style="width:300px;" value="${specialSubject.spName }"/></td>
				</tr>
				<tr class="lh28">
					<td class="ti">专题关键字:</td>
					<td><input type="text"  id="keywords" name="keywords" style="width:300px;" value="${specialSubject.keywords }" maxlength="25"/></td>
					<td class="ti">专题描述:</td>
					<td><input type="text" id="spDesc" name="spDesc" style="width:300px;" value="${specialSubject.spDesc }" maxlength="100"/></td>
				</tr>
				
				<tr class="lh28">
					<td class="ti">起始时间:</td>
					<td>
						<input id="beginTime" class="dateCss" type="text" onclick="SelectDate(this,'yyyy-MM-dd','')" readonly="readonly" name="beginTime" style="width:300px;" value="${specialSubject.beginTime }"/>
					</td>
					<td class="ti">结束时间:</td>
					<td>
						<input id="endTime" class="dateCss" type="text" onclick="SelectDate(this,'yyyy-MM-dd','')" readonly="readonly" name="endTime" style="width:300px;" value="${specialSubject.endTime }"/>
					</td>
				</tr>
				<tr class="lh28">
					<%-- <td class="ti">城市:</td>
					<td>
						<input readonly="readonly"  id="city" onclick="showMenu(); return false;" id="city"  style="width:300px;height:25px" value="${func:getallCity(specialSubject.cityId==null?-1:specialSubject.cityId)}"/>
						<input  id="cityId" name="cityId" type="hidden" value="${specialSubject.cityId == null?-1:specialSubject.cityId}"/>
					</td> --%>
					<td class="ti">状态:</td>
					<td align="left">  
						<select name="spOnline">
							<option value="">--请选择--</option> 
							<option value="1" <c:if test="${specialSubject.spOnline==1 }">selected="select"</c:if>>上线</option>
							<option value="2" <c:if test="${specialSubject.spOnline==2 }">selected="select"</c:if>>下线</option>
						</select>
					</td>
					<td class="ti">编辑人员:</td>
					<td align="left">  
						<select name="operateUserId">
						    <option value="" >--请选择--</option>
							<c:forEach items="${operaList }" var="opera">
								<c:if test="${opera.operateUserId!=0 }">
									<option value="${opera.operateUserId }"<c:if test="${specialSubject.operateUserId==opera.operateUserId}">selected</c:if>><c:if test="${opera.operateUserId!=0 }">${opera.operateUserName }</c:if></option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<div align="left">
			<input type="button" value="搜索"  class="btn" onclick="searchSubimt(1);"/>
			<a  id="aForSearchAll" href="/specialSubject/home" >所有专题</a> <font color="red" id="errorFont"></font>
			</div>
			
			<div style="text-align:left;line-height:25px;">
				<input name="hyperlink" id="hyperlink" type="hidden" value=""/>
			</div>
  			<div>
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr">
							<th><input type="checkbox" id="allCheck" onclick="selectAll()"/></th>
							<th style="white-space:nowrap;">专题编号</th>
							<th style="white-space:nowrap;">专题名称</th>
							<th style="white-space:nowrap;">专题关联模板</th>
							<th style="white-space:nowrap;">专题url</th>
							<!-- <th style="white-space:nowrap;">关联城市</th> -->
							<th style="white-space:nowrap;">品牌车型</th>
							<th style="white-space:nowrap;">上线状态</th>
							<th style="white-space:nowrap;">创建时间</th>
							<th style="white-space:nowrap;">上线时间</th>
							<th style="white-space:nowrap;">操作人员</th>
							<th style="white-space:nowrap;">操作</th>
						</tr>
					</thead>
					<tbody align="center" id="dataBody">
					<c:choose>
						<c:when test="${not empty specialByPage }">
							<c:forEach items="${specialByPage }" var="specialSubject">
															
									<tr class="" id="sp_${specialSubject.id }">
									    <td><input type="checkbox" name="checkboxSpecial" class="checkboxSpecial" value="${specialSubject.id }"/></td>
										<td>${specialSubject.id }</td>
										<td width="270px" style="word-break:break-all">${specialSubject.spName }</td>
										<td>${specialSubject.tpName }</td>
										<td>
											<c:choose>
												<c:when test="${not empty specialSubject.ztType }">
													${specialSubject.spUrl }
												</c:when>
												<c:otherwise>
												
											    </c:otherwise>
											</c:choose>
										</td>
										<%-- <td>${specialSubject.cityTrueName }</td> --%>
										<c:if test="${specialSubject.carStyleId==null||specialSubject.carStyleId==''}">
										    	<input id="styleId" name="carStyleId" type="hidden" value="${specialSubject.brandId},"/>
										        <c:set  var="valueStr" value="${specialSubject.brandId}" scope="request" ></c:set>
										</c:if>
										<c:if test="${specialSubject.carStyleId!=null&&specialSubject.carStyleId!=''}">
									        <c:set  var="valueStr" value="${specialSubject.brandId}-${specialSubject.carStyleId}" scope="request" ></c:set>
										</c:if>
										<td>${func:getStyleInfo(valueStr)}</td>
											<td id="specials_status">
												<c:choose>
													<c:when test="${specialSubject.spOnline==1 }">
														上线
													</c:when>
													<c:otherwise>
														未上线
													</c:otherwise>
												</c:choose>
											</td>
										<td>${specialSubject.pubTime }</td>
										<td><c:if test="${specialSubject.onlineTime!=null&&specialSubject.onlineTime!='' }">${specialSubject.onlineTime }</c:if></td>
										<td>${specialSubject.operateUserName }</td>
										<td>
											<c:choose>
												<c:when test="${specialSubject.spOnline==1 }">
													<a id="specials_upOrDown" href="javascript:upOrDown(${specialSubject.id },${specialSubject.spOnline });">下线</a>
												</c:when>
											    <c:otherwise>
												    <a  id="specials_upOrDown" href="javascript:upOrDown(${specialSubject.id },${specialSubject.spOnline });">上线</a>	
											    </c:otherwise>
		                                    </c:choose>																						
											<a href="/specialSubject/preUpdate?id=${specialSubject.id }&pageNo=${page.pageNo}">修改</a>
											<a href="javascript:deleteSp('${specialSubject.id }')">删除</a> 
											<%-- <a target="_blank" href="/specialSubject/createHtml?id=${specialSubject.id }" onclick="return selectTemp('${specialSubject.templateId }')">浏览</a>  --%>
											<a target="_blank" href="http://tuanche.com/zt-${specialSubject.ztType=='1'?'mc':'htxc'}/${specialSubject.id }">浏览</a> 
											<a href="javascript:tOnline('${specialSubject.spUrl }')">同步线上</a> 
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
			<div style="text-align:left;">
				<input type="button" value="删除" class="chop" onclick="deleteSelect(-1)"/>
				<input type="button" value="上线" class="chop" onclick="deleteSelect(1)"/>
				<input type="button" value="下线" class="chop" onclick="deleteSelect(2)"/>
				<input type="button" value="同步线上" class="chop" onclick="createHtmlBySelect()"/>
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