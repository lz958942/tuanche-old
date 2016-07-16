<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<html>
<head>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/recordStat/recordStat.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
	.uploadCss{
		max-width: 400px;
		max-height: 142px;
	}
</style>
<title>收录统计添加</title>
</head>
<body>
	<form action="/recordStatManage/updateRecordStat" style="padding:0 10px 0 10px; margin-top:0px" method="post" id="updateRecordStatForm">
		<div>
			<label style="font-size: 16px;font-weight: bold;">收录统计详情:</label>
			<input type="hidden" id="id" name="id" value="${record.id }"/>
		</div>
		<div id="man_zone">
		<div>
		<div class="b-con a-form">
			<div align="center">
				<label class="pr15" style="width: 180px">日期：
				<div class="input-prepend">
					<span class="add-on"><i class="icon-calendar"></i></span>
					<input type="text" autocomplete="off" value="${record.date }" class="inptime span2" style="width:70px" id="date" name="date">
                </div>
				</label>
				<label class="pr15">网站site收录：
					<input type="text" style="width: 100px" id="siteRecord" name="siteRecord" value="${record.siteRecord }" maxlength="11"/>
				</label>
				<label class="pr15">索引量：
					<input type="text" style="width: 100px" id="indexNumber" name="indexNumber" value="${record.indexNumber }" maxlength="11"/>
				</label>
				<label class="pr15" style="width: 200px">搜索引擎：
	          			<select id="queryEngine" name="queryEngine">
							<option value="baidu" selected>百度</option>
						</select>
	          	</label>
			</div>
			<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
			<div align="center">
				<label class="pr15">
					<input id="updateRecord" type="button" value="保存" class="btn btn-info"/>
				</label>
			</div>
		</div>
		</div>
		</div>
	</form>
</body>
</html>