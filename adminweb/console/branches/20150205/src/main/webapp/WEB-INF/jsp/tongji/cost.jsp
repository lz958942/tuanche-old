<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="func"  uri="/WEB-INF/func.tld" %>
<%@ taglib prefix="npage" uri="/WEB-INF/pagetag.tld"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>花费查询</title>
<style>
	.tr1{background:none repeat scroll 0px 0px rgb(224, 255, 255)!important;}
	.tr2{background:none repeat scroll 0px 0px rgb(255, 251, 236)!important;}
</style>
</head>
<body>
	<div id="man_zone">
		<div>
      		<div class="a-form b-con">
      			<form action="/tongji/cost" method="post" id="tjCost" enctype="multipart/form-data">
      				<div class="pd5">
      				<label class="pr15">公司：
                      <select class="w130" name='companyCode'>
                      	<option value="">请选择</option>
                      	<c:forEach var="company" items="${companyMap}">
                      		<option value="${company.key}" ${search.companyCode==company.key?"selected":""}>${company.value.companyName}</option>
                      	</c:forEach>
                      </select>
                      </label>
      				<label class="pr15">账户：
                      <select class="w130 accountCode" name='accountCode'>
                      	<option value="">请选择</option>
                      </select>
      				</label>
      				<label class="pr15">城市：
                      <select class="w130" name='cityCode'>
                      	<option value="">请选择</option>
                      	<c:forEach var="city" items="${cityMap}">
                      		<option value="${city.key}" ${search.cityCode==city.key?"selected":""} >${city.value.orderName}</option>
                      	</c:forEach>
                      </select>
                      </label>
      				<label class="pr15">品牌：
                      <select class="w130" name="brandId">
                      	<option value="0">请选择</option>
                      	<c:forEach var="brand" items="${brandMap}">
                      		<option value="${brand.key}" ${search.brandId==brand.key?"selected":""}>${brand.value.orderName}</option>
                      	</c:forEach>
                      </select>
                      </label>
                      <label class="pr15">日期：
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='starttime' class="inptime span2 starttime" value="${search.starttime}" readonly="readonly" autocomplete="off" />
                      	</div>-
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='endtime' class="inptime span2" value="${search.endtime}" readonly="readonly" autocomplete="off" />
                      	</div>
                      </label>
                      <label>
                      		<input class="btn btn-info" type="submit" value="查询" />
                      </label>
                      <label>
                      		<input class="btn btn-info" id="deleteDay" type="button" value="删除" />
                      </label>
                      <label>
                      		<input class="btn btn-info" id="download" type="button" value="下载详细" />
                      </label>
                      <label>
                      		<input class="btn btn-info" id="insertCityBrandSum" type="button" value="结算当天花费" />
                      </label>
                     </div>
                     <div class="pd5">
                    	<%-- <c:forEach var="bType" items="${businessTypeMap}">
	                     			<font color="red">${nameMap[bType.key]}</font>:
	                     			<c:forEach var="sType" items="${bType.value}">
	                     			<label class="radio inline">
	                     				<input type="radio" name="${bType.key}" value="${sType.bizCode}" ${pb.searchCondtions[bType.key]==sType.bizCode?"checked":""} />${sType.bizName}
	                     				</label>
	                     			</c:forEach>
	                     			&nbsp;&nbsp;
                     	</c:forEach>  --%>
                     	<input name="file" type="file"  class="exFile"/>
                     	<input class="btn btn-info" id="uploadExcel" type="button" value="导入" />
                     </div>
      			</form>
      		</div>
      	</div>
      	<c:if test="${data!=null&&data.size()>0}">
      		<div class="rb-con">
	    		<div class="over-auto">
	      			<table class="table table-bordered chargeTable">
	      				<thead>
	          				<tr style="background:none repeat scroll 0 0 #E8F1FD">
	          					<th>账户</th>
	          					<th>账户代码</th>
	          					<th>公司</th>
	          					<th>公司代码</th>
	          					<th>城市</th>
	          					<th>品牌</th>
	          					<th>报名</th>
	          					<th>花费</th>
	          					<th>平均</th>
	          					<th>url</th>
	          					<th>日期</th>
	          					<th>操作</th>
	          				</tr>
	          				</thead>
	          				<tbody>
	          					<c:forEach var="d" items="${data}">
	          					<c:set scope="request" var="style" value="${style==\"tr1\"?\"tr2\":\"tr1\"}" />
	          						<tr class="${style}">
	          							<td>${accountMap[d.accountCode].accountName}</td>
	          							<td>${d.accountCode}</td>
	          							<td>${companyMap[d.companyCode].companyName}</td>
	          							<td>${d.companyCode}</td>
	          							<td>${func:getCity(d.districtId)}</td>
	          							<td>${brandMap[d.brandId].name}</td>
	          							<td>${d.applyNum}</td>
	          							<td>${d.costMoney}</td>
	          							<td><c:choose><c:when test="${d.applyNum!=0}"><fmt:formatNumber value="${d.costMoney/d.applyNum}" pattern="#0.00" /></c:when><c:otherwise>${d.costMoney}</c:otherwise></c:choose></td>
	          							<td>${d.url}</td>
	          							<td><fmt:formatDate value="${d.datetime}" pattern="yyyy-MM-dd" /></td>
	          							<td><a data-value="${d.id}" class="seeKeyword" href="/common/seeKeyword?d=${d.districtId}&b=${d.brandId}&c=${d.carstyleId}&a=${d.accountId}&t='<fmt:formatDate value="${d.datetime}" pattern="yyyy-MM-dd" />'">查看详情</a></td>
	          						</tr>
	          					</c:forEach>
	          						<c:if test="${objother!=null }">
			          					<tr class="other">
		         							<td>其他</td><td></td><td></td><td></td><td></td><td></td>
		         							<td>${objother.applyNum}</td><td>${objother.costMoney}</td>
		         							<td></td><td></td><td></td><td></td>
			          					</tr>
		          					</c:if>
	          						<c:if test="${objsum!=null }">
			          					<tr class="sum">
		         							<td>合计</td><td></td><td></td><td></td><td></td><td></td>
		         							<td>${objsum.applyNum}</td><td>${objsum.costMoney}</td>
		         							<td><c:choose><c:when test="${objsum.applyNum!=0}"><fmt:formatNumber value="${objsum.costMoney/objsum.applyNum}" pattern="#0.00" /></c:when><c:otherwise>${objsum.costMoney}</c:otherwise></c:choose></td>
		         							<td></td><td></td><td></td>
			          					</tr>
		          					</c:if>
	          				</tbody>
	          		</table>
	          	</div>
	          	<div class="table-page">
          			<div class="page tcenter">
            			<div class="pageWrap">
              				<npage:npage pager="${pb}" />
            			</div>
          			</div>
      			</div>
          	</div>
          	</c:if>
     </div>
     <script type="text/javascript">
     	$("#uploadExcel").live("click",function(){
     		 if($(".exFile").val()==''){
     			alert("请选择文件！")
     			return false;
     		}
     		if($(".accountCode").val()==''){
     			alert("请选择账户！");
     			return false;
     		}
     		if($(".starttime").val()==''){
     			alert("请选择日期！");
     			return false;
     		} 
     		$("#tjCost").attr("action","/cost/uploadExcel");
     		$("#tjCost").submit();
     		
     	});
     	$("#insertCityBrandSum").live("click",function(){
     		if($(".starttime").val()==''){
     			alert("请选择日期！");
     			return false;
     		} 
     		if(confirm("请确定数据准确后结算！")){
     			$("#tjCost").attr("action","/tongji/insertDayCityBrandCost");
     			$("#tjCost").ajaxSubmit(function(message) {
         			alert("结算成功！");
         		});
     		}
     		$("#tjCost").attr("action","/tongji/cost");
     		return false;
     	});
     	$("#download").live("click",function(){
     		$("#tjCost").attr("action","/tongji/downLoadExcel");
 			$("#tjCost").ajaxSubmit(function(message) {
     			alert(message);
     		});
 			$("#tjCost").attr("action","/tongji/cost");
     		return false;
     	});
     	
     	
     	$("#deleteDay").live("click",function(){
     		if($(".accountCode").val()==''){
     			alert("请选择账户！");
     			return false;
     		}
     		if($(".starttime").val()==''){
     			alert("请选择日期！");
     			return false;
     		} 
     		if(confirm("确认要删除吗？")){
     			$("#tjCost").attr("action","/tongji/deleteDayData");
     			$("#tjCost").submit();
     		}
     	});
     	
     	var accountData={};
     	$('select[name=companyCode]').live('change',function(){
     		var id=$(this).val();
     		var str='<option value="">请选择</option>';
     		if(id){
     			if(!accountData[id]){
     				$.ajax({async:false,type:'post',url:'/common/getAccountInfo',dataType:'json',data:{'code':id},success:function(data){accountData[id]=data;}});
     			}
     			$.each(accountData[id],function(m,n){
					str+='<option value="'+accountData[id][m].accountCode+'" '+("${search.accountCode}"==accountData[id][m].accountCode?"selected":"")+'>'+accountData[id][m].accountName+'</option>'
				});
     		}
     		$('input:radio').each(function(){
     			$(this).attr("checked",false);
     		});
     		$('select[name=accountCode]').html(str);
     	});
     	$('select[name=accountCode]').live('change',function(){
     		var that=this;
     		var data=accountData[$('select[name=companyCode]').val()];
     		$.each(data,function(m,n){
     			if(data[m].accountCode==$(that).val()){
     				data[m].bizCode.split("").forEach(function(e){
     					$('input[value='+e+']').attr("checked",true);
     				});
     			}
     		});
     	});
     	
     	if('${search.companyCode}'){
     		$('select[name=companyCode]').change();
     	}
     	if('${search.accountCode}'){
     		$('select[name=accountCode]').change();
     	}
     	
     	$('.seeKeyword').live('click',function(e){
     		e= e || window.event;
     		e.preventDefault();
     		var that=this;
     		var id=$(this).attr('data-value');
     		if($('.k'+id).size()>0){
    			$('.k'+id).css('display')=='none'?$('.k'+id).show():$('.k'+id).hide();
    			return false;
    		}
     		$.get($(this).attr('href'),{},function(data){
     			if(data.length==0){
     				alert('没有数据！');
     				return false;
     			}
     			var str='';
     			$.each(data,function(m,n){
     				str+='<tr class="k'+id+'"><td></td><td></td><td></td><td></td><td></td><td>'+data[m].title+'</td><td>'+data[m].applyNum+'</td><td>'+data[m].costMoney+'</td><td>'+data[m].costMoney/data[m].applyNum+'</td><td></td><td></td><td></tr>'
     				$(that).closest('tr').after(str);
     			});
     		},'json');
     	});
     </script>
</body>
</html>