<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consol兑换后台</title>
<style type="text/css">
body{font-family:"Segoe UI", Frutiger,Tahoma,Helvetica,"Helvetica Neue", Arial, sans-serif;font-size:12px;}
#div2 table{border-collapse:collapse;width: 100%;height: 100%}
#div2 td, th{text-align:center;border:1px solid #ddd;padding:2px 5px;}
#div2 td, th{font-size:12px;padding:2px;}
#div2 th{background-color:#f4f4f4;} 
#div2 table{float:left;margin:5px 0 0;}
.blue {
        background:#bcd4ec;  
}
.box h2{height:25px;font-size:14px;background-color:#3366cc;position:relative;padding-left:10px;line-height:25px;color:#fff;}
.box h2 a{position:absolute;right:5px;font-size:12px;color:#fff;}
</style>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#div2 tr[name=tbody]").hover(function() {
			$(this).addClass("blue");
		}, function() {
			$(this).removeClass("blue");
		});
	$("img[name=imgMy]").hover(function() {
		$("[sta=showdiv]").hide();
		$("#show_"+$(this).attr("id")).show();
	}, function() {
		$("[sta=showdiv]").hide();
	});
	$(".close").click(function(){
		$(".box ").hide();
	});
	
	
	});
</script>
</head>
<body>
<form action="/gift/home" id="from1" method="post">
<div id="div1">
	<table>
		<tr>
		<td>编号：</td>
		<td><input maxlength="8" sts='h' onkeyup="this.value=this.value.replace(/[^\d]/g,'') " style="width:220px;height: 30px" type="text" name="id" value="${bean.id }"></td>
		<td>兑换方式：</td>
			<td>
					<select sts='h'  name="exchangeType" style="width:220px;height: 35px ">
					<option value="">请选择</option>
						<c:forEach items="${exchangeType}" var="ct">
							<option <c:if test="${bean.exchangeType ==ct.key}">selected="select"</c:if> value="${ct.key }">${ct.value.name}</option>
						</c:forEach>
					</select>
			</td>
			<td>礼品标题：</td>
			<td>
			<input maxlength="50" sts='h' style="width:220px;height: 30px" type="text" name="giftTitle" value="${bean.giftTitle}">
			</td>
		</tr>
		<tr>
			<td>分类：</td>
			<td>
				<select sts='h' name=giftClass style="width:220px;height: 35px ">
				<option value="">请选择</option>
					<c:forEach items="${giftClass}" var="c">
							<option <c:if test="${c.key==bean.giftClass}">selected="selected"</c:if>  value="${c.key}">${ c.value.name}</option>
						</c:forEach>
				</select>
			</td>
			<td>来源：</td>
			<td>
				<select sts='h' name=giftSource style="width:220px;height: 35px ">
				<option value="">请选择</option>
					<c:forEach items="${giftSource}" var="c">
							<option <c:if test="${c.key==bean.giftSource}">selected="selected"</c:if>  value="${c.key}">${ c.value.name}</option>
						</c:forEach>
				</select>
			</td>
			<td>状态：</td>
			<td>
				<select name="giftStatus" style="width:220px;height: 35px ">
					<option value="">请选择</option>
					<option value="0" <c:if test="${bean.giftStatus==0 }">selected='selected'</c:if> >未上线</option>
					<option value="1" <c:if test="${bean.giftStatus==1 }">selected='selected'</c:if>>已上线</option>
				</select>
			</td>
		</tr>
	</table>
	<div>
	<input type="submit" value="搜索">
			<input type="button" value="添加礼品" onclick="addPic(6)">
			<input type="button" value="批量上线" onclick="addPic(1)">
			<input type="button" value="批量下线" onclick="addPic(0)">
			<input type="button" value="清空条件" onclick="addPic(5)">
			</div>
</div>
<div id="div2">
	<table id="boxtable">
		<thead>
			<tr>
				<th><input  onclick="boxMy(1,3)" type="checkbox" id="pcheckbox"></th>
				<th>编号</th>
				<th>数量</th>
				<th>剩余数量</th>
				<th>分类</th>
				<th>来源</th>
				<th>兑换方式</th>
				<th>礼品标题—简介</th>
				<th>创建时间</th>
				<th>展示时间(天)</th>
				<th>图片</th>
				<th>状态</th>
				<th style="width: 24px">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${beans!=null }">
		<c:forEach items="${beans}" var="bean">
			<tr id="tr_${bean.id }" name="tbody">
				<td><input id="box_${bean.id }" onclick="boxMy(2,${bean.id })" type="checkbox" sta="scheckbox" value="${bean.id }"></td>
				<td>${bean.id}</td>
				<td>${bean.number}</td>
				<td>${bean.remainNumber}</td>
				<td>${func:getGiftById(bean.giftClass,3)}</td>
				<td>${func:getGiftById(bean.giftSource,2)}</td>
				<td>${func:getGiftById(bean.exchangeType,1) }</td>
				<td>
				<c:if test="${bean.giftDesc==null }">
					无
				</c:if>
				<c:if test="${bean.giftDesc!=null }">
									<a href="javascript:void(0)" onclick="divshow(${bean.id})"> 详细</a>
									<div  sta="showdiv" id="oladiv_${bean.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
									<h3>${bean.giftTitle==null?'无':bean.giftTitle}</h3>
									${bean.giftDesc}
									 <div style="text-align:right;">	
									<input type="button" value="关闭" onclick="nonediv(${bean.id})">
									</div>
									</div>
				</c:if>
				</td>
				<td>${bean.showTime}</td>
				<td>
				${bean.showDate }
				</td>
				<td>
					<c:if test="${bean.imgUrl==null || bean.imgUrl=='' }">无</c:if>
					
					<c:if test="${bean.imgUrl!=null && bean.imgUrl!='' }"><img  name="imgMy" id="${bean.id }" style="width:30px;height: 30px" src="http://pic.tuanche.com/recpic/${bean.imgUrl }">
					
					<div  sta="showdiv"  id="show_${bean.id}" style="display:none;position:fixed;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;left: 184px; top: 72px;">
						<img style="width:400px;height: 450px" src="http://pic.tuanche.com/recpic/${bean.imgUrl }">
					</div>
					</c:if>				
				</td>
				<td>
					<c:if test="${bean.giftStatus==0 }">未上线</c:if>
					<c:if test="${bean.giftStatus==1 }">上线</c:if>
				</td>
				<td>
				<c:if test="${bean.giftStatus==0 }"><a href="#" onclick="giftOperation(${bean.id},1)" >上线</a></c:if>
				<c:if test="${bean.giftStatus==1 }"><a href="#" onclick="giftOperation(${bean.id},0)" >下线</a></c:if>
					<a href="/gift/saveGift.do?id=${bean.id }" >修改</a>
					<a href="#" onclick="giftOperation(${bean.id},-1)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</c:if>
		</tbody>
	</table>
	
	<c:if test="${beans!=null}">
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
	</c:if>
</div>
</form>
<script type="text/javascript">

function divshow(id){
	var $divs=$("div[sta=showdiv]").hide();
	$("#oladiv_"+id).show();
}
function nonediv(id){
	$("#oladiv_"+id).hide();
}
function uploadFile(){
	$.ajaxFileUpload({
		url:"/json/uploadFile?delSrc="+$("#delSrc").val(),
		fileElementId:'boxFile',
		dataType: 'json',
		success: function (data){
			if(data==null){
			alert("上传失败！");
			return false;
			}
			var args=data.trim();
			$("#src").val(args);
			$("#boxImg").attr("src",args);
			var delSrc=args.substring(args.lastIndexOf("/"),args.length);
			if(delSrc!=null && delSrc!=""){
				$("#delSrc").val(delSrc);
			}
			alert("上传成功");
		},
	});
}
function boxMy(type,id){
	if(type==1){
		$("#boxtable input[sta='scheckbox']").attr("checked",$("#pcheckbox").attr("checked") ?true :false );	
	}else{
		var boxsta=$("#box_"+id).attr("checked");
		var boxs=$("#boxtable input[sta='scheckbox']");
		var boxsNumber=boxs.length;
		var boxfalse=0;
		boxs.each(function(){
				if($(this).attr("checked")){
					pboxsta=true;
				}else{
					boxfalse=boxfalse+1;
				}
			});
			$("#pcheckbox").attr("checked",boxfalse==0?true:false);
	}
}
function addPic(type){
	var name=type==0?"下线":"上线";
	if(type==6){
		//新添
		window.location.href="/gift/saveGift.do";
	}else if(type==0 || type==1){
		//上线 //4下线
		var ids="";
		$("#boxtable input[sta='scheckbox']").each(function(){
			if($(this).attr("checked")){
				ids+=$(this).val()+",";
			}
		});
		if(ids){
			$.post("/gift/updateStatus.do",{ids:ids,type:type},function(result){
				if(result){
					alert("批量"+name+"成功！");
					$("#from1").submit();
				}else{
					alert("批量失败！");
				}
			},'json');
		}else{
			alert("请选择"+name+"内容！");
			return false;
		}
	}else if(type==5){
		//清空搜索条件
		$("[sts=h]").val("");
		$("#from1").submit();
	}
}
function  giftOperation(id,type){
	var name="";
	if(type==0){name="下线";}else if(type==1){name="上线";}else if(type==-1){name="删除";}
	if(name&&id){
		if(window.confirm('你确定要'+name)){
			window.location.href="/gift/updateStatus?id="+id+"&type="+type;
		}
	}else{
		alert("操作无效！");
		return false;
	}
}
</script>
</body>
</html>