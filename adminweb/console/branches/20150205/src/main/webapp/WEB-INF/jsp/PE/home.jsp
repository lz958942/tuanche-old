<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/js/common/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery.tipsy.js"></script>
<script type="text/javascript" src="/js/common/validation.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
 <script type="text/javascript" charset="utf-8" src="/ue/ueditor.config.js"></script>
 <script type="text/javascript" charset="utf-8" src="/ue/ueditor.all.min.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ztree/ztree.3.5.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ztree/jquery.ztree.exhide-3.5.min.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/js/ajaxfileupload.js"> </script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" /> <script type="text/javascript" src="/js/zixun/zixun.js?v=2"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
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
				 $("#keywordid").append("<option selected='selected' value=''>请选择</option>");
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
					 $("#keywordid").append("<option selected='selected' value=''>请选择</option>");
					   for(i in data) {
					   		$("#keywordid").append("<option value="+data[i].id+">"+data[i].style+"</option>");
					   }
					   } 
				});
		 }
	}
	function selectAll() {
		$("input[name='ids']").attr("checked",$("#allCheck").attr("checked") ? true : false);
	}
	function operation(id,type,name){
		if(id!=null && type!=null && name != null){
			//type==1 操作，2删除，3上线
			var url="";
			if(type==1){
				url="/PE/operation?id="+id+"&type=1";
				window.location.href=url;
				return false;
			}else if(type==2){
				url="/PE/operation?id="+id+"&type=2";
			}else if(type==3 || type==4){
				url="/PE/operation?id="+id+"&type="+type;
			}
			if(window.confirm('你确定'+name+'！')){
				window.location.href=url;
			}
		}else{
			alert("操作无效");
			return false;
		}
	}
	function batchOnLine(type,name){
		var ids2="";
		if(type==null){
			alert("操作无效！");
			return false;
		}
		var ids=$("[name=ids]:checked");
		if(ids.length==0){
			alert("请选择"+name+"！");
			return false;
		}
		for(var j=0;j < ids.length; j++ ){
			ids2+=ids[j].value+",";
		}
		if(window.confirm('你确定批量'+name)){
			window.location.href="/PE/operation?ids="+ids2+"&type="+type;
		}
	}
</script>
</head>
<body>
<input id="carid" type="hidden" value="${bean.styleId }">
<div style="height: 5px"></div>
		<form action="/PE/PEList" method="post" style="padding: 0 10px 0 10px; margin-top: 0px;" name="review" id="reviewForm">
			<table>
				<tr class="lh28">
					<td class="ti">编号：&nbsp;</td>
					<td><input onkeyup="this.value=this.value.replace(/[^\d]/g,'') " type="text"maxlength="10" id="id" name="id" style="width:200px; height:30px" value="${bean.id}" /></td>
					<td class="ti">用户：&nbsp;</td>
					<td><input type="text" name="userName" style="width: 220px ;height: 30px;"value="${bean.userName}" id="titleid" /></td>
				<td class="ti">城市：</td>
					<td id="pradio">
						<select name="cityId" id="cityId" style="width: 200px">
						<option value="" selected="selected">请选择城市</option>
						<c:forEach items="${citys }" var="city">
							<option <c:if test="${bean.cityId==city.value.id }">selected='selected'</c:if>  value="${city.value.id }">${city.value.name }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr class="lh28">
					<td class="ti">品牌：&nbsp;</td>
					<td>
					<select id="brind" name="brandId" style="width: 200px" onchange="selectCar(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option  <c:if test="${bean.brandId ==b.id}">selected="select"</c:if> value="${b.id }">${b.reviewInitial}${b.name}</option>
						</c:forEach>
					</select>
					</td>
					<td class="ti">评论车型：&nbsp;</td>
					<td>
					<select name="styleId" id="keywordid" style="width: 220px" >
					</select>
				<td class="ti">评论指数：</td>
					<td>
					<select name="priceEvaluate" id="source" style="width: 200px">
							<option selected="selected" value="">请选择</option>
							<option  value="1" <c:if test="${bean.priceEvaluate ==1}">selected="select"</c:if>>一星</option>
							<option value="2" <c:if test="${bean.priceEvaluate ==2}">selected="select"</c:if> >二星</option>
							<option  value="3" <c:if test="${bean.priceEvaluate ==3}">selected="select"</c:if>>三星</option>
							<option  value="4" <c:if test="${bean.priceEvaluate ==4}">selected="select"</c:if>>四星</option>
							<option  value="5" <c:if test="${bean.priceEvaluate ==5}">selected="select"</c:if>>五星</option>
						</select>&nbsp;
					</td>
				</tr>
				<tr>
					<td class="ti">状态</td>
					<td>
					<select name="status">
						<option selected="selected" value="">请选择</option>
						<option <c:if test="${bean.status==0}">selected="selected"</c:if> value="0">未上线</option>
						<option  <c:if test="${bean.status==1}">selected="selected"</c:if> value="1">上线</option>
					</select>
					<td class="ti">图片</td>
					<td>
					<select name="picture">
						<option selected="selected" value="">请选择</option>
						<option <c:if test="${bean.picture==0 && bean.picture!=''}">selected="selected"</c:if> value="0">无图片</option>
						<option  <c:if test="${bean.picture==1 &&bean.picture!=''}">selected="selected"</c:if> value="1">有图片</option>
					</select>
					<input type="submit" value="搜索" class="btn" />
					</td>
					<td><a href="#" class="btn" onclick="batchOnLine(0,'下线')">批量下线</a></td>
					<td>
						<a href="#" class="btn"  onclick="batchOnLine(1,'上线')">批量上线</a>
						<a href="#" class="btn" onclick="batchOnLine(-1,'删除')">批量删除</a>
						<a href="/PE/savePE.do" class="btn">添加案例</a>
					</td>
				</tr>
				<tr>
				<td>
				</td>
				</tr>
			</table>
			<div  style="text-align: center;margin: 20px auto 0px auto;">
				<table class="table_style table table-bordered" style="">
					<tr class="attr">
						<td><input  id="allCheck" type="checkbox" onclick="selectAll()"></td>
						<td style="white-space: nowrap;">编号</td>
						<td style="white-space: nowrap;">评价</td>
						<td style="white-space: nowrap;">城市</td>
						<td style="white-space: nowrap;">品牌</td>
						<td style="white-space: nowrap;">车型名称</td>
						<td style="white-space: nowrap;">用户</td>
						<td style="white-space: nowrap;">创建人</td>
						<td style="white-space: nowrap;">创建时间</td>
						<td style="white-space: nowrap;">图片</td>
						<td style="white-space: nowrap;">操作人</td>
						<td style="white-space: nowrap;">操作时间</td>
						<td style="white-space: nowrap;">状态</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<tbody align="center" id="dataBody">
					<c:if test="${beanList!=null }">
					<c:forEach items="${beanList}" var="list">
								<tr>
								<td><input type="checkbox" name='ids' value='${list.id}'/></td>
									<td >${list.id }</td>
									<td>
									<c:if test="${list.priceEvaluate==null ||list.priceEvaluate==''}">无评价</c:if> 
									<c:if test="${list.priceEvaluate==1}">一星</c:if> 
									<c:if test="${list.priceEvaluate==2}">二星</c:if> 
									<c:if test="${list.priceEvaluate==3}">三星</c:if> 
									<c:if test="${list.priceEvaluate==4}">四星</c:if> 
									<c:if test="${list.priceEvaluate==5}">五星</c:if> 
									</td>
									<td >
									<c:if test="${list.cityName ==null}">无</c:if>
									<c:if test="${list.cityName !=null}">${list.cityName }</c:if>
									</td>
									<td >
									<c:if test="${list.brabdName==null || list.brabdName==''}">
										暂无
									</c:if>
									<c:if test="${list.brabdName!=null }">
										${list.brabdName }
									</c:if>
									</td>
									<td>
									<c:if test="${list.styleName==null  || ''==list.styleName}">
										无
									</c:if>
									<c:if test="${list.styleName!=null  && ''!=list.styleName}">
										${list.styleName}
									</c:if>
									</td>
									<td >
									<c:if test="${list.userName==null || list.userName==''}">无</c:if>
									<c:if test="${list.userName!=null || list.userName!=''}">
									${list.userName}
									</c:if>
									</td>
									<td>
									<c:if test="${list.createUid==null || list.createUid==''}">无</c:if>
									<c:if test="${list.createUid!=null || list.createUid!=''}">
									${func:getEditName(list.createUid)}
									</c:if>
									</td>
									<td >
									<c:if test="${list.crateTime==null || list.crateTime==''}">无</c:if>
									<c:if test="${list.crateTime!=null || list.crateTime!=''}">
									${list.crateTime}
									</c:if>
									</td>
									<td >
									<c:if test="${list.picture==null || list.picture==''}">
										无
									</c:if>
									<c:if test="${list.picture!=null && list.picture!=''}">
										有
									</c:if>
									</td>
									<td >
									<c:if test="${list.updateUid==null || list.updateUid==''}">无</c:if>
									<c:if test="${list.updateUid!=null || list.updateUid!=''}">
									${func:getEditName(list.updateUid)}
									</c:if>
									</td>
									<td >
									<c:if test="${list.updateTime==null || list.updateTime==''}">无</c:if>
									<c:if test="${list.updateTime!=null || list.updateTime!=''}">
									${list.updateTime}
									</c:if>
									</td>
									<td >
									<c:if test="${list.status!=null && list.status==1}">上线</c:if>
									<c:if test="${list.status==null || list.status==0}">未上线</c:if>
									</td>
									<td>
									<a href="javascript:void(0)"  onclick="operation(${list.id},1,'操作')">操作</a>|
									<a href="javascript:void(0)"  onclick="operation(${list.id},2,'删除')">删除</a>|
									<c:if test="${list.status==null || list.status=='' || list.status==0 }">
									<a href="javascript:void(0)"  onclick="operation(${list.id},3,'上线')">上线</a>
									</c:if>
									<c:if test="${list.status!=null && list.status!='' && list.status==1}">
									<a href="javascript:void(0)"  onclick="operation(${list.id},4,'下线')">下线</a>
									</c:if>
									|<a href="http://${list.cityCode}.tuanche.com/console/tuanyousay?cityId=${list.cityId }&brandId=${list.brandId }&styleId=${list.styleId}" target="_blank">预览</a>
									</td>
								</tr>
					</c:forEach>
						</c:if>	
					<c:if test="${beanList==null }">
								<tr class="main_info">
									<td colspan="14">没有相关数据</td>
								</tr>
						</c:if>	
					</tbody>
				</table>
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
		</form>

<div id="menuContent" style="display: none; position: absolute;">
	<ul id="treeCity" class="ztree"></ul>
</div>
</body>
</html>