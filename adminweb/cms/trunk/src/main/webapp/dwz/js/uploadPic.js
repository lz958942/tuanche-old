function saveOriginal(){
   	$('#x').val(0);
   	$('#y').val(0);
   	$('#x2').val(0);
   	$('#y2').val(0);
   	$('#w').val(0);
   	$('#h').val(0);
   	upload();
}
function upload(){
   	var x = $('#x').val();
   	var y = $('#y').val();
   	var x2 = $('#x2').val();
   	var y2 = $('#y2').val();
   	var w = $('#w').val();
   	var h = $('#h').val();
	var widthShow = $("#width").val();
	var heightShow = $("#height").val();

   	if (w=='' || parseInt(w)<=0){
		if(!confirm('不剪切,确定使用整张图片?')){
			return false;
		}else{
	   		x=0;
	   		y=0;
	   		x2=0;
	   		y2=0;   		
	   		w=0;
	   		h=0;
		}
   	}
   	var pathHidden = $("#pathHidden").val();
   	var cutUrl = $("#cutUrl").val();
   	$.ajax({
		type : "GET",
		async : false,
		url : cutUrl,
		dataType : "json",
		data : {
			x:parseInt(x),
			y:parseInt(y),
			x2:parseInt(x2),
			y2:parseInt(y2),
			w:parseInt(w),
			h:parseInt(h),
			widthShow:parseInt(widthShow),
			heightShow:parseInt(heightShow),
			path:pathHidden
		},
		success : function(data) {
			if(data.scs){
				$("#dynamicDialog").dialog("close");
				$("#preview").attr("src",data.pic_url + data.path);
				$("#preview").show();
				$("#picPath").val(data.path);
			}else{
				alert("剪切失败!");
			}
		}
	});
}

// 更新坐标
function updateCoords(c)
{
	jQuery('#x').val(c.x);
	jQuery('#y').val(c.y);
	jQuery('#x2').val(c.x2);
	jQuery('#y2').val(c.y2);
	jQuery('#w').val(c.w);
	jQuery('#h').val(c.h);
}


function checkAndUpload(){
	var fileName=$('#imgFile').val();
	var widthShow = $("#width").val();
	var heightShow = $("#height").val();
	if(widthShow==''||heightShow==''){
		alert("请设置图片的宽高");
		return false;
	}
	var ext = fileName.split(".")[1];
	var tmpDate = new Date(); 
	if(ext=='jpg'||ext=='JPG'||ext=='gif'||ext=='GIF'||ext=='png'||ext=='PNG'||ext=='jpeg'||ext=='JPEG'||ext=='bmp'){
		var picServer = $("#pic_url").val();
		var uploadUrl = $("#uploadUrl").val();
		$.ajaxFileUpload({
			url : uploadUrl,
			secureuri :false,
			fileElementId :'imgFile',
			dataType: 'json',
			success : function(data,status){
				if(data.scs){
					if(data.path==""){
						alert("请稍后再试.");
					} else {
						var picwidth = data.picwidth;
						var picheight = data.picheight;
						var jcrop_api;
				        var $dialog = $('<div id="dynamicDialog"><div class="jc-dialog">'
				        		+'<img id="originPic" src="'+picServer+data.path+'?t='+ tmpDate.getTime()+'" style="width:'+picwidth+'px;height:'+picheight+'px;"/></div></div>');
				        $dialog.find('img').Jcrop({
				        	minSize: [50,50],
				        	setSelect: [0,0,100,100],
				        	onSelect: updateCoords,
				        	onRelease: updateCoords,
				        	aspectRatio : widthShow/heightShow,
				        	boxWidth:picwidth,
				        	boxHeight:picheight
				        },function(){
				          jcrop_api = this;
				          jcrop_api.setOptions({ bgColor: 'black', bgOpacity: 0.4 });
				          //jcrop_api.ui.selection.addClass('jcrop-selection');
				          //jcrop_api.ui.holder.addClass('jcrop-dark');
				          if(jcrop_api.getBounds()[0]<10 || jcrop_api.getBounds()[1]<10){
				        	  alert("图片长宽小于10*10,无法精确剪切,请先缩放后上传!");
				        	  return;
				          }
				          $dialog.dialog({
				            modal: true,
				            title: '图片剪裁',
				            close: function(){ $dialog.remove(); },
				            width: jcrop_api.getBounds()[0]+50>550?550:jcrop_api.getBounds()[0]+50,
						    height: jcrop_api.getBounds()[1]+150>650?650:jcrop_api.getBounds()[1]+150,
				            resizable: true
				          });
				        });
				        $dialog.append('<p><b>请剪切图片,然后点击完成</b>'
				        		+'<div>'
				        		+'<input type="hidden" id="x" name="x" />'
				        		+'<input type="hidden" id="y" name="y" />'
				        		+'<input type="hidden" id="x2" name="x2" />'
				        		+'<input type="hidden" id="y2" name="y2" />'
				        		+'<input type="hidden" id="w" name="w" />'
				        		+'<input type="hidden" id="h" name="h" />'
				        		+'<input type="hidden" name="widthShow" value='+widthShow+'/>'
				        		+'<input type="hidden" name="heightShow" value='+heightShow+'/>'
				        		+'<input type="hidden" id="pathHidden" name="pathHidden" value="'+data.path+'"/>'
				        		+'<input type="button" id="releaseSelect" onclick="saveOriginal();" value="保存原图" style="float:right; width: 98px;" />'
				        		+'<input type="button" id="toCutPic" onclick="upload();" value="完成" style="float:right; width: 98px;" /></div>'
				        		+'</p>');
				        return false;
					}
				} else {
					alert("请稍后再试.");
				}
			},
			error: function (data, status, e)  
			{alert(e);} 
		});
	} else {
		alert("请选择图片上传!");
	}
}

function xUploadPic(){
	$("#imgFile").attr("disable",true);
	return true;
}

function checkAndSubmit(){
	xUploadPic();
	$("#addContentForm").submit();
}