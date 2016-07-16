<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="flase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<title>团车网console系统</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
</head>
<style type="text/css">
 	*{
 	
	font-size:12px;
	}
 </style>
<script type="text/javascript">
/* function windowOpen(){
	openWindow("/sysConfig/addConfig","yes",600,500);
}
function openWindow(mypage,haveScroll,theWidth,theHight,theName)
{
	var w = paramexists(theWidth)? theWidth:600;
	var h = paramexists(theHight)? theHight:450;
	var scroll = paramexists(haveScroll)? haveScroll:'no';
	var myname = paramexists(theName)? theName:'';
	
	LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
	TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
	settings =
	'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
	window.open(mypage,myname,settings)
}

function paramexists(what){
	return(typeof what!="undefined" && what!="")
	} */
</script>
<body>
<form  id="form3" name="form3" method="post" action="/sysConfig/home">
<input type="hidden" name="type" value="2">
	<div id="man_zone">
		<table class="table_style table table-bordered" >
			<tr class="info">
				<td colspan="6"  class="left_title_2 " style="background-color: ;"><a href="/sysConfig/addConfig" class="btn fr"><i class="icon-cog"></i> 新增配置</a>配置管理</td>
			</tr>
			<tr>
				<td colspan="6">
					键值:<input type="text" id="keyvalue" name="key" value="${sysConfig.key}" style="height: 25px" />
					编码名称:<input type="text" id="name" name="name" value="${sysConfig.name}" size="5" maxlength="15" style="height: 25px">
				  			<input type="submit" name="Submit" value="查询" class="btn btn-info"> 
				</td>
			</tr>
			<tr >
				<td>键值</td>
				<td>编码</td>
				<td>编码名称</td>
				<td>添加人</td>
				<td>添加时间</td>
				<td>描述</td>
				<td style="width:20px;height: 20px">操作</td>
			</tr>
			<c:forEach var="sysConfig" items="${sysConfiglist}">
				<tr>
					<td>${sysConfig.key}</td>
					<td>${sysConfig.code}</td>
					<td>${sysConfig.name}</td>
					<td>
					<c:if test="${sysConfig.createUid==null }">
										暂无
									</c:if>
									<c:if test="${sysConfig.createUid!=null }">
									${func:getEditName(sysConfig.createUid)}
									</c:if>
					</td>
					<td>
					<c:if test="${sysConfig.createTime==null}">
					无
					</c:if>
					<c:if test="${sysConfig.createTime!=null}">
					${sysConfig.createTime }
					</c:if>
					</td>
					<td>${sysConfig.desc}</td>
					<td><a href="/sysConfig/updateBefore?id=${sysConfig.id}" class="btn btn-small btn-primary">操作</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
	</div>
	</form>
</body>
</html>
