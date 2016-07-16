<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map,java.util.HashMap,com.tuanche.console.util.NameUtil,com.tuanche.console.util.GlobalConstants" %> 
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网维度系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>
<body>
<form  action="/keyword/insertDim" id='addDimension' method="POST">
<div id="man_zone" style="width: 99%;">
<table class="table_style table table-bordered" >
<thead>
<tr>
<td><input type="checkbox" id="checkAll"/></td>
<td style="text-align: center">维度名称</td>
<td style="text-align: center">相关关键词</td>
</tr>
</thead>
<tbody id="tbody">
<tr id="addHtml">
<td style="vertical-align: middle;"><input id="check_num1" name="check_num1" type="checkbox" class="checkItem" onclick="setSelectAll()"/></td>
<td style="text-align: center;vertical-align: middle;">
<input id="dimNameArray_num1" name="dimNameArray" type='text' maxlength="100" value="${dimension.dieName }" style="width: 150px;height: 25px" />
<p><span class="font" style="color: red;font-size: 12px">*  请控制在100字以内</span></p>
</td>
<td style="text-align: center">
<textarea rows="2" id="keywordsArray_num1" style="width: 95%;resize:none" name="keywordsArray" maxlength="500">${dimension.keywords }</textarea>
<p><span class="font" style="color: #696969;font-size: 12px">请控制在500字以内</span></p>
</td>
</tr>
</tbody>
</table>
<div>
<table style="float:left;width:200px;margin:5px">
<tr>
<c:if test="${dimension ==null}">
<td><input type="button" class="btn" value="添加项" id="add" onclick="addAtr()" /></td>
<td><input type="button" class="btn" value="删除项" id="delete" onclick="deleteAtr()" /></td>
</c:if>
<td><input type="button" class="btn" value="保存" onclick="saveItems();"/></td>
<td><input type="hidden"  value="1" name="num"  id="num"/></td>
</tr>
</table>
</div>
</div>
<c:if test="${dimension.id!=null }">
	<input id="id" name="id" value="${dimension.id }" type="hidden">
	<input id="createTime" name="createTime" value="${dimension.createTime }" type="hidden">
</c:if>
</form>
<script>

var retainStr;
var list;
var listLength;
$(function() {
	$("#checkAll").click(function() {
        $(".checkItem").attr("checked",this.checked); 
    });
   var idText=$("#id").val();
   retainStr='<tr>'+$("#addHtml").html()+'</tr>';
   if(idText!=null&&idText!=''){
	  $("#dimNameArray_num1").attr("name","dieName");
	  $("#keywordsArray_num1").attr("name","keywords");
   }
 });
 
function deleteAtr(){
	if($("tbody input:checkbox:checked").length>0){
		if(confirm("删除之后将不会恢复，确认删除吗？")){
			$("tbody input:checkbox:checked").parent().parent().remove();
			if($("#checkAll").attr("checked")){
				$("#checkAll").attr("checked",false); 
			} 
			var lineNum=$("#num").val();
			linNum=parseInt(lineNum)-1;
			$("#num").val(linNum);
		}
	}
	else{
		alert("请选择需要删除的记录！");
	}
}

function addAtr(){
	var lineNum=$("#num").val();
	lineNum=parseInt(lineNum)+1;
	$("#num").val(lineNum);
	var rg=new RegExp("_num1","g");
	var addHtml=retainStr.replace(rg,"_num"+lineNum);
	$("#tbody").append(addHtml);
}

function saveItems(){
	var flag=true;
	var idText=$("#id").val();
	var array=new Array();
	$("input[id*='dimName']").each(function(i){
			array[i]=$(this).val();
			for(var x=0;x<array.length;x++){
				if(i==x){
					continue;
				}else{
					if($(this).val()===array[x]){
						alert("第"+(x+1)+"行和第"+(i+1)+"行名称重复,请重新输入");
						flag=false;
						return flag;
					}
				}
			}
			if($(this).val()==''||$(this).val()==null){
				alert("第"+(i+1)+"行不能为空!");
				flag=false;
				return flag;
			}
	});
	if(idText!=null&&idText!=''){
		$("#addDimension").submit();
	}else{
		$.ajax({
			url:'/keyword/selectDieName',
			type:'post',
			dataType:'json',
			success:function(data){
				list=data.data;
				listLength=list.length;
				$("input[id*='dimName']").each(function(i){
					for(var x=0;x<listLength;x++){
						if($(this).val()===(list[x].dieName)){
							alert("第"+(i+1)+"行输入的名称已存在!");
							flag=false;
							return flag;
						}
					}
				});
				if(flag){
					$("#addDimension").submit();
				}
			}
		});	
	}
		
}
</script>

</body>

</html>