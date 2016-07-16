function upload(fileId,picValueId,showPicId,type){
	if(!checkImg(fileId)){
		alert("图片格式错误！图片格式为jpg,png,gif,bmp");
		return;
	}
	$.ajaxFileUpload({//type 1专题  2adv  3 cms
		url:"/adPositionTime/imageUpload?type="+type+'&file='+fileId,
		secureuri:false,
		async:true,
		fileElementId:fileId,
		dataType: 'jsonp',	
		success: function (data){
			//alert(data);
			if(isNaN(data)){//成功
//				$("#"+picValueId).val(data.trim());
//				$("#"+showPicId).attr("src",data.trim()+"?"+Math.random());
				var subImageUrl = data.trim().substring(data.trim().indexOf(".com")+4);
				$("#"+picValueId).val(subImageUrl);
				$("#"+showPicId).attr("src",data.trim()+"?"+Math.random());
				
				
				setTimeout(function(){
					$("#"+showPicId).attr("src",data.trim()+"?"+Math.random());
				} ,1000)
				
			}else{
				alert("上传失败");
			}
		},
		error: function (data, status, e){
			alert(e);
		}
	});
}
//检查文件格式
function checkImg(fileId){
	//$("#imgMsg").text('');//清空错误信息
	var fileName = $("#"+fileId).val(), //文件名称
	fileType=["jpg","png","gif","bmp"], //图片类型
	fileExt = ""; //图片拓展名
	fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	for(var i in fileType){
		if(fileExt==fileType[i]){
			return true;
		}
	}
	return false;
}

