<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>推广信息添加</title>
</head>
<body>
<form id="addForm" action="/promo/add" method="POST">
	<div id="man_zone">
		<table class="table_style table table-bordered" id="addtable">
		<thead>
		<tr>
			<!-- <td><input type="checkbox" id="checkAll"/></td> -->
			<td>城市</td>
			<td>城市code</td>
			<td>品牌</td>
			<td>推广费用</td>
			<td>推广时间</td>
		</tr>
	</thead>
	<tbody id="tbody">
		<tr id="addHtml">
			<!-- <td><input id='check_num1' name='check_num1' type="checkbox"  class="checkItem" onclick="setSelectAll()"/></td> -->
			<td>
				<select id="cityId" name="cityId" class="w130 regname {required:true} ">
					<option value="">请选择</option>
					<c:forEach items="${citys }" var="citys">
						<option value="${citys.value.id }" ${citys.value.id==promotion.cityId?'selected':'' }>${citys.value.orderName}</option>
					</c:forEach>
				</select>
			</td>
			<td>
         			<input type="text" id="cityCode" name="cityCode" value=""/>
			</td>
			<td>
				<select id="brandId" name="brandId" class="w130 regname {required:true} ">
					<option value="">请选择</option>
					<c:forEach items="${brands }" var="brands">
						<option value="${brands.id }" ${brands.id==promotion.brandId?'selected':'' }>${brands.orderName} ${brands.name }</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<input type="text" id="money" name="money"/>
			</td>
			<td>
				<input id="spendTime" type='text' name='spendTime' class="w130 regname {required:true} " value="" readonly="readonly" autocomplete="off" />
			</td>
		</tr>
	</tbody>
</table>
	<!-- 	<div>
			<table style="float:left;width:200px;margin:5px">
				<tr>
						<td><input type="button" class="btn" value="添加项" id="add" onclick="addAtr()" /></td>
						<td><input type="button" class="btn" value="删除项" id="delete" onclick="deleteAtr()" /></td>
						<td><input type="button" class="btn" value="全选" id="selectBtn" onclick="selectAll()" /></td>
				</tr>
			</table>
		</div> -->
	</div>
	<div align="center">
		<input type="button" id="saveBtn" class="btn" value="保存"/>
	</div>
</form>
</body>
<script type="text/javascript">
$("#spendTime").live("blur",function(){
	spendTime = $("#spendTime").val();
});
$("input[name*=spendTime]").live("click",function() {
	WdatePicker({
	isShowClear:true,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});
	
var cityId=$("#cityId").val();
var brandId=$("#brandId").val();
if(spendTime==null){
	spendTime=$("#spendTime").val();
}
$("#saveBtn").click(function(){
	$("#addForm").submit();
});

$("#addForm").validate({
    //信息存放位置
    errorPlacement:function(error, element) {
       error.appendTo(element.parent());
    },
    //包裹信息标签
    wrapper: "span",
    //验证通过执行
    submitHandler:function(form){
    	$.ajax({
    		type:'post',
    		url:'/promo/selectSameCount',
    		dataType:'text',
    		data:{
    			cityId:cityId,
    			brandId:brandId,
    			spendTime:spendTime,
    			id:null,
    		},
    		success:function(data){
    			if(data!=null&&data!=''){
    				alert(data);
    			}else{
    				form.submit();
    			}
    		}
    	}) 
    }
});
</script>
</html>