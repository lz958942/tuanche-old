<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
 <style type="text/css">
 	*{
 	
	font-size:12px;
	}
 </style>
<script type="text/javascript">
$(document).ready(function(){ 
	var pid=$("#brind").val();
	if(pid!=null && ""!=pid){
		 $.ajax({
			   type: "POST",
			   url: "/json/carstyle/ajaxStyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#keywordid").val("");
				   for(i in data) {
				   		if(data[i].id==$("#carid").val()){
				   			$("#keywordid").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
				   		}else{
				   			$("#keywordid").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	　　}); 
	
	function divshow(id){
		var $divs=$("div[sta=showdiv]").hide();
		$("#oladiv_"+id).show();
	}
	function nonediv(id){
		$("#oladiv_"+id).hide();
	}
	function selectCar(pid){
		 $("#keywordid option").remove();
		 if($("#brind").val()!=null&&$("#brind").val()!=""){
			$.ajax({
				   type: "POST",
				   url: "/json/carstyle/ajaxStyle",
				   dataType:'json',
				   data: {
					   'brandID':pid
				   },
				   success: function(data){
					 $("#keywordid").val("");
					   for(i in data) {
					   		$("#keywordid").append("<option value="+data[i].id+">"+data[i].style+"</option>");
					   }
					   } 
				});
		 }
	}
	function cream(id){
		if(window.confirm('设为精华帖会自动通过审核，请确认！')){
			$("#reviewForm").attr("action","/review/cream?isCream=1&idd="+id);
			$("#reviewForm").submit();
			alert("设置精华帖成功！");
		}
	}
	function pass(id){
		if(id!=null && id!=""){
			if(id!=null && id!=""){
				if(window.confirm('你确定要通过记录！')){
				$.ajax({
					   type: "GET",
					   url: "/review/pass?idd="+id,
					   success: function(m){
						 $("#reviewForm").submit();	
					   }
					});
				}
		
	}
	}
	}
	function noPass(id){
		if(id!=null && id!=""){
			if(window.confirm('你确定要不通过记录！')){
			$.ajax({
				   type: "GET",
				   url: "/review/passNo?idd="+id,
				   success: function(m){
					 $("#reviewForm").submit();	
				   }
				});
			}
	}
	}
	function findPic(id){
		openWindow("/review/particular?idd="+id,"yes",700,500);
	}
	function openWindow(mypage,haveScroll,theWidth,theHight,theName)
	{
		var w = paramexists(theWidth)? theWidth:600;
		var h = paramexists(theHight)? theHight:450;
		var scroll = paramexists(haveScroll)? haveScroll:'no';
		var myname = paramexists(theName)? theName:'';
		
		LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
		TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
		settings ='height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
		window.open(mypage,myname,settings);
	}
	function paramexists(what){
		return(typeof what!="undefined" && what!="")
		}
	function replyShow(id){
		if(id!=null && id!=0){
			$("#kevinDiv1").show();
			$("#replyContent").val("");
			 $.ajax({
				 	type: "GET",
				    url: "/json/ajaxGetShortcut?id="+id,
				    dataType:'json',
				    success: function(data){
					   $("#shortcut_ajax option").remove()
					   $("#shortcut_ajax").append("<option  selected='select' value=''>请选择</option>");
					   for(i in data) {
					   		$("#shortcut_ajax").append("<option value="+data[i].desc+">"+data[i].desc+"</option>");
					   }
				   }
				 });
			 $("#replyID").val(id);
		}
	}
	function clossKevinDic1(){
		$("#kevinDiv1").hide();
	}
	function reply(){
		  var id= $("#replyID").val();
		  var content= $.trim($("#replyContent").val());
		  if(id!=null && id!=0){
			  if(content==null || ""==content){
				  alert("请填写回复内容！");
				  return;
			  }
			  $.ajax({
				 	type: "POST",
				    url: "/reply/comments",
				    data:{idd:id,content:content},
				    success: function(data){
				    	alert("评论成功，即将关闭！");
				    	$("#kevinDiv1").hide();
				    	$("#reviewForm").submit();
				   }
				 });
		  }
	}
	function changelevel(date){
		if(date!=null && ""!=date){
			$("#replyContent").val("");
			$("#replyContent").val(date);
		}
	}
</script>
</head>
<body>
<input id="carid" type="hidden" value="${review.carstyleId }">
<div style="height: 5px"></div>
		<form action="/review/search" method="post" style="padding: 0 10px 0 10px; margin-top: 0px;" name="review" id="reviewForm">
			<table>
				<tr class="lh28">
					<td class="ti">评论编号：&nbsp;</td>
					<td><input onkeyup="this.value=this.value.replace(/[^\d]/g,'') " type="text"maxlength="10" id="id" name="id" style="width:200px; height:30px" value="${review.id}" /></td>
					<td class="ti">评论关键字：&nbsp;</td>
					<td><input type="text" name="comment" style="width: 200px ;height: 30px;"value="${review.comment}" id="titleid" /></td>
				<td class="ti">是否晒车：</td>
					<td id="pradio">
						<select name="ishavepic" id="pradio" style="width: 200px">
							<option  value="1" <c:if test="${review.ishavepic ==1}">selected="select"</c:if>>有图片</option>
							<option value="0" <c:if test="${review.ishavepic ==0}">selected="select"</c:if> >无图片</option>
							<option value="3" <c:if test="${review.ishavepic ==3||review.ishavepic==null}">selected="select"</c:if>> 全部</option>
						</select>
					</td>
				</tr>
				<tr class="lh28">
					<td class="ti">品牌：&nbsp;</td>
					<td>
					<select id="brind" name="brandName" style="width: 200px" onchange="selectCar(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option  <c:if test="${bid ==b.id}">selected="select"</c:if> value="${b.id }">${b.reviewInitial}${b.name}</option>
						</c:forEach>
					</select>
					</td>
					<td class="ti">评论车型：&nbsp;</td>
					<td>
					<select name="carstyleId" id="keywordid" style="width: 200px" >
						<%-- <c:forEach items="${car }" var="car">
							<option  <c:if test="${review.carstyleId ==car.id}">selected="select"</c:if> value="${car.id }">${car.style}</option>
						</c:forEach> --%>
					</select>
<%-- 					<input type="text" name="carstyleId" style="width: 200px;"value="${review.carstyleId}" id="keywordid" />
 --%>					</td>
				<td class="ti">评论来源：</td>
					<td>
					<select name="source" id="source" style="width: 200px">
							<option  value="1" <c:if test="${review.source ==1}">selected="select"</c:if>>PC端</option>
							<option value="2" <c:if test="${review.source ==2}">selected="select"</c:if> >安卓</option>
							<option  value="3" <c:if test="${review.source ==3}">selected="select"</c:if>>IOS</option>
							<option  value="4" <c:if test="${review.source ==4}">selected="select"</c:if>>WAP</option>
							<option value="0" <c:if test="${review.source ==0||review.source==null}">selected="select"</c:if>>全部来源</option>
						</select>&nbsp;
					</td>
				</tr>
				<tr>
					<td class="ti">评论时间：</td>
					<td>
						<input id="startDate" name="modifyTime" class="dateCss"
						type="text" onclick="SelectDate(this,'yyyy-MM-dd','起始日期')"
						readonly="readonly" value="${review.modifyTime }">
					</td>
					<td class="ti">终止日期</td>
					<td>
						<input id="endtDate" name="endtDate" class="dateCss"
						type="text" onclick="SelectDate(this,'yyyy-MM-dd','终止日期')"
						readonly="readonly" value="${review.endtDate }">
						<input type="button" value="搜索" class="btn"onclick="submit()" />
					</td>
				</tr>
				
			</table>
			<div  style="text-align: center;margin: 20px auto 0px auto;">
				<table class="table_style table table-bordered" style="">
					<tr class="attr">
						<td style="white-space: nowrap;">编号</td>
						<td style="white-space: nowrap;">总评价</td>
						<td style="white-space: nowrap;">车型名称</td>
						<td style="white-space: nowrap;">价格程度</td>
						<td style="white-space: nowrap;">团长服务质量</td>
						<td style="white-space: nowrap;">4S店服务质量</td>
						<td style="white-space: nowrap;">点评内容</td>
						<td style="white-space: nowrap;">是否晒车</td>
						<td style="white-space: nowrap;">评论来源</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<tbody align="center" id="dataBody">
					<c:if test="${list!=null }">
					<c:forEach items="${list}" var="list">
								<tr>
									<td >${list.id }</td>
									<td >
									<c:if test="${list.commentTotal==null ||list.commentTotal==''}">无评价</c:if> 
									<c:if test="${list.commentTotal==1}">一星</c:if> 
									<c:if test="${list.commentTotal==2}">二星</c:if> 
									<c:if test="${list.commentTotal==3}">三星</c:if> 
									<c:if test="${list.commentTotal==4}">四星</c:if> 
									<c:if test="${list.commentTotal==5}">五星</c:if> 
									</td>
									<td >
									<c:if test="${list.carName==null || list.carName==''}">
										暂无
									</c:if>
									<c:if test="${list.carName!=null }">
										${list.carName }
									</c:if>
									<c:if test="${list.carName==null  || ''==list.carName}">
										无车型
									</c:if>
									</td>
									<td >
									<c:if test="${list.commentPrice==null || list.commentPrice==0}">无评价</c:if> 
									<c:if test="${list.commentPrice==1}">一星</c:if> 
									<c:if test="${list.commentPrice==2}">二星</c:if> 
									<c:if test="${list.commentPrice==3}">三星</c:if> 
									<c:if test="${list.commentPrice==4}">四星</c:if> 
									<c:if test="${list.commentPrice==5}">五星</c:if>
									</td>
									<td >
									<c:if test="${list.commentService==null || list.commentService==0}">无评价</c:if>
									<c:if test="${list.commentService==1}">一星</c:if> 
									<c:if test="${list.commentService==2}">二星</c:if> 
									<c:if test="${list.commentService==3}">三星</c:if> 
									<c:if test="${list.commentService==4}">四星</c:if> 
									<c:if test="${list.commentService==5}">五星</c:if>
									</td>
									<td >
									<c:if test="${list.commentShop==null || list.commentShop==0}">无评价</c:if> 
									<c:if test="${list.commentShop==1}">一星</c:if> 
									<c:if test="${list.commentShop==2}">二星</c:if> 
									<c:if test="${list.commentShop==3}">三星</c:if> 
									<c:if test="${list.commentShop==4}">四星</c:if> 
									<c:if test="${list.commentShop==5}">五星</c:if>
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
									<td >
										<c:if test="${list.ishavepic==1}">有</c:if> 
										<c:if test="${list.ishavepic==0||list.ishavepic==null}">无</c:if>
									</td>
									<td >
										<c:if test="${list.source==1}">PC</c:if> 
										<c:if test="${list.source==2}">安卓</c:if> 
										<c:if test="${list.source==3}">IOS</c:if> 
										<c:if test="${list.source==4}">WAP</c:if>
										<c:if test="${list.source==0||list.source==null}">未知来源</c:if>
									</td>
									<td >
									<c:if test="${list.ishavepic!=0 && list.ishavepic!=null}">
									<a href="javascript:void(0)" onclick="findPic(${list.id})">审核图片</a>|
									</c:if>
									<a href="javascript:void(0)"  onclick="pass(${list.id})">通过</a>|<a href="javascript:void(0)"  onclick="noPass(${list.id})">不通过</a>|<a href="javascript:void(0)"  onclick="cream(${list.id})">精华</a>
									<c:if test="${list.isReply==0 ||list.isReply==null}">|<a href="javascript:void(0)" onclick="replyShow(${list.id})">回复</a></c:if>
									</td>
								</tr>
					</c:forEach>
						</c:if>	
					<c:if test="${list==null }">
								<tr class="main_info">
									<td colspan="14">没有相关数据</td>
								</tr>
						</c:if>	
					</tbody>
				</table>
				<!-- 回复 -->
			<div  id="kevinDiv1"  style="position:fixed;display:none;margin-left: auto;margin-right: auto;z-index:9;height:;width:500px;border:1px solid #ddd;left:210px;top:110px;border-bottom: none !important;background-color:#8F8FBD;">
				<input type="hidden" id="replyID">
				<font>回复</font><br>
				<textarea id="replyContent" name="content" style="width: 450px" rows="3" cols="31" maxlength="500"></textarea><br>
				<font>快捷回复：</font>
				<select id="shortcut_ajax" onchange="changelevel(this.options[this.options.selectedIndex].value)" >
					<option>请选择</option>
				</select> <input type="button" value="回复" onclick="reply()"> <input onclick="clossKevinDic1()" type="button" value="关闭">
			</div>
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
		</form>

<div id="menuContent" style="display: none; position: absolute;">
	<ul id="treeCity" class="ztree"></ul>
</div>

<div id="menuContentClass" style="display: none; position: absolute;">
	<ul id="treeClass" class="ztree"></ul>
</div>
</body>
</html>