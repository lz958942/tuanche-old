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
	 <form action="/tongji/kcost" method="post" name="classifyCost">
		<div>
      		<div class="a-form b-con">
      				<div class="pd5">
      				<%-- <label class="pr15">公司：
                      <select class="w130" >
                      	<option value="-1">请选择</option>
                      	<c:forEach var="company" items="${companyMap}">
                      		<c:if test="${company != null }">
                      		<option value="${company.key}" ${obj.accountid==company.key?"selected":""} >${company.value.companyName}</option>
                      		</c:if>
                      	</c:forEach>
                      </select>
                     </label> --%>
      				<label class="pr15">账户：
                      <select class="w130" name='accountid'>
                      	<option value="-1">请选择</option>
                      		<c:forEach var="account" items="${accountList}">
                      		<option value="${account.id}"  ${obj.accountid==account.id?"selected":""} >${account.accountName}</option>
                      	</c:forEach>
                      </select>
      				</label>
      			<label class="pr15">城市：
                      <select class="w130" name='cityId'>
                      	<option value="-1">请选择</option>
                      	<c:forEach var="city" items="${cityMap}">
                      		<option value="${city.key}" ${obj.cityId==city.key?"selected":""} >${city.value.dname}</option>
                      	</c:forEach>
                      </select>
                      </label>
      					<label class="pr15">分类：
                      <select class="w130" name='device'>
                      	<option value="-1">请选择</option>
                      	<option value="1"  ${obj.device==1?"selected":""} >计算机端</option>
                      	<option value="2" ${obj.device==2?"selected":""}>移动端</option>
                      </select>
                      </label>
      				<label class="pr15">品牌：
                      <select class="w130" name='brandId'>
                      	<option value="-1">请选择</option>
                      	<c:forEach var="brand" items="${brandMap}">
                      		<option value="${brand.key}" ${obj.brandId==brand.key?"selected":""}>${brand.value.orderName}</option>
                      	</c:forEach>
                      </select> 
                       </label>
                      <label class="pr15">日期：
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='startTime' class="inptime span2" value="${obj.startTime}" readonly="readonly" autocomplete="off" />
                      	</div>-
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='endTime' class="inptime span2" value="${obj.endTime}" readonly="readonly" autocomplete="off" />
                      	</div>
                      </label>
                      <input class="btn btn-info" type="submit" value="查询" />&nbsp;&nbsp;
                      <a href="/common/downkcost" class="btn btn-info download">下载</a>
                     </div>
      		</div>
      	</div>
      	<c:if test="${data!=null&&data.size()>0}">
      		<div class="rb-con">
	    		<div class="over-auto">
	      			<table class="table table-bordered chargeTable">
	      				<thead>
	          				<tr style="background:none repeat scroll 0 0 #E8F1FD">
	          					<th>账户</th>
	          					<th>分类</th>
	          					<th>城市</th>
	          					<th>品牌</th>
	          					<th>关键词ID</th>
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
	          							<td>${accountMap[d.accountid].accountName}</td>
	          							<td>
	          								<c:if test="${d.device == 1 }">PC</c:if>
	          								<c:if test="${d.device == 2}">移动</c:if>
	          							</td>
	          							<td>${d.cityName }</td>
	          							<td>${brandMap[d.brandId].name}</td>
	          							<td>${d.uesKeywordid}</td>
	          							<td>${d.title}</td>
	          							<td>${d.applyNum}</td>
	          							<td>${d.dayCost}</td>
	          							<td><c:choose><c:when test="${d.applyNum!=0}"><fmt:formatNumber value="${d.dayCost/d.applyNum}" pattern="#0.00" /></c:when><c:otherwise>${d.dayCost}</c:otherwise></c:choose></td>
	          							<td>${d.click}</td>
	          							<td>${d.strdate}</td>
	          						</tr>
	          					</c:forEach>
	          				</tbody>
	          		</table>
	          	</div>
          	</div>
          	</c:if>
          	<div class="page_and_btn" style="text-align:center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
			
      	</form>
     </div>
     <script type="text/javascript">
     $('.download').bind('click',function(e){
    		e= e || window.event;
    		e.preventDefault();
    		var href=$(this).attr('href')+'?accountid='+$('select[name=accountid]').val()
    				+'&cityId='+$('select[name=cityId]').val()+'&device='+$('select[name=device]').val()
    				+'&brandId='+$('select[name=brandId]').val()
    				+"&startTime="+$('input[name=startTime]').val()+"&endTime="+$('input[name=endTime]').val();
    		window.location.href=href;
    	});
     
     /*	var accountData={};
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
     	} */
     </script>
</body>
</html>