<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>团车网品牌添加</title>
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
			});
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
<form action="/sites/brand/addNewBrand" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="addForm">
	<div class="borderDiv">
		
	</div>
	<div class="borderDiv">
	
		<center><table>
            <tr>
            	<td style="vertical-align:top">品牌名称：</td>
                <td><input sta="p" staName="品牌名称" maxlength="50" style="width: 280px;height: 25px" type="text"  id="name" name="name" onchange="verificationmy()"/><span id="myqqqq" style="color:red;">* (请控制50字以内)</span>
                
                </td>
            <tr>
				<td>品牌拼音：</td>
				<td><input sta="p" staName="品牌拼音" type="text" style="width: 280px;height: 25px" maxlength="100" name="pinyin" id="pinyin"><span style="color:red;">* (请控制100字以内.拼音可能存在多音字，如有出入，请重新输入！)</span></td>
			</tr>
            <tr>
				<td>品牌logo：</td>
				<td>
				<c:choose>
          	         <c:when test="${empty brand}">
          	            <img  id="oneImage" style="width: 100px;height: 60px" src='/images/upload.jpg' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">*(支持.jpg)</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage" style="width: 80px;height: 80px"  src='${brand.logosrc }' onclick="$('#listPicFile').trigger('click')"/>
          	            </c:otherwise>
          	     </c:choose>
          	     <input sta="p" staName="品牌logo" type="hidden" name="logo" id="logo">
          	       <div style="display: none">
					           <input id="listPicFile" name="listPicFile" type="file"  onchange="sitesUpload()"/>
				            </div>
          	    </td>
			</tr>
			 <tr>
				<td>品牌列表图：</td>
				<td>
				<c:choose>
          	         <c:when test="${empty brand}">
          	            <img  id="oneImage2" style="width: 100px;height: 60px" src='/images/upload.jpg' onclick="$('#listPicFileBrand').trigger('click')"/><span class="font" style="color: red">(支持.jpg)</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage2" style="width: 80px;height: 80px"  src='${brand.logosrc }' onclick="$('#listPicFileBrand').trigger('click')"/>
          	            </c:otherwise>
          	     </c:choose>
          	     <input   type="hidden" name="brndPic" id="brndPic">
          	       <div style="display: none">
					           <input id="listPicFileBrand" name="listPicFileBrand" type="file"  onchange="brandPic()"/>
				            </div>
          	    </td>
			</tr>
            <tr>
            	<td style="vertical-align:top">品牌国别:</td>
                <td>
                	<select  name="contry" id="contry"  style="width: 280px;">
                		<option value="">请选择</option>
                		<option value="0">中国</option>
                		<option value="1">德国</option>
                		<option value="2">日本</option>
                		<option value="3">美国</option>
                		<option value="4">法国</option>
                		<option value="5">韩国</option>
                		<option value="6">瑞典</option>
                		<option value="7">英国</option>
                		<option value="8">意大利</option>
                		<option value="9">西班牙</option>
                	</select>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top">品牌系别:</td>
                <td>
                	<select  name="newSeries" id="series" style="width: 280px;">
                		<option value="">请选系</option>
                		<option value="1">德系</option>
                		<option value="2">日韩系</option>
                		<option value="3">美系</option>
                		<option value="4">欧系</option>
                		<option value="7">国产</option>
                	</select>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top" >生产厂家性质:</td>
                  <td>
                	<select  name="vender" id="vender" style="width: 280px;" >
                		<option value="">请选择</option>
                		<option value="1">合资</option>
                		<option value="2">进口</option>
                		<option value="3">国产</option>
                	</select>
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top" >品牌标识:</td>
                  <td>
                	<select  name="publicMark" style="width: 280px;" >
                		<option value="">请选择</option>
                		<option value="0">全部</option>
                		<option value="1">新车</option>
                		<option value="2">二手车</option>
                	</select>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top">厂商名称:</td>
                <td><input  type="text" style="width: 280px;height: 25px" maxlength="20" id="cname" name="cname" ><span style="color:red;"> (请控制20字以内)</span></td>
            </tr>
        </table>
        </center>
	</div>
	<center>
	<div class="xtnext">
		<input type="button" id="newZixunBtn" value="保存" onclick="subAdd()"/>
	</div>
	</center>
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