//摇号查询
function YHquery(){
	var code = $("#yhcode").val();
	var name = $("#yhname").val();
	if(code ==null || code.trim()==''){
		return ;
	}
	$.ajax({
	     type: "POST",
	     async:false,
	     url: "http://localhost:8080/service/yaohao/query",
	     data: {"name":name,"code":code},
	     dataType: "jsonp",
	     success:function(data){
	    	var info =  eval("("+data+")");
	    	if(info ==null){//未中签
	    		$("#yhForm").addClass("hide");
	    		$("#yh_no").removeClass("hide");
	    		$("#no_infoCode").html("编码：  "+code);
	    		$("#no_infoName").html("姓名： "+name);
	    	}else{//中签
	    		$("#yhForm").addClass("hide");
	    		$("#yh_yes").removeClass("hide");
	    		$("#period").html("期号："+info.period);
	    		$("#infoName").html("姓名： "+info.name);
	    		$("#infoCode").html("编码：  "+info.code);
	    		$("#infoNumber").html("基数：  "+info.number);
	    	}
	     },
	     error:function(){
	    	 alert("查询失败！");
	     }
	 });
}