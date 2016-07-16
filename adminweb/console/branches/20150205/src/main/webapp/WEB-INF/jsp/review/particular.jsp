<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>

<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>

<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript">
function passPic(picid,sta){
	var url="";
	if(sta==0){
		//不通过审核
		url="/review/pass.do?id="+picid+"&type=3";
	}else{
		//通过
		url="/review/pass.do?id="+picid+"&type=4";
	}
	$.ajax({
		  url: url,
		  success: function(date){
			  if($.trim(date)==1){
				  //通过
				  $("#sta_"+picid).text("通过");
			  }
			  if($.trim(date)==0){
				  //通过
				  $("#sta_"+picid).text("未通过");
			  }
		  }
		});
}
function finishPic(){
	//window.opener.document.getElementById("reviewForm").submit()
	window.close()
}
function batchPass(){
	var ids="";
	$("#statable input[type='checkbox']").each(function(){
		if(this.checked&&this.value!=0){
			ids+=this.value+"-";
		}
	 });
	if(""!=ids){
		$.ajax({
			type: "GET",
			url: "/review/batchPass?ids="+ids,
			   success: function(a){
				  var arrid=$.trim(a).split("-");
				  for(i in arrid){
					  if(""!=arrid[i]){
						  $("#tr_"+arrid[i]).remove();
					  }
				  }
				  $("#statable input[type='checkbox']").attr("checked",$("input[name='idBox']:checked").attr("checked") ? true :false );
				   },
			   error :function(){
				  alert("批量通失败！");
			   }
			});
	}
}
function selectAll(){
	$("#statable input[type='checkbox']").attr("checked",$("input[name='idBox']:checked").attr("checked") ? false :true );
}
</script>
	
		<form action="${actionUrl}" method="post" style="padding: 0 10px 0 10px; margin-top: 0px;" name="searchZixun">
		<c:if test="${id!=null}">
		<input type="hidden" name="id" value="${id}">
		</c:if>
		<c:if test="${comment!=null}">
		<input type="hidden" name="comment" value="${comment}">
		</c:if>
		<c:if test="${carstyleId!=null}">
		<input type="hidden" name="carstyleId" value="${carstyleId}">
		</c:if>
		<c:if test="${${ishavepic!=null}">
		<input type="hidden" name="ishavepic" value="${ishavepic}">
		</c:if><c:if test="${source!=null}">
		<input type="hidden" name="source" value="${source}">
		</c:if>
			<table class="table_style table table-bordered" id="statable">
					<tr class="attr">
					<td style="white-space: nowrap;"><label><input type="checkbox"  value="0" id="allSelect" onchange="selectAll()" />全选 </label></td>
						<td style="white-space: nowrap;">编号</td>
						<td style="white-space: nowrap;">待审核图片</td>
						<td style="white-space: nowrap;">状态</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<tbody align="center" id="dataBody">
					<c:forEach items="${pic}" var="list">
						
								<tr id="tr_${list.id }">
									<td>
									<input name="idBox" type="checkbox" value="${list.id}" />
									</td>
									<td >${list.id }</td>
									<td >
									<img style="width: 100px;height: 60px" src="${list.pic }">
									</td>
									<td >
										<font id="sta_${list.id}">
											<c:if test="${list.state==0 }">未通过</c:if>
											<c:if test="${list.state==1 }">通过</c:if>
										</font>
									</td>
									<td >
									<a href="#" onclick="passPic(${list.id},0)">不通过</a>
									<a href="#"  onclick="passPic(${list.id},1)">通过</a>
									</td>
								</tr>
						
					</c:forEach>
					</table>
					<c:if test="${pic!=null && pic.size()>0}">
				<input type="button" value="关闭" onclick="finishPic()">
				<input type="button" value="批量通过" onclick="batchPass()">
			</c:if>
			<c:if test="${pic==null|| pic.size()==0}">
				图片已全部审核通过！</br>
				<input type="button" value="取消" onclick="window.close()">
			</c:if>
		</form>

