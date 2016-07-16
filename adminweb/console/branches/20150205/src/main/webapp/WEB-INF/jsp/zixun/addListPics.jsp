<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc/header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/common/ajaxfileupload.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
function getId(id){
	var ids=id;
	$("#vvv").val(id);
}
function closeOp(sta){
	var ids="";
	if(sta==0){
		//alert($("td[sta='0']").text());
	$("td[sta='0']").each(function (){
		ids+=$(this).text()+"_";
		});
		$.ajax({
			type : "GET",
			async : false,
			url : "/zixun/addListPics?tab=0&ids="+ids ,
			dataType : "text",
			success : function(data) {
				alert("保存成功！");
				window.close()
			},
			error:function(){
				alert("保存失败！");
			}
		})
	/* alert("保存成功!");
	window.close() */
	}else{
		window.close()
	}
	
}

</script>
<title>批量添加图片</title>
</head>
<body>
	<form action="/zixun">
			<input type="hidden" id="vvv"  value="0"/>
			 <div>
			 <font>管理咨询>>>批量添加列表图</font>
				<table class="table_style table table-bordered">
					<tr class="attr">
						<td >编号</td>
						<td >标题</td>
						<td >请选择图片(列表图尺寸) </td>
					</tr>
						
							<c:if test="${not empty zixuns}">
								<c:forEach items="${zixuns}" var="zixun">
									<tr class="attr" id="tr_${zixun.id}">
										<td sta="0">${zixun.id}</td>
										<td>${zixun.title}</td>
										<td>
					<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"><!--选择列表图片：--></span>
          	    <img id="listImage_${zixun.id}" src='${zixun!=null?zixun.listPicFull:"/images/selectPiuc.jpg"}'  width="60" height="40" onclick="$('#listPicFile').click(getId(${zixun.id})).trigger('click')"/>
          	    																											
          	    <input type="hidden" id="listPic_${zixun.id}" name="listPic"  value="${zixun.listPic}" />
          	   
										</td>
					
								</c:forEach>
							</c:if >
							<c:if test="${zixuns==null}">
								<tr class="main_info">
									<td colspan="14">没有相关数据</td>
								</tr>
							</c:if>
						
					</tbody>
				</table>
				<center><input type="button" value="保存"  onclick="closeOp(0)"/>
				<input type="button" value="取消" onclick="closeOp(1)"/></center>
			</div> 
			<div style="display: none">
<!-- 			<input id="listPicFile" name="listPicFile" type="file" onchange="uploadImages($('#vvv').val())"/> -->
							<input id="listPicFile" name="listPicFile" type="file" onchange="uploadImages($('#vvv').val())"/>	
			</div>
		</form>
</body>
</html>