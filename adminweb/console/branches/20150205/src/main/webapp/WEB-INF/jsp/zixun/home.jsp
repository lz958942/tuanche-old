<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>

<c:set var="currentPage" value="zixunManage" scope="page"></c:set>
<script type="text/javascript" src="/js/zixun/zixun.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript">
function qwer(id){
	$('#searchZixunForm').attr("action","/zixun/getZixunProperties");
	$("#zixunId").attr("value",id);
	$('#searchZixunForm').submit();
	}
function addListPics(){
	var ids="";
		var box = $("input[name='idBox']:checked");
		if(box.length==0){
			alert("未选中任何行！");
			return;
		}
		for(var i =0 ;i<box.length; i++){
			ids+= $(box[i]).val()+"_";
		}
		if($("#allSelect").attr("checked")){
		$("#allSelect").attr("checked",$("#allSelect").attr("checked") ? false :true );
		}
		$("#dataBody input[type='checkbox']").attr("checked",$("input[name='idBox']:checked").attr("checked") ? false :true );
		openWindow("/zixun/addListPics?ids="+ids,"yes",600,500);
		//$("#hide").dialog("/zixun/addListPics?ids="+ids);
}
</script>
<div id="zixunHomeDiv">
	<%@ include file="./zixunMenuHeader.jsp"%>
	<!-- 检查权限 -->
	<c:set var="hasDeploy" value="${func:checkAuth('/zixun/deployZixun')}" />
	<c:set var="hasDel" value="${func:checkAuth('/zixun/delZixun')}" />
	<div class="borderDiv">
		<form action="${actionUrl}" method="post"
			style="padding: 0 10px 0 10px; margin-top: 0px;" name="searchZixun"
			id="searchZixunForm" enctype="multipart/form-data">
			<input type="hidden" name="ids" id="ids" value="3">
			<input type="hidden" name="tab" id="tab" value="3">
			<input type="hidden" name="tabfu" id=tabfu value="3">
			<table>
				<tr class="lh28">
					<td class="ti">资讯的编号:</td>
					<td><input
						onkeyup="this.value=this.value.replace(/[^\d]/g,'') " type="text"
						maxlength="10" id="zixunId" name="id" style="width: 200px;"
						value="${searchZixun.id}" /></td>
					<td class="ti">资讯的标题:</td>
					<td><input type="text" name="title" style="width: 20px height: 18px;"
						value="${searchZixun.title}" id="titleid" /></td>
				</tr>
				<tr class="lh28">
					<td class="ti">资讯关键字:</td>
					<td><input type="text" name="keyword" style="width: 200px;"
						value="${searchZixun.keyword}" id="keywordid" /></td>
					<td class="ti">列表图状态:</td>
					<td id="pradio"><input type="radio" value="1" name="listPic"
						<c:if test="${searchZixun.listPic ==1}">checked="checked"</c:if> />有列表图
						<input type="radio" value="2" name="listPic"
						<c:if test="${searchZixun.listPic ==2}">checked="checked"</c:if> />无列表图
						<input type="radio" value="3" name="listPic"
						<c:if test="${empty searchZixun.listPic || searchZixun.listPic ==3}">checked="checked"</c:if> />全部
					</td>
				</tr>
				<tr class="lh28">
					<td class="ti">创建人:</td>
					<td><select name="editorId" id="editorId">
							<option value="0">不限</option>
							
							<c:forEach items="${editers}" var="editer">
								<option id="editersided" value="${editer.id==null?0:editer.id}"
									<c:if test="${editer.id== searchZixun.editorId}"> selected="selected" </c:if>>${editer.empName}</option>
							</c:forEach>
					</select></td>
					<%-- <td class="ti">修改人:</td>
					<td><select name="updateEditorId" id="updateEditorId">
							<option value="0">不限</option>
							<c:forEach items="${editers}" var="editer">
								<option id="editersided" value="${editer.id==null?0:editer.id}"
									<c:if test="${editer.id== searchZixun.updateEditorId}"> selected="selected" </c:if>>${editer.empName}</option>
							</c:forEach>
					</select></td> --%>
					<td class="ti">发布人:</td>
					<td><select name="publishEditorId" id="publishEditorId">
							<option value="0">不限</option>
							<c:forEach items="${editers}" var="editer">
								<option id="editersided" value="${editer.id==null?0:editer.id}"
									<c:if test="${editer.id== searchZixun.publishEditorId}"> selected="selected" </c:if>>${editer.empName}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td class="ti">发布状态:</td>
					<td id="ti"><input type="radio" value="1" name="status"
						<c:if test="${searchZixun.status ==1}">checked="checked"</c:if> />已发布
						<input type="radio" value="0" name="status"
						<c:if test="${searchZixun.status ==0}">checked="checked"</c:if> />待发布
						<input type="radio" value="3" name="status"
						<c:if test="${empty searchZixun.status || searchZixun.status ==3}">checked="checked"</c:if> />全部
					</td>
				</tr>
				<tr class="lh28">
					<td class="ti">起始时间:</td>
					<td><input id="startDate" name="startDate" class="dateCss"
						type="text" onclick="SelectDate(this,'yyyy-MM-dd','起始日期')"
						readonly="readonly" value="${searchZixun.startDate}"></td>
					<td class="ti">截止时间:</td>
					<td><input id="endDate" name="endDate" class="dateCss"
						type="text" onclick="SelectDate(this,'yyyy-MM-dd','终止日期')"
						readonly="readonly" value="${searchZixun.endDate}"></td>
				</tr>
				<tr class="lh28">
					<td class="ti">城市:</td>
					<td><input readonly="readonly" id="city"
						onclick="showMenu(); return false;" id="city"
						style="width: 215px; height: 25px"
						value="${func:getallCity(searchZixun.cityId==0?-1:searchZixun.cityId)}" />
						<input id="cityId" name="cityId" type="hidden"
						value="${searchZixun.cityId==0?-1:searchZixun.cityId}" /></td>
					<td class="ti">分类:</td>
					<td><input style="width: 215px; height: 25px"
						readonly="readonly" id="classId"
						onclick="classshowMenu(); return false;"
						value="${func:getZXClass(searchZixun.classId)}" /> <input
						id="classIdh" name="classId" type="hidden"
						value="${searchZixun.classId}" /></td>
					<td class="ti">品牌:</td>
					<td id="ti">
						<select id="brandId" name="brandId" onchange="changeBrand(this.id);">
							<option value="">--请选择--</option>
							<c:if test="${brandList!=null&&brandList.size()>0 }">
								<c:forEach items="${brandList }" var="brandList">
									<option value="${brandList.id }" ${searchZixun.brandId==brandList.id?'selected':'' }>${brandList.orderName} ${brandList.name }</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
					<td class="ti">车型:</td>
					<td id="ti">
						<select id="carStyleId" name="styleId">
							<option value="">--请选择--</option>
							<c:if test="${carStyleList!=null&&carStyleList.size()>0 }">
								<c:forEach items="${carStyleList }" var="carStyleList">
									<option value="${carStyleList.id }" ${searchZixun.styleId==carStyleList.id?'selected':'' }>${carStyleList.carStyleName }</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
			</table>
			<div align="left">
				<input type="button" value="搜索" class="btn"
					onclick="searchforZixun()" />
				<c:if test="${hasDel}">
					<input type="button" value="删除" class="btn"
						onclick="batchUpdate(-1)" />
				</c:if>
				<c:if test="${hasDeploy}">
					<input type="button" value="发布" class="btn"
						onclick="batchUpdate(1)" />
					<input type="button" value="取消发布" class="btn"
						onclick="batchUpdate(0)" />
				</c:if>

				<!-- <input type="file" name="file" id="file" />
			<input type="button" value="导入excel" class="btn" onclick="excel()" /> -->
			 <input type="button" value="添加多文件列表图"  onclick="addListPics()" class="btn"/>
				<a id="aForSearchAll" href="/zixun/home?mkg=0">清空搜索条件</a> 
			 <label>点击总数：${clickSum }</label>
			</div>
			<div style="text-align: left; line-height: 25px;">
				<input name="hyperlink" id="hyperlink" type="hidden"
					value="${searchZixun.hyperlink}" />
			</div>
			<div>
				<table class="table_style table table-bordered">
					<tr class="attr">
						<td style="white-space: nowrap;"><label><input
								type="checkbox" id="allSelect" onchange="selectAll()" /> </label></td>
						<td style="white-space: nowrap;">编号</td>
						<td style="white-space: nowrap;">城市</td>
						<td style="white-space: nowrap;">品牌车型</td>
						<td style="white-space: nowrap;">标题</td>
						<td style="white-space: nowrap;">分类</td>
						<td style="white-space: nowrap;">url</td>
						<td style="white-space: nowrap;">创建人</td>
						<td style="white-space: nowrap;">发布人</td>
						<td style="white-space: nowrap;">制作日期</td>
						<td style="white-space: nowrap;">发布日期</td>
						<td style="white-space: nowrap;">发布状态</td>
						<!-- <td style="white-space: nowrap;">编辑</td>
						<td style="white-space: nowrap;">设置热词</td> -->
						<td style="white-space: nowrap;">点击</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<tbody align="center" id="dataBody">
						<c:choose>
							<c:when test="${not empty zixuns}">
								<c:forEach items="${zixuns}" var="zixun">
									<tr class="attr" id="tr_${zixun.id}">
										<td><input name="idBox" type="checkbox"
											value="${zixun.id}" /></td>
										<td><div style="white:13px">${zixun.id}</div> </td>
										<td>${zixun.trueCityName}</td>
										<td>
											<c:if test="${zixun.styleId==null||zixun.styleId==''}">
												<c:set var="valueStr" value="${zixun.brandId}" scope="request" ></c:set>
											</c:if>
											<c:if test="${zixun.styleId!=null&&zixun.styleId!=''}">
												<c:set var="valueStr" value="${zixun.brandId}-${zixun.styleId}" scope="request" ></c:set>
											</c:if>
											${func:getStyleInfo(valueStr)}
										</td>
										<td><div style="width:150px;word-break: break-all;">${zixun.title}</div></td>
										<td style="white-space: nowrap;">${func:getZXClass(zixun.classId)}</td>
										<td>${zixun.url}</td>
										<td>${func:getEditName(zixun.editorId)}</td>
										<td>${func:getEditName(zixun.publishEditorId)}</td>
										<td>${zixun.createTime}</td>
										<td>${zixun.publishDateStr}</td>
										<td id="status_td_${zixun.id}"><c:choose>
												<c:when test="${zixun.status == 0}">
													待发布
												</c:when>
												<c:when test="${zixun.status == 1}">
													已发布
												</c:when>
											</c:choose></td>
										<td>${zixun.clickCount}</td>
										<td>
											<a href="/zixun/getZixunProperties?id=${zixun.id}" onclick="qwer(${zixun.id})">修改</a>
											<a href="/zixun/getZixunHouseInfo?id=${zixun.id}">设置热词</a>
											<c:choose>
												<c:when test="${hasDel}">
													<a href="javascript:delZixun(${zixun.id});">删除&nbsp;&nbsp;</a>
												</c:when>
												</c:choose> 
												<span id="deploy_cell_${zixun.id}"> 
													<c:choose>
														<c:when test="${hasDeploy}">
															<c:choose>
																<c:when test="${zixun.status == 0}">
																	<a href="javascript:deployZixun(${zixun.id},true);">发布&nbsp;&nbsp;</a>
																</c:when>
																<c:when test="${zixun.status == 1}">
																	<a href="javascript:deployZixun(${zixun.id},false);">取消发布</a>
																</c:when>
															</c:choose>
														</c:when>
													</c:choose>
												</span>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="14">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="page_and_btn" style="text-align: center;">
				<jsp:include page="/WEB-INF/snippets/page.jsp" />
			</div>
		</form>

	</div>
</div>
<div id="menuContent" style="display: none; position: absolute;">
	<ul id="treeCity" class="ztree"></ul>
</div>

<div id="menuContentClass" style="display: none; position: absolute;">
	<ul id="treeClass" class="ztree"></ul>
</div>