<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>黑词查询</title>
</head>
<body>
<div id="man_zone">
<form method="post" action="/black/search" id='black' >
	<div>
		<div class="b-con a-form">
          	<div class="pd5">
          		<label class="pr15">敏感词类型:
					<select  name='type' id='muio'>
					<c:if test="${configs==null || configs.size()==0 }">
						<option value="">请选择</option>
					</c:if>
					<c:if test="${configs!=null&& configs.size()>0 }">
					<option selected="selected" value="">请选择</option>
						<c:forEach items="${configs}" var="c">
							<option <c:if test="${c.code==blackBean.type}">selected="selected"</c:if>  value="${c.code}">${ c.name}</option>
						</c:forEach>
					</c:if>
					</select>
          		</label>
          	<%-- 	<label class="pr15">添加人:
					<select  name='createUid' id='muio'>
					<c:if test="${editers==null || editers.size()==0 }">
						<option value="0">无</option>
					</c:if>
					<c:if test="${editers!=null&& editers.size()>0 }">
						<option value="0">不限</option>
						<c:forEach items="${editers}" var="editer">
								<option id="editersided" value="${editer.id==null?0:editer.id}"
									<c:if test="${editer.id== blackBean.createUid}"> selected="selected" </c:if>>${editer.empName}</option>
							</c:forEach>
					</c:if>
					</select>
          		</label> --%>
          		<label class="pr15">敏感词:
          			<input  name="word" type="text" value="${blackBean.word }">
          			<input type="button" value="搜索" onclick="submit()" class="btn btn-info">
          		</label>
          		<label class="pr25">
<!--           			<input type="button" value="添加敏感词" onclick="window.open('/black/ToAddBlack')" class="btn btn-info">
 -->          			<a href="/black/ToAddBlack" class="btn btn-info">添加敏感词</a>
          		</label>
          		<%-- <label class="pr15">
          			日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:
          			<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='starttime' class="querytime span2" value="<fmt:formatDate value="${starttime}" pattern="yyyy-MM-dd" />" readonly="readonly" autocomplete="off" />
                      	</div>-
                      	<div class="input-prepend">
                      		<span class="add-on"><i class="icon-calendar"></i></span>
                      		<input type='text' name='endtime' class="querytime span2" value="<fmt:formatDate value="${endtime}" pattern="yyyy-MM-dd" />" readonly="readonly" autocomplete="off" />
                      	</div>
          		</label> --%>
          	</div>
         </div>
	</div>
	<div class="rb-con">
		<div class="over-auto">
			<table class="table table-bordered chargeTable">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th>编号</th>
						<th>敏感词</th>
						<th>类型</th>
						<th>添加时间</th>
						<th>修改时间</th>
						<th>添加人</th>
						<th>修改人</th>
						<th>操作</th>
					</tr>
	          	</thead>
	          	<tbody>
						<c:forEach items="${list}" var="list">
						<tr style="background:none repeat scroll 0 0 #E8F1FD">
							<td>${list.id}</td>
		  					<td>${list.word}</td>
		  					<td><c:if test="${configs!=null&& configs.size()>0 }">
		  					<c:forEach items="${configs}" var="c2">
							 <c:if test="${c2.code==list.type}">${ c2.name}</c:if>
						</c:forEach>
		  					</c:if>
		  					</td>
		  					<td>${list.createTime}</td>
		  					<td>${list.updateTime}</td>	
							<td>${func:getEditName(list.createUid)}</td>
							<td>${func:getEditName(list.updateUid)}</td>
							<td>
							<a href="/black/ToUpdateBlack?id=${list.id }" >修改</a>
							<!-- <a href="javascript:void(0）" onclick="">删除</a> -->
							</td>
						</tr>
						</c:forEach>
	          	</tbody>
	          </table>
	     </div>
	</div>
	<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
	</form>
</div>

<script type="text/javascript">
$('.querytime').live("click",function() {
	WdatePicker({
	isShowClear:false,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});
</script>
</body>
</html>