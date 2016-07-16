<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网专题管理</title>
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
						<li class="tabs_active"><a href="/questionAnswer/oneKind">一级分类</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toUpdateOne">新建一级分类</a></li>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/twoKind">二级分类</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toUpdateTwo">新建二级分类</a></li>
					</ul>
			   </div>
			</td>
		</tr>
		
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr">
							<th style="white-space:nowrap;">id</th>
							<th style="white-space:nowrap;">分类名称</th>
							<th style="white-space:nowrap;">排序</th>
							<th style="white-space:nowrap;">操作</th>
						</tr>
					</thead>
					<tbody align="center" >
					<c:choose>
						<c:when test="${not empty kind }">
							<c:forEach items="${kind }" var="kind">
															
									<tr class="" id="sp_${kind.id }">
										<td>${kind.id }</td>										
										<td>${kind.name}</td>
										<td>${kind.sort}</td>
										<td>
											<a href="/questionAnswer/toUpdateOne?id=${kind.id}">修改</a>
											<a href="javascript:void(0)" onclick="deletet(${kind.id})" >删除</a> 
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
			</div>
</table>
</body>
<script type="text/javascript">
function deletet(id){
	if(confirm('确认删除一级分类？')){
		window.location.href='/questionAnswer/deleteKind?id='+id;
	}else{
		return false;
	}
}
</script>
</html>