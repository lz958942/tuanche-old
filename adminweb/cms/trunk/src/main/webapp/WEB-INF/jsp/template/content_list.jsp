<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/common/import.jsp" %>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery.tipsy.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
<link href="/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
 <!-- ztree ue-->

 
  <script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
    
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/template/content.js"></script>

<link type="text/css" rel="stylesheet" href="/css/divZ_Index.css" />

<script type="text/ecmascript" src="/js/upload.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
 
  <script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
  
  
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<script type="text/javascript" src="/js/template/tlcity.js"></script>

<script type="text/javascript">
function showDiv(dir){
	//板块信息
	var type = $("#infotype").val();
	var dataType = $("#infodataType").val();
	if(type==3||type==4){//不显示图片
		$("#popDiv2").find("#img_type").html("");
	}
	if(dataType != 4){//不显示团长
		$("#popDiv2").find("#groupLeader").html("");
	}
	if(dataType != 5){//不显示车型
		$("#popDiv2").find("#carStyle").html("");
	}
	$("#popDiv").html($("#popDiv2").html());
	if(dir=="update"){//修改
		$("#contentForm").attr("action","/content/update");
		var temId = $("#contentList").find(".selected").attr("rel");
		if(!temId){
			alert("请选择内容！");
			return;
		}
		$.ajax({
			type : "POST",
			async : false,
			url : "/content/toUpdate" ,
			data:{'contentId':temId},
			dataType : "text",
			success : function(data) {
				data = eval("("+data+")");
				$("#popDiv").find("#contentId").val(data.id);
				$("#popDiv").find("#contentTitle").val(data.title);
				$("#popDiv").find("#contentDesc").val(data.descriction);
				$("#popDiv").find("#imagUrl").val(data.imagUrl);
				
				$("#popDiv").find("#groupLeaderId").val(data.groupLeaderId);
				$("#popDiv").find("#expand").val(data.expand);
				$("#popDiv").find("#styleId").val(data.carTypeId);
				$("#popDiv").find("#selectCarStyle").val(data.carTypeId);
				$("#popDiv").find("#contentPlateId").val(data.plateId);
				if(data.imagUrl != null && data.imagUrl.trim()!=''){
					$("#popDiv").find("#imageShow").attr("src",data.imagUrl);
				}
				$("#popDiv").find("#contentHyper").val(data.hyperlink);
			}
		});
	}else{
		$("#popDiv").find("#contentPlateId").val($("#plateId").val());
	}
	$("#popDiv").css("display","");
}
function closeDiv(){
	document.getElementById('popDiv').style.display='none';
	document.getElementById('bg').style.display='none';
}
var imgServicePath = '${imgServicePath}';
</script>
</head>
<body>
<form action="/content/toList" target="manFrame" name="content">
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<div >
		<!-- 板块信息 -->
		<input type="hidden" id="infotype" value="${plate.type}"/>
		<input type="hidden" id="infodataType" value="${plate.dataType}">
		<input type="hidden" name="plateId" id="plateId" value="${plate.id }"/>
		<font size="20px">
		板块城市：${plate.cityName }  板块名称：${plate.plateName } 标签名称：${plate.label}
		</font>
	</div>
</div>
<div class="panelBar">
<ul class="toolBar">
		<li class=""><a class="add" href="javascript:showDiv('add')" target="manFrame" width="800" height="550"> <span>添加内容</span> </a></li>
		<li class="line">line</li>
		<li><a class="edit" href="javascript:showDiv('update')" target="manFrame"  width="800" height="550" mask="true"><span>修改</span></a></li>
		<li class="line">line</li>
		<li><a class="" ><span></span></a></li>
		<li><a class="" ><span></span></a></li>
		<li><a class="delete deleteContent" ><span>删除</span></a></li>
	</ul>
</div>
<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid; OVERFLOW: auto;">
		<div layoutH="70" style="OVERFLOW: auto;height: 430px">
	<table class="list" width="100%">
		<thead>
			<tr align="center">
				<th>序号</th>
				<th>图片</th>
				<th>内容标题</th>
				<th>描述</th>
				<th>排序</th>
			</tr>
			</thead>
			<tbody id="contentList">
			<c:forEach items="${contentList }" var="item" varStatus="var">
				<tr align="center" target="sid_obj"  rel="${item.id}" class='trColor<c:if test="${var.index%2==0}"> trbg</c:if>'>
					<td>${var.index + 1}</td>
					<td><img alt="" src="${item.imagUrl}" width="80px"></td>
					<td>${item.title}</td>
					<td>${item.descriction}</td>
					<td>
						<a class="upSort" rel="${item.id}">↑上移</a>      
						<a class="downSort" rel="${item.id}">↓下移</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	
	<%@include file="/WEB-INF/jsp/common/page.jsp"%>
	
</div>
</form>
<div id="popDiv" class="mydiv" style="display:none;">

</div>
<div id="bg" class="bg" style="display:none;"></div>


<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>
<div id="menuContentselectCarStyle" style="display:none; position: absolute;z-index: 99" >
	<ul id="treeselectCarStyle" class="ztree"  ></ul>
</div>
</body>
<div id="popDiv2" style="display:none;">
	<%@include file="/WEB-INF/jsp/template/content_new.jsp" %>
</div>

</html>