$(document).ready(function(){
	
	
	//var div="<span style=\"font-size:16px;height:50px;font-weight: bold;\">事件回顾:</span>";
	var fenge="<table width=\"775px\" height=\"80px\"><td width=\"45%\" style=\"vertical-align:middle;\"><hr style=\"border:1px dashed;color:#D0D0D0;\"/></td><td width=\"10%\" align=\"center\" style=\"text-align:center;vertical-align:middle;\"><span style=\"font-size:16px;height:50px;\">事件回顾</span></td><td width=\"45%\" style=\"vertical-align:middle;\"><hr style=\"border:1px dashed;color:#D0D0D0;\"></td></table>";
	var fengepic="<table width=\"775px\" height=\"80px\"><td width=\"45%\" style=\"vertical-align:middle;\"><hr style=\"border:1px dashed;color:#D0D0D0;\"/></td><td width=\"10%\" align=\"center\" style=\"text-align:center;vertical-align:middle;\"><span style=\"font-size:16px;height:50px;\">图片策划</span></td><td width=\"45%\" style=\"vertical-align:middle;\"><hr style=\"border:1px dashed;color:#D0D0D0;\"></td></table>";
	var fengeDown="<hr width=\"100%\" />";
	$("#biaoti_0").before(fenge);
	$("#msg").before(fengeDown);
	$("#picList").before(fengepic);
	
	var divNum0=0;
	var divNum1=0; 
	$("#addbutton0").click(function(){
		++divNum0;
		var divHtml =  $("#biaoti_0").html();
		divHtml = divHtml.replace(/#biaoti_0/g, "#biaoti_"+divNum0);
		divHtml = "<table id=\"biaoti_"+divNum0+"\">" +divHtml+"</table>";
		$("#biaoti_0").before(divHtml);
	});
	$("#addpics").click(function(){
		++divNum1;
		
		var divHtml =  "<td>上传图片：</td><td><img id=\"listImage_0\" src='/images/upload.jpg'  onclick=\"$('#listPicFile_0').trigger('click')\"/><input type=\"hidden\" id=\"listPic_0\" name=\"listPic\"  value=\"\" /> <input type=\"hidden\" name=\"picListId\" value=\"-1\" /><input type=\"button\" value=\"删除\" onclick=\"deletePics('#pics_0')\"/></td><td><span style=\"width:100px;padding-right:16px;padding-left:16px;vertical-align:top\"><!--选择列表图片：--></span><div style=\"display: none\"><input id=\"listPicFile_0\" name=\"listPicFile_0\" type=\"file\"  onchange=\"uploadPics('listPicFile_0','listPic_0','listImage_0','1')\"/></div></td>";
		divHtml = divHtml.replace(/0/g, divNum1);
		divHtml = "<tr id=\"pics_"+divNum1+"\">" +divHtml+"</tr>";
		$("#pics_0").before(divHtml);
		
	});
	$("#newTemplateBtn").click(function(){
		var nameText=$("#tpName").val();
		var idText=$("#id").val();
		$.ajax({
			url:"/specialSubject/selectTempName",
			type:"post",
			dataType:"text",
			data:{
				tpName:nameText
			},
			success:function(date){
				if(idText==null||''==idText){
					if(''!=date&&date!=null){
						alert(date);
						return;
					}
				}
				if(nameText.trim()==null||''==nameText.trim()){
					alert("模板名不能为空");
					return;
				}else{
					$("#newTemplateForm").submit();
				}
			}
		});
	});
})
var count=0;
//修改专题，增加一行
function updatePicBtn(lienum){
	++count;
	lienum=parseInt(lienum)+parseInt(count);
	var divHtml = "<td>上传图片：</td><td><img id=\"listImage_0\" src='/images/upload.jpg'  onclick=\"$('#listPicFile_0').trigger('click')\"/><input type=\"hidden\" id=\"listPic_0\" name=\"listPic\"  value=\"\" /> <input type=\"hidden\" name=\"picListId\" value=\"-1\" /><input type=\"button\" value=\"删除\" onclick=\"deletePics('#pics_0')\"/></td><td><span style=\"width:100px;padding-right:16px;padding-left:16px;vertical-align:top\"><!--选择列表图片：--></span><div style=\"display: none\"><input id=\"listPicFile_0\" name=\"listPicFile_0\" type=\"file\"  onchange=\"uploadPics('listPicFile_0','listPic_0','listImage_0','1')\"/></div></td>";
	divHtml = divHtml.replace(/0/g, lienum);
	divHtml = "<tr id=\"pics_"+lienum+"\">" +divHtml+"</tr>";
	$("#pics_0").before(divHtml);
}
function updateAddTitle(lienum){
	++count;
	lienum=parseInt(lienum)+parseInt(count);
	var divHtml="<table id=\"biaoti_0\"><tr><input type=\"hidden\" name=\"titlesId\" value=\"-2\"/><td style=\"white-space:nowrap\" width=\"99.99px\">标题名称：</td><td><input name=\"stTitle\" id=\"stTitle\" style=\"width:310px;\" type=\"text\" value=\"\" maxlength=\"25\"/><p style=\"width: 150px\" class=\"font\">（请控制25字以内）</p></td> <td style=\"white-space:nowrap\" width=\"85px\" align=\"right\">标题url：</td><td><input  name=\"titleUrls\" id=\"titleUrls\" style=\"width:270px;\" type=\"text\" value=\"\" maxlength=\"25\"/><p style=\"width: 150px\" class=\"font\">（请控制25字以内）</p></td></tr><tr><td style=\"vertical-align: middle;white-space: nowrap;\">标题内容：</td><td><textarea maxlength=\"500\" cols=\"50\"  rows=\"3\" id=\"stContent\" name=\"stContent\" style=\"width:310px\"></textarea><p style=\"width: 150px\" class=\"font\">（请控制500字以内）</p></td><td style=\"white-space:nowrap\" width=\"85px\" align=\"right\">标题排序：</td><td><input  name=\"sorts\" id=\"sorts\" style=\"width:270px;font-size: 12px;\" type=\"text\" value=\"\" maxlength=\"25\"/><p style=\"width: 150px\" class=\"font\">（数值,由高到低排序）</p></td><td><input type=\"button\" value=\"删除\" onclick=\"deleteButton('#biaoti_0')\"/></td></tr></table>";
	divHtml = divHtml.replace(/0/g, lienum);
	divHtml = "<table id=\"biaoti_"+lienum+"\">" +divHtml+"</table>";
	$("#biaoti_0").before(divHtml);
}

// 图片上传
function upload(fileId, picValueId, showPicId, type) {
	if (!checkImg(fileId)) {
		alert("图片格式错误！图片格式为jpg,png,gif,bmp");
		return;
	}
	$.ajaxFileUpload({// type 1专题 2adv 3 cms
		url : "/specialSubject/uploadPic?type=" + type + '&file=' + fileId,
		secureuri : false,
		async : true,
		fileElementId : fileId,
		dataType : 'jsonp',
		success : function(data) {
			alert("上传成功");
			if (isNaN(data)) {// 成功
				$("#" + picValueId).val(data.trim());
				$("#" + showPicId).attr("src",
						data.trim() + "?" + Math.random());
				$("#" + showPicId).attr("class", "uploadCss");
				setTimeout(function() {
					$("#" + showPicId).attr("src",
							data.trim() + "?" + Math.random());
				}, 1000)

			} else {
				alert("上传失败");
			}
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
}
// 多图片上传
var divnum3 = 0;
function uploadPics(fileId, picValueId, showPicId, type, id) {
	++divnum3;
	var listImage1 = $("#pics_" + divnum3 + " img").attr("id");
	if (!checkImg(fileId)) {
		alert("图片格式错误！图片格式为jpg,png,gif,bmp");
		return;
	}
	$.ajaxFileUpload({// type 1专题 2adv 3 cms
		url : "/specialSubject/uploadPic?type=" + type + '&file=' + fileId,
		secureuri : false,
		async : true,
		fileElementId : fileId,
		dataType : 'jsonp',
		success : function(data) {
			 alert("上传成功");
			if (isNaN(data)) {// 成功
				 if(listImage1 == null){
					$("#" + picValueId).val(data.trim());
					$("#" + showPicId).attr("src",
							data.trim() + "?" + Math.random());
					$("#" + showPicId).attr("class", "uploadCss");
					setTimeout(function() {
						$("#" + showPicId).attr("src",
								data.trim() + "?" + Math.random());
					}, 1000)
				}else{
					$("#" + picValueId ).val(data.trim());
					$("#" + showPicId ).attr("src",
							data.trim() + "?" + Math.random());
					$("#" + showPicId).attr("class", "uploadCss");
					setTimeout(function() {
						$("#" + showPicId ).attr("src",
								data.trim() + "?" + Math.random());
					}, 1000)
				}
			} else {
				alert("上传失败");
			}
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
}

// 检查文件格式
function checkImg(fileId) {
	// $("#imgMsg").text('');//清空错误信息
	var fileName = $("#" + fileId).val(), // 文件名称
	fileType = [ "jpg", "png", "gif", "bmp" ], // 图片类型
	fileExt = ""; // 图片拓展名
	fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	for ( var i in fileType) {
		if (fileExt == fileType[i]) {
			return true;
		}
	}
	return false;
}
// 全选
function selectAll() {
	$("#dataBody input[type='checkbox']").attr("checked",
			$("#allCheck").attr("checked") ? true : false);
}

// 批量生成页面
function createHtmlBySelect() {
	var selectids = $("input[name='checkboxSpecial']:checked");
	var ids = "";
	for ( var i = 0; i < selectids.length; i++) {
		if (i == selectids.length - 1) {
			ids += $(selectids[i]).val();
		} else {
			ids += $(selectids[i]).val() + ",";
		}
	}
	if (selectids.length == 0) {
		alert("未选中任何行！")
		return;
	}
	$.ajax({
		url : "/specialSubject/createHtmlBySelect",
		type : "post",
		dataType : "text",
		data : {
			'ids' : ids
		},
		success : function(date) {
			confirm(date);
		}
	})
}

// 批量删除和上下线
function deleteSelect(status) {
	var datas = "";
	var ids = $("input[name='checkboxSpecial']:checked");
	if (ids.length == 0) {
		alert("未选中任何行！");
		return;
	}
	var tip = "确认"
	tip += status == -1 ? "删除" : (status == 1 ? "上线" : "下线");
	tip += "?";
	if (!confirm(tip)) {
		return;
	}
	var acUrl = "/specialSubject/";
	if (status == -1) {
		acUrl += "deleteSelect";
	} else {
		acUrl += "upOrDownSelect";
		datas += "type=" + (status == 1 ? "true" : "false");
	}

	for ( var i = 0; i < ids.length; i++) {
		datas += ("&id=" + $(ids[i]).val());
	}

	$.ajax({
		url : acUrl,
		type : 'POST',
		data : datas,
		success : function(date) {
			window.location.href = "/specialSubject/search?page.pageNo="
					+ $("#currentNo").val();
		}
	});
}
// 删除标题单元格
function deleteButton(id,stId) {
	if(stId!=null&&''!=stId){
		if (id!="#biaoti_0") {
			if (!confirm("确定要删除标题?")) {
				return;
			}
			$.ajax({
				url:'/specialSubject/deletePic',
				type:'post',
				dateType:'text',
				data:{
					'id':stId
				},
				success:function(data){
					alert(data);
					$(id).remove();
				},
			});
		} else {
			alert("最小单元不可删除");
			return;
		}
	}else{
		if (id=="#biaoti_0") {
			alert("最小单元不可删除");
			return;
		} else {
			$(id).remove();
		}
	}
	
}
// 删除图片单元格
function deletePics(id,spId){
	
	if(spId!=null&&''!=spId){
		if(id!="#pics_0"){
			if (!confirm("确定要删除图片?")) {
				return;
			}
			$.ajax({
				url:'/specialSubject/deletePic',
				type:'post',
				dateType:'text',
				data:{
					'id':spId
				},
				success:function(data){
					alert(data);
					$(id).remove();
				},
			});
		}else{
			alert("最小单元不可删除");
			return;
		}
	}else{
		if (id=="#pics_0") {
			alert("最小单元不可删除");
			return;
		} else {
			$(id).remove();
		}
	}
}
// 上下线
function upOrDown(id, spOnline) {
	$.ajax({
		url : "/specialSubject/upOrDown",
		type : "post",
		data : {
			'id' : id,
			'spOnline' : spOnline
		},
		success : function(date) {

			updateOperaHtml(id, spOnline);
		}
	})
}
// 专题删除
function deleteSp(id) {
	
	if(!confirm("确定要删除?")){
		return;
	}
	$.ajax({
		url : "/specialSubject/deleteSp",
		type : "post",
		data : {
			'id' : id
		},
		success : function(date) {
			updateDte(id);
		}
	})
}
// 模板删除
function deleteTp(id) {
	
	if(!confirm('确定要删除?')){
		return;
	}
	$.ajax({
		url : "/specialSubject/deleteTp",
		type : "post",
		data : {
			'id' : id
		},
		success : function(date) {
			updateDte(id);
		}
	})
}
// 更新专题状态
function updateOperaHtml(id, spOnline) {
	if (spOnline == 1) {
		$("#sp_" + id).find("#specials_status").html("下线");
		$("#sp_" + id).find("#specials_upOrDown").attr("href",
				"javascript:upOrDown(" + id + "," + 2 + ");");
		$('#sp_' + id).find("#specials_upOrDown").html("上线");
	} else {
		$("#sp_" + id).find("#specials_status").html("上线");
		$("#sp_" + id).find("#specials_upOrDown").attr("href",
				"javascript:upOrDown(" + id + "," + 1 + ");");
		$('#sp_' + id).find("#specials_upOrDown").html("下线");
	}
}
// 删除专题行
function updateDte(id) {
	$("#sp_" + id).remove();
}
// 搜索条件表单提交
function searchSubimt(id) {
	if (id == 1) {
		var text = $("#specialId").val();
		if ("" != text) {
			if (!(/^(\+|-)?\d+$/.test(text)) || text < 0 || text > 2147483648) {
				alert("专题编号格式不对");
			} else {
				$("#searchSpecialForm").submit();
			}
		} else {
			$("#searchSpecialForm").submit();
		}

	} else {
		var text = $("#id").val();
		if ("" != text) {
			if (!(/^(\+|-)?\d+$/.test(text)) || text < 0 || text > 2147483648) {
				alert("模板编号格式不对");
			} else {
				$("#searchTemplateForm").submit();
			}
		} else {
			$("#searchTemplateForm").submit();
		}
	}
}

function sub(){
	var ztType=$("#ztType").val();
	var spName = $("#spName").attr("value").trim();
	var spAbstract = $("#spAbstract").attr("value").trim();
	var onePic = $("#onePic").attr("value").trim();
	if (spName == null || '' == spName) {
		alert("专题名称不能为空");
		return;
	}
	if (spAbstract == null || '' == spAbstract) {
		alert("专题导语不能为空");
		return;
	}
	if (onePic == null || '' == onePic) {
		alert("专题头图不能为空");
		return;
	}
	if(ztType=='1'){
		var picTitle = $("#picTitle").attr("value").trim();
		var picValue = $("#listPic_0").attr("value").trim();
		if (picTitle == null || '' == picTitle) {
			alert("图片策划标题不能为空");
			return;
		}
		if (picValue == null || '' == picValue) {
			alert("请至少上传一张图片");
			return;
		}
	}
	$("#newZixunPropertiesForm").submit();

}
function selectTemp(id){
	if(id==null||id==''){
		alert('请先选择模板');
		return false;
	}
}
function tOnline(url){
	$.ajax({
		url:'/specialSubject/tOnline',
		type:'post',
		dataType:'text',
		data:{
			'url':url
		},
		success:function(data){
			alert(data);
			return;
		}
	});
};
