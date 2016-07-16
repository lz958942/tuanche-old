<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="flase"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" href="/css/common.css" type="text/css" />
<title>团车网CMS后台管理系统</title>
<style type="text/css">
html{ height:100%;}
</style>
<script language="javascript" src="/js/jquery.js"></script>
<script language="javascript"> 
//if(top.document.location.href!=document.location.href) top.document.location.href=document.location.href; 
$(window).resize(function(){
	var ht = $(window).height();
	$("#myFrame").css("height",ht);
})
</script>
</head>
<frameset rows="32,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="/inc/top.jsp" name="topFrame" frameborder="no" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset id="myFrame" name="myFrame" cols="199,7,*" frameborder="no" border="0" framespacing="0">
		<frame src="/inc/left.jsp" name="leftFrame" frameborder="no" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="/inc/switch.jsp" name="midFrame" frameborder="no" scrolling="no" noresize="noresize" id="midFrame" title="midFrame" />
		<frame src="/inc/welcome.jsp" name="manFrame" frameborder="no" id="manFrame" title="manFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>