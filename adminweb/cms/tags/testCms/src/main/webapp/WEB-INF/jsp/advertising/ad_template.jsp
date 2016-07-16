<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=""  %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib  uri="/WEB-INF/pagetag.tld" prefix="page"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.checkbox input{_width:auto;_float:left;}
table{table-layout:fixed;word-wrap:break-word;}
</style>
<title>团车网CMS系统</title>
</head>
<body>
<form  id="adTemplateForm" method="post" action="/adTemplate/templateList">


<div class="b-con a-form">



<div class="pd5">
<!-- 
<label>城市
<select  id="city" name="city">
<option value="-1">--请选择--</option>
<option value="0">--全国--</option>
<c:forEach items="${cityMap}" var="city">
<option value="${city.key}" <c:if test="${city.key==adTemplate.cityId}">selected</c:if>>${city.value}</option>
</c:forEach>
</select>
</label> -->

<!-- <label>状态
<select  id="status" name="status">
<option value="-1">--请选择--</option>
<option value="0"  <c:if test="${adTemplate.status=='0'}">selected</c:if>>有条目使用</option>
<option value="1"  <c:if test="${adTemplate.status=='1'}">selected</c:if>>无条目使用</option>
</select>
</label>  -->
<label>编码
<input id='templateCode' name='templateCode' type='text' value="${adTemplate.code}" style=width:100px />
</label>
<label>名称
<input id='templateName' name='templateName' type='text' value="${adTemplate.name}" style=width:100px />
</label>
&nbsp;&nbsp;&nbsp;
</div>



<div  align="center">
<input type="submit" value="查询" class="btn" />
<input type="button" value="清空" onclick="javascript:clearSearch();" class="btn" />
<input type="button" value="添加模板" onclick="javascript:addAdTemplate();" class="btn" />
</div>



</div>


<div>
<table class="table_style table table-bordered">
						<thead>
							<tr align="center">
								<th >ID</th>
								<th>名称</th>
								<th>编码</th>
								<th width="400px">内容</th>
								<!-- <th>状态</th> -->
								<th >操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="position" varStatus="var">
								<tr align="center">
									<td>${position.id}</td>
									<td>${position.name}</td>
									<td>${position.code}</td>
									<td style="width:350px">
										${position.content}
									</td>
									<!-- 
									<td>
									<c:if test="${position.status=='1'}">使用状态</c:if>
									<c:if test="${position.status=='0'}">未使用</c:if>
									
									</td> -->
									<td>
										<a href="/adTemplate/editAdTemplate/${position.id}">编辑</a>
										<c:if test="${position.status=='0'}">
										<a href="/adTemplate/deleteAdTemplateById/${position.id}">删除</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
<div class="table-page">
 <page:page pager="${pb}"/>
</div>
</div>




</form>

<script type="text/javascript" charset="UTF-8">
function clearSearch(){
	$("#templateCode").val('');
	$("#templateName").val('');
}
function addAdTemplate(){
	$("#adTemplateForm").attr("action","/adTemplate/addTemplate");
	$("#adTemplateForm").submit();
}
</script>
</body>
</html>