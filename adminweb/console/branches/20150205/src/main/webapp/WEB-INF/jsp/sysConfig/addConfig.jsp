<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>console配置管理</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<style type="text/css">
	*{
	
font-size:12px;
}
</style>
<script type="text/javascript">
function saveConfig(){
	var sta=true;
	$("input[sta=p]").each(function(){
		if($(this).val()==null||$(this).val()==""){
			sta=false;
			alert($(this).attr("staName")+"不能为空！");
			return false;
			}
		}
		);
			if(sta){
			var ss=$("#tytpe").val();
				if("no"==ss){
					alert("code重复！");
					return;
				}
			$("#from1").submit();
			
		//window.opener.document.getElementById("form3").submit();
		}
}

function qwer(){
	if($("#code").val()!=null && $("#code").val()!=""){
		if($("#key").val()!=null &&$("#key").val()!="" ){
		$.ajax({
 	 	 type: "GET",
  		 url: "/sysConfig/verification?key="+$("#key").val()+"&code="+$("#code").val(),
  	     success: function(d){
         var succe=$.trim(d);
         if("yes"==succe){
         	$("#tytpe").val("yes");
         	$("#font1").text("可以使用").css("color","green");
         	return ;
         }
         if("no"==succe){
         $("#tytpe").val("no");
         $("#font1").text("code重复！").css("color","red");
         return ;
         }
         /* if("1"==succe){
        	 $("#tytpe").val("1");
             $("#font1").text("请填写key").css("color","red");
         } */
     }
  		
});
		}else{
			alert("请输入key值！");
			  $("#tytpe").val("no");
		}
	}

}

</script>
</head>
<body>
<input type="hidden" id="tytpe">
<c:if test="${config.id!=null}">
<div style="height: 30px">
	<font style="float:left"> 当前位置：配置管理-->修改配置</font>
</div>
<form  id="from1" action="/sysConfig/saveConfig" method="post">
	<input   type="hidden" id="type" name="2">
	<input type="hidden" id="id" name="id" value="${config.id}">
	<table class="table_style table table-bordered" >
		<tr>
			<td>键值</td>
			<td><input sta="p"  id="key" staName="键值" type="text" name="key" maxlength="150" value="${config.key}"><font color="red">*</font></td>
		</tr>
		<tr>
			<td>编码</td>
			<td><input sta="p"  id="code"staName="编码" type="text" name="code" maxlength="50" value="${config.code}" onchange="qwer()"><font  id="font1" color="red">*</font></td>
		</tr>
		<tr>
			<td>编码名称</td>
			<td><input sta="p" staName="编码名称" type="text" name="name" maxlength="150"  value="${config.name}"><font color="red">*</font></td>
		</tr>
		<tr>
			<td>描述</td>
			<td><input sta="p" staName="描述" type="text" maxlength="300"name="desc" value="${config.desc}"><font color="red">*</font></td>
		</tr>
			<c:if test="${config.id!=null}">
			<tr>
			<td>状态</td>
			<td>
				<select name="status">
					<option value="1" <c:if test="${config.status==1}">selected="selected"</c:if>>正常</option>
					<option value="-1" <c:if test="${config.status<0}">selected="selected"</c:if>>删除</option>
				</select>
			</td>
		</tr>
		</c:if>
	</table>
	 <input type="button"  value="修改" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
	 </form>
	 </c:if>
	 <c:if test="${config.id==null}">
	 <div style="height: 30px">
	<font style="float:left"> 当前位置：配置管理-->新增配置</font>
</div>
<form  id="from1" action="/sysConfig/saveConfig" method="post">
	<table class="table_style table table-bordered" >
		<tr>
			<td>键值</td>
			<td><input  sta="p"  id="key" staName="键值" type="text" name="key" maxlength="150"><font color="red">*</font></td>
		</tr>
		<tr>
			<td>编码</td>
			<td><input sta="p"  id="code"staName="编码" type="text" name="code" maxlength="50" onchange="qwer()" ><font  id="font1" color="red">*</font></td>
		</tr>
		<tr>
			<td>编码名称</td>
			<td><input sta="p" staName="编码名称" type="text" name="name" maxlength="150"  ><font color="red">*</font></td>
		</tr>
		<tr>
			<td>描述</td>
			<td><input sta="p" staName="描述" type="text" maxlength="300"name="desc"><font color="red">*</font></td>
		</tr>
	</table>
	 <input type="button"  value="添加" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
	 </form>
	 </c:if>
</body>
</html>