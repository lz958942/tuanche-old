<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map,java.util.HashMap,com.tuanche.cms.util.NameUtil,com.tuanche.cms.util.GlobalConstants" %> 
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网CMS系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>


<body>
<form id='editAdTemplate' action="/adTemplate/updateTemplate">
<input type="hidden" name="templateId" value="${adTemplate.id}" class="regname {required:true}"/>
<div id="man_zone">
<table class="table_style table table-bordered" >
<tr>
<td>名称</td>
<td><input id='templateName' name='templateName' type='text' style=width:140px  
class="regname {required:true}" value="${adTemplate.name}"/></td>
</tr>
<tr>
<td>编码</td>
<td><input id='templateCode' name='templateCode' type='text' style=width:140px  
class="regname {required:true}" value="${adTemplate.code}" /></td>
</tr>
<tr>
<td>内容</td>
<td>
<textarea  name="templateContent"  id="templateContent"  class="regname {required:true,maxlength:800}" style="float:left;height:400px;width:380px">${adTemplate.content}</textarea>
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
function cancleAdd(){
	$("#editAdTemplate .regname").each(function(){
		$(this).attr('name','e'+$(this).attr('name'));
	});
	$("#editAdTemplate").attr("action","/adTemplate/templateList");
	$("#editAdTemplate").submit();
}
function addSubmit(){
	$("#editAdTemplate").validate({
	    //信息存放位置
	    errorPlacement: function(error, element){
	       error.appendTo(element.parent());
	    },
	    //包裹信息标签
	    wrapper: "li",
	});
	$("#editAdTemplate").submit();
}
</script>

</body>

</html>