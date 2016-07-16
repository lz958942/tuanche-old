<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>站点报名查询</title>
<style>
	.tr1{background:none repeat scroll 0px 0px rgb(224, 255, 255)!important;}
	.tr2{background:none repeat scroll 0px 0px rgb(255, 251, 236)!important;}
</style>
</head>
<body>
<div id="man_zone">
	<div>
		<div class="b-con a-form">
          <form method='post' action='/apply/plist'>
          	<div class="pd5">
          		<label class="pr15">选择城市:
          			<select name="districtId">
						<option value="">请选择</option>
						<c:forEach var="city" items="${citys}">
							<option value="${city.value.id}" ${search.districtId==city.value.id?"selected":"" }>${city.value.orderName}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">页面位置:
          			<select name="bmtype">
						<option value="">请选择</option>
						<c:forEach var="bm" items="${bmtype}">
							<option value="${bm.key}" ${search.bmtype==bm.key?"selected":""}>${bm.value}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">表单位置:
          			<select name="position">
						<option value="">请选择</option>
						<c:forEach var="pos" items="${position}">
							<option value="${pos.key}" ${search.position==pos.key?"selected":""}>${pos.value}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">
          			日期:
          			<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='starttime' class="querytime span2" value="<fmt:formatDate value="${starttime}" pattern="yyyy-MM-dd HH:00:00" />" readonly="readonly" autocomplete="off" />
                      	</div>-
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='endtime' class="querytime span2" value="<fmt:formatDate value="${endtime}" pattern="yyyy-MM-dd HH:00:00" />" readonly="readonly" autocomplete="off" />
                      	</div>
          		</label>
          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;&nbsp;
          	</div>
          </form>
         </div>
	</div>
	<c:if test="${resultMap!=null&&resultMap.size()>0}">
	<div class="rb-con">
		<div class="over-auto">
			<table class="table table-bordered chargeTable">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th></th>
						<c:forEach var="result" items="${btypeMap}">
							<th id="${result.key}">${bmtype[result.key]}</th>
						</c:forEach>
						<th>总计</th>
					</tr>
	          	</thead>
	          	<tbody>
	          	<c:forEach var="result" items="${resultMap}">
	          		<c:set scope="request" var="num" value="0" />
	          		<c:set scope="request" var="style" value="${style==\"tr1\"?\"tr2\":\"tr1\"}" />
	          		<tr class="${style}">
	          			<td><a data-value="${result.key=='0'?0:result.key}" href="/common/position?isDay=${search.timeType=='%Y-%m-%d'?0:1}" class="time">${result.key=="0"?"总计":result.key}</a></td>
	          			<c:forEach var="type" items="${btypeMap}">
	          				<c:set scope="request" var="num" value="${num+result.value[type.key] }" />
	          				<td><c:out value="${result.value[type.key]}" default="0"/></td>	
	          			</c:forEach>
	          			<td class="num">${num}</td>
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
	dateFmt:'yyyy-MM-dd HH:00:00'
	});
	});
$('.time').live('click',function(e){
	e= e || window.event;
	e.preventDefault();
	var time=$(this).attr('data-value');
	var s=time.split(' ');
	time=time+(s.length>1?":00:00":" 00:00:00");
	var that=this;
	if(!$('select[name=districtId]').val()&&!$(this).hasClass("city")){
		var flag=(s.length>1?(s[0]+s[1]):s[0]);
		if($('.t'+flag+"city").size()>0){
			$('.t'+flag+"city").css('display')=='none'?$('.t'+flag+"city").show():$('.t'+flag+"city").hide()&&$(".single").hide();
			return false;
		}
		var href="/common/selectAll?time="+time
		+($('select[name=bmtype]').val()?'&bmtype='+$('select[name=bmtype]').val():"")
		+($('select[name=position]').val()?'&position='+$('select[name=position]').val():"");
		$.get(href,{},function(data){
			$.each(data,function(i,j){
				if(data.length==0){
					return false;
				}
				var temp=i.split('-');
				var tr='<tr class="t'+flag+'city" style="background: #bff2bb;"><td><a id="'+temp[1]+'" data-value="'+$(that).attr('data-value')+'" href="/common/position?districtId='+temp[1]+'" class="city time">'+temp[0]+'</a></td>'
				var num=0;
				$('.table thead th').each(function(){
					var time=$(this).html();
					var id=$(this).attr('id');
					if(id&&time!='总计'){
						num+=(data[i][id]?data[i][id]:0);
						tr+='<td>'+(data[i][id]?data[i][id]:0)+'</td>';
					}
					
				});
				tr+='<td class="num">'+num+'</td></tr>';
				$(that).closest('tr').after(tr);
			});
		},'json');
		return true;
	}
	var flag=(s.length>1?(s[0]+s[1]):s[0]);
	flag='t'+flag+($(this).attr('id')?$(this).attr('id'):"");
	if($("."+flag).size()>0){
		$("."+flag).css('display')=='none'?$("."+flag).show():$("."+flag).hide();
		return false;
	}
	var href=$(this).attr('href')+'&time='+time
			+($('select[name=districtId]').val()?'&districtId='+$('select[name=districtId]').val():"")
			+($('select[name=bmtype]').val()?'&bmtype='+$('select[name=bmtype]').val():"")
			+($('select[name=position]').val()?'&position='+$('select[name=position]').val():"");
	var that=this;
	$.get(href,{},function(data){
		$.each(data,function(i,j){
			if(data.length==0){
				return false;
			}
			var tr='<tr class="'+flag+' single"><td>'+i+'</td>'
			var num=0;
			$('.table thead th').each(function(){
				var time=$(this).html();
				var id=$(this).attr('id');
				if(id&&time!='总计'){
					num+=(data[i][id]?data[i][id]:0);
					tr+='<td>'+(data[i][id]?data[i][id]:0)+'</td>';
				}
				
			});
			tr+='<td class="num">'+num+'</td></tr>';
			$(that).closest('tr').after(tr);
		});
	},'json');
});
</script>
</body>
</html>