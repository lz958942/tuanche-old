<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/decorate/decorate.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>装饰列表</title>
</head>
<body>
<form action="/decorate/toBaselist" method="post" id="searchForm">
	<div id="man_zone">
		<div>
			<div class="b-con a-form">
	          	<div class="pd5">
	          		<label class="pr15">id:<input id="id" type="text" name="id" pattern="[0-9]{0,10}" value="${decorateBase.id }" /></label>
	          		<label class="pr15">标题名称:<input id="title" type="text"  name="title" value="${decorateBase.title }" /></label>&nbsp;&nbsp;
	          		<label class="pr15">分类:
	          			<select id="kindId" name="kindId">
	          				<option value="">--请选择--</option>
	          				<c:forEach items="${kindList }" var="kind">
	          					<option value="${kind.code }" ${kind.code==decorateBase.kindId?'selected':'' }>${kind.name }</option>
	          				</c:forEach>
	          			</select>
	          		</label>
	          		<label class="pr15">上线状态:
	          			<select id="status" name="status">
	          				<option value="">--请选择--</option>
	          				<option value="1" <c:if test="${decorateBase.status=='1' }">selected='selected'</c:if> >是</option>
	          				<option value="2" <c:if test="${decorateBase.status=='2' }">selected='selected'</c:if>>否
	          			</select>
	          		</label>
	          		<label class="pr15">选择城市:
	          			<select id="cityId" name="cityId">
							<option value="">请选择</option>
							<c:forEach items="${citys }" var="citys">
								<option value="${citys.value.id }" ${citys.value.id==decorateBase.cityId?'selected':'' }>${citys.value.orderName}</option>
							</c:forEach>
						</select>
	          		</label>
	          		<label class="pr15">
	          				创建日期:
	          				<div class="input-prepend">
	                      		<span class="add-on"><i class="icon-calendar"></i></span>
	                      		<input id="starttime" type='text' name='startTime' class="querytime span2" value="${decorateBase.startTime }" readonly="readonly" autocomplete="off" />
	                      	</div>-
	                    	<div class="input-prepend">
	                      		<span class="add-on"><i class="icon-calendar"></i></span>
	                      		<input type='text' id="endtime" name='endTime' class="querytime span2" value="${decorateBase.endTime }" readonly="readonly" autocomplete="off" />
	                      	</div>
	          		</label>
	          		<div style="text-align: center">
		          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;&nbsp;
		          		<input type="button" value="清空" class="btn btn-info" onclick="javascript:clearSearch();"/>&nbsp;&nbsp;
	          		</div>
	          	</div>
	         </div>
		</div>
		<div class="rb-con">
			<div class="over-auto">
				<table class="table table-bordered chargeTable">
					<tr>
						<td style="text-align: center;">ID</td>
						<td style="text-align: center;">标题名称</td>
						<td style="text-align: center;">城市</td>
						<td style="text-align: center;">关键词</td>
						<td style="text-align: center;">url</td>
						<td style="text-align: center;">团车价</td>
						<td style="text-align: center;">市场价</td>
						<td style="text-align: center;">分类</td>
						<td style="text-align: center;">是否上线</td>
						<td style="text-align: center;">操作人</td>
						<td style="text-align: center;">操作时间</td>
						<td style="text-align: center;">操作</td>
					</tr>
					<c:if test="${decBaseList!=null&&decBaseList.size()>0 }">
						<c:forEach items="${decBaseList }" var="decBaseList">
							<tr id="tr_${decBaseList.id }">
								<td style="text-align: center;" width="50px">${decBaseList.id }</td>
								<td style="text-align: center;" width="200px">${decBaseList.title }</td>
								<td style="text-align: center;" width="100px">${func:getallCity(decBaseList.cityId) }</td>
								<td style="text-align: center;" width="150px">${decBaseList.keywords }</td>
								<td style="text-align: center;" width="200px">http://${func:getCityPY(decBaseList.cityId) }.tuanche.com/zhuangshitest/${decBaseList.id }/</td>
								<td style="text-align: center;" width="80px">${decBaseList.prePrice }</td>
								<td style="text-align: center;" width="80px">${decBaseList.marPrice }</td>
								<td style="text-align: center;" width="100px">${func:getKindName(decBaseList.kindId) }</td>
								<td id="baseStatus_${decBaseList.id }" style="text-align: center;" width="100px">
									<c:if test="${decBaseList.status=='1' }">是</c:if>
									<c:if test="${decBaseList.status=='2' }">否</c:if>
								</td>
								<td style="text-align: center;" width="150px">${decBaseList.addUserName }</td>
								<td style="text-align: center;" width="200px">${decBaseList.addTime }</td>
								<td style="text-align: center;" width="200px">
									<label>
										<a id="changeStatus_${decBaseList.id }" href="javascript:;" onclick="changelineStatus(${decBaseList.id },${decBaseList.status })">
											<c:if test="${decBaseList.status=='1' }">下线</c:if>
											<c:if test="${decBaseList.status=='2' }">上线</c:if>
										</a>
										<a href="javascript:deleteDecorate(${decBaseList.id });">删除</a>
										<a href="/decorate/selectById?id=${decBaseList.id }">修改</a>
									</label>
									<label>
										<a href="/decorate/toExpendList?bid=${decBaseList.id }">扩展</a>
										<a target="_blank" href="http://${func:getCityPY(decBaseList.cityId)) }.tuanche.com/zhuangshitest/${decBaseList.id}/">浏览</a>
									</label>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${decBaseList==null&&decBaseList.size()<=0 }">
						<tr>
							<td>没有数据</td>
						</tr>
					</c:if>
		          </table>
		     </div>
		</div>
	</div>
	<div class="page_and_btn" style="text-align:center;">
   		<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>
</body>
</html>