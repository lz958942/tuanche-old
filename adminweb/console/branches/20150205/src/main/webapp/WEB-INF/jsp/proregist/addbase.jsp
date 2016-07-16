<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<html>
<head>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/decorate/decorate.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
	.uploadCss{
		max-width: 400px;
		max-height: 142px;
	}
</style>
<title>装饰基本信息添加</title>
</head>
<body>
	<form action="/decorate/addDecorateBase" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="addBaseForm">
		<div>
			<label style="font-size: 16px;font-weight: bold;">基本信息添加:</label>
			<input type="hidden" id="id" name="id" value="${decorateBase.id }"/>
		</div>
		<div id="man_zone">
		<div>
		<div class="b-con a-form">
			<div align="center">
				<label class="pr15" style="width: 200px">选择城市：
	          			<select id="cityId" name="cityId">
							<option value="">请选择</option>
							<c:forEach items="${citys }" var="citys">
								<option value="${citys.value.id }" ${citys.value.id==decorateBase.cityId?'selected':'' }>${citys.value.orderName}</option>
							</c:forEach>
						</select>
	          	</label>
	          	<label class="pr15" style="width: 180px">选择分类:
	          			<select id="kindId" name="kindId">
							<option value="">请选择</option>
							<c:forEach items="${kindList }" var="kind">
	          					<option value="${kind.code }" ${kind.code==decorateBase.kindId?'selected':'' }>${kind.name }</option>
	          				</c:forEach>
						</select>
	          	</label>
			</div>		
			<div align="center">
				<label class="pr15">装饰名称：
					<input type="text" id="title" name="title" value="${decorateBase.title }" maxlength="15" style="width: 500px"/>
				</label>
				<span style="color: red">*(请控制在15字左右)</span>
			</div>
			<div align="center">
				<label class="pr15">关键词：
					<input type="text" id="keywords" name="keywords" value="${decorateBase.keywords }" maxlength="10" style="width: 500px"/>
				</label>
				<span style="color: red">*(请控制在10字左右)</span>
			</div>
			<div align="center">
				<label class="pr15">装饰描述：
					<textarea  id="dec_desc" name="decDesc" maxlength="80" style="width: 500px;height: 100px">${decorateBase.decDesc }</textarea>
				</label>
				<span style="color: red">*(请控制在80字左右)</span>
			</div>
			<div align="center">
				<label class="pr15" style="width: 180px">团车价：
					<input type="text" style="width: 100px" id="pre_price" name="prePrice" value="${decorateBase.prePrice }" maxlength="5"/>
				</label>
				<label class="pr15">市场价：
					<input type="text" style="width: 100px" id="mar_price" name="marPrice" value="${decorateBase.marPrice }" maxlength="5"/>
				</label>
			</div>
			<div align="center">
				<label class="pr15">效果图：
					  <img id="topPic" <c:if test="${decorateBase.topPicUrl!=null }">src='${picHost }${decorateBase.topPicUrl }' class='uploadCss'</c:if> 
					  		<c:if test="${decorateBase.topPicUrl==null }">src='/images/upload.jpg'</c:if> onclick="$('#topPicFile').trigger('click')"/>
					  <input type="hidden" id="topPicUrl" name="topPicUrl"  value="${decorateBase.topPicUrl }" />
				</label>
				<span style="color: red">*</span>
				<label class="pr15">列表图：
					  <img  id="titlePicUrl" <c:if test="${decorateBase.picUrl!=null }">src='${picHost }${decorateBase.picUrl }' class='uploadCss'</c:if> 
					  		<c:if test="${decorateBase.picUrl==null }">src='/images/upload.jpg'</c:if> onclick="$('#PicFile').trigger('click')"/>
					   <input type="hidden" id="picUrl" name="picUrl"  value="${decorateBase.picUrl }" />
				</label>
				<span style="color: red">*</span>
			</div>
			<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
				<div style="display: none">
					<input id="topPicFile" name="topPicFile" type="file"  onchange="upload('topPicFile','topPicUrl','topPic','4')"/>
					<input id="PicFile" name="PicFile" type="file"  onchange="upload('PicFile','picUrl','titlePicUrl','4')"/>
				</div>
			<div align="center">
				<label class="pr15">
					<input type="button" value="保存" class="btn btn-info" onclick="sub();"/>
				</label>
			</div>
		</div>
		</div>
		</div>
	</form>
</body>
</html>