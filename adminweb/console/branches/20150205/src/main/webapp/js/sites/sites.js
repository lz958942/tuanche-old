function addBrand(){
	openWindow("/sites/brand/SaveBefore","yes",600,500);
}
function openWindow(mypage,haveScroll,theWidth,theHight,theName)
{
	var w = paramexists(theWidth)? theWidth:600;
	var h = paramexists(theHight)? theHight:450;
	var scroll = paramexists(haveScroll)? haveScroll:'no';
	var myname = paramexists(theName)? theName:'';
	
	LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
	TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
	settings ='height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
	window.open(mypage,myname,settings);
}
function sitesUpload(){
	var imagesize=0;
	var ss=$("#listPicFile").each(function(){
		if(this.files[0].size>2000000){
			
			imagesize=this.files[0].size
			return;
		}
		
	});
	if(imagesize>2000000){
		alert("图片过大，超出2M！");
		return;
	}
	if(!checkImg()){
		return;
	}
	$.ajaxFileUpload({
		url:"/common/brand/uploadImgLogo",
		secureuri:false,
		async:true,
		fileElementId:'listPicFile',
		dataType: 'text',				
		success: function (data){
			if(isNaN(data)){//成功
				$("#logo").val(data.trim());
				$("#oneImage").attr("src","/pic_tmp/car"+data.trim()+"_s.jpg"+"?"+Math.random());
				
				setTimeout(function(){
				} ,1000)
				alert("上传成功");
			}else{
				alert("上传失败");
			}
		},
		error: function (data, status, e){
			alert(e);
		},
	});
}

function brandPic(){
	var imagesize=0;
	var ss=$("#listPicFileBrand").each(function(){
		if(this.files[0].size>2000000){
			
			imagesize=this.files[0].size
			return;
		}
		
	});
	if(imagesize>2000000){
		alert("图片过大，超出2M！");
		return;
	}
	if(!checkImgBrandPic()){
		return;
	}
	$.ajaxFileUpload({
		url:"/common/brandPic",
		secureuri:false,
		async:true,
		fileElementId:'listPicFileBrand',
		dataType: 'text',				
		success: function (data){
			if(isNaN(data)){//成功
				$("#brndPic").val(data.trim());
				$("#oneImage2").attr("src","/pic_tmp/car"+data.trim()+".jpg"+"?"+Math.random());
				
				setTimeout(function(){
				} ,1000)
				alert("上传成功");
			}else{
				alert("上传失败");
			}
		},
		error: function (data, status, e){
			alert(e);
		},
	});
}

//检查文件格式
function checkImgBrandPic(){
	//$("#imgMsg").text('');//清空错误信息
	var fileName = $("#listPicFileBrand").val(), //文件名称
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
function checkImg(){
	//$("#imgMsg").text('');//清空错误信息
	var fileName = $("#listPicFile").val(), //文件名称
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


function paramexists(what){
	return(typeof what!="undefined" && what!="")
	}
function saveOneBrand(){
	
}

/*function uploadImgStyle(){
	alert(1);
	if(!checkImg()){
		return;
	}
	
	$.ajaxFileUpload({
		url:"/sites/uploadImgStyle",
		secureuri:false,
		async:true,
		fileElementId:'listPicFile',
		dataType: 'text',				
		success: function (data){
			if(isNaN(data)){//成功
				$("#logo").val(data.trim());
				$("#oneImage").attr("src","/pic_tmp/sites"+data.trim()+"_s.jpg"+"?"+Math.random());
				
				setTimeout(function(){
					//$("#listImage").attr("src","http://pic.tuanche.com/"+data.trim()+"_s.jpg"+"?"+Math.random());
				} ,1000)
				alert("上传成功");
			}else{
				alert("上传失败");
			}
		},
		error: function (data, status, e){
			alert(e);
		},
	});
}*/


/*function importSubmit(nameID){
	var str=$("#"+nameID+"").val();
	var pos=str.lastIndexOf(".");
	var lastname = str.substring(pos,str.length); 
	if(lastname!='.xls'){
		alert("文件格式不支持，请上传 xls 格式的文件");
		return false;
	}
	return true;
}*/
//审核
/*function findPic(id){
	var ages="";
	if($("#id").val()!=null&& 0!=$("#id").val()){
		ages+="&id="+$("#id").val();
	}
	
	if($("#keywordid").val()&&""!=$("#keywordid").val()){
		ages+="&carstyleId="+$("#keywordid").val();
	}
	if($("#titleid").val()&&""!=$("#titleid").val()){
		ages+="&comment="+$("#titleid").val();
	}
	if($("#pradio").val()&&""!=$("#pradio").val()){
		ages+="&ishavepic="+$("#pradio").val();
	}
	openWindow("/review/particular?idd="+id+ages,"yes",700,500);
}*/
/*function pass(id){
	if(id!=null && id!=""){
		if(id!=null && id!=""){
			if(window.confirm('你确定要通过记录！')){
			$.ajax({
				   type: "GET",
				   url: "/review/pass?idd="+id,
				   success: function(m){
					 $("#reviewForm").submit();	
				   }
				});
			}
	
}
}
}
function noPass(id){
	if(id!=null && id!=""){
		if(window.confirm('你确定要不通过记录！')){
		$.ajax({
			   type: "GET",
			   url: "/review/passNo?idd="+id,
			   success: function(m){
				 $("#reviewForm").submit();	
			   }
			});
		}
}
}*/
