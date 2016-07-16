<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
	<script type="text/javascript">
	
	function publicAjax(url,dataName,dataArgs,selectId){
		$.ajax({
			   type: "POST",
			   url: url+$.trim(dataName)+"="+dataArgs,
			   dataType:'json',
			   success: function(data){
				   for(i in data) {
				   		$("#"+selectId).append("<option value="+data[i].id+">"+data[i].style+"</option>");
				   }
				   } 
			
			});
	}
	function save(){
		var sta=true;
		$("[sta=p]").each(function(){
			if($(this).val()==null||$(this).val()==""){
				sta=false;
				alert($(this).attr("staName")+"不能为空！");
				return false;
				}
			}
			);
		if(sta){
			if($("#staKevin").val()!=null && $("#staKevin").val()!="" ){
					alert("标题重复！");
					return false;
			}
			$("#newZixunPropertiesForm").submit();
		}
	}
	function titleRepetition(){
		var title=$("#t").val();
		$("#staKevin").val("");
		if(title!=null && title!=""){
			$.post("/json/titleRepetition",{title:title},function(result){
				var t= result.trim();
				if(t!=null && t!=""){
					$("#ttt").text("*");
					if(t=="no"){
						$("#ttt").text("标题重复！");
						$("#staKevin").val(t);
					}
				}
			},'json');
	}
	}
	</script>
</head>
<body>
<div class="ztsx">
	<label><strong>添加新闻</strong></label>
</div>
<input type="hidden" id="staKevin" >
<form action="/xw/saveXW" method="post" style="padding:0 10px 0 10px; margin-top:0px" name="zixun" id="newZixunPropertiesForm">
	<input type="hidden" name="id" value="${bean.id }">
	<div class="borderDiv">
		<table class="table_style table table-bordered" >
			<tr>
				<td>
				<span style="width:100px;padding-right:16px; vertical-align:top">报道时间：</span>
				</td>
				<td>
				<input sta="p"  staName="报道时间"  onclick="SelectDate(this,'yyyy-MM-dd','')" readonly="readonly" style="width: 600px;height: 35px"  type="text" name="reportTime" value="${bean.reportTime}"><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<span style="width:100px;padding-right:16px; vertical-align:top">标题：</span>
				</td>	
				<td>
				<input  onchange="titleRepetition()" id="t" style="width: 600px;height: 35px" sta=p staName="标题" type="text" name="title" value="${bean.title }" maxlength="35"> <font id="ttt" color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
				<span style="width:100px;padding-right:16px; vertical-align:top">URL：</span>
				</td>
				<td>
				<input style="width: 600px;height: 35px"  sta=p staName="url" type="text" name="url" value="${bean.url}" maxlength="200"><font id="ttt" color="red">* 例：http://tuanche.com</font>
				</td>
			</tr>
			<tr>
				<td>
				<span style="width:100px;padding-right:16px; vertical-align:top">报道来源：</span>
				</td>
				<td>
					<input style="width: 600px;height: 35px" type="text" name="reportSource" value="${bean.reportSource}">
				</td>
			</tr>
			<tr>
				<td>
				<span style="width:100px;padding-right:16px; vertical-align:top">审核状态：</span>
				</td>
				<td>
				
					<input type="radio" name="status" checked="checked" value="0">保存
					<input type="radio" name="status"  <c:if test="${bean.status==1 }">checked="checked"</c:if> value="1">上线
				</td>
				<tr>
		</table>
		<div>
			<input type="button" value="保存" onclick="save()">
		</div>
		
	</div>
</form>
</body>
</html>
