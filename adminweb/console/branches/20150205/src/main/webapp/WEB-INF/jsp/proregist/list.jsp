<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>汽车装饰报名查询</title>
</head>
<body>
<form action="/regist/toHome" method="post" id="registForm">
	<div id="man_zone">
		<div>
			<div class="b-con a-form">
	          	<div class="pd5">
	          		<label class="pr15">id:<input id="id" type="text" name="id" pattern="[0-9]{0,10}" value="${subjectInfo.id }" /></label>
	          		<label class="pr15">报名人:<input id="userName" type="text"  name="userName" value="${subjectInfo.userName }" /></label>&nbsp;&nbsp;
	          		<label class="pr15">联系电话:<input id="phone" type="text" name="phone" value="${subjectInfo.phone }" /></label>&nbsp;&nbsp;
	          		<label class="pr15">选择用品类型:
	          			<select id="styleId" name="styleId" onchange="changeList()">
							<option value="">请选择</option>
							<c:if test="${subKindList!=null&&subKindList.size()>0 }">
								<c:forEach items="${subKindList }" var="subKindList">
									<option value="${subKindList.id }" <c:if test="${subKindList.id==subjectInfo.styleId }">selected='select'</c:if>>${subKindList.name }</option>
								</c:forEach>
							</c:if>
						</select>
	          		</label>
	          		<label class="pr15">选择专题:
	          		    <input type="hidden" id="ztids" value="${subjectInfo.ztId }" />
	          			<select id="ztId" name="ztId">
							<option value="">请选择</option>
							<c:if test="${subjectList!=null&&subjectList.size()>0 }">
								<c:forEach items="${subjectList }" var="subjectList">
									<option value="${subjectList.id }" <c:if test="${subjectList.id==subjectInfo.ztId }">selected='select'</c:if>>${subjectList.name }</option>
								</c:forEach>
							</c:if>
						</select>
	          		</label>
	          		<label class="pr15">是否购车:
	          			<select id="status" name="status">
							<option value="">请选择</option>
							<option value="1" <c:if test="${subjectInfo.status=='1' }">selected='select'</c:if>>是</option>
							<option value="0" <c:if test="${subjectInfo.status=='0' }">selected='select'</c:if>>否</option>
						</select>
	          		</label>
	          		<label class="pr15">新分类:
	          		    <input type="hidden" id="newKindIds" value="${subjectInfo.newKindId }" />
	          			<select id="newKindId" name="newKindId" onchange="changeNewKind()">
							<option value="">请选择</option>
							<c:if test="${kindList!=null&&kindList.size()>0 }">
								<c:forEach items="${kindList }" var="kindList">
									<option value="${kindList.code }" <c:if test="${kindList.code==subjectInfo.newKindId }">selected='select'</c:if>>${kindList.name }</option>
								</c:forEach>
							</c:if>
						</select>
	          		</label>
	          		<%-- <label class="pr15">新专题:
	          		    <input type="hidden" id="newSubjectIds" value="${subjectInfo.newSubjectId }" />
	          			<select id="newSubjectId" name="newSubjectId">
							<option value="">请选择</option>
							<c:if test="${ztMap!=null&&ztMap.size()>0 }">
								<c:forEach items="${ztMap }" var="ztMap">
									<option value="${ztMap.key }" <c:if test="${ztMap.key==subjectInfo.newSubjectId }">selected='select'</c:if>>${ztMap.value }</option>
								</c:forEach>
							</c:if>
						</select>
	          		</label> --%>
	          		<label class="pr15">
	          			报名日期:
	          			<div class="input-prepend">
	                      		<span class="add-on"><i class="icon-calendar"></i></span>
	                      		<input id="starttime" type='text' name='startTime' class="querytime span2" value="${subjectInfo.beginTime }" readonly="readonly" autocomplete="off" />
	                      	</div>-
	                      	<div class="input-prepend">
	                      		<span class="add-on"><i class="icon-calendar"></i></span>
	                      		<input type='text' id="endtime" name='endTime' class="querytime span2" value="${subjectInfo.stopTime }" readonly="readonly" autocomplete="off" />
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
					
				</table>
			</div>
		</div>
		<div class="rb-con">
			<div class="over-auto">
				<table class="table table-bordered chargeTable">
					<tr>
						<td style="text-align: center;">ID</td>
						<td style="text-align: center;">报名姓名</td>
						<td style="text-align: center;">手机号码</td>
						<td style="text-align: center;">专题名称</td>
						<td style="text-align: center;">汽车用品分类</td>
						<td style="text-align: center;">车牌号</td>
						<td style="text-align: center;">车主地址</td>
						<td style="text-align: center;">是否购车</td>
						<td style="text-align: center;">新装饰标题</td>
						<td style="text-align: center;">新装饰分类</td>
						<td style="text-align: center;">报名时间</td>
					</tr>
					<c:if test="${subInfList!=null&&subInfList.size()>0 }">
						<c:forEach items="${subInfList }" var="subjectInfo">
							<tr>
								<td style="text-align: center;" width="50px">${subjectInfo.id }</td>
								<td style="text-align: center;" width="150px">${subjectInfo.userName }</td>
								<td style="text-align: center;" width="150px">${subjectInfo.phone }</td>
								<td style="text-align: center;" width="200px">${subjectInfo.ztName }</td>
								<td style="text-align: center;" width="200px">${subjectInfo.styleName }</td>
								<td style="text-align: center;" width="100px">${subjectInfo.carLicense }</td>
								<td style="text-align: center;" width="400px">${subjectInfo.address }</td>
								<td style="text-align: center;" width="50px">
									<c:if test="${subjectInfo.status=='1' }">是</c:if>
									<c:if test="${subjectInfo.status=='0' }">否</c:if>
								</td>
								<td style="text-align: center;" width="300px">${subjectInfo.title }</td>
								<td style="text-align: center;" width="200px">
									<c:if test="${kindList!=null&&kindList.size()>0 }">
										<c:forEach items="${kindList }" var="kindList">
											<c:if test="${kindList.code==subjectInfo.newKindId }">${kindList.name }</c:if>
										</c:forEach>
									</c:if>
								</td>
								<td style="text-align: center;">${subjectInfo.registTime }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${subInfList==null&&subInfList.size()<=0 }">
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
<script type="text/javascript">
$(function(){
	changeList(0);
	changeNewKind();
	
});
$('.querytime').live("click",function() {
	WdatePicker({
	isShowClear:true,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});
	function changeList(x){
		
		var ztHtml=$("#ztId");
		var ztids=$("#ztids").val();
		ztHtml.html("<option value=\"\">请选择</option>");
		var styleId=$("#styleId").val();
		$.ajax({
			url:"/regist/ztList",
			type:"post",
			dataType:"json",
			data:{
				id:styleId
			},
		success:function(data){
			if(data.subjectList!=null){
				var length=data.subjectList.length;
				var list=data.subjectList;
				var ztHtml=$("#ztId");
				$(list).each(function(i){
					if(x==1){
						 var option = "<option value="+list[i].id+">"+list[i].name+"</option>";
					}else{
						if(ztids==list[i].id){
							 var option = "<option value="+list[i].id+" selected='select'>"+list[i].name+"</option>";
						 }else{
							 var option = "<option value="+list[i].id+">"+list[i].name+"</option>";
						 }
					}
					 ztHtml.append(option);
				});
			}
		}
		});
	}
	function clearSearch(){
		$("#id").val('');
		$("#userName").val('');
		$("#phone").val('');
		$("#ztId").val('');
		$("#styleId").val('');
		$("#status").val('');
		$("#starttime").val('');
		$("#endtime").val('');
		$("#newSubjectId").val('');
		$("#newKindId").val('');
		changeList(1);
		$("#registForm").submit();
	}
	
	function changeNewKind(){
		var newKindId=$("#newKindId").val();
		var newSubjectId=$("#newSubjectId");
		if(newKindId!="1"&&newKindId!=""){
			var option = "<option value=''>请选择</option>";
			newSubjectId.html(option);
		}else{
			var option = "<option value=''>请选择</option><option value='1'>3M贴膜</option>";
			newSubjectId.html(option);
		}
	}
</script>
</body>
</html>