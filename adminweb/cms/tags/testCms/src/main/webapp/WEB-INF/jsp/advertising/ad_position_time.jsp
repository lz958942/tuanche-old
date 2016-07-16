<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=""  %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/pagetag.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网CMS系统</title>
<style type="text/css">
.checkbox input{_width:auto;_float:left;}
table{table-layout:fixed;word-wrap:break-word;}
</style>
</head>
<body>
<form  method="post" action="/adPositionTime/positionTime" id="positionTime">
<div class="b-con a-form">
<div class="pd5">
<label>城市
<input style="width: 100px;" type="text" name="cityName" value="${adPositionTime.cityName }"/>
</label>
<label>频道
<select  id="channel" name="channel">
<option value="-1">--请选择--</option>
<c:forEach items="${channelMap}" var="channel">
<option value="${channel.key}" <c:if test="${channel.key==adPositionTime.channel}">selected</c:if>>${channel.value}</option>
</c:forEach>
</select>
</label>
<label>类别
<select id="adType" name="adType">
 <option value="-1">--请选择--</option>
<c:forEach items="${adTypeMap}" var="type">
<option value="${type.key}" <c:if test="${type.key==adPositionTime.adType}">selected</c:if>>${type.value}</option>
</c:forEach>
							
</select>
</label>

<label>是否默认
<select id="isDefault" name="isDefault">
 <option value="">--请选择--</option>
 <option value="1" <c:if test="${1==adPositionTime.isDefault}">selected</c:if>>默认</option>
 <option value="2" <c:if test="${2==adPositionTime.isDefault}">selected</c:if>>非默认</option>				
</select>
</label>
<label class="pr15">发布时间：
<span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly" value="${adPositionTime.startDate}" class="rqtime {required:true} inptime span2" style="width:70px" name="startDate"  id="startDate">
</label>
<label class="pr15">下架时间：
<span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly" value="${adPositionTime.endDate}" class="rqtime {required:true} inptime span2" style="width:70px" name="endDate"  id="endDate">
</label>
&nbsp;&nbsp;&nbsp;
</div>

<div  align="center">
<input type="submit" value="查询" class="btn"  id="searchbtn"/>
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
<input type="button" value="添加排期" onclick="javascript:addTimeRank();" class="btn" />
</div>
</div>
<div>
<table class="table table-bordered chargeTable">
<thead>
	<tr>
		<th>城市</th>
		<th>频道</th>
		<th>广告信息</th>
		<th>排期信息</th>
		<th>内容信息</th>
		<th width="200px">文字显示信息（描述）</th>
		<th>操作</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${list}" var="position" varStatus="var">
		<tr align="center">
			<td>${func:getCityName(position.cityId)}</td>
			<td>${func:getChannelName(position.channel)}</td>
			<td>类型：${func:getAdTypeName(position.adType)}<br>
			          链接：${position.adLink}
			</td>
			<td>发布时间：${position.startDate} <br>
			          下架时间：${position.endDate}
			</td>
			<td>内容位置编码：${position.locationCode}<br>
			内容位置名：${position.locationName}</td>
			<td style="width:30px">${position.describe}</td>
			<td>
				<c:if test="${position.channel ==1010}">
					<a href="##" onclick="changeWap(${position.cityId})">wap更新</a>
				</c:if>
				<a href="/adPositionTime/editPositionTime/${position.id}">编辑</a>
				<c:choose>
					<c:when test="${position.status==0}">
						<a href="/adPositionTime/openContent/${position.id}/1">上线</a>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${position.status==1}">
						<a href="/adPositionTime/openContent/${position.id}/0">停用</a>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${position.status==0}">
						<a  href="javascript:void(0)"  onclick="deleteById('${position.id}')">删除</a>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
</div>
<div class="table-page">
 <page:page pager="${pb}"/>
</div>
</form>

<script>
function changeWap(cityId){
	$.ajax({
		url:"/adPositionTime/deleteWapMemcache",
		type:"post",
		dataType:"json",
		data:{cityId:cityId},
		async:false,
		success:function(data){
			alert("更新成功！");
		},error:function(){
			alert("更新失败！");
		}
	});	
}
function clearSearch(){
	$("#cityId").val('-1');
	$("#channel").val('-1');
	$("#adType").val('-1');
	$("#startDate").val('');
	$("#endDate").val('');
	$("#searchbtn").click();
}
function addTimeRank(){
	$("#positionTime").attr("action","/adPositionTime/addPositionTime");
	$("#positionTime").submit();
}

function deleteById(positionId){
	if(confirm("确认删除记录吗？")){
		$("#positionTime").attr("action","/adPositionTime/deleteById/"+positionId);
		$("#positionTime").submit();
	}
}
</script>
</body>
</html>