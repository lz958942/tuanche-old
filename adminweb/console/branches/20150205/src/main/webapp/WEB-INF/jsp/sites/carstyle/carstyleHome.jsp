<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>团车网车型管理</title>
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
<script type="text/javascript" charset="utf-8" src="/js/sites/sites.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<style>
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
	function abc(){
		$("#searchSpecialForm").attr("action","/sites/carstyle/carStylesearch");
		$("#searchSpecialForm").submit();
	}

		function isDel(id,mypid){
			var isdel=window.confirm('该车型关联车款一级车型团购也将删除？确认删除？');
		if(isdel){
		window.location.href="/sites/carstyle/carStyleDel?id="+id+"&mypid="+mypid;
		}
	} 
function saleStop(id,type){
	if(id!=null&&""!=id){
	$.ajax({
		   url:"/sites/carstyle/slodOut.do?id="+id+"&type="+type ,
		   success: function(date){
			   if(date.trim()!=null){
				 $("#searchSpecialForm").submit();
			   }
		   }
		});
	}
}
</script>
</head>
<body>
<form action="/sites/carstyle/carStylesearch" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="specialSubject" id="searchSpecialForm">
	<input type="hidden" name="mypid" value="${mypid }">
	<input type="hidden" name="statusid" value="${statusid }">
	
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/sites/carstyle/carStyleHomeAll">全部车型列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/sites/carstyle/carStyleAdd?mypid=${mypid}">添加车型</a></li>
					</ul>
			   </div>
			</td>
		</tr>

			<table>
				<tr class="lh28">
					<td class="ti" >品牌名称:</td>
					
					<td>
					<select name="brandName" >
						<option value="">请选择品牌</option>
						<c:forEach items="${pBrands}" var="pb">
						<option value="${pb.id }"  <c:if test="${pb.id==brandName }"> selected="selected"</c:if>>${pb.typepinyI }${pb.name }</option>
						</c:forEach>
					</select>
					
					车型名称： <input type="text" name="cName" id="cName" maxlength="20" style="width:150px;" value="${cName}"/>
					<font>车型状态:</font>
					<select  name="show" style="width: 200px">
						<option value="9">请选择</option>
						<option  value="0" <c:if test="${show==0 }">selected="selected"</c:if>>显示</option>
						<option value="1"<c:if test="${show==1}">selected="selected"</c:if> >隐藏</option>
					</select>
					<font>车型标识:</font>
					<select  name="publicMark" style="width: 200px">
						<option value="">请选择</option>
						<option value="0"<c:if test="${publicMark==0 }">selected="selected"</c:if> >全部</option>
						<option  value="1" <c:if test="${publicMark==1 }">selected="selected"</c:if>>新车</option>
						<option  value="2" <c:if test="${publicMark==2 }">selected="selected"</c:if>>二手车</option>
					</select>
					
					<input type="button" value="搜索"  class="btn" id="tijiao" onclick="abc()"/> 
					</td>
				
				</tr>
				<tr class="lh28">
				</tr>
			</table>
			<div style="text-align:left;line-height:25px;">
				<input name="hyperlink" id="hyperlink" type="hidden" value=""/>
			</div>
  			<div>
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr">
							<!-- <th><input type="checkbox" id="allCheck" onclick="selectAll()"/></th> -->
							<th style="white-space:nowrap;">车型编号</th>
							<th style="white-space:nowrap;">所属品牌</th>
							<th style="white-space:nowrap;">车型名称</th>
						<!-- 	<th style="white-space:nowrap;">英文名称</th> -->
							<th style="white-space:nowrap;">车型图片</th>
							<th style="white-space:nowrap;">厂商指导价</th>
							<th style="white-space:nowrap;">车型大小</th>
							<th style="white-space:nowrap;">排量</th>	
							<th style="white-space:nowrap;">变速箱</th>
							<th style="white-space:nowrap;">车系标识</th>
							<th style="white-space:nowrap;">添加时间</th>
							<th style="white-space:nowrap;">添加人</th>
							<th style="white-space:nowrap;">修改时间</th>
							<th style="white-space:nowrap;">修改人</th>
							<th style="white-space:nowrap;">状态</th>
							<th style="white-space:nowrap;">操作</th>
							
						</tr>
					</thead>
					<c:if test="${cList!=null }">
						<c:forEach items="${cList }" var="cList">
						<tr>
							<td>${cList.id}</td>
							<td>${func:getBrandName(cList.pid)} </td>
							<td>${cList.style}</td>
							<td>
						<c:if test="${cList.spicsrc!=''}"> 
							<img width="50px" height="50px" src="${cList.spicsrc}"> 
						 </c:if> 
							<c:if test="${cList.spicsrc==''}">
							暂无图片
							</c:if>
							</td>
							<td>${cList.factoryPrice}</td>
							<td>
							<c:if test="${cList.bos==1 }">小型车</c:if>
            	 			<c:if test="${cList.bos==2 }">紧凑车型</c:if>
            				<c:if test="${cList.bos==3 }">中级车</c:if>
            				<c:if test="${cList.bos==4 }">中高级车</c:if>
            	 			<c:if test="${cList.bos==5 }">豪华车</c:if>
            				<c:if test="${cList.bos==6 }">MPV</c:if>
            				<c:if test="${cList.bos==7 }">SUV</c:if>
            	 			<c:if test="${cList.bos==8 }">跑车</c:if>
            				<c:if test="${cList.bos==9 }">微型车</c:if>
            				<c:if test="${cList.bos==10 }">轻客</c:if>
            				</td>
							<td>${cList.level}</td>
							<td>${cList.speedBox}</td>
						<td>
							<c:if test="${cList.publicMark==0}">全部</c:if> 
							<c:if test="${cList.publicMark==1}">新车</c:if> 
							<c:if test="${cList.publicMark==2}">二手车</c:if> 
							<c:if test="${cList.publicMark==null}">无</c:if> 
						</td>
							<td>${cList.createTime}</td>
							<td>${func:getEditName(cList.createTimeUserID)}</td>
							<td>${cList.updateTime}</td>
							<td>${func:getEditName(cList.updateTimeUserID)}</td>
							<%-- <td>
							<c:if test="${cList.type==null||cList.type==1}">
							<font color="red">停售</font>
							</c:if>
							<c:if test="${cList.type!=null&&cList.type==0}">
							<font color="green">正常</font>
							</c:if>
							</td> --%>
							<td>
							<c:if test="${cList.show==null || cList.show==1 }">隐藏</c:if>
							<c:if test="${cList.show!=null && cList.show==0 }">显示</c:if>
							</td>
							<td>
							<a href="/sites/carstyle/carStyleUpdateBefore?id=${cList.id}&pid=${cList.pid}">编辑 </a> |<a   onclick="isDel(${cList.id},${cList.pid})" href="javascript:void(0)">车型删除 </a>| <a href="/sites/carstyle/styleHome?ppid=${cList.id}&bid=${cList.pid}">管理车款 </a> <c:if test="${cList.type==0}">
																																																																				|<a href="javascript:void(0)" onclick="saleStop(${cList.id},1)">停售 </a>
																																																																				</c:if> 
																																																																				<c:if test="${cList.type==null || ${cList.type==''}"> </c:if>
																																																																				<c:if test="${cList.type==1}">
																																																																				|<a href="javascript:void(0)" onclick="saleStop(${cList.id},0)">在售 </a>
																																																																				</c:if>
																																																																				|<a href="/sites/carstyle/styleAdd?myppid=${cList.id}">添加车款 </a>
																																																																				|<c:if test="${cList.show==1 ||cList.show==null }"><a href="javascript:void(0)" onclick="saleStop(${cList.id},2)">找回</a></c:if>
																																																																				</td>
																																																																				
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${cList==null }">
					<tr class="main_info">
						<td colspan="14">没有相关数据</td>
					</tr>
					</c:if>
				</table>
			</div>
			<div class="page_and_btn" style="text-align:center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
</table>
	</form>
<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
</body>
</html>