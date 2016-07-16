<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>console自定义评论</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script> 
<style type="text/css">
	*{
	
font-size:12px;
}
</style>
<script type="text/javascript">
function saveConfig(){
	var sta=true;
	$("[sta=p]").each(function(){
		if($(this).val()==null||$(this).val()==""){
			sta=false;
			alert($(this).attr("staName")+"不能为空！");
			sta=false;
			return false;
			}
		}
		);
	if(sta){
	$("#from1").submit();
	}
}
function getStylemy(pid){
	if(pid!=null &&　pid	!=""){
	 $("#muio option").remove();
	 $("#muio").append("<option selected='selected' value=''>请选择车型</option>");
	 publicAjax("/json/carstyle/ajaxStyle?","brandID",pid,'muio');
	}
	 
}
function getCarstyleModels(id){
	if(id==null || id==""){
		return;
	}
	 $("#carstyleModelId option").remove();
	 $("#carstyleModelId").append("<option selected='selected' value=''>请选择车款</option>");
	publicAjax("/json/carstyle/ajaxCarStyle?","ppid",id,"carstyleModelId");
}
function publicAjax(url,dataName,dataArgs,selectId){
	$.ajax({
		   type: "POST",
		   url: url+$.trim(dataName)+"="+dataArgs,
		   dataType:'json',
		   success: function(data){
			   for(i in data) {
			   		$("#"+selectId).append("<option value="+data[i].id+">"+data[i].style+"</option>");
			   }
			   } 
		
		});
}
function sitesUpload(){
	var imagesize=0;
	var delSrc=$("#delSrc").val();
	var url="/review/saveReview.do";
	var ss=$("#listPicFile").each(function(){
		if(this.files[0].size>2000000){
			
			imagesize=this.files[0].size
			return;
		}
		
	});
	if(imagesize>2000000){
		alert("图片过大，超出2M！");
		return;
	}
	if(!checkImg()){
		return;
	}
	if(delSrc!=null && delSrc!=""){
		url="/review/saveReview.do?delSrc="+delSrc;
	}
	$.ajaxFileUpload({
		url:url,
		fileElementId:'listPicFile',
		dataType: 'text',
		success: function (data){
			if(isNaN(data)){//成功
				var args=data.trim();
				$("#logo").val(args);
				$("#oneImage").attr("src","/pic_tmp/review/"+args+"?"+Math.random());
				var delSrc=args.substring(args.lastIndexOf("/"),args.length);
				if(delSrc!=null && delSrc!=""){
					$("#delSrc").val(delSrc);
				}
				alert("上传成功");
			}else{
				alert("上传失败");
			}
		},

	});
}
//检查文件格式
function checkImg(){
	var fileName = $("#listPicFile").val(), //文件名称
	fileType=["jpg","png","gif","bmp"], //图片类型
	fileExt = ""; //图片拓展名
	fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	for(var i in fileType){
		if(fileExt==fileType[i]){
			return true;
		}
	}
	return false;
}
</script>
</head>
<body>
<input type="hidden" id="tytpe">
<c:if test="${review.id!=null}">
<div style="height: 30px">
	<font style="float:left"> 当前位置：自定义评论管理-->修改自定义评论</font>
</div>
<form  id="from1" action="/review/saveReview" method="post">
<input type="hidden" id="id" name="id" value="${review.id}">
	<table class="table_style table table-bordered" >
	<tr>
		<td class="ti">城市：&nbsp;</td>
					<td>
					<select  id="cityId" name="cityId" style="width: 200px" onchange="getStylemy(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${citys}" var="ct">
							<option  <c:if test="${review.cityId ==ct.value.id}">selected="select"</c:if> value="${ct.value.id }">${ct.value.name}</option>
						</c:forEach>
					</select>
					</td>
		</tr>
		<tr>
		<td class="ti">品牌：&nbsp;</td>
					<td>
					<select sta=p  staName="品牌" id="brind" name="brandId" style="width: 200px" onchange="getStylemy(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option  <c:if test="${review.brandId ==b.id}">selected="select"</c:if> value="${b.id }">${b.reviewInitial}${b.name}</option>
						</c:forEach>
					</select>
					</td>
		</tr>
		<tr>
			<td class="ti">车型名称:</td>
			<td>
			<select name="carstyleId" id="muio"  onchange="getCarstyleModels(this.options[this.options.selectedIndex].value)">
					<option value="">请选择车型</option>
					<c:forEach items="${carShapes }" var="cs">
						<option <c:if test="${cs.id==review.carstyleId }">selected='selected'</c:if>  value="${cs.id }">${cs.style }</option>
					</c:forEach>
					</select>
			</td>
		</tr>
		<tr>
			<td class="ti">车款:</td>
			<td>
			<select name="carstyleModelId" id="carstyleModelId" >
					<option value="">请选择车款</option>
					<c:forEach items="${carStyles }" var="cs2">
						<option <c:if test="${cs2.id==review.carstyleModelId }">selected='selected'</c:if>  value="${cs2.id }">${cs2.style }</option>
					</c:forEach>
					</select>
			</td>
		</tr>
	<tr>
			<td class="ti">活动总评价:</td>
			<td>
			<select sta=p  staName="活动总评价" name="commentTotal"  >
					<option value="">请选择</option>
					<option <c:if test="${review.commentTotal==1 }">selected='selected'</c:if>  value="1">一星</option>
					<option <c:if test="${review.commentTotal==2 }">selected='selected'</c:if> value="2">二星</option>
					<option <c:if test="${review.commentTotal==3 }">selected='selected'</c:if> value="3">三星</option>
					<option <c:if test="${review.commentTotal==4 }">selected='selected'</c:if> value="4">四星</option>
					<option<c:if test="${review.commentTotal==5 }">selected='selected'</c:if> value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
		<tr>
			<td class="ti">价格给力度:</td>
			<td>
			<select sta=p  staName="价格给力度" name="commentPrice"  >
					<option value="">请选择</option>
					<option <c:if test="${review.commentPrice==1 }">selected='selected'</c:if>  value="1">一星</option>
					<option <c:if test="${review.commentPrice==2 }">selected='selected'</c:if> value="2">二星</option>
					<option <c:if test="${review.commentPrice==3 }">selected='selected'</c:if> value="3">三星</option>
					<option <c:if test="${review.commentPrice==4 }">selected='selected'</c:if> value="4">四星</option>
					<option <c:if test="${review.commentPrice==5 }">selected='selected'</c:if> value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
			<tr>
			<td class="ti">团长服务质量:</td>
			<td>
			<select  sta=p  staName="团长服务质量" name="commentService">
					<option value="">请选择</option>
					<option <c:if test="${review.commentService==1 }">selected='selected'</c:if>  value="1">一星</option>
					<option <c:if test="${review.commentService==2 }">selected='selected'</c:if> value="2">二星</option>
					<option <c:if test="${review.commentService==3 }">selected='selected'</c:if> value="3">三星</option>
					<option <c:if test="${review.commentService==4 }">selected='selected'</c:if> value="4">四星</option>
					<option <c:if test="${review.commentService==5 }">selected='selected'</c:if> value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
		<tr>
			<td  class="ti">4S店服务质量:</td>
			<td>
			<select sta=p  staName="4S店服务质量" name="commentShop"  >
					<option value="">请选择</option>
					<option <c:if test="${review.commentShop==1 }">selected='selected'</c:if>  value="1">一星</option>
					<option <c:if test="${review.commentShop==2 }">selected='selected'</c:if> value="2">二星</option>
					<option <c:if test="${review.commentShop==3 }">selected='selected'</c:if> value="3">三星</option>
					<option <c:if test="${review.commentShop==4 }">selected='selected'</c:if> value="4">四星</option>
					<option <c:if test="${review.commentShop==5 }">selected='selected'</c:if> value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
		<tr>
			<td>评论人</td>
			<td><input   sta="p"  id="key" staName="评论人" type="text" name="evaName" maxlength="20" value="${review.evaName }"><font  id="font1" color="red">*</font></td>
		</tr>
		 <tr>
				<td>修改图片：</td>
				<td>
				<c:choose>
          	         <c:when test="${empty review.imageSrc}">
          	            <img  id="oneImage" style="width: 100px;height: 60px" src='/images/upload.jpg' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">(支持.jpg)仅限一张</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage" style="width: 80px;height: 80px" onclick="$('#listPicFile').trigger('click')" src="${review.imageSrc}"/>
          	         </c:otherwise>
          	     </c:choose>
          	     <input type="hidden" name="imageSrc" id="logo">
          	       <div style="display: none">
					           <input id="listPicFile" name="listPicFile" type="file"  onchange="sitesUpload()"/>
				            	<input  id="delSrc"type="hidden" >
				            </div>
          	    </td>
			</tr>
		<tr>
			<td>是否为精华：</td>
		<td>
		<select name="isCream">
			<option  value="">请选择</option>
			<option <c:if test="${review.isCream==0}">selected='selected'</c:if> value="0">否</option>
			<option <c:if test="${review.isCream==1}">selected='selected'</c:if> value="1">是</option>
		</select>
		</td>
			
		</tr>
		<tr>
			<td>评论时间：</td>
		<td>
		<input  readonly="readonly" id="d11" name="createtime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${ review.createtime}"/> 
		</td>
		</tr>
		<tr>
			<td>评论内容</td>
	<td><textarea sta="p"  staName="评论" id="replyContent" name="comment" style="width: 450px" rows="3" cols="31" maxlength="500">${review.comment}</textarea></td>
			
		</tr>
	</table>
	 <input type="button"  value="修改" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
	 </form>
	 </c:if>
	 <c:if test="${review.id==null}">
	 <div style="height: 30px">
	<font style="float:left"> 当前位置：自定义评论管理-->自定义评论添加</font>
</div>
<form  id="from1" action="/review/saveReview" method="post">
	<table class="table_style table table-bordered" >
	<tr>
		<td class="ti">城市： &nbsp;</td>
					<td>
					<select  id="cityId" name="cityId" style="width: 200px">
					<option value="">请选择</option>
						<c:forEach items="${citys}" var="ct">
							<option value="${ct.value.id }">${ct.value.name}</option>
						</c:forEach>
					</select>
					</td>
		</tr>
		<tr>
		<td class="ti">品牌：&nbsp;</td>
					<td>
					<select sta=p  staName="品牌" id="brind" name="brandId" style="width: 200px" onchange="getStylemy(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option value="${b.id }">${b.reviewInitial}${b.name}</option>
						</c:forEach>
					</select><font  id="font1" color="red">*</font>
					</td>
		</tr>
		<tr>
			<td class="ti">车型名称:</td>
			<td>
			<select name="carstyleId" id="muio" onchange="getCarstyleModels(this.options[this.options.selectedIndex].value)" >
					<option value="">请选择车型</option>
					</select>
			</td>
		</tr>
		<tr>
			<td class="ti">车款:</td>
			<td>
			<select name="carstyleModelId" id="carstyleModelId" >
					<option value="">请选择车款</option>
					</select>
			</td>
		</tr>
		<tr>
			<td class="ti">活动总评价:</td>
			<td>
			<select sta=p  staName="活动总评价" name="commentTotal"  >
					<option value="">请选择</option>
					<option value="1">一星</option>
					<option value="2">二星</option>
					<option value="3">三星</option>
					<option value="4">四星</option>
					<option value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
		<tr>
			<td class="ti">价格给力度:</td>
			<td>
			<select sta=p  staName="价格给力度" name="commentPrice"  >
					<option value="">请选择</option>
					<option value="1">一星</option>
					<option value="2">二星</option>
					<option value="3">三星</option>
					<option value="4">四星</option>
					<option value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
		<tr>
			<td class="ti">团长服务质量:</td>
			<td>
			<select  sta=p  staName="团长服务质量" name="commentService">
					<option value="">请选择</option>
					<option value="1">一星</option>
					<option value="2">二星</option>
					<option value="3">三星</option>
					<option value="4">四星</option>
					<option value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
		<tr>
			<td  class="ti">4S店服务质量:</td>
			<td>
			<select sta=p  staName="4S店服务质量" name="commentShop"  >
					<option value="">请选择</option>
					<option value="1">一星</option>
					<option value="2">二星</option>
					<option value="3">三星</option>
					<option value="4">四星</option>
					<option value="5">五星</option>
					</select><font  id="font1" color="red">*</font>
			</td>
		</tr>
		<tr>
			<td>评论人</td>
			<td><input   sta="p"  id="key" staName="评论人" type="text" name="evaName" maxlength="20"><font  id="font1" color="red">*</font></td>
		</tr>
		 <tr>
				<td>添加图片：</td>
				<td>
				<c:choose>
          	         <c:when test="${empty imageSrc}">
          	            <img  id="oneImage" style="width: 100px;height: 60px" src='/images/upload.jpg' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">(支持.jpg)仅限一张</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage" style="width: 80px;height: 80px" onclick="$('#listPicFile').trigger('click')" src="${review.imageSrc}"/>
          	            </c:otherwise>
          	     </c:choose>
          	     <input type="hidden" name="imageSrc" id="logo">
          	       <div style="display: none">
					           <input id="listPicFile" name="listPicFile" type="file"  onchange="sitesUpload()"/>
				            	<input  id="delSrc"type="hidden" >
				   </div>
          	    </td>
			</tr>
		<tr>
			<td>是否为精华：</td>
		<td>
		<select name="isCream">
			<option value="">请选择</option>
			<option value="0">否</option>
			<option value="1">是</option>
		</select>
		</td>
			
		</tr>
		<tr>
			<td>评论时间：</td>
		<td>
			<input  readonly="readonly" id="d11" name="createtime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${ review.createtime}"/> 
		</td>
			
		</tr>
		<tr>
			<td>评论内容</td>
	<td><textarea sta="p"  staName="评论" id="replyContent" name="comment" style="width: 450px" rows="3" cols="31" maxlength="500"></textarea><font  id="font1" color="red">*</font></td>
			
		</tr>
	</table>
	 <input type="button"  value="添加" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
	 </form>
	 </c:if>
</body>
</html>