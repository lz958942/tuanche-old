<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.tuanche.console.web.AuthUtil"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网添加问答话题</title>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery.tipsy.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>

 <!-- ztree ue-->
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<style type="text/css">
.uploadCss{
	width:200px;
	height:100px;
}
</style>
</head>
<body>
<% boolean uploadImg=AuthUtil.checkAuth(request,"/questionAnswer/json/uploadImg"); %>
<% boolean update=AuthUtil.checkAuth(request,"/questionAnswer/updateTopic"); %>
<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
				<div style="display: none">
					<input id="topPicFile" name="topPicFile" type="file"  onchange="upload('topPicFile','picture','img','5')"/>
				</div>

<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/topicList">问答话题管理</a></li>
						<li  class="tabs_active" ><a href="/questionAnswer/toUpdateTopic">新增问答话题</a></li>
					</ul>
			   </div>
			</td>
		</tr>
</table>
		<form action="/questionAnswer/updateTopic" method="post" id='magazineFome'>
		<input type="hidden" id='styleId' value='${topic.carstyleId}'/>
		<table style="width:100%;text-align:left;" >
		<tr><td>
		&nbsp;&nbsp;
		</td></tr>
		<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="hidden" id="id" name='id' value='${topic.id}'/>
		话题title：<input type="text" id='title' name='title' value='${topic.title}' maxlength="50" style="width:66%;"/><span style="color:red;">*(请不要超过50字)</span></td></tr>
		<tr><td>
		&nbsp;&nbsp;
		</td></tr>
		<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：<textarea id="content" name="content" style="width:66%; height:174px">${topic.content}</textarea><span style="color:red;">*(请不要超过300字)</span></td>
		</tr>
		<tr class='add' id="id_1">
		<td><%if(uploadImg){%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图： <img id="img" class="uploadCss" <c:if test="${topic.picture!=null}">src='${topic.picture}'</c:if>
		<c:if test="${topic.picture==null}">src='/images/upload.jpg'</c:if>
		 onclick="$('#topPicFile').trigger('click')"/>
		 <%}%>
		<input id='picture' type="hidden" name='picture' value='${topic.picture}'/><span style="color:red;">*</span></td>
		</tr>
		<tr><td>
		&nbsp;&nbsp;
		</td></tr>
		<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：
				<select name="brandId" onchange="getStylemy(this.options[this.options.selectedIndex].value)" id="brandId">
					<option value="">请选择</option>
					<c:forEach var="brand" items="${brands}">
					<option value="${brand.id}"<c:if test="${brand.id==topic.brandId}">selected='selected'</c:if> >${brand.typepinyI}-${brand.name}</option>
					</c:forEach>
				</select></td>
		</td>
		</tr>
		
		<tr><td>
		&nbsp;&nbsp;
		</td></tr>
		<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：
			<select  name='carstyleId' id='muio'>
				<option value="">请选择</option>
			</select>
				</td>
		</tr>
		<%if(update){%>
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value='提交' onclick="checkSubmit()" style="width:40px;height:40px;"></td></tr>
		<%}%>
		</table>
		</form>

</body>
<script type="text/javascript">
function checkSubmit(){
	if($("#title").val()==null || $("#title").val()=='' ){
		alert("话题title不能为空！");
		return false;
	}
	if($("#content").val()==null || $("#content").val()=='' ){
		alert("话题内容不能为空！");
		return false;
	}
	if($("#picture").val()==null || $("#picture").val()=='' ){
		alert("请选择话题图片！");
		return false;
	}
/* 	if($("#brandId").val()==null || $("#brandId").val()=='' ){
		alert("请选择品牌！");
		return false;
	}
	if($("#muio").val()==null || $("#muio").val()=='' ){
		alert("请选择车型！");
		return false;
	} */
	if($("#title").val().length>50){
		alert("话题title过长超过50字！");
		return false;
	}
	if($("#content").val().length>300){
		alert("话题内容过长超过300字！");
		return false;
	}
	$("#magazineFome").submit();
}
</script>
<script type="text/javascript">
$(document).ready(function(){ 
	var pid=$("#brandId").val();
	if(pid!=null && ""!=pid){
		 $("#muio option").remove();
		 $.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#muio").val("");
				 $("#muio").append("<option value=''>请选择</option>");
				   for(i in data) {
				   		if(data[i].id==$("#styleId").val()){
				   			$("#muio").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	　　}); 
function getStylemy(pid){
	 $("#muio option").remove();
	 if($("#brandId").val()!=null&&""!=$("#brandId").val()){
	 $.ajax({
		   type: "POST",
		   url: "/json/carstyle/ajaxStyle",
		   dataType:'json',
		   data: {
			   'brandID':pid
		   },
		   success: function(data){
			 $("#muio").val("");
			 $("#muio").append("<option value=''>请选择</option>");
			   for(i in data) {
				   /* alert(data[i].style+"名");
			   		alert(data[i].id); */
			   		$("#muio").append("<option value="+data[i].id+">"+data[i].style+"</option>");
			   }
				  
			   } 
		
		});
	 }
}
</script>
<script type="text/javascript">
//图片上传
function upload(fileId, picValueId, showPicId, type) {
	if (!checkImg(fileId)) {
		alert("图片格式错误！图片格式为jpg,png,gif,bmp");
		return;
	}
	$.ajaxFileUpload({// type 1专题 2adv 3 cms 4.decorate
		url : "/questionAnswer/json/uploadImg",
		secureuri : false,
		async : true,
		fileElementId : fileId,
		dataType : 'json',
		success : function(data) {
			alert("上传成功");
			if (isNaN(data)) {// 成功
				$("#" + picValueId).val(data.trim());
				$("#" + showPicId).attr("src",
						data.trim() + "?" + Math.random());
				$("#" + showPicId).attr("class", "uploadCss");
				setTimeout(function() {
					$("#" + showPicId).attr("src",
							data.trim() + "?" + Math.random());
				}, 1000)

			} else {
				alert("上传失败");
			}
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
}
//检查文件格式
function checkImg(fileId) {
	// $("#imgMsg").text('');//清空错误信息
	var fileName = $("#" + fileId).val(), // 文件名称
	fileType = [ "jpg", "png", "gif", "bmp" ], // 图片类型
	fileExt = ""; // 图片拓展名
	fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	for ( var i in fileType) {
		if (fileExt == fileType[i]) {
			return true;
		}
	}
	return false;
}
</script>
</html>