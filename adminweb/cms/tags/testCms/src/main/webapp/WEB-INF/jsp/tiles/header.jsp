<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
function logout(){
	if(confirm("确定要退出吗？")){
		document.location = "/loginOut";
	}
}
//-->
</script>
<div class="main_header">
	<div class="header_left"></div>
	<div class="header_right">
		<span id="sysTime"></span>
		<span>${sessionScope.sessionUser.user.chnName}，您好！- <a href="/user/modifyInfo" style="color: #fff">个人信息修改</a></span>
		<a href="javascript:logout();"><img src="/images/out.gif" title="退出系统" border="0"/></a>
	</div>
</div>
