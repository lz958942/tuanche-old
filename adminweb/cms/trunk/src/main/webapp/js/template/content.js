$(function(){
	
	 //删除
	 $(".deleteContent").live("click",function(){
		 var plateId = $("#plateId").val();
		 var temId = $("#contentList").find(".selected").attr("rel");
			if(!temId || !plateId){
				alert("请选择内容！");
				return;
			}
		if(confirm("确定要删除吗?")){
			$(this).attr("href","/content/delete?contentId="+temId+"&plateId="+plateId);	
		}
	 });
	 
	 //上移
	 $(".upSort").live("click",function(){
		var plateId =  $("#plateId").val();
		var upconId = $(this).attr("rel");
		var downconId = $(this).parent().parent().prev().attr("rel");
		if(downconId == null){
			alert("已是最顶！");
			return false;
		}
		if(upconId && downconId && plateId){
			$(this).attr("href","/content/sort?upContentId="+upconId+"&DownContentId="+downconId+"&plateId="+plateId);
		}
	 });
	 //下移
	 $(".downSort").live("click",function(){
		 var plateId =  $("#plateId").val();
			var downconId = $(this).attr("rel");
			var upconId = $(this).parent().parent().next().attr("rel");
			if(upconId == null){
				alert("已是最底！");
				return false;
			}
			if(upconId && downconId && plateId){
				$(this).attr("href","/content/sort?upContentId="+upconId+"&DownContentId="+downconId+"&plateId="+plateId);
			}
	 });
});


