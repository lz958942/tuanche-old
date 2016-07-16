<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib prefix="npage" uri="/WEB-INF/pagetag.tld"%>
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
          <form id="form" method='post' action='/company/campaign'>
          <input type="hidden" name="type" value="${search.search}" /> 
          	<div class="pd5">
          		<label class="pr15">选择公司:
          			<select name="companyId">
						<option value="">请选择</option>
						<c:forEach var="company" items="${companyList}">
							<option value="${company.id}" ${search.companyId==company.id?"selected":"" }>${company.companyName}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">选择账户:
          			<select name="accountId">
						<option value="">请选择</option>
					</select>
          		</label>
          		<label class="pr15">标题:
          			<input type="text"  class="w130" name="searchWord" value="${search.searchWord}" />
          		</label>
          		<label class="radio inline"><input type="radio" name="search" value="campaign" ${search.search=='campaign'?'checked':'' } />推广计划</label>
          		<label class="radio inline"><input type="radio" name="search" value="group" ${search.search=='group'?'checked':'' } />推广单元组</label>
          		<label class="radio inline"><input type="radio" name="search" value="keyword" ${search.search=='keyword'?'checked':'' } />关键词</label>
          		<input type="button" value="查询" class="btn btn-info submit"/>&nbsp;&nbsp;
          		<a href="/common/downkeyword" class="btn btn-info download" id="downkeyword">下载关键词</a>
          	</div>
          </form>
         </div>
	</div>
	<c:if test="${campaignList!=null&&campaignList.size()>0}">
	<div class="rb-con">
		<div class="over-auto">
			<div style="text-align:center;font-size:24px;line-height:50px;">
				<c:if test="${search.type!='campaign'}">
					<a href="javascript:history.go(-1);" class="btn btn-info" style="float:left;">返回</a>
				</c:if>
				${search.title}
			</div>
			<table class="table table-bordered chargeTable">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th>编号</th>
						<th>名称</th>
					</tr>
	          	</thead>
	          	<tbody>
	          		<c:forEach var="bean" items="${campaignList}">
	          		<c:set scope="request" var="style" value="${style==\"tr1\"?\"tr2\":\"tr1\"}" />
	          			<tr class="${style}">
	          				<c:set var="url" value="${func:getUrl(search,bean.id)}" scope="request" />
	          				<td><a href="${url}">${bean.id}</a></td>
	          				<td><a href="${url}">${bean.name}</a></td>
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
<script type="text/javascript" charset="utf-8">
$('.download').bind('click',function(e){
	e= e || window.event;
	e.preventDefault();
	if(!$('select[name=accountId]').val()){
		alert('请选择账户');
		return false;
	}
	var href=$(this).attr('href')+'?accountId='+$('select[name=accountId]').val()+"&name="+$('select[name=accountId] option:selected').text();
	window.location.href=href;
});
$('select[name=companyId]').live('change',function(){
	var id=$(this).val();
	if(id){
		$.get('/common/accountjson?id='+id,{},function(data){
			var str='<option value="">请选择</option>';
			$.each(data,function(m,n){
				str+='<option value="'+data[m].id+'" '+('${search.accountId}'==data[m].id?"selected":"")+'>'+data[m].accountName+'</option>';
			});
			$('select[name=accountId]').html(str);
		},'json');
	}
	$('select[name=accountId]').html('<option value="">请选择</option>');
});

if('${search.companyId}'){
	$('select[name=companyId]').change();
}
$('.submit').click(function(){
	if($('select[name=accountId]').val()){
		if($('input[name=search]:checked').val()){
			$('input[name=type]').val($('input[name=search]:checked').val());
		}
		$('#form').submit();
		return true;
	}
	alert('请选择账户');
	return false;
});
</script>
</body>
</html>