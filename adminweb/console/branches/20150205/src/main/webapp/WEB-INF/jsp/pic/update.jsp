<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团修改图片</title>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript">
$(document).ready(function() {
	var pid=$("#brind").val();
	var cid=$("#carStyleId").val();
	var style=$("#carid").val();
	if(pid!=null && ""!=pid){
		 $.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#muio option ").remove();
				 $("#muio").append("<option  selected='selected'  value=''>请选择车型</option>");
				   for(i in data) {
				   		if(data[i].id==$("#carid").val()){
				   			$("#muio").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	if(style!=null && style!=""){
		 $.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxCarStyle",
			   dataType:'json',
			   data: {
				   'ppid':style
			   },
			   success: function(data){
				 $("#carStyles option ").remove();
				 $("#carStyles").append("<option  selected='selected'  value=''>请选择车款</option>");
				   for(i in data) {
				   		if(data[i].id==cid){
				   			$("#carStyles").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#carStyles").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			});
	}
	});
</script>
</head>
<body>
<font color="red">页面所有元素为必选！</font>
<input type="hidden" id="brind" value="${bean.bid }">
<input type="hidden" id="carid" value="${bean.cid }">
<input type="hidden" id="carStyleId" value="${bean.carId }">
	<form action="/pic/operation.do" method="post" id='questPic'>
	<input name="type" type="hidden" value="3">
	<c:if test="${bean!=null && bean.id!=null}">
	<input name="colourId" type="hidden" value="${bean.colourId }">
	<input name="id" type="hidden" value="${bean.id }">
	</c:if>
	<div id="div1">
		<table>
			<tr>
				<td>品牌：</td>
			<td>
					<select sta="p" staName="品牌" id="brind" name="bid" style="width:220px;height: 35px " onchange="getStylemy(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option  <c:if test="${bean.bid ==b.id}">selected="select"</c:if> value="${b.id }">${b.reviewInitial}${b.name}</option>
						</c:forEach>
					</select>
			</td>
			<td>车型:</td>
			<td>
			<select  sta="p" staName="车型" name="cid" id="muio" style="width:220px;height: 35px" onchange="getCarStyles(this.options[this.options.selectedIndex].value)">
					<option value="">请选择车型</option>
			</select>
			</td>
			<td>车款:</td>
			<td>
			<select sta="p" staName="车款" name="carId" id="carStyles" style="width:220px;height: 35px ">
					<option value="">请选择车款</option>
			</select>
			</td>
			</tr>
			<tr>
				<td>颜色：</td>
				<td><input sta="p" staName="颜色"  type="text" name="colourName" maxlength="10" value="${bean.colourName }"></td>
				<td>标题：</td>
				<td><input sta="p" staName="标题" type="text" name="picTitle" maxlength="10" value="${bean.picTitle }"></td>
				<td>分类：</td>
			<td>
				<select  sta="p" staName="分类" name="classid" style="width:220px;height: 35px ">
				<option value="">请选择</option>
					<c:forEach items="${classIds}" var="c">
							<option <c:if test="${c.code==bean.classid}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option>
						</c:forEach>
				</select>
			</td>
			</tr>
			<tr>
			<td>属性：</td>
			<td>
				<select sta="p" staName="属性" name="propertyid" style="width:220px;height: 35px ">
				<option value="">请选择</option>
					<c:forEach items="${propertyIds}" var="c">
							<option <c:if test="${c.code==bean.propertyid}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option>
						</c:forEach>
				</select>
			</td>
			</tr>
		</table>
		简介：</br>
		<textarea  sta="p" staName="简介" name="desc" style="width: 451px; height: 158px;"  maxlength="300" rows="" cols="">${bean.desc }</textarea>
	</div>
	<input type="button" value="修改" onclick="submint1(1)">
</form>
</body>
<script type="text/javascript">
function getStylemy(pid){
	if(pid!=null &&　pid	!=""){
	 $("#muio option").remove();
	 $("#muio").append("<option selected='selected' value=''>请选择车型</option>");
	 publicAjax("/json/carstyle/ajaxStyle?","brandID",pid,'muio');
	}else{
		 $("#muio option").remove();
		 $("#muio").append("<option selected='selected' value=''>请选择车型</option>");
		 $("#carStyles option").remove();
		 $("#carStyles").append("<option selected='selected' value=''>请选择车款</option>");
	}
}
	function getCarStyles(id){
		if(id==null || id==""){
			return;
		}
		 $("#carStyles option").remove();
		 $("#carStyles").append("<option selected='selected' value=''>请选择车款</option>");
		publicAjax("/json/carstyle/ajaxCarStyle?","ppid",id,"carStyles");
		}
function publicAjax(url,dataName,dataArgs,selectId){
	$.ajax({
		   type: "POST",
		   url: url+$.trim(dataName)+"="+dataArgs,
		   dataType:'json',
		   success: function(data){
			   for(i in data) {
			   		$("#"+selectId).append("<option value="+data[i].id+">"+data[i].style+"</option>");
			   }
			   } 
		
		});
}
function submint1(id){
	 var sta=true;
	 var type=1;
		$("[sta=p]").each(function(){
			if($(this).val()==null||$(this).val()==""){
				sta=false;
				alert($(this).attr("staName")+"不能为空！");
				return false;
				}
			});
		$("[sta=cover]").each(function(){
			if($(this).val()=="√"){
				type=1;
				}
			});
		if(type==1){
				if(sta){
					$("#questPic").submit();	
			}
		}
} 
</script>
</html>