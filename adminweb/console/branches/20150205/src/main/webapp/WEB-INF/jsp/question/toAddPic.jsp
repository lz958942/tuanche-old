<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网专题管理</title>
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
					<input id="topPicFile" name="topPicFile" type="file"  onchange="upload('topPicFile','picUrl','img','5')"/>
				</div>

<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toPicList">图片管理</a></li>
						<li  class="tabs_active" ><a href="/questionAnswer/toUpdatePic">新增图片</a></li>
					</ul>
			   </div>
			</td>
		</tr>
</table>
		<form action="/questionAnswer/updatePic" method="post">
		<table style='text-align:left;'>
		<tr>
		<td>
		<input type="hidden" id="id" name='id' value='${pic.id}'/>
		图片标题：<input type="text" id='title' name='title' value='${pic.title}' maxlength="500" /></td></tr>
		<tr class='add' id="id_1">
		<td>图&nbsp;&nbsp;&nbsp;&nbsp;片： <img id="img" class="uploadCss" <c:if test="${pic.picUrl!=null}">src='${pic.picUrl}'</c:if>
		<c:if test="${pic.picUrl==null}">src='/images/upload.jpg'</c:if> 
		
		onclick="$('#topPicFile').trigger('click')"/>
<input id='picUrl' type="hidden" name='picUrl' value='${pic.picUrl}'/></td>
		</tr>
		<tr>
		<td>图片链接: <input type="text" class='hrefUrl' name='hrefUrl' value='${pic.hrefUrl}' maxlength="500"></td>
		</tr>
		<tr>
		<td>排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序: <input type="text" class='sort' name='sort' value='${pic.sort}' maxlength="2"><span style="color:red;">*(请输入1-99的正整数)</span></td>
		</tr>
		<tr><td><input type="submit" value='提交'></td></tr>
		</table>
		</form>

</body>
<script type="text/javascript">
//图片上传
function upload(fileId, picValueId, showPicId, type) {
	if (!checkImg(fileId)) {
		alert("图片格式错误！图片格式为jpg,png,gif,bmp");
		return;
	}
	$.ajaxFileUpload({// type 1专题 2adv 3 cms 4.decorate
		url : "/questionAnswer/uploadPic?type=" + type + '&file=' + fileId,
		secureuri : false,
		async : true,
		fileElementId : fileId,
		dataType : 'jsonp',
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