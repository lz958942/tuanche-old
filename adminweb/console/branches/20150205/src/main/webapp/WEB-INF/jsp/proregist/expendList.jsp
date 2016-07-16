<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<!-- <script type="text/javascript" src="/js/ajaxfileupload.js"></script> -->
<script type="text/javascript" src="/js/decorate/decorate.js"></script>
<style type="text/css">
	.uploadCss{
		max-width: 350px;
		max-height: 92px;
	}
</style>
<title>页面板块管理</title>
</head>
<body>
<form action="/decorate/toExpendList?bid=${decorateTemp.baseId  }" method="post" id="addExpendForm">
	<div id="man_zone">
		<div>
			<div class="b-con a-form">
	          	<div class="pd5">
	          		<label class="pr15">版块名称:
	          			<select name="plateId">
							<option value="">请选择</option>
							<c:forEach items="${plateList }" var="plateList">
								<option value="${plateList.code }" ${decorateTemp.plateId==plateList.code?'selected':'' }>${plateList.name }</option>
							</c:forEach>
						</select>
	          		</label>
	          		<label class="pr15">小标题名:
	          			<input id="title" type="text"  name="title" value="${decorateTemp.title }" />
	          		</label>
	          		<label class="pr15">标题样式:
	          			<select name="titleShowStyle">
							<option value="">请选择</option>
							<option value="1" style="color: #383838;font-size: 16px" ${decorateTemp.titleShowStyle=='1'?'selected':'' }>样式一</option>
							<option value="2" style="color: #2457a6;font-size: 16px" ${decorateTemp.titleShowStyle=='2'?'selected':'' }>样式二</option>
						</select>
	          		</label>
	          		<label class="pr15">内容样式:
	          			<select name="titleContentStyle">
							<option value="">请选择</option>
							<option value="1" ${decorateTemp.titleContentStyle=='1'?'selected':''  }>文字</option>
							<option value="2" ${decorateTemp.titleContentStyle=='2'?'selected':''  }>图片</option>
							<option value="3" ${decorateTemp.titleContentStyle=='3'?'selected':''  }>图文</option>
						</select>
	          		</label>
	         		<label class="pr15">
	       				创建日期:
	       				<div class="input-prepend">
	                   		<span class="add-on"><i class="icon-calendar"></i></span>
	                   		<input id="starttime" type='text' name='startTime' class="querytime span2" value="${decorateTemp.startTime }" readonly="readonly" autocomplete="off" />
	                   	</div>-
	                  	<div class="input-prepend">
	                    	<span class="add-on"><i class="icon-calendar"></i></span>
	                    	<input type='text' id="endtime" name='endTime' class="querytime span2" value="${decorateTemp.endTime }" readonly="readonly" autocomplete="off" />
	                   	</div>
	          		</label>
	          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;&nbsp;
	          	</div>
	         </div>
	         <div style="height: 10px"></div>
	         <div>
				<div class="b-con a-form" style="height: 30px">
		          	<div class="pd5">
		          		<input id="showAndHid" type="button" value="添加板块" style="height: 25px" class="btn btn-info" onclick="showForm();"/>&nbsp;&nbsp;
		          	</div>
		         </div>
			</div>
		</div>
		<div class="rb-con">
				<div class="over-auto">
					<table class="table table-bordered chargeTable">
						<tr>
							<td style="text-align: center;">ID</td>
							<td style="text-align: center;">标题名称</td>
							<td style="text-align: center;">板块名称</td>
							<td style="text-align: center;">小标题</td>
							<td style="text-align: center;">标题样式</td>
							<td style="text-align: center;">内容样式</td>
							<td style="text-align: center;">显示级别(倒序)</td>
							<td style="text-align: center;">操作人</td>
							<td style="text-align: center;">操作时间</td>
							<td style="text-align: center;">操作</td>
						</tr>
						<c:if test="${decTempList!=null&&decTempList.size()>0 }">
							<c:forEach items="${decTempList }" var="decTempList">
								<tr>
									<td style="text-align: center;" width="50px">${decTempList.id }</td>
									<td style="text-align: center;" width="200px">${decTempList.baseTitle }</td>
									<td style="text-align: center;" width="100px">${func:getPlateName(decTempList.plateId) }</td>
									<td style="text-align: center;" width="100px">${decTempList.title }</td>
									<td style="text-align: center;" width="100px">
										<c:if test="${decTempList.titleShowStyle=='1' }">
											<label style="color: #383838;font-size: 16px">样式一</label>
										</c:if>
										<c:if test="${decTempList.titleShowStyle=='2' }">
											<label style="color: #2457a6;font-size: 16px">样式二</label>
										</c:if>
									</td>
									<td style="text-align: center;" width="100px">
										<c:if test="${decTempList.titleContentStyle=='1' }">文字</c:if>
										<c:if test="${decTempList.titleContentStyle=='2' }">图片</c:if>
										<c:if test="${decTempList.titleContentStyle=='3' }">图文</c:if>
									</td>
									<td style="text-align: center;" width="100px">${decTempList.sort }</td>
									<td style="text-align: center;" width="200px">${decTempList.addUserName }</td>
									<td style="text-align: center;" width="200px">${decTempList.addTime }</td>
									<td style="text-align: center;" width="200px">
										<a href="javascript:deletePlate(${decTempList.id },${decTempList.baseId });">删除</a>
										<a href="javascript:selectPlateById(${decTempList.id });">修改</a>
										<a href="javascript:selectContentById(${decTempList.id },${decTempList.titleContentStyle},${decTempList.baseId });">内容管理</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${decTempList==null||decTempList.size()<0 }">
							<tr>
								<td colspan="10">没有数据</td>
							</tr>
						</c:if>
			          </table>
			     </div>
			</div>
	</div>
	<div class="page_and_btn" style="text-align:center;">
	   	<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>
<%@include file="addExpend.inc" %>
<%-- <%@include file="addContent.jsp" %> --%>
</body>

</html>