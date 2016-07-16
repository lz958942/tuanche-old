<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网添加周刊人员</title>
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
					<input id="topPicFile" name="topPicFile" type="file"  onchange="upload('topPicFile','employePic','img','5')"/>
				</div>

<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/magazine/magazineList">周刊管理</a></li>
						<li  class="tabs_active" ><a href="/magazine/toUpdateMagazine">新增周刊</a></li>
					</ul>
			   </div>
			</td>
		</tr>
</table>
		<form action="/magazine/updateMagazine" method="post" id='magazineFome'>
		<table style="width:100%;text-align:left;" >
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
		<tr>
		<td>
		<input type="hidden" id="id" name='id' value='${magazine.id}'/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周刊期数：<input type="text" id='edition' name='edition' value='${magazine.edition}' maxlength="4"style="width:66%;"/><span style="color:red;">*(请输入正整数0~9999)</span></td></tr>
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
		<tr class='add' id="id_1">
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周刊title：<textarea name='title' id='title' style="width:66%; height:174px" >${magazine.title}</textarea><span style="color:red;">*</span></td>
		</tr>
		
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value='提交' onclick="checkSubmit()" style="width:40px;height:40px;"></td></tr>
		</table>
		</form>

</body>
<script type="text/javascript">
function checkSubmit(){
	var aa=$("#edition").val();
	var edit=$("#edition").val();
	var id=$("#id").val();
	var flag=true;
	$.ajax({
		 type: "POST",
		 url: "/magazine/check",                       
        cache: false,
        async: false,
        dataType: "json",//返回的数据类型  
        data: {edit:edit,id:id},
        success: function (result){
        	if(result>0){
    			alert("请不要添加已有周刊期数");
    			$("#edition").attr("value","");
    			flag=false;
    		}
        },
        error: function (){
      	 alert('周刊期数重复');
        }
   });

	/* $.post("/magazine/check",{edit:edit},function(result){
		if(result>0){
			alert("请不要添加已有周刊期数");
			$("#edition").attr("value","");
			$("#edit1").attr("value","true");
			flag=false;
		}
	},'json'); */

	if(!flag){
		return false;
	}
	if($("#edition").val()==null || $("#edition").val()=='' ){
		alert("周刊期数不能为空！");
		return false;
	}
	if($("#title").val()==null || $("#title").val()=='' ){
		alert("周刊title不能为空！");
		return false;
	}
	if(/^\d{1,4}$/.test(aa)==false){
		alert("请输入正确期数格式！");
		return false;
	}
	if($("#title").val().length>300){
		alert("周刊title过长超过300字！");
		return false;
	}
	$("#magazineFome").submit();
}
</script>
</html>