<%@page import="com.tuanche.console.web.AuthUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>用户中心统计查询</title>
<style>
	.tr1{background:none repeat scroll 0px 0px rgb(224, 255, 255)!important;}
	.tr2{background:none repeat scroll 0px 0px rgb(255, 251, 236)!important;}
</style>
</head>
<body>
<%
	boolean isDown=AuthUtil.checkAuth(request,"/uc/download");
%>
<div id="man_zone">
	<div>
		<div class="b-con a-form">
          <form method='post' action='/tongji/uc'>
          	<div class="pd5">
          		<label class="pr15">
          			日期:
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
          		<% if(isDown){ %>
          		<a href="/uc/download" class="btn btn-info download" id="download">下载</a>
          		<% } %>
          	</div>
          </form>
         </div>
	</div>
	<c:if test="${list!=null && list.size() > 0}">
	<div class="rb-con">
		<div class="over-auto">
			<table class="table table-bordered chargeTable">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th>日期</th>
						<th>登录总数</th>
						<th>PC端注册总数</th>	
						<th>PC端主动注册数</th>
						<th>Android端注册数</th>
						<th>IOS端注册数</th>
						<th>WAP端注册数</th>
						<!-- <th>总计</th> -->
					</tr>
	          	</thead>
	          	<tbody>
	          	<c:forEach var="data" items="${list}">
	          		<tr>
	          			<td>${data.day}</td>
	          			<td>${data.login_total_num}</td>
	          			<td>${data.pc_reg_num}</td>
	          			<td>${data.pc_actreg_num}</td>
	          			<td>${data.android_reg_num}</td>
	          			<td>${data.ios_reg_num}</td>
	          			<td>${data.wap_reg_num}</td>
	          		</tr>
	          	</c:forEach>
	          	</tbody>
	          </table>
	     </div>
	</div>
	</c:if>
</div>
<script type="text/javascript">
$('.querytime').live("click",function() {
	WdatePicker({
	isShowClear:false,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});
$('.download').bind('click',function(e){
	e= e || window.event;
	e.preventDefault();
	var href=$(this).attr('href')+'?starttime='+$('input[name=starttime]').val()+'&endtime='+$('input[name=endtime]').val()+($('select[name=brandId]').val()?'&brandId='+$('select[name=brandId]').val():"")+($('select[name=districtId]').val()?'&districtId='+$('select[name=districtId]').val():"");
	window.location.href=href;
});
(function(){
	var trs=$('.table tbody tr');
	$('.table tbody').html("");
	trs.each(function(){
		var that=this;
		var len=$('.table tbody tr').length;
		if(len>0){
			var i=0;
			
			$('.table tbody tr').each(function(){
				i++;
				$(this).removeClass('tr1').removeClass('tr2').addClass(i%2==1?'tr1':'tr2');
				if(parseInt($(that).find('.num').html())>parseInt($(this).find('.num').html())){
					$(this).before($(that));
					return false;
				}else if(i==len){
					$(this).after($(that));
					return false;
				}
			});
		}else{
			$('.table tbody').append($(this));
		}
	});
})();

function changeSort(str,obj,cla){
	var trs=$(obj).closest('tr').nextAll(cla);
	if(trs.length>0){
		var i=0;
		trs.each(function(){
			i++;
			if(parseInt($(this).find('.num').html())<parseInt($(str).find('.num').html())){
				$(this).before(str);
				return false;
			}else if(i==trs.length){
				$(this).after(str);
				return false;
			}
		});
	}else{
		$(obj).closest('tr').after(str)
	}
	
}
</script>
</body>
</html>