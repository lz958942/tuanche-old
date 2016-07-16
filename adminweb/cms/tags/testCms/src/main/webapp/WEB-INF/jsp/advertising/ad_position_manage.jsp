<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=""  %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/pagetag.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网CMS系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>
<body>
<form  method="post" action="/advertisingPosition/adContentPosition">
<div class="b-con a-form">
<div class="pd5">
<label>城市
<input type="text" name="cityName" value="${adContentPosition.cityName }"/>
</label>
<label>频道
<select  id="channel" name="channel">
<option value="-1">--请选择--</option>
<c:forEach items="${channelMap}" var="channel">
<option value="${channel.key}" <c:if test="${channel.key==adContentPosition.channel}">selected</c:if>>${channel.value}</option>
</c:forEach>
</select>
</label>
<label>类别
<select id="adType" name="adType">
 <option value="-1">--请选择--</option>
<c:forEach items="${adTypeMap}" var="type">
<option value="${type.key}" <c:if test="${type.key==adContentPosition.adType}">selected</c:if>>${type.value}</option>
</c:forEach>
							
</select>
</label>
<label>名称
<input id='locationName' name='locationName' type='text' value="${adContentPosition.locationName}" style=width:100px />
</label>
<div  align="center">
<input type="submit" value="查询" class="btn" />
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
</div>
</div>

<div>
<table class="table_style table table-bordered">
						<thead>
							<tr align="center">
								<th>ID</th>
								<th>内容ID</th>
								<th>组名</th>
								<th>城市</th>
								<th>频道</th>
								<th>广告类别</th>
								<th>名称</th>
								<th>描述</th>
								<th>位置代码</th>
								<th>相框尺寸</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="position" varStatus="var">
								<tr align="center">
									<td>${position.contentPositionId}</td>
									<td>${position.adContentId}</td>
									<td>${position.groupName}</td>
									<td>${func:getCityName(position.cityId)}</td>
									<td>${func:getChannelName(position.channel)}</td>
									<td>${func:getAdTypeName(position.adType)}</td>
									<td>${position.locationName}</td>
									<td>${position.describe}</td>
									<td>${position.locationCode}</td>
									<td>${position.height}x${position.width}</td>
									<td>
									<!-- 
									<c:choose>
										<c:when test="${position.contentPositiontatus==1}">
											<a href="/advertisingPosition/openContentPosition/${position.contentPositionId}/0" >停用</a>
										</c:when>
										<c:otherwise>
											<a href="/advertisingPosition/openContentPosition/${position.contentPositionId}/1" >开启</a>
										</c:otherwise>
									</c:choose>
									 -->
									<a href="/advertisingPosition/deleteContentPositionById/${position.contentPositionId}" >删除</a>	
									</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
<div class="table-page">
 <page:page pager="${pb}"/>
</div>
</div>

</div>
</form>

<script>
function clearSearch(){
	$("#cityId").val('-1');
	$("#channel").val('-1');
	$("#adType").val('-1');
	$("#locationName").val('');
}
</script>
</body>
</html>