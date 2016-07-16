<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>团车网车款管理</title>
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
<script type="text/javascript" charset="utf-8" src="/js/sites/sites.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript">
$(document).ready(function(){ 
	var pid=$("#brandName").val();
	if(pid!=null && ""!=pid){
		 $("#muio option").remove();
		 $.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#muio").val("");
				   for(i in data) {
					   /* alert(data[i].style+"名");
				   		alert(data[i].id); */
				   		if(data[i].id==$("#qwer").val()){
				   			$("#muio").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	　　}); 
	function allcart(){
		$("#searchSpecialForm").attr("action","/sites/carstyle/stylesearch");
		
		$("#searchSpecialForm").submit();
	}
	function getStylemy(pid){
		 $("#muio option").remove();
 		if($("#brandName").val()!=null && ""!=$("#brandName").val()){
		$.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				   for(i in data) {
				   		$("#muio").append("<option value="+data[i].id+">"+data[i].style+"</option>");
				   
				   }
					  
				   } 
			
			});
 		 }
	}
	function isdel(id,ppid){
		if(window.confirm('你确定要删除该记录！')){
			window.location.href="/sites/carstyle/carStyleDelMyppid?id="+id+"&myppid="+ppid;
		
		}
	}
	
	function editBefore(id,ppid){
		var arges="";
		if($("brandName").val()!=null&&""!=$("brandName").val()){
			arges+="&brandName="+$("brandName").val()
		}
		if($("pppid").val()!=null&&""!=$("muio").val()){
			arges+="&pppid="+$("muio").val()
		}
		if($("cName").val()!=null&&""!=$("cName").val()){
			arges+="&cName="+$("cName").val()
		}
		window.location.href="/sites/carstyle/styleEditBefore?id="+id+"&myppid="+ppid+arges;
	}
</script>
</head>
<body>
<%-- <input type="hidden" id="qwer" value="${caename.id}"> --%>
<input type="hidden" id="qwer" value="${styleNameMy}">
<input type="hidden" id="Mystyleid" value="${Mystyleid}">
<form action="/sites/carstyle/stylesearch" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="specialSubject" id="searchSpecialForm">
	<input type="hidden" name="mypid" value="${mypid }">
	<input type="hidden" name="statusid" value="${statusid}">
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/sites/carstyle/styleHomeAll">全部车款列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/sites/carstyle/styleAdd?myppid=${myppid }">添加车款</a></li>
					</ul>
			   </div>
			</td>
		</tr>
			<table>
				<tr class="lh28">
					<td class="ti">品牌名称:</td>
					<td>
					<select name="brandName" onchange="getStylemy(this.options[this.options.selectedIndex].value)" id="brandName">
						<option value="">请选择品牌</option>
						<c:forEach items="${pBrands}" var="pb">
						<option value="${pb.id }"  <c:if test="${pb.id==brandName }"> selected="selected"</c:if>> ${pb.typepinyI }${pb.name }</option>
						</c:forEach>
					</select>
					
					</td>
				
					<td class="ti">车型名称:</td>
					<td>
					<select name="pppid" id="muio" >
					<option value="">请选择车型</option>
					</select>
					<font>车型标识:</font>
					<select  name="publicMark" style="width: 200px">
						<option  value="">请选择</option>
						<option value="0"<c:if test="${publicMark==0 }">selected="selected"</c:if> >全部</option>
						<option  value="1" <c:if test="${publicMark==1 }">selected="selected"</c:if>>新车</option>
						<option  value="2" <c:if test="${publicMark==2 }">selected="selected"</c:if>>二手车</option>
					</select>
					车款名称： <input type="text" name="cName" id="cName" maxlength="20" style="width:150px;" value="${cName}"/>
					<input type="button" value="搜索"  class="btn" onclick="allcart()"/> 
					</td>
					
					</tr>			
					
				<tr class="lh28">
				</tr>
			</table>
  			
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr">
							<!-- <th><input type="checkbox" id="allCheck" onclick="selectAll()"/></th> -->
							<th style="white-space:nowrap;">车款编号</th>
							<th style="white-space:nowrap;">所属品牌</th>
							<th style="white-space:nowrap;">所属车系</th>
							<th style="white-space:nowrap;">车款名称</th>
							<th style="white-space:nowrap;">厂商指导价</th>
							<th style="white-space:nowrap;">排量</th>	
							<th style="white-space:nowrap;">变速箱</th>
							<th style="white-space:nowrap;">车款标识</th>
							<th style="white-space:nowrap;">添加时间</th>
							<th style="white-space:nowrap;">添加人</th>
							<th style="white-space:nowrap;">修改时间</th>
							<th style="white-space:nowrap;">修改人</th>
							<th style="white-space:nowrap;">状态</th>
							<th style="white-space:nowrap;">操作</th>
							
						</tr>
					</thead>
					<c:if test="${slist!=null }">
						<c:forEach items="${slist }" var="cList">
						<tr>
						<%-- ${func:getEditName(zixun.editorId)} --%>
							<%-- <th><input name="idBox" type="checkbox"value="${blist.id}" /></th> --%>
							<td>${cList.id}</td>
							<td>${func:getBrandName(cList.bbip)}</td>
							<td>${cList.carname}</td>
							<td>${cList.style}</td>
							<td>${cList.factoryPrice}</td>
							<td>${cList.level}</td>
							<td>${cList.speedBox}</td>
						<td>
							<c:if test="${cList.publicMark==0}">全部</c:if> 
							<c:if test="${cList.publicMark==1}">新车</c:if> 
							<c:if test="${cList.publicMark==2}">二手车</c:if> 
							<c:if test="${cList.publicMark==null}">无</c:if> 
						</td>
							<td>${cList.createTime}</td>
							<td>${func:getEditName(cList.createTimeUserID)}</td>
							<td>${cList.updateTime}</td>
							<td>${func:getEditName(cList.updateTimeUserID)}</td>
							<td> <c:if test="${cList.type==0}">正常</c:if> 
								<c:if test="${cList.type==1}">停售</c:if>
							</td>
							<td><a onclick="editBefore(${cList.id},${cList.ppid})" href="#">编辑 </a> <a  onclick="isdel(${cList.id},${cList.ppid})"  href="#">车款删除 </a> 
							<c:if test="${cList.type==0}"><a href="/sites/carstyle/slodOut?ppid=${cList.ppid}&id=${cList.id}">停售</a></c:if>
							<c:if test="${cList.type==1}"><a href="/sites/carstyle/putaway?ppid=${cList.ppid}&id=${cList.id}">在售 </a></c:if>
							</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${slist==null }">
					<tr class="main_info">
						<td colspan="14">没有相关数据</td>
					</tr>
					</c:if>
				</table>
			</div>
			<div class="page_and_btn" style="text-align:center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
</table>
</form>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
</body>
</html>