<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加二级品牌</title>
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
<!-- <script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script> -->
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<!-- <script type="text/javascript" src="/js/zixun/zixun.js"></script> -->
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/sites/sites.js"></script>
<link type="text/css" rel="stylesheet" href="/css/common/smoothness/jquery-ui-1.8.14.custom.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/tipsy.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
</head>

<body>

	<!-- <div id="tabs" class="tabs">  
	<ul>
		<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/specialSubject/home">专题列表</a></li>
		<li class="tabs_active" ><a href="/specialSubject/toAdd">添加品牌</a></li>
	</ul>
</div> -->
当前位置：后台管理>修改二级品牌
<c:if test="${twoBrand.id!=null}">
<form action="/sites/brand/twoBrandUpdate" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="newZixunPropertiesForm">
	<input type="hidden" name="id" value="${twoBrand.id }">
	<input type="hidden" name="pid" value="${twoBrand.pid }">
	<div class="borderDiv">
		<table>
            <tr>
            	<td style="vertical-align:top">二级品牌名称：</td>
                <td><input maxlength="20" type="text"  id="name" name="name" value="${twoBrand.name }" style="width:130px;" />
                <span style="color:red;">* (请控制50字以内)</span>
                </td>
            </tr>
            	<tr>
            	<td style="vertical-align:top">生产厂家性质：</td>
                <td>
                	<select name="vender">
                		<option selected="selected" value="">请选择厂家性质</option>
                		<option value="1"  <c:if test="${twoBrand.vender==1}">selected="selected"</c:if>>合资</option>
                		<option value="2" <c:if test="${twoBrand.vender==2}">selected="selected"</c:if>>进口</option>
                		<option value="3" <c:if test="${twoBrand.vender==3}">selected="selected"</c:if>>国产</option>
                	</select>
                 </td>
            </tr>
            <tr>
            <td>生产厂家名称：</td>
              <td><input maxlength="20" type="text"  id="cname" name="cname" value="${twoBrand.cname }" style="width:130px;" />
            </tr>
          </table>
            <table id="msg">
            <tr>
            	<td>&nbsp;</td>
            	<td>
            		<span id="errMsg" style="color:red;"></span>
            	</td>
            </tr>
        </table>
	</div>
	<div class="xtnext">
		<input type="submit"  value="保存"/>
	</div>
	</form>
	</c:if>
	<c:if test="${twoBrand.id==null}">
	暂无数据
	</c:if>


</body>
</html>