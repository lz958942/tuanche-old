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
<title>团车网品牌修改</title>
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

<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/specialSubject/specialSubject.js"></script>
<link type="text/css" rel="stylesheet" href="/css/common/smoothness/jquery-ui-1.8.14.custom.css"/>
<link type="text/css" rel="stylesheet" href="/css/common/tipsy.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript" src="/js/sites/sites.js"></script>
<script type="text/javascript">
	function subAdd(){
		var sta=true;
		$("[sta=p]").each(function(){
			if($(this).val()==null||$(this).val()==""){
				sta=false;
				alert($(this).attr("staName")+"不能为空！");
				return false;
				}
			}
			);
				if(sta){
				var ss=$("#statusName").val();
				if(ss=="no"){
					alert("品牌名称重复！");
					return;
				}
				$("#addForm").submit();
			}
	}
	function verificationmy(){
		
		var name=$("#name").val();
		
		$.ajax({
			   type: "POST",
			   url: "/json/validationName",
			   data: {
				   'name':name,
				  	'type':0
			   },
			   success: function(data){
				   var tmp=data.trim().split("_");
				   $("#statusName").val(tmp[0]);
				   $("#pinyin").val(tmp[1]);
				   if(tmp[0]=="no"){
					   $("#myqqqq").text("名称重复！");
				   }else{
					   $("#myqqqq").text("可以使用！");
				   }
			   }
			});
		
	}
</script>
</head>
<body>
	<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/sites/brand/newHome">品牌列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/sites/brand/toAddBrand">添加品牌</a></li>
					</ul>
			   </div>
			    <input type="hidden"  id="statusName" >
<form action="/sites/brand/newUpdateBrand" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="addForm">
	<div class="borderDiv">
	<input type="hidden" name="id" value="${brand.id}">
	<input type="hidden" name="oldImage" value="${brand.logo}">
	<c:if test="${staName!=null }">
	<input type="hidden" name="staName" value="${staName}">
	</c:if>
	<c:if test="${publicMark!=null }">
	<input type="hidden" name="publicMarkMy" value="${publicMark}">
	</c:if>
	<c:if test="${status!=null }">
	<input type="hidden" name="status" value="${status}">
	</c:if>
	</div>
	<div class="borderDiv">
		<table>
            <tr>
            	<td style="vertical-align:top">品牌名称：</td>
                <td><input sta="p" staName="品牌名称" maxlength="50" type="text"  id="name" name="name" value="${brand.name}" style="width: 280px;height: 25px" onchange="verificationmy()"/>
                <span  id="myqqqq" style="color:red;">* (请控制50字以内)</span>
                </td>
            </tr>
            <tr>
				<td>品牌拼音：</td>
				<td><input sta="p" staName="品牌拼音" type="text" maxlength="100" name="pinyin" id="pinyin" value="${brand.pinyin}" style="width: 280px;height: 25px"><span style="color:red;">* (请控制100字以内.拼音可能存在多音字，如有出入，请重新输入！)</span></td>
			</tr>
            <tr>
				<td>品牌logo：</td>
				<td>
				<c:choose>
          	         <c:when test="${empty brand}">
          	              <img  id="oneImage" src='/images/upload.jpg' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">*(支持.jpg)</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage" width="80px" height="80px" src='${brand.logosrc }' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">*(支持.jpg)</span>
          	            </c:otherwise>
          	     </c:choose>
          	     <input sta="p" staName="品牌logo" type="hidden" name="logo" id="logo"value="${brand.logo }">
          	       <div style="display: none">
					           <input id="listPicFile" name="listPicFile" type="file"  onchange="sitesUpload()"/>
				            </div>
          	    </td>
			</tr>
            <tr>
            	<td style="vertical-align:top">品牌国别:</td>
                <td>
                	<select  name="contry" id="contry" style="width: 280px;">
                	
                		<option value="" >请选择</option>
                		<option value="0" <c:if test="${brand.contry==0}">selected="selected"</c:if> >中国</option>
                		<option value="1" <c:if test="${brand.contry==1}">selected="selected"</c:if>>德国</option>
                		<option value="2" <c:if test="${brand.contry==2}">selected="selected"</c:if>>日本</option>
                		<option value="3" <c:if test="${brand.contry==3}">selected="selected"</c:if>>美国</option>
                		<option value="4" <c:if test="${brand.contry==4}">selected="selected"</c:if>>法国</option>
                		<option value="5" <c:if test="${brand.contry==5}">selected="selected"</c:if>>韩国</option>
                		<option value="6" <c:if test="${brand.contry==6}">selected="selected"</c:if>>瑞典</option>
                		<option value="7" <c:if test="${brand.contry==7}">selected="selected"</c:if>>英国</option>
                		<option value="8" <c:if test="${brand.contry==8}">selected="selected"</c:if>>意大利</option>
                		<option value="9" <c:if test="${brand.contry==9}">selected="selected"</c:if>>西班牙</option>
                	</select><span class="font" style="color: red"></span>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top">品牌系别:</td>
                <td>
                	<select  name="newSeries" id="series" style="width: 280px;">
                		<option value=""  selected="selected">请选系</option>
                		<option value="1" <c:if test="${brand.newSeries==1}">selected="selected"</c:if> >德系</option>
                		<option value="2" <c:if test="${brand.newSeries==2}">selected="selected"</c:if> >日韩系</option>
                		<option value="3" <c:if test="${brand.newSeries==3}">selected="selected"</c:if> >美系</option>
                		<option value="4" <c:if test="${brand.newSeries==4}">selected="selected"</c:if> >欧系</option>
                		<option value="7" <c:if test="${brand.newSeries==7}">selected="selected"</c:if> >国产</option>
                	</select><span class="font" style="color: red"></span>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top">生产厂家性质:</td>
                  <td>
                	<select  name="vender" id="vender" style="width: 280px;">
                		<option value="">请选择</option>
                		<option value="1" <c:if test="${brand.vender==1}">selected="selected"</c:if>>合资</option>
                		<option value="2" <c:if test="${brand.vender==2}">selected="selected"</c:if>>进口</option>
                		<option value="3" <c:if test="${brand.vender==3}">selected="selected"</c:if>>国产</option>
                	</select><span class="font" style="color: red"></span>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top" >品牌标识:</td>
                  <td>
                	<select  name="publicMark" style="width: 280px;" >
                		<option <c:if test="${brand.publicMark==null }">selected="selected"</c:if> value="">请选择</option>
                		<option <c:if test="${brand.publicMark==0 }">selected="selected"</c:if> value="0">全部</option>
                		<option <c:if test="${brand.publicMark==1 }">selected="selected"</c:if> value="1">新车</option>
                		<option <c:if test="${brand.publicMark==2 }">selected="selected"</c:if> value="2">二手车</option>
                	</select><span class="font" style="color: red"></span>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top">厂商名称:</td>
                <td><input   type="text" maxlength="20" id="cname" name="cname" value="${brand.cname}" style="width: 280px;height: 25px"><span style="color:red;"> (请控制20字以内)</span>
                </td>
            </tr>
        </table>
	</div>
	<div class="xtnext">
		<input type="button" id="newZixunBtn" value="保存" onclick="subAdd()"/>
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