<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Console图片管理</title>
<style type="text/css">
body{font-family:"Segoe UI", Frutiger,Tahoma,Helvetica,"Helvetica Neue", Arial, sans-serif;font-size:12px;}
#div2 table{border-collapse:collapse;width: 100%;height: 100%}
#div2 td, th{text-align:center;border:1px solid #ddd;padding:2px 5px;}
#div2 td, th{font-size:12px;padding:2px;}
#div2 th{background-color:#f4f4f4;} 
#div2 table{float:left;margin:5px 0 0;}
.blue {
        background:#bcd4ec;  
}
.box h2{height:25px;font-size:14px;background-color:#3366cc;position:relative;padding-left:10px;line-height:25px;color:#fff;}
.box h2 a{position:absolute;right:5px;font-size:12px;color:#fff;}
</style>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#div2 tr[name=tbody]").hover(function() {
			$(this).addClass("blue");
		}, function() {
			$(this).removeClass("blue");
		});
	$("img[name=imgMy]").dblclick(function() {
		$("#boxId").val($(this).attr("id"));
		if($(this).attr("boxatlasId")!=null && $(this).attr("boxatlasId")!=""){
			$("#boxAtlasId").val($(this).attr("boxatlasId"));
		}
		if($(this).attr("boxTextarea")!=""){
			$("#boxTextarea").val($(this).attr("boxTextarea"));
		}
		if($(this).attr("boxInput")!=""){
			$("#boxInput").val($(this).attr("boxInput"));
		}
		if($(this).attr("boxScore")!=""){
		$("#boxScore").val($(this).attr("boxScore"));
		}
		if($(this).attr("boxImg")!=""){
			$("#boxImg").attr("src",$(this).attr("boxImg"));
		}
			 //$("#update_"+$(this).attr("id")).show(); 
			 $("div .box").show();
	});
	$("img[name=imgMy]").hover(function() {
		$("[sta=showdiv]").hide();
		$("#show_"+$(this).attr("id")).show();
	}, function() {
		$("[sta=showdiv]").hide();
	});
	var pid=$("#brind").val();
	if(pid!=null && ""!=pid && pid!=0){
		 $.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#muio option ").remove();
				 $("#muio").append("<option  selected='selected'  value=''>请选择车型</option>");
				   for(i in data) {
				   		if(data[i].id==$("#carid").val()){
				   			$("#muio").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	$(".close").click(function(){
		$(".box ").hide();
	});
	});
</script>
</head>
<body>
<input type="hidden" id="brind" value="${bean.brind }">
<input type="hidden" id="carid" value="${bean.carId }">
<form action="/pic/pichome" id="from1">
<div id="div1">
	<table>
		<tr>
		<td>编号：</td>
		<td><input sts='h' onkeyup="this.value=this.value.replace(/[^\d]/g,'') " style="width:220px;height: 30px" type="text" name="id" value="${bean.id }"></td>
		<td>图集</td>
			<td>
					<select sts='h'  name="collectionId" style="width:220px;height: 35px ">
					<option value="">请选择</option>
						<c:forEach items="${atlas}" var="ct">
							<option <c:if test="${bean.collectionId ==ct.id}">selected="select"</c:if> value="${ct.id }">${ct.name}</option>
						</c:forEach>
					</select>
					</td>
					<td>颜色：</td>
			<td>
			<input sts='h' style="width:220px;height: 30px" type="text" name="colourName" value="${bean.colourName}">
			</td>
		</tr>
		<tr>
		<td>品牌：</td>
			<td>
					<select sts='h'  id="brind" name="bid" style="width:220px;height: 35px " onchange="getStylemy(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option  <c:if test="${bean.bid ==b.id}">selected="select"</c:if> value="${b.id }">${b.typepinyI }-${b.name}</option>
						</c:forEach>
					</select>
			</td>
			<td>车型名称:</td>
			<td>
			<select sts='h' name="cid" id="muio" style="width:220px;height: 35px ">
					<option value="">请选择车型</option>
			</select>
			</td>
			<td>属性：</td>
			<td>
				<select sts='h' name="propertyid" style="width:220px;height: 35px ">
				<option value="">请选择</option>
					<c:forEach items="${propertyIds}" var="c">
							<option <c:if test="${c.code==bean.propertyid}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>分类：</td>
			<td>
				<select sts='h' name="classid" style="width:220px;height: 35px ">
				<option value="">请选择</option>
					<c:forEach items="${classIds}" var="c">
							<option <c:if test="${c.code==bean.classid}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option>
						</c:forEach>
				</select>
			</td>
			<td>
			</td>
		</tr>
	</table>
	<div>
	<input type="submit" value="搜索">
			<input type="button" value="添加车型图片" onclick="addPic(1)">
			<input type="button" value="添加其他图片" onclick="addPic(2)">
			<input type="button" value="修改图集" onclick="addPic(3)">
			<input type="button" value="清空条件" onclick="addPic(4)">
			</div>
</div>
<div id="div2">
	<table>
		<thead>
			<tr>
				<th>编号</th>
				<th>品牌</th>
				<th>车型</th>
				<th>车款</th>
				<th>属性</th>
				<th>分类</th>
				<th>图集</th>
				<th>图片标题—简介</th>
				<th>图片等级</th>
				<th>颜色</th>
				<th>图片</th>
				<th>访问url</th>
				<th>添加时间</th>
				<th>添加人</th>
				<th style="width: 24px">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${beans}" var="bean">
			<tr id="tr_${bean.id }" name="tbody">
				<td>${bean.id}</td>
				<td>${func:getBrandName(bean.bid)}</td>
				<td>${func:getallStyle(bean.cid)}</td>
				<td>${func:getcarStyleNameById(bean.carId) }</td>
				<td>${func:getPropertyOrClassByCode(bean.propertyid,1)}</td>
				<td>${func:getPropertyOrClassByCode(bean.classid,9)}</td>
				<td>${bean.collectionName}</td>
				<td>
				<c:if test="${bean.desc==null }">
					无
				</c:if>
				<c:if test="${bean.desc!=null }">
									<a href="javascript:void(0)" onclick="divshow(${bean.id})"> 详细</a>
									<div  sta="showdiv" id="oladiv_${bean.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
									<h3>${bean.picTitle==null?'无':bean.picTitle}</h3>
									${bean.desc}
									 <div style="text-align:right;">	
									<input type="button" value="关闭" onclick="nonediv(${bean.id})">
									</div>
									</div>
				</c:if>					
				</td>
				<td>${bean.score}</td>
				<td>
				${bean.colourName}
				</td>
				<td>
					<c:if test="${bean.url==null || bean.url=='' }">无</c:if>
					
					<c:if test="${bean.url!=null && bean.url!='' }"><img boxImg="${bean.surl }" boxatlasId="${bean.collectionId }" boxScore="${bean.score }" boxTextarea="${bean.desc }" boxInput="${bean.picTitle }" name="imgMy" id="${bean.id }" style="width:30px;height: 30px" src="${bean.surl }">
					<div  sta="showdiv"  id="show_${bean.id}" style="display:none;position:fixed;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;left: 184px; top: 72px;">
									<img style="width:400px;height: 450px" src="${bean.burl }">
						</div>
					</c:if>				
				</td>
				<td>${bean.visitUrl}</td>
				<td>${bean.createTimeX }</td>
				<td>${func:getEditName(bean.createUid)}</td>
				<td>
				<c:if test="${bean.bid==0}"><a href="/pic/operation.action?id=${bean.id}&type=4">操作</a></br></c:if>
				<c:if test="${bean.bid!=0}"> <a href="/pic/operation.action?id=${bean.id}&type=3">操作</a></br></c:if>
					<a href="javascript:void(0)" onclick="operation(${bean.id},-1,0)">删除</a>
					<c:if test="${bean.picCover==null || bean.picCover==0 }"><a href="javascript:void(0)" onclick="operation(${bean.id},9,${bean.collectionId})">封面</a></c:if>
<%-- 					<c:if test="${bean.picCover!=null && bean.picCover==1 }"><a href="javascript:void(0)" onclick="operation(${bean.id},10)">去除封面</a></c:if>
 --%>				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
				<div class="box"  id="update_${bean.id}" style="display:none;position:fixed;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 10px solid #3366cc;left: 292px; top: 72px;margin: 0;width: 350px;height:380px;">
				<h2>修改图片 <a href="#" class="close">关闭</a></h2>
				<font>图片标题：</font> <input maxlength="25" name="kevin" type="text" id="boxInput" name="boxInput" class="boxInput"></br>
				<font>图片描述：</font> <textarea maxlength="300" name="kevin"  style="width: 264px; height: 66px;" id="boxTextarea"></textarea></br>
				<font>图片：</font><img id="boxImg" style="width:150px;height: 150px" onclick="$('#boxFile').trigger('click')" > </br>
				<font>等级：</font><input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength='4' name="kevin" type="text" id="boxScore"></br>
				<font>图集：</font> 
				<select   id="boxAtlasId" style="width:220px;height: 35px ">
					<option value="">请选择</option>
						<c:forEach items="${atlas}" var="ct">
							<option value="${ct.id }">${ct.name}</option>
						</c:forEach>
					</select></br>
				<input type="button" value="修改" onclick="boxUpdate()">
				<div style="display:none;">
					<input  type="file" id="boxFile" name="boxFile" onchange="uploadFile()"/>
					<input name="kevin" type="hidden" id="boxId">
					<input  name="kevin" type="hidden" id="src">
					<input name="kevin" id="delSrc" type="hidden" id="delSrc">
				</div>
			</div>
	
	<c:if test="${beans!=null && beans.size()>1 }">
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
	</c:if>
</div>
</form>
<div  class="box" id="collDiv" style="display:none;position:fixed;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 10px solid #3366cc;left: 292px; top: 72px;margin: 0;width: 350px;height:380px;">
				<input type="hidden" id="collId">
				<h2>修改图集 <a href="#" class="close">关闭</a></h2>
				<font>图片图集：</font>
				<select id="select7" onchange="getColl(this.options[this.options.selectedIndex].value)">
				</select></br>
				<font>图片标题：</font> <input maxlength="25" type="text" id="colltitle"></br>
				<font>图片描述：</font> <textarea maxlength="300"  id="colldesc"   style="width: 264px; height: 66px;" id="boxTextarea"></textarea></br>
				<input type="button" value="修改" onclick="updateCollection()">
			</div>
<script type="text/javascript">
function getStylemy(pid){
	if(pid!=null &&　pid	!=""){
	 $("#muio option").remove();
	 $("#muio").append("<option selected='selected' value=''>请选择车型</option>");
	 publicAjax("/json/carstyle/ajaxStyle?","brandID",pid,'muio');
	}else{
		 $("#muio option").remove();
		 $("#muio").append("<option selected='selected' value=''>请选择车型</option>");
	}
	 
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
function operation(id,type,cid){
	if(id!=null && type!=null){
		if(type==-1){
			if(window.confirm('你确定要删除该记录！')){
				$.post("/pic/operation",{id:id,type:type},function(result){
					var sta=$.trim(result);
					if(sta!=null){
					alert("删除成功！");
					  if(sta%4==0 || sta==11){
						$("#from1").submit();	
					}else{  
						$("#tr_"+id).remove();	
					}
				}
				},'json');	
			}	
		}else if(type==9){
			if(window.confirm('你确定要添加封面！')){
				window.location.href="/pic/savePic.do?collid="+cid+"&id="+id;
			}
		}else{
		alert("操作无效！");
	}
}
}
function divshow(id){
	var $divs=$("div[sta=showdiv]").hide();
	$("#oladiv_"+id).show();
}
function nonediv(id){
	$("#oladiv_"+id).hide();
}
function boxUpdate(){
	var desc=$("#boxTextarea").val();
	var picTitle=$("#boxInput").val();
	var boxid=$("#boxId").val();
	var boxScore=$("#boxScore").val();
	var boxSrc=$("#src").val();
	var atlasId=$("#boxAtlasId").val();
	if(desc&&picTitle&&boxid&&boxScore&&atlasId){
		$.post("/pic/simple/update",{id:boxid,turl:boxSrc,score:boxScore,desc:desc,picTitle:picTitle,collectionId:atlasId},function(result){
			alert("修改成功！");
			$(".box").hide();
			$("[name=kevin]").val("");
			$("#from1").submit();
		},'json');
	}else{
		alert("操作无效！");
	}
}
function uploadFile(){
	$.ajaxFileUpload({
		url:"/json/uploadFile?delSrc="+$("#delSrc").val(),
		fileElementId:'boxFile',
		dataType: 'json',
		success: function (data){
			if(data==null){
			alert("上传失败！");
			return false;
			}
			var args=data.trim();
			$("#src").val(args);
			$("#boxImg").attr("src",args);
			var delSrc=args.substring(args.lastIndexOf("/"),args.length);
			if(delSrc!=null && delSrc!=""){
				$("#delSrc").val(delSrc);
			}
			alert("上传成功");
		},
	});
}

function getColl(id){
	if(id!=null && id!=""){
		$.post("/json/getCollById",{id:id},function(result){
			$("#colltitle").val(result.atlasTitle);
			$("#colldesc").text(result.atlasDesc);
			$("#collId").val(id);
		},'json');	
	}else{
		$("#colltitle").val("");
		$("#colldesc").text("");
	}
}
function updateCollection(){
	var collTitle=$("#colltitle").val();
	var collDesc=$("#colldesc").val();
	var collId=$("#collId").val();
	if(collTitle==null || collTitle==""|| collDesc==null || collDesc=="" || collId==null || collId==""){
		alert("操作无效！");
		return false;
	}
	$.post("/pic/updateCollection",{id:collId,atlasTitle:collTitle,atlasDesc:collDesc},function(result){
		alert("修改成功！");
		  $("#collDiv").hide();
		  $("#from1").submit();
	},'json');
}
function addPic(type){
	if(type==1){
		window.location.href="/pic/operation.action";
	}else if(type==2){
		window.location.href="/pic/operation.action?type=1";
	}else if(type==3){
		//获取图集
		$.post("/json/getColl",{id:type},function(result){
			 $("#select7 option").remove();
			$("#select7").append("<option value=''>请选择</option>");
			  for(i in result) {
				 $("#select7").append("<option value="+result[i].id+">"+result[i].name+"</option>");
			}
			  $("#collDiv").show();
		},'json');
	}else if(type==4){
		$("[sts=h]").val("");
	}
}
</script>
</body>
</html>