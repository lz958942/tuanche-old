<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map,java.util.HashMap,com.tuanche.cms.util.NameUtil,com.tuanche.cms.util.GlobalConstants" %> 
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网CMS系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>

<body>
<form id='saveEdit' action="/advertising/saveEdit/add">
<div id="man_zone">
<table class="table_style table table-bordered" >
<tr>
<td>频道</td>
<td>
<select id='channel' name='channel' class="w130 regname" style=width:150px >
<c:forEach var="item" items="${channelMap}">
<option value="${item.key}">${item.value}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>显示类型</td>
<td>
<select id='adType' name='adType' class="w130 regname" style=width:150px >
<c:forEach var="item" items="${adTypeMap}">
<option value="${item.key}">${item.value}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>位置名称</td>
<td><input id='locationName' name='locationName' type='text' style=width:140px  
class="regname {required:true,maxlength:30}" /></td>
</tr>
<tr>
<td>位置描述</td>
<td><input id='describe' name='describe' type='text' style=width:140px  
class="regname {required:true,maxlength:100}" /></td>
</tr>
<tr>
<td>位置编码</td>
<td><input id='locationCode' name='locationCode' type='text' style=width:140px 
 class="regname {required:true,maxlength:20}" /></td>
</tr>
<tr>
<td>广告位宽(px)</td>
<td><input id='width' name='width' type='text' style=width:140px 
 class="regname {required:true,digits:true,maxlength:4}" /></td>
</tr>
<tr>
<td>广告位高(px)</td>
<td><input id='height' name='height' type='text' style=width:140px 
 class="regname {required:true,digits:true,maxlength:4}" /></td>
</tr>
<tr>
<td>组名</td>
<td><select id='groupName1' name='groupName1' class="w130 regname"
style=width:100px >
 <option value="-1">---请选择---</option>
<c:forEach var="item" items="${GroupNameList}">
<option value="${item}">${item}</option>
</c:forEach>
</select>
<input id='groupName2' name='groupName2' type='text' style=width:100px  
class="regname {maxlength:15}" onkeydown="clearGroupName1();" /></td>
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
	$("#saveEdit").attr("action","/advertising/contentList");
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
	$("#saveEdit").submit();
}
</script>

</body>

</html>