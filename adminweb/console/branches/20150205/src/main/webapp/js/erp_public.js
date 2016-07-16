/**
 *  
 *  团车网ERP后台公共JS方法
 *
 *  详细方法使用可参考 read me.txt文件
 *
 */

// alert弹出框
function bAlert(message){
	$("#bAlert").remove();
	var str = '<div id="bAlert" title="提示框">'
              +'      <p>'+message+'</p>'
              +'  </div>'
	
	$("body").append(str);	
	$('#bAlert').dialog({
		bigiframe:true,
		modal:true,
		width:400,
		draggable:false,
		resizable:false,
		buttons:{
			"确定":function(){
				$(this).dialog("close");
			}
		}
	});
}

// confirm弹出框
function bConfirm(message,fn){
	$("#bConfirm").remove();
	var str = '<div id="bConfirm" title="提示框">'
              +'      <p>'+message+'</p>'
              +'  </div>'
	
	$("body").append(str);	
	$('#bConfirm').dialog({
		modal:true,
		width:400,
		draggable:false,
		resizable:false,
		buttons: fn

	});
}

// confirm弹出框带参数
function bConfirmPar(message,fn,param){
	var param = param ? param : '';
	$("#bConfirmPar").remove();
	var str = '<div id="bConfirmPar" title="提示框" data-param="'+param+'">'
              +'      <p>'+message+'</p>'
              +'  </div>'
	
	$("body").append(str);	
	$('#bConfirmPar').dialog({
		modal:true,
		width:400,
		draggable:false,
		resizable:false,
		buttons: fn

	});
}

// 弹出层
function bPopup(title,url){
	$("#bPopup").remove();
	var str = '<div id="bPopup" title="'+title+'"></div>'
	$("body").append(str);
	$("#bPopup").load(url,function(){
		$('#bPopup').dialog({
			bigiframe:true,
			modal:true,
			width:400,
			draggable:true,
			resizable:true,
			buttons:{
				"确定":function(){
					$(this).dialog("close");
				}
			}
		});
	});
}

//弹出层自定义按钮
function bPopupBtn(title,url,fn,wid){
	var wid = wid ? wid : 500;
	$("#bPopup").remove();
	var str = '<div id="bPopup" title="'+title+'"></div>'
	$("body").append(str);
	$("#bPopup").load(url,function(){
		$('#bPopup').dialog({
			bigiframe:true,
			modal:true,
			width:wid,
			draggable:true,
			resizable:true,
			buttons:fn
		});
	});
}

function bPopupIframe(title,url,wid,hei,fn){

	$("#bPopupIframe").remove();
	var str = '<div id="bPopupIframe" title="'+title+'"><iframe name="popIframe" id="iframeId" frameborder="0" src="'+url+'" width="100%" height="100%"></iframe></div>'
	$("body").append(str);
	$('#bPopupIframe').dialog({
		bigiframe:true,
		modal:true,
		width: parseInt(wid)+30,
		height: parseInt(hei)+30,
		draggable:true,
		resizable:true,
		buttons:fn
	});
}


function bPopupLocal(id,fn){
	$('#'+id).dialog({
		bigiframe:true,
		modal:true,
		width:400,
		draggable:true,
		resizable:true,
		buttons:fn
	});
}

function bTip(message){
	$("#bTip").remove();
	var str = '<div id="bTip" title="温馨提示">'
              +'      <p class="text-center" style="font-size:16px; font-weight:bold; line-height:30px;">'+message+'</p>'
              +'  </div>'
	
	$("body").append(str);	
	$('#bTip').dialog({
		bigiframe:true,
		modal:true,
		height:150,
		draggable:false,
		resizable:false,		
		open:function(){
			setTimeout(function(){$('#bTip').dialog("close")},3000);	
		}				
	});	
}

//全选
function selectAll(obj,self){
	if($(self).attr("checked")){
		$('input[name="'+obj+'"][type="checkbox"]').each(function(){
			$(this).attr("checked",true);
		});
	}else{
		$('input[name="'+obj+'"][type="checkbox"]').each(function(){
			$(this).removeAttr("checked");
		});
	};
};

// 反选
function selectReverse(obj){
	$('input[name="'+obj+'"][type="checkbox"]').each(function(){
		if($(this).attr("checked")){
			$(this).removeAttr("checked");
		}else{
			$(this).attr("checked",true);
		};		
	});	
};

