<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加礼品</title>
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
function sitesUpload(){
	var imagesize=0;
	var delSrc=$("#delSrc").val();
	var url="/json/gift/uploadImg";
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
		url="/json/gift/uploadImg?delSrc="+delSrc;
	}
	$.ajaxFileUpload({
		url:url,
		fileElementId:'listPicFile',
		dataType: 'json',
		success: function (data){
			if(isNaN(data)){//成功
				var args=data.trim();
				$("#logo").val(args);
				$("#oneImage").attr("src",args);
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
<div style="height: 30px">
	<c:if test="${bean.id==null}">
	<font style="float:left"> 当前位置：礼品管理-->添加礼品</font>
	</c:if>
	<c:if test="${bean.id!=null}">
	<font style="float:left"> 当前位置：礼品管理-->修改礼品</font>
	</c:if>
</div>
<form  id="from1" action="/gift/saveGift" method="post">
<input type="hidden" id="id" name="id" value="${bean.id}">
	<table class="table_style table table-bordered" >
<tr>
		<td style="width: 80px">礼品标题：&nbsp;</td>
		<td style="width: 229px">
					<input placeholder='限定50个字' maxlength="50" type="text" name="giftTitle" value="${bean.giftTitle }" sta="p" staName="礼品标题" ><font color="red">*</font>
		</td>
		<td style="width: 80px">礼品分类：</td>
		<td>
		  <select sta="p" staName="礼品分类" name=giftClass style="width:220px;height: 35px ">
			<option value="">请选择</option>
				<c:forEach items="${giftClass}" var="c">
					<option <c:if test="${c.key==bean.giftClass}">selected="selected"</c:if>  value="${c.key}">${ c.value.name}</option>
				</c:forEach>
			</select><font color="red">*</font>
			</td>
</tr>
		<tr>
		<td class="ti">来源：&nbsp;</td>
		<td>
		  <select sta="p" staName="礼品来源" name=giftSource style="width:220px;height: 35px ">
			<option value="">请选择</option>
				<c:forEach items="${giftSource}" var="c">
					<option <c:if test="${c.key==bean.giftSource}">selected="selected"</c:if>  value="${c.key}">${ c.value.name}</option>
				</c:forEach>
			</select><font color="red">*</font>
			</td>
			<td class="ti">兑换方式:</td>
			<td>
					<select sta="p" staName="兑换方式"  name="exchangeType" style="width:220px;height: 35px ">
					<option value="">请选择</option>
						<c:forEach items="${exchangeType}" var="ct">
							<option <c:if test="${bean.exchangeType ==ct.key}">selected="select"</c:if> value="${ct.key }">${ct.value.name}</option>
						</c:forEach>
					</select><font color="red">*</font>
			</td>
		</tr>
		<tr>
			<td class="ti">礼品个数:</td>
			<td>
			<input  maxlength="4" sta="p" staName="礼品个数" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " type="text"  name="number" value='${bean.number }'  staName="礼品个数"><font color="red">*</font>
			</td>
			<td>兑换礼品需要金币：</td>
			<td><input  onkeyup="this.value=this.value.replace(/[^\d]/g,'') "   sta="p"  id="key" staName="兑换礼品需要金币" type="text" name="exchangeNumber" maxlength="4" value="${bean.exchangeNumber }"><font  id="font1" color="red">*</font></td>
		</tr>
		 <tr>
				<td>图片：</td>
				<td>
				<input  sta="p" staName="图片标题" type="text"placeholder="图片标题（50字）" name="imgTitle" value="${bean.imgTitle }" maxlength="25"><font color="red">*</font>
				<input sta="p" staName="点击跳转的url" type="text" placeholder="点击图片跳转的url(例：http://bj.tuacnhe.com)" name="imgTargetUrl" value="${bean.imgTargetUrl }" maxlength="100"><font color="red">*</font>
				<c:choose>
          	         <c:when test="${empty bean.imgUrl}">
          	            <img  id="oneImage" style="width: 100px;height: 60px" src='/images/upload.jpg' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">*支持.jpg</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage" style="width: 80px;height: 80px" onclick="$('#listPicFile').trigger('click')" src="http://pic.tuanche.com/recpic/${bean.imgUrl}"/><font color="red">*</font>
          	         </c:otherwise>
          	     </c:choose>
          	     <input sta="p" staName="礼品图片" type="hidden" name=imgUrl id="logo" value="${bean.imgUrl }">
          	       <div style="display: none">
					           <input  id="listPicFile" name="listPicFile" type="file"  onchange="sitesUpload()"/>
				            	<input  id="delSrc"type="hidden" >
				            </div>
          	    </td>
          	    <td>礼品描述：</td>
          	    <td><textarea sta="p"  staName="礼品描述" id="replyContent" name="giftDesc" style="width: 450px" rows="3" cols="31" maxlength="500">${bean.giftDesc}</textarea><font color="red">*(500字)</font></td>
			</tr>
		<tr>
			<td>展示时间：</td>
		<td>
		<input sta="p" staName="展示时间" readonly="readonly" id="d11" name="showStartTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${ bean.showStartTime}"/> -<input sta="p" staName="展示时间"  readonly="readonly" id="d11" name="showEndTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${ bean.showEndTime}"/><font color="red">*</font> 
		</td>
		<td>状态：</td>
		<td>
			<select name="giftStatus">
				<option <c:if test="${bean.giftStatus==0 }">selected='selected'</c:if> value="0">不上线</option>
				<option <c:if test="${bean.giftStatus==1 }">selected='selected'</c:if> value="1">上线</option>
			</select>
		</td>
		</tr>
	</table>
	<div>
	<c:if test="${bean.id!=null }">
		<input type="button"  value="修改" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">	
	</c:if>
	<c:if test="${bean.id==null }">
		<input type="button"  value="保存" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">	
	</c:if>
	</div>
	 </form>
</body>
</html>