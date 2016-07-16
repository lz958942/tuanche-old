$(function(){
	$(".updatePlate").live("click",function(){
		var temId = $("#tlcity").find(".selected").attr("rel");
		if(!temId){
			alert("请选择板块！");
			return;
		}
		$(this).attr("href","/plate/toUpdate?id="+temId)
	});
	 //删除
	 $(".deletePlate").live("click",function(){
		 var temId = $("#tlcity").find(".selected").attr("rel");
			if(!temId){
				alert("请选择城市模板！");
				return;
			}
		if(confirm("确定要删除吗?")){
			$(this).attr("href","/plate/delete?id="+temId);
		}
	 });
	 //选择数据类型
	 $("#dataTypeSelector").live('change',function(){
		 var thisValue = $(this).val();
		 if(thisValue == 1){//资讯   有分类
			$("#dataTypeClass").css("display","");
			$("#dataTypeClass").html($("#zixunTypeClass").html());
		 }else if(thisValue == 5){//车型 
			$("#dataTypeClass").css("display","");
			$("#dataTypeClass").html($("#carTypeClass").html());
		 }else{
			 $("#dataTypeClass").css("display","none");
			 $("#dataTypeClass").html("");
		 }
	 });
	 
	 //选择模板
	 $("#templateSelector").live("change",function(){
		var tname = $(this).find("[value = "+$(this).val()+"]").html();
		$("#tnameV").val(tname);
	 });
	 
	 //展示类型
	 $("#typeSelector").live("change",function(){
		 var typeId = $(this).val();
		 if(typeId==4){
			 $("#selectDataType").css("display","none");
			 $("#tiShi").css("display","block");
			 $("#isauto").find("[value = 2]").attr("selected","selected");
			 $("#isauto").attr("disabled","disabled");
		 }else{
			 $("#selectDataType").css("display","");
			 $("#tiShi").css("display","none");
			 $("#isauto").removeAttr("disabled");
		 }
	 });
	 //新增时
	 if(!$("#plateId").val().trim()){
		 $("#templateSelector").change();
		 $("#dataTypeClass").css("display","");
		 $("#dataTypeClass").html($("#zixunTypeClass").html());
	 }else{//修改时
		 var dataType = $("#dataTypeSelector").val();
		 if(dataType==1){//资讯
			$("#dataTypeClass").css("display","");
			$("#dataTypeClass").html($("#zixunTypeClass").html());
		 }else if(dataType == 5){//车型
			 $("#dataTypeClass").css("display","");
			 $("#dataTypeClass").html($("#carTypeClass").html());
		 }else{
			 
		 }
	 }
	
});