<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>站点报名查询</title>
</head>
<body>
<div id="man_zone">
	<div>
		<div class="b-con a-form">
          <form method='post' action='/apply/accountlist'>
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
          		<label class="radio inline">账户:<input type="radio" name="type" value="accountCode" checked="checked" ${search.type=="accountCode"?"checked":"" }/></label>
          		<label class="radio inline">资源id:<input type="radio" name="type" value="newsid" ${search.type=="newsid"?"checked":""}  /></label>
          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;&nbsp;
          	</div>
          </form>
         </div>
	</div>
	<c:if test="${accountMap!=null&&accountMap.size()>0}">
	<div class="rb-con">
		<div class="over-auto">
			<table class="table table-bordered chargeTable">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th></th>
						<c:forEach var="account" items="${accountMap}">
							<th>${account.key}</th>
						</c:forEach>
						<th>总计</th>
					</tr>
	          	</thead>
	          	<tbody>
	          		<c:forEach var="result" items="${data}">
	          		<c:set scope="request" var="style" value="${style=='background:none repeat scroll 0 0 #FFFBEC;'?'background:none repeat scroll 0 0 #E0FFFF':'background:none repeat scroll 0 0 #FFFBEC;' }" />
	          			<tr style="${style}">
	          				<c:set scope="request" var="num" value="0" />
	          				<td>${result.key=="0"?"总计":result.key}</td>
	          				<c:forEach var="account" items="${accountMap}">
	          				<c:set scope="request" var="num" value="${num+result.value[account.key] }" />
	          					<td>
	          						<c:out value="${result.value[account.key]}" default="0" />
	          					</td>
	          				</c:forEach>
	          				<td>${num}</td>
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
</script>
</body>
</html>