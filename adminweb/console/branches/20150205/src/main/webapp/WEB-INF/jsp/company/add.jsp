<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>添加公司</title>
</head>
<body>
	<div id="man_zone">
	<form action="/company/add" method="post" id="form">
	<input name="id" value="${company.id}" type="hidden" />
	<input name="token" value="${token}" type="hidden" />
		<table class="table table-bordered" style="margin:0 0 10px 0;">
			<thead>
                	<tr>
                    	<th colspan="4" style="text-align:center">添加公司</th>
                    </tr>
                </thead>
                <tbody>
				<tr>
					<td><font class='text-error'>*</font>公司名称</td>
					<td>
					<input name="companyName" type="text" class="{required:true}" value="${company.companyName}" />
					</td>
					<td><font class='text-error'>*</font>公司域名</td>
					<td>
						<input type='text' name='companyDomain' class="{required:true}" value="${company.companyDomain}">
					</td>
				</tr>
				<tr>
					<td><font class='text-error'>*</font>公司代码</td>
					<td>
					<input name="code" type="text" class="{required:true}" value="${company.code}" />
					</td>
					<td>备注</td>
					<td>
					<textarea name="remark">${company.remark}</textarea>
					</td>
				</tr>
				<tr>
					<td>选择父公司</td>
					<td colspan="3">
						<select name="parentCode">
							<option value="">请选择</option>
							<c:forEach var="com" items="${companyList}">
								<option value="${com.code}" ${com.code==company.parentCode?"selected":""}>${com.companyName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4" >
        				<div class="text-center"><input type='submit' value='提交' class="btn-info btn" /></div>
        			</td>
				</tr>
		</table>
		</form>
	</div>
	<script type="text/javascript" charset="utf-8">
	$(function(){
		$("#form").validate({
			errorPlacement: function(error, element) {
				error.addClass('help-inline');
				error.insertAfter(element);
			},
			//包裹信息标签
			wrapper: "span",
			//验证通过执行
			submitHandler:function(form){
					form.submit(); 	
			}
		});
	});
	</script>
</body>
</html>