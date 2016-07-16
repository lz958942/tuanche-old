<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.tuanche.console.web.AuthUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网问答话题管理</title>
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
<script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
</head>
<body>
<% boolean recom=AuthUtil.checkAuth(request,"/questionAnswer/recom"); %>
<% boolean hasUpdate=AuthUtil.checkAuth(request,"/questionAnswer/toUpdateTopic"); %>
<% boolean hasUpdates=AuthUtil.checkAuth(request,"/questionAnswer/updatetopstatus"); %>
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/questionAnswer/topicList">问答话题管理</a></li>
						<%if(hasUpdate){%>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toUpdateTopic">新增问答话题</a></li>
						<%}%>
					</ul>
			   </div>
			</td>
		</tr>
	</table>	
	<form action="/questionAnswer/topicList" method="post" id='questPic'>
			<table style="height:40px">
			<tr style="height:10px"></tr>
			<tr style="height:28px"><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="hidden" id='styleId' value='${topic.carstyleId}'/>
			选择品牌:
     			<select name="brandId" onchange="getStylemy(this.options[this.options.selectedIndex].value)" id="brandId">
					<option value="">请选择</option>
					<c:forEach var="brand" items="${brands}">
					<option value="${brand.id}"<c:if test="${brand.id==topic.brandId}">selected='selected'</c:if> >${brand.typepinyI}-${brand.name}</option>
					</c:forEach>
				</select>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			选择车型:
			<select  name='carstyleId' id='muio'>
				<option value="">请选择</option>
			</select>
          		
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			话题title:<input type="text" value='${topic.title}' name='title' style="height:20px"/>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			状态：<select name='topicStatus'>
						<option value=''>-----请选择-----</option>
						<option value='0' <c:if test="${topic.topicStatus==0}">selected='selected'</c:if>>-----隐藏-----</option>
						<option value='1' <c:if test="${topic.topicStatus==1}">selected='selected'</c:if>>-----上线-----</option>
						</select>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			是否推荐：<select name='isRecom'>
						<option value=''>-----请选择-----</option>
						<option value='0' <c:if test="${topic.isRecom==0}">selected='selected'</c:if>>-----未推荐-----</option>
						<option value='1' <c:if test="${topic.isRecom==1}">selected='selected'</c:if>>-----已推荐-----</option>
						</select>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="查询" ></td>
			</tr>
			<tr style="height:10px"></tr>
			</table>			
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr" style="background-color:#2b84be;color:white" >
							<th style="white-space:nowrap;">话题id</th>
							<th style="white-space:nowrap;">话题title</th>
							<th style="white-space:nowrap;">内容</th>
							<th style="white-space:nowrap;width:150px">大图</th>
							<th style="white-space:nowrap;">创建时间</th>
							<th style="white-space:nowrap;">状态</th>
							<th style="white-space:nowrap;width:75px">品牌</th>
							<th style="white-space:nowrap;width:75px">车型</th>
							<th style="white-space:nowrap;width:75px">操作</th>
						</tr>
					</thead>
					<tbody align="center" >
					<c:choose>
						<c:when test="${not empty topics }">
							<c:forEach items="${topics}" var="topic">
															
									<tr>
										<td>${topic.id}</td>
										<td>
										${topic.title}
										</td>
										<td>
										<div  style="width:389px ; height:20px ;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${topic.content}</div>
										<a href="javascript:void(0)" onclick="divshow(${topic.id})"> 详细</a>
										<div  sta="showdiv" id="oladiv_${topic.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
										 ${topic.content}
										 <div style="text-align:right;">	
										<input type="button" value="关闭" onclick="nonediv(${topic.id})">
										</div>
										</div>
										</td>
										<td><c:if test="${topic.picture!=''}"><img style="width:150px;height:100px;"  src="${topic.picture}"></c:if>										
										</td>										
										<td>${topic.buildTime}</td>
										<td>
										<c:if test="${topic.topicStatus==0}">隐藏</c:if>
										<c:if test="${topic.topicStatus==1}">上线</c:if>
										</td>
										<td>${func:getBrandName(topic.brandId)}</td>
										<td>${func:getallStyle(topic.carstyleId)}	</td>
										<td>
										<%if(hasUpdate){%>
											<a href="/questionAnswer/toUpdateTopic?id=${topic.id}">修改</a>
										<%}%>
										<%if(hasUpdates){%>
											<c:if test="${topic.topicStatus==1}">
											<a href="javascript:void(0)" onclick="deletet1(${topic.id})">隐藏</a>
											</c:if> 
											<c:if test="${topic.topicStatus==0}">
											<a href="javascript:void(0)" onclick="deletet2(${topic.id})">上线</a>
											</c:if>
										<%}%>
										<%if(recom){%>
											<c:if test="${topic.isRecom==0}">
											<a href="javascript:void(0)" onclick="recom(${topic.id},'推荐',1)">推荐</a>
											</c:if>
											<c:if test="${topic.isRecom==1}">
											<a href="javascript:void(0)" onclick="recom(${topic.id},'取消推荐',0)">取消推荐</a>
											</c:if>
										<%}%>
										<c:if test="${topic.current==0}">
											<a href="javascript:void(0)" onclick="current(${topic.id},'设为本期',1)">设为本期</a>
											</c:if>
											<c:if test="${topic.current==1}">
											<a href="javascript:void(0)" onclick="current(${topic.id},'取消本期',0)">取消本期</a>
											</c:if>
											<%if(hasUpdates){%>
											<a href="javascript:void(0)" onclick="deletet3(${topic.id})">删除</a>
										<%}%>
										</td>
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>											
								<tr class="main_info">
									<td colspan="14">没有相关数据</td>
								</tr>
						</c:otherwise>					
					</c:choose>													
					</tbody>
				
</table>
<div class="page_and_btn" style="text-align: center;">
	<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>
</body>
<script type="text/javascript">
function recom(id,name,recom){
	if(window.confirm('你确定'+name+'话题?')){
		$.post('/questionAnswer/recom?id='+id+'&recom='+recom,function(result){
			$("#questPic").submit();
		},'json');
	}
}
function current(id,name,current){
	if(window.confirm('你确定'+name+'话题?')){
		$.post('/questionAnswer/current?id='+id+'&current='+current,function(result){
			$("#questPic").submit();
		},'json');
	}
}
function deletet1(id){
	if(window.confirm('你确定隐藏话题?')){
		$.post('/questionAnswer/updatetopstatus?id='+id+'&status=0',function(result){
			$("#questPic").submit();
		},'json');
	}
}
function deletet2(id){
	if(window.confirm('你确定上线话题?')){
		$.post('/questionAnswer/updatetopstatus?id='+id+'&status=1',function(result){
			$("#questPic").submit();
		},'json');
	}
}
function deletet3(id){
	if(window.confirm('你确定删除话题?')){
		$.post('/questionAnswer/updatetopstatus?id='+id+'&status=-1',function(result){
			$("#questPic").submit();
		},'json');
	}
}
function divshow(id){
	var $divs=$("div[sta=showdiv]").hide();
	$("#oladiv_"+id).show();
}
function nonediv(id){
	$("#oladiv_"+id).hide();
}
</script>
<script type="text/javascript">
$(document).ready(function(){ 
	var pid=$("#brandId").val();
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
				 $("#muio").append("<option value=''>请选择</option>");
				   for(i in data) {
				   		if(data[i].id==$("#styleId").val()){
				   			$("#muio").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	　　}); 
function getStylemy(pid){
	 $("#muio option").remove();
	 if($("#brandId").val()!=null&&""!=$("#brandId").val()){
	 $.ajax({
		   type: "POST",
		   url: "/json/carstyle/ajaxStyle",
		   dataType:'json',
		   data: {
			   'brandID':pid
		   },
		   success: function(data){
			 $("#muio").val("");
			 $("#muio").append("<option value=''>请选择</option>");
			   for(i in data) {
				   /* alert(data[i].style+"名");
			   		alert(data[i].id); */
			   		$("#muio").append("<option value="+data[i].id+">"+data[i].style+"</option>");
			   }
				  
			   } 
		
		});
	 }
}
</script>
</html>