<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>公司列表</title>
</head>
<body>
	<div id="man_zone">
      	<div class="rb-con">
    		<div class="over-auto">
      			<table class="table table-bordered chargeTable">
      				<thead>
          				<tr>
          					<th>编号</th>
          					<th>公司名称</th>
          					<th>公司域名</th>
          					<th>公司代码</th>
          					<th>操作</th>
          				</tr>
          				</thead>
          				<tbody>
          				<c:forEach var="company" items="${companyList}">
          					<tr>
          						<td>${company.id}</td>
          						<td>${company.companyName}</td>
          						<td>${company.companyDomain}</td>
          						<td>${company.code}</td>
          						<td><a href="/company/add?id=${company.id}">修改</a>&nbsp;&nbsp;<a href="/company/accountadd?companyId=${company.id}">添加账户</a></td>
          					</tr>
          				</c:forEach>
          				<tr>
          					<td colspan="5"><center><a href="/company/add" class="btn btn-info">添加</a></center></td>
          				</tr>
          				</tbody>
      			</table>
      		</div>
      	</div>
    </div>
</body>
</html>