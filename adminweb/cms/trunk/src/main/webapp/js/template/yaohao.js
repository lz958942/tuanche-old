//摇号查询
function YHquery(){
	var code = $("#yhcode").val();
	var name = $("#yhname").val();
	if(code ==null || code.trim()==''){
		return false;
	}
	if(name == null || name.trim()==''){
		return false;
	}
	$.ajax({
	     type: "POST",
	     async:false,
	     url: "http://localhost:8080/service/yaohao/query",
	     data: {"name":name,"code":code},
	     dataType: "jsonp",
	     success:function(data){
	    	var info =  eval("("+data+")");
	    	if(info ==null){
	    		alert("查无结果！");
	    		return false;
	    	}
	    	//查询后的处理
	    	alert(info.id);
	     },
	     error:function(){
	     }
	 });
}