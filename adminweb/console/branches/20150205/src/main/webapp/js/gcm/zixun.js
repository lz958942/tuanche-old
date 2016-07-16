var setting = {
			async: {
				enable: true,
				url:"/local/ajax/zone"
			},
			view: {
				dblClickExpand: false,
				selectedMulti: false,
				showLine: true,
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: citybeforeClick,
				onClick: cityonClick
			}
		};

function citybeforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent || treeNode.id==-1);
	return check;
}

function cityonClick(e, treeId, treeNode) {

	$("#cityId").val(treeNode.id);
	$("#city").val(treeNode.name);
	
	$("#classId").val("");
	$("#classIdh").val(0);
	
	hideMenu();
}
function hiddenNode(tree){
	var zTreeobj = $.fn.zTree.getZTreeObj(tree);
	var nodes = zTreeobj.transformToArray(zTreeobj.getNodes());
	for(node in nodes){
		var no = nodes[node];
		if(no.level && no.level==1 &&!no.isParent){
			zTreeobj.hideNode(no);
		}
	}
}

function showMenu() {
	hiddenNode("treeCity");
	var cityObj = $("#city");
	var cityOffset = $("#city").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "city" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}



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
	return true;
}

function classonClick(e, treeId, treeNode) {
	$("#classId").val(treeNode.name);
	$("#classIdh").val(treeNode.id);
	classhideMenu();
}

function classshowMenu() {
	
	var zTreeobj = $.fn.zTree.getZTreeObj("treeClass");
	var nodes = zTreeobj.transformToArray(zTreeobj.getNodes());
	
	
	var isAll = $("#cityId").val()<1;
	for(var node in nodes){
		
		zTreeobj.showNode(nodes[node]);
		
//		if(nodes[node].status==0&&nodes[node].id>0){
//			zTreeobj.hideNode(nodes[node]);
//		}
		
		if(isAll){
			if(nodes[node].cityAttr==1){
				zTreeobj.hideNode(nodes[node]);
			}
		}else{
			if(nodes[node].id==0){
				continue;
			}
			if(nodes[node].cityAttr==0){
				zTreeobj.hideNode(nodes[node]);
			}
		}
	}
	
	var cityObj = $("#classId");
	var cityOffset = $("#classId").offset();
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


var settingselectCarStyle = {
		async: {
			enable: true,
			url:"/local/ajax/carstyles"
		},
		view: {
			dblClickExpand: false,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: selectCarStylebeforeClick,
			onClick: selectCarStyleonClick
		}
	};

//记录当前选择汽车品牌的元素
var thisBrand;
function selectCarStyleshow(_this) {
	thisBrand = $(_this);
	hiddenNode("treeselectCarStyle");
	var cityObj = $(thisBrand);
	var cityOffset = $(thisBrand).offset();
	$("#menuContentselectCarStyle").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", selectCarStyleonBodyDown);
}

function selectCarStylebeforeClick(treeId, treeNode) {
	if(treeNode.level && treeNode.level==1){
		return false;
	}
	var zTree = $.fn.zTree.getZTreeObj("treeselectCarStyle");
	var nodes = zTree.getSelectedNodes();
	if(nodes.length>4){
		return false;
	}
	return true;
}

function selectCarStyleonClick(e, treeId, treeNode) {
	
	var zTree = $.fn.zTree.getZTreeObj("treeselectCarStyle");
	var nodes = zTree.getSelectedNodes();
	var bv ="";
	var bid="";
	var sv ="";
	var sid ="";
	
	nodes.sort(function compare(a,b){return a.id-b.id;});
	
	for (var i=0, l=nodes.length; i<l; i++) {
			var node = nodes[i];
			var id=node.id;
			
			if(id.indexOf("-")<0){
				bv+=node.name+",";
				bid+=node.id;
			}else{
				alert('请选择汽车品牌');
			}
			
	}
	
	var v = bv+sv;
	 v = v.substring(0,v.length-1);
	$(thisBrand).val(v);
	$(thisBrand).next().val(bid);
	
}


function selectCarStylehideMenu() {
$("#menuContentselectCarStyle").fadeOut("fast");
$("body").unbind("mousedown", selectCarStyleonBodyDown);
}

function selectCarStyleonBodyDown(event) {
if (!(event.target.id == "menuContentselectCarStyle" || $(event.target).parents("#menuContentselectCarStyle").length>0)) {
	selectCarStylehideMenu();
}
}


$(document).ready(function(){
	$.fn.zTree.init($("#treeCity"), setting);
	$.fn.zTree.init($("#treeClass"), settingclass);
	$.fn.zTree.init($("#treeselectCarStyle"), settingselectCarStyle);
});




