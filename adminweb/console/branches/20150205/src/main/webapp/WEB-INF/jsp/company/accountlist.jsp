<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>账户列表</title>
</head>
<body>
<div id="man_zone">
      	<div class="rb-con">
    		<div class="over-auto">
      			<table class="table table-bordered chargeTable">
      				<thead>
          				<tr>
          					<th>编号</th>
          					<th>账户名称</th>
          					<th>所属公司</th>
          					<th>业务类型</th>
          					<th>账户代码</th>
          					<th>操作</th>
          				</tr>
          				</thead>
          				<tbody>
          				<c:forEach var="account" items="${accountList}">
          					<tr>
          						<td>${account.id}</td>
          						<td>${account.accountName}</td>
          						<td>${account.companyName}</td>
          						<td>${func:getBusinessName(account.bizCode,businessMap)}</td>
          						<td>${account.accountCode}${account.bizCode}</td>
          						<td><a href="/company/accountadd?id=${account.id}">修改</a></td>
          					</tr>
          				</c:forEach>
          				<tr>
          					<td colspan="6"><center><a href="/company/accountadd" class="btn btn-info">添加</a></center></td>
          				</tr>
          				</tbody>
      			</table>
      		</div>
      	</div>
    </div>
</body>
</html>