<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团车网答案详细</title>
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
			window.location.href="/wd/best?id="+id+"&type="+type2;
		}
	}
	function divshow(id){
		var $divs=$("div[sta=showdiv]").hide();
		$("#oladiv_"+id).show();
	}
	function nonediv(id){
		$("#oladiv_"+id).hide();
	}
	function jsonGetTwoByOne(pid){
		 $("#secondkindId option").remove();
		 $("#secondkindId").append("<option  selected='select' value=''>--请选择--</option>");
		if(pid!=null){
	$.post("/json/getTwoByOne",{parentId:pid},function(result){
				if(result!=null){
					 $("#secondkindId option").remove();
					   $("#secondkindId").append("<option  selected='select' value=''>--请选择--</option>");
					   for(i in result) {
					   		$("#secondkindId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
					   }
				}
			},'json');
		}
	}
	function selectAll() {
		$("input[name='ids']").attr("checked",$("#allCheck").attr("checked") ? true : false);
	}
	function online(){
		var checkeds=$("[name=ids]:checked");
		if(checkeds.length==0){
			alert("请选择上线答案！");
			return false;
		}
		var pageNo=${page.pageNo};
		if(window.confirm('你确定批量上线?')){
			$.post('/wd/updatestatus?type=4',checkeds,function(result){
				if(pageNo==1){
					$("#downexcel").submit();
					return;
					}
				$("#Number1").attr('value',pageNo);
				checkNumber(pageNo);
			},'json');
		}
	}
	function deleteAnswers(){
		var checkeds=$("[name=ids]:checked");
		if(checkeds.length==0){
			alert("请选择删除答案！");
			return false;
		}
		var pageNo=${page.pageNo};
		if(window.confirm('你确定批量删除?')){
			$.post('/wd/updatestatus?type=0',checkeds,function(result){
				if(pageNo==1){
					$("#downexcel").submit();
					return;
					}
				$("#Number1").attr('value',pageNo);
				checkNumber(pageNo);
			},'json');
		}
	}
</script>
</head>
<body>
<div>
	<!-- <ul class="nav nav-tabs list-inline tab">
		<li data-toggle="tab"><a href="/questionAnswer/oneKind">一级分类</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toUpdateOne">新建一级分类</a></li>
						<li style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/twoKind">二级分类</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/questionAnswer/toUpdateTwo">新建二级分类</a></li>
					</ul> -->
					
	<form   action="/wd/answerList" method="post" id='downexcel'>
	<div class="tab-content">
		<table style="width:100%;text-align:center;">
		<tr>
		<td>问题：
		<input type="text" name="questionAnswer" value="${answer.questionAnswer }" style="width:320px">
			
				<%-- <select name="answerStatus">
					<option selected="selected" value="">请选择</option>
					<option value="4" <c:if test="${answer.answerStatus==4 }">selected='selected'</c:if>>上线</option>
					<option value="2" <c:if test="${answer.answerStatus==0 }">selected='selected'</c:if>>删除</option>
				</select> --%>
				&nbsp;<input type="submit" value="搜索">
			</td>
		</tr>
	</table>
		<div class="list_1 tab-pane active">
			<table class="table  table-bordered">
					<tr>
					<td><input  id="allCheck" type="checkbox" onclick="selectAll()"></td>
					<td>答案</td>
					<td style="width: 200px;">问题</td>
					<td>回复人</td>
					<td>生成时间</td>
					<!-- <td>审批人</td>
					<td style="width:40px;">是否审核</td> -->
					<td>操作</td>
				</tr>
				<c:forEach items="${answers}" var="answers">
				<tr>
					<td><input type="checkbox" name='ids' value='${answers.id}'/></td>
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
					<td>${answers.questionAnswer }</td>					
					<td>${answers.showEmp }</td>
					<td>${answers.updateTime }</td>
					<%-- <td>
					${func:getEditName(answers.updateEmp)}
					</td>
					<td>
					<c:if test="${answers.answerStatus==null }">未知</c:if>
						<c:if test="${answers.answerStatus!=4 }">未审核</c:if>
						<c:if test="${answers.answerStatus==4}">上线</c:if>
					</td> --%>
					<td>
					<%-- <c:if test="${answers.answerAdopt==0  || answers.answerAdopt==null }"><a id="adopt_${answers.id }" onclick="bestKevin(${answers.id},1,'最佳')"  href="#">最佳</a></c:if> --%>
					<%-- <c:if test="${answers.answerAdopt==1 }"><a  id="adopt_${answers.id }" onclick="bestKevin(${answers.id},2,'取消最佳')" href="#">取消最佳</a></c:if> --%>
					<c:if test="${answers.answerStatus!=4 }"><a id="status2_${answers.id }" onclick="bestKevin(${answers.id},4,'上线')"  href="#">审核通过</a> </c:if>
					<%-- <c:if test="${answers.answerStatus==4 }"><a id="status2_${answers.id }" onclick="bestKevin(${answers.id},3,'隐藏')" href="#">隐藏</a> </c:if> --%>
						<a href="#" onclick="bestKevin(${answers.id},5,'删除')">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<input type="button" value="审核通过" onclick="javascript:online();">
			<input type="button" value="删除" onclick="javascript:deleteAnswers();">
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
			</form>
		</div>	
		<div class="list_2 tab-pane active">
		</div>
	</div>
</div>
</body>
</html>