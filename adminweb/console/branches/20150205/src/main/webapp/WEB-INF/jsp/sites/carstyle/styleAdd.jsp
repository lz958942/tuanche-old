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
<title>团车网添加车款</title>
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
		var ppid=$("#ppid").val();
		var name=$("#name").val();
		var factoryPrice=$("#factoryPrice").val();
		var level=$("#level").val();
		var speedBox=$("#speedBox").val();
		
		
		if(ppid==null||""==ppid){
		alert("所属车系不能为空");
		return;
		}
		if(name==null||""==name){
			alert("车款名称不能为空");
			return;
			}
		if(factoryPrice==null||""==factoryPrice){
			alert("厂商指导价不能为空");
			return;
			}
		if(!(factoryPrice.substring(factoryPrice.lastIndexOf(".")+1,factoryPrice.length).length<3)){
			alert("厂商指导价保留小数点后两位数字！");
			return;
			}
		if(level==null||""==level){
			alert("排量不能为空");
			return;
			}
		if(speedBox==null||""==speedBox){
			alert("变数箱不能为空！");
			return;
			}
		if($("#statusName").val().trim()=="no"){
			alert("名称已经重复!");
			return;
			}
		
		
		$("#addForm").submit()
	}
	function verificationmy(){
		var name=$("#name").val();
		$.ajax({
			   type: "POST",
			   url: "/json/validationName",
			   data: {
				   'name':name,
				  	'type':"3,"+$("#ppid").val()
			   },
			   success: function(data){
				   $("#statusName").val(data);
				   if(data.trim()=="no"){
					   $("#myqqqq").text("名称已经存在！");
				   }else{
					   $("#myqqqq").text("可以使用！");
				   }
			   }
			});
		
	}
	function getStylemy(pid){
		 $("#ppid option").remove();
		if($("#brandName").val()!=null && ""!=$("#brandName").val()){
		$.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#ppid").val("");
				   for(i in data) {
				   		$("#ppid").append("<option value="+data[i].id+">"+data[i].style+"</option>");
				   }
					  
				   } 
			
			});
		 }
	}
</script>
</head>
<body>
	<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/sites/carstyle/styleHomeAll">全部车款列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/sites/carstyle/styleAdd?myppid=${myppid }">添加车款</a></li>
					</ul>
			   </div>
			   <input type="hidden" id="statusName">
<form action="/sites/carstyle/styleAddTo" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="addForm">
<input type="hidden" name="myppid" value="${myppid }">
<input type="hidden" name="id" value="${sdomain.id }">
	<div class="borderDiv">
		
	</div>
	<div class="borderDiv">
		<table>
		<tr>
		<td class="ti">品牌名称:</td>
					<td>
					<select name="brandName" style="width: 280px;" onchange="getStylemy(this.options[this.options.selectedIndex].value)" id="brandName">
						<option value="">请选择品牌</option>
						<c:forEach items="${pBrands}" var="pb">
						<option value="${pb.id }"  <c:if test="${pb.id==statusBrand }"> selected="selected"</c:if>> ${pb.typepinyI }${pb.name }</option>
						</c:forEach>
					</select>
					</td>
		</tr>
		<tr>
		 	<td style="vertical-align:top" >所属车型：</td>
                <td>
                <select name="ppid" id="ppid" style="width: 280px;">
                	<option value="">请选择</option>
                	<c:forEach items="${series}" var="pb">
						<option value="${pb.id }"  <c:if test="${pb.id==myppid }">selected="selected"</c:if> >${pb.style}</option>
						</c:forEach>
                </select> <span style="color:red;">* </span>
                </td>
                </tr>
                <tr>
            	<td style="vertical-align:top" >车款标识:</td>
                  <td>
                	<select sta="p" staName="品牌标识" name="publicMark" style="width: 280px;" >
                		<option value="">请选择</option>
                		<option value="0">全部</option>
                		<option value="1">新车</option>
                		<option value="2">二手车</option>
                	</select><span class="font" style="color: red">*</span>
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">车款名称：</td>
                <td><input maxlength="50" type="text"  id="name"   name="style"  style="width: 280px;height: 25px" onchange="verificationmy()"/>
                <span  id="myqqqq" style="color:red;">* (请控制50字以内)</span>
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">厂商指导价:</td>
                <td>
                <input type="text" name="factoryPrice" maxlength="50" id="factoryPrice"  style="width: 280px;height: 25px" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')">万<span style="color:red;" >* (请输入数字示例：xx.00)</span>
                </td>
            </tr>
             <!--  <tr>
            	<td style="vertical-align:top">市场价:</td>
                <td>
                <input type="text" name="marketPrice" id="marketPrice"  maxlength="50" style="width: 280px;height: 25px"><span style="color:red;"> (请控制50字以内)</span>
                </td>
            </tr> -->
             <!-- <tr>
            	<td style="vertical-align:top" >车型大小:</td>
            	
            	 <td>
            	
 			<select name="bos" id="bos" style="width: 280px;">
            	 	<option value="">请选择</option>
            	 <option value="2"  >小型车</option>
            	 <option value="4"  >中型车</option>
            	 <option value="9" >大型车</option>
            	 </select><span style="color:red;">* </span>
            	 </td>
            </tr> -->
               <tr>
            	<td style="vertical-align:top">排量:</td>
            	 <td><input type="text" name="level" id="level"  style="width: 280px;height: 25px" maxlength="50"><span style="color:red;">* (请控制50字以内)</span></td>
            </tr>
               <tr>
            	<td style="vertical-align:top">变速箱:</td>
            	 <td><input type="text" name="speedBox" id="speedBox"  style="width: 280px;height: 25px" maxlength="50"><span style="color:red;">* (请控制50字以内)</span></td>
            </tr>
            
               <tr>
            	<td style="vertical-align:top">车身颜色:</td>
            	 <td><input type="text" name="colors" id="colors"  style="width: 280px;height: 25px" maxlength="200"><span style="color:red;">(请控制200字以内)</span></td>
            </tr>
             <tr>
            	<td style="vertical-align:top">详细:</td>
                <td>
              <textarea maxlength="200" rows="3" cols="60" name="texts" ></textarea><span style="color:red;">(请控制200字以内)</span></td>
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