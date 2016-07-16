<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="flase"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网ERP</title>

</head>
<body>
	<div class="header_content">
		<div class="logo"><img src="/images/logo_tc.jpg" height="27" alt="团车网"></div>
		<!-- 
		<ul class="nav_return">
			<li><img src="/images/return.gif" width="13" height="13" style="vertical-align: middle;margin-right: 3px;" />快捷入库</li>
		</ul> -->
		<div id="user_info">
			<!--<a href="/inc/pwd/update" target="manFrame">[修改密码]</a> | -->
			<span style="margin:0 10px;">欢迎您，<strong style="position:static;width:auto;"><i class="icon-user"></i> ${sessionScope.emp_session.empName}</strong></span> |
			<a href="/inc/loginout.jsp" target="_parent" onclick="return confirm('确认退出吗？')">退出</a>
		</div>				
	</div>
</body>
</html>
