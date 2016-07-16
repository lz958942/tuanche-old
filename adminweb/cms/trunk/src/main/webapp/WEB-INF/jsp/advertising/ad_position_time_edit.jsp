<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map,java.util.HashMap,com.tuanche.cms.util.NameUtil,com.tuanche.cms.util.GlobalConstants" %> 
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<%@include file="/WEB-INF/jsp/common/import.jsp" %>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<title>团车网CMS系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>


<body>
<form id='saveEdit' action="/adPositionTime/saveEditPositionTime">
<input type="hidden" value="${adPositionTime.id}" name="adPositionTimeId">
<div id="man_zone">
<table class="table_style table table-bordered" >
<tr>
<td>模板</td>
<td>
<select id='template' name='template' class="w130 regname" style=width:250px >
<c:forEach var="item" items="${templateList}">
<option value="${item.id}" <c:if test="${item.id==adPositionTime.templateId}">selected</c:if>>${item.name}</option>
</c:forEach>
</select>
</td>
<tr>
<td>
选择列表图：
</td>
<td>
<img border="1px" id="listImage" name="listImage" height="200px" width="200px" <c:if test='${adPositionTime.picName!=""}'>src='${imgServicePath}${adPositionTime.picName}'</c:if>  
<c:if test='${adPositionTime.picName==""}'>src='/images/upload.jpg'</c:if>  onclick="$('#listPicFile').trigger('click')"/>
<input type="hidden" id="listPic" name="listPic"  value="${adPositionTime.picName}" />
<div style="display: none">
<input id="listPicFile" name="listPicFile" type="file" onchange="upload('listPicFile','listPic','listImage','2')"/>
</div>
</td>
</tr>
 <tr>
<td>请选择城市</td>
<td><select id="cityId" name="cityId" style="width:250px"  onchange="getPostion();"> 
<option value="0" <c:if test="${func:isEqual(adPositionTime.cityId,item.key)}">selected="true"</c:if>> 全国</option>
<c:forEach items="${cityMap}" var="item">	
<option value="${item.key}" <c:if test="${func:isEqual(adPositionTime.cityId,item.key)}">selected="true"</c:if>>${item.value}</option>
</c:forEach>						
</select></td>
</tr>
<tr>
<td>位置选择</td>
<td><select id="location" name="location" style="width:250px" class="regname" > 
<c:forEach items="${adContentPositionList}" var="item">	
<option value="${item.contentPositionId}"  <c:if test="${item.contentPositionId==adPositionTime.contentPositionId}">selected</c:if>> ${item.locationName}</option>
</c:forEach>						
</select></td>
</tr>
<tr>
<tr>
<td>
品&nbsp;&nbsp;&nbsp;牌
</td>
<td>
<select class="w130" name='brandId' id="brandId"  onchange="getStyle('brandId','styleId','carId','');" style="width:250px" >
<option value="-1" >请选择</option>
<option value="0" ${adPositionTime.brandId=='0'?'selected':''}>全部品牌</option>
<c:forEach var="item" items="${brand}"> 
<option value="${item.id}" ${adPositionTime.brandId==item.id?'selected':''}>${item.name}</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td>
车&nbsp;&nbsp;&nbsp;型
</td>
<td>
 <select class="w130" name='styleId'  id="styleId" onchange="getCar('styleId','carId','')" style="width:250px" >
<option value="-1">请选择</option>
<option value="0" ${adPositionTime.styleId=='0'?'selected':''}>全部车型</option>
</select>
</td>
</tr>
<tr>
<td>广告链接</td>
<td><input id='adLink' name='adLink' type='text' style=width:250px 
 class="regname {required:true,maxlength:380}"  value="${adPositionTime.adLink}"  value="http://" />
</td>
</tr>
<tr>
<td>广告标题</td>
<td><input id='title' name='title' type='text' style=width:250px 
 class="regname {required:true,maxlength:30}" value="${adPositionTime.adTitle }"/></td>
</tr>
<tr>
<td>描述内容</td>
<td><input id='describe' name='describe' type='text' style=width:250px 
value="${adPositionTime.describe}" class="regname {required:true,maxlength:200}" />
</td>
<tr>
<td>
<label class="pr15">发布时间
<span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" 
readonly="readonly" value="${adPositionTime.startDate}" class="regname rqtime {required:true} inptime span2" style="width:150px" name="startDate"  id="startDate" />
</label>
</td>
<td>
<label class="pr15">下架时间
<span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" 
readonly="readonly" value="${adPositionTime.endDate}" class="regname rqtime {required:true} inptime span2" style="width:150px" name="endDate"  id="endDate" />
下架时间：（例）2014-01-01 -> 2014-01-01 23:59:59
</label>
</td>
</tr>
<tr>
<td>
<label class="pr15">是否是默认&nbsp;&nbsp;<input type="checkbox" ${adPositionTime.isDefault==1?'checked':''} name="isDefault"  id="isDefault"/>
</label>
</td>
<td>
</td>
</tr>
<tr>
<td>
<label class="pr15">扩展代码</label>
</td>
<td>
<textarea rows="10" cols="10" style="width: 417px; height: 105px;" name="extendCode">${adPositionTime.extendCode}</textarea>
</td>
</tr>
</table>
</div>

<div align="center">
<input type="button" class="btn" value="保存" onclick="addSubmit()" />
<input type="button"  class="btn" value="取消"  onclick="cancleAdd()"/>
</div>
</form>

<script charset="UTF-8">
var styleId=${adPositionTime.styleId};
$(document).ready(function(){
	$("#groupName1").click(function(){
		var value = $("#groupName1").val();
		if(value!='-1'){
			$("#groupName2").val('');
		}
	});
	getStyle('brandId','styleId','carId',styleId);
});
function cancleAdd(){
	$("#saveEdit .regname").each(function(){
		$(this).attr('name','e'+$(this).attr('name'));
	});
	$("#saveEdit").attr("action","/adPositionTime/positionTime");
	$("#saveEdit").submit();
}
function clearGroupName1(){
	$("#groupName1").val('-1');
}
function addSubmit(){
	$("#saveEdit").validate({
	    //信息存放位置
	    errorPlacement: function(error, element){
	       error.appendTo(element.parent());
	    },
	    //包裹信息标签
	    wrapper: "li",
	});
	if($("#startDate").val()>$("#endDate").val()){
		alert("下架时间应该大于或等于开始时间！");
		return;
	}
	$("#saveEdit").submit();
}
function getPostion(){
	var cityId=$("#cityId").val();
	var url='/advertisingPosition/getpositionbycityid';
	var str = '';
	var date = {cityId: cityId,format:"json"};
	$("#location").html('');
	$.get(url, date,function(data){
		   if(data!=null){			
				for(i in data){
					str += '<option value="'+data[i].contentPositionId+'" >'+data[i].locationName+'</option>';
					
				}	
		   }
		   $("#location").html(str);
		},
		"json"
	);
}
</script>

</body>

</html>