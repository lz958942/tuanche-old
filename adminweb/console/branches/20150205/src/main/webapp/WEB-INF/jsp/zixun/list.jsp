<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/pagetag.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网资讯分类系统</title>
<script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<script type="text/javascript" charset="UTF-8">
var settingclass = {
		async: {
			enable: true,
			url:"/local/ajax/class"
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: classbeforeClick,
			onClick: classonClick
		}
	};


function classbeforeClick(treeId, treeNode) {
var check = (treeNode && !treeNode.isParent);
return true;
}

function classonClick(e, treeId, treeNode) {
var zTree = $.fn.zTree.getZTreeObj("treeClass"),
nodes = zTree.getSelectedNodes(),
v = "";
var id="";
nodes.sort(function compare(a,b){return a.id-b.id;});
for (var i=0, l=nodes.length; i<l; i++) {
	v += nodes[i].name + ",";
	id+= nodes[i].id +",";
}
if (v.length > 0 ) v = v.substring(0, v.length-1);
var cityObj = $("#parent");
id=id.substring(0,id.length-1);
$("#pid").val(id);
cityObj.attr("value", v);
classhideMenu();
}

function classshowMenu() {
	var cityObj = $("#parent");
	var cityOffset = $("#parent").offset();
	$("#menuContentClass").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", classonBodyDown);
}

function classhideMenu() {
$("#menuContentClass").fadeOut("fast");
$("body").unbind("mousedown", classonBodyDown);
}

function classonBodyDown(event) {
if (!(event.target.id == "menuContentClass" || $(event.target).parents("#menuContentClass").length>0)) {
	classhideMenu();
}
}


$(document).ready(function(){
			$.fn.zTree.init($("#treeClass"), settingclass);
});

function search(){
	$("#idid").val(null);
	$("#listForm").attr("action","/zixunclass/search");
	$("#listForm").submit();
}

function save(){
	if($("#title").val()=="" || $("#keyword").val()=="" || $("#name").val()==""||$("#online").val()==-1||$("#cityAttr").val()==-1){
		$("#msg").html('请完善信息！');
		return;
	}
	$("#listForm").attr("action","/zixunclass/save");
	//alert($("#listForm").serialize())
	$("#listForm").submit();
}

</script>
</head>
<body>
<form id="listForm" action="${action}" >
<br>
<div class="b-con a-form">
<div class="pd5">
<label>名字
<input id='name' value="${class.name}" name='name' type='text'  style=width:150px />
<input id='idid' name='id' value="${class.id}" type='hidden'  />
</label>
<label>选择父目录
<input id='parent' readonly="readonly" onclick="classshowMenu(); return false;"  type='text'  style=width:150px value="${func:getZXClass(class.pId)}"  />
<input id='pid' name="pId" value="${class.pId==null?0:class.pId}"  type='hidden'  style=width:150px />
</label>
<label>url
<input id='url'  value="${class.url}"  name="url"  type='text'  style=width:150px />
</label>
<label>关键词
<input id='keyword'  value="${class.keyword}"  name="keyword"  type='text'  style=width:150px />
</label>
<label>标题
<input id='title'  value="${class.title}"  name="title"  type='text'  style=width:150px />
</label>
<label>上线状态
<select name="status" id="online">
<option value="-1" >--请选择--</option>
<option value="0" <c:if test="${class.status==0}"> selected="selected" </c:if> >未上线</option>
<option value="1" <c:if test="${class.status==1}"> selected="selected" </c:if> >已上线</option>
</select>
</label>
<label>是否关联城市
<select name="cityAttr" id="cityAttr">
<option value="-1" >--请选择--</option>
<option value="0" <c:if test="${class.cityAttr==0}"> selected="selected" </c:if> >否</option>
<option value="1" <c:if test="${class.cityAttr==1}"> selected="selected" </c:if>>是</option>
</select>
</label>
</div>
<div  align="center">
<input type="button" value="搜索"  class="btn" onclick="search()"/>
<input type="button" value="${(class.id==null||class.id==0)?'保存':'更新'}" onclick="save()" class="btn" />
<a href="/zixunclass/home">清除搜索条件</a>
</div>
<div align="center" id="msg" style=" color:red;">${msg}</div>
</div>

<table class="table_style table table-bordered" id="attrtable">
<thead>
<tr>
<td>ID</td><td>父ID</td><td>名字</td><td>父节点</td><td>级别</td><td>url</td>
<td>是否关联城市</td><td>上线状态</td><td>操作</td>
</tr>
</thead>

<tbody>
<c:forEach var="class" items="${classes}">
<tr class="attr">
<td style="display:none"><input type="hidden" value="${item.id}"></td>
<td>${class.id}</td>
<td>${class.pId}</td>
<td title="标题:${class.title}   关键词:${class.keyword}">${class.name}</td>
<td>${func:getZXClass(class.pId)}</td>
<td>${class.level}</td>
<td>${class.url}</td>
<td>${class.cityAttr==1?"是":"否"}</td>
<td>${class.status==1?"已上线":"未上线"}</td>
<td style="width:110px">

<c:if test="${class.status==0}">
	<a href="/zixunclass/updateStatus?id=${class.id}&status=1&p=${page.pageNo}" >上线</a>
	<a href="/zixunclass/updateStatus?id=${class.id}&status=-1&p=${page.pageNo}"  onclick="return confirm('确认删除？');">删除</a>
</c:if>
<c:if test="${class.status==1}">
	<a href="/zixunclass/updateStatus?id=${class.id}&status=0&p=${page.pageNo}" onclick="return confirm('确认下线？');">下线</a>
</c:if>
<a href="/zixunclass/search?id=${class.id}" >修改</a>

</td>
</tr>
</c:forEach>
</tbody>
</table>
<div class="table-page">
<jsp:include page="/WEB-INF/snippets/page.jsp" />
</div>
</form>
<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
</body>
</html>