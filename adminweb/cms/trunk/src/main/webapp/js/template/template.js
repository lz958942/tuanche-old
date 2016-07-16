$(function(){
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
	
	$(".toUpdate").live("click",function(){
		var temId = $("#temList").find(".selected").attr("rel");
		if(!temId){
			alert("请选择模板！");
			return;
		}
		$(this).attr("href","/template/toUpdate?id="+temId)
	});
	
	$(".deleteButton").live("click",function(){
		var temId = $("#temList").find(".selected").attr("rel");
		if(!temId){
			alert("请选择模板！");
			return;
		}
		if(confirm("确定要删除吗?")){
			$(this).attr("href","/template/delete?id="+temId)
		};
	});
});




