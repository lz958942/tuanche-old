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
<script type="text/javascript">
function saveAdd(){
	var stairName=$("#stairName").val();
	var newSeries=$("#newSeries").val();
	var contry=$("#contry").val();
 	var	pinyin=$("#pinyin").val();
	var args="";
	if(stairName==null||stairName==""){
		alert("请添加二级品牌名称");
		return;
	}
	if(newSeries==null||newSeries==""){
		alert("请添加二级品牌生产厂家性质");
		return;
	}
	if(contry==null||contry==""){
		alert("请添加生产厂家名称");
		return;
	}
	window.location.href="/sites/brand/brandSave?name="+stairName+"&logo="+logo+"&newSeries="+newSeries+"&contry="+contry+"&pinyin="+pinyin;
	alert("添加成功！");
	window.location.href="/sites/brand/home";
	window.close();
}
function closeAdd(){
	window.close();
}

</script>
<title>添加二级品牌</title>
</head>
<body>
	<form action="/sites/brand/brandSave">
		<table>
			<tr>
				<td>二级品牌名称：</td>
				<td><input type="text" name="name" id="stairName"></td>
			</tr>
			<tr>
			<tr>
				<td>生产厂家性质：</td>
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
				<td>厂商名称：</td>
				<td><input type="text" name="stairName" id="stairName" value="${pid}"></td>
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