<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="flase"%>  
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/pagetag.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.checkbox input{_width:auto;_float:left;}
table{table-layout:fixed;word-wrap:break-word;}
</style>
<title>团车网CMS系统</title>
</head>
<body>
<form  id="man_zone" action="/advertising/contentList">
<div>

<div class="b-con a-form">

<div class="pd5">
<label>频道
<select  id="channel" name="channel">
<option value="-1">--请选择--</option>
<c:forEach items="${channelMap}" var="channel">
<option value="${channel.key}" <c:if test="${channel.key==adContent.channel}">selected</c:if>>${channel.value}</option>
</c:forEach>
</select>
</label>
<label>类别
<select id="adType" name="adType">
<option value="-1">--请选择--</option>
<c:forEach items="${adTypeMap}" var="type">
<option value="${type.key}" <c:if test="${type.key==adContent.adType}">selected</c:if>>${type.value}</option>
</c:forEach>							
</select>
</label>
<label>名称
<input id='locationName' name='locationName' type='text' value="${adContent.locationName}" style=width:100px />
</label>
</div>
<div  align="center">
<input type="submit" value="查询" class="btn" />
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
<input type="button" value="添加广告位" onclick="javascript:addPosition();" class="btn" />
</div>
</div>
</div>

<div>
<table class="table_style table table-bordered">
						<thead>
							<tr align="center">
								<th width="40px">ID</th>
								<th width="50px">频道</th>
								<th>组名</th>
								<th width="80px">广告类别</th>
								<th>名称</th>
								<th width="200px">描述</th>
								<th>位置编码</th>
								<th>相框尺寸(size)</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="position" varStatus="var">
								<tr align="center">
									<td>${position.id}</td>
									<td>${func:getChannelName(position.channel)}</td>
									<td>${position.groupName}</td>
									<td>${func:getAdTypeName(position.adType)}</td>
									<td>${position.locationName}</td>
									<td>${position.describe}</td>
									<td>${position.locationCode}</td>
									<td>${position.height}x${position.width}</td>
									<td>
											<a href="/advertising/editContent/${position.id}">编辑</a>
											<a href="/advertising/assignCity/${position.id}">指定城市</a>
										<!--<c:choose>
											<c:when test="${position.status==1}">
												<a href="/advertising/openContent/${position.id}/0">停用</a>
											</c:when>
											<c:otherwise>
												<a href="/advertising/openContent/${position.id}/1">开启</a>
											</c:otherwise>
										</c:choose>-->
										<a href="/advertising/deleteContentbyId/${position.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
<div class="table-page">
     <page:page pager="${pb}"/>
</div>
</div>

</form>
<script type="text/javascript" charset="UTF-8">
function clearSearch(){
	$("#channel").val(-1);
	$("#adType").val(-1);
	$("#locationName").val('');
}
function addPosition(){
	$("#man_zone").attr("action","/advertising/addContentPosition");
	$("#man_zone").submit();
}
</script>
</body>
</html>