<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加团购推荐</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<style type="text/css">
	*{
	
font-size:12px;
}
</style>
<script type="text/javascript">
function saveConfig(){
	var sta=true;
	$("[sta=p]").each(function(){
		if($(this).val()==null||$(this).val()==""){
			sta=false;
			alert($(this).attr("staName")+"不能为空！");
			sta=false;
			return false;
			}
		}
		);
	if(sta){
		if($("[name='carStyleNameR']:checked").length==0){
			alert("页面车型必须选择！");
			return false;
		}
		if($("[name='carStyleName']:checked").length==0){
			alert("查询车型必须选择！");
			return false;
		}
	$("#from1").submit();
	}
}
function getBrand(cid){
	if(cid){
	$.post("/json/r12n/getBrand",{cityId:cid},function(result){
		$("#r_b span").remove();
			for(var i=0;i < result.length;i++ ){
				$("#r_b").append("<span style='display:inline-block;width:85px'><input type='checkbox'  onclick='getRCarAll(this,1)' name='' cityId='"+result[i].cityId+"' value="+result[i].brandId+">"+result[i].brandName+"</span>");
			}
		},'json');
	}else{
		$("#r_b span").remove();
		$("#r_c span").remove();
	}
}
function getRCarAll(type,sta){
	var $type=$(type);
	var $cityId=$type.attr("cityId");
	var $bid=$type.val();
	var $status="";
	if(sta==1){
		$("#r_c span [name='carStyleNameR']").each(function(){
			if(!$(this).attr("checked")){
				$(this).parent().remove();
			}
		});	
		$status="#r_c";
	}else{
		$status="#car";
	}
	if($type.attr("checked")){
		$.post("/json/r12n/getCar",{cityId:$cityId,brandId:$bid},function(result){
		for(var i=0;i < result.length;i++){
			$($status).append("<span brandId='"+result[i].brandId+"' style='display:inline-block;width:85px'><input type='checkbox'   name='carStyleNameR'  value="+$bid+"-"+result[i].carstyleId+">"+result[i].carStyleName+"</span>");
		}
		},'json');
	}else{
		$($status+" span[brandId='"+$bid+"']").remove();
	}
}

//查询品牌
function getCarAll(type){
	var $type=$(type);
	var $bid= $type.val();
	if($type.attr("checked")){
		$("#s_car [name='carStyleName']").each(function(){
			if(!$(this).attr("checked")){
				$(this).parent().remove();
			}
		});	
		/*  $("#s_car span input:checked").parent().siblings().remove();  */
		$.post("/json/carstyle/ajaxStyle",{brandID:$bid},function(result){
			for(var i=0;i< result.length;i++){
				$("#s_car").append("<span brandId='"+$bid+"' style='display:inline-block;width:100px'><input type='checkbox'   name='carStyleName'  value="+$bid+"-"+result[i].id+">"+result[i].style+"</span>");	
			}
		},'json');		
	}else{
		$("#s_car span[brandId='"+$bid+"']").remove();
	}
}
</script>
</head>
<body>
<input type="hidden" id="tytpe">
<div style="height: 30px">
	<c:if test="${bean==null}">
	<font style="float:left"> 当前位置：推荐管理-->添加推荐</font>
	</c:if>
	<c:if test="${bean!=null}">
	<font style="float:left"> 当前位置：推荐管理-->修改推荐</font>
	</c:if>
</div>
<form  id="from1" action="/r12n/add/r12n.do" method="post">
<input type="hidden" id="id" name="id" value="${bean.id}">
	<table class="table_style table table-bordered" >
<tr>
<td style="width: 80px;height: 40px">城市：</td>
		<td>
					<select  sta='p' staName="城市" id="cityId" name="cityId" style="width: 200px" onchange="getBrand(this.options[this.options.selectedIndex].value)">
					<option value="" onclick="">请选择</option>
						<c:forEach items="${citys}" var="ct">
							<option  <c:if test="${review.cityId ==ct.value.id}">selected="select"</c:if> value="${ct.value.id }">${ct.value.name}</option>
						</c:forEach>
					</select>
		</td>
		<td style="width: 80px">页面类型：</td>
		<td id="g_b_id">
		<input type="radio" name="type" value="1">品牌页面
		<input type="radio" name="type" value="2">车型页面
		<input type="radio" name="type" checked="checked" value="3">所有页面
		 <font color="red">*</font>
		</td>
</tr>
		<tr>
		<td class="ti">页面推荐品牌：&nbsp;</td>
		<td id="r_b" style="width: 360px">
			
		</td>
			<td class="ti">页面推荐车型:</td>
			<td id="r_c">
				
			</td>
		</tr>
		<tr>
			<td>查询品牌：</td>
		<td>
		<c:forEach items="${beands }" var="b">
			<span style='display:inline-block;width:85px'><input onclick='getCarAll(this)' type="checkbox"name="" value="${b.key }">${b.value.name }</span>
		</c:forEach>
		</td>
		<td>查询车型：</td>
		<td id="s_car">
			
		</td>
		</tr>
		<tr>
		<td>状态：</td>
		<td>
			<select name="status">
				<option  value="0">不上线</option>
				<option  value="1">上线</option>
			</select>
		</td>
		<td>操作：</td>
		<td>
			<div>
	<c:if test="${bean.id!=null }">
		<input type="button"  value="修改" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">	
	</c:if>
	<c:if test="${bean.id==null }">
		<input type="button"  value="保存" onclick="saveConfig()"  style="border: 0;background-color: #006dcc;background-image: linear-gradient(to bottom, #08c, #04c); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);color: #fff;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">	
	</c:if>
	</div>
		</td>
		</tr>
	</table>
	 </form>
</body>
</html>