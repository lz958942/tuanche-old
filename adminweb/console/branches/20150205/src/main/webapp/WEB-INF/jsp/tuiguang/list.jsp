<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>推广查询</title>
</head>
<body>
<form id="listForm" enctype="multipart/form-data" action="/promo/tolist" method="POST">
	<div id="man_zone">
		<div>批量导入
			<span class="add-on"><i class="icon-calendar"></i></span>
			导入日期：<input id="spendTime" type='text' name='spendTime' class="querytime span2" readonly="readonly" autocomplete="off" />
			<input type="file" name="file" id="file" />
			<input id="import" name="import" type="button" value="上传" class="btn" onclick="importSubmit()"/>
		</div>
		<div>
			<div class="b-con a-form">
	          	<div class="pd5">
	          		<label class="pr15">选择城市:
	          			<select id="cityId" name="cityId">
							<option value="">请选择</option>
							<c:forEach items="${citys }" var="citys">
								<option value="${citys.value.id }" ${citys.value.id==promotion.cityId?'selected':'' }>${citys.value.orderName}</option>
							</c:forEach>
						</select>
	          		</label>
	          		<label class="pr15">选择品牌:
	          			<select id="brandId" name="brandId">
							<option value="">请选择</option>
							<option value="-1" ${promotion.brandId==-1?'selected':'' }>其他</option>
							<option value="0" ${promotion.brandId==0?'selected':'' }>汽车团购</option>
							<c:forEach items="${brands }" var="brands">
								<option value="${brands.id }" ${brands.id==promotion.brandId?'selected':'' }>${brands.orderName} ${brands.name }</option>
							</c:forEach>
						</select>
	          		</label>
	          		<label class="pr15">
	          				推广日期:
	          				<div class="input-prepend">
	                      		<span class="add-on"><i class="icon-calendar"></i></span>
	                      		<input id="starttime" type='text' name='startTime' class="querytime span2" value="${promotion.startTime }" readonly="readonly" autocomplete="off" />
	                      	</div>-
	                    	<div class="input-prepend">
	                      		<span class="add-on"><i class="icon-calendar"></i></span>
	                      		<input type='text' id="endtime" name='endTime' class="querytime span2" value="${promotion.endTime }" readonly="readonly" autocomplete="off" />
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
						<td style="text-align: center;">城市</td>
						<td style="text-align: center;">城市编码</td>
						<td style="text-align: center;">品牌</td>
						<td style="text-align: center;">推广费用</td>
						<td style="text-align: center;">推广时间</td>
						<td style="text-align: center;">创建时间</td>
						<td style="text-align: center;">操作人</td>
						<td style="text-align: center;">操作</td>
					</tr>
					<c:if test="${promoList!=null&&promoList.size()>0 }">
						<c:forEach items="${promoList }" var="promoList">
							<tr>
								<td style="text-align: center;" width="50px">${promoList.id }</td>
								<td style="text-align: center;" width="150px">${func:getallCity(promoList.cityId)}</td>
								<td style="text-align: center;" width="150px">${promoList.cityCode }</td>
								<td style="text-align: center;" width="200px">
									<c:if test="${promoList.brandId==-1 }">其他</c:if>
									<c:if test="${promoList.brandId==0 }">汽车团购</c:if>
									<c:if test="${promoList.brandId>0 }">${func:getBrandName(promoList.brandId) }</c:if>
								</td>
								<td style="text-align: center;" width="200px">${promoList.money }</td>
								<td style="text-align: center;" width="200px">${promoList.spendTime }</td>
								<td style="text-align: center;" width="200px">${promoList.addTime }</td>
								<td style="text-align: center;" width="50px">${func:getEditName(promoList.adminId)}</td>
								<td style="text-align: center;" width="50px">
									<a href="/promo/preUpdate?id=${promoList.id }">修改</a>
									<a href="javascript:deleteOne(${promoList.id });" >删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${promoList==null||promoList.size()<=0 }">
						<tr>
							<td colspan="8">没有数据</td>
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
$('.querytime').live("click",function() {
	WdatePicker({
	isShowClear:true,
	qsEnabled:false,
	dateFmt:'yyyy-MM-dd'
	});
	});
	function clearSearch(){
		$("#id").val('');
		$("#adminId").val('');
		$("#brandId").val('');
		$("#cityId").val('');
		$("#minMoney").val('');
		$("#maxMoney").val('');
		$("#starttime").val('');
		$("#endtime").val('');
		$("#listForm").submit();
	}
	
	function importSubmit(){
		if($("#spendTime").val()==''){
			alert("请选择导入时间！");
			return false;
		}
		if($("#file").val()==''){
			alert("请选择上传的文件！");
			return false;
		}
		
		var str=$("#file").val();
		var pos=str.lastIndexOf(".");
		var lastname = str.substring(pos,str.length); 
		if(lastname!='.xlsx'&&lastname!='.xls'){
			alert("文件格式不支持，请上传 xlsx或者xls 格式的文件");
			return false;
		}
		$("#listForm").attr("action","/promo/excleImport");
		$("#listForm").attr("method","post");
		$("#listForm").submit();
	}
	
	function deleteOne(id){
		if(!confirm("此项为真实删除,请确认")){
			return;
		}
		$.ajax({
			type:'post',
			url:'/promo/delete',
			dataType:'text',
			data:{
				id:id,
			},
			success:function(data){
				if(data!=null){
					alert(data);
					$("#listForm").submit();
				}
			}
		})
	}
</script>
</body>
</html>