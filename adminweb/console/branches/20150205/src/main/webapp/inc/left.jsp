<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="flase" %>
<%@page import="com.tuanche.console.util.GlobalConstants"%>
<%@page import="com.tuanche.console.bean.Employee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="/css/common.css" type="text/css" />
<script type="text/javascript" src="/js/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/ztree.js" charset="UTF-8"></script>
<title>左侧导航栏
</title>
</head>
<script  type="text/javascript" charset="UTF-8">
/*加载新的导航*/
var curMenu = null, zTree_Menu = null;
var setting = {
	view: {
		showLine: false,
		showIcon: false,
		selectedMulti: false,
		dblClickExpand: false				
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		beforeExpand: beforeExpand,
		onExpand: onExpand,
		onClick: onClick
	}
};
// 保持单一展开路径
// 利用 setting.callback.beforeExpand / onExpand 事件回调函数实现展开规则
var curExpandNode = null;
function beforeExpand(treeId, treeNode) {
	var pNode = curExpandNode ? curExpandNode.getParentNode():null;
	var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
	var zTree = $.fn.zTree.getZTreeObj("treeMenu");
	for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
		if (treeNode !== treeNodeP.children[i]) {
			zTree.expandNode(treeNodeP.children[i], false);
		}
	}
	while (pNode) {
		if (pNode === treeNode) {
			break;
		}
		pNode = pNode.getParentNode();
	}
	if (!pNode) {
		singlePath(treeNode);
	}

}
function singlePath(newNode) {
	if (newNode === curExpandNode) return;
	if (curExpandNode && curExpandNode.open==true) {
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		if (newNode.parentTId === curExpandNode.parentTId) {
			zTree.expandNode(curExpandNode, false);
		} else {
			var newParents = [];
			while (newNode) {
				newNode = newNode.getParentNode();
				if (newNode === curExpandNode) {
					newParents = null;
					break;
				} else if (newNode) {
					newParents.push(newNode);
				}
			}
			if (newParents!=null) {
				var oldNode = curExpandNode;
				var oldParents = [];
				while (oldNode) {
					oldNode = oldNode.getParentNode();
					if (oldNode) {
						oldParents.push(oldNode);
					}
				}
				if (newParents.length>0) {
					zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
				} else {
					zTree.expandNode(oldParents[oldParents.length-1], false);
				}
			}
		}
	}
	curExpandNode = newNode;
}
function onExpand(event, treeId, treeNode) {
	curExpandNode = treeNode;
}

function onClick(e,treeId, treeNode) {	
	var zTree = $.fn.zTree.getZTreeObj("treeMenu");
	zTree.expandNode(treeNode, null, null, null, true);
}
$(document).ready(function(){
	$.ajaxSetup({async : false});
	var zNodes = '';
	<%
	Employee employee=(Employee)session.getAttribute(GlobalConstants.SESSION_EMP);
	if(employee!=null){
		for(String role:employee.getRoleIds().split(",")){
			if(role!=null&&role.length()>0){
	%>
		$.get('/inc/auth/<%=role%>.js',function (data){
			zNodes += data;	
			zNodes=zNodes.replace("][","");
		 },'script');		
	<%
	}}}%>
	zNodes = zNodes.replace("][","");
	zNodes = eval("("+zNodes+")");
	$.fn.zTree.init($("#treeMenu"), setting, zNodes);	
});
$(window).resize(function(){
	var windowHeight = $(window).height();
	if(windowHeight < 600){
		$("#left_content").css({"height":windowHeight});
	}else{
		$("#left_content").css({"height":"600px"});
	};
});
</script>
<body>
<div id="left_content">	
	 <div id="main_nav">
     	 <ul id="treeMenu" class="ztree menutree showIcon"></ul>
	 </div>
</div>
</body>
</html>