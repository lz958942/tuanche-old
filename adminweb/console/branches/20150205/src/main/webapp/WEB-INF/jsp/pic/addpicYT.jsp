<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片添加</title>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<style type="text/css">
	.blue {
        background:#bcd4ec;  
}
</style>
<script type="text/javascript">
$(function(){
	$("#likeNametable tr").live("mouseover",function() {
	$(this).attr("style","background:#bcd4ec;");
	$(this).siblings().removeAttr("style");
});
/* $("#atlasId").live("blur",function() {
	 $("#likeName").hide();
	$("#likeNametable tr").remove();
}); */
})
</script>
</head>
<body>
	<!--其他 -->
	<div id="div2" >
	<font color="red">页面所有元素为必选！</font>
	<input type="hidden" id="statype">
	<form action="/pic/savePic" id="from2">
	<input type="hidden" name="names" id="names">
	<c:if test="${bean.id!=null && bean.id>0 }">
	<input type="hidden" name="id" value="${bean.id }"> 
	</c:if>
	<c:if test="${bean!=null && bean.id!=null}"><input name="colourId" type="hidden" value="${bean.colourId }"></c:if>
		<table>
			<tr>
			<td>分类:</td>
			<td>
				<select sta="p" staName="分类" name="classid" style="width:220px;height: 35px ">
				<option value="">请选择</option>
					<c:forEach items="${classIds}" var="c">
							<%-- <option <c:if test="${c.code==bean.classid}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option> --%>
							<option <c:if test="${c.code==8}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option>
						</c:forEach>
				</select>
			</td>
				<td>标题：</td>
				<td><input  sta="p" staName="标题" id="picTitle" type="text" name="picTitle" maxlength="30" value="${bean.picTitle  }"></td>
				<c:if test="${bean.id==null || bean.id==0 }">
				<td>图集名称：</td>
				<td><input onkeyup="getCollName(this.value)" sta="p" staName="图集名称"  maxlength="10" id="atlasId"  name="collectionName" type="text" value="${bean.colourName  }" ><font color="red"></font></td>
				</c:if>
			</tr>
			<c:if test="${bean.id==null || bean.id==0 }">
			<tr>
		<td>多图片：</td>
				<td><input  id="myZip2"  name="myZip2" type="file" onchange="zipUpload()" ><font color="red">*仅限.zip</font></td>
		</tr>
		</c:if>
		</table>
			描述：</br> 
		<textarea sta="p" staName="描述" id="desc" name="desc" style="width: 451px; height: 158px;"  maxlength="300" rows="" cols="">${bean.desc }</textarea></br>
		<c:if test="${bean.id==null || bean.id==0 }">
		<input id='submintButton' disabled="disabled" type="button" value="保存" onclick="submint1(2)">	<input   type="button" value="删除图片（批量）" onclick="delpic(0.1)"></br>
		</c:if>
		<c:if test="${bean.id!=null && bean.id>0 }">
		<input id='submintButton'  type="button" value="保存" onclick="submint1(2)"></br>
		</c:if>
		<font  id="showText" color="red"></font>
	<div id="divyt" name="divShow" style="display: none;">
		<table id="tablety">
		</table>
	</div>
	</form>
	<div>
	</div>
	</div>
	<div  id="hdiv" style="display:none; background-color:rgba(0,0,0,0.5);position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;   z-index:1001">
		</br>
		</br>
		</br>
		</br>
		</br>
		</br>
		</br>
		</br>
		</br>
		</br>
		</br>
		</br>
		<center><font style="padding:40px;">请稍后。。。</font></center>
	</div>
	<div id="likeName" style="background-color: rgb(255, 255, 255); box-shadow: 0px 0px 5px rgb(153, 153, 153); border: 1px solid rgb(249, 249, 249); position: fixed; margin: -261px; left: 911px; width: 215px; height: 126px;display: none;">
		<table id="likeNametable">
		</table>
	</div>
</body>
<script type="text/javascript">
function submint1(id){
		 var sta=true;
		/*  var type=$("#statype").val(); */
		 var type=1;
			$("[sta=p]").each(function(){
				if($(this).val()==null||$(this).val()==""){
					sta=false;
					alert($(this).attr("staName")+"不能为空！");
					return false;
					}
				});
		 	$("[sta=cover]").each(function(){
				if($(this).val()=="√"){
					type=1;
					}
				}); 
			if(type==1){
					if(sta){
						$("#from2").submit();
						}
					
				}else{
	type=0;
	alert("请选择封面！");
}
}
 function coverBox(id){
	 $("[sta=cover]").val("×").css("color","red");
	 $("#pic_"+id+" [sta=cover]").val("√").css("color","green");
	 $("#statype").val(1);
 }
 function zipUpload(){
	 var title=$("#picTitle").val();
		var desc=$("#desc").val();
		if(title==null || title=="" ||desc==null ||desc==""){
			alert("上传图片前请先填写标题以及描述！");
			return false;
		}
		var size=0;
		var d=new Date();
		var tmpYear=d.getFullYear();
		var tmpMonth=d.getMonth() + 1;
		var tmpDate=d.getDate();
		 if(tmpDate <= 9){
			 tmpDate="0"+ tmpDate;
		 }
		 var dar=tmpYear+""+tmpMonth+tmpDate
		var zip=$("#myZip2").val();
		var pos=zip.lastIndexOf(".");
		var lastname = zip.substring(pos,zip.length); 
		$("#myZip2").each(function(){
			if(this.files[0].size>31457280){
				size=this.files[0].size;
				return false;
			}
		});
		if(size > 31457280 ){
			alert("zip不能超过30M！");
			return false;
		}
		if(lastname!='.zip'){
			alert("文件格式不支持，请上传 zip格式的文件");
			return false;
		}
		$("#hdiv").show();
		//$("#tablety tr").remove();
		$.ajaxFileUpload({
			url:"/json/zipUpload?type=myZip2",
			fileElementId:'myZip2',
			dataType: 'text',
			success: function (data){
				if(data==null){
					alert("上传失败！");
					return false;
				}else{
					var submintType=$("#submintType").val();
					var divName="divyt";
					var fileName=data.split("-lgh-");
					if(fileName.length>1 ){
						var names=fileName[0].split(",");
						$("#names").val(fileName[0]);
						  for(var i=0;i <names.length;i++ ){
							var name=names[i].replace(".jpg","_s.jpg")
							 if(name!=null && name!=""){
								 var number=parseInt(i+100*Math.random());
								$("#divyt table").append("<tr picName="+"'"+name+"'"+"id='pic_"+number+"'><td><input boxNumber="+number+" value="+"'"+name+"'"+"  type='checkbox' name='imgCheckbox'></td><td><img style='width:100px;height:100px' src='/pic_tmp/piclibrary/"+dar+"/"+name+"'><td><td>标题：</td><td><input name='boxPicTitle' maxlength='50' type='input' value='"+title+"'></td><td>简介：</td><td><textarea maxlength='300' name='boxPicTitle'>"+desc+"</textarea></td><td><input type='hidden' name='boxPicTitle' value='"+name+"'><b><font>封面：</font></b><input style='height: 10px;width: 10px' type='text'  sta='cover' onclick='coverBox("+number+")' name='boxPicTitle' value='×'  color='red' ></td><td>等级：<input   maxlength='4' style='height: 20px;width: 20px' type='text' name='boxPicTitle' value='0' ></td><td><input type='button' value='删除' onclick='delpic("+number+")'"+"></td></tr>");	
								$("#divyt").show();
							} 
						}  
						  $("#hdiv").hide();
						  $("#showText").text("请选择封面，一个图集只允许有一个封面，若前边勾选封面，请不要重复勾选！");
						  $("#submintButton").removeAttr("disabled");
						var errorNames=fileName[1];
						if(errorNames!=null && errorNames!="\n"){
							var errorName=errorNames.split(",");
							alert("失败列表！"+errorName);
						}
					}
				}
			},

		});
	}
 function getCollName(collName){
		if(collName!=null && collName!=""){
			$.post("/json/getCollByName",{collName:collName},function(result){
				if(result!=null){
					$("#likeNametable tr").remove();
					for(var i=0;i<result.length;i++){
						$("#likeNametable").append("<tr style='width: 210' ><td  style='width: 210'> <font onclick='likeNameValue(this)'>"+result[i].name+"</font></td></tr>");
					}
					$("#likeName").show();
				}else{ $("#likeName").hide();}
			},'json');	
		}else{
			 $("#likeName").hide();
		}
	}
function likeNameValue(text){
	alert(1);
	 $("#atlasId").val($(text).text());
	 $("#likeName").hide();
}

function delpic(id){
	 if(id==0.1){
		 var names="";
		 var boxs=$("[name=imgCheckbox]:checked");
		 if(3>boxs.size()){
			alert("请选择要删除的图片！删除图片不少于3张！"); 
			return false;
		 }
		 for(var j=0;j < boxs.length; j++ ){
			 names+=boxs[j].value+",";
			 $("#pic_"+$(boxs[j]).attr("boxNumber")).remove();
			}
		 $.post("/json/ImgDel",{name:names},function(result){
			 if($("[name=imgCheckbox]").size()==0){
					$("#submintButton").attr("disabled","disabled");
				}
			},'json');
		 return false;
	 }
	var name= $("#pic_"+id).attr("picName");
	$.post("/json/ImgDel",{name:name},function(result){
		if($("[name=imgCheckbox]").size()==0){
			$("#submintButton").attr("disabled","disabled");
		}
	},'json');
	$("#pic_"+id).remove();
}
</script>
</html>