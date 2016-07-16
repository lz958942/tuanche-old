<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=""  %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/pagetag.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网CMS系统</title>
</head>
<body>
<form  id="man_zone" action="/adStatistic/adStatisticList">
<div>
<div class="b-con a-form">
<div class="pd5">
<label class="pr15">日期
<span class="add-on"><i class="icon-calendar"></i></span>
<input type="text" autocomplete="off" readonly="readonly"  class="rqtime {required:true} inptime span2"  name="date"  id="date" style="width:80px" value="${adStatistic.date}">
</label>
<label>城市
<select  id="city" name="city">
<option value="-1">--请选择--</option>
<c:forEach items="${cityMap}" var="city">
<option value="${city.key}" <c:if test="${city.key==adStatistic.cityId}">selected</c:if>>${city.value}</option>
</c:forEach>
</select>
</label>
<label>频道
<select  id="channel" name="channel">
<option value="-1">--请选择--</option>
<c:forEach items="${channelMap}" var="channel">
<option value="${channel.key}" <c:if test="${channel.key==adStatistic.channel}">selected</c:if>>${channel.value}</option>
</c:forEach>
</select>
</label>
<label>广告类型
<select id="adType" name="adType">
<option value="-1">--请选择--</option>
<c:forEach items="${adTypeMap}" var="type">
<option value="${type.key}" <c:if test="${type.key==adStatistic.adType}">selected</c:if>>${type.value}</option>
</c:forEach>							
</select>
</label>
&nbsp;&nbsp;&nbsp;
</div>
<div  align="center">
<input type="submit" value="查询" class="btn" />
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
</div>
</div>
</div>
<div>
<table class="table_style table table-bordered">
						<thead>
							<tr align="center">
								<th>日期</th>
								<th>城市</th>
								<th>频道</th>
								<th>位置ID</th>
								<th>位置名</th>
								<th>展现量</th>
								<th>点击量</th>
								<th>唯一点击量</th>
								<th>广告ID</th>
								<th>广告类型</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="position" varStatus="var">
								<tr align="center">
									<td>${position.date}</td>
									<td>${func:getCityName(position.cityId)}</td>
									<td>${func:getChannelName(position.channel)}</td>
									<td>${position.positionId}</td>
									<td>${position.positionName}</td>
									<td>${position.views}</td>
									<td>${position.clicks}</td>
									<td>${position.uniqClicks}</td>
									<td>${position.adId}</td>
									<td>${func:getAdTypeName(position.adType)}</td>
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
	$("#city").val('');
	$("#date").val('');
}
</script>
</body>
</html>