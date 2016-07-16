<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
 <style type="text/css">
 	*{
 	
	font-size:12px;
	}
	.blue {
        background:#bcd4ec;  
}
 </style>
<script type="text/javascript">
$(function(){
	$("tr[name=tbody]").hover(function() {
		$(this).addClass("blue");
	}, function() {
		$(this).removeClass("blue");
	});
});
function divshow(id){
	var $divs=$("div[sta=showdiv]").hide();
	$("#oladiv_"+id).show();
}
function nonediv(id){
	if(id){
		$("#oladiv_"+id).hide();
	}else{
		$("#kevinDiv1").hide();
	}
}
function uploadDivShow(sta){
	if(sta){
		if(!($('select[name=topicId]').val())){
			alert("请选择话题！");
			$('select[name=topicId]').focus();
			return false;
		}
		$("#kevinDiv2").show();
	}else{
		$("#kevinDiv2").hide();
	}
}
function operation(id,sta,desc){
	if(sta && desc){
		var ids="";
		var name="";
		var sta=new Number(sta);
		var desc=new Number(desc);
		if(desc==1 || desc==-1){
			//批量上线
			name=sta==2?"批量通过":"批量不通过";
			if(!($("input[sta='scheckbox']:checked").length==0)){
				$("input[sta='scheckbox']:checked").each(function(){
					ids+=this.value+",";
				});
			}else{
				alert("请选择内容！");
				return false;
			}
		}else if(desc==2){
			name=sta==2?"通过":"不通过";
		}
		
		if(window.confirm('你确定要'+name)){
			if(!id){
				id=null;
			}
			if(!ids){
				ids=null;
			}
			$("#hdiv").show();
			$.post("/wd/review/operation",{id:id,ids:ids,auditStatus:sta},function(result){
				if(result){
					alert(""+name+"成功！");
					$("#from1").submit();
				}else{
					alert(""+name+"失败！");
				}
				$("#hdiv").hide();
			},'json');	
		}
	}else{
		alert("操作无效！");
	}
}
function boxMy(type,id){
	if(type==1){
		$("input[sta='scheckbox']").attr("checked",$("#pcheckbox").attr("checked") ?true :false );	
	}else{
		var boxsta=$("#box_"+id).attr("checked");
		var boxs=$("input[sta='scheckbox']");
		var boxsNumber=boxs.length;
		var boxfalse=0;
		boxs.each(function(){
				if($(this).attr("checked")){
					pboxsta=true;
				}else{
					boxfalse=boxfalse+1;
				}
			})
			$("#pcheckbox").attr("checked",boxfalse==0?true:false);
	}
}

function kevinDiv1(id){
	if(id){
		var divId=$("#id_"+id).text();//编号
		var divSort=$("#sort_"+id).text();	//原排序
		var topicID=$("#topicID_"+id).attr("tid");//话题id
		if(divId&&divSort&&topicID){
			$("#divId").text(divId);
			$("#topicID").text(topicID);
			$("#divSort").text(divSort);
			$("#kevinDiv1").show();
		}
	}else{
		alert("操作无效！");
	}
}
function reply(){
	var divId= $("#divId").text();
	var topicID= $("#topicID").text();
	var divSort= $("#divSort").text();
	var sort=$("#divTest").val();
	if(divId&&topicID&&divSort&&sort){
	$.post("/wd/review/updateSort",{id:divId,topicId:topicID,divSort:divSort,sort:sort},function(result){
			if(result){
				alert("操作成功！");
				$("#from1").submit();
			}else{
				alert("操作失败！");
			}
		},'json');
	}else{
		alert("操作无效！");
	}
}

function leadExcel(){
	if(!($('select[name=topicId]').val())){
		alert("请选择话题！");
		$('select[name=topicId]').focus();
		return false;
	}
	var fileName = $("#file1").val(); //文件名称
	var fileExt="";
	if(!fileName){
		alert("请上传文件");
		return false;
	}
		fileExt= fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
		if(!(fileExt=="xls" || fileExt=="xlsx") ){
			alert("请上传正确格式文件");
			return false;
		}
		$("#hintText").text("正在上传。。。");
		$.ajaxFileUpload({
			url:"/json/ht/review/uploadExcel?gid="+$('select[name=topicId]').val(),
			fileElementId:'file1',
			dataType: 'json',
			success: function (data){
				alert("成功！插入"+data.size+"条记录");
				var errorName=data.error;
				alert(errorName==''?"无错误数据":"错误数据："+data.error);
				//window.location.href="/gift/code/home";
				$("#kevinDiv2").hide();
				$("#hdiv").hide();
				$("#hintText").text("");
			},

		});	
}
</script>
</head>
<body>
<div style="height: 5px"></div>
		<form action="/wd/review/list" method="post" style="padding: 0 10px 0 10px; margin-top: 0px;" name="review" id="from1">
			<table>
				<tr>
					<td class="ti">话题</td>
					<td>
					<select name="topicId">
					<option value="">请选择 </option>
						<c:forEach items="${topic }" var="t">
							<option  <c:if test="${bean.auditStatus==t.key}">selected='selected'</c:if> value="${t.key }">${t.value.title }</option>
						</c:forEach>
					</select>
					</td>
					<td class="ti" >状态：</td>
					<td>
						<select name="auditStatus">
							<option value="" >请选择</option>
							<option <c:if test="${bean.auditStatus==1 }">selected='selected'</c:if> value="1">未审核</option>
							<option <c:if test="${bean.auditStatus==2 }">selected='selected'</c:if> value="2">通过</option>
							<option <c:if test="${bean.auditStatus==3 }">selected='selected'</c:if> value="3">未通过</option>
						</select>
					</td>
					<td>
					</td>
					<td>
					<input type="submit" value="搜索" class="btn" />
						<input type="button" value="批量通过" class="btn" onclick="operation(0,2,1)">
						<input type="button" value="批量不通过"  class="btn" onclick="operation(0,3,-1)">
						<input type="button" value="导入评论"  class="btn" onclick="uploadDivShow(1)">
					</td>
				</tr>
				
			</table>
				<table  class="table_style table table-bordered" >
					<tr id="boxtable"  class="attr">
					<th><input  onclick="boxMy(1,3)" type="checkbox" id="pcheckbox"></th>
						<td style="white-space: nowrap;">编号</td>
						<td style="white-space: nowrap;">话题</td>
						<td style="white-space: nowrap;">内容</td>
						<td style="white-space: nowrap;">排序</td>
						<td style="white-space: nowrap;">状态</td>
						<td style="white-space: nowrap;">评论用户</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<tbody align="center" id="dataBody">
					<c:if test="${beans!=null }">
					<c:forEach items="${beans}" var="list">
								<tr name='tbody'>
								<td><input id="box_${list.id }" onclick="boxMy(2,${list.id })" type="checkbox" sta="scheckbox" value="${list.id }"></td>
									<td ><span id="id_${list.id }">${list.id }</span> </td>
									<td><span  id="topicID_${list.id }" tid="${list.topicId}">${func:getTopicId(list.topicId,1)}</span></td>
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
									<td><span id="sort_${list.id}">${list.sort}</span></td>
									<td>
										<c:if test="${list.auditStatus==1 }">
										未审核
									</c:if>
									<c:if test="${list.auditStatus==2 }">
										通过
									</c:if>
									<c:if test="${list.auditStatus==3 }">
										未通过
									</c:if>
									</td>
									<td>${list.userName }</td>
									<td >
									<c:if test="${list.auditStatus==1||list.auditStatus==null}">
									<a href="javascript:void(0)"  onclick="operation(${list.id},2,2)">通过</a>
									<a href="javascript:void(0)"  onclick="operation(${list.id},3,2)">不通过</a>
									</c:if>
									<c:if test="${list.auditStatus==2 }">
									<a href="javascript:void(0)"  onclick="operation(${list.id},3,2)">不通过</a>
									</c:if>
									<c:if test="${list.auditStatus==3 }">
										<a href="javascript:void(0)"  onclick="operation(${list.id},2,2)">通过</a>
									</c:if>
									<a href="javascript:void(0)"  onclick="kevinDiv1(${list.id})">排序</a>
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

<div  id="kevinDiv1"  style="position:fixed;display:none;margin-left: auto;margin-right: auto;z-index:9;height:;width:500px;border:1px solid #ddd;left:210px;top:110px;border-bottom: none !important;background-color:#8F8FBD;">
				<center><font>排序</font><br>
				<font>话题编号：</font><font id="topicID"></font></br>
				<font>编号：</font><font id="divId"></font></br>
				<font>原排序：</font><font id="divSort"></font></br>
				<font>排序：</font><input type="text" id="divTest">
			    <input type="button" value="提交" onclick="reply()"> <input onclick="nonediv(0)" type="button" value="关闭">
			</center>
</div>
<div  id="kevinDiv2"  style="display:none; position: fixed; margin-left: auto; margin-right: auto; z-index: 9; width: 500px; border-top: 1px solid rgb(221, 221, 221); border-right: 1px solid rgb(221, 221, 221); border-left: 1px solid rgb(221, 221, 221); -moz-border-top-colors: none; -moz-border-right-colors: none; -moz-border-bottom-colors: none; -moz-border-left-colors: none; border-image: none; left: 210px; top: 167px; border-bottom: medium none ! important; background-color: rgb(143, 143, 189); height: 120px;z-index:100;">
				<input type="hidden" id="replyID">
				<center><font>导入评论</font></center><br>
				<input type="hidden" id="tmpValue">
				<center><input type="file" name="file1" id="file1"><span id="hintText"></span></center><br>
					<center>
					<input type="button" value="导入" onclick="leadExcel()"> 
					<input id="kevinButtonClose" onclick="uploadDivShow(0)" type="button" value="关闭">
					</center>
</div>
<div id="hdiv" style="background-color: rgba(0, 0, 0, 0.5); top: 0%; left: 0%; width: 100%; height: 100%; z-index: 1001; position: fixed;display: none;"></div>
</body>
</html>