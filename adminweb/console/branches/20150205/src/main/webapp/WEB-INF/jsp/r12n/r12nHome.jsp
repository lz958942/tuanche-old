<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Console团购推荐管理</title>
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
	$(".close").click(function(){
		$(".box ").hide();
	});
	
	if($("#staleId").val()){
		$.post("/json/r12n/getCarStyle",{brandId:$("#brandId").val()},function(data){
			if(data){
				$("#muio option").remove();
				$("#muio").append("<option  selected='selected'  value=''>请选择</option>");
				for(var i=0;i<data.length;i++){
					if(data[i].id==$("#staleId").val()){
			   			$("#muio").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
			   		}else{
			   			$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
			   		}
				}
			}
		},'json');
	}
	});
	function operation(type,id,sta){
		//1 上线，0 下线，-1 删除 -11 批量删除  3 添加页面 12 清空条件 8批量上线 -8 批量下线
		var url="";
		var ids="";
		var sta=true;
		if(type==3){
			//添加
			window.location.href="/r12n/add/r12n";
			return false;
		}else if(type==0){
		//下线
		if(!window.confirm('你确定要下线！')){
			return false;
		}
		}else if(type==1){
		//上线
		if(!window.confirm('你确定要上线！')){
			return false;
		}
		}else if(type==-1){
			if(sta==1){
				alert("上线状态不能删除！");
				return false;
			}
			if(!window.confirm('你确定要删除')){
				return false;
			}
		}else if(type==-11 || type==8 || type==-8){
			var name="";
			if(type > -11){
				if(type==8){
					name="上线";
				}else{
					name="下线";
				}
			}else{
				name="删除";
			}
			if(window.confirm("你确定要批量"+name)){
				if(!($("#boxtable input[sta='scheckbox']:checked").length==0)){
					$("#boxtable input[sta='scheckbox']:checked").each(function(){
						ids+=this.value+",";
						if(type==-11){
							if(($("#sta_"+this.value).text())=="上线"){
								alert("批量删除失败！包含上线内容");
								sta=false;
								return false;
							}
						}
					});
				}else{
					alert("请选择内容！");
					return false;
				}
			}
			//批量删除
		}else if(type==12){
			$("[sts='h']").val("");
			$("#from1").submit();
			return false;
		}
		if(sta){
			$.post("/r12n/UpdateStatusOrbatch",{id:id,type:type,ids:ids},function(result){
				if(result){
					alert("成功！");
					$("#from1").submit();
				}
			},'json');	
		}
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
	
function getStylemy(bid){
	if(bid){
		$.post("/json/r12n/getCarStyle",{brandId:bid},function(data){
			if(data){
				$("#muio option").remove();
				$("#muio").append("<option  selected='selected'  value=''>请选择</option>");
				for(var i=0;i<data.length;i++){
			   	$("#muio").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
				}
			}
		},'json');
	}
}	
</script>
</head>
<body>
<input type="hidden" id="staleId" value="${bean.carstyleId }"> 
<form action="/r12n/home" id="from1" method="post"> 
<div id="div1">
	<table>
		<tr>
		<td>编号：</td>
		<td><input sts='h' onkeyup="this.value=this.value.replace(/[^\d]/g,'') " style="width:220px;height: 30px" type="text" name="id" value="${bean.id }"></td>
		<td> 状态：</td>
		<td>
			<select sts='h' name="status" style="width:227px;height: 35px ">
				<option value="">请选择</option>
				<option value="1" <c:if test="${bean.status==1}">selected='selected'</c:if> >上线</option>
				<option value="0" <c:if test="${bean.status==0}">selected='selected'</c:if> >下线</option>
			</select>
		</td>
		<td> 页面类型：</td>
		<td>
			<select sts='h' name="type" style="width:227px;height: 35px ">
			<option value=""  >请选择</option>
				<option value="3" <c:if test="${bean.type==3 }">selected='selected'</c:if> >所有页面</option>
				<option value="1" <c:if test="${bean.type==1 }">selected='selected'</c:if> >品牌页面</option>
				<option value="2" <c:if test="${bean.type==2 }">selected='selected'</c:if> >车型页面</option>
			</select>
		</tr>
		<tr>
		<td>搜索品牌：</td>
			<td>
					<select sts='h'  id="brandId" name="brandId" style="width:227px;height: 35px " onchange="getStylemy(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option  <c:if test="${bean.brandId ==b.id}">selected="select"</c:if> value="${b.id }">${b.typepinyI }-${b.name}</option>
						</c:forEach>
					</select>
			</td>
			<td>车型名称:</td>
			<td>
			<select sts='h' name="carstyleId" id="muio" style="width:220px;height: 35px ">
					<option value="">请选择车型</option>
			</select>
			</td>
			<td>城市:</td>
			<td>
				<select sts='h'  id="cityId" name="cityId" style="width:220px;height: 35px " onchange="getBrand(this.options[this.options.selectedIndex].value)">
					<option value="" onclick="">请选择</option>
						<c:forEach items="${citys}" var="ct">
							<option  <c:if test="${bean.cityId ==ct.value.id}">selected="select"</c:if> value="${ct.value.id }">${ct.value.name}</option>
						</c:forEach>
					</select>
			</td>
		</tr>
	</table>
	<div>
	<input type="submit" value="搜索">
			<input type="button" value="添加推荐" onclick="operation(3,1,-10)">
			<input type="button" value="批量上线" onclick="operation(8,1,-10)">
			<input type="button" value="批量下线" onclick="operation(-8,1,-10)">
			<input type="button" value="批量删除" onclick="operation(-11,1,-10)">
			<input type="button" value="清空条件" onclick="operation(12,1,-10)">
			</div>
</div>
<div id="div2">
	<table id="boxtable">
		<thead>
			<tr>
			<th><input  onclick="boxMy(1,3)" type="checkbox" id="pcheckbox"></th>
				<th>编号</th>
				<th>城市</th>
				<th>推荐品牌</th>
				<th>推荐车型</th>
				<th>页面类型</th>
				<th>品牌</th>
				<th>车型</th>
				<th>添加人</th>
				<th>状态</th>
				<th style="width: 24px">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${beans}" var="bean">
			<tr id="tr_${bean.id }" name="tbody">
			<td><input id="box_${bean.id }" onclick="boxMy(2,${bean.id })" type="checkbox" sta="scheckbox" value="${bean.id }"></td>
				<td>${bean.id}</td>
				<td>${func:getallCity(bean.cityId==0?-1:bean.cityId)}</td>
				<td>${func:getBrandName(bean.rBrandId)}</td>
				<td>${func:getallStyle(bean.rStyleId)}</td>
				<td>
					<c:if test="${bean.type==1 }">品牌页面</c:if>
					<c:if test="${bean.type==2 }">车型页面</c:if>
					<c:if test="${bean.type==3 }">所有页面</c:if>
				</td>
				<td>${func:getBrandName(bean.brandId)}</td>
				<td>${func:getallStyle(bean.carstyleId)}</td>
				<td>${func:getEditName(bean.createUid)}</td>
				<td>
					<c:if test="${bean.status==0 || bean.status==null}"><span id="sta_${bean.id }">未上线</span></c:if>
					<c:if test="${bean.status!=null && bean.status==1}"><span id="sta_${bean.id }">上线</span></c:if>
				</td>
				<td>
					<c:if test="${bean.status==0 || bean.status==null}">
						<a href="javascript:void()" onclick="operation(1,${bean.id},-10)">上线</a>
					</c:if>
					<c:if test="${bean.status!=null && bean.status==1}">
						<a href="javascript:void()" onclick="operation(0,${bean.id},-10)">下线</a>
					</c:if>
						<a href="javascript:void()" onclick="operation(-1,${bean.id},${bean.status})">删除</a>
				</td>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${beans!=null }">
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
	</c:if>
</div>
</form>
</body>
</html>