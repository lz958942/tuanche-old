	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网模板管理</title>
<%@include file="/WEB-INF/jsp/common/import.jsp" %>
<script type="text/javascript" src="/js/common/jsdate.js?9090"></script>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
 
 <script type="text/javascript" src="/js/jquery.validate.js"></script>
 
  <script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
  
  
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<script type="text/ecmascript" src="/js/upload.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>

<script type="text/javascript" src="/js/template/tlcity.js"></script>
<script type="text/javascript" src="/js/template/plate.js"></script>
<script type="text/javascript">
//上传图片
function upLoadImage(){
	 var fileId = $(this).val();
	 upload(fileId,"imgFile","imageShow",3);
}
$().ready(function() {
	 $("#plate").validate();
	});

</script>
<link type="text/css" rel="stylesheet" href="/css/common/tipsy.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
</head>
<body>

<form action='<c:if test="${ plate== null}">/plate/add</c:if><c:if test="${ plate!= null}">/plate/update</c:if>' method="post" id="plate">
	<div class="borderDiv">
		<table>
			<tr>
				<td >
					<font >
					<c:if test="${ plate== null}">新增</c:if><c:if test="${ plate!= null}">修改</c:if>板块
					</font>
				</td>
			</tr>
			<tr>
            	<td>板块类型：</td>
                <td>
               		 <select name="ptype">
               		 	<option value="1" <c:if test="${ plate.ptype ==1}">selected="selected"</c:if>>资讯板块</option>
               		 	<option value="2" <c:if test="${ plate.ptype ==2}">selected="selected"</c:if>>城市板块</option>
               		 	<option value="3" <c:if test="${ plate.ptype ==3}">selected="selected"</c:if>>摇号板块</option>
               		 </select>
                </td>
            </tr>
			<tr>
            	<td>城市：</td>
                <td>
               		 <input type="hidden" name="id" id="plateId" value="${plate.id }"/>
					<input class="required" type="text" id="city" name = "cityName" value="${plate.cityName}" maxlength="30"/>
					<input type="hidden" id="cityId" name = "cityId" value="${plate.cityId}"/>
                </td>
            </tr>
            <tr>
            	<td>展示类型：</td>
                <td> 
					<select id="typeSelector" name="type" >
						<option value="1" <c:if test="${plate.type == 1}">selected ="selected"</c:if>>图文类型</option>
						<option value="2" <c:if test="${plate.type == 2}">selected ="selected"</c:if>>图片类型</option>
						<option value="3" <c:if test="${plate.type == 3}">selected ="selected"</c:if>>文字类型</option>
						<option value="4" <c:if test="${plate.type == 4}">selected ="selected"</c:if>>特殊类型</option>
					</select>
					<p id="tiShi" <c:if test="${plate.type != 4}">style="display: none;"</c:if> >
					<img alt="" width="180px" src="/images/coreshow.jpg">
					特殊类型，手动板块，上图表示一个板块，标红的每一个框都是一条内容，如果手推不足不会自推，请注意排版和排序。
					</p>
                </td>
            </tr>
             <tr>
            	<td>数据填充方式：</td>
                <td>
					<select name="dataFillMode"  id="isauto" <c:if test="${plate.type == 4}">disabled="disabled"</c:if>>
						<option value="1" <c:if test="${plate.dataFillMode == 1}">selected ="selected"</c:if>>自动</option>
						<option value="2" <c:if test="${plate.dataFillMode == 2 || plate.type == 4}">selected ="selected"</c:if>>手动</option>
					</select>
                </td>
            </tr>
            <tr>
            	<td>展示条数：</td>
                <td>
					<input type="text" name="conCount" class="number required" value="${plate.conCount }" maxlength="2"/>
                </td>
            </tr>
            <tr id="selectDataType"  <c:if test="${plate.type == 4}"> style="display: none;"</c:if>>
            	<td>数据类型：</td>
                <td>
					<select id="dataTypeSelector" name="dataType">
						<option value="1" <c:if test="${plate.dataType == 1}">selected ="selected"</c:if>>资讯数据</option>
						<option value="2" <c:if test="${plate.dataType == 2}">selected ="selected"</c:if>>团购数据</option>
						<option value="3" <c:if test="${plate.dataType == 3}">selected ="selected"</c:if>>专题数据</option>
						<option value="4" <c:if test="${plate.dataType == 4}">selected ="selected"</c:if>>团长数据</option>
						<option value="5" <c:if test="${plate.dataType == 5}">selected ="selected"</c:if>>车型数据</option>
						<option value="9" <c:if test="${plate.dataType == 9}">selected ="selected"</c:if>>其他数据</option>
					</select>
					<!-- 显示类型数据 -->
					<select <c:if test="${plate.dataType != 1 && plate.dataType !=5}">style="display: none"</c:if> name="dataTypeClass" id="dataTypeClass">
						
					</select>
					<!-- 资讯类型 -->
					<select style="display: none" id="zixunTypeClass">
						<c:forEach items="${zixunClass }" var="zixunclass">
							<option <c:if test="${plate.dataTypeClass == zixunclass.id}">selected ="selected"</c:if> value="${zixunclass.id}">${zixunclass.name}</option>
						</c:forEach>
					</select>
					<!-- 车型类型 -->
					<select style="display: none" id="carTypeClass">
						<option <c:if test="${plate.dataTypeClass == 1}">selected ="selected"</c:if> value="1">小型车</option>
						<option <c:if test="${plate.dataTypeClass == 2}">selected ="selected"</c:if> value="2">紧凑车型</option>
						<option <c:if test="${plate.dataTypeClass == 3}">selected ="selected"</c:if> value="3">中级车</option>
						<option <c:if test="${plate.dataTypeClass == 4}">selected ="selected"</c:if> value="4">中高级车</option>
						<option <c:if test="${plate.dataTypeClass == 5}">selected ="selected"</c:if> value="5">豪华车</option>
						<option <c:if test="${plate.dataTypeClass == 6}">selected ="selected"</c:if> value="6">MPV</option>
						<option <c:if test="${plate.dataTypeClass == 7}">selected ="selected"</c:if>value="7">suv</option>
						<option <c:if test="${plate.dataTypeClass == 8}">selected ="selected"</c:if> value="8">跑车</option>
				   <!-- 暂时没有数据
				   		<option value="9">微型车</option><option value="12">新能源汽车</option>
						<option value="13">电动汽车</option><option value="14">商务车</option>
					 -->
					</select>
                </td>
            </tr>
            <tr>
            	<td>板块名称：</td>
                <td>
					<input class="required" type="text" name = "plateName" value="${plate.plateName }" maxlength="30"/>
                </td>
            </tr>
             <tr>
            	<td>超链接：</td>
                <td>
						<input  type="text" name="hyperlink" value="${plate.hyperlink }" maxlength="80"/>
                </td>
            </tr>
            <tr>
            	<td>标签：</td>
                <td>
					<input class="required" type="text" id="plateLabel" name ="label" value="${plate.label }" maxlength="30"/>
                </td>
            </tr>
             <tr>
            	<td>标题：</td>
                <td>
					<input class="required" type="text" name="title" value="${plate.title }" maxlength="30"/>
                </td>
            </tr>
            <tr>
            	<td>备忘：</td>
                <td>
					<input class="required" type="text" name="memo" value="${plate.memo }" maxlength="30"/>
                </td>
            </tr>
             <tr>
            	<td><button class="saveTlcity"  onclick="$('#templateSelector').trigger('change')">保存</button></td>
                <td>
					<button type="button"  onclick="javascript:window.history.back();">取消</button>
                </td>
            </tr>
        </table>
	</div>
	
</form>
</body>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>
</html>