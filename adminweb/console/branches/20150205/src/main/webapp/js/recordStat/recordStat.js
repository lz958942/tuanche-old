$(document).ready(bindWidgets);

/**
 * 事件绑定页面组件
 */
function bindWidgets() {
	$("#subRecord").click(sub);
	$("#updateRecord").click(sub);
	$("#addRecordStat").click(toAdd);
	$("#resetCondition").click(resetCondition);
	getJsonData();
}

var content, sDate, eDate;
function getJsonData() {
	$.ajaxSettings.traditional = true;
	sDate = $("#startDate").val();
	eDate = $("#endDate").val();
	//alert("startDate: " + sDate + ", endDate: " + eDate + ", serviceTypes: " + chk_value);
	$.getJSON(
			"/recordStatManage/flowJsonData",
			{startDate:sDate, endDate:eDate}, 
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
	var id = $("#id").val();
	var date = $("#date").val();
	var siteRecord = $("#siteRecord").val();
	var indexNumber = $("#indexNumber").val();
	var queryEngine = $("#queryEngine").val();
	var isExist = false;
	if(!date) {
		alert("请填写日期");
		$("#date").focus();
		return;
	}
	if(!siteRecord) {
		alert("请填写网站site收录");
		$("#siteRecord").focus();
		return;
	}
	if(!indexNumber) {
		alert("请填写索引量");
		$("#indexNumber").focus();
		return;
	}
	if(!queryEngine) {
		alert("请选择搜索引擎");
		$("#queryEngine").focus();
		return;
	}
	if(isNaN(siteRecord))
	{
		alert("网站site收录必须是一个有效的数字");
		$("#siteRecord").focus();
		return;
	}
	if(!f_Reg.test(siteRecord) || siteRecord < 0)
	{
		alert("网站site收录必须是一个正整数");
		$("#siteRecord").focus();
		return;
	}
	if(isNaN(indexNumber))
	{
		alert("索引量必须是一个有效的数字");
		$("#indexNumber").focus();
		return;
	}
	if(!f_Reg.test(indexNumber) || indexNumber < 0)
	{
		alert("索引量必须是一个正整数");
		$("#indexNumber").focus();
		return;
	}

	var params = 
	{
		"id" : $("#id").val(),
		"date" : $("#date").val().trim(),
		"siteRecord" : $("#siteRecord").val().trim(),
		"indexNumber" : $("#indexNumber").val().trim(),
		"queryEngine" : $("select[name='queryEngine']").val().trim(),
	};
	var url = "/recordStatManage/isExist";
	/*
	$.post(url, params, function(data) 
	{
		alert(data.toString());
		if (data > 0)
		{
			alert("数据重复，同一天不能存在相同的搜索引擎数据");
			isExist = true;
		}
		else {
			if(confirm("确认提交吗?")) {
				if(id) {
					$("#updateRecordStatForm").submit();
				}
				else {
					$("#addRecordStatForm").submit();
				}
			}
		}
	});
	*/

	$.getJSON(url,params, function(data) {
		if (data > 0) {
			alert("数据重复，同一天不能存在相同的搜索引擎数据");
			isExist = true;
		}
		else {
			if(confirm("确认提交吗?")) {
				if(id) {
					$("#updateRecordStatForm").submit();
				}
				else {
					$("#addRecordStatForm").submit();
				}
			}
		}
	});
}

/**
 * 跳转到添加页面
 */
function toAdd() {
	$("#recordStatSearchForm").attr("action","/recordStatManage/toAdd");
	$("#recordStatSearchForm").submit();
}

/**
 * 清空查询条件
 */
function resetCondition() {
	$("#startDate").val("");
	$("#endDate").val("");
}

/**
 * 删除一条收录统计
 */
function deleteRecordStat(id) {
	if (confirm("确认删除吗?")) {
		$.ajax({
			type : 'POST',
			url : "/recordStatManage/deleteRecordStat?id=" + id,
			success : function(data) {
				alert(data);
				$("#recordStatSearchForm").submit();
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
function updateRecordStatById(id) {
	$("#recordStatSearchForm").attr("action","/recordStatManage/toUpdate?id=" + id);
	$("#recordStatSearchForm").submit();
}
