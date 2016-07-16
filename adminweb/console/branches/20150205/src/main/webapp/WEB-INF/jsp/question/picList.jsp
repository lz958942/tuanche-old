<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网问答管理</title>
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
						<li class="tabs_active"><a href="/questionAnswer/toPicList">图片管理</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toUpdatePic">新增图片</a></li>
					</ul>
			   </div>
			</td>
		</tr>
	</table>	
	<form action="toUpdateSort" method="post" id='questPic'>
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr" style="background-color:#2b84be;color:white" >
							<th style="white-space:nowrap;">排序</th>
							<th style="white-space:nowrap;">图片标题</th>
							<th style="white-space:nowrap;">图片</th>
							<th style="white-space:nowrap;">上传时间</th>
							<th style="white-space:nowrap;">操作</th>
						</tr>
					</thead>
					<tbody align="center" >
					<c:choose>
						<c:when test="${not empty pics }">
							<c:forEach items="${pics}" var="pic">
															
									<tr>
									
										<td>
										<input type="hidden" name='id' id='id' value='${pic.id}'/>
										<input type="text" name='sort' id='sort' value='${pic.sort }'/></td>
										<td>${pic.title}</td>
										<td><c:if test="${pic.picUrl!=''}"><img style="width:150px;height:100px;"  src="${pic.picUrl}"></c:if>										
										</td>										
										<td>${pic.buildTime}</td>
										<td>
											<a href="/questionAnswer/toUpdatePic?id=${pic.id}">修改</a>
											<c:if test="${pic.picStatus==2}">
											<a href="javascript:void(0)" onclick="deletet1(${pic.id})">隐藏</a>
											</c:if> 
											<c:if test="${pic.picStatus==1}">
											<a href="javascript:void(0)" onclick="deletet2(${pic.id})">上线</a>
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
<input type="submit" value='排序'/>
</form>
</body>
<script type="text/javascript">
function deletet1(id){
	if(window.confirm('你确定隐藏图片?')){
		$.post('/questionAnswer/deletePic?id='+id+'&status=1',function(result){
			$("#questPic").submit();
		},'json');
	}
}
function deletet2(id){
	if(window.confirm('你确定上线图片?')){
		$.post('/questionAnswer/deletePic?id='+id+'&status=2',function(result){
			$("#questPic").submit();
		},'json');
	}
}
</script>
</html>