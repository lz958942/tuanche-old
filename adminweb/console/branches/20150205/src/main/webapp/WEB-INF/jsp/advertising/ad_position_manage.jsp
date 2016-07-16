<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=""  %>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网Console系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css" />
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<style type="text/css">.checkbox input{_width:auto;_float:left;}
	.blue {
        background:#bcd4ec;  
		}
.box h2{height:25px;font-size:14px;background-color:#3366cc;position:relative;padding-left:10px;line-height:25px;color:#fff;}
.box h2 a{position:absolute;right:5px;font-size:12px;color:#fff;}
</style>
<script type="text/javascript">
	$(function(){
		$("tr[name=tbody]").hover(function() {
			$(this).addClass("blue");
		}, function() {
			$(this).removeClass("blue");
		});	
		
		$(".close").click(function(){
			$(".box ").hide();
		});
	})
</script>
</head>
<body>
<form  method="post" action="/advertisingPosition/adContentPosition" id="form1">
<input type="hidden" name="type" value="${types }">
<div class="b-con a-form">
<div class="pd5">
<label>城市
<input  style="height: 30px" type="text" name="cityName" value="${adContentPosition.cityName }"/>
</label>
<label>频道:
<c:if test="${channelMap.size()>1 }">
<select  id="channel" name="channel">
<c:forEach items="${channelMap}" var="channel">
<option value="${channel.key}" <c:if test="${channel.key==adContentPosition.channel}">selected</c:if>>${channel.value.name}</option>
</c:forEach>
</select>
</c:if>
<c:if test="${channelMap.size()==1 }">
	<%-- <input type="hidden" name="channel" value="${channelMap.key }"> --%>
	<c:forEach items="${channelMap}" var="channel">
		<font>${channel.value.name }</font>
	</c:forEach>
</c:if>
</label>
<label>类别
<select id="adType" name="adType">
 <option value="-1">--请选择--</option>
<c:forEach items="${adTypeMap}" var="type">
<option value="${type.key}" <c:if test="${type.key==adContentPosition.adType}">selected</c:if>>${type.value}</option>
</c:forEach>
							
</select>
</label>
<label>名称
<input style="height: 30px" id='locationName' name='locationName' type='text' value="${adContentPosition.locationName}" style=width:100px />
</label>
<div  align="center">
<input type="submit" value="查询" class="btn" />
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
</div>
</div>

<div>
<table class="table_style table table-bordered">
						<thead>
							<tr align="center">
								<th>ID</th>
								<th style="width: 30px">内容ID</th>
								<th>组名</th>
								<th style="width: 30px">城市</th>
								<th style="width: 30px">频道</th>
								<th style="width: 30px">广告类别</th>
								<th>名称</th>
								<th>描述</th>
								<th>位置代码</th>
								<th>相框尺寸</th>
								<th>图片</th>
								<th style="width: 30px">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="position" varStatus="var">
								<tr name='tbody' align="center">
									<td>${position.contentPositionId}</td>
									<td>${position.adContentId}</td>
									<td>
									<c:if test="${position.groupName==null || position.groupName==''}">无</c:if>
									<c:if test="${position.groupName!=null && position.groupName!=''}">${position.groupName}</c:if>
									</td>
									<td>
									<c:if test="${position.cityId==0 }">全国</c:if>
									<c:if test="${position.cityId>0 }">${func:getCity(position.cityId)}</c:if>
									</td>
									<td>${func:getChannelName(position.channel)}</td>
									 <td>
									<c:if test="${position.adType==null || position.adType==0 }">无</c:if>
									 <c:if test="${position.adType==1 }">文字链接</c:if>
									 <c:if test="${position.adType==2 }">图片文字</c:if>
									 </td> 
									<td>${position.locationName}</td>
									<td>${position.describe}</td>
									<td>${position.locationCode}</td>
									<td>${position.height}x${position.width}</td>
									<td>
									<c:if test="${position.url==null || position==''}">
										<img onclick="showBox(${position.contentPositionId},${position.height },${position.width },'${position.url }')" style="height:40px;width: 40px" src="/images/selectPiuc.jpg">
									</c:if>
									<c:if test="${position.url!=null && position!=''}">
										<img  onclick="showBox(${position.contentPositionId},${position.width },${position.height },'${position.url }')" id="boxImg" onclick="$('#boxFile').trigger('click')"  style="height:40px;width: 40px" src="${position.url }">
									</c:if>
									</td>
									<td>
									<%-- <a href="/advertisingPosition/deleteContentPositionById?contentPositionId=${position.contentPositionId}&types=${types}" >删除</a>	 --%>
									<c:if test="${position.contentPositiontatus==null || position.contentPositiontatus==0 }">
										<a href="/advertisingPosition/deleteContentPositionById?contentPositionId=${position.contentPositionId}&types=${types}&sta=1" >上线</a>	
									</c:if>
									<c:if test="${position.contentPositiontatus!=null && position.contentPositiontatus==1 }">
										<a href="/advertisingPosition/deleteContentPositionById?contentPositionId=${position.contentPositionId}&types=${types}&sta=0" >下线</a>	
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
</div>
</div>
</div>
<div  class="box" id="collDiv" style="display:none;position:fixed;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;left: 292px; top: 72px;margin: 0;width: 350px;height:380px;">
				<h2>添加图片 <a href="#" class="close">关闭</a></h2>
				<font>图片宽：</font> <font id="boxw"></font></br>
				<font>图片高：</font>  <font id="boxh"></font></br>
				<font>标题：</font>  <input maxlength="25" style="height: 30px" sta="tar" type="text" id="picTitle"> </br>
				<font>描述：</font>  <textarea  maxlength="200" sta="tar" id="picDesc" style="width: 308px; height: 96px;"></textarea></br>
				<font>url：</font>  <input maxlength="100" style="height: 30px" sta="tar" type="text"  placeholder="例：http://bj.tuanche.com" id="targetUrl"></br>
				<font>图片：</font><img id="kevinimg" onclick="$('#boxFile').trigger('click')"  style="height:40px;width: 40px"></br>
				<input type="button" value="提交" onclick="updateImg()" >
			</div>
</form>
<div style="display: none;">	
	<input type="hidden" id="boxid">
	<input type="hidden" id="path">
	<input type="hidden" id="delSrc">
	<input type="file" id="boxFile"  name="boxFile" onchange="uploadFile1()">
</div>
<script>
function clearSearch(){
	$("#cityId").val('-1');
	$("#channel").val('-1');
	$("#adType").val('-1');
	$("#locationName").val('');
	$("[name=cityName]").val("");
}
function uploadFile1(){
	var h=	$("#boxh").text();
	var w=	$("#boxw").text();
	var del=$("#delSrc").val();
		$.ajaxFileUpload({
			url:"/json/ap/push?w="+w+"&h="+h+"&delSrc="+del,
			fileElementId:'boxFile',
			dataType: 'json',
			success: function (data){
				if(data==null){
				alert("上传失败！");
				return false;
				}
				var args=data.trim();
				$("#path").val(args);
				$("#kevinimg").attr("src",args);
				var delSrc=args.substring(args.lastIndexOf("/"),args.length);
				if(delSrc!=null && delSrc!=""){
					$("#delSrc").val(delSrc);
				}
				alert("上传成功");
			},
		});
	}
function showBox(id,h,w,url){
	if(id==null || h==null || w==null || id=="" || h=="" || w==""){
		alert("操作无效！");
		return false;
	}
	if(url==null || url==""){
		$("#kevinimg").attr("src","/images/selectPiuc.jpg");
	}else{
		$("#kevinimg").attr("src",url);
	}
	$.post("/json/ap/findByid",{id:id},function(result){
		if(result.picTitle!=null){
			$("#picTitle").val(result.picTitle);	
		}
		if(result.picDesc!=null){
			$("#picDesc").val(result.picDesc);
		}
		if(result.targetUrl!=null){
			$("#targetUrl").val(result.targetUrl);
		}
	},'json');
	
	$("#boxh").text(h);
	$("#boxw").text(w);
	$("#boxid").val(id);
	$("#collDiv").show()
}
function updateImg(){
	var id=$("#boxid").val(); 
	var path=$("#path").val();
	var picTitle=$("#picTitle").val();
	var picDesc=$("#picDesc").val();
	var targetUrl=$("#targetUrl").val();
	/* if(id==null || picTitle==null || targetUrl==null ||picDesc==null ||picTitle=="" ||targetUrl=="" ){
		alert("操作无效！");
		return false;
	} */
	$.post("/advertisingPosition/updateImg",{id:id,path:path,picTitle:picTitle,picDesc:picDesc,targetUrl:targetUrl},function(result){
		alert("修改成功！");
		$("[sta=tar]").val("");
		$("#form1").submit();
	},'json');
}
</script>
</body>
</html>