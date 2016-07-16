<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网CMS管理</title>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery.tipsy.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
<script type="text/javascript" src="/js/jquery.validate.js"></script>

 <!-- ztree ue-->
  <script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/smoothness/jquery-ui-1.8.14.custom.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/tipsy.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript">
$().ready(function() {
	 $("#templateForm").validate();
});

</script>
</head>
<body>

<form id="templateForm"  action='<c:if test="${ template == null}">/template/add</c:if><c:if test="${ template != null}">/template/update</c:if>' method="post">	
	<div class="borderDiv">
		<c:if test="${ template == null}"> 新增</c:if><c:if test="${ template != null}"> 修改</c:if>基础模板信息
		<table>
           	<tr>
           		<td>模板名称：</td>
				<td>
					<!-- 模板id -->
					<input type="hidden" name="id" value="${template.id}" id="templateId"/>
					<input class="required" name="name" value="${template.name }" maxlength="20"/>    
				</td>
           	</tr>
           	<tr>
           		<td>模板备注：</td>
           		<td>
					<input class="required" name="memo" value="${template.memo }" maxlength="50"/>
           		</td>
           	</tr>
           	<tr>
           		<td style="vertical-align:top">模板标签：</td>
           		<td>
           			<input class="required" name="pic" value="${template.pic  }" maxlength="50"/>
           		</td>
           	</tr>
           	<tr>
           		<td style="vertical-align:top">模板内容：</td>
           		<td>
           			<textarea class="required" class="required" name="content" rows="15" cols="80">${template.content }</textarea>
           		</td>
           	</tr>
           	<tr>
           		<td><input type="submit" value="保存"/></td>
           		<td><button type="button"  onclick="javascript:window.history.back();">取消</button></td>
           	</tr>
        </table>
	</div>
	
</form>
</body>
</html>
