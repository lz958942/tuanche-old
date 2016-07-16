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
<form  action='/keyword/insertImport' id='addkeyword' method="POST">
<input type="hidden" name="returnStr" value="" id="returnStr"/>
<input type="hidden" value="1" id="pageType"/>
<table class="table_style table table-bordered" id="attrtable">
<thead>
<tr>
<td>
<input type="checkbox" id="checkAll"/>
</td>
<td>关键词</td><td>维度</td><td>品牌</td><td>车型</td><td>类型</td><td>级别</td><td>百度索引/排名</td><td>描述</td>
</tr>
</thead>

<tbody>
<c:forEach var="keyword" items="${list}" varStatus="s">
<tr class="attr">
<td style="display:none"><input type="hidden" value="${item.id}"></td>
<td><input type="checkbox" class="checkItem"/></td>
<td><input type="text" name="word" id="word" value="${keyword.word}"  class="{required:true}" style=width:100px /></td>
<td>
<select id="kdId${s['index']}" name='kdId' class="w130" class="{required:true}" style=width:100px >
<option value="">--请选择--</option>
<c:forEach var="dim" items="${dimList}">
<option value="${dim.id}" ${keyword.dimName==dim.dieName?'selected':''}>${dim.dieName}</option>
</c:forEach>
</select>
<font id="msg${s['index'] }" color="red" style="display: none">* 请添加维度</font>
</td>
<td>
<select id="brandId_num${s['index'] }" name="brandId" onchange="changeBrand(this.id)">
	<option value="">--请选择--</option>
	<c:if test="${brandList!=null&&brandList.size()>0 }">
		<c:forEach items="${brandList }" var="brandList">
			<option value="${brandList.id }" ${keyword.brandId==brandList.id?'selected':'' }>${brandList.orderName} ${brandList.name }</option>
		</c:forEach>
	</c:if>
</select>
</td>
<td>
<select id="carStyleId_num${s['index'] }" name="carStyleId">
	<option value="">--请选择--</option>
	<c:if test="${carStyleList!=null&&carStyleList.size()>0 }">
		<c:forEach items="${carStyleList }" var="carStyleList">
			<option value="${carStyleList.id }" ${keyword.carStyleId==carStyleList.id?'selected':'' }>${carStyleList.carStyleName }</option>
		</c:forEach>
	</c:if>
</select>
</td>
<td>
<select id='type' name='type' class="w130" class="{required:true}" style=width:100px >
<option value="">--请选择--</option>
<c:forEach var="item" items="${typemap}">
<option value="${item.key}" ${keyword.type==item.key?'selected':''}>${item.value}</option>
</c:forEach>
</select>
</td>
<td>
<select id='level' name='level' class="w130 " 
style=width:100px >
<option value="-1" ${keyword.level=='-1'?'selected':''}>--请选择--</option>
<option value="2" ${keyword.level=='2'?'selected':''}>二级</option>
<option value="3" ${keyword.level=='3'?'selected':''}>三级</option>
</select>
</td>
<td width="50px"><input type="text" name="baiduIndex" id="baiduIndex" value="${keyword.baiduIndex=='-1'?'':keyword.baiduIndex}" class="{digits:true}" style="width: 10px"/><span style="font-size: 20px">/</span><input type="text" name="rank" id="rank" value="${keyword.rank=='-1'?'':keyword.rank}" class="{digits:true}"  style=width:10px /></td>
<td><textarea rows="2" id="describe" name="describe" class="regname {maxlength:200}">${keyword.describe}</textarea></td>
</tr>
</c:forEach>
</tbody>
</table>

<table>
<tr>
<td><input type="submit" value="保存" class="btn"/></td>
<td><input type="button" value="删除" class="btn" onclick="deleteItem()"/></td>
</tr>
</table>
</form>
<script type="text/javascript" charset="UTF-8">
var flag=false;
$(function() {
    $("#checkAll").click(function() {
         $(".checkItem").attr("checked",this.checked); 
     });
    $("select[id*=kdId]").change(function(){
    	if($(this).val()!=''){
    		var selectId=$(this).attr("id");
    		selectId=selectId.substring(4,selectId.length);
    		$("#msg"+selectId).attr("style","display:none;");
    		flag=false;
    	}
    });
 });

function deleteItem(){
	if($("tbody input:checkbox:checked").length>0){
		if(confirm("删除之后将不会恢复，确认删除吗？")){
			$("tbody input:checkbox:checked").parent().parent().remove();
		}
	}
	else{
		alert("请选择需要删除的记录！");
	}
}

$("#addkeyword").validate({
    
    errorPlacement:function(error, element) {
       error.appendTo(element.parent());
    },
    //包裹信息标签
    wrapper: "span",
    //验证通过执行
    submitHandler:function(form){
       $("select[id*=kdId]").each(function(i){
 	    	if($(this).val()==''){
 	    		$("#msg"+i).attr("style","visible");
 	    		flag=true;
 	    	}
 	    });
    	if($(".attr").length<=0){
    		alert("没有数据，无法进行操作！");
    		return ;
    	}
    	if(flag){
    		alert("请先添加维度！");
    		return;
    	}
   		form.submit();  	 
    }
});

</script>
</body>
</html>