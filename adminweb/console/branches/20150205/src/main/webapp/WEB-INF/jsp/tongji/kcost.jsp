<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="func"  uri="/WEB-INF/func.tld" %>
<%@ taglib prefix="npage" uri="/WEB-INF/pagetag.tld"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>关键词花费统计</title>
<style>
	.tr1{background:none repeat scroll 0px 0px rgb(224, 255, 255)!important;}
	.tr2{background:none repeat scroll 0px 0px rgb(255, 251, 236)!important;}
</style>
</head>
<body>
	<div id="man_zone">
		<div>
      		<div class="a-form b-con">
      			<form action="/tongji/kcost" method="post">
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
                      <select class="w130" name='accountId'>
                      	<option value="0">请选择</option>
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
                      		<input type='text' name='starttime' class="inptime span2" value="${search.starttime}" readonly="readonly" autocomplete="off" />
                      	</div>-
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='endtime' class="inptime span2" value="${search.endtime}" readonly="readonly" autocomplete="off" />
                      	</div>
                      </label>
                      <input class="btn btn-info" type="submit" value="查询" />&nbsp;&nbsp;
                      <a href="/common/downkcost" class="btn btn-info download">下载</a>
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
	          					<th>城市</th>
	          					<th>品牌</th>
	          					<th>关键词</th>
	          					<th>报名</th>
	          					<th>花费</th>
	          					<th>平均</th>
	          					<th>点击次数</th>
	          					<th>日期</th>
	          				</tr>
	          				</thead>
	          				<tbody>
	          					<c:forEach var="d" items="${data}">
	          					<c:set scope="request" var="style" value="${style==\"tr1\"?\"tr2\":\"tr1\"}" />
	          						<tr>
	          							<td>${accountMap[d.accountId].accountName}</td>
	          							<td>${cityMap[d.cityCode].name}</td>
	          							<td>${d.brandId==0?"首页":brandMap[d.brandId].name}</td>
	          							<td>${d.title}</td>
	          							<td>${d.applyNum}</td>
	          							<td>${d.costMoney}</td>
	          							<td><c:choose><c:when test="${d.applyNum!=0}"><fmt:formatNumber value="${d.costMoney/d.applyNum}" pattern="#0.00" /></c:when><c:otherwise>${d.costMoney}</c:otherwise></c:choose></td>
	          							<td>${d.click}</td>
	          							<td><fmt:formatDate value="${d.datetime}" pattern="yyyy-MM-dd" /></td>
	          						</tr>
	          					</c:forEach>
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
     $('.download').bind('click',function(e){
    		e= e || window.event;
    		e.preventDefault();
    		var href=$(this).attr('href')+'?name='+$('select[name=accountId] option:selected').text()+'&accountId='+$('select[name=accountId]').val()+'&starttime='+$('input[name=starttime]').val()+'&endtime='+$('input[name=endtime]').val()+($('select[name=brandId]').val()?'&brandId='+$('select[name=brandId]').val():"")+($('select[name=cityCode]').val()?'&cityCode='+$('select[name=cityCode]').val():"");
    		window.location.href=href;
    	});
     
     	var accountData={};
     	$('select[name=companyCode]').live('change',function(){
     		var id=$(this).val();
     		var str='<option value="0">请选择</option>';
     		if(id){
     			if(!accountData[id]){
     				$.ajax({async:false,type:'post',url:'/common/getAccountInfo',dataType:'json',data:{'code':id},success:function(data){accountData[id]=data;}});
     			}
     			$.each(accountData[id],function(m,n){
					str+='<option value="'+accountData[id][m].id+'" '+("${search.accountId}"==accountData[id][m].id?"selected":"")+'>'+accountData[id][m].accountName+'</option>'
				});
     		}
     		$('input:radio').each(function(){
     			$(this).attr("checked",false);
     		});
     		$('select[name=accountId]').html(str);
     	});
     	
     	if('${search.companyCode}'){
     		$('select[name=companyCode]').change();
     	}
     	if('${search.accountId}'){
     		$('select[name=accountCode]').change();
     	}
     </script>
</body>
</html>