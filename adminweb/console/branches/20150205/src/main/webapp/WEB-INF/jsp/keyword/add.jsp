<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
<%@ page import="java.util.Map,java.util.HashMap,com.tuanche.console.util.NameUtil,com.tuanche.console.util.GlobalConstants" %> 
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网关键词系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
<script type="text/javascript" src="/js/keyword/keyword.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
</head>
<body>
<form  action='/keyword/insert' id='addkeyword' method="POST">
<div id="man_zone">
<table class="table_style table table-bordered" id="addtable">
<tbody id="tbody_num1">
<tr>
<td rowspan="4" style="width: 5%;vertical-align: middle; text-align: center"><input id='check_num1' name='check_num1' type="checkbox"  class="checkItem"/></td>
<td>关键词</td>
<td>维度</td>
<td>品牌</td>
<td>车型</td>
<td>类型</td>
</tr>
<tr>
<!-- <td style="width: 20px;vertical-align: middle;"><input id='check_num1' name='check_num1' type="checkbox"  class="checkItem" onclick="setSelectAll()"/></td> -->
<td style="width: 100px;vertical-align: middle;"><input id='word_num1' name='word_num1' type='text' style=width:100px  class="regname {required:true}" /></td>
<td style="width: 100px;vertical-align: middle;">
<select id='kdId_num1' name='kdId_num1' class="w130 regname {required:true}" onchange="changeType(this.name)" style=width:100px >
<option value="">--请选择--</option>
</select>
</td>
<td style="width: 100px;vertical-align: middle;">
<select id="brandId_num1" name="brandId" onchange="changeBrand(this.id)">
	<option value="">--请选择--</option>
	<c:if test="${brandList!=null&&brandList.size()>0 }">
		<c:forEach items="${brandList }" var="brandList">
			<option value="${brandList.id }">${brandList.orderName} ${brandList.name }</option>
		</c:forEach>
	</c:if>
</select>
</td>
<td style="width: 100px;vertical-align: middle;">
<select id="carStyleId_num1" name="carStyleId">
	<option value="">--请选择--</option>
	<c:if test="${carStyleList!=null&&carStyleList.size()>0 }">
		<c:forEach items="${carStyleList }" var="carStyleList">
			<option value="${carStyleList.id }" ${keyword.carStyleId==carStyleList.id?'selected':'' }>${carStyleList.carStyleName }</option>
		</c:forEach>
	</c:if>
</select>
</td>
<td style="width: 100px;vertical-align: middle;">
	<select id='type_num1' name='type_num1' class="w130 regname {required:true}" onchange="changeType(this.name)" style=width:100px >
		<option value="">--请选择--</option>
		<c:forEach var="item" items="${typemap}">
			<option value="${item.key}">${item.value}</option>
		</c:forEach>
	</select>
</td>
<tr>
	<td>级别</td>
	<td>上一级</td>
	<td>城市</td>
	<td nowrap="nowrap">百度指数/排名</td>
	<td>描述</td>
</tr>
<tr>
	<td style="width: 100px;vertical-align: middle;">
		<select id='level_num1' name='level_num1' class="w130 regname {required:true} " onchange="changelevel(this.options[this.options.selectedIndex].value,this.name)" style=width:100px >
			<option value="">--请选择--</option>
			<option value="2">二级</option>
			<option value="3">三级</option>
		</select>
	</td>
	<td style="width: 100px;vertical-align: middle;">
		<select id='pid_num1' name='pid_num1' class="w130 regname" style=width:100px >
		<option value="">--请选择--</option>
		</select>
	</td>
	<td style="width: 100px;vertical-align: middle;">
		<select id='city_num1' name='city_num1' class="w130 regname " style=width:100px > 
			<option value="">--请选择--</option>
			<c:forEach var="item" items="${citymap}">
				<option value="${item.key}">${item.value}</option>
			</c:forEach>
		</select>
	</td>
	<td style="width: 100px;vertical-align: middle;" >
		<input id='baiduIndex_num1' name='baiduIndex_num1'  type='text' style=width:24px class="regname {digits:true}"/><span style="font-size: 20px;vertical-align: middle;">/</span><input id='rank_num1' name='rank_num1' type='text' style=width:24px class="regname {digits:true}"/>
	</td>
	<td style="width: 100px;vertical-align: middle;">
		<textarea rows="2" id="describe_num1" name="describe_num1" class="regname {maxlength:200}"></textarea>
	</td>
</tr>
</tbody>
</table>
<div>
<table style="float:left;width:200px;margin:5px">
<tr>
<td><input type="button" class="btn" value="添加项" id="add" onclick="addAtr()" /></td>
<td><input type="button" class="btn" value="删除项" id="delete" onclick="deleteAtr()" /></td>
<td><input type="button" class="btn" value="全选" id="selectBtn" onclick="selectAll()" /></td>
<td><input type="hidden"  value="1" name="num"  id="num"/></td>
<td><input type="hidden" id="status" value="1" /></td>
</tr>
</table>
</div>
</div>
<div align="center">
<input type="button" id="saveBtn" class="btn" value="保存" onclick="saveItems()"/>
<input type="button" id="onlineBtn" class="btn" value="上线"  onclick="onlineItems()"/>
<input type="hidden" id="online" name="online" value=""/>
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
<script>

var retainStr;
$(function() {
    /* $("#checkAll").click(function() {
         $(".checkItem").attr("checked",this.checked); 
     }); */
    retainStr=$("#addtable").html();
    selectText(1);
 });

function selectText(num){
	$.ajax({
		
		url:"/keyword/selectDim",
		type:'post',
		dataType:'json',
		success:function(data){
			var list=data.dimList;
			var listLength=list.length;
			for(var x=0;x<listLength;x++){
				$("#kdId_num"+num).append('<option value="'+list[x].id+'">'+list[x].dieName+'</option>');
			}
		}
		
	});
}
function addAtr(){
	var lineNum=$("#num").val();
	lineNum=parseInt(lineNum)+1;
	selectText(lineNum);
	$("#num").val(lineNum);
	var rg=new RegExp("_num1","g");
	var addHtml=retainStr.replace(rg,"_num"+lineNum);
	$("#addtable").append(addHtml);
	$("#saveBtn").attr("disabled",false);
	$("#onlineBtn").attr("disabled",false);
	$("#selectBtn").show();
}
function deleteAtr(){
	if($("tbody input:checkbox:checked").length>0){
		if(confirm("删除之后将不会恢复，确认删除吗？")){
			$("tbody tr td input:checkbox:checked").parent().parent().parent().remove();
			var lineNum=$("#num").val();
			linNum=parseInt(lineNum)-1;
			$("#num").val(linNum);
		}
		if($("tbody[id*=tbody]").length<1){
			$("#saveBtn").attr("disabled",true);
			$("#onlineBtn").attr("disabled",true);
			$("#selectBtn").hide();
		}
	}
	else{
		alert("请选择需要删除的记录！");
	}
}

function selectAll(){
	var status=$("#status").val();
	if("1"==status){
		$(".checkItem").attr("checked",true); 
		$("#selectBtn").val("取消");
		$("#status").val("-1");
	}else{
		$(".checkItem").attr("checked",false); 
		$("#selectBtn").val("全选");
		$("#status").val("1");
	}
	
}

function changeType(typeName){
	var num=typeName.substring(typeName.length-1,typeName.length);
	var name="#level_num"+num;
	var level=$(name).val();
	changelevel(level,name);
}


function changelevel(level,name){
	var num=name.substring(name.length-1,name.length);
	var pid="#pid_num"+num;
	var options=null;
	var plevel=level-1;
	var typee="#type_num"+num;
	var typeValue=$(typee).val();
	if(typeValue!=1){
		options+='<option value="-1">--请选择--</option>';
		$(pid).html("");
		$(pid).append(options);
		return;
	}
	if(level=='2'){
		options+='<option value="-1">--请选择--</option>';
		options+='<option value="1">买车/购车</option>';
		//$(pid).empty();
		$(pid).html("");
		$(pid).append(options);
	}else if(level=='3'){
		$.ajax({
			 type: "POST",
			 url: "/keyword/exgetbylevel",                       
	         cache: false,
	         async: false,
	         dataType: "json",//返回的数据类型  
	         data: {"plevel":"2",format:"json"},
	         success: function (data){
	        	var datas=data.infos;
	        	var options='<option value="-1">--请选择--</option>';
	        	if(datas!=null){
	        		for(var i=0;i<datas.length;i++){
	        			options+='<option value='+datas[i].id+'>'+datas[i].word+'</option>';
	        		}
	        	}
	        	//$(pid).empty(); 
	        	$(pid).html("");
	        	$(pid).append(options);
	         },
	         error: function (){
	       	 alert('返回数据失败');
	         }
	    });
	}
}

function saveItems(){
	if(check()){
		$("#online").val('0');
		$("#addkeyword").submit();
	}
}
function onlineItems(){
	if(check()){
		$("#online").val('1');
		$("#addkeyword").submit();
	}
}

function check(){
	return true;
}

$("#addkeyword").validate({
    //信息存放位置
    errorPlacement:function(error, element) {
       error.appendTo(element.parent());
    },
    //包裹信息标签
    wrapper: "span",
    //验证通过执行
    submitHandler:function(form){
    	$("#addkeyword .regname").each(function(){
    		$(this).attr('name',$(this).attr('name').split("_")[0]);				    		
    	});   	
   		form.submit();  	 
    }
});

</script>

</body>

</html>