<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网console系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>

<body>
<input type="hidden" id="lgv" value="2">
<form id='saveUpdate' action="/advertising/saveEdit">
<input type="hidden" name="type" value="update">
<input type="hidden" value="${adContent.id}" name="adContentId">
<div id="man_zone">
<table class="table_style table table-bordered" >
<tr>
<td>频道</td>
<td>
<select id='channel' name='channel' class="w130 regname" style=width:160px >
<c:forEach var="item" items="${channelMap}">
<option value="${item.key}" <c:if test="${item.key==adContent.channel}">selected</c:if>>${item.value.name}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>显示类型</td>
<td>
<select id='adType' name='adType' class="w130 regname" style=width:160px >
<option  value="-1">--请选择--</option>
<option  <c:if test="${adContent.adType==1 }">selected='selected'</c:if> value="1">文字链接</option>
<option <c:if test="${adContent.adType==2 }">selected='selected'</c:if> value="2">图片文字</option>
</select>
</td>
</tr>
<tr>
<td>位置名称</td>
<td><input id='locationName' name='locationName' type='text' style=width:150px  
class="regname {required:true,maxlength:30}" value="${adContent.locationName}" /></td>
</tr>
<tr>
<td>位置描述</td>
<td><input id='describe' name='describe' type='text' style=width:150px  
class="regname {required:true,maxlength:100}" value="${adContent.describe}" /></td>
</tr>
<tr>
<td>位置编码</td>
<td><input id='locationCode' name='locationCode' type='text' style=width:150px 
 class="regname {required:true,maxlength:20}" value="${adContent.locationCode}"  onchange="validateLC(this.value)"/></td>
</tr>
<tr>
<td>广告位宽</td>
<td><input id='width' name='width' type='text' style=width:150px 
 class="regname {required:true,digits:true,maxlength:4}"  value="${adContent.width}"/></td>
</tr>
<tr>
<td>广告位高</td>
<td><input id='height' name='height' type='text' style=width:150px 
 class="regname {required:true,digits:true,maxlength:4}"  value="${adContent.height}"/></td>
</tr>
<tr>
<td>组名</td>
<td><select id='groupName1' name='groupName1' class="w130 regname"
style=width:200px >
 <option value="-1">---请选择---</option>
<c:forEach var="item" items="${groupList}">
<option value="${item}" <c:if test="${item==adContent.groupName}">selected</c:if>>${item}</option>
</c:forEach>
</select>
<input id='groupName2' name='groupName2' type='text' style=width:100px  
class="regname {maxlength:50}" onkeydown="clearGroupName1();" /></td>
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
	$("#saveUpdate .regname").each(function(){
		$(this).attr('name','e'+$(this).attr('name'));
	});
	$("#saveUpdate").attr("action","/advertising/contentList");
	$("#saveUpdate").submit();
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
	if($("#lgv").val()==null ||$("#lgv").val()=="2" ){
	$("#saveUpdate").submit();
	}else{
		alert("位置编码重复！");
	}
}

function validateLC(locationCode){
	if(locationCode!=null && locationCode!=""){
$.post("/json/validateLC",{name:locationCode},function(result){
	var sta=result;
	if(!sta){
		$("#lgv").val("1");
		$("#valText").text("位置编码重复！");
	}else{
		$("#valText").text("");
		$("#lgv").val("2");
	}
		},'json');
	}
	//var locationCode=$("#locationCode").val()
}
</script>

</body>

</html>