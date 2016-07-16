<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>添加账户</title>
</head>
<body>
	<div id="man_zone">
	<form action="/company/accountadd" method="post" id="form">
	<input name="id" value="${account.id}" type="hidden" />
	<input name="accountCode" value="${account.accountCode}" type="hidden" />
	<input name="token" value="${token}" type="hidden" />
		<table class="table table-bordered" style="margin:0 0 10px 0;">
			<thead>
                	<tr>
                    	<th colspan="4" style="text-align:center">添加账户</th>
                    </tr>
                </thead>
                <tbody>
				<tr>
					<td><font class='text-error'>*</font>账户名称</td>
					<td>
					<input name="accountName" type="text" class="{required:true} span2" value="${account.accountName}" />
					</td>
					<td><font class='text-error'>*</font>所属公司</td>
					<td>
						<select name="companyId" class="{required:true}">
							<option value="">请选择</option>
							<c:forEach var="company" items="${companyList}">
								<option value="${company.id}" ${account.companyId==company.id?"selected":""}>${company.companyName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<c:forEach var="bType" items="${businessTypeMap}">
				<tr>
					<td><font class='text-error'>*</font>${nameMap[bType.key]}</td>
					<td colspan="3">
						<c:forEach var="sType" items="${bType.value}">
							<label class="radio inline"><input type="radio" name="${bType.key}" value="${sType.bizCode}" class="{required:true}" ${func:inString(sType.bizCode,account.bizCode)?"checked":"" } />${sType.bizName}</label>
						</c:forEach>
					</td>
				</tr>
				</c:forEach>
				<tr>
					<td>备注</td>
					<td colspan="3">
						<textarea  name="remark">${account.remark}</textarea>
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