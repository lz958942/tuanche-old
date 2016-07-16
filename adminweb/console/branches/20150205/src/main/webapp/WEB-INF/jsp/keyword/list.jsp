<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=""  %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/pagetag.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网关键词系统</title>
<script type="text/javascript" src="/js/keyword/keyword.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>

<style type="text/css">
ul.ztree {margin-top:0px;border: 1px solid #617775;
			background: #f0f6e4;
			width:140px;
			height:280px;
			overflow-y:scroll;
			overflow-x:auto;
}
</style>
</head>
<body>
<form id="listForm" enctype="multipart/form-data" method="POST">
<br>
<table>
<tr>
<td><label>批量导入</label></td> 
<td><input type="file" name="file" id="file" /></td>
<td><input id="import" name="import" type="button" value="上传" class="btn" onclick="importSubmit()"/></td>
</tr>
</table>
<input type="hidden" name="returnStr" value="" id="returnStr"/>
<div class="b-con a-form">
<div class="pd5">
<label>关键字
<input id='word' name='word' type='text' value="${keyword.word}" style=width:100px />
</label>
<label>维度
<input id='dimension' name='dimName' type='text' value="${keyword.dimName}" style=width:100px />
</label>
<label>级别
<select id='level' name='level' class="w130 " 
style=width:100px >
<option value="-1" ${keyword.level=='-1'?'selected':''}>--请选择--</option>
<option value="2" ${keyword.level=='2'?'selected':''}>二级</option>
<option value="3" ${keyword.level=='3'?'selected':''}>三级</option>
</select>
</label>
<label>城市
<select id='city' name='city' class="w130" style=width:100px > 
<option value="-1">--请选择--</option>
<c:forEach var="item" items="${citymap}">
<option value="${item.key}" ${keyword.cityId==item.key?'selected':''}>${item.value}</option>
</c:forEach>
</select>
</label>
<label>品牌
<select id="brandId" name="brandId" class="w130" style="width:120px;" onchange="changeBrand(this.id);">
	<option value="">--请选择--</option>
	<c:if test="${brandList!=null&&brandList.size()>0 }">
		<c:forEach items="${brandList }" var="brandList">
			<option value="${brandList.id }" ${keyword.brandId==brandList.id?'selected':'' }>${brandList.orderName} ${brandList.name }</option>
		</c:forEach>
	</c:if>
</select>
</label>
<label>车型
<select id="carStyleId" class="w130" name="carStyleId"  style="width:120px;" >
	<option value="">--请选择--</option>
	<c:if test="${carStyleList!=null&&carStyleList.size()>0 }">
		<c:forEach items="${carStyleList }" var="carStyleList">
			<option value="${carStyleList.id }" ${keyword.carStyleId==carStyleList.id?'selected':'' }>${carStyleList.carStyleName }</option>
		</c:forEach>
	</c:if>
</select>
</label>
<label>类型
<select id='type' name='type' class="w130" style=width:100px >
<option value="">--请选择--</option>
<c:forEach var="item" items="${typemap}">
<option value="${item.key}" ${keyword.type==item.key?'selected':''}>${item.value}</option>
</c:forEach>
</select>
</label>
<label>上线状态
<select name="online" id="online">
<option value="-1" ${keyword.online=='-1'?'selected':''}>--请选择--</option>
<option value="0" ${keyword.online=='0'?'selected':''}>未上线</option>
<option value="1" ${keyword.online=='1'?'selected':''}>已上线</option>
</select>
</label>
&nbsp;&nbsp;&nbsp;
<label>收录情况
<select name="included" id="included">
<option value="-1" ${keyword.included=='-1'?'selected':''}>--请选择--</option>
<option value="0" ${keyword.included=='0'?'selected':''}>未收录</option>
<option value="1" ${keyword.included=='1'?'selected':''}>已收录</option>
</select>
</label>
&nbsp;&nbsp;&nbsp;
<label>排名 
<input type=text name="rank" id="rank" value="${keyword.rank=='-1'?'':keyword.rank}" style="width:50px" class=" {digits:true}" />
</label>
<label class="pr15">添加人：
<select class="w130" name='addEmployeeId'  id="addEmployeeId">
    <option value="-1">---请选择---</option>
    <c:forEach var="item" items="${users}"> 
<option value="${item.key}"  ${keyword.addUserId==item.key?'selected':''}>${item.value}</option>
</c:forEach>
</select>
</label>
<label class="pr15">添加时间：
      <span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly" value="${keyword.startDate}" class=" inptime span2" style="width:70px" name="startDate"  id="startDate">&nbsp;至&nbsp;
      <span class="add-on"><i class="icon-calendar"></i></span><input type="text" autocomplete="off" readonly="readonly" value="${keyword.endDate}" class="inptime span2" style="width:70px" name="endDate"  id="endDate">
</label>
&nbsp;&nbsp;&nbsp;
<!-- 
<label>UV
<input type=text name="uv" id="uv" value="${keyword.uv=='-1'?'':keyword.uv}" style="width:50px" class=" {digits:true}"  />
</label>
 -->
</div>
<div  align="center">
<input type="submit" value="查询" class="btn" />
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
</div>
</div>
<table class="table_style table table-bordered" id="attrtable">
<thead>
<tr>
<td>
<input type="checkbox" id="checkAll"/>
</td>
<td>ID</td><td>关键词</td><td>相关维度</td><td>URL</td><td>品牌车型</td><td>级别</td><td>城市</td><td>类型</td><td>父节点</td><td>百度指数/排名</td>
<td>上线状态</td><td>是否收录</td><td>添加时间</td><td>操作</td>
</tr>
</thead>

<tbody>
<c:forEach var="item" items="${list}">
<tr class="attr">
<td style="display:none"><input type="hidden" value="${item.id}" name="id"></td>
<td><input type="checkbox" class="checkItem"/></td>
<td>${item.id}</td>
<td>${item.word}</td>
<td>${item.dimName }</td>
<td>
<c:if test="${item.level==1}">
http://tuanche.com/cars/
</c:if>
<c:if test="${item.level==2}">
http://tuanche.com/cars-${item.id}/
</c:if>
<c:if test="${item.level==3}">
http://tuanche.com/cars/${item.id}/
</c:if>
</td>
<c:if test="${item.carStyleId==null||item.carStyleId==''}">
	<c:set var="valueStr" value="${item.brandId}" scope="request" ></c:set>
</c:if>
<c:if test="${item.carStyleId!=null&&item.carStyleId!=''}">
	<c:set var="valueStr" value="${item.brandId}-${item.carStyleId}" scope="request" ></c:set>
</c:if>
<td>${func:getStyleInfo(valueStr)}</td>
<td>${func:getLevel(item.level)}</td>
<td>${func:getCity(item.cityId)}</td>
<td>${func:getType(item.type)}</td>
<td>${func:getWord(item.pid)}</td>
<td width="80px"><input type="text" value="${item.baiduIndex=='0'?'':item.baiduIndex}" style="width:20px" class="{digits:true}" name="wordBaiduIndex" id="wordBaiduIndex"/><span style="font-size: 20px">/</span><input type="text" value="${item.rank=='0'?'':item.rank}" style="width:20px"  class="{digits:true}" name="wordRank" id="wordRank" /></td>
<td width="30px">${func:getOnline(item.online)}</td>
<td width="30px">${func:getIncluded(item.included)}</td>
<td style="display:none"><input type="hidden" value="${item.addUserId}"></td>
<!-- <td style=width:200px>${item.describe}</td> -->
<td>${item.addDate}</td>
<td style="width:80px">
<a href="/keyword/update?id=${item.id}">修改</a>
<a href="javascript:deleteItem(${item.id},${item.online},${item.addUserId});">删除</a>
</td>
</tr>
</c:forEach>


</tbody>
</table>
<table>
<tr>
<td><input type="button" value="收录" onclick="javascript:includedItems();" class="btn"/></td>
<td><input type="button" value="上线" onclick="javascript:onlineItems();" class="btn"/></td>
<td><input type="button" value="删除" onclick="javascript:deleteItems();" class="btn"/></td>
</tr>
</table>
<div class="table-page">
<page:page pager="${pb}"/>
每页
<select name="selectNum" onchange="pageNumChange()">
	<option value="20" <c:if test="${selectNum==20 }"> selected="selected"</c:if>>20</option>
	<option value="50" <c:if test="${selectNum==50 }"> selected="selected"</c:if>>50</option>
	<option value="100" <c:if test="${selectNum==100 }"> selected="selected"</c:if>>100</option>
	<option value="200" <c:if test="${selectNum==200 }"> selected="selected"</c:if>>200</option>
	<option value="500" <c:if test="${selectNum==500 }"> selected="selected"</c:if>>500</option>
</select>
条
<input type="hidden" name="isPageNumChange" value="0">
</div>
</form>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
<div id="menuContentselectCarStyle" style="display:none; position: absolute;" >
	<ul id="treeselectCarStyle" class="ztree"  ></ul>
</div>
<script type="text/javascript" charset="UTF-8">
var emId;
$(function() {
	<%--<%HttpSession sessions=request.getSession();
	int employeeId=(int)session.getAttribute("employeeId");
	%>
	emId=<%=employeeId%>;--%>
	emId=${sessionScope.employeeId};
	$(".attr").each(function(){
		if($(this).find("td:eq(14)").find("input").val()!=emId){
			$(this).find("td:eq(1)").find("input").hide();
			$(this).find("td:eq(11)").find("input").attr("disabled",true);
			$(this).find("td:eq(12)").find("input").attr("disabled",true);
			$(this).find("a:eq(0)").hide();
			$(this).find("a:eq(1)").hide();
		}
	});
    $("#checkAll").click(function() {
         $(".checkItem:visible").attr("checked",this.checked); 
     });
 });




function clearSearch(){	
	$("#online").val('-1');
	$("#included").val('-1');
	$("#rank").val('');
	$("#uv").val('');
	$("#city").val('-1');
	$("#level").val('');
	$("#word").val('');
	$("#type").val('-1');
	$("#startDate").val('');
	$("#endDate").val('');
	$("#dimension").val('');
	$("#brandId").val('');
	$("#carStyleId").val('');
	$("#addEmployeeId").val('-1');
	$("#selectCarStyle").val('');
}
function itemdeletecheck(){
	var strArray = new Array();
	var i=0;
	var del=0;
	var size=$("#attrtable tbody tr").size();
	var returnStr;
	$(".attr").each(function(){
		if(($(this).find("td:eq(1)").find("input").attr("checked")=="checked")){
			strArray[i]=$(this).find("td:eq(0)").find("input").val();
			i++;
			if($(this).find("td:eq(13)").html()=='是'){
				del=1;	
			}
		}
	});
	var returnStr=strArray[0];
	for(var j=1;j<strArray.length;j++){
		returnStr+=',';
		returnStr+=strArray[j];
	}
	if(strArray.length<=0){
		alert("请选择要操作的选项！");
		return false;
	}else if(del==1){
		alert("删除项中包含已上线条目，不允许删除！");
		return false;
	}else{	
		$("#returnStr").val(returnStr);
		return true;
		}
}
function itemcheck(){
	var strArray = new Array();
	var i=0;
	var j=0;
	var size=$("#attrtable tbody tr").size();
	var returnStr;
	$(".attr").each(function(){
		if(($(this).find("td:eq(1)").find("input").attr("checked")=="checked")){
			if($(this).find("td:eq(14)").find("input").val()!=emId){
				alert("项目中包含不是您添加的项，不允许操作！");
				j=1;
				return false;
			}
			strArray[i]=$(this).find("td:eq(0)").find("input").val();
			i++;

		}
	});
	var returnStr=strArray[0];
	for(var j=1;j<strArray.length;j++){
		returnStr+=',';
		returnStr+=strArray[j];
	}
	/* if(j==1){
		return false;
	}  */
	if(strArray.length<=0){
		alert("请选择要操作的选项！");
		return false;
	}else{	
		$("#returnStr").val(returnStr);
		return true;
		}
}
function includedItems(){
	if(itemcheck()){
		if(confirm("选中的信息将被收录，确认收录选中记录吗？")){
			$("#listForm").attr("action","/keyword/includedItems");
			$("#listForm").submit();
		}
	}else{
		return false;
	}	
}
function onlineItems(){
	if(itemcheck()){
		if(confirm("选中的信息将上线，确认相关记录上线吗？")){
			$("#listForm").attr("action","/keyword/onlineItems");
			$("#listForm").submit();
		}
	}
}

function importSubmit(){
	if($("#file").val()==''){
		alert("请选择上传的文件！");
		return false;
	}
	
	var str=$("#file").val();
	var pos=str.lastIndexOf(".");
	var lastname = str.substring(pos,str.length); 
	if(lastname!='.xls'){
		alert("文件格式不支持，请上传 xls 格式的文件");
		return false;
	}
	$("#listForm").attr("action","/keyword/excleBatchImport");
	$("#listForm").attr("method","post");
	$("#listForm").submit();
}
/*****
$("#listForm").validate({
    rules: {
    	"file": {
    		required: true,
    		accept: "xls"
    		}
    },
    messages: {
    	"file": {
    		required: "请选择上传文件",
    		accept: "文件格式不支持，请上传 xls 格式的文件"
    		}
    },   

    //验证通过执行
    submitHandler:function(form){
   		form.submit();  	 
    }
});
*****/
$("#listForm").validate({
    //信息存放位置
    errorPlacement: function(error, element){
       error.appendTo(element.parent());
    },
    //包裹信息标签
    wrapper: "li",
});


function deleteItem(id,online,employeeId){
	if(online=='1'){
		alert ("已上线，不允许删除！");
		//return false;
	}else if(employeeId!=emId){
		alert("此条记录不是您添加，您没有删除权限");
		//return;
	}else{
		if(confirm("选中的信息将被删除，确认删除记录吗？")){
			$("#listForm").attr("action","/keyword/deleteItems");
			$("#returnStr").val(id);
			$("#listForm").submit();
		}
	}
}


function deleteItems(){
	if(itemdeletecheck()){
		if(confirm("选中的信息将被删除，确认删除记录吗？")){
			$("#listForm").attr("action","/keyword/deleteItems");
			$("#listForm").submit();
		}
	}
}

function pageNumChange(){
	$("#pageNumChange").val(1);
	$("#listForm").submit();
}
</script>
</body>
</html>