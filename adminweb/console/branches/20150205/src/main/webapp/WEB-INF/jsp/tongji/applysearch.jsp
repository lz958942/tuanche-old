<%@page import="com.tuanche.console.web.AuthUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>报名查询</title>
<style>
	.tr1{background:none repeat scroll 0px 0px rgb(224, 255, 255)!important;}
	.tr2{background:none repeat scroll 0px 0px rgb(255, 251, 236)!important;}
	#man_zone table tr {
		background-color: #fff;
		border-bottom: 1px solid #ddd;
		/*line-height: 4.7;*/
}
#kevinDiv tr td{
padding: 8px;
line-height: 20px;
text-align: left;
vertical-align: top;}
</style>
</head>
<body>
<%
	boolean isDown=AuthUtil.checkAuth(request,"/apply/download");
	boolean isDownNum=AuthUtil.checkAuth(request,"/apply/downloadnum");
%>
<div id="man_zone">
	<div>
		<div class="b-con a-form">
          <form method='post' action='/apply/list'>
          	<div class="pd5">
          		<label class="pr15">选择城市:
          			<select name="districtId">
						<option value="">请选择</option>
						<c:forEach var="city" items="${citys}">
							<option value="${city.value.id}" ${search.districtId==city.value.id?"selected":"" }>${city.value.orderName}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">选择品牌:
          			<select name="brandId">
						<option value="">请选择</option>
						<c:forEach var="brand" items="${brands}">
							<option value="${brand.key}" ${search.brandId==brand.key?"selected":""}>${brand.value.orderName}</option>
						</c:forEach>
					</select>
          		</label>
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
          		<a href="/apply/download" class="btn btn-info download" id="download">下载报名详细</a>
          		<% } %>
          		<% if(isDownNum){ %>
          		&nbsp;&nbsp;
          		<a href="/apply/downloadnum" class="btn btn-info download" id="downloadnum">下载报名数</a>
          		<% } %>
          	</div>
          </form>
         </div>
	</div>
	<c:if test="${timeMap!=null&&timeMap.size()>0}">
	<div class="rb-con" style="position: absolute;height:1000px;">
		<div class="">
			<table class="over-auto table table-bordered chargeTable" style="font-size: 11px">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th></th>
						<c:forEach var="time" items="${timeMap}">
							<th timeAll="${time.key}">${fn:substring(time.key,5,7)}${fn:substring(time.key,8,11)}</th>
						</c:forEach>
						<th style="white-space:nowrap;">总计</th>
					</tr>
	          	</thead>
	          	<tbody>
	          	<c:set scope="request" var="heji" value="0" />
	          	<c:forEach var="result" items="${data}">
	          		<c:set scope="request" var="num" value="0" />
	          		<c:set scope="request" var="style" value="${style==\"tr1\"?\"tr2\":\"tr1\"}" />
	          		<tr  id="t${result.key}" class='${name=="brand"?"brand":"district" } ${style}'>
	          			<td> <font sta='1'>${"<a data-value=\""}${result.key}${"\" href=\"/common/"}${ajaxName}${"?"}${name=="brand"?"brandId":"districtId"}${"="}${result.key}${"\">"}${result.key>0?(name=="brand"?(func:getBrandName(result.key)):(func:getDistrictName(result.key))):"总计"}${"</a>"}</font></td>
	          			<c:forEach var="time" items="${timeMap}">
	          				<td>
	          					<c:choose>
	          						<c:when test="${result.key ==0 }">
	          							<c:out value="${timeSum[time.key]}" default="0"/>
	          							<c:set scope="request" var="num" value="${num+timeSum[time.key]}" />
	          						</c:when>
	          						<c:otherwise>
	          							<c:out value="${result.value[time.key]}" default="0"/>
	          							<c:set scope="request" var="num" value="${num+result.value[time.key] }" />
	          						</c:otherwise>
	          					</c:choose>
	          				</td>	
	          			</c:forEach>
	          			<td class="num">
	          				 <c:choose>
	          				 	<c:when test="${ result.key != 0}">
	          				 		${disSum[result.key]}
	          				 		<c:set scope="request" var="heji" value="${disSum[result.key]+heji}" />
	          				 	</c:when>
	          				 	<c:otherwise>
	          				 	${heji}
	          				 	</c:otherwise>
	          				 </c:choose>
	          			</td>
	          		</tr>
	          	</c:forEach>
	          	</tbody>
	          </table>
	          
	     </div>
	</div>
	</c:if>
	<div  id="kevinDiv" style="position:fixed;z-index:9;width:28px;display:none;border:1px solid #ddd;left:0;top:129px;border-bottom: none !important;">
	        <table >
	         <c:forEach var="result" items="${data}" varStatus="c">
	         <c:set scope="request" var="num" value="0" />
	         <c:set scope="request" var="style" value="${style==\"tr1\"?\"tr2\":\"tr1\"}"/>
	         <tr class='${name=="brand"?"brand":"district" } ${style}'>
	         	<td> <font id="kevin_${c.index }"></font></td>
	         </tr>
	         </c:forEach>
	         </table>
 </div>
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
$('.brand a').live('click',function(e){
	e= e || window.event;
	e.preventDefault();
	var href=$(this).attr('href')+'&starttime='+$('input[name=starttime]').val()+'&endtime='+$('input[name=endtime]').val()+($('select[name=districtId]').val()?'&districtId='+$('select[name=districtId]').val():"");
	var that=this;
	var bid=$(this).attr('data-value');
	if($(that).closest('tr').nextAll('.c'+bid).size()>0){
		$(that).closest('tr').nextAll('.c'+bid).css('display')=='none'?$(that).closest('tr').nextAll('.c'+bid).show():$(that).closest('tr').nextAll('.c'+bid).hide();
		return false;
	}
	$.get(href,{},function(data){
		$.each(data,function(i,j){
			var tr='<tr class="c'+bid+' bb"><td>'+i+'</td>'
			var num=0;
			$('.table thead th').each(function(){
				var time=$(this).html();
				if(time&&time!='总计'){
					num+=(data[i][time]?data[i][time]:0);
					tr+='<td>'+(data[i][time]?data[i][time]:0)+'</td>';
				}
				
			});
			tr+='<td class="num">'+num+'</td></tr>';
			changeSort(tr,that,'.c'+bid)
		});
	},'json');
});

$('.district a').live('click',function(e){
	e= e || window.event;
	e.preventDefault();
	var href=$(this).attr('href')+'&starttime='+$('input[name=starttime]').val()+'&endtime='+$('input[name=endtime]').val()+($('select[name=brandId]').val()?'&brandId='+$('select[name=brandId]').val():"");
	var that=this;
	var did=$(this).attr('data-value');
	if($('.d'+did).size()>0){
		$('.d'+did).css('display')=='none'?$('.d'+did).show():$('.d'+did).hide()&&$('.bb').hide();
		return false;
	}
	$.get(href,{},function(data){
		$.each(data,function(i,j){
			d=i.split('-');
			var brandName = ''
			if(d.length>2){
				brandName = d[1]+"-"+d[2];
			}else{
				brandName = d[1];
			}
			var tr='<tr class="d'+did+' brand" style="background: #bff2bb;"><td><a href="/common/seecarstyle?districtId='+did+'&brandId='+d[0]+'" data-value="'+d[0]+'">'+brandName+'</a></td>'
			var num=0;
			$('.table thead th').each(function(){
				var time=$(this).attr("timeAll");
				if(time&&time!='总计'){
					num+=(data[i][time]?data[i][time]:0);
					tr+='<td>'+(data[i][time]?data[i][time]:0)+'</td>';
				}
				
			});
			tr+='<td class="num">'+data[i]['00']+'</td></tr>';
			changeSort(tr,that,'.d'+did);
		});
	},'json');
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


$(window).scroll(function(){
	var num=0;
	if($(this).scrollLeft()>30){
		$("font[sta=1]").each(function(){
			//console.log($(this).text());
			$("#kevin_"+num).text($(this).text());
			num++;
		});
		$("#kevinDiv").show();
	}else{
		$("#kevinDiv").hide();
	};
	var top = 129;
	if($(this).scrollTop() < 129){
		top = 129 - $(this).scrollTop();
	}else{
		top = -($(this).scrollTop() - 129);
	};
	$("#kevinDiv").css("top",top)
	
})

$("#"+t${result.key}).mousedown(function(e){ 
          if(3 == e.which){ 
        	  window.open("/common/seebrand?districtId=10",'_blank','');
          }
})

</script>
</body>
</html>