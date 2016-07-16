
 var setting = {
		 async: {
		 enable: true,
		 url:"/local/ajax/zone"
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
		 beforeClick: citybeforeClick,
		 onClick: cityonClick
		 }
}; 
 $(document).ready(function(){
	 $.fn.zTree.init($("#treeCity"), setting);
	 $.fn.zTree.init($("#treeselectCarStyle"), settingselectCarStyle);
	 
	//列表 行颜色
	$(".trColor").hover(function(){
		$(this).addClass("hover");
	},
	function(){
		$(this).removeClass("hover");
	}
	);
	
	$(".trColor").live("click",function(){
		var isSelect = $(this).hasClass("selected");
		$(".trColor").removeClass("selected");
		if(!isSelect){
			$(this).addClass("selected");
		}
		
	});
	
	//城市树
	 $("#city").live("click",function(){
		 $(this).attr("disabled","disabled");
		 hiddenNode("treeCity");
		var cityObj = $("#city");
		var cityOffset = $("#city").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	 });
	 //修改
	 $(".updateTlcity").live("click",function(){
		var temId = $("#tlcity").find(".selected").attr("rel");
		if(!temId){
			alert("请选择城市模板！");
			return;
		}
		$(this).attr("href","/tlcity/toUpdate?id="+temId)
	 });
	 
	 
	 //删除
	 $(".deleteTlcity").live("click",function(){
		 var temId = $("#tlcity").find(".selected").attr("rel");
			if(!temId){
				alert("请选择城市模板！");
				return;
			}
			if(confirm("确定要删除吗?")){
				$(this).attr("href","/tlcity/delete?id="+temId)
			}
	 });
	 //生成页面
	 $(".makeHtml").live("click",function(){
		 var temId = $("#tlcity").find(".selected").attr("rel");
			if(!temId){
				alert("请选择城市模板！");
				return;
			}
			if(confirm("确定要生成页面吗?")){
				 $.ajax({
		             type: "POST",
		             async:false,
		             url: "/free/makePageByCityId",
		             data: {"tlCityId":temId},
		             dataType: "json",
		             success:function(data){
		            	 alert("生成页面成功!");
		             },
		             error:function(){
		            	 alert("生成页面失败!");
		             }
				 });
			}
	 });
	//预览
	 $(".viewHtml").live("click",function(){
		 var temId = $("#tlcity").find(".selected").attr("rel");
			if(!temId){
				alert("请选择城市模板！");
				return;
			}
			 $.ajax({
	             type: "POST",
	             async:false,
	             url: "/free/viewHtml",
	             data: {"tlCityId":temId},
	             dataType: "json",
	             success:function(data){
	            	 $("#viewHtml").attr("href","/ftl/static/"+eval(data)[0].Htmlname);
	             },
	             error:function(){
	            	 $("#viewHtml").attr("href","javascript:void(0);");
	            	 alert("预览失败");
	             }
			 });
	 });
}); 

 function citybeforeClick(treeId, treeNode) {
	 var check = (treeNode && !treeNode.isParent || treeNode.id==-1);
	 return check;
	 }
	 function cityonClick(e, treeId, treeNode) {
	 $("#cityId").val(treeNode.id);
	 $("#city").val(treeNode.name);
	 $("#city").removeAttr("disabled");
	 hideMenu();
} 
 function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("#city").removeAttr("disabled");
	$("body").unbind("mousedown", onBodyDown);
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

 function onBodyDown(event) {
		if (!(event.target.id == "city" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
}
 
 var settingselectCarStyle = {
			async: {
				enable: true,
				url:"/local/ajax/carstyles"
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
				beforeClick: selectCarStylebeforeClick,
				onClick: selectCarStyleonClick
			}
		};

	function selectCarStyleshow() {
		hiddenNode("treeselectCarStyle");
		var cityObj = $("#selectCarStyle");
		var cityOffset = $("#selectCarStyle").offset();
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
				if(node.pId>0){
					sv+=node.name+",";
					sid=new Number(node.id);
					if(sid>1000000){
						sid=sid-1000000;
					}
				}else{
					bv+=node.name+",";
					bid+=node.id+",";
				}
				
		}
		
		var v = bv+sv;
		 v = v.substring(0,v.length-1);
		$("#selectCarStyle").val(sid);
		$("#brindId").val(bid);
		$("#styleId").val(sid);
		
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


