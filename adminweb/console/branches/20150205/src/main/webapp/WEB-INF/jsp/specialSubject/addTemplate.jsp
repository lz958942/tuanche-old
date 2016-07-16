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

<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/specialSubject/specialSubject.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/common/jsdate.js"></script>
</head>
<body>
	<div id="tabs" class="tabs">  
	<ul>
		<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/specialSubject/tempHome">模板列表</a></li>
		<li class="tabs_active" ><a href="/specialSubject/toAddTemp">新建模板</a></li>
	</ul>
</div>
<form action="/specialSubject/addTemplate" method="post" style="padding:0 10px 0 10px; margin-top:0px" name="zixun" id="newTemplateForm">
	<div class="borderDiv">
		<table>
            <tr class="lh28">
            	<input id="id" type="hidden" name="id" value="${template.id }"/>
            	<td class="ti" style="vertical-align:top">模板名称:</td>
                <td><input maxlength="44" type="text"  id="tpName" name="tpName" value="${template.tpName }" style="width:675px;"/><span style="color:red;">*</span>(40字以内)
                </td>
            </tr>
             <tr class="lh28">
            	<td style="vertical-align:top">模板描述:</td>
                <td><textarea maxlength="250" cols="50"  rows="3" id="tpDesc" name="tpDesc" style="width:675px">${template.tpDesc }</textarea>
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">模板内容:</td>
                <td><textarea  cols="50"  rows="5" id="tpContnt" name="tpContent" style="width:675px">${template.tpContent }</textarea><span style="color:red;">*</span>
                </td>
            </tr>
        </table>
	</div>
	<div class="xtnext">
		<input id="newTemplateBtn" type="button" value="保存"/>
	</div>
</form>

<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
<div id="menuContentselectCarStyle" style="display:none; position: absolute;" >
	<ul id="treeselectCarStyle" class="ztree"  ></ul>
</div>
</body>
</html>