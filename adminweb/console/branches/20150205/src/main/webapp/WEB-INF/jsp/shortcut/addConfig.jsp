<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>console点评快捷回复管理</title>
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
	$("[sta=p]").each(function(){
		if($(this).val()==null||$(this).val()==""){
			sta=false;
			alert($(this).attr("staName")+"不能为空！");
			return false;
			}
		}
		);
	if(sta){
		$("#from1").submit();
	}
}
function divshow(id){
	var $divs=$("div[sta=showdiv]").hide();
	$("#oladiv").show();
}
function nonediv(){
	$("#oladiv").hide();
}
</script>
</head>
<body>
<input type="hidden" id="tytpe">
<c:if test="${config.id!=null}">
<div style="height: 30px">
	<font style="float:left"> 当前位置：配置管理-->修改快捷回复</font>
</div>
<form  id="from1" action="/sysConfig/saveConfig.do" method="post">
	<input   type="hidden" id="type" name="2">
	<input type="hidden" id="id" name="id" value="${config.id}">
	<input type="hidden" id="key" name="key" value="${config.key}">
	<input type="hidden" id="code" name="code" value="${config.code}">
	<table class="table_style table table-bordered" >
		<tr>
			<td>编码名称</td>
			<td><input sta="p" staName="编码名称" type="text" name="name" maxlength="150"  value="${config.name}"><font color="red">*</font></td>
		</tr>
		<tr>
			<td>快捷回复</td>
			<td>
			<textarea  name="desc" style="width: 350px" sta="p" staName="快捷内容" rows="3" cols="8" maxlength="150">${config.desc }</textarea><font color="red">*</font>
			</td>
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
	<font style="float:left"> 当前位置：快捷回复管理-->添加快捷回复</font>
</div>
<form  id="from1" action="/sysConfig/saveConfig.do" method="post">
	<table class="table_style table table-bordered" >
		<tr>
			<td>描述</td>
			<td><input sta="p" staName="描述" type="text" maxlength="300"name="name"><font color="red">*</font> <a onclick="divshow()">说明</a></td>
		</tr>
		<div  sta="showdiv" id="oladiv" style="display:none;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
									 <font color="green">
									 描述与首页编码名称一致填写格式：添加人名字+内容以便首页搜索 不要使用_,&,$,#,@，！,*,^等特殊符号</br>
									 例：张三+快捷回复</br>
									  </font>
									 <div style="text-align:right;">	
									<input type="button" value="关闭" onclick="nonediv(${list.id})">
									</div>
									</div>
		<tr>
			<td>快捷内容</td>
			<td>
			<textarea  sta="p" staName="快捷回复" name="desc" style="width: 350px"  rows="3" cols="8" maxlength="150"></textarea><font color="red">*</font>
			</td>
		</tr>
	</table>
	 <input type="button"  value="添加" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
	 </form>
	 </c:if>
</body>
</html>