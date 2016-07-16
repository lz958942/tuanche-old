<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网周刊人员管理</title>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery.tipsy.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>

 <!-- ztree ue-->
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
</head>
<body>
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/magazine/employeList">周刊人员管理</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/magazine/toUpdateEmploye">新增周刊人员</a></li>
					</ul>
			   </div>
			</td>
		</tr>
	</table>	
	<form action="/magazine/employeList" method="post" id='questPic'>
	<!-- <table  style="height:40px">
	<tr style="height:10px"></tr>
	<tr>
	<td> 
	<select name='position'>
		<option value=''>-----请选择-----</option>
		<option value='1'>-----统筹-----</option>
		<option value='2'>-----摄影-----</option>
		<option value='3'>-----设计/制作-----</option>
	</select>
	</td>
	<td><input type="text" name='name'/></td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" ></td>
	</tr>
	<tr style="height:10px"></tr>
	</table> -->
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr" style="background-color:#2b84be;color:white" >
							<th style="white-space:nowrap;width:100px">职务</th>
							<th style="white-space:nowrap;width:100px">昵称</th>
							<th style="white-space:nowrap;width:160px">头像</th>
							<th style="white-space:nowrap;">创建时间</th>
							<th style="white-space:nowrap;">状态</th>
							<th style="white-space:nowrap;width:80px">操作</th>
						</tr>
					</thead>
					<tbody align="center" >
					<c:choose>
						<c:when test="${not empty emps }">
							<c:forEach items="${emps}" var="emp">
															
									<tr>
									
										<td>
										<c:if test="${emp.position==1}">统筹</c:if>
										<c:if test="${emp.position==2}">摄影</c:if>
										<c:if test="${emp.position==3}">设计/制作</c:if>
										</td>
										<td>${emp.name}</td>
										<td><c:if test="${emp.employePic!=''}"><img style="width:150px;height:100px;"  src="${emp.employePic}"></c:if>										
										</td>										
										<td>${emp.buildTime}</td>
										<td>
										<c:if test="${emp.employeStatus==0}">隐藏</c:if>
										<c:if test="${emp.employeStatus==1}">上线</c:if>
										</td>
										<td>
											<a href="/magazine/toUpdateEmploye?id=${emp.id}">修改</a>
											<c:if test="${emp.employeStatus==1}">
											<a href="javascript:void(0)" onclick="deletet1(${emp.id})">隐藏</a>
											<a href="javascript:void(0)" onclick="deletet3(${emp.id})">删除</a>
											</c:if> 
											<c:if test="${emp.employeStatus==0}">
											<a href="javascript:void(0)" onclick="deletet2(${emp.id})">上线</a>
											<a href="javascript:void(0)" onclick="deletet3(${emp.id})">删除</a>
											</c:if>
										</td>
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>											
								<tr class="main_info">
									<td colspan="14">没有相关数据</td>
								</tr>
						</c:otherwise>					
					</c:choose>													
					</tbody>
				
</table>

<div class="page_and_btn" style="text-align: center;">
	<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>
</body>
<script type="text/javascript">
function deletet1(id){
	if(window.confirm('你确定隐藏图片?')){
		$.post('/magazine/updatestatus?id='+id+'&status=0',function(result){
			$("#questPic").submit();
		},'json');
	}
}
function deletet2(id){
	if(window.confirm('你确定上线图片?')){
		$.post('/magazine/updatestatus?id='+id+'&status=1',function(result){
			$("#questPic").submit();
		},'json');
	}
}
function deletet3(id){
	if(window.confirm('你确定删除图片?')){
		$.post('/magazine/updatestatus?id='+id+'&status=-1',function(result){
			$("#questPic").submit();
		},'json');
	}
}
</script>
</html>