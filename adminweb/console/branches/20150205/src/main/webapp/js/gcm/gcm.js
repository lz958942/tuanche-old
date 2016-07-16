//添加品牌      type  添加品牌的车型分类   1 ：热门  2：高端   3：国产   4：合资
function addBrand(type){
	//车型分类id
	var tId = 'carbrand_tem'+type;
	//下标
	var parsonNum = $("#"+tId).find(".td_mapper").length;
	var nameStr,vender;
	switch (type) {
		case 1:nameStr='hotStyle';vender=null;break;
		case 2:nameStr='highStyle';vender=2;break;
		case 3:nameStr='homemadeStyle';vender=3;break;
		case 4:nameStr='jointStyle';vender=1;break;
		default:nameStr='';break;
	}

//	var str = '<tr><td class="td_mapper">选择品牌：</td><td>'+
//		'<input class="dateCss" type="text" onclick="selectCarStyleshow(this)" readonly="readonly">'+
//		'<input  type="hidden"  name="'+nameStr+'['+parsonNum+'].brandId">'+
//		'&nbsp;排序：<input  type="text"  name="'+nameStr+'['+parsonNum+'].sort"  style="width: 20px;">'+
//		'</td><td><input type="button" value="删除" onclick="delBrand(this)"/></td></tr>';

	var str = '<tr>'+
	'<td class="td_mapper">选择品牌：</td>'+
	'<td>'+
		'<select name="'+nameStr+'['+parsonNum+'].brandId">'+
		'<option value="">请选择</option>';
	
	if(vender==null){
		str += $("#brand_s").html();
	}else{
		var brand_s = $("#brand_s").children();
		var venderStr = "vender_"+vender;
		for (var int = 0; int < brand_s.length; int++) {
			if($(brand_s[int]).hasClass(venderStr)){
				str += '<option value="'+$(brand_s[int]).val()+'">'+$(brand_s[int]).html()+'</option>';
			}
		}
	}
	str += '</select>'+
		'&nbsp;排序：<input  type="text"  name="'+nameStr+'['+parsonNum+'].sort" style="width: 20px;">'+
	'</td>'+
	'<td class="buttons">'+
		'<input class="btn btn-info"  type="button" value="删除" onclick="delBrand(this)"/>'+
	'</td>'+
    '</tr>';


	$("#"+tId +" tbody").append(str);
}

/**
 * 删除车型品牌
 * @param _this  当前元素
 * @param gcmBrandId  车型品牌id
 */
function delBrand(_this,gcmBrandId){
	$(_this).parent().parent().remove();
}

/**
 * 添加资讯
 */
function addZixun(){
	//个数  （下一个的下标）
	var parsonNum = $("#zixun").find(".zixunC").length;
	var str = '<tr class="zixunC">'+
			   '<td class="td_mapper">标题:</td><td><input type="text" name="carNews['+parsonNum+'].title"/></td>'+
			   '<td class="td_mapper">链接:</td><td><input type="text" name="carNews['+parsonNum+'].url"/></td>'+
			   '<td class="buttons"><input class="btn btn-info" type="button" value="删除" onclick="deleteZixun(this)"/></td>'+
			   '</tr>';
	$("#zixun tbody").append(str);
}
function deleteZixun(_this){
	$(_this).parent().parent().remove();
}

/**
 * 添加汽车回顾
 */
function addCarReview(){
	//个数  （下一个的下标）
	var parsonNum = $("#carReview").find(".carReviewC").length;
	var str =   '<tr class="carReviewC"><td class="td_mapper">标题：</td>'+
				'<td><input type="text" name="carReview['+parsonNum+'].title"/></td>'+
				'<td class="td_mapper ">链接:</td>'+
				'<td><input type="text" name="carReview['+parsonNum+'].url"/></td>'+
				'<td class="td_mapper">车展图片：</td><td>'+
				'<img  id="cheZImg_'+parsonNum+'" width="160px" src="/images/upload.jpg" onclick="$(\'#cheZFile_'+parsonNum+'\').trigger(\'click\')"/>'+
				'<input type="hidden" id="cheZPic_'+parsonNum+'" name="carReview['+parsonNum+'].image"/></td>'+
				'<td class="buttons"><input class="btn btn-info" type="button" value="删除" onclick="deleteCarReview(this)"/></td></tr>'
	$("#carReview tbody").append(str);
	
	var uploadStr = '<input id="cheZFile_'+parsonNum+'" name="cheZFile_'+parsonNum+'" type="file"  onchange="upload(\'cheZFile_'+parsonNum+'\',\'cheZPic_'+parsonNum+'\',\'cheZImg_'+parsonNum+'\',\'1\')"/>';
	$("#hidFileDiv").append(uploadStr);
}

function deleteCarReview(_this){
	$(_this).parent().parent().next().remove();
	$(_this).parent().parent().remove();
}
//车展巡展 type 1:往期车展   2：县市巡展
function addcarGoShow(type){
	var parsonNum ,divId;
	if(type==1){
		parsonNum = $("#oldCarShow").find(".oldCarShow").length;
		divId = "oldCarShow";
	}else{
		parsonNum = $("#countyGoShow").find(".countyGoShow").length;
		divId = "countyGoShow";
	}
	var str = '<tr class="'+divId+'"><td class="td_mapper">标题:</td>'+
   	'<td><input type="text" name="'+divId+'['+parsonNum+'].title"/></td>'+
   	'<td class="td_mapper">链接:</td>'+
   	'<td><input type="text" name="'+divId+'['+parsonNum+'].url"/></td>'+
   	'<td class="buttons"><input class="btn btn-info" type="button" value="删除" onclick="deletecarGoShow(this)"/></td></tr>';
	
	$("#"+divId+" tbody").append(str);
} 
function deletecarGoShow(_this){
	$(_this).parent().parent().remove();
} 
function goline(gcmId,isOnline,_this){
	 $.ajax({
         type: "POST",
         async:false,
         url: "/gcm/onLineGcmCity",
         data: {"gcmId":gcmId,"isOnline":isOnline},
         dataType: "json",
         success:function(data){
        	 if(isOnline==1){
        		 $(_this).html("上线");
        		 $(_this).attr("onclick","goline("+gcmId+","+2+",this)");
        	 }else{
        		 $(_this).html("下线");
        		 $(_this).attr("onclick","goline("+gcmId+","+1+",this)");
        	 }
			
         },
         error:function(){
         }
	 });
}
$(function(){
	//删除团车会车型品牌
	$(".deleteGcmBrand").live("click",function(){
		if(confirm("确定要删除吗?")){
			var gcmBrandId = $(this).attr("gcmBrandId");
			var thisTr = $(this);
			 $.ajax({
	             type: "POST",
	             async:false,
	             url: "/gcm/deleteGcmBrand",
	             data: {"gcmBrandId":gcmBrandId},
		         dataType: "json",
	             success:function(data){
					 $(thisTr).parent().parent().remove();
	             },
	             error:function(){
	             }
			 });
		}
	});
	//删除团车会 内容
	$(".deleteContent").live("click",function(){
		if(confirm("确定要删除吗?")){
			var gcmContentId = $(this).attr("gcmContentId");
			var thisTr = $(this);
			 $.ajax({
	             type: "POST",
	             async:false,
	             url: "/gcm/deleteGcmContent",
	             data: {"gcmContentId":gcmContentId},
		         dataType: "json",
	             success:function(data){
	            	 if($(thisTr).parent().parent().hasClass("carReviewC")){
	            		 $(thisTr).parent().parent().next().remove();
	            	 }
					 $(thisTr).parent().parent().remove();
	             },
	             error:function(){
	             }
			 });
		}
	});
	//4s店
	$("#addDian").live("click",function(){
		var personCount = $("#dian").find(".dianTr").length;
		var str = '<tr class="dianTr"><td>公司名称：</td>'+
			'<td class="td_mapper"><input type="text" name=""/>&nbsp;<input class="btn btn-info dian_query" type="button" dian_count="'+personCount+'" value="查询"/>&nbsp;'+
			'<select name="showShops['+personCount+'].dianId" class="dianSelect"><option value="-1">请选择</option></select>'+
			'&nbsp;排序:<input type="text" style="width: 20px;" name="showShops['+personCount+'].sort" /></td>'+
			'<td><input type="button" class="btn btn-info" id="deleteDian" value="删除"/></td></tr>';
		$("#dianTable tbody").append(str);
	});
	$("#deleteDian").live("click",function(){
		$(this).parent().parent().remove();
	});
	
	//4s店查询
	$(".dian_query").live("click",function(){
		var dian_name = $(this).prev().val();
		var dian_count = $(this).attr("dian_count");
		$.ajax({
            type: "POST",
            async:false,
            url: "/gcm/getCustomer",
            data: {"customerName":dian_name},
	         dataType: "json",
            success:function(data){
            	var seleStr = ''; 
            	if(data.length == 0){
            		alert("查无此店");
            		return false;
            	}
            	for(var i=0;i<data.length;i++){
            		seleStr += '<option value="'+data[i].id+'">'+data[i].customer+'</option>';
            	}
            	$("[name='showShops["+dian_count+"].dianId']").html(seleStr);
            },
            error:function(){
            }
		 });
	})
})














