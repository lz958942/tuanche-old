var picHtml;
var tpHtml;
var tHtml;
$(function(){
	tHtml="<div id=\"dctitle_num1\"><label class=\"pr15\">标题(有则填):" +
			"<input type=\"hidden\" id=\"tid\" name=\"tid\" value=\"\"/>" +
			"<input type=\"hidden\" id=\"htype\" value=\"1\"/>"+
			"<input id=\"dcTitle\" type=\"text\" style=\"width: 35%\" maxlength=\"100\" name=\"dctitle\" value=\"\" /></label>" +
			"<label class=\"pr15\">排序：" +
			"<input id=\"dcSort\" type=\"text\" style=\"width: 15px;\" maxlength=\"4\" name=\"dcSort\" value=\"\" />" +
			"<span style=\"color:red;\">*</span>" +
			"</label>" +
			"<label class=\"pr15\">文字内容:" +
			"<textarea id=\"content\" name=\"content\" rows=\"4\" cols=\"5\" style=\"width: 350px\" maxlength=\"999\"></textarea>" +
			"</label>" +
			"<input id=\"dcTitleBtn_num1\" type=\"button\" value=\"添加\" class=\"btn btn-info\" onclick=\"addTitleHang();\"/>" +
			"</div>";
	picHtml="<div id=\"dcpic_num1\">图片:" +
			"<label class=\"pr15\" style=\"vertical-align: middle;\">" +
			"<input type=\"hidden\" id=\"pid\" name=\"pid\" value=\"\"/>" +
			"<input type=\"hidden\" id=\"htype\" value=\"2\"/>"+
			"<img id=\"dcPic_num1\" src='/images/upload.jpg' onclick=\"$('#picFile_num1').trigger('click')\"/>" +
			"<input type=\"hidden\" id=\"picUrl_num1\" name=\"picUrls\"  value=\"\" />" +
			"</label>" +
			"<label class=\"pr15\">排序：" +
			"<input id=\"picSort\" type=\"text\" style=\"width: 15px;\" maxlength=\"4\" name=\"picSort\" value=\"\" />" +
			"<span style=\"color:red;\">*</span>" +
			"</label>" +
			"<span style=\"width:100px;padding-right:16px;padding-left:16px;vertical-align:top\"></span>" +
			"<div style=\"display: none\">" +
			"<input id=\"picFile_num1\" name=\"picFile_num1\" type=\"file\"  onchange=\"upload('picFile_num1','picUrl_num1','dcPic_num1','4')\"/>" +
			"</div>" +
			"<input id=\"dcPicBtn_num1\" type=\"button\" value=\"添加\" class=\"btn btn-info\" onclick=\"addPicHang();\"/>" +
			"</div>";
	tpHtml="<div id=\"titAndPic_num1\">" +
			"<input type=\"hidden\" id=\"wid\" name=\"wid\" value=\"\"/> " +
			"<input type=\"hidden\" id=\"htype\" value=\"3\"/>"+
			"<label class=\"pr15\" style=\"vertical-align: middle;\">" +
			"<img id=\"tpPic_num1\" src='/images/upload.jpg' onclick=\"$('#tpFile_num1').trigger('click')\"/>" +
			"<input type=\"hidden\" id=\"tpicUrl_num1\" name=\"tpicUrl\"  value=\"\" />" +
			"</label>" +
			"<label class=\"pr15\">排序：" +
			"<input id=\"tpSort\" type=\"text\" style=\"width: 15px;\" maxlength=\"4\" name=\"tpSort\" value=\"\" />" +
			"<span style=\"color:red;\">*</span>" +
			"</label>" +
			"<label class=\"pr15\">文字内容:" +
			" <textarea id=\"contents_num1\" name=\"contents\" rows=\"4\" cols=\"5\" style=\"width: 350px\" maxlength=\"999\"></textarea>" +
			"</label>" +
			"<input id=\"titAndPicBtn_num1\" type=\"button\" value=\"添加\" class=\"btn btn-info\" onclick=\"addTitAndPicHang();\"/>" +
			"<span style=\"width:100px;padding-right:16px;padding-left:16px;vertical-align:top\"></span>" +
			"<div style=\"display: none\">" +
			"<input id=\"tpFile_num1\" name=\"tpFile_num1\" type=\"file\"  onchange=\"upload('tpFile_num1','tpicUrl_num1','tpPic_num1','4')\"/>" +
			"</div>" +
			"</div>";

/*	$("#addPlateForm").validate({
		errorPlacement: function(error, element) {					
			element.parent().find('.help-inline').html("");		
			error.appendTo( element.parent().find('.help-inline'));			
		}
	});*/

});

//时间格式
$('.querytime').live("click",function() {
	WdatePicker({
	isShowClear:true,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});
//显示标题表单
function showForm(){
	$("#addExpend").show();
}
//显示内容表单
function showContent(){
	$("#addContent").show();
}
//隐藏内容表单
function hideConDiv(baseId){
	window.location.href="/decorate/toExpendList?bid="+baseId+"";
}
//隐藏标题表单
function hideDiv(){
	$("#addExpend").hide();
	$("#addordel_num1").show();
}
//查询单个小标题信息
function selectPlateById(id){
	$.ajax({
		url:'/decorate/selectPlateById',
		type:'post',
		dataType:'json',
		data:{
			id:id,
		},
		success:function(data){
			if(data!=null&&data.decorateTemp!=null){
				showForm();
				var temp=data.decorateTemp;
				$("#addordel_num1").hide();
				$("#idDiv").val(temp.id);
				$("#plateIdDiv").val(temp.plateId);
				$("#titleDiv").val(temp.title);
				$("#contentStyleDiv").val(temp.titleContentStyle);
				$("#showStyleDiv").val(temp.titleShowStyle);
				$("#sortDiv").val(temp.sort);
			}
		}
		
	});
}

function selectContentById(id,type,baseId){
	$.ajax({
		url:'/decorate/selectContentByTempId',
		type:'post',
		dataType:'text',
		data:{
			id:id,
		},
		success:function(data){
				window.location.href="/decorate/selectContentById?id="+id+"&baseId="+baseId+"&type="+type;
				if(type=='1'){
					$("#dctitleDiv").show();
					$("#dcpicDiv").hide();
					$("#tuwenDiv").hide();
				}else if(type=='2'){
					$("#dctitleDiv").hide();
					$("#tuwenDiv").hide();
					$("#dcpicDiv").show();
				}else if(type=='3'){
					$("#dctitleDiv").hide();
					$("#tuwenDiv").show();
					$("#dcpicDiv").hide();
				}
		}
	});
}

//增加一行标题
var divnum=1;
var divnumt=1;
var titlenum=1;
var divnumtp=1;
function addExpHang(){
	divnum++;
	var divHtml=$("#div_num1").html();
	divHtml=divHtml.replace(/addordel_num1/,"addordel_num"+divnum);
	newHang="<div style='width:450px;margin:0 auto' id='div_num"+divnum+"'>"+divHtml+"</div>";
	$("#div_num1").parent().append(newHang);
	$("#addordel_num"+divnum).attr("onclick","deletehang(this.id);");
	$("#addordel_num"+divnum).val("删除");
}
//添加一行标题
function addTitleHang(){
	titlenum++;
	var tsize=parseInt($("#tsize").val());
	if(tsize!=null&&tsize!=''){
		titlenum+=(tsize+1);
	}
	tHtml2=tHtml.replace(/num1/g,"num"+titlenum);
	$("#dctitle_num1").parent().append(tHtml2);
	$("#dcTitleBtn_num"+titlenum).attr("onclick","deletehang(this.id);");
	$("#dcTitleBtn_num"+titlenum).val("删除");
}
//添加一行图片
function addPicHang(){
	divnumt++;
	var psize=parseInt($("#psize").val());
	if(psize!=null&&psize!=''){
		divnumt+=(psize+1);
	}
	picHtml2=picHtml.replace(/num1/g,"num"+divnumt);
	$("#dcpic_num1").parent().append(picHtml2);
	$("#dcPicBtn_num"+divnumt).attr("onclick","deletehang(this.id);");
	$("#dcPicBtn_num"+divnumt).val("删除");
}
//删除图片
function deleteContent(id,contentId,baseId){
	if(!confirm("确定要删除?")){
		return;
	}
	$.ajax({
		url:'/decorate/deleteContent',
		type:'post',
		dataType:'text',
		data:{
			id:id,
		},
		success:function(data){
			alert(data);
			window.location.href="/decorate/toExpendList?bid="+baseId+"";
		}
		
	});
}
//删除板块
function deletePlate(id,baseId){
	if(!confirm("确认要删除?")){
		return;
	}
	window.location.href="/decorate/deletePlate?id="+id+"&baseId="+baseId+"";
}
function addTitAndPicHang(){
	divnumtp++;
	var tpsize=parseInt($("#tpsize").val());
	if(tpsize!=null&&tpsize!=''){
		divnumtp+=(tpsize+1);
	}
	tpHtml2=tpHtml.replace(/num1/g,"num"+divnumtp);
	$("#tuwenDiv").append(tpHtml2);
	$("#titAndPicBtn_num"+divnumtp).attr("onclick","deletehang(this.id);");
	$("#titAndPicBtn_num"+divnumtp).val("删除");
}
//提交addPlate

function savePlate(){
	var flag=true;
	var plateId=$("#plateIdDiv").val();
	if(plateId == null || plateId == ''){
		alert("请选版块名称");
		return false;
	}
	$("input[name*='sort']").each(function(i){
		if($(this).val()===''){
			alert("请填入顺序(倒序显示)");
			flag=false;
			return false;
		}
	});
	if(!flag){
		return false;
	}
	$("#addPlateForm").submit();
}

function saveContent(){
	var type=$("#htype").val();
	var flag=true;
	if(type=='1'){
		$("input[name*='dcSort']").each(function(i){
			if($(this).val()===''){
				alert("请填入顺序(倒序显示)");
				flag=false;
				return false;
			}
		});
	}
	if(type=='2'){
		$("input[name*='picSort']").each(function(i){
			if($(this).val()===''){
				alert("请填入顺序(倒序显示)");
				flag=false;
				return false;
			}
		});
		if(flag){
			$("input[name*='picUrl']").each(function(i){
				if($(this).val()===''){
					alert("请上传图片");
					flag=false;
					return false;
				}
			});
		}
	}
	if(type=='3'){
		$("input[name*='tpSort']").each(function(i){
			if($(this).val()===''){
				alert("请填入顺序(倒序显示)");
				flag=false;
				return false;
			}
		});
		if(flag){
			$("input[name*='tpicUrl']").each(function(i){
				if($(this).val()===''){
					alert("请上传图片");
					flag=false;
					return false;
				}
			});
		}
	}
	if(!flag){
		return false;
	}
	$("#addContentForm").submit();
}

//删除行
function deletehang(id){
	$("#"+id).parent().remove();
}

//更改上线状态
function changelineStatus(id,status){
	$.ajax({
		url:'/decorate/changeOnline',
		type:'post',
		dataType:'text',
		data:{
			id:id,
			status:status,
		},
		success:function(date){
			
			changeOnlineText(id,status);
		}
	});
}
function changeOnlineText(id,status){
	if(status=='1'){
		$("#baseStatus_"+id).text('否');
		$("#changeStatus_"+id).text("上线");
		$("#changeStatus_"+id).attr("onclick","changelineStatus("+id+","+2+")");
	}else{
		$("#baseStatus_"+id).text('是');
		$("#changeStatus_"+id).text('下线');
		$("#changeStatus_"+id).attr("onclick","changelineStatus("+id+","+1+")");
	}
	console.log($("#changeStatus_"+id).html() + "===" + id);
}

//删除装饰文章
function deleteDecorate(id){
	if(!confirm("确认要删除?")){
		return;
	}
	$.ajax({
		url:'/decorate/deleteDecorate',
		type:'post',
		dataType:'text',
		data:{
			id:id,
		},
		success:function(date){
			$("#tr_"+id).remove();
		}
	});
}

//图片上传
function upload(fileId, picValueId, showPicId, type) {
	if (!checkImg(fileId)) {
		alert("图片格式错误！图片格式为jpg,png,gif,bmp");
		return;
	}
	$.ajaxFileUpload({// type 1专题 2adv 3 cms 4.decorate
		url : "/decorate/uploadPic?type=" + type + '&file=' + fileId,
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
//检查文件格式
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

//表单提交验证
function sub(){
	var title = $("#title").val().trim();
	var cityId=$("#cityId").val().trim();
	var kindId=$("#kindId").val().trim();
	var topPic = $("#topPicUrl").val().trim();
	var picUrl=$("#picUrl").val().trim(); 
	var prePrice=$("#pre_price").val().trim();
	var marPrice=$("#mar_price").val().trim();
	var keywords=$("#keywords").val().trim();
	var decDesc=$("#dec_desc").val().trim();
	if (kindId == null || '' == kindId) {
		alert("请选择分类");
		return;
	}
	if (cityId == null || '' == cityId) {
		alert("请选择城市");
		return;
	}
	if (title == null || '' == title) {
		alert("装饰名称不能为空");
		return;
	}
	if (decDesc == null || '' == decDesc) {
		alert("描述不能为空");
		return;
	}
	if (keywords == null || '' == keywords) {
		alert("关键词不能为空");
		return;
	}
	if ((prePrice==null||''==prePrice)||isNaN(prePrice)) {
		alert("价格请输入数字");
		return;
	}
	if ((marPrice==null||''==marPrice)||isNaN(marPrice)) {
		alert("请输入数字");
		return;
	}
	if (topPic == null || '' == topPic) {
		alert("请上传效果图");
		return;
	}
	if (picUrl == null || '' == picUrl) {
		alert("请上传列表图");
		return;
	}
	
	$("#addBaseForm").submit();
}

//清空
function clearSearch(){
	$("#id").val('');
	$("#cityId").val('');
	$("#kindId").val('');
	$("#title").val('');
	$("#starttime").val('');
	$("#endtime").val('');
	$("#status").val('');
	$("#searchForm").submit();
}