<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>自定义评论查询</title>
<html>
<head>
<script type="text/javascript" src="/js/common/jsdate.js"></script> 
	<script type="text/javascript">
	$(document).ready(function(){ 
		var bid=$("#brandId").val();
		if(bid!=null &&　bid!=''){
			 $("#carstyleId").append("<option selected='selected' value=''>请选择车型</option>");
			publicAjax("/json/carstyle/ajaxStyle?","brandID",bid,"carstyleId");
		}
		});
	
	function getCarstyleModels(id){
		if(id==null || id==""){
			return;
		}
		 $("#carstyleId option").remove();
		 $("#carstyleId").append("<option selected='selected' value=''>请选择车型</option>");
		publicAjax("/json/carstyle/ajaxStyle?","brandID",id,"carstyleId");
	}
	function publicAjax(url,dataName,dataArgs,selectId){
		$.ajax({
			   type: "POST",
			   url: url+$.trim(dataName)+"="+dataArgs,
			   dataType:'json',
			   success: function(data){
				   var $carstyleId=$("#carstyleIdD").val();
				   if($carstyleId!=null && ""!=$carstyleId){
					   for(i in data) {
						   if($carstyleId==data[i].id){
							   $("#"+selectId).append("<option selected='select'  value="+data[i].id+">"+data[i].style+"</option>");
						   }
					   		$("#"+selectId).append("<option value="+data[i].id+">"+data[i].style+"</option>");
					   }
				   }else{
					   for(i in data) {
				   		$("#"+selectId).append("<option value="+data[i].id+">"+data[i].style+"</option>");
				   }
				   } 
			   }
			});
	}
	</script>
</head>
<body>
<div style="background-color:#8F8FBD;height: 35px">
				<td colspan="6"  class="left_title_2 " style="background-color: ;"><a href="/review/addReviewTo" class="btn fr"><i class="icon-cog"></i> 评论添加</a>评论添加管理</td>
	</div>
	<input id="carstyleIdD" type="hidden" value="${reviewBean.carstyleId }">
<form method="post" action="/review/homeReview" id='black' >
<table>
				<tr>
					<td >评论编号：&nbsp;</td>
					<td><input onkeyup="this.value=this.value.replace(/[^\d]/g,'') " type="text"maxlength="10" id="id" name="id" style="width:200px; height:25px" value="${reviewBean.id}" /></td>
				<td >是否晒车：</td>
					<td id="pradio">
						<select name="ishavepic" id="ishavepic" style="width: 200px">
							<option  value="1" <c:if test="${reviewBean.ishavepic ==1}">selected="select"</c:if>>有图片</option>
							<option value="0" <c:if test="${reviewBean.ishavepic ==0}">selected="select"</c:if> >无图片</option>
							<option value="3" <c:if test="${reviewBean.ishavepic ==3||review.ishavepic==null}">selected="select"</c:if>> 全部</option>
						</select>
					</td>
					<td >城市：</td>
					<td id="pradio">
						<select name="cityId" id="cityId" style="width: 200px">
						<option value="" selected="selected">请选择城市</option>
						<c:forEach items="${citys }" var="city">
							<option <c:if test="${reviewBean.cityId==city.value.id }">selected='selected'</c:if>  value="${city.value.id }">${city.value.name }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr >
					<td >品牌：&nbsp;</td>
					<td>
					<select id="brandId" name="brandId" style="width: 200px" onchange="getCarstyleModels(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option  <c:if test="${reviewBean.brandId ==b.id}">selected="select"</c:if> value="${b.id }">${b.reviewInitial}${b.name}</option>
						</c:forEach>
					</select>
					</td>
					<td >评论车型：&nbsp;</td>
					<td>
					<select name="carstyleId" id="carstyleId" style="width: 200px" >
					</select>
					<input type="button" value="搜索" class="btn"onclick="submit()" />
 					</td>
					<%-- <td >评论时间：</td>
					<td>
						<input id="startDate" name="createtime" class="dateCss"
						type="text" onclick="SelectDate(this,'yyyy-MM-dd','')"
						readonly="readonly" value="${review.createtime }">
					</td> --%>
				</tr>
				<!-- <tr>
					<td><input type="button" value="搜索" class="btn"onclick="submit()" /></td>
				</tr> -->
			</table>
		<div class="over-auto">
			<table class="table table-bordered chargeTable">
				<thead>
					<tr style="background:none repeat scroll 0 0 #E8F1FD">
						<th>编号</th>
						<th>城市</th>
						<th>品牌</th>
						<th>车型</th>
						<th>晒图</th>
						<th>评论内容</th>
						<th>类型</th>
						<th>评论时间</th>
						<th>评论人</th>
						<th>操作</th>
					</tr>
	          	</thead>
	          	<tbody>
						<c:forEach items="${list}" var="list">
						<tr style="background:none repeat scroll 0 0 #E8F1FD">
							<td>${list.id}</td>
							<td>
							<c:if test="${list.cityId==null }">无</c:if>
							<c:if test="${list.cityId!=null }">${func:getallCity(list.cityId)}</c:if>
		  					</td>
		  					<td>
		  					<c:if test="${list.brandId!=null }">
		  					${func:getBrandName(list.brandId)}
		  					</c:if>
		  					<c:if test="${list.brandId==null || list.brandId==''}">
		  					无
		  					</c:if>
		  					</td>
		  					<td>
		  					<c:if test="${list.carName==null || list.carName==''}">
		  					无
		  					</c:if>
		  					<c:if test="${list.carName!=null || list.carName!=''}">
		  					${list.carName}
		  					</c:if>
		  					</td>
		  					<td>
		  					<c:if test="${list.ishavepic==0 }">无图</c:if>
		  					<c:if test="${list.ishavepic==1 }">有图</c:if>
		  					<c:if test="${list.ishavepic==null }">无</c:if>
		  					</td>
		  					<td>
		  						<div  style="width:389px ; height:20px ;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${list.comment}</div>
		  						<a href="javascript:void(0)" onclick="divshow(${list.id})"> 详细</a>
									<div  sta="showdiv" id="oladiv_${list.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
									 ${list.comment}
									 <div style="text-align:right;">	
									<input type="button" value="关闭" onclick="nonediv(${list.id})">
									</div>
									</div>
		  					</td>
		  					<td>
		  					<c:if test="${list.isCream==0}">一般贴</c:if>
		  					<c:if test="${list.isCream==1}">精华帖</c:if>
		  					</td>	
		  					<td>
		  					<c:if test="${list.createtime==null || list.createtime==''}">无</c:if>
		  					<c:if test="${list.createtime!=null && list.createtime!=''}">${list.createtime}</c:if>
		  					</td>
		  					
							<td>
							<c:if test="${list.evaName==null || list.evaName=='' }">无</c:if>
							<c:if test="${list.evaName!=null || list.evaName!='' }">${list.evaName}</c:if>
							</td>
							<td>
							<a href="/review/addReviewTo?idd=${list.id }" >修改</a>
							|<a href="javascript:void(0)"  onclick="deleteReview(${list.id })">删除</a>
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
function divshow(id){
	var $divs=$("div[sta=showdiv]").hide();
	$("#oladiv_"+id).show();
}
function nonediv(id){
	$("#oladiv_"+id).hide();
}
function deleteReview(id){
	if(id!=null && id!=""){
		if(window.confirm('你确定要删除该记录！')){
			window.location.href="/review/deleteReview?id="+id;
		}
	}
}
</script>
</body>
</html>