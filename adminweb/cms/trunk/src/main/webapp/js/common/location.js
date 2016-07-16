
var  localUrl="/agentManage/ajax/zone/";
function  locationInit(cityPageId,distPageId,blockPageId,selectCityId,selectDistId,selectBlockId){
	getDist(cityPageId,localUrl+0, selectCityId);
	if(selectCityId!=null&&selectCityId!=''&&selectCityId!='-2'){
	    getDist(distPageId,localUrl+selectCityId, selectDistId);
	}
	if(selectDistId!=null&&selectDistId!=''&&selectDistId!='-2'){
	   getDist(blockPageId,localUrl+selectDistId, selectBlockId);
	}
	$("#"+cityPageId).bind("change", function() {cityChange(distPageId,blockPageId,$(this).val());});
	$("#"+distPageId).bind("change", function() {distChange(blockPageId,$(this).val());});
}



// ajax城市复位
function cityChange(distPageId,blockPageId,id) {
	if(id!=null&&id!=''&&id!='-2'){
	    getDist(distPageId, localUrl + id,-1);
	}else{
		$("#"+distPageId).empty().append("<option value='-2'>请选择区域</option>");
		$("#"+blockPageId).empty().append("<option value='-2'>请选择商圈</option>");
	}
	
}

// ajax区域复位
function distChange(blockPageId,id) {
	if(id!=null&&id!=''&&id!='-2'){
		getDist(blockPageId, localUrl + id,-1);
	}else{
		$("#"+blockPageId).empty().append("<option value='-2'>请选择商圈</option>");
	}
	
}
function getDist(selectId, _url, selectedValue) {
	_url = encodeURI(_url);
	_url = encodeURI(_url);
	var selectElem = "#" + selectId;
	var def;
	if (selectId.indexOf("city")>=0) {
		def = "请选择城市";
	} else if (selectId.indexOf("dist")>=0) {
		def = "请选择区域";
	} else {
		def = "请选择商圈";
	}
	$.ajax({
		type : "GET",
		async : false,
		url : _url,
		dataType : "json",
		success : function(data) {
			$(selectElem).empty();
			var option = "<option value='-2'>" + def + "</option>";
			$(option).appendTo(selectElem);
			var list = data.distList;
			if (list != null && list.length > 0) {
				for (i in list) {
					var local = list[i];
					var option;
					if (local.localid == selectedValue) {
						option = "<option value='" + local.localid
								+ "' selected=\"selected\">" + local.localname
								+ "</option>";
					} else {
						option = "<option value='" + local.localid + "'>"
								+ local.localname + "</option>";
					}
					$(option).appendTo(selectElem);
				}
			}
		}
	});
}