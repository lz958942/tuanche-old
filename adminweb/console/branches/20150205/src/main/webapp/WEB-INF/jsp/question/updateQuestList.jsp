<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>团车网问答详细</title>
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

<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>

<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
</head>
<body>
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="#">问题列表</a></li>
					</ul>
			   </div>
			</td>
		</tr>
</table>
	<form action="/questionAnswer/toQuestionList" method="post" name="question" id='downexcel'>
	<div id="query">
		<input type="hidden" id='secondId' value='${question.secondkindId}'/>
		问题id：<input type='text' id='id' name='id' value='${question.id}' style="width:50px"/>
		问题标题：<input type='text' id='title' name='title' value='${question.title}' />
		问题来源：<select  name='firstkindId' onchange="jsonGetTwoByOne(this.options[this.options.selectedIndex].value)" id='firstkindId'>
				<option value=''>--请选择--</option>
				<c:forEach items="${oneKinds}" var="kind">
				<option value='${kind.id}'<c:if test="${kind.id==question.firstkindId}">selected='selected'</c:if>>${kind.name}</option>
				</c:forEach>
				</select>
				<select id="secondkindId" name='secondkindId'>
				<option value=''>--请选择--</option>
				</select>
		是否解决：<select name='isResolve'>
				<option value=''>--请选择--</option>
				<option value='0'<c:if test="${question.isResolve==0}">selected='selected'</c:if>>未解决</option>
				<option value='1'<c:if test="${question.isResolve==1}">selected='selected'</c:if>>已解决</option>
				</select>
		是否推荐：<select name='isRecom'>
				<option value=''>--请选择--</option>
				<option value='0'<c:if test="${question.isRecom==0}">selected='selected'</c:if>>未推荐</option>
				<option value='1'<c:if test="${question.isRecom==1}">selected='selected'</c:if>>推荐</option>
				</select>
				<br>
		是否回复：<select name='reply'>
				<option value=''>--请选择--</option>
				<option value='0'<c:if test="${question.reply==0}">selected='selected'</c:if>>未回复</option>
				<option value='1'<c:if test="${question.reply>0}">selected='selected'</c:if>>已回复</option>
				</select>		
		管理状态：<select name='questionStatus'>
				<option value=''>--请选择--</option>
				<option value='0'<c:if test="${question.questionStatus==0}">selected='selected'</c:if>>删除</option>
				<option value='2'<c:if test="${question.questionStatus==2}">selected='selected'</c:if>>未审核</option>
				<option value='4'<c:if test="${question.questionStatus==4}">selected='selected'</c:if>>上线</option>
				 </select>
		<input type="submit" value="查询" >
		<input type="button" value="导出" onclick="javascript:download()">
	</div>
	<table class="table table-bordered">
		<tr>
			<td><input  id="allCheck" type="checkbox" onclick="selectAll()"></td>
			<td>会员账号</td>
			<td>问题内容</td>
			<td>问题分类</td>
			<td>问题子分类</td>
			<td>回复</td>
			<td>品牌</td>
			<td>车型</td>
			<td>操作人</td>
			<td>操作时间</td>
			<td>回复状态</td>
			<td>状态</td>
			<td>推荐状态</td>
			<td>操作</td>
		</tr>
		 <c:forEach items="${questions}" var="question">
		<tr>
			<td><input type="checkbox" name='ids' value='${question.id}'/></td>
			<td style="width:80px;">${question.showEmp}</td>
			<td><div  style="width:180px ; height:20px ;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${question.title}</div>
				<a href="javascript:void(0)" onclick="divshow(${question.id})" title="${question.title}"> 详细</a>
				<div  sta="showdiv" id="oladiv_${question.id}" style="display:none;position:absolute;border:1px solid blue;background-color:#fff;width:520px;box-shadow: 0 0 5px #999; border: 1px solid #f9f9f9;">
				<input type="text" id="content${question.id}" value="${question.content}" style="width: 500px;height: 30px;"/> 
				<div style="text-align:right;">		
				<input type="button" value="关闭" onclick="nonediv(${question.id})">
				<input type="button" value="更改" onclick="javascript:contentUpdate(${question.id})">
				<input type="button" value="删除" onclick="javascript:contentDelete(${question.id})">
				</div>
				</div>
			</td>
			<td id="tf${question.id}">
				<select  name='tfirstkindId' onchange="jsonGetTwoByOneTb(this.options[this.options.selectedIndex].value,${question.id})" id='tfirstkindId' style="width:80px;<c:if test="${empty question.firstkindId }">color:red;</c:if>">
				<option value=""  <c:if test="${0==question.firstkindId }">selected="selected"</c:if> >未指定</option>
				<c:forEach items="${oneKinds}" var="kind">
				<option value='${kind.id}'<c:if test="${kind.id==question.firstkindId}">selected='selected'</c:if>>${kind.name}</option>
				</c:forEach>
				</select>
			</td>
			<td id="td${question.id}">
				<select id="tsecondkindId" name='tsecondkindId' style="width:80px ;<c:if test="${empty question.secondkindId }">color:red;</c:if>">
					<option value='${question.secondkindId}'>${func:getquestionKind(question.secondkindId)}</option>
				</select>
			</td>
			<td style="width:10px;">${question.reply}</td>
			<td id="tb${question.id}">
				 <select name="tbrandId" style="width: 80px;<c:if test="${0 == question.brandId }">color:red;</c:if>" id="pid" onchange="selectCar(this.options[this.options.selectedIndex].value,${question.id})">
				 	<option value="0"  <c:if test="${0==question.brandId }">selected="selected"</c:if> >未指定</option>
                	<c:forEach items="${pBrands}" var="pb">
						<option value="${pb.id }"  <c:if test="${pb.id==question.brandId }">selected="selected"</c:if> >${pb.typepinyI} ${pb.name}</option>
					</c:forEach>
                </select>
			</td>
			<td id="tc${question.id}">
				<select name="tcarstyleId" id="tkeywordid" style="width:80px;<c:if test="${0==question.carstyleId }">color:red;</c:if>" >
					<option value='${question.carstyleId}'>${func:getCarstyleName(question.carstyleId)}</option>
				</select>
			</td>
			<td>${func:getEditName(question.updateEmp)}</td>
			<td style="width:50px;">${question.updateTime}</td>
			<td>
			<c:if test="${question.reply==0}">未回复</c:if>
			<c:if test="${question.reply>0}">已回复</c:if>
			</td>
			<td>
			<c:if test="${question.questionStatus==0}">删除</c:if>
			<c:if test="${question.questionStatus==1}">下线</c:if>
			<%-- <c:if test="${question.questionStatus==2}">未上线</c:if> --%>
			<c:if test="${question.questionStatus==2}">未审核</c:if>
			<c:if test="${question.questionStatus==4}">上线</c:if>
			</td>
			<td>
			<c:if test="${question.isRecom==0}">未推荐</c:if>
			<c:if test="${question.isRecom==1}">推荐</c:if>
			</td>
			<td>
				<a href="/wd/replys?id=${question.id}">回复</a> 
				<c:if test="${question.questionStatus!=0}">
				<a href="javascript:void(0)" onclick="deleteOne(${question.id})">删除</a> 
				</c:if>
				<c:if test="${question.isRecom==0}">
				<a href="javascript:void(0)" onclick="recom(${question.id})">推荐</a>
				</c:if>
				<c:if test="${question.isRecom==1}">
				<a href="javascript:void(0)" onclick="norecom(${question.id})">取消推荐</a>
				</c:if>
				<a href="javascript:void(0)" onclick="modify(${question.id})">提交修改</a>
			</td>
		</tr>
		</c:forEach>
	</table>
<!-- 批量选择 -->
	<select  name='firstkindId2' onchange="jsonGetTwoByOne2(this.options[this.options.selectedIndex].value)" id='firstkindId2'>
		<option value=''>--请选择--</option>
		<c:forEach items="${oneKinds}" var="kind">
		<option value='${kind.id}'>${kind.name}</option>
		</c:forEach>
	</select>
		<select id="secondkindId2" name='secondkindId2'>
		<option value=''>--请选择--</option>
	</select>
	<select name="tbrandId2" id="tbrandId2" style="width: 80px;" id="pid" onchange="selectCar2(this.options[this.options.selectedIndex].value)">
		 	<option value="0">未指定</option>
              	<c:forEach items="${pBrands}" var="pb">
				<option value="${pb.id }">${pb.typepinyI} ${pb.name}</option>
			</c:forEach>
    </select>
	<select name="tcarstyleId2" id="tcarstyleId2" style="width:80px;" >
		<option value="0">--请选择--</option>
	</select>

	<div class="page_and_btn" style="text-align: center;">
	<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){ 
	$("#xls").change(function(){
		exam();
	}) 
	var pid=$("#firstkindId").val();
	if(pid!=null && ""!=pid){
		 $("#secondkindId option").remove();
		 $.ajax({
			   type: "POST",
			   url: "/json/getTwoByOne",
			   dataType:'json',
			   data: {
				   'parentId':pid
			   },
			   success: function(data){
				 $("#secondkindId").val("");
				 $("#secondkindId").append("<option value=''>--请选择--</option>");
				   for(i in data) {
				   		if(data[i].id==$("#secondId").val()){
				   			$("#secondkindId").append("<option  selected='selected'  value="+data[i].id+">"+data[i].name+"</option>");
				   		}else{
				   			$("#secondkindId").append("<option  value="+data[i].id+">"+data[i].name+"</option>");
				   		}
				   }
				   } 
			
			});
	}
	　　}); 
function recom(id){
	var pageNo=${page.pageNo};
	if(window.confirm('你确定推荐?')){
		$.post('/questionAnswer/recomQuestion?id='+id+'&recom=1',function(result){
			if(pageNo==1){
				$("#downexcel").submit();
				return;
				}
			$("#Number1").attr('value',pageNo);
			checkNumber(pageNo);
		},'json');
	}
}
function norecom(id){
	var pageNo=${page.pageNo};
	if(window.confirm('你确定取消推荐?')){
		$.post('/questionAnswer/recomQuestion?id='+id+'&recom=0',function(result){
			if(pageNo==1){
				$("#downexcel").submit();
				return;
				}
			$("#Number1").attr('value',pageNo);
			checkNumber(pageNo);
		},'json');
	}
}
function deleteOne(id){
	var pageNo=${page.pageNo};
	if(window.confirm('你确定删除?')){
		$.post('/questionAnswer/deleteOneQuest?id='+id,function(result){
			if(pageNo==1){
				$("#downexcel").submit();
				return;
				}
			$("#Number1").attr('value',pageNo);
			checkNumber(pageNo);
		},'json');
	}
}
function download(){
	$("#downexcel").attr('action',"/questionAnswer/download");
	$("#downexcel").submit();
	$("#downexcel").attr('action',"/questionAnswer/toQuestionList");
}
function line(){
	var checkeds=$("[name=ids]:checked");
	if(checkeds.length==0){
		alert("请选择下线问题！");
		return false;
	}
	var pageNo=${page.pageNo};
	if(window.confirm('你确定批量下线?')){
		$.post('/questionAnswer/updatestatus?type=2',checkeds,function(result){
			if(pageNo==1){
				$("#downexcel").submit();
				return;
				}
			$("#Number1").attr('value',pageNo);
			checkNumber(pageNo);
		},'json');
	}
}
function deleteQuests(){
	var checkeds=$("[name=ids]:checked");
	if(checkeds.length==0){
		alert("请选择删除问题！");
		return false;
	}
	var pageNo=${page.pageNo};
	if(window.confirm('你确定批量删除?')){
		$.post('/questionAnswer/updatestatus?type=0',checkeds,function(result){
			if(pageNo==1){
				$("#downexcel").submit();
				return;
				}
			$("#Number1").attr('value',pageNo);
			checkNumber(pageNo);
		},'json');
	}
}
function online(){
	var checkeds=$("[name=ids]:checked");
	if(checkeds.length==0){
		alert("请选择上线问题！");
		return false;
	}
	var pageNo=${page.pageNo};
	if(window.confirm('你确定批量上线?')){
		$.post('/questionAnswer/updatestatus?type=4',checkeds,function(result){
			if(pageNo==1){
				$("#downexcel").submit();
				return;
				}
			$("#Number1").attr('value',pageNo);
			checkNumber(pageNo);
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
function exam(){	
	$("#btnzhuce").attr({"disabled":"disabled"});//将按钮置灰	

	$.ajaxFileUpload({ 
		url:'/questionAnswer/examExcel', 
		type:'post', //数据发送方式 
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json) 
		timeout:120000,
		async:false,
		fileElementId:'xls',
		success: function(msg){ //成功 
				if($.trim(msg)=="yes"){
					alert("文件校验完成");
				$("#btnzhuce").removeAttr("disabled");//将按钮可用
				}else{
					alert("文件校验失败,请重新修改上传");
					$("#downexcel").submit();
					//$("xls").attr("value","");
				}
			}, 
		error:function (){
			alert("文件校验失败或格式错误,请检查文件并刷新页面 ");
			$("#downexcel").submit();
			return false;
		}
		}); 	
	}
	function selectAll() {
		$("input[name='ids']").attr("checked",$("#allCheck").attr("checked") ? true : false);
	}
	/**
	 * content删除
	 */
	function contentDelete(id){
		var pageNo=${page.pageNo};
		if(window.confirm('你确定删除该详情?')){
			$.post('/questionAnswer/deleteContent',{id:id},function(){
				if(pageNo==1){
					$("#downexcel").submit();
					return;
					}
				$("#Number1").attr('value',pageNo);
				checkNumber(pageNo);
			});
		}
		$("#oladiv_"+id).hide();
	}
	
	/**
	 * content更改
	 */
	 function contentUpdate(id){
		var content = document.getElementById('content'+id).value;
		var pageNo=${page.pageNo};
		$.post('/questionAnswer/updateContent',{id:id,content:content},function(){
			if(pageNo==1){
				$("#downexcel").submit();
				return;
				}
			$("#Number1").attr('value',pageNo);
			checkNumber(pageNo);
			});
		$("#oladiv_"+id).hide();
		alert("修改成功！");
	}
	
	 function jsonGetTwoByOneTb(pid,qid){
			$("#td"+qid+" #tsecondkindId option").remove();
			$("#td"+qid+" #tsecondkindId").append("<option selected='select' value=''>--请选择--</option>");
			if(pid!=null){
				$.post("/json/getTwoByOne",{parentId:pid},function(result){
							if(result!=null){
								 $("#td"+qid+" #tsecondkindId option").remove();
								   $("#td"+qid+" #tsecondkindId").append("<option  selected='select' value=''>--请选择--</option>");
								   for(i in result) {
								   		$("#td"+qid+" #tsecondkindId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
								   }
							}
				},'json');
			}
		}
	 
	 function modify(id){
			var pageNo=${page.pageNo};
			var firstkindId = $("#tf"+id+" #tfirstkindId").val();
			var secondkindId = $("#td"+id+" #tsecondkindId").val();
			var brandId = $("#tb"+id+" #pid").val();
			var carstyleId = $("#tc"+id+" #tkeywordid").val();
			$.post('/questionAnswer/modify',{id:id,firstkindId:firstkindId,secondkindId:secondkindId,brandId:brandId,carstyleId:carstyleId},function(result){
				if(pageNo==1){
					$("#downexcel").submit();
					return;
					}
				$("#Number1").attr('value',pageNo);
				checkNumber(pageNo);
			},'json');
		}
	 function selectCar(pid,id){
		$("#tc"+id+" #tkeywordid option").remove();
		$.ajax({
			   type: "POST",
			   url: "/questionAnswer/carstyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				 $("#tc"+id+" #tkeywordid").val("");
				 $("#tc"+id+" #tkeywordid").append("<option value='0'>--请选择--</option>");
				   for(i in data) {
				   		$("#tc"+id+" #tkeywordid").append("<option value="+data[i].id+">"+data[i].style+"</option>");
				   }
				   } 
			});
	}
		
	//批量修改
	function jsonGetTwoByOne2(pid){
		 $("#secondkindId2 option").remove();
		 $("#secondkindId2").append("<option  selected='select' value=''>--请选择--</option>");
		if(pid!=null){
			$.post("/json/getTwoByOne",{parentId:pid},function(result){
				if(result!=null){
					 $("#secondkindId2 option").remove();
					   $("#secondkindId2").append("<option  selected='select' value=''>--请选择--</option>");
					   for(i in result) {
					   		$("#secondkindId2").append("<option value="+result[i].id+">"+result[i].name+"</option>");
					   }
				}
			},'json');
		}
	}
	//批量修改
	function selectCar2(pid){
		$("#tcarstyleId2 option").remove();
		$.ajax({
			   type: "POST",
			   url: "/questionAnswer/carstyle",
			   dataType:'json',
			   data: {
				   'brandID':pid
			   },
			   success: function(data){
				   $("#tcarstyleId2 option").val("");
				   $("#tcarstyleId2").append("<option value='0'>--请选择--</option>");
				   for(i in data) {
				   		$("#tcarstyleId2").append("<option value="+data[i].id+">"+data[i].style+"</option>");
				   }
				} 
			});
	}
</script>
</html>