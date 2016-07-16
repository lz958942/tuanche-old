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
  
  <script type="text/javascript" src="/js/template/tlcity.js"></script>
  
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/smoothness/jquery-ui-1.8.14.custom.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/tipsy.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript">
$().ready(function() {
	 $("#tlcity").validate();
});

</script>
</head>
<body>

<form action='<c:if test="${ tlcity== null}">/tlcity/add</c:if><c:if test="${ tlcity!= null}">/tlcity/update</c:if>' method="post" id="tlcity">
	<div class="borderDiv">
	<c:if test="${ tlcity== null}">新增</c:if><c:if test="${ tlcity!= null}">修改</c:if>城市模板信息
		<table>
           	<tr>
           		<td>城市：</td>
				<td>
					<input type="hidden" name="id" value="${tlcity.id }"/>
					<input type="text" id="city" name = "cityName" value="${tlcity.cityName}" class="required"/>
					<input type="hidden" id="cityId" name = "cityId" value="${tlcity.cityId}"  class="required"/>
				</td>
           	</tr>
            <tr>
            	<td>模板类型：</td>
            	<td>
            		<select  name="tid" id="selectTl">
						<c:forEach items="${templateList }" var="template">
							<option value="${template.id }" <c:if test="${ template.id == tlcity.tid}">selected="selected"</c:if>>${template.name}</option>
						</c:forEach>
					</select>
            	</td>
            </tr>
            <tr>
           		<td>类型：</td>
				<td>
					<select name="tlcitytype">
						<option value="1" <c:if test="${tlcity.tlcitytype ==1}">selected="selected"</c:if>>资讯</option>
						<option value="2" <c:if test="${tlcity.tlcitytype ==2}">selected="selected"</c:if>>城市</option>
						<option value="3" <c:if test="${tlcity.tlcitytype ==3}">selected="selected"</c:if>>摇号</option>
					</select>
				</td>
           	</tr>
            <tr>
            	<td><button class="saveTlcity">保存</button></td>
            	<td><button type="button"  onclick="javascript:window.history.back();">取消</button></td>
            </tr>
        </table>
	</div>
	
</form>
</body>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>
</html>
