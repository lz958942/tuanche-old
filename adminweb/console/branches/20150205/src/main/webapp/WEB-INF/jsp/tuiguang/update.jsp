<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>推广修改</title>
</head>
<body>
<form id="listForm" action="/promo/toUpdate" method="POST">
	<input type="hidden" id="proid" name="id" value="${promotion.id }">
	<div id="man_zone">
		<div>
			<div class="b-con a-form">
	          	<div class="pd5">
	          		<label class="pr15">选择城市:
	          			<select id="cityId" name="cityId">
							<option value="">请选择</option>
							<c:forEach items="${citys }" var="citys">
								<option value="${citys.value.id }" ${citys.value.id==promotion.cityId?'selected':'' }>${citys.value.orderName}</option>
							</c:forEach>
						</select>
	          		</label>
	          		<label class="pr15">城市code:
	          			<input type="text" id="cityCode" name="cityCode" value="${promotion.cityCode }"/>
	          		</label>
	          		<label class="pr15">选择品牌:
	          			<select id="brandId" name="brandId">
							<option value="">请选择</option>
							<c:forEach items="${brands }" var="brands">
								<option value="${brands.id }" ${brands.id==promotion.brandId?'selected':'' }>${brands.orderName} ${brands.name }</option>
							</c:forEach>
						</select>
	          		</label>
	          		<label class="pr15">推广费用:
	          			<input type="text" id="money" name="money" value="${promotion.money }"/>
	          		</label>
	          		<label class="pr15">推广日期:
	                      <input id="spendTime" type='text' name='spendTime' class="querytime span2" value="${promotion.spendTime }" readonly="readonly" autocomplete="off" />
	          		</label>
	          		<label style="text-align: center">
		          		<input id="updateBtn" type="button" value="修改" class="btn btn-info"/>
	          		</label>
	          	</div>
	         </div>
		</div>
	</div>
</form>
</body>
<script type="text/javascript">
var spendTime;
$("#spendTime").live("blur",function(){
	spendTime = $("#spendTime").val();
});

$('.querytime').live("click",function() {
	WdatePicker({
	isShowClear:true,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});

	var cityId=$("#cityId").val();
	var brandId=$("#brandId").val();
	if(spendTime==null){
		spendTime=$("#spendTime").val();
	}
	var id=$("#proid").val();
	$("#updateBtn").click(function(){
		$.ajax({
			type:'post',
			url:'/promo/selectSameCount',
			dataType:'text',
			data:{
				cityId:cityId,
				brandId:brandId,
				spendTime:spendTime,
				id:id,
			},
			success:function(data){
				if(data!=null&&data!=''){
					alert(data);
				}else{
					$("#listForm").submit();
				}
			}
		}) 
	});
</script>
</html>