<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网关键词系统</title>
</head>
<body>
<form id="listForm" method="POST" action="/keyword/search">
<br>
<div class="b-con a-form">
<div class="pd5">
<label>维度名称
<input id='dieName' name='dieName' type='text' value="${dimension.dieName }" style=width:100px />
</label>
<label>相关关键词
<input id='keywords' name='keywords' type='text' value="${dimension.keywords }" style=width:100px />
</label>
<label>操作人员
<select id='operate' name='operateUserId' class="w130 " 
style=width:100px >
<option id="default" value="" >--请选择--</option>
<c:forEach items="${operateList }" var="operateList">
	<option value="${operateList.operateUserId }"<c:if test="${dimension.operateUserId==operateList.operateUserId }">selected</c:if>>${operateList.operateUserName }</option>
</c:forEach>
</select>
</label>
<label class="pr15">创建时间：
      <span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly" value="${dimension.startTime }" class=" inptime span2" style="width:70px" name="startTime"  id="startTime">&nbsp;至&nbsp;
      <span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly" value="${dimension.endTime }" class="inptime span2" style="width:70px" name="endTime"  id="endTime">
</label>
&nbsp;&nbsp;&nbsp;
</div>
<div  align="center">
<input type="submit" value="查询" class="btn" onclick="search();"/>
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
</div>
</div>
<table class="table_style table table-bordered" id="attrtable">
<thead>
<tr>
<td>
<input type="checkbox" id="checkAll"/>
</td>
<td>ID</td>
<td width="200px">维度名称</td>
<td width="300px">相关关键词</td>
<td>操作人员</td>
<td>创建时间</td>
<td>更新时间</td>
<td width="300px">操作</td>
</tr>
</thead>

<tbody>
<c:forEach items="${dimList }" var="dimension">
<tr id="tr_${dimension.id }" class="attr">

<td><input id="check_${dimension.id }" name="checkone" type="checkbox" class="checkItem" value="${dimension.id }"/></td>
<td width="80px">${dimension.id }</td>
<td width="300px">${dimension.dieName }</td>
<td width="40%">${dimension.keywords }</td>
<td width="100px">${dimension.operateUserName }</td>
<td width="350px">${dimension.createTime }</td>
<td width="350px">${dimension.updateTime }</td>
<td width="10%">
	<a href="/keyword/preUpdate?id=${dimension.id }">修改</a>
	<a href="javascript:deleteDim('${dimension.id }')">删除</a> 
</td>
</tr>
</c:forEach>
</tbody>
</table>
<table>
<tr>
<td><input type="button" value="删除" onclick="javascript:deleteAllDim();" class="btn"/></td>
</tr>
</table>
<div class="page_and_btn" style="text-align:center;">
   <jsp:include page="/WEB-INF/snippets/page.jsp" />
</div>
</form>
<script type="text/javascript" charset="UTF-8">
$(function() {
	emId=${sessionScope.employeeId};
	$("#checkAll").click(function() {
        $(".checkItem").attr("checked",this.checked); 
    });
 });
/* 清空查询条件 */
function clearSearch(){	
	$("#dieName").val('');
	$("#keywords").val('');
	$("#operate").val('');
	$("#startTime").val('');
	$("#endTime").val('');
	window.location.href="/keyword/search";
}
/* 条件查询 */
function search(){
	$("#listForm").submit();
}
/* 批量删除 */
function deleteAllDim(){
	var selectids = $("input[name='checkone']:checked");
	var ids = "";
	for ( var i = 0; i < selectids.length; i++) {
		if (i == selectids.length - 1) {
			ids += $(selectids[i]).val();
		} else {
			ids += $(selectids[i]).val() + ",";
		}
	}
	if (selectids.length == 0) {
		alert("未选中任何行！")
		return;
	}
	if(confirm("确定要删除选中的维度？")){
		$.ajax({
			url:'/keyword/deleteAllDim',
			dataType:'text',
			type:'post',
			data:{
				'ids':ids
			},
			success:function(data){
				for ( var i = 0; i < selectids.length; i++) {
					$("#tr_"+$(selectids[i]).val()).remove();
					$("#listForm").submit();
				}
				 if($("#checkAll").attr("checked")){
					 $("#checkAll").attr("checked",false);
				 }
				
			}
			
		});
	}
}

$("#listForm").validate({
    //信息存放位置
    errorPlacement: function(error, element){
       error.appendTo(element.parent());
    },
    //包裹信息标签
    wrapper: "li",
});


function deleteDim(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			url:'/keyword/deleteDie',
			type:'post',
			dataType:'text',
			data:{
				'id':id,
			},
			success:function(data){
				$("#tr_"+id).remove();
				$("#listForm").submit();
			}
		});
	}
}

function saveItems(){
	$(".attr").each(function(){
		if($(this).find("td:eq(14)").find("input").val()!=emId){
			$(this).find("td:eq(10)").find("input").attr("disabled",false);
			$(this).find("td:eq(11)").find("input").attr("disabled",false);
			
		}
	});
	$("#listForm").attr("action","/keyword/saveItems");
	$("#listForm").submit();
}
</script>
</body>
</html>