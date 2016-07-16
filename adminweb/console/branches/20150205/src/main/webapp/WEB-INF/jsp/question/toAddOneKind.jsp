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
</head>
<body>

<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/oneKind">一级分类</a></li>
						<li  class="tabs_active" ><a href="/questionAnswer/toUpdateOne">新建一级分类</a></li>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/twoKind">二级分类</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toUpdateTwo">新建二级分类</a></li>
					</ul>
			   </div>
			</td>
		</tr>
</table>
		<form  id="kevinFrom"action="/questionAnswer/updateOneKind" method="post">
		<table>
		<tr><td><input type="hidden" id="id" name='id' value='${kind.id}'/>分类名称：<input type="text" id='name' name='name' value='${kind.name}'/></td></tr>
		<tr class='add' id="id_1">
		<td>关键维度：<input type="text" class='dimension' name='dimension' value='${kind.dimension}'></td>
		</tr>
		<tr>
		<td>页面关键字：<input type="text" class='dimension' name='pageKeyword' value='${kind.pageKeyword}'></td>
		</tr>
		<tr>
		<td>页面描述：<input type="text" class='dimension' name='pageDesc' value='${kind.pageDesc}'></td>
		</tr>
		<tr>
		<td>页面title：<input type="text" class='dimension' name='pageTitle' value='${kind.pageTitle}'></td>
		</tr>
		<tr>
		<td>类别排序：<input type="text" class='dimension' name='sort' value='${kind.sort}'></td>
		</tr>
		<tr><td><input type="button" value='提交' onclick="submitKevin()"></td></tr>
		</table>
		</form>
</body>
<script type="text/javascript">
	function submitKevin(){
		if($(".dimension").val()==null && $(".dimension").val()=="" ){
			alert("键维度不能为空");
			return false;
		}
		$("#kevinFrom").submit();
	}
</script>
</html>