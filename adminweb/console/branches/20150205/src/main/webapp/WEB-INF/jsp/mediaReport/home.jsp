<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" /> 
 <style type="text/css">
 	*{
 	
	font-size:12px;
	}
.table th, .table td{
padding:5px;
text-align:center;
vertical-align:middle;
}
.table td a{
display:inline-block;
}
 </style>
<script type="text/javascript">
	function selectAll() {
		$("input[name='ids']").attr("checked",$("#allCheck").attr("checked") ? true : false);
	}
	function operation(id,type,name){
		if(id!=null && type!=null && name != null){
			//type==1 上线，-1删除，0下线
			var url="";
			if(type==1){
				url="/xw/operation?id="+id+"&type=1";
			}else if(type==0){
				url="/xw/operation?id="+id+"&type=0";
			}else if(type==-1){
				url="/xw/operation?id="+id+"&type=-1";
			}
			if(window.confirm('你确定'+name+'！')){
				$.post(url,function(result){
					$("#reviewForm").submit();
				},'json');
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
			$.post('/xw/operation?ids='+ids2+"&type="+type,function(result){
				$("#reviewForm").submit();
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
</head>
<body>
<div style="height: 5px"></div>
		<form action="/xw/list" method="post" style="padding: 0 10px 0 10px; margin-top: 0px;" name="review" id="reviewForm">
			<table>
				<tr class="lh28">
					<td class="ti">编号：&nbsp;</td>
					<td><input onkeyup="this.value=this.value.replace(/[^\d]/g,'') " type="text"maxlength="10" id="id" name="id" style="width:200px; height:30px" value="${bean.id}" /></td>
					<td class="ti">标题：&nbsp;</td>
					<td><input type="text" name="title" style="width: 220px ;height: 30px;"value="${bean.title}" id="titleid" /></td>
					<td class="ti">状态:</td>
					<td>
					<select style="width: 200px" name="status">
						<option selected="selected" value="">请选择</option>
						<option <c:if test="${bean.status==0}">selected="selected"</c:if> value="0">未上线</option>
						<option  <c:if test="${bean.status==1}">selected="selected"</c:if> value="1">上线</option>
					</select>
				</tr>
				<tr>
					<td>报道时间:</td>
					<td><input onclick="SelectDate(this,'yyyy-MM-dd','')" readonly="readonly" value="${bean.reportTime }"    name="reportTime" style="width: 90px;height: 28px" type="text">--
					<input onclick="SelectDate(this,'yyyy-MM-dd','')" readonly="readonly"     value="${bean.reportTimeNnd }" name="reportTimeNnd" style="width: 90px;height: 28px" type="text">
					<td>
					<input type="submit" value="搜索" class="btn" />
					</td>
					<td>
						<a href="#" class="btn" onclick="batchOnLine(0,'下线')">批量下线</a>
						<a href="#" class="btn"  onclick="batchOnLine(1,'上线')">批量上线</a>
						<a href="#" class="btn" onclick="batchOnLine(-1,'删除')">批量删除</a>
						<a href="/xw/saveXW.do" class="btn">添加新闻</a>
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
						<td style="white-space: nowrap;">标题</td>
						<td style="white-space: nowrap;">url</td>
						<td style="white-space: nowrap;">报道来源</td>
						<td style="white-space: nowrap;">报道时间</td>
						<td style="white-space: nowrap;">创建人</td>
						<td style="white-space: nowrap;">创建时间</td>
						<td style="white-space: nowrap;">发布人</td>
						<td style="white-space: nowrap;">发布时间</td>
						<td style="white-space: nowrap;">操作人</td>
						<td style="white-space: nowrap;">操作时间</td>
						<td style="white-space: nowrap;">状态</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<tbody align="center" id="dataBody">
					<c:if test="${beans!=null }">
					<c:forEach items="${beans}" var="list">
								<tr>
								<td><input type="checkbox" name='ids' value='${list.id}'/></td>
									<td >${list.id }</td>
									<td>
									<c:if test="${list.title==null ||list.title==''}">无</c:if> 
									<c:if test="${list.title!=null && list.title!=''}">
									<div  style="width:90px ; height:20px ;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${list.title}</div>
									<a href="javascript:void(0)" onclick="divshow(${list.id})"> 详细</a>
									<div  sta="showdiv" id="oladiv_${list.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
									 ${list.title}
									 <div style="text-align:right;">	
									<input type="button" value="关闭" onclick="nonediv(${list.id})">
									</div>
									</div>
									</c:if> 
									
									
									</td>
									<td >
									<c:if test="${list.url==null}">无</c:if>
									<c:if test="${list.url !=null}">${list.url }</c:if>
									</td>
									<td >
									<c:if test="${list.reportSource==null || list.reportSource==''}">
										无
									</c:if>
									<c:if test="${list.reportSource!=null }">
										${list.reportSource }
									</c:if>
									</td>
									<td>
									<c:if test="${list.reportTimeCope==null  || ''==list.reportTimeCope}">
										无
									</c:if>
									<c:if test="${list.reportTimeCope!=null  && ''!=list.reportTimeCope}">
										${list.reportTimeCope}
									</c:if>
									</td>
									<td>
									<c:if test="${list.createUid==null || list.createUid==''}">无</c:if>
									<c:if test="${list.createUid!=null || list.createUid!=''}">
									${func:getEditName(list.createUid)}
									</c:if>
									</td>
									<td >
									<c:if test="${list.createTimeCope==null || list.createTimeCope==''}">无</c:if>
									<c:if test="${list.createTimeCope!=null || list.createTimeCope!=''}">
									${list.createTimeCope}
									</c:if>
									</td>
									<td >
									<c:if test="${list.publishUid==null || list.publishUid==''}">无</c:if>
									<c:if test="${list.publishUid!=null || list.publishUid!=''}">
									${func:getEditName(list.publishUid)}
									</c:if>
									</td>
									<td >
									<c:if test="${list.updateTimeCope==null || list.updateTimeCope==''}">无</c:if>
									<c:if test="${list.updateTimeCope!=null || list.updateTimeCope!=''}">
									${list.updateTimeCope}
									</c:if>
									</td>
									<td >
									<c:if test="${list.updateUid==null || list.updateUid==''}">无</c:if>
									<c:if test="${list.updateUid!=null || list.updateUid!=''}">
									${func:getEditName(list.updateUid)}
									</c:if>
									</td>
									<td >
									<c:if test="${list.updateTimeCope==null || list.updateTimeCope==''}">无</c:if>
									<c:if test="${list.updateTimeCope!=null || list.updateTimeCope!=''}">
									${list.updateTimeCope}
									</c:if>
									</td>
									<td >
									<c:if test="${list.status!=null && list.status==1}">上线</c:if>
									<c:if test="${list.status==null || list.status==0}">未上线</c:if>
									</td>
									<td>
									<a href="/xw/saveXW.do?id=${list.id }" >操作</a>
									<a href="javascript:void(0)"  onclick="operation(${list.id},-1,'删除')">删除</a>
									<c:if test="${list.status==null || list.status=='' || list.status==0 }">
									<a href="javascript:void(0)"  onclick="operation(${list.id},1,'上线')">上线</a>
									</c:if>
									<c:if test="${list.status!=null && list.status!='' && list.status==1}">
									<a href="javascript:void(0)"  onclick="operation(${list.id},0,'下线')">下线</a>
									</c:if>
									</td>
								</tr>
					</c:forEach>
						</c:if>	
					<c:if test="${beans==null }">
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