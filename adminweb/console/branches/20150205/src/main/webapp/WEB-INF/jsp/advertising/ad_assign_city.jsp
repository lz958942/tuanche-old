<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网console系统</title>
<style type="text/css">.checkbox input{_width:auto;_float:left;}</style>
</head>

<body>
<form id='assignCity' action="/advertising/assignCitySave">
<input type="hidden" value="" id="checkItemSave" name="checkItemSave"/>
<input type="hidden" value="${adContent.id}" id="adContentId" name="adContentId"/>
<input type="hidden" id="selectedCityId" value="${cityList}" />
<div id="man_zone">
<br>
<p style="font-size:150%;text-align:center">城市选择</p>
<br>
<table class="table_style table table-bordered">
<td><input type="hidden" value="0" id="selectedCity"/><input type="checkbox" class="checkItem"/>&nbsp;&nbsp;全国</td>
<c:forEach var="item" items="${cityMap}" varStatus="itemCount">
<c:if test="${(itemCount.count+1)%4==0}"><td><input type="hidden" value="${item.key}" id="selectedCity"/><input type="checkbox" class="checkItem"/>&nbsp;&nbsp;${item.value}</td><tr></c:if>
<c:if test="${(itemCount.count+1)%4!=0}"><td><input type="hidden" value="${item.key}" id="selectedCity"/><input type="checkbox" class="checkItem"/>&nbsp;&nbsp;${item.value}</td></c:if>
</c:forEach>
</table>
</div>
<br>
<div align="center">
<input type="button" class="btn" value="保存" onclick="javascript:assignCitySubmit();" />
<input type="button"  class="btn" value="取消"  onclick="cancleCheckCity()"/>
</div>
</form>
<script>
var cityArray=new Array();
cityArray=$("#selectedCityId").val().substring(1,$("#selectedCityId").val().lastIndexOf("]")).split(","); 
$(document).ready(function(){
	$(".checkItem").each(function(){	
		 for(var i=0;i<cityArray.length;i++){
			 if(cityArray[i].replace(/[ ]/g,"")==$(this).parent().find("#selectedCity").val().replace(/[ ]/g,"")){
				 $(this).attr("checked","checked");
			 }
		 }
	});
});
function cancleCheckCity(){
	$("#assignCity").attr("action","/advertising/contentList");
	$("#assignCity").submit();
}
function assignCitySubmit(){
	var checkItemSave='';
	$(".checkItem").each(function(){
		if($(this).attr("checked")=="checked"){
			checkItemSave+=$(this).parent().find("#selectedCity").val();
			checkItemSave+=',';
		}
	});
	$("#checkItemSave").val(checkItemSave);
	$("#assignCity").submit();
	
}

</script>

</body>

</html>