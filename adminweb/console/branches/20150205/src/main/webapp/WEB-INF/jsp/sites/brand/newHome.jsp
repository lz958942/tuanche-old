<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>团车网品牌管理</title>
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
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.ztree.css" />
<link type="text/css" rel="stylesheet" href="/css/zTreeStyle.css"/>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<link type="text/css" rel="stylesheet" href="/css/layout.css"/>
<script type="text/javascript">
function isDel(id){
	///
	var isdel=window.confirm("品牌下可能包含车型车款，删除后对应的车型车款也将删除，确认删除品牌？");
	if(isdel){
		window.location.href="/sites/brand/newDelBrand?id="+id;
	}
}
function bedit(id){
	var staName=$("#title").val();
	if(staName==null||staName==""){
		window.location.href="/sites/brand/newAlertBefore?id="+id;
		return;
	}
	window.location.href="/sites/brand/newAlertBefore?id="+id+"&staName="+staName;
} 
function uodateBrandPic(id,type){
	openWindow("/common/brandPicBeff?id="+id+"&type="+type,"yes",700,500);
}
</script>
</head>
<body>
<form action="/sites/brand/newSelectBrand" method="post" style="padding:0 10px 0 10px;margin-top:0px; " name="specialSubject" id="searchSpecialForm">
<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
		<tr>
			<td>
				<div id="tabs" class="tabs">  
					<ul>
						<li class="tabs_active"><a href="/sites/brand/newHome">品牌列表</a></li>
						<li  style="background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%" ><a href="/sites/brand/toAddBrand">添加品牌</a></li>
					</ul>
			   </div>
			</td>
		</tr>

			<table>
				<tr class="lh28">
					<td class="ti">品牌名称:</td>
					<td>
					<input type="text" name="name" id="title" maxlength="20" style="width:150px;" value="${brandDomain.name}"/>
					</td>
					<td></td>
					<td class="ti">品牌标识:</td>
					<td>
					<select name="publicMark" id="publicMark">
						<option <c:if test="${brandDomain.publicMark==null }">selected='selected'</c:if> value="">请选择</option>
						<option <c:if test="${brandDomain.publicMark==0 }">selected='selected'</c:if> value="0">全部</option>
						<option <c:if test="${brandDomain.publicMark==1 }">selected='selected'</c:if> value="1">新车</option>
						<option <c:if test="${brandDomain.publicMark==2 }">selected='selected'</c:if> value="2">二手车</option>
					</select>
					<input type="submit" value="搜索"  class="btn"/>
					</td>
				</tr>
			</table>
			<div style="text-align:left;line-height:25px;">
				<input name="hyperlink" id="hyperlink" type="hidden" value=""/>
			</div>
  			<div>
				<table class="table_style table table-bordered" >
					<thead>
						<tr class="attr">
							<th style="white-space:nowrap;">品牌编号</th>
							<th style="white-space:nowrap;">品牌名称</th>
							<th style="white-space:nowrap;">国别</th>
							<th style="white-space:nowrap;">生产厂家性质</th>
							<th style="white-space:nowrap;">系别</th>
							<th style="white-space:nowrap;">品牌标识</th>
							<th style="white-space:nowrap;">logo图片</th>
							<th style="white-space:nowrap;">添加时间</th>
							<th style="white-space:nowrap;">添加人</th>
							<th style="white-space:nowrap;">修改时间</th>
							<th style="white-space:nowrap;">修改人</th>
							<th style="white-space:nowrap;">品牌图片</th>
							<th style="white-space:nowrap;">操作</th>
							
						</tr>
					</thead>
					<c:if test="${bList!=null }">
						<c:forEach items="${bList }" var="blist">
						<tr>
							<td>${blist.id}</td>
							<td>${blist.name}</td>
							<td>
						<c:if test="${blist.contry==0}">中国</c:if> 
                		 <c:if test="${blist.contry==1}">德国</c:if>
                		<c:if test="${blist.contry==2}">日本</c:if>
                		<c:if test="${blist.contry==3}">美国</c:if>
                		<c:if test="${blist.contry==4}">法国</c:if>
                		<c:if test="${blist.contry==5}">韩国</c:if>
                		<c:if test="${blist.contry==6}">瑞典</c:if>
                		<c:if test="${blist.contry==7}">英国</c:if>
                		<c:if test="${blist.contry==8}">意大利</c:if>
                		<c:if test="${blist.contry==9}">西班牙</c:if>
							</td>
							<td>
                		<c:if test="${blist.vender==1}">合资</c:if>
                		 <c:if test="${blist.vender==2}">进口</c:if>
                		 <c:if test="${blist.vender==3}">国产</c:if>
								<td>
							<c:if test="${blist.newSeries==1}">德系</c:if> 
							<c:if test="${blist.newSeries==2}">日系</c:if> 
							<c:if test="${blist.newSeries==3}">美系</c:if> 
							<c:if test="${blist.newSeries==4}">欧系</c:if> 
							<c:if test="${blist.newSeries==5}">韩系</c:if> 
							<c:if test="${blist.newSeries==6}">法系</c:if> 
							<c:if test="${blist.newSeries==7}">自主</c:if> 
							</td>
						<td>
							<c:if test="${blist.publicMark==0}">全部</c:if> 
							<c:if test="${blist.publicMark==1}">新车</c:if> 
							<c:if test="${blist.publicMark==2}">二手车</c:if> 
							<c:if test="${blist.publicMark==null}">无</c:if> 
						</td>
							<td>
						 	<c:if test="${blist.logosrc!=''}"> 
							<img  style="width:80px; height:80px" src="${blist.logosrc}"> 
						 </c:if> 
							<c:if test="${blist.logosrc==''}">
							暂无图片
							</c:if>
							</td>
							<td>${blist.createTime}</td>
							<td>${func:getEditName(blist.createUserId)}</td>
							<td>${blist.updateTime}</td>
							<td>${func:getEditName(blist.updateUserId)}</td>
							<td>
							<c:if test="${blist.brndPic==null||blist.brndPic=='' || blist.brndPic=='NULL'}">
								无
							</c:if>
							<c:if test="${blist.brndPic!=null &&blist.brndPic!='NULL' && blist.brndPic!='' }">
								有
							</c:if>
							</td>
							<td><a href="#" onclick="bedit(${blist.id})">编辑 </a> |<a onclick="isDel(${blist.id})"  href="#">品牌删除 </a>| <a href="/sites/carstyle/carStyleHome?pid=${blist.id}">管理车型 </a><c:if test="${blist.brndPic!=null &&blist.brndPic!='NULL' && blist.brndPic!='' }">
								|<a href="#" onclick="uodateBrandPic(${blist.id},2)">修改列表图</a>
								</c:if>
								<c:if test="${blist.brndPic==null || blist.brndPic=='NULL' || blist.brndPic=='' }">
								|<a href="#" onclick="uodateBrandPic(${blist.id},1)">添加列表图</a>
							</c:if>
							|<a href="/sites/carstyle/carStyleAdd?mypid=${blist.id}">添加车系</a>
							</td>
						</tr> 
						
						</c:forEach>
					</c:if>
					<c:if test="${bList==null }">
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