<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网问题答案详细</title>
<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css" rel="stylesheet">
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript">
	function bestKevin(id,type2,name){
		//type==1 最佳  type==2  取消最佳  type==3 显示  type==4 隐藏 type==5 删除
		if(id==null ||id=="" ){
			alert("操作无效");
			return false;
		}
		if(type2==null ||""==type2 ){
			alert("操作无效");
			return false;
		}
		if(name==null ||""==name ){
			alert("操作无效");
			return false;
		}
		if(window.confirm('你确定'+name)){
			window.location.href="/wd/best?id="+id+"&type="+type2+"&pid="+$("#questionID").val();
		}
	}
	function kevinSubmit(){
		if($("#questionID").val()==null ||$("#questionID").val()=='' ){
			alert("操作无效！");
			return false;
		}
		if($("#showEmp").val()==null || $("#showEmp").val()=='' ){
			alert("用户不能为空！");
			return false;
		}
		if($("[name='content']").val()==null || $("[name='content']").val()=='' ){
			alert("回复内容不能为空！");
			return false;
		}
		$("#kevinFome").submit();
	}
	function divshow(id){
		var $divs=$("div[sta=showdiv]").hide();
		$("#oladiv_"+id).show();
	}
	function nonediv(id){
		$("#oladiv_"+id).hide();
	}
	function importSubmit(id){
		var xlsKevin=$("#"+id).val();
		if(xlsKevin==''){
			alert("请选择上传的文件！");
			return false;
		}
		
		var pos=xlsKevin.lastIndexOf(".");
		var lastname = xlsKevin.substring(pos,xlsKevin.length); 
		if(lastname!='.xlsx'){
			alert("文件格式不支持，请上传 xlsx 格式的文件");
			return false;
		}
		$("#excelImport").submit();
		}
</script>
</head>
<body>
<div>
					答案导入
	<form  id="excelImport" action="/questionAnswer/answerImport" enctype="multipart/form-data" method="POST" method="post">
	<input id="xls" type="file" name="file"/>
	<input onclick="importSubmit('xls')" type="button" value="导入">
	</form>
	<form   action="/wd/replys" method="post">
	<input type="hidden"  name="id" id="questionID" value="${question.id}">
	<div class="tab-content">
		<div class="list_1 tab-pane active">
			问题：${question.title }
			<table class="table  table-bordered">
					<tr>
					<td>回复内容</td>
					<td>回复人</td>
					<td>回复时间</td>
					<td>审批人</td>
					<td>显示状态</td>
					<td>是否为最佳答案</td>
					<td>显示操作</td>
				</tr>
				<c:forEach items="${answers}" var="answers">
				<tr>
					<td>
					<div  style="width:389px ; height:20px ;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${answers.content }</div>
									<a href="javascript:void(0)" onclick="divshow(${answers.id})"> 详细</a>
									<div  sta="showdiv" id="oladiv_${answers.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:400px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
									 ${answers.content }
									 <div style="text-align:right;">	
									<input type="button" value="关闭" onclick="nonediv(${answers.id})">
									</div>
									</div>
					</td>
					<td>${answers.showEmp }</td>
					<td>${answers.buildTime }</td>
					<td>
					${func:getEditName(answers.updateEmp)}
					</td>
					<td>
					<c:if test="${answers.answerStatus==null }">未知</c:if>
						<c:if test="${answers.answerStatus!=4 }">隐藏</c:if>
						<c:if test="${answers.answerStatus==4}">显示</c:if>
					</td>
					<td><c:if test="${answers.answerAdopt==null }">非最佳</c:if>
						<c:if test="${answers.answerAdopt==0 }">非最佳</c:if>
						<c:if test="${answers.answerAdopt==1 }">最佳</c:if>
					</td>
					<td>
					<c:if test="${answers.answerAdopt==0  || answers.answerAdopt==null }"><a id="adopt_${answers.id }" onclick="bestKevin(${answers.id},1,'最佳')"  href="#">最佳</a></c:if>
					<c:if test="${answers.answerAdopt==1 }"><a  id="adopt_${answers.id }" onclick="bestKevin(${answers.id},2,'取消最佳')" href="#">取消最佳</a></c:if>
					<c:if test="${answers.answerStatus!=4 }"><a id="status2_${answers.id }" onclick="bestKevin(${answers.id},4,'显示')"  href="#">显示</a> </c:if>
					<c:if test="${answers.answerStatus==4 }"><a id="status2_${answers.id }" onclick="bestKevin(${answers.id},3,'隐藏')" href="#">隐藏</a> </c:if>
						<a href="#" onclick="bestKevin(${answers.id},5,'删除')">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
			</form>
			<form  id="kevinFome" action="/wd/saveWd" method="post">
			<input  type="hidden"  name="questionId" id="questionID" value="${question.id}">
			<div>用户：<input id="showEmp" type="text"  name="showEmp" class="input_v_align"/></div>
			<div>回复内容：<textarea name="content" style="vertical-align: top;width: 260px;height: 100px;"></textarea></div>
			<div><button type="button" onclick="kevinSubmit()">新增回复</button></div>
			</form>
		</div>	
		<div class="list_2 tab-pane active">
			
		</div>
	</div>
</div>
</body>
</html>