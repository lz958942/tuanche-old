<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<% 
	if(request.getAttribute("errorMsg") != null)
	{
		out.write("<script> alert('"+request.getAttribute("errorMsg").toString()+"'); </script>");
	}		
%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<html>
<head>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/flowStat/flowStat.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
	.uploadCss{
		max-width: 400px;
		max-height: 142px;
	}
</style>
<title>流量统计添加</title>
</head>
<body>
	<form action="/flowStatManage/addFlowStat" style="padding:0 10px 0 10px; margin-top:0px" method="post" id="addFlowStatForm">
		<div>
			<label style="font-size: 16px;font-weight: bold;">流量统计添加:</label>
		</div>
		<div id="man_zone">
		<div>
		<div class="b-con a-form">
			<div align="center">
				<label class="pr15" style="width: 180px">日期：
				<div class="input-prepend">
					<span class="add-on"><i class="icon-calendar"></i></span>
					<input type="text" autocomplete="off" class="inptime span2" value="${flow.date }" style="width:70px" id="date" name="date">
                </div>
				</label>
				<label class="pr15" style="width: 200px">域名：
					<input type="text" style="width: 100px" id="domain" name="domain" value="${flow.domain }" maxlength="50"/>
	          	</label>
				<label class="pr15">pv：
					<input type="text" style="width: 100px" id="pv" name="pv" value="${flow.pv }" maxlength="11"/>
				</label>
				<label class="pr15">uv：
					<input type="text" style="width: 100px" id="uv" name="uv" value="${flow.uv }" maxlength="11"/>
					<input type="hidden" id="dataType" name="dataType" value="seo"/>
				</label>
			</div>
			<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
			<div align="center">
				<label class="pr15">
					<input id="subFlow" type="button" value="保存" class="btn btn-info"/>
				</label>
			</div>
		</div>
		</div>
		</div>
	</form>
</body>
</html>