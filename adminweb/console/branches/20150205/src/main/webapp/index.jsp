<%@page import="com.tuanche.console.bean.Employee"%>
<%@page import="com.tuanche.console.util.Resources"%>
<%@page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
Employee employee=(Employee)request.getSession(true).getAttribute("emp_session");
if(employee!=null){
	response.sendRedirect("/main");
}
%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团车网console系统</title>
<script language="javascript">
if(self!=top) top.location=self.location;
</script>
<style type="text/css">
html,body{background:#e2e3e4;}
sup{font-size:12px; top:-15px;}	
.save_to_desktop,.add_to_favi{position:absolute;top:10%;right:10%;}
.add_to_favi{right:1%;}
</style>
</head>

<body>
<!--[if lte IE 7]>
<div class="alert text-center" style="width:700px;position:absolute;top:0;left:50%;margin-left:-350px;">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  建议您使用 Chrome、Firefox、360极速<span class="text-error">(极速模式)</span>、IE8+ 浏览器！
</div>
<![endif]-->

<div class="loginBox">
    <form name="loginform" action="/login" method="POST" id="loginform">
        <h1>团车console后台管理系统<sup>Beta</sup></h1>
        <table class="table_style table table-bordered">
        <%
        String error=(String)request.getParameter("error_key");
        if(error!=null&&error.length()>0){
        %>
            <tr>
                <td class="left_title_1">错误消息:</td>
                <td class="text-error"><%=Resources.getWebMessage(error) %></td>
            </tr>
            <% }%>
            <tr>
                <td valign="middle" align="center" class="left_title_1">用户名:</td>
                <td valign="middle"><input name="empLogin" id="empLogin" type="text" maxlength="20" class="txt {required:true}" /></td>
            </tr>
            <tr>
                <td valign="middle" align="center" class="left_title_1">密 &nbsp; 码:</td>
                <td valign="middle"> <input name="empPwd" id="empPwd" type="password" maxlength="50" class="txt {required:true}" /></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center;"><input name="Submit" value="登 &nbsp; 录" type="submit" class="btn" /> &nbsp; &nbsp; &nbsp; 
            </tr>
        </table>
    </form>        
</div>
<script type="text/javascript" charset="utf-8">
$(function(){
	$('#loginform').validate({
		submitHandler:function(form){
			form.submit();
		}
	});
});
</script>
</body>
</html>
