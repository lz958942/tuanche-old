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

 <!-- ztree ue-->
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ue/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
<script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
<!-- <script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script> -->
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<style type="text/css">
	.uploadCss{
		width: auto/9;
		height: 150px;
	}
</style>
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/specialSubject/specialSubject.js"></script>
<link type="text/css" rel="stylesheet" href="/css/common/smoothness/jquery-ui-1.8.14.custom.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/tipsy.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
</head>
<body>
	<div id="tabs" class="tabs">  
	<ul>
		<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/specialSubject/home">专题列表</a></li>
		<li class="tabs_active" ><a href="/specialSubject/toAdd">新建专题</a></li>
	</ul>
</div>
<form action="/specialSubject/addSpecialSubject" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="newZixunPropertiesForm">
	<div class="borderDiv">
		<table>
			<tr class="ti">
				<td><input type="hidden" name="id" value="${specialSubject.id }"/></td>
				<td><input type="hidden" name="pageNo" value="${pageNo}"/></td>
				<td><input id="ztType" type="hidden"  value="${specialSubject.ztType }"/></td>
				<%-- <td><span style="width:100px;padding-right:16px; vertical-align:top">选择城市：  </span>
				<input readonly="readonly" id="city" onclick="showMenu(); return false;" id="city"  style="width:93px" value="${func:getallCity(specialSubject.cityId==null?-1:specialSubject.cityId)}"/>
				<input  id="cityId" name="cityId" type="hidden" value="${specialSubject.cityId==null?-1:specialSubject.cityId}"/>
				</td> --%>
				<td width="100px">选择模板:</td>
					<td class="ti">
						<select name="templateId">
							<c:forEach items="${tempList }" var="template">
								<option value="${template.id }"<c:if test="${specialSubject.templateId==template.id }">selected</c:if>>${template.tpName }</option>
							</c:forEach>
						</select>
					</td>
				<td width="246px" style="text-align: right;">选择分类：</td>
					<td>
						<select name="ztType">
								<c:if test="${specialSubject.ztType=='1'}">
									<option value="1" selected="selected">
										买车
									</option>
								</c:if>
								<c:if test="${specialSubject.ztType=='2'}">
									<option value="2" selected="selected">
										惠车
									</option>
								</c:if>
								<c:if test="${empty specialSubject.ztType }">
									<option value="1" selected="selected">
										买车
									</option>
								</c:if>
						</select>
					</td>
				
			</tr>
		</table>
		<table>
			<tr>
					<td style="width: 100px">关联车型：</td>
					<c:if test="${specialSubject.carStyleId!=null&&specialSubject.carStyleId!=''}">
					    	<input id="styleId" name="carStyleId" type="hidden" value="${specialSubject.brandId}-${specialSubject.carStyleId},"/>
					        <c:set  var="valueStr" value="${specialSubject.brandId}-${specialSubject.carStyleId}" scope="request" ></c:set>
					</c:if>
					<c:if test="${specialSubject.carStyleId==null||specialSubject.carStyleId==''}">
					
					    	<input id="styleId" name="carStyleId" type="hidden" value="${specialSubject.brandId},"/>
					    	 <c:set  var="valueStr" value="${specialSubject.brandId}" scope="request" ></c:set>
					    
					</c:if>
					<td>
					<input id="selectCarStyle" class="dateCss" type="text" value="${func:getStyleInfo(valueStr)}" readonly="readonly" onclick="selectCarStyleshow()">
					<input id="brindId" name="brandId" type="hidden" value="${specialSubject.brandId},"/>
					</td>
            	<td>&nbsp;</td>
            	<td width="247px" style="text-align: right;">上线时间：</td>
                <td>
                	<input id="onlineDate" class="dateCss" type="text" value="${specialSubject.onlineDate }" readonly="readonly" onfocus=" WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'}) " name="onlineDate">
                </td>
			</tr>
		</table>
	</div>
	<div class="borderDiv">
		<table>
            <tr>
            	<td style="width: 100px">专题名称：</td>
                <td><input maxlength="100" type="text"  id="spName" name="spName" value="${specialSubject.spName }" style="width:675px;font-size: 12px;" />
                <span style="color:red;">* (请控制100字以内)</span>
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">专题导语:</td>
                <td><textarea maxlength="500" cols="107"  rows="2" id="spAbstract" name="spAbstract" style="width:675px;font-size: 12px;">${specialSubject.spAbstract }</textarea><span class="font" style="color: red">*  (请控制500字以内)</span>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top">专题描述:</td>
                <td><textarea maxlength="200" cols="50"  rows="2" id="spDesc" name="spDesc" style="width:675px;font-size: 12px;">${specialSubject.spDesc }</textarea>
                  <span class="font">（请控制200字以内）</span>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top">往期专题</br>回顾内容:</td>
                <td><textarea maxlength="500" cols="50"  rows="6" id="content" name="content" style="width:675px;font-size: 12px;">${specialSubject.content }</textarea>
                 <span class="font">（请控制500字以内）</span>
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">关键词：</td>
                <td>
                	<input maxlength="50" type="text"  id="keywords" name="keywords" value="${specialSubject.keywords }" style="width:675px;font-size: 12px;" />
                 </td>
            </tr>
            <tr id="zixun">
            	<td style="vertical-align:top">关联资讯：</td>
                <td>
                	<input onkeyup="this.value=this.value.replace(/[^\d|,]/g,'')"  maxlength="50" type="text" id="zixunIds" name="zixunIds" value="${specialSubject.zixunIds }" style="width:675px;"  />
                 </td>
            </tr>
              <tr >
            	<td>头图标语：</td>
                <td>
                	<input maxlength="20" type="text"  id="banner" name="banner" value="${spOnePic.banner }" style="width:675px;font-size: 12px;" />
                 	<span class="font">（请控制20字以内）</span>
                 </td>
            </tr>
            <tr>
            				<td>
          	    	                               上传头图：
          	                </td>
          	                <td>
          	                <c:choose>
          	                	<c:when test="${empty spOnePic}">
          	                		  <img  id="oneImage" src='/images/upload.jpg' onclick="$('#onePicFile').trigger('click')"/><span class="font" style="color: red">*</span>
          	                	</c:when>
          	                	<c:otherwise>
          	                		  <img  id="oneImage" class="uploadCss" src='${spOnePic.listPics }' onclick="$('#onePicFile').trigger('click')"/>
          	                	</c:otherwise>                                                                                                   	                
          	                </c:choose>
          	                    <input type="hidden" id="onePic" name="onePic"  value="${spOnePic.listPics }" />
          	                    <input type="hidden"  name="onePicId"  value="${spOnePic.id }" />
          	                </td>
          	                <td>
                          	<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
					          <div style="display: none">
					           <input id="onePicFile" name="onePicFile" type="file"  onchange="upload('onePicFile','onePic','oneImage','1')"/>
				             </div>
			               </td>
            </tr>
            
           
            <c:if test="${specialSubject.ztType ne '2'}">
            <table id="picList">
            	<c:choose>
            		<c:when test="${not empty spPicsList}">
            			<tr id="pics_t">
			            	<td style="vertical-align:top" width="100px">标题：</td>
			                <td>
			                	<input maxlength="50" type="text"  id="picTitle" name="picTitle" value="${picTitle }" style="width:675px;font-size: 12px;" />
			                	  <p class="font">（请控制50字以内）</p>
			                 </td>
           				 </tr>
           				 <c:forEach items="${spPicsList }" var="picsList" varStatus="var">
				         <tr id="pics_${var.index}">
					         <td>
					          	            上传图片：
					         </td>
					         <td>
					            <img id="listImage_${var.index }" class="uploadCss" src='${picsList.listPics }'  onclick="$('#listPicFile_${var.index }').trigger('click')"/>
				          	    <input type="hidden" id="listPic_${var.index }" name="listPic"  value="${picsList.listPics }" />
				          	    <input type="hidden" name="picListId" value="${picsList.id }" />
				          	     <c:choose>
									<c:when test="${not empty spPicsList}">
										<input type="button" value="删除" onclick="deletePics('#pics_${var.index}','${picsList.id }')"/>
									</c:when>
									<c:otherwise>
										<input type="button" value="删除" onclick="deletePics('#pics_0')"/>
									</c:otherwise>          
          						</c:choose>
					          
					         </td>
					         <td>
				                <span style="width:100%;padding-right:16px;padding-left:16px;vertical-align:top"><!--选择列表图片：--></span>
								<div style="display: none">
								<input id="listPicFile_${var.index }" name="listPicFile_${var.index }" type="file"  onchange="uploadPics('listPicFile_${var.index }','listPic_${var.index }','listImage_${var.index }','1')"/>
								</div>
							</td>
					    </tr>
            			</c:forEach>
            		</c:when>
            		<c:otherwise>
            			<tr id="pics_t">
			            	<td style="vertical-align:top" width="100px">标题：</td>
			                <td>
			                	<input maxlength="50" type="text"  id="picTitle" name="picTitle" value="" style="width:675px;font-size: 12px;" />
			                	  <p class="font">（请控制50字以内）</p>
			                 </td>
           				 </tr>
				         <tr id="pics_0">
					         <td>
					          	            上传图片：
					         </td>
					         <td>
					            <img  id="listImage_0" src='/images/upload.jpg'  onclick="$('#listPicFile_0').trigger('click')"/><span class="font" style="color: red">*</span>
				          	    <input type="hidden" id="listPic_0" name="listPic"  value="" />
          						<input type="button" value="删除" onclick="deletePics('#pics_0')"/>
					         </td>
					         <td>
				                <span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"><!--选择列表图片：--></span>
								<div style="display: none">
								<input id="listPicFile_0" name="listPicFile_0" type="file"  onchange="uploadPics('listPicFile_0','listPic_0','listImage_0','1')"/>
								</div>
							</td>
					    </tr>
            		</c:otherwise>
            	</c:choose>
            </table>
            </table>
          <c:choose>
				<c:when test="${not empty spPicsList}">
					<input type="button" onclick="updatePicBtn('${fn:length(spPicsList)}')" value="增加一行"/>	 
				</c:when>
				<c:otherwise>
					  <input type="button" id="addpics" value="增加一行"/>	
				</c:otherwise>          
          </c:choose>
          	<c:choose>
            			<c:when test="${not empty spConList}">
            				<c:forEach items="${spConList}" var="specialContent" varStatus="var">
            				<table id="biaoti_${var.index }">
            		   <tr>
            		   			<input type="hidden" name="titlesId" value="${specialContent.id }" />
			            		<td style="white-space:nowrap" width="100px">标题名称：</td>
				                <td>
				                	<input  name="stTitle" id="stTitle" style="width:310px;font-size: 12px;" type="text" value="${specialContent.stTitles }" maxlength="25"/>
				                	  <p style="width: 150px" class="font">（请控制25字以内）</p>
				                </td> 
				                <td style="white-space:nowrap" width="85px" align="right">标题url：</td>
				                <td><input  name="titleUrls" id="titleUrls" style="width:270px;font-size: 12px;" type="text" value="${specialContent.titleUrl }" maxlength="25"/>
				                  <p style="width: 150px" class="font">（请控制25字以内）</p>
				                 </td>
				        </tr>
				        <tr>
				                 <td style="vertical-align: middle;white-space: nowrap;">标题内容：</td>
			            	     <td>
				                   <textarea maxlength="500" cols="50"  rows="3" id="stContent" name="stContent" style="width:310px;font-size: 12px;">${specialContent.stContents }</textarea>
				                     <p style="width: 150px" class="font">（请控制500字以内）</p>
				                 </td>
				                 <td style="white-space:nowrap" width="85px" align="right">标题排序：</td>
			            	     <td>
				                   <input  name="sorts" id="sorts" style="width:270px;font-size: 12px;" type="text" value="${specialContent.sort }" maxlength="25"/>
				                    <p style="width: 150px" class="font">（数值,由高到低排序）</p>
				                 </td>
				                 <c:choose>
				                 	<c:when test="${not empty spConList}">
				                 		<td><input type="button" value="删除" onclick="deleteButton('#biaoti_${var.index }','${specialContent.id }')"/></td>
				                 	</c:when>
				                 	<c:otherwise>
				                 		<td><input type="button" value="删除" onclick="deleteButton('#biaoti_0')"/></td>
				                 	</c:otherwise>
				                 </c:choose>
			            </tr>
			               </table>
            	</c:forEach>
            			</c:when>
            			<c:otherwise>
            			<table id="biaoti_0">
            				<tr>
			            		<td style="white-space:nowrap" width="100px">标题名称：</td>
				                <td>
				                	<input name="stTitle" id="stTitle" style="width:310px;font-size: 12px;" type="text" value="" maxlength="25"/>
				                	  <p style="width: 150px" class="font">（请控制25字以内）</p>
				                </td> 
				                <td style="white-space:nowrap" width="85px" align="right">标题url：</td>
				                <td><input  name="titleUrls" id="titleUrls" style="width:270px;font-size: 12px;" type="text" value="" maxlength="25"/>
				                  <p style="width: 150px" class="font">（请控制25字以内）</p>
				                 </td>
				      		 </tr>
				        	 <tr>
				                 <td style="vertical-align: middle;white-space: nowrap;">标题内容：</td>
			            	     <td>
				                   <textarea maxlength="500" cols="50"  rows="3" id="stContent" name="stContent" style="width:310px;font-size: 12px;"></textarea>
				                     <p style="width: 150px" class="font">（请控制500字以内）</p>
				                 </td>
				                 <td style="white-space:nowrap" width="85px" align="right">标题排序：</td>
			            	     <td>
				                   <input  name="sorts" id="sorts" style="width:270px;font-size: 12px;" type="text" value="" maxlength="25"/>
				                   <p style="width: 150px" class="font">（数值,由高到低排序）</p>
				                 </td>
				                 <td><input type="button" value="删除" onclick="deleteButton('#biaoti_0')"/></td>
			            	</tr>
			            	</table>
			        </c:otherwise>
            		</c:choose>
                     <c:choose>
				         <c:when test="${not empty spConList}">
				          	<td><input type="button" id="addbutton1" onclick="updateAddTitle('${fn:length(spConList)}')" value="增加一行"/></td>	
				         </c:when>
				         <c:otherwise>
				              <td><input type="button" id="addbutton0" value="增加一行"/></td>	
				         </c:otherwise>
				         </c:choose>
           <!--  <table id="biaoti_0"> -->
            		
            </c:if>
            
            <table id="msg">
            <tr>
            	<td>&nbsp;</td>
            	<td>
            		<span id="errMsg" style="color:red;"></span>
            	</td>
            </tr>
        </table>
	</div>
	<div class="xtnext">
		<input type="button" id="newZixunBtn" value="保存" onclick="sub()"/>
	</div>
</form>

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