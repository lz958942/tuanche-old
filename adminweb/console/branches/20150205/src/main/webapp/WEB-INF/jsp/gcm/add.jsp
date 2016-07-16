<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
 <script type="text/javascript" src="/js/jquery.validate.js"></script>
 <!-- ztree ue-->
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/gcm/gcm.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<style type="text/css">
	.uploadCss{
		width: auto/9;
		height: 150px;
	}
	td{
		padding-top: 10px;
	}
	.td_mapper{
		padding-left: 10px;
		padding-right: 10px;
	}
	.td_r{
		padding-left: 10px;
		padding-right: 10px;
	}
	
	.td_l_170{
		padding-left: 264px;
		padding-right: 10px;
	}
	.td_l_145{
		padding-left: 145px;
	}
  .borderDiv label.error {
		 padding-left: 16px; padding-bottom: 2px; font-weight: bold; color: #EA5200;
	}
	.divfr{
		float: left;
	}
	.uneditable-input {
	    padding: 4px 6px;
	    vertical-align: middle;
	}
	.buttons{  
		padding-left: 10px;
	}
	select{
		margin-bottom: 0px;
	}
	
</style>
<script type="text/javascript" src="/js/gcm/zixun.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/specialSubject/specialSubject.js"></script>
<link type="text/css" rel="stylesheet" href="/css/common/smoothness/jquery-ui-1.8.14.custom.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/tipsy.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript">
$().ready(function() {
	 $("#newZixunBtn").live("click",function(){
		 var le = $("#addressDesc").val().length;
		 	$("#gcmForm").validate();
			if( le > 230){
				 alert("地址描述超长，请控制在230字以内！");
				 return false;
			}
			$("#gcmForm").submit();
	 });
	});

</script>
<c:import url="/WEB-INF/jsp/common/import.jsp"></c:import>
</head>
<body>
<div style="display: none">
	<select id="brand_s">
		<c:forEach var="brand" items="${brands}">
			<option class="vender_${brand.value.vender}" value="${brand.key}">${brand.value.orderName}</option>
		</c:forEach>
	</select>
</div>
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
	<tr>
		<td>
			<div id="tabs" class="tabs">  
				<ul>
					<li ><a href="/gcm/toList">团车会列表</a></li>
					<li class="tabs_active"><a href="/gcm/toAdd">添加团车会</a></li>
				</ul>
		   </div>
		</td>
	</tr>
</table>
<div style="height: 1000px;">
	<form action="/gcm/<c:if test="${ gcm == null}">add</c:if><c:if test="${ gcm != null}">update</c:if>" method="post" id="gcmForm">
		<div class="borderDiv">
			<!-- 团车会id -->
			<input type="hidden" name="id" value="${gcm.id}"/>
			<span style="font-size:20px;">团购会信息</span>
			<table >
		        <tr >
			       	<td class="td_mapper">城市:</td>
					<td>
						<select  name="cityId" style="width: 214px">
						<option value="">全国</option>
						<c:forEach var="city" items="${citys}">
							<option value="${city.value.id}" ${gcm.cityId ==city.value.id?"selected":"" }>${city.value.orderName}</option>
						</c:forEach>
						</select>
					</td>
					<td class="td_l_170">标题:</td>
					<td style="text-align: right;">
						<input type="text" maxlength="10" class="required" name="title" value="${gcm.title }"/>
					</td>
		        </tr>
		        <tr>
		       		<td class="td_mapper">开始时间:</td>
		       		<td>
		       			<input id="onlineDate" class="required" class="dateCss" name="beginTimeStr" value="${gcm.beginTimeStr}" type="text" readonly="readonly" onfocus=" WdatePicker({maxDate:'#F{$dp.$D(\'endlineDate\')}',dateFmt:'yyyy-MM-dd H:mm:ss'}) " >
		       		</td>
		       		<td class="td_l_170">结束时间:</td>
		       		<td>
		       			<input id="endlineDate" class="required" class="dateCss" name="endTimeStr" value="${gcm.endTimeStr }" type="text" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'onlineDate\')}',dateFmt:'yyyy-MM-dd H:mm:ss'})" >
		       		</td>
		        </tr> 
		        <tr>
		       		<td class="td_mapper">活动地点:</td>
		       		<td>
		       			<input type="text"  maxlength="30" class="required"  name="address" value="${gcm.address }">
		       		</td>
		       		<td class="td_l_170">品牌报名标题:</td>
		       		<td>
		       			<input type="text"maxlength="30" name="url" value="${gcm.url }">
		       		</td>
		        </tr>
		        <tr>
		        	<td class="td_mapper">期数:</td>
		        	<td>
		        		<input type="text" maxlength="5"  class="required number" name="periodsNum" value="${gcm.periodsNum}">
		        	</td>
		        	<td class="td_l_170">隐藏:</td>
		        	<td>
		        		<input type="checkbox" name="isShow" value="2" <c:if test="${gcm.isShow == 2 }">checked</c:if>/>
						<span>参展4S店以及以下隐藏</span>		        	
		        	</td>
		        </tr>
		        <tr>
		        	<td class="td_mapper">头图:</td>
		        	<td>
		        		<img  id="oneImage" width="214px" src='<c:if test="${gcm.image !=null }">${picPath}${gcm.image}</c:if><c:if test="${gcm.image ==null }">/images/upload.jpg</c:if>' onclick="$('#onePicFile').trigger('click')"/>
		        		<input type="hidden" id="onePic" name="image" value="${gcm.image}"/>
		        	</td>
		        	<td class="td_l_170"></td>
		        	<td>
		        		    	
		        	</td>
		        </tr>
		    </table>
		    <br/> 
		    <span style="font-size:20px;">展会地址</span>
		    <div style="clear:both;"></div>
		    <div id="zbcj" class="divfr">
		    	<iframe src="http://css.100che.cn/baiduht.html" width="534px" height="300px" id="baiduMap" name="baiduMap"></iframe>
		    </div>
		    <div class="divfr">
			     <table>
			    	<tr>
				    	<td class="td_mapper">地点坐标：</td>
				    	<td>
				    		<input type="text" maxlength="30" name="addressNumber" value="${gcm.addressNumber}"/>
				    	</td>
			    	</tr>
			    	<tr>
				    	<td class="td_mapper">地址描述：</td>
				    	<td>
				    		<textarea id="addressDesc" rows="12"  cols="20"  name="addressDesc">${gcm.addressDesc}</textarea>
				    	</td>
			    	</tr>
			    </table>
		    </div>
		    <br/>
		    <div style="clear:both;margin-top: 10px;"></div>
		     <span style="font-size:20px;">品牌报名</span>
		    <div style="clear:both;">
		    <div  style="float: left;">
				<table id="carbrand_tem1">
					<tr><td class="td_r">热门车型</td></tr>
					<c:if test="${gcm.hotStyle ==null }">
						<tr>
							<td class="td_mapper">选择品牌：</td>
							<td>
								<select name="hotStyle[0].brandId">
									<option value="">请选择</option>
									<c:forEach var="brand" items="${brands}">
										<option value="${brand.key}">${brand.value.orderName}</option>
									</c:forEach>
								</select>
								排序：<input  type="text"  name="hotStyle[0].sort" style="width: 20px;">
							</td>
							<td class="buttons">
								<input class="btn btn-info"  type="button" value="添加" onclick="addBrand(1)"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${gcm.hotStyle !=null }">
						<c:forEach items="${gcm.hotStyle}" var="brand" varStatus="index">
							<tr>
								<td class="td_mapper">选择品牌：</td>
								<td>
									<input name="hotStyle[${index.count-1 }].id" type="hidden" value="${brand.id }"/>
									<select name="hotStyle[${index.count-1 }].brandId">
										<option value="">请选择</option>
										<c:forEach var="brandA" items="${brands}">
											<option value="${brandA.key}"${brand.brandId==brandA.key?"selected":""}>${brandA.value.orderName}</option>
										</c:forEach>
									</select>
									排序：<input  type="text"  name="hotStyle[${index.count-1}].sort" style="width: 20px;" value="${brand.sort }">
								</td>
								<td class="buttons">
									<c:if test="${index.count==1}">
										<input class="btn btn-info" type="button" value="添加" onclick="addBrand(1)"/>
									</c:if>
									<c:if test="${index.count!=1}">
										<input class="btn btn-info deleteGcmBrand" type="button" value="删除" gcmBrandId = "${brand.id }" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div style="float: left;">
				<table id="carbrand_tem3" style="margin-left: 163px">
					<tr><td class="td_r">国产车型</td></tr>
					<c:if test="${gcm.homemadeStyle ==null }">
						<tr>
							<td class="td_mapper">选择品牌：</td>
							<td>
								<select name="homemadeStyle[0].brandId">
									<option value="">请选择</option>
									<c:forEach var="brand" items="${brands}">
										<c:if test="${brand.value.vender == 3}">
											<option value="${brand.key}" ${search.brandId==brand.key?"selected":""}>${brand.value.orderName}</option>
										</c:if>
									</c:forEach>
								</select>
								排序：<input  type="text"  name="homemadeStyle[0].sort" style="width: 20px;">
							</td>
							<td  class="buttons">
								<input class="btn btn-info" type="button" value="添加" onclick="addBrand(3)"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${gcm.homemadeStyle !=null }">
						<c:forEach items="${gcm.homemadeStyle}" var="brand" varStatus="index">
							<tr>
								<td class="td_mapper">选择品牌：</td>
								<td>
									<input name="homemadeStyle[${index.count-1 }].id" type="hidden" value="${brand.id }"/>
									<select name="homemadeStyle[${index.count-1 }].brandId">
										<option value="">请选择</option>
										<c:forEach var="brandA" items="${brands}">
											<option value="${brandA.key}"${brand.brandId==brandA.key?"selected":""}>${brandA.value.orderName}</option>
										</c:forEach>
									</select>
									排序：<input  type="text" name="homemadeStyle[${index.count-1 }].sort" value="${brand.sort }" style="width: 20px;">
								</td>
								<td class="buttons">
									<c:if test="${index.count==1}">
										<input class="btn btn-info" type="button" value="添加" onclick="addBrand(3)"/>
									</c:if>
									<c:if test="${index.count!=1}">
										<input class="btn btn-info deleteGcmBrand" type="button" value="删除" gcmBrandId = "${brand.id }"/>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>  
			</div>
			</div>
			<div style="clear: both;">
			<div style="float: left;"> 
				<table id="carbrand_tem4"  >
					<tr><td class="td_r">合资车型</td></tr>
					<c:if  test="${gcm.jointStyle ==null }">
						<tr>
							<td class="td_mapper">选择品牌：</td>
							<td>
								<select name="jointStyle[0].brandId">
									<option value="">请选择</option>
									<c:forEach var="brand" items="${brands}">
										<c:if test="${brand.value.vender == 1}">
											<option value="${brand.key}" ${search.brandId==brand.key?"selected":""}>${brand.value.orderName}</option>
										</c:if>
									</c:forEach>
								</select>
								排序：<input type="text" name="jointStyle[0].sort" style="width: 20px;">
							</td>
							<td class="buttons">
								<input class="btn btn-info"  type="button" value="添加" onclick="addBrand(4)"/>
							</td>
						</tr>
					</c:if>
					<c:if  test="${gcm.jointStyle !=null }">
						<c:forEach items="${gcm.jointStyle}" var="brand" varStatus="index">
							<tr>
								<td class="td_mapper">选择品牌：</td>
								<td>
									<input name="jointStyle[${index.count-1 }].id" type="hidden" value="${brand.id }"/>
									<select name="jointStyle[${index.count-1 }].brandId">
										<option value="">请选择</option>
										<c:forEach var="brandA" items="${brands}">
											<option value="${brandA.key}"${brand.brandId==brandA.key?"selected":""}>${brandA.value.orderName}</option>
										</c:forEach>
									</select>
									排序：<input  type="text" name="jointStyle[${index.count-1 }].sort" value="${brand.sort }" style="width: 20px;">
								</td>
								<td class="buttons">
									<c:if test="${index.count==1}">
										<input class="btn btn-info"  type="button" value="添加" onclick="addBrand(4)"/>
									</c:if>
									<c:if test="${index.count!=1}">
										<input class="btn btn-info deleteGcmBrand"  type="button" value="删除" gcmBrandId = "${brand.id }"/>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>   
			</div>  
				<div  style="float: left;">
				<table id="carbrand_tem2" style="margin-left: 163px">
					<tr><td class="td_r">高端车型</td></tr>
					<c:if test="${gcm.highStyle ==null }">
						<tr>
							<td class="td_mapper">选择品牌：</td>
							<td>
								<select name="highStyle[0].brandId">
									<option value="">请选择</option>
									<c:forEach var="brand" items="${brands}">
										<c:if test="${brand.value.vender == 2}">
											<option value="${brand.key}" ${search.brandId==brand.key?"selected":""}>${brand.value.orderName}</option>
										</c:if>
									</c:forEach>
								</select>
								排序：<input  type="text"  name="highStyle[0].sort" style="width: 20px;">
							</td>
							<td class="buttons">
								<input class="btn btn-info" type="button" value="添加" onclick="addBrand(2)"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${gcm.highStyle !=null }">
						<c:forEach items="${gcm.highStyle}" var="brand" varStatus="index">
							<tr>
								<td class="td_mapper">选择品牌：</td>
								<td>
									<input name="highStyle[${index.count-1 }].id" type="hidden" value="${brand.id }"/>
									<select name="highStyle[${index.count-1 }].brandId">
										<option value="">请选择</option>
										<c:forEach var="brandA" items="${brands}">
											<option value="${brandA.key}"${brand.brandId==brandA.key?"selected":""}>${brandA.value.orderName}</option>
										</c:forEach>
									</select>
									排序：<input  type="text"  name="highStyle[${index.count-1 }].sort" value="${brand.sort }" style="width: 20px;">
								</td>
								<td class="buttons">
									<c:if test="${index.count==1}">
										<input  class="btn btn-info" type="button" value="添加" onclick="addBrand(2)"/>
									</c:if>
									<c:if test="${index.count!=1}">
										<input  class="btn btn-info deleteGcmBrand" type="button" value="删除" gcmBrandId = "${brand.id }" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>   
			</div> 
			<div style="clear: both;"></div>	
			<div >
				<br/>
				<span style="font-size:20px;">团车会新闻</span>
				<table id="zixun">
					<c:if test="${gcm.carNews == null }">
				        <tr class="zixunC">
					       	<td class="td_mapper">标题:</td>
					       	<td><input type="text" name="carNews[0].title"/></td>
					       	<td class="td_mapper">链接:</td>
					       	<td><input type="text" name="carNews[0].url"/></td>
					    	<td class="td_mapper">资讯图片：</td>
					    	<td>
						    	<img  id="zixunImage" width="160px" src="/images/upload.jpg" onclick="$('#zixunPicFile').trigger('click')"/>
				        		<input type="hidden" id="zixunPic" name="carNews[0].image"/>
					    	</td>
					    	<td class="buttons"><input class="btn btn-info" type="button" value="添加" onclick="addZixun()"/></td>
					    </tr>
				    </c:if>
				    <c:if test="${gcm.carNews != null }">
				    	<c:forEach items="${gcm.carNews}" var="zixun" varStatus="index">
				    		<tr class="zixunC">
						       	<td class="td_mapper">标题:</td>
						       	<td>
						       		<input type="hidden" name="carNews[${index.count-1 }].id" value="${zixun.id }"/>
						       		<input type="text" name="carNews[${index.count-1 }].title" value="${zixun.title }"/>
						       	</td>
						       	<td class="td_mapper ">链接:</td>
						       	<td>
						       		<input type="text" name="carNews[${index.count-1 }].url" value="${zixun.url}"/>
						       	</td>
							  	<c:if test="${index.count == 1}">
							    	<td class="td_mapper">资讯图片：</td>
							    	<td>
								    	<img  id="zixunImage" width="160px" src="<c:if test="${zixun.image!=null }">${picPath}${zixun.image}</c:if><c:if test="${zixun.image ==null }">/images/upload.jpg</c:if>" onclick="$('#zixunPicFile').trigger('click')"/>
						        		<input type="hidden" id="zixunPic" name="carNews[${index.count-1 }].image"/>
							    	</td>
							   	</c:if>
							   	<td class="buttons">
						       		<c:choose>
						       			<c:when test="${index.count == 1}">
						       				<input class="btn btn-info" type="button" value="添加" onclick="addZixun()"/>
						       			</c:when>
						       			<c:otherwise>
						       				<input class="btn btn-info deleteContent" type="button" value="删除" gcmContentId="${zixun.id }"/>
						       			</c:otherwise>
						       		</c:choose>
								</td>
						    </tr>
				    	</c:forEach>
				    </c:if>
			    </table>
			</div>
			<div >
				<br/>
				<span style="font-size:20px;">精彩回顾</span>
				<table id="carReview">
				 	<c:if test="${gcm.carReview == null }">
						<tr class="carReviewC">
							<td class="td_mapper">标题:</td>
							<td><input type="text" name="carReview[0].title"/></td>
							<td class="td_mapper">链接:</td>
							<td><input type="text" name="carReview[0].url"/></td>
					    	<td class="td_mapper">车展图片：</td>
					    	<td>
						    	<img  id="cheZImg_0" width="160px" src="/images/upload.jpg" onclick="$('#cheZFile_0').trigger('click')"/>
				        		<input type="hidden" id="cheZPic_0" name="carReview[0].image"/>
					    	</td>
					    	<td class="buttons"><input class="btn btn-info " type="button" value="添加" onclick="addCarReview()"/></td>
					    </tr>
					</c:if>
				    <c:if test="${gcm.carReview != null }">
				    	<c:forEach items="${gcm.carReview}" var="carReview" varStatus="index">
				    		<tr class="carReviewC">
								<td class="td_mapper"><input type="hidden" name="carReview[${index.count-1 }].id" value="${carReview.id}">标题：</td>
								<td><input type="text" name="carReview[${index.count-1 }].title" value="${carReview.title }"/></td>
								<td class="td_mapper">链接:</td>
								<td><input type="text" name="carReview[${index.count-1 }].url" value="${carReview.url }"/></td>
						    	<td class="td_mapper">车展图片：</td>
						    	<td>
							    	<img  id="cheZImg_${index.count-1 }" width="160px" src="<c:if test="${carReview.image!=null }">${picPath}${carReview.image}</c:if><c:if test="${carReview.image ==null }">/images/upload.jpg</c:if>" onclick="$('#cheZFile_${index.count-1 }').trigger('click')"/>
					        		<input type="hidden" id="cheZPic_${index.count-1 }" name="carReview[${index.count-1 }].image"/>
						    	</td>
						    	<c:if test="${index.count == 1}">
									<td class="buttons"><input class="btn btn-info" type="button" value="添加" onclick="addCarReview()"/></td>
								</c:if>
								<c:if test="${index.count != 1}">
									<td class="buttons"><input class="btn btn-info deleteContent" type="button" value="删除" gcmContentId="${carReview.id }"/></td>
								</c:if>
						    </tr>
				   		</c:forEach>
				    </c:if>
				</table>
			</div>
			<div id="dian">
				<br/>
				<span style="font-size:20px;">部分参展商</span>
				<table id="dianTable">
					<c:if test="${gcm.showShops == null}">
						<tr class="dianTr">
							<td>公司名称：</td>
							<td class="td_mapper">
								<input type="text" name=""/>
								<input class="btn btn-info dian_query" type="button" dian_count="0" value="查询"/>
								<select name="showShops[0].dianId" class="dianSelect">
									<option value="-1">请选择</option>
								</select>&nbsp;排序:<input type="text" style="width: 20px;" name="showShops[0].sort" />
							</td>
							<td ><input class="btn btn-info" type="button" id="addDian" value="添加"/></td>
						</tr>
					</c:if>
					<c:if test="${gcm.showShops != null}">
						<c:forEach items="${gcm.showShops}" var="showShops" varStatus="index">
							<tr class="dianTr">
								<td>公司名称：</td>
								<td class="td_mapper">
									<input type="text" name=""/>
									<input class="btn btn-info dian_query" type="button" dian_count="${index.count-1  }" value="查询"/>
									<input type="hidden" name="showShops[${index.count-1 }].id" value="${showShops.id }"/>
									<select name="showShops[${index.count-1 }].dianId" class="dianSelect">
										<option value="${showShops.dianId }">${showShops.dianName }</option>
									</select>&nbsp;排序:<input type="text" style="width: 20px;" name="showShops[${index.count-1 }].sort" value="${showShops.sort }"/>
								</td>
								<td>
									<c:if test="${ index.count == 1}">
										<input class="btn btn-info" type="button" id="addDian" value="添加"/>
									</c:if>
									<c:if test="${ index.count != 1}">
										<input class="btn btn-info deleteContent" type="button" value="删除" gcmContentId="${showShops.id }"/>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div id="oldCarShow">
				<br/>
				<span style="font-size:20px;">往期车展</span>
				<table>
					<c:if test="${gcm.oldCarShow == null}">
						<tr class="oldCarShow">
					       	<td class="td_mapper">标题:</td>
					       	<td>
					       		<input type="text" name="oldCarShow[0].title"/>
					       	</td>
					       	<td class="td_mapper">链接:</td>
					       	<td>
					       		<input type="text" name="oldCarShow[0].url"/>
					       	</td>
					       	<td class="buttons">
								<input type="button" class="btn btn-info"  value="添加" onclick="addcarGoShow(1)"/>
							</td>
					    </tr>
					</c:if>
					<c:if test="${gcm.oldCarShow != null}">
						<c:forEach items="${gcm.oldCarShow}" var="oldCarShow" varStatus="index">
							<tr class="oldCarShow">
						       	<td class="td_mapper">标题:</td>
						       	<td>
						       		<input type="hidden" name="oldCarShow[${index.count-1 }].id" value="${oldCarShow.id }"/>
						       		<input type="text" name="oldCarShow[${index.count-1 }].title" value="${oldCarShow.title }"/>
						       	</td>
						       	<td class="td_mapper">链接:</td>
						       	<td>
						       		<input type="text" name="oldCarShow[${index.count-1 }].url" value="${oldCarShow.url }"/>
						       	</td>
						       	<td class="buttons">
						       		<c:if test="${ index.count == 1}">
									<input class="btn btn-info"  type="button" value="添加" onclick="addcarGoShow(1)"/>
									</c:if>
									<c:if test="${ index.count != 1}">
									<input class="btn btn-info deleteContent" type="button" value="删除"  gcmContentId="${oldCarShow.id }"/>
									</c:if>
								</td>
						    </tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div id="countyGoShow">
				<br/>
				<span style="font-size:20px;">县市巡展</span>
				<table>
					<c:if test="${gcm.countyGoShow == null}">
						<tr class="countyGoShow">
					       	<td class="td_mapper">标题:</td>
					       	<td>
					       		<input type="text" name="countyGoShow[0].title"/>
					       	</td>
					       	<td class="td_mapper">链接:</td>
					       	<td>
					       		<input type="text" name="countyGoShow[0].url"/>
					       	</td>
					       	<td class="buttons">
								<input type="button" class="btn btn-info"  value="添加" onclick="addcarGoShow(2)"/>
							</td>
					    </tr>
					</c:if>
					<c:if test="${gcm.countyGoShow != null}">
						<c:forEach items="${gcm.countyGoShow}" var="countyGoShow" varStatus="index">
							<tr class="countyGoShow">
						       	<td class="td_mapper">标题:</td>
						       	<td>
						       		<input type="hidden" name="countyGoShow[${index.count-1 }].id" value="${countyGoShow.id }"/>
						       		<input type="text" name="countyGoShow[${index.count-1 }].title" value="${countyGoShow.title }"/>
						       	</td>
						       	<td class="td_mapper">链接:</td>
						       	<td>
						       		<input type="text" name="countyGoShow[${index.count-1 }].url" value="${countyGoShow.url }"/>
						       	</td>
						       	<td class="buttons">
						       		<c:if test="${ index.count == 1}">
									<input class="btn btn-info" type="button" value="添加" onclick="addcarGoShow(2)"/>
									</c:if>
									<c:if test="${ index.count != 1}">
									<input class="btn btn-info deleteContent" type="button" value="删除"  gcmContentId="${countyGoShow.id }"/>
									</c:if>
								</td>
						    </tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div> 
		</div>     
		<div style="display: none;" id="hidFileDiv">
			<input id="onePicFile" name="onePicFile" type="file"  onchange="upload('onePicFile','onePic','oneImage','1')"/>
			<input id="zixunPicFile" name="zixunPicFile" type="file"  onchange="upload('zixunPicFile','zixunPic','zixunImage','1')"/>
			<c:forEach items="${gcm.carReview}" var="carReview" varStatus="index">
				<input id="cheZFile_${index.count-1 }" name="cheZFile_${index.count-1 }" type="file"  onchange="upload('cheZFile_${index.count-1 }','cheZPic_${index.count-1 }','cheZImg_${index.count-1 }','1')"/>
			</c:forEach>
			<c:if test="${gcm.carReview==null}">
				<input id="cheZFile_0" name="cheZFile_0" type="file"  onchange="upload('cheZFile_0','cheZPic_0','cheZImg_0','1')"/>
			</c:if>
		</div>
		<div class="xtnext" style="clear: both;margin-top: 15px">
			<input type="button" class="btn btn-info" id="newZixunBtn" value="保存"/>
		</div>   
	</form>
</div>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
<div id="menuContentselectCarStyle" style="display:none; position: absolute;" >
	<ul id="treeselectCarStyle" class="ztree"  ></ul>
</div>
</body>
</html>