<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/sites/sites.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<c:if test="${type==1}"><title>品牌图片添加</title></c:if>
<c:if test="${type==2}"><title>品牌图片修改</title></c:if>
<script type="text/javascript">
	function qwer(id,type){
		if($("#brndPic").val()==null ||$("#brndPic").val()==""){
			alert("请添加图片");
			return;
		}
		$("#pic_"+id).show();
		$.ajax({
				type: "GET",
			   url: "/common/brandPicUpdate?id="+id+"&path="+$('#brndPic').val(),
			   success: function(m){
				   $("#pic_"+id).hide();
				   if(type==1){
					  //添加 
					 window.opener.document.getElementById("searchSpecialForm").submit()
					 alert("添加成功，即将关闭！");
				   }else{
					   alert("修改成功，即将关闭！");
				   }
			    
			     window.close();
			   }
			});
	}
</script>
</head>
<c:if test="${type==1}">
<body>
<div style="height: 40px ; background: #CDCDCD;border: 1px ;solid #f9f9f9;border-top:1px;">
<font>当前位置：品牌管理-->品牌图片添加</font>
</div>
<center><div id="pic_${brand.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:100%;height:100%;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
	<center>请稍后。。。。</center>
</div></center>
<input type="hidden" name="path" id="path">
<input type="hidden" name="id" value="${brand.id}">
<table>
	<tr>
	<td>
	<center>
	 <img  id="oneImage2" style="width: 100px;height: 60px" src='/images/upload.jpg' onclick="$('#listPicFileBrand').trigger('click')"/><span class="font" style="color: red">*(支持.jpg)</span>
</center>
	</td>
	</tr>
	<tr>
	<input type="hidden" name="brndPic" id="brndPic">
          	       <div style="display: none">
					           <input id="listPicFileBrand" name="listPicFileBrand" type="file"  onchange="brandPic()"/>
				            </div>
	<td>
	<input type="button" value="添加" onclick="qwer(${brand.id},1)">
	
	</td>
	</tr>
</table>
</body>
</c:if>
<c:if test="${type==2}">
<body>
<center><div id="pic_${brand.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:100%;height:100%;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
	<center>请稍后。。。。</center>
</div></center>
<div style="height: 40px ; background: #CDCDCD;border: 2px solid #f9f9f9;">
<font>当前位置：品牌管理-->品牌图片修改   </font>
</div>
<input type="hidden" name="path" id="path">
<input type="hidden" name="id" value="${brand.id}">
<table>
	<tr>
	<td>
	<center>
	   <img  id="oneImage2" style="width: 350px;height: 350px" src='${brand.brndPicSrc}' onclick="$('#listPicFileBrand').trigger('click')"/>
</center>
	</td>
	</tr>
	<tr>
	<input type="hidden" name="brndPic" id="brndPic">
          	       <div style="display: none">
					           <input id="listPicFileBrand" name="listPicFileBrand" type="file"  onchange="brandPic()"/>
				            </div>
	<td>
	<input type="button" value="修改" onclick="qwer(${brand.id},2)">
	<input type="button" value="关闭" onclick="window.close()">
	</td>
	</tr>
</table>
</body>
</c:if>
</html>