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
	$("[sta=p]").each(function(){
		if($(this).val()==null||$(this).val()==""){
			sta=false;
			alert($(this).attr("staName")+"不能为空！");
			return false;
			}
		}
		);
			if(sta){
			var ss=$("#tytpe").val();
			if(ss=="no"){
				alert("敏感词重复！");
				return;
			}
			$("#from1").submit();
			
		}
}
function verifyWord(){
	if($("#type").val()!=null && $("#type").val()!=""){
		if($("#key").val()!=null &&$("#key").val()!="" ){
			kevinAjax();
		}else{
			alert("敏感词不能为空！");
		}
	}else{
		 $("#tytpe").val("no");
	}

}
function kevinAjax(){
	$.ajax({
	 	 type: "GET",
 		 url: "/black/verifyWord?word="+$("#key").val()+"&type="+$("#type").val(),
 	     success: function(d){
        var succe=$.trim(d);
        if("yes"==succe){
        	$("#tytpe").val("yes");
        	$("#font1").text("可以使用").css("color","green");
        	return;
        }
        if("no"==succe){
        $("#tytpe").val("no");
        $("#font1").text("敏感词重复！").css("color","red");
        return ;
        }
    }
 		
});
}
</script>
</head>
<body>
<input type="hidden" id="tytpe">
<c:if test="${blackBean.id!=null}">
<div style="height: 30px">
	<font style="float:left"> 当前位置：黑词管理-->修改黑词</font>
</div>
<form  id="from1" action="/black/saveBlack" method="post">
	<input type="hidden" id="id" name="id" value="${blackBean.id}">
	<table class="table_style table table-bordered" >
		<tr>
			<td>敏感词</td>
			<td><input onchange="verifyWord(1)" id="key" sta="p" staName="敏感词" type="text" name="word" maxlength="20" value="${blackBean.word}"><font id="font1" color="red">*</font></td>
		</tr>
		<tr>
			<td>敏感词类型:</td>
			<td>
			<select  onchange="verifyWord()"  sta="p" name='type' staName="敏感词类型" id="type" >
					<c:if test="${configs==null || configs.size()==0 }">
						<option value="">请选择</option>
					</c:if>
					<c:if test="${configs!=null&& configs.size()>0 }">
						<c:forEach items="${configs}" var="c">
							<option <c:if test="${c.code==blackBean.type}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option>
						</c:forEach>
					</c:if>
					</select>
					<font  id="font1" color="red">*</font>
					</td>
		</tr>
			<c:if test="${blackBean.id!=null}">
			<tr>
			<td>状态</td>
			<td>
				<select name="status">
					<option value="1" <c:if test="${blackBean.status==1}">selected="selected"</c:if>>正常</option>
					<option value="-1" <c:if test="${blackBean.status<0}">selected="selected"</c:if>>删除</option>
				</select>
			</td>
		</tr>
		</c:if>
	</table>
	 <input type="button"  value="修改" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
	 </form>
	 </c:if>
	 <c:if test="${blackBean.id==null}">
	 <div style="height: 30px">
	<font style="float:left"> 当前位置：黑词管理-->黑词添加</font>
</div>
<input type="hidden" id="tytpe">
<form  id="from1" action="/black/saveBlack" method="post">
	<table class="table_style table table-bordered" >
		<tr>
			<td>敏感词</td>
			<td><input onchange="verifyWord()"  sta="p"  id="key" staName="敏感词" type="text" name="word" maxlength="20"><font  id="font1" color="red">*</font></td>
		</tr>
		<tr>
			<td>敏感词类型：</td>
			<td>
			<select  onchange="verifyWord()"  id="type"sta="p" name='type' staName="敏感词类型">
			
			<c:if test="${configs!=null &&configs.size()>0 }">
			<option  selected="selected" value="">请选择</option>
				<c:forEach items="${configs}" var="c">
							<option value="${c.code}">${ c.name}</option>
				</c:forEach>
			</c:if>
				<c:if test="${configs==null || configs.size()==0 }">
						<option value="">无</option>
					</c:if>
			</select><font  id="font1" color="red">*</font></td>
		</tr>
	</table>
	 <input type="button"  value="添加" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
	 </form>
	 </c:if>
</body>
</html>