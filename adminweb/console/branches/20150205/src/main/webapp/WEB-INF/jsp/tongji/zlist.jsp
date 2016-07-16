<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>站点报名查询</title>
</head>
<body>
<form method="post" action="/apply/zlist" id='downexcel' name='downexcel'>
<div id="man_zone">
	<div>
		<div class="b-con a-form">
			<input type="hidden" id='styleId' value='${styleId}'/>
          	<div class="pd5">
          		<label class="pr15">选择城市:
          		<select name="cityId" id="cityId">
          		<option value="">请选择</option>
          		<c:forEach items="${citys}" var="city">
          		<option value="${city.value.id}"<c:if test="${city.value.id==cityId}">selected='selected'</c:if>>${city.value.orderName}</option>
          		</c:forEach>
          		</select>
          		</label>
          		<label class="pr15">选择品牌:
          			<select name="brandName" onchange="getStylemy(this.options[this.options.selectedIndex].value)" id="brandName">
						<option value="">请选择</option>
						<c:forEach var="brand" items="${brands}">
							<option value="${brand.id}"<c:if test="${brand.id==brandName}">selected='selected'</c:if> >${brand.typepinyI}-${brand.name}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">选择车型:
					<select name="styleId" name='pppid' id='muio'>
						<option value="">请选择</option>
					</select>
          		</label>
          		<label class="pr15">页面类型:
          		<select name="pageType" id="pageType">
          		<option value="">请选择</option>
          		<option value="200" <c:if test="${pagetype==200}">selected='selected'</c:if>>品牌底层页</option>
          		<option value="300" <c:if test="${pagetype==300}">selected='selected'</c:if>>车型底层页</option>
          		</select>
          		</label>
          		<label class="pr15">
          			日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:
          			<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='starttime' class="querytime span2" value="<fmt:formatDate value="${starttime}" pattern="yyyy-MM-dd" />" readonly="readonly" autocomplete="off" />
                      	</div>-
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='endtime' class="querytime span2" value="<fmt:formatDate value="${endtime}" pattern="yyyy-MM-dd" />" readonly="readonly" autocomplete="off" />
                      	</div>
          		</label>
          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;&nbsp;
          		<a  class="btn btn-info" id="download" onclick='downExcel()'>下载详细</a>
          		<input type="button" value="查询汇总" class="btn btn-info" onclick='selectAll()'/>&nbsp;&nbsp;
          		<a  class="btn btn-info" id="download" onclick='downAllExcel()'>下载汇总</a>
          	</div>
         </div>
	</div>
	<div class="rb-con">
		<div class="over-auto">
			<table class="table table-bordered chargeTable">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th>日期</th>
						<th>城市</th>
						<c:if test="${flag==1}">
						<th>品牌</th>
						<th>车型</th>
						<th>页面类型</th>
						</c:if>
						<th>pv</th>
						<th>uv</th>
						<th>转化率(%)</th>
						<th>报名数</th>
					</tr>
	          	</thead>
	          	<tbody>
						<c:forEach items="${rate}" var="rate">
						<tr style="background:none repeat scroll 0 0 #E8F1FD">
							<td>${rate.date}</td>
		  					<td>${func:getallCity(rate.cityId)}</td>
		  					<c:if test="${flag==1}">
		  					<td>${func:getallBrand(rate.brandId)}</td>
		  					<td>${func:getallStyle(rate.styleId)}</td>
		  					<td>${func:getPageType(rate.pageType)}</td>	
		  					</c:if>
							<td>${rate.pv}</td>
							<td>${rate.uv}</td>
							<td>${rate.rate}</td>
							<td style='text-align:right'>${rate.applyNumber}&nbsp;&nbsp;&nbsp;人</td>
						</tr>
						</c:forEach>
	          	</tbody>
	          </table>
	     </div>
	</div>
</div>
<c:if test="${flag==1}">
<div class="page_and_btn" style="text-align:center;">
   <jsp:include page="/WEB-INF/snippets/page.jsp" />
</div>
</c:if>
<c:if test="${flag==null}">
<div class="page_and_btn" style="text-align:center;">
   <jsp:include page="/WEB-INF/snippets/page1.jsp" />
</div>
</c:if>
</form>
<script type="text/javascript">
$('.querytime').live("click",function() {
	WdatePicker({
	isShowClear:false,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});
	function downExcel(){
		$("#downexcel").attr('action',"/apply/downloadRate");
		$("#downexcel").submit();
		$("#downexcel").attr('action',"/apply/zlist");
	}
	function selectAll(){
		var brand=$("#brandName").val();
		var pageType=$("#pageType").val();
		if(brand!=""){
			alert("对不起，汇总暂不支持品牌汇总");
			return;
		}
		if(pageType!=""){
			alert("对不起，汇总暂不支持页面类型汇总");
			return;
		}
		$("#downexcel").attr('action',"/apply/zzlist");
		$("#downexcel").submit();
		$("#downexcel").attr('action',"/apply/zlist");
	}
	function downAllExcel(){
		var brand=$("#brandName").val();
		var pageType=$("#pageType").val();
		if(brand!=""){
			alert("对不起，汇总暂不支持品牌汇总");
			return;
		}
		if(pageType!=""){
			alert("对不起，汇总暂不支持页面类型汇总");
			return;
		}
		$("#downexcel").attr('action',"/apply/downloadRateAll");
		$("#downexcel").submit();
		$("#downexcel").attr('action',"/apply/zlist");
	}
</script>
<script type="text/javascript">
$(document).ready(function(){ 
	var pid=$("#brandName").val();
	if(pid!=null && ""!=pid){
		 $("#muio option").remove();
		 $.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#muio").val("");
				 $("#muio").append("<option value=''>请选择</option>");
				   for(i in data) {
				   		if(data[i].id==$("#styleId").val()){
				   			$("#muio").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	　　}); 
function getStylemy(pid){
	 $("#muio option").remove();
	 if($("#brandName").val()!=null&&""!=$("#brandName").val()){
	 $.ajax({
		   type: "POST",
		   url: "/json/carstyle/ajaxStyle",
		   dataType:'json',
		   data: {
			   'brandID':pid
		   },
		   success: function(data){
			 $("#muio").val("");
			 $("#muio").append("<option value=''>请选择</option>");
			   for(i in data) {
				   /* alert(data[i].style+"名");
			   		alert(data[i].id); */
			   		$("#muio").append("<option value="+data[i].id+">"+data[i].style+"</option>");
			   }
				  
			   } 
		
		});
	 }
}
</script>
</body>
</html>