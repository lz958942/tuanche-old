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
<title>团车网修改车系</title>
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
		if($("#enname").val().length>25){
			alert("拼音过长,请控制在25字以内!");
			return;
			}
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
					alert("车型名称重复！");
					return;
				}
				$("#addForm").submit();
			}
	}
	function uploadImgStyle(){
		
		var imagesize=0;
		var ss=$("#listPicFile").each(function(){
			if(this.files[0].size>2000000){
				
				imagesize=this.files[0].size
				return;
			}
			
		});
		if(imagesize>2000000){
			alert("图片过大，超出2M！");
			return;
		}
		if(!checkImg()){
			return;
		}
		
		$.ajaxFileUpload({
			url:"/common/uploadImgStyle",
			secureuri:false,
			async:true,
			fileElementId:'listPicFile',
			dataType: 'text',				
			success: function (data){
				if(isNaN(data)){//成功
					$("#logo").val(data.trim());
					$("#oneImage").attr("src","/pic_tmp/car"+data.trim()+"_s.jpg"+"?"+Math.random());
					
					setTimeout(function(){
					} ,1000)
					alert("上传成功");
				}else{
					alert("上传失败");
				}
			},
			error: function (data, status, e){
				alert(e);
			},
		});
	}
	function verificationmy(){
		var name=$("#name").val();
		$.ajax({
			   type: "POST",
			   url: "/json/validationName",
			   data: {
				   'name':name,
				  	'type':1
			   },
			   success: function(data){
				   var tmp=data.trim().split("_");
				   $("#statusName").val(tmp[0]);
				   $("#enname").val(tmp[1]);
				   $("#initial").val( tmp[2]);
				   if(tmp[0]=="no"){
					   $("#myqqqq").text("名称已经存在！");
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
						<li class="tabs_active"><a href="/sites/carstyle/carStyleHomeAll">全部车型列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/sites/carstyle/carStyleAdd?mypid=${mypid}">添加车型</a></li>
					</ul>
			   </div>
			   <input type="hidden" id="statusName">
<form action="/sites/carstyle/carStyleUpdate" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="addForm">
<input type="hidden" name="mypid" value="${mypid }">
<input type="hidden" name="id" value="${cList.id }">
<input type="hidden" name="pid" value="${mypid }">
	<input type="hidden" name="oldImage" value="${cList.spic}">

  
	<div class="borderDiv">
		
	</div>
	<div class="borderDiv">
		<table>
		<tr>
		 	<td style="vertical-align:top" >所属品牌：</td>
                <td>
                <select disabled="disabled" sta="p" staName="所属品牌"  id="pid">
                	<option value="" style="width: 280px;" >请选择</option>
                	<c:forEach items="${pBrands}" var="pb">
						<option value="${pb.id }"  <c:if test="${pb.id==mypid }">selected="selected"</c:if> > ${pb.typepinyI } ${pb.name}</option>
						</c:forEach>
                </select><span style="color:red;">*</span>
                </td>
                </tr>
            <tr>
            	<td style="vertical-align:top">车型名称：</td>
                <td><input  sta="p" staName="车型名称" id="name" maxlength="50" type="text"  id="name" style="width: 280px;height: 25px" name="style" value="${cList.style }" onchange="verificationmy()"/>
                <span id="myqqqq" style="color:red;">* (请控制50字以内)</span>
                </td>
            </tr>
			  <tr>
				<td>车型拼音：</td>
				<td><input sta="p" staName="所属品牌" type="text" name="enname" id="enname" maxlength="25" value="${cList.enname }" style="width: 280px;height: 25px"><span style="color:red;">* (请控制25字以内)</span></td>
			</tr>
			 <tr>
				<td>车型简拼：</td>
				<td><input sta="p" staName="车型简拼" type="text" name="initial" id="initial" value="${cList.initial}" maxlength="25"  style="width: 280px;height: 25px"><span style="color:red;">* (请控制25字以内,拼音可能存在多音字，如有出入，请重新输入。)</span></td>
			</tr>
            <tr>
				<td>车型图片：</td>
				<td>
				<c:choose>
          	         <c:when test="${empty cList}">
          	              <img  id="oneImage" src='/images/upload.jpg' onclick="$('#listPicFile').trigger('click')"/><span class="font" style="color: red">*(支持.jpg)</span>
          	      </c:when>
          	        <c:otherwise>
          	          <img  id="oneImage" width="100px" height="100px" src='${cList.spicsrc }' onclick="$('#listPicFile').trigger('click')"/>
          	            </c:otherwise>
          	     </c:choose>
          	     <input  sta="p" staName="车型图片"type="hidden" name="spic" id="logo"  value="${cList.spic}">
          	       <div style="display: none">
					           <input id="listPicFile" name="listPicFile" type="file"  onchange="uploadImgStyle()"/>
				            </div>
          	    </td>
			</tr>
            <tr>
            	<td style="vertical-align:top">厂商指导价:</td>
                <td>
                <input sta="p" staName="厂商指导价" type="text" name="factoryPrice" id="factoryPrice" maxlength="50" value="${cList.factoryPrice }"  style="width: 280px;height: 25px" onkeyup="this.value=this.value.replace(/[^\d.-]/g,'') "><span style="color:red;">* (请输入数字，示例：xx-xx)</span>
                </td>
            </tr>
             <tr>
            	<td style="vertical-align:top" >品牌标识:</td>
                  <td>
                	<select sta="p" staName="品牌标识" name="publicMark" style="width: 280px;" >
                		<option <c:if test="${cList.publicMark==null }">selected="selected"</c:if> value="">请选择</option>
                		<option <c:if test="${cList.publicMark==0 }">selected="selected"</c:if> value="0">全部</option>
                		<option <c:if test="${cList.publicMark==1 }">selected="selected"</c:if> value="1">新车</option>
                		<option <c:if test="${cList.publicMark==2 }">selected="selected"</c:if> value="2">二手车</option>
                	</select><span class="font" style="color: red">*</span>
                </td>
            </tr>
            
             <tr>
            	<td style="vertical-align:top">车型大小:</td>
            	 <td>
 				<select  name="bos" id="bos" style="width: 280px;">
            	 <option value="0">请选择</option>
            	 <option value="1" <c:if test="${cList.bos==1 }">selected="selected"</c:if>>小型车</option>
            	 <option value="2" <c:if test="${cList.bos==2 }">selected="selected"</c:if>>紧凑车型</option>
            	 <option value="3" <c:if test="${cList.bos==3 }">selected="selected"</c:if>>中级车</option>
            	 <option value="4" <c:if test="${cList.bos==4 }">selected="selected"</c:if>>中高级车</option>
            	 <option value="5" <c:if test="${cList.bos==5 }">selected="selected"</c:if>>豪华车</option>
            	 <option value="6" <c:if test="${cList.bos==6 }">selected="selected"</c:if>>MPV</option>
            	 <option value="7" <c:if test="${cList.bos==7 }">selected="selected"</c:if>>SUV</option>
            	 <option value="8" <c:if test="${cList.bos==8 }">selected="selected"</c:if>>跑车</option>
            	 <option value="9" <c:if test="${cList.bos==9 }">selected="selected"</c:if>>微型车</option>
            	 <option value="10" <c:if test="${cList.bos==10 }">selected="selected"</c:if>>轻客</option>
            	 </select>
            	 </td>
            </tr>
               <tr>
            	<td style="vertical-align:top">排量:</td>
            	 <td><input sta="p" staName="排量" type="text" name="level" id="level" maxlength="50" value="${cList.level }" style="width: 280px;height: 25px" onkeyup="this.value=this.value.replace(/[^\d.TL|]/g,'')"><span style="color:red;">* (请控制50字以内)</span></td>
            </tr>
               <tr>
            	<td style="vertical-align:top">变速箱:</td>
            	 <td><input  sta="p" staName="变速箱" type="text" name="speedBox" maxlength="50" id="speedBox" value="${cList.speedBox }" style="width: 280px;height: 25px"><span style="color:red;">* (请控制50字以内)</span></td>
            </tr>
             <tr>
            	<td style="vertical-align:top">详细:</td>
                <td>
              <textarea maxlength="200"  rows="3" cols="60" name="texts"  >${cList.texts }</textarea><span style="color:red;">(请控制200字以内)</span>
                </td>
            </tr>
            <tr><td>
		<input type="button" id="newZixunBtn" value="保存" onclick="subAdd()"/>
	</tr>
        </table>
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