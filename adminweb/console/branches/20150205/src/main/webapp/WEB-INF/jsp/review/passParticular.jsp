<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
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
</script>
	</head>
	<body>
		<form action="${actionUrl}" method="post" style="padding: 0 10px 0 10px; margin-top: 0px;" name="searchZixun">
			<table class="table_style table table-bordered" id="statable">
					<tr class="attr">
						<td style="white-space: nowrap;">编号</td>
						<td style="white-space: nowrap;">待审核图片</td>
						<td style="white-space: nowrap;">状态</td>
						<td style="white-space: nowrap;">审核人</td>
						<td style="white-space: nowrap;">审核时间</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<tbody align="center" id="dataBody">
					<c:forEach items="${list}" var="list">
						
								<tr id="tr_${list.id }">
									<td >${list.id }</td>
									<td >
									<img style="width: 100px;height: 60px" src="${list.pic }">
									</td>
									<td >
										<font id="sta_${list.id}">
											<c:if test="${list.state==0 }"> <font color="gray">未审核</font></c:if>
											<c:if test="${list.state==2 }"> <font color="red">未通过</font></c:if>
											<c:if test="${list.state==1 }"> <font color="green">通过</font></c:if>
										</font>
									</td>
									<td >
									<c:if test="${list.aprrovalUser!=null }">
									${func:getEditName(list.aprrovalUser)}
									</c:if>
									<c:if test="${list.aprrovalUser==null }">
									暂无
									</c:if>
									</td>
									<td >
									<c:if test="${list.aprrovalTime!=null }">
									${list.aprrovalTime}
									</c:if>
									<c:if test="${list.aprrovalTime==null }">
									暂无
									</c:if>
									</td>
									<td>
									<a href="#" onclick="passPic(${list.id},1)">通过</a>|<a href="#" onclick="passPic(${list.id},0)">不通过</a>
										<%-- <c:if test="${list.state==0 }"> </c:if> --%>
										<%-- <c:if test="${list.state==2 }"> <font color="red">无操作</font></c:if>
										<c:if test="${list.state==1 }"> <font color="green">无操作</font></c:if> --%>
									</td>
								</tr>
						
					</c:forEach>
					</table>
					<c:if test="${list!=null && list.size()>0}">
				<input type="button" value="取消" onclick="window.close()">
			</c:if>
			<c:if test="${list==null|| list.size()==0}">
				无图片！</br>
				<input type="button" value="取消" onclick="window.close()">
			</c:if>
		</form>
		</body>
</html>
