<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="flase"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>title</title>
<style type="text/css">
html,body{ height:100%;}
body{ margin:0; position:relative;background:#f9f9f9;border-left:solid 1px #ccc;}
#switchpic{ width:7px; height:31px; position:absolute; left:0; top:50%;}
a img{ border:none;}
</style>
<script src="/js/jquery.js"></script>
<script language="JavaScript">
function Submit_onclick(){
	if(window.parent.document.getElementById("myFrame").cols == "199,7,*") {
		window.parent.document.getElementById("myFrame").cols = "0,7,*";
		document.getElementById("ImgArrow").src="/images/switch_right.gif";
	} else {
		window.parent.document.getElementById("myFrame").cols = "199,7,*";
		document.getElementById("ImgArrow").src="/images/switch_left.gif";
	}
}

function MyLoad() {
	if(window.parent.location.href.indexOf("MainUrl")>0) {
		window.top.midFrame.document.getElementById("ImgArrow").src="/images/switch_right.gif";
	}
}
</script>
</head>
<body>
<div id="switchpic"><a href="javascript:Submit_onclick()"><img src="/images/switch_left.gif" alt="" id="ImgArrow" /></a></div>
</body>
</html>
