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
<form  action='/keyword/updateSave' id='addkeyword' method="POST">
<input type="hidden" id='wordid' name='wordid' value="${keyword.id}"/>
<input type="hidden" id="online" name="online" value="${keyword.online}"/>
<div id="man_zone">
<table class="table_style table table-bordered" >

<tr>
<td>类型</td>
<td>
<select id='type_num1' name='type_num1' class="w130 regname {required:true}" style=width:150px >
<c:forEach var="item" items="${typemap}">
<option value="${item.key}" ${keyword.type==item.key?'selected':''}>${item.value}</option>
</c:forEach>
</select>
</td>
<td>维度</td>
<td>
<select id='kdId_num1' name='kdId_num1' class="regname {required:true}" style=width:150px >
<c:forEach var="dim" items="${dimList}">
<option value="${dim.id}" ${keyword.kdId==dim.id?'selected':''}>${dim.dieName}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>关键词</td>
<td><input id='word_num1' name='word_num1' type='text' style=width:200px  class="regname {required:true}" 
value="${keyword.word}" /></td>
<td>级别</td>
<td>
<select id='level_num1' name='level_num1' class="w130 regname {required:true}"
onchange="changelevel(this.options[this.options.selectedIndex].value,this.name)" 
style=width:150px >
<option value="2" ${keyword.level=='2'?'selected':''}>二级</option>
<option value="3" ${keyword.level=='3'?'selected':''}>三级</option>
</select>
</td>
</tr>
<tr>
<td>百度排名</td>
<td>
<input id='rank_num1' name='rank_num1' type='text' style=width:140px class="regname {digits:true}" value='${keyword.rank==0?"":keyword.rank}'/>
</td>
<td>城市</td>
<td>
<select id='city_num1' name='city_num1' class="w130 regname" style=width:150px > 
<option value="">--请选择--</option>
<c:forEach var="item" items="${citymap}">
<option value="${item.key}" ${keyword.cityId==item.key?'selected':''}>${item.value}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td width="40px">百度指数</td>
<td>
<input id='baiduIndex_num1' name='baiduIndex_num1'  type='text' style=width:140px class="regname {digits:true}"  value='${keyword.baiduIndex==0?"":keyword.baiduIndex}'/>
</td>
<td>上一级</td>
<td>
<input type="hidden" id="pidd" name="pidd" value="${keyword.pid}">
<select id='pid_num1' name='pid_num1' class="w130 regname" style=width:150px >
<option value="">--请选择--</option>
</select>
</td>
</tr>
<tr>
<td>描述</td>
<td>
<textarea rows="2" id="describe_num1" name="describe_num1" class="regname {maxlength:200}">${keyword.describe}</textarea>
</td>
<td>品牌/车型</td>
<td>
<div style="display: inline;">品牌
<select id="brandId" name="brandId" class="w130" style="width:120px;" onchange="changeBrand(this.id);">
	<option value="">--请选择--</option>
	<c:if test="${brandList!=null&&brandList.size()>0 }">
		<c:forEach items="${brandList }" var="brandList">
			<option value="${brandList.id }" ${keyword.brandId==brandList.id?'selected':'' }>${brandList.orderName} ${brandList.name }</option>
		</c:forEach>
	</c:if>
</select>
</div>
<div style="display: inline;">车型
<select id="carStyleId" class="w130" name="carStyleId"  style="width:120px;" >
	<option value="">--请选择--</option>
	<c:if test="${carStyleList!=null&&carStyleList.size()>0 }">
		<c:forEach items="${carStyleList }" var="carStyleList">
			<option value="${carStyleList.id }" ${keyword.carStyleId==carStyleList.id?'selected':'' }>${carStyleList.carStyleName }</option>
		</c:forEach>
	</c:if>
</select>
</div>
</td>
</tr>
</table>
<div>
</div>
</div>

<div align="center">
<input type="button" class="btn" value="保存" onclick="saveItems()"/>
<input type="button"  class="btn" value="上线"  onclick="onlineItems()"/>
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

$(function(){
	var level=$("#level_num1").val();
	var options=null;
	var ppid=null;
	if(level==2){
		options+='<option value="-1">--请选择--</option>';
		options+='<option value="1">买车/购车</option>';
		$("#pid_num1").html("");
		$("#pid_num1").append(options);
		ppid=$("#pidd").val();
		$("#pid_num1").val(ppid);
	}if(level==3){
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
	        	$("#pid_num1").html("");
	        	$("#pid_num1").append(options);
	         },
	         error: function (){
	       	 alert('返回数据失败');
	         }
	    });
		ppid=$("#pidd").val();
		$("#pid_num1").val(ppid);
	}
 });


function changelevel(level,name){
	var num=name.substring(name.length-1,name.length);
	var pid="#pid_num"+num;
	var options=null;
	var plevel=level-1;
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
    wrapper: "li",
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