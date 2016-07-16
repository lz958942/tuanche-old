$(function(){
	$("#usedCarstyle").find(".selectBrand").live("change",function(){
		var brandID = $(this).val();
		$.ajax({
		   type: "POST",
		   url: "/json/carstyle/ajaxStyle",
		   dataType:'json',
		   data: {
			   'brandID':brandID
		   },
		   success: function(data){
			 $("#usedCarstyle").find("#cname").val("");
			 $("#rid").html("<option value=''>请选择</option>");
			   for(i in data) {
				   $("#rid").append("<option value="+data[i].id+">"+data[i].style+"</option>");
			   }	  
			} 
		});
	});
	$("#usedCarstyle").find("#rid").live("change",function(){
		var rname = $(this).find(":checked").html();
		var rid = $(this).find(":checked").val();
		$("#usedCarstyle").find("#cname").val("");
		if(rid!=""){
			$("#usedCarstyle").find("#cname").val(rname);
		}
	});
	$("#usedstyle").find(".brandGetUsedC").live("change",function(){
		var brandId = $(this).val();
		$.ajax({
			   type: "POST",
			   url: "/used/ajaxgetUcarstyleBybId",
			   dataType:'json',
			   data: {
				   'brandId':brandId
			   },
			   success: function(data){
				 $("#usedstyle").find("#pid").val("");
				 $("#pid").html("<option value=''>请选择</option>");
				   for(i in data) {
					   $("#pid").append("<option cid='"+data[i].rid+"' value="+data[i].id+">"+data[i].cname+"</option>");
				   }	  
				} 
			});
	});
	
	$("#usedstyle").find("#pid").live("change",function(){
		var cid = $(this).find(":checked").attr("cid");
		$.ajax({
			type: "POST",
			url: "/used/ajaxgetcstyleBycId",
			dataType:'json',
			data: {
				'cid':cid
			},
			success: function(data){
				$("#usedstyle").find("#rid").val("");
				$("#rid").html("<option value=''>请选择</option>");
				for(i in data) {
					$("#rid").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				}	  
			} 
		});
	});
	$("#usedstyle").find("#rid").live("change",function(){
		var rname = $(this).find(":checked").html();
		var rid = $(this).find(":checked").val();
		$("#usedstyle").find("#cname").val("");
		if(rid!=""){
			$("#usedstyle").find("#cname").val(rname);
		}
	});
	
	$("#saveBut").live("click",function(){
		var rid = $("#rid").val();
		var objId = $("#objId").val();
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			url: "/used/ajaxCheckDouble",
			dataType:'json',
			data: {
				'rid':rid,"objId":objId
			},
			success: function(data){
				if(data != null){
					alert("该关联已经存在！");
				}else{
					$(".formCar").submit();
				}
			} 
		});
	});
	
})


function deletec(objId,str){
	if(window.confirm("确定删除？")){
		window.location.href="/used/delete?modelstr="+str+"&id="+objId;
	}
}