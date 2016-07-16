<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/sites/sites.js"></script>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>

<title>添加二级品牌</title>
</head>
<body>
	<form action="/sites/brand/brandSave">
		<table>
			<tr>
				<td>一级品牌名称：</td>
				<td><input type="text" name="stairName" id="stairName"></td>
			</tr>
			<tr>
			<tr>
				<td>一级品牌拼音：</td>
				<td><input type="text" name="pinyin" id="pinyin"></td>
			</tr>
			<tr>
				<td>品牌logo：</td>
				<td>
				<c:choose>
          	         <c:when test="${empty brand}">
          	              <img  id="oneImage" src='/images/upload.jpg' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">*</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage" width="100px" height="100px" src='${brand.logo }' onclick="$('#listPicFile').trigger('click')"/>
          	            </c:otherwise>
          	     </c:choose>
          	     <input type="hidden" name="logo" id="logo">
          	       <div style="display: none">
					           <input id="listPicFile" name="listPicFile" type="file"  onchange="sitesUpload()"/>
				            </div>
          	    </td>
			</tr>
			<tr>
				<td>系别</td>
				<td>
				<select name="newSeries" id="newSeries">
					<option selected="selected" value="">请选择</option>
					<option value="1" >1</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">${pid }</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>国别</td>
				<td>
				<select name="contry" id="contry">
					<option selected="selected" value="">请选择</option>
					<option value="1">1</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
					<option value="1">请选择</option>
				</select>
				</td>
			</tr>
			<tr>
				<td> <input type="button" value="保存" onclick="saveAdd()" >
					<input type="button" value="取消" onclick="closeAdd()">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>