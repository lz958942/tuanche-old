var zTree;
var demoIframe;

var settingCheck = {
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	check: {
		enable: true
	},
	callback: {
		beforeClick: beforeClick,
		onCheck: onCheck
	}
};

var settingRadio = {
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	callback: {
		beforeClick: beforeClick,
		onCheck: onRadio
	}
};

var settingMenu={
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
	}		
}


function loadCityTree(id,valId,check,zNodes){
	
	if(check){
		loadCheckTree(id,zNodes,valId)
	}else{
		loadRadioTree(id,zNodes,valId)
	}
}

function loadBrandTree(id,valId,check,zNodes){
	if(check){
		loadCheckTree(id,zNodes,valId)
	}else{
		loadRadioTree(id,zNodes,valId)
	}
}



function loadCheckTree(id,zNodes,valId){
	var names='';
	var str = $("#"+valId).val();
	for(var i = 0; i < zNodes.length; i++){
		if(str.indexOf(zNodes[i].id+",") >= 0){
			zNodes[i].checked = true;
			if(zNodes[i].pId != 0){
				names += zNodes[i].name+",";
			}
		}
	}
	if (names.length > 0 ){
		names = names.substring(0, names.length-1);
	};
	$('#'+id+"_idss").val(names);	
	var t = $("#"+id);
	t = $.fn.zTree.init(t, settingCheck, zNodes);

}



function loadRadioTree(id,zNodes,valId){
	var names='';
	var str = $("#"+valId).val();
	for(var i = 0; i < zNodes.length; i++){
		if(str.indexOf(zNodes[i].id+",") >= 0){
			zNodes[i].checked = true;
			names = zNodes[i].name;
		}
	}
	$('#'+id+"_idss").val(names);	
	var t = $("#"+id);
	t = $.fn.zTree.init(t, settingRadio, zNodes);	
}


function loadMenuTree(id,zNodes){		
	var t = $("#"+id);
	t = $.fn.zTree.init(t, settingMenu, zNodes);	
}

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);	
	var checkCount = zTree.getCheckedNodes(true);
	var v = "",n = "";
	for (var i=0, l=checkCount.length; i<l; i++) {
		if(!checkCount[i].isParent){
			v += checkCount[i].id + ",";
			n += checkCount[i].name + ",";
		}		
	};
	if (n.length > 0 ){
		n = n.substring(0, n.length-1);
	};
	$("#"+treeId+"_ids").val(v);
	if($("#"+treeId+"_idss").size() > 0){
		$("#"+treeId+"_idss").focus();
		$("#"+treeId+"_idss").val(n);
	};
}

function onRadio(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId),
	checkCount = zTree.getCheckedNodes(true);
	var v = "",n="";
	for (var i=0, l=checkCount.length; i<l; i++) {
		if(!checkCount[i].isParent){
			v += checkCount[i].id;
			n += checkCount[i].name;
		}		
	};
	$("#"+treeId+"_ids").val(v);
	if($("#"+treeId+"_idss").size() > 0){
		$("#"+treeId+"_idss").focus();
		$("#"+treeId+"_idss").val(n);
	}
}

function showMenu(treesId,layId) {
	var cityObj = $("#"+treesId);
	var cityOffset = cityObj.offset();
	$("#"+layId).css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	var obj =$("#"+layId);
	obj.hover(function(){
		  $('body').unbind('mousedown');
	}, function(){
		  $('body').bind('mousedown', function(){
			   $("#"+layId).hide();           
		  });
	});
}
function hideMenu(layId) {
	$("#"+layId).fadeOut("fast");
	$("body").unbind("mousedown");
}

