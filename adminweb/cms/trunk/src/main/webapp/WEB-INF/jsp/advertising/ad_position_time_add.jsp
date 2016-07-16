<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map,java.util.HashMap,com.tuanche.cms.util.NameUtil,com.tuanche.cms.util.GlobalConstants" %> 
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>

<title>团车网CMS系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>


<body>
<form id='saveEdit' action="/adPositionTime/saveAddPositionTime">
<div id="man_zone">
<table class="table_style table table-bordered" >
<tr>
<td>模板</td>
<td>
<select id='template' name='template' class="w130 regname" style=width:250px >
<c:forEach var="item" items="${templateList}">
<option value="${item.id}">${item.name}</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td>
选择列表图：
</td>
<td>
<img border="1px" id="listImage" height="200px" width="200px" src='/images/upload.jpg'   onclick="$('#listPicFile').trigger('click')"/>
<input type="hidden" id="listPic" name="listPic"  value="${zixun.listPic}" />
<div style="display: none">
<input id="listPicFile" name="listPicFile" type="file"  onchange="upload('listPicFile','listPic','listImage','2')"/>
</div>
</td>
</tr>

<!-- 
<tr>
<td>
<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
<div style="display: none">
<input id="listPicFile" name="listPicFile" type="file"  onchange="upload('listPicFile','listPic','listImage','2')"/>
</div>
</td>
<td>&nbsp;</td>
<td>
<span id="errMsg" style="color:red;"></span>
</td>
</tr>
 -->
 <tr>
<td>请选择城市</td>
<td><select id="cityId" name="cityId" style="width:250px"  onchange="getPostion();"> 
<option value="0" > 全国</option>
<c:forEach items="${cityMap}" var="item">	
<option value="${item.key}" > ${item.value}</option>
</c:forEach>						
</select></td>
</tr>
<tr>
<td>位置选择</td>
<td><select id="location" name="location" style="width:250px"> 
<c:forEach items="${adContentPositionList}" var="item">	
<option value="${item.contentPositionId}" > ${func:getCityName(item.cityId)}----${item.locationName}</option>
</c:forEach>						
</select></td>
</tr>

<tr>
<td>
品&nbsp;&nbsp;&nbsp;牌
</td>
<td>
<select class="w130" name='brandId' id="brandId"  onchange="getStyle('brandId','styleId','carId','');" style="width:250px">
<option value="-1" >请选择</option>
<option value="0" >全部品牌</option>
<c:forEach var="item" items="${brand}"> 
<option value="${item.id}">${item.name}</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td>
车&nbsp;&nbsp;&nbsp;型
</td>
<td>
 <select class="w130" name='styleId'  id="styleId" onchange="getCar('styleId','carId','')" style="width:250px">
<option value="-1">请选择</option>
<option value="0" >全部车型</option>
</select>
</td>
</tr>

<tr>
<td>广告链接</td>
<td><input id='adLink' name='adLink' type='text' style=width:250px 
  class="{required:true,maxlength:380} regname"  value="http://"/></td>
</tr>
<tr>
<td>广告标题</td>
<td><input id='title' name='title' type='text' style=width:250px 
 class="regname {required:true,maxlength:20}" /></td>
</tr>
<tr>
<td>描述内容</td>
<td><input id='describe' name='describe' type='text' style=width:250px 
 class="regname {required:true,maxlength:200}" />
</td>
<tr>
<td>
<label class="pr15">发布时间：
<span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly"  class="rqtime {required:true} inptime span2"  name="startDate"  id="startDate">
</label>
</td>
<td>
<label class="pr15">下架时间：
<span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly"  class="rqtime {required:true} inptime span2"  name="endDate"  id="endDate">
下架时间：（例）2014-01-01 -> 2014-01-01 23:59:59
</label>
</td>
</tr>
<tr>
<td>
<label class="pr15">是否是默认&nbsp;&nbsp;<input type="checkbox"  name="isDefault" id="isDefault"/>
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
<textarea rows="10" cols="10" style="width: 417px; height: 105px;" name="extendCode"></textarea>
</td>
</tr>
</table>
</div>

<div align="center">
<input type="button" class="btn" value="保存" onclick="addSubmit()" />
<input type="button"  class="btn" value="取消"  onclick="cancleAdd()"/>
</div>
</form>
<script>

$(document).ready(function(){
	$("#groupName1").click(function(){
		var value = $("#groupName1").val();
		if(value!='-1'){
			$("#groupName2").val('');
		}
	});
	
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
	var cityId = $("#cityId").val();
	var location = $("#location").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var isDefault = $("#isDefault").attr("checked");
/* 	$.ajax({
	   type: "POST",
	   url: "/adPositionTime/checkDouble",
	   dataType:'json',
	   data: {
		   'cityId':cityId,"location":location,"startDate":startDate,"endDate":endDate,"isDefault":isDefault
	   },
	   success: function(data){
		 if(data == null){
			 $("#saveEdit").submit();
		 }else{
			 alert("排期时间冲突！");
		 }
	   } 
	}); */
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