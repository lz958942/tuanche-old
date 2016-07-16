<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/recordStat/recordStat.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="/js/fusioncharts/themes/fusioncharts.theme.zune.js"></script>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<style type="text/css">
	.uploadCss{
		max-width: 350px;
		max-height: 92px;
	}
</style>
<title>收录统计管理</title>
</head>
<body>
<form action="/recordStatManage/recordStatView" method="post" id="recordStatSearchForm">
	<div id="man_zone">
		<div>
			<div class="b-con a-form">
	          	<div class="pd5">
	         		<label class="pr15">
	       				日期:
	       				<div class="input-prepend">
	                   		<span class="add-on"><i class="icon-calendar"></i></span>
	                   		<input type='text' id="startDate" name='startDate' class="inptime span2" value="${startDate }" autocomplete="off" />
	                   	</div>-
	                  	<div class="input-prepend">
	                    	<span class="add-on"><i class="icon-calendar"></i></span>
	                    	<input type='text' id="endDate" name='endDate' class="inptime span2" value="${endDate }" autocomplete="off" />
	                   	</div>
	          		</label>
	          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;&nbsp;
	          		<input id="resetCondition" type="button" value="清空" class="btn btn-info"/>
	          	</div>
	         </div>
	         <div style="height: 10px"></div>
	         <div>
				<div class="b-con a-form" style="height: 30px">
		          	<div class="pd5">
		          		<input id="addRecordStat" type="button" value="添加" class="btn btn-info"/>&nbsp;&nbsp;
		          	</div>
		         </div>
			</div>
		</div>
		<div class="rb-con">
	         <div id="chartContainer"></div>
				<div class="over-auto">
					<table class="table table-bordered chargeTable">
						<tr>
							<td style="text-align: center;">日期</td>
							<td style="text-align: center;">网站site收录</td>
							<td style="text-align: center;">索引量</td>
							<td style="text-align: center;">搜索引擎</td>
							<td style="text-align: center;">操作</td>
						</tr>
						<c:forEach items="${recordStatList }" var="bean">
							<tr>
								<td style="text-align: center;" width="50px">${bean.date }</td>
								<td style="text-align: center;" width="200px">${bean.siteRecord }</td>
								<td style="text-align: center;" width="100px">${bean.indexNumber }</td>
								<td style="text-align: center;" width="100px">${bean.queryEngine }</td>
								<td style="text-align: center;" width="200px">
									<a href="####" onclick="deleteRecordStat(${bean.id });">删除</a>
									<a href="####" onclick="updateRecordStatById(${bean.id });">修改</a>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(recordStatList) == 0}">
							<tr>
								<td colspan="10">没有数据</td>
							</tr>
						</c:if>
			          </table>
			     </div>
			</div>
	</div>
	<div class="page_and_btn" style="text-align:center;">
	   	<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>

</body>

</html>