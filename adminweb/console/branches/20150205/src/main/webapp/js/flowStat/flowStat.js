$(document).ready(bindWidgets);

/**
 * 事件绑定页面组件
 */
function bindWidgets() {
	$("#subFlow").click(sub);
	$("#updateFlow").click(sub);
	$("#addFlowStat").click(toAdd);
	$("#resetFlowCondition").click(resetFlowCondition);
	$("#uploadExcel").click(uploadFlow);
	getJsonData();
}

var content, sDate, eDate, domain, dataType;
function getJsonData() {
	$.ajaxSettings.traditional = true;
	sDate = $("#startDate").val();
	eDate = $("#endDate").val();
	domain = $("#domain").val();
	dataType = $("#dataType").val();
	from=$("#from").val();
	type=$("#type").val();
	//alert("startDate: " + sDate + ", endDate: " + eDate + ", serviceTypes: " + chk_value);
	$.getJSON("/flowStatManage/flowJsonData",
			{startDate:sDate, endDate:eDate, domain:domain, dataType:dataType,from:from,type:type}, 
			function(data) {
				content = data;
				FusionCharts.ready(chartContainer);
			});
}

/**
* 渲染组件
*/
function chartContainer() {
    var revenueChart = new FusionCharts({
      type: "msline",
      renderAt: "chartContainer",
      width: "1100",
      height: "500",
      dataFormat: "json",
      dataSource: content
  });
  revenueChart.render("chartContainer");
}

/**
 * 收录统计提交
 */
function sub() {
	var f_Reg = /^-?\d+$/;
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
			    + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
			    + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			    + "|" // 允许IP和DOMAIN（域名）
			    + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
			    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
			    + "[a-z]{2,6})" // first level domain- .com or .museum
			    + "(:[0-9]{1,4})?" // 端口- :80
			    + "((/?)|" // a slash isn't required if there is no file name
			    + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	var domainReg = new RegExp(strRegex);
	var id = $("#id").val();
	var date = $("#date").val();
	var pv = $("#pv").val();
	var uv = $("#uv").val();
	var domain = $("#domain").val();
	var dataType = $("#dataType").val();
	
	if(!date) {
		alert("请填写日期");
		$("#date").focus();
		return;
	}
	if(!pv) {
		alert("请填写pv");
		$("#pv").focus();
		return;
	}
	if(!uv) {
		alert("请填写uv");
		$("#uv").focus();
		return;
	}
	if(!domain) {
		alert("请填写域名");
		$("#domain").focus();
		return;
	}
	if(!domainReg.test(domain)) {
		alert("域名格式不正确");
		$("#domain").focus();
		return;
	}
	if(isNaN(pv))
	{
		alert("pv必须是一个有效的数字");
		$("#pv").focus();
		return;
	}
	if(!f_Reg.test(pv) || pv < 0)
	{
		alert("pv必须是一个正整数");
		$("#pv").focus();
		return;
	}
	if(isNaN(uv))
	{
		alert("uv必须是一个有效的数字");
		$("#uv").focus();
		return;
	}
	if(!f_Reg.test(uv) || uv < 0)
	{
		alert("uv必须是一个正整数");
		$("#uv").focus();
		return;
	}

	var params = 
	{
		"id" : id,
		"date" : date,
		"pv" : pv,
		"uv" : uv,
		"domain" : domain,
		"dataType" : dataType
	};
	var url = "/flowStatManage/isExist";
	$.getJSON(url,params, function(data) {
		if (data > 0) {
			alert("数据重复，同一天不能存在相同的域名数据");
			isExist = true;
		}
		else {
			if(confirm("确认提交吗?")) {
				if(id) {
					$("#updateFlowStatForm").submit();
				}
				else {
					$("#addFlowStatForm").submit();
				}
			}
		}
	});
	
}

/**
 * 跳转到添加页面
 */
function toAdd() {
	$("#flowStatSearchForm").attr("action","/flowStatManage/toAdd");
	$("#flowStatSearchForm").submit();
}

/**
 * 清空查询条件
 */
function resetFlowCondition() {
	$("#startDate").val("");
	$("#endDate").val("");
	$("#domain").val("");
	$("#dataType").val("");
	
}

/**
 * 删除一条收录统计
 */
function deleteFlowStat(id) {
	if (confirm("确认删除吗?")) {
		$.ajax({
			type : 'POST',
			url : "/flowStatManage/deleteFlowStat?id=" + id,
			success : function(data) {
				alert(data);
				$("#flowStatSearchForm").submit();
			},
			error : function(data) {
				alert(data);
			}
		});
	}
}

/**
 * 编辑收录统计
 */
function updateFlowStatById(id) {
	$("#flowStatSearchForm").attr("action","/flowStatManage/toUpdate?id=" + id);
	$("#flowStatSearchForm").submit();
}

/**
 * 上传excel
 */
function uploadFlow() {
	var startDate = $("#startDateExcel").val();
	var endDate =$("#endDateExcel").val();
	var fileName = $("#uploadFile").val();

	//alert(startDate +","+ endDate);
	
	if(!fileName) {
		alert("请选择需要上传的文件")
		return;
	}
	
	if(fileName.indexOf(".xls") == -1) {
		alert("文件格式不正确");
		return;
	}
	
	if(confirm("确认要上传吗?")) {
		$("#flowStatSearchForm").attr("enctype","multipart/form-data");
		$("#flowStatSearchForm").attr("action","/flowStatManage/uploadExcel?startDate=" + startDate + "&endDate=" + endDate);
		$("#flowStatSearchForm").submit();
	}
	
}
