<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/js/zixun/zixun.js?90"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>

<c:set var="currentPage" value="newZixunProperties" scope="page"></c:set>
<%@ include file="./zixunMenuHeader.jsp" %>
<form action="/zixun/saveZixun" style="margin-top:10px; border-top:#B6E6F9 solid 1px;" method="post" name="zixun" id="zixunForm">
		<input type="hidden" name="id" value="${zixun.id}"/>
	<!-- 
	<input type="hidden" name="cityId" value="${zixun.cityId}" />
	<input type="hidden" name="brandId" value="${zixun.brandId}" />
	<input type="hidden" name="styleId" value="${zixun.styleId}" />
	<input type="hidden" name="channel" value="${zixun.channel}" />
	<input type="hidden" name="listPic" value="${zixun.listPic}" />
	<input type="hidden" name="classId" value="${zixun.classId}" />
	<input type="hidden" name="title" value="${zixun.title}" />
	<input type="hidden" name="labelWord" value="${zixun.labelWord}" />
	<input type="hidden" name="keyword" value="${zixun.keyword}" />
	<input type="hidden" name="description" value="${zixun.description}" />
	<input type="hidden" name="status" value="${zixun.status}" />
	<input type="hidden" name="content" value="${zixun.content}" />
	<input type="hidden" name="publishDateStr" value="${zixun.publishDateStr}"/>
	 -->
	<input type="hidden" id="hotWordsTxt" name="hotWordsTxt" value="${zixun.hotWordsTxt}"/>
	<div class="ztsx">
		<label><strong>设置热词</strong></label><br/>
		<label style="color:red;">备注：成对填写关键词和URL</label>
	</div>
	<div style="margin-top:10px;">
		<table>
			<c:forEach items="${zixun.hotWords}" var="hotWord" varStatus="status">
				<tr>
					<td style="width:5%">${status.index + 1}、</td>
					<td style="width:30%">关键词：<input type="text" id="hotWord_${status.index}" style="width:200px;" class="hotWord" value="${hotWord.keyword}"/></td>
					<td style="width:60%">链接：<input type="text" id="hotWordUrl_${status.index}" style="width:550px" class="hotWordUrl" value="${hotWord.url}"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<p class="bnn">
		<c:if test="${newZixun}">
			<input type="hidden" value="1" name="isPreStep"/>
			<input type="button" value="上一步" onclick="preStep(${zixun.id});"/>
		</c:if>
		<input type="button" value="保存" onclick="saveZixun(${newZixun});"/>
	</p>
</form>