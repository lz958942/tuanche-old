$(function(){
	changeBrand("brandId");
	var pageType;
	if(($("#pageType").length)>0){
		pageType=$("#pageType").val();
	}
	if(pageType!="1"){
		$("select[id*=carStyleId]").html("<option value=''>--请选择--</option>");
	}
});
function changeBrand(id){
	var num;
	var brandId;
	var carStyleId;
	if(id.indexOf("num")>0){
		num=id.substring(id.indexOf("_"),id.length);
		brandId="brandId"+num;
		carStyleId="carStyleId"+num;
	}else{
		brandId=id;
		carStyleId="carStyleId";
	}
	brandId=$("#"+id).val();
	carStyleHtml=$("#"+carStyleId);
    carStyleId=$("#"+carStyleId).val();
    if(brandId==''||brandId==null){
		carStyleHtml.html("<option value=''>--请选择--</option>");
	}else{
		carStyleHtml.html("<option value=''>--请选择--</option>");
		$.ajax({
			url:"/keyword/getCarStyleById",
			type:"post",
			dataType:"json",
			data:{
				pid:brandId
			},
			success:function(data){
				var list=data.carStyleList;
				var length=data.carStyleList.length;
				$(list).each(function(i){
					if(list[i].id==carStyleId){
						var option="<option value="+list[i].id+" selected='selected'>"+list[i].carStyleName+"</option>";
					}else{
						var option="<option value="+list[i].id+">"+list[i].carStyleName+"</option>";
					}
					carStyleHtml.append(option);
				});
			}
			
		});
	}
}

