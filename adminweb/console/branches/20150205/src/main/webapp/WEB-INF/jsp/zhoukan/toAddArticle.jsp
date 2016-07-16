<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网添加周刊文章</title>
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
<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
				<div style="display: none">
					<input id="topPicFile" name="topPicFile" type="file"  onchange="upload('topPicFile','picture','img','5')"/>
				</div>

<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/magazine/articleList">周刊文章管理</a></li>
						<li  class="tabs_active" ><a href="/magazine/toUpdateArticle">新增周刊文章</a></li>
					</ul>
			   </div>
			</td>
		</tr>
</table>
		<form action="/magazine/updateArticle" method="post" id='magazineFome'>
		<input type="hidden" name='hiddenUrl' value='${article.picture}'>
		<table style="width:100%;text-align:left;" >
		<tr><td>
		&nbsp;&nbsp;
		</td></tr>
		<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="hidden" id="id" name='id' value='${article.id}'/>
		文章title：<input type="text" id='title' name='title' value='${article.title}' maxlength="50" style="width:66%;"/><span style="color:red;">*(请不要超过50字)</span></td></tr>
		<tr><td>
		&nbsp;&nbsp;
		</td></tr>
		<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：<textarea id="content" name="content" style="width:66%; height:174px">${article.content}</textarea><span style="color:red;">*(请不要超过300字)</span></td>
		</tr>
		<tr class='add' id="id_1">
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图： <img id="img" class="uploadCss" <c:if test="${article.picture!=null}">src='${article.picture}'</c:if>
		<c:if test="${article.picture==null}">src='/images/upload.jpg'</c:if>
		 onclick="$('#topPicFile').trigger('click')"/>
		<input id='picture' type="hidden" name='picture' value='${article.picture}'/><span style="color:red;">*</span></td>
		</tr>
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;u&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;r&nbsp;&nbsp;&nbsp;
		l&nbsp;：&nbsp;&nbsp;<input type="text" id='url' name='url' value='${article.url}' maxlength="100" style="width:66%"/><span style="color:red;">*</span></td>
		</tr>
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：<input type="text" id='sort' name='sort' value='${article.sort}' maxlength="100" style="width:66%"/><span style="color:red;">*(请输入1-9999的正整数)</span></td>
		</tr>
		<tr><td>
		&nbsp;&nbsp;
		</td></tr>
		<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：
					<select name="magazineId" id='magazineId' style="width:212px;">
					<option value="">---------请选择---------</option>
					<c:forEach items="${magazines}" var="magazine" >
					<option value="${magazine.id}" <c:if test="${magazine.id==article.magazineId}">selected='selected'</c:if>>---------第${magazine.edition}期---------</option>
					</c:forEach>
				</select><span style="color:red;">*</span>
				</td>
		</tr>
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value='提交' onclick="checkSubmit()" style="width:40px;height:40px;"></td></tr>
		</table>
		</form>

</body>
<script type="text/javascript">
function checkSubmit(){
	var sort=$("#sort").val();
	var id=$("#id").val();
	var magazineId=$("#magazineId").val();
	var flag=true;
	$.ajax({
		 type: "POST",
		 url: "/magazine/check1",                       
        cache: false,
        async: false,
        dataType: "json",//返回的数据类型  
        data: {sort:sort,id:id,magazineId:magazineId},
        success: function (result){
        	if(result>0){
    			alert("请不要添加已有排序");
    			$("#sort").attr("value","");
    			flag=false;
    		}
        },
        error: function (){
      	 alert('序号重复');
        }
   });
	if(!flag){
		return false;
	}
	if($("#title").val()==null || $("#title").val()=='' ){
		alert("文章title不能为空！");
		return false;
	}
	if($("#content").val()==null || $("#content").val()=='' ){
		alert("文章内容不能为空！");
		return false;
	}
	if($("#picture").val()==null || $("#picture").val()=='' ){
		alert("请选择文章图片！");
		return false;
	}
	if($("#url").val()==null || $("#url").val()=='' ){
		alert("url不能为空！");
		return false;
	}
	if($("#sort").val()==null || $("#sort").val()=='' ){
		alert("排序不能为空！");
		return false;
	}
	if($("#magazineId").val()==null || $("#magazineId").val()=='' ){
		alert("请选择期数！");
		return false;
	}
	if($("#title").val().length>50){
		alert("文章title过长超过50字！");
		return false;
	}
	if(/^\d{1,4}$/.test(sort)==false){
		alert("请输入正确序号格式！");
		return false;
	}
	if($("#content").val().length>300){
		alert("文章内容过长超过300字！");
		return false;
	}
	$("#magazineFome").submit();
}
</script>
<script type="text/javascript">
//图片上传
function upload(fileId, picValueId, showPicId, type) {
	if (!checkImg(fileId)) {
		alert("图片格式错误！图片格式为jpg,png,gif,bmp");
		return;
	}
	var delSrc=$("#img").val();
	$.ajaxFileUpload({// type 1专题 2adv 3 cms 4.decorate
		url : "/magazine/json/uploadImg"+"?delSrc="+delSrc,
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