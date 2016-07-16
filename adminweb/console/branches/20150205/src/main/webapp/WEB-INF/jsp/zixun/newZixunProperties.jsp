<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<c:set var="currentPage" value="newZixunProperties" scope="page"></c:set>
<%@ include file="./zixunMenuHeader.jsp" %>
<div class="ztsx">
	<label><strong>编辑资讯内容</strong></label>
</div>
	<!-- 检查权限 -->
	<c:set var="hasDeploy" value="${func:checkAuth('/zixun/deployZixun')}"/>
<form action="/zixun/nextZixunProperties" method="post" style="padding:0 10px 0 10px; margin-top:0px" name="zixun" id="newZixunPropertiesForm">
		<input id="zixunId" type="hidden" name="id" value="${zixun.id}" />
	<div class="borderDiv">
		<table>
			<tr>
				<td><span style="width:100px;padding-right:16px; vertical-align:top">选择城市：</span>
				<input readonly="readonly" id="city" onclick="showMenu(); return false;" id="city"  style="width:93px" value="${func:getallCity(zixun.cityId==null?-1:zixun.cityId)}"/>
				<input  id="cityId" name="cityId" type="hidden" value="${zixun.cityId==null?-1:zixun.cityId}"/>
				</td>
				<td>
					<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top">选择分类：<span style="color:red;">*</span></span>
					<input readonly="readonly" id="classId" onclick="classshowMenu(); return false;" value="${zixun==null?'综合':func:getZXClass(zixun.classId)}" />
					<input id="classIdh" name="classId" type="hidden" value="${zixun==null?36:zixun.classId}"/>
				</td>
				<td>
					<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top">选择关联车型：</span>
					<c:if test="${zixun.styleId!=null&&zixun.styleId!=''}">
					    	<input id="styleId" name="styleId" type="hidden" value="${zixun.brandId}-${zixun.styleId},"/>
					        <c:set  var="valueStr" value="${zixun.brandId}-${zixun.styleId}" scope="request" ></c:set>
					</c:if>
					<c:if test="${zixun.styleId==null||zixun.styleId==''}">
					
					    	<input id="styleId" name="styleId" type="hidden" value="${zixun.styleId}"/>
					    	 <c:set  var="valueStr" value="${zixun.brandId}" scope="request" ></c:set>
					    
					</c:if>
					<input readonly="readonly" id="selectCarStyle" onclick="selectCarStyleshow()" value=" ${func:getStyleInfo(valueStr)}"/>
					<input id="brindId" name="brandId" type="hidden" value="${zixun.brandId},"/>
					
				</td>
				<td>
					<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"><!--选择列表图片：--></span>
					<div style="display: none">
					<input id="listPicFile" name="listPicFile" type="file" onchange="upload()"/>
					</div>
				</td>
			</tr>
		</table>
		
	</div>
	<div class="borderDiv">
		<table>
			<tr>
            	<td>发布时间:</td>
                <td>
                <input value="${zixun.publishDateStr}"  onfocus=" WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'}) "  name="publishDateStr" style="width:300px;" class="dateCss" type="text"  readonly="readonly">
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">资讯标题：<span style="color:red;">*</span></td>
                <td><input maxlength="44" type="text"  id="zixunTitle" name="title" value="${zixun.title}" style="width:675px;" />
                	<p class="font">资讯标题是资讯的标题，在正文的开头出现。并且在资讯页面代码的title中显示（限定44个字）</p>
                </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">资讯描述:</td>
                <td><textarea maxlength="250" cols="107"  rows="5" id="description" name="description" style="width:675px">${zixun.description}</textarea>
                 <p class="font">用于描述该专题页面，会以 meta 标签的形式内嵌在网页中，尽量在描述中加入你主要的关键字，在源代码的description中显示，配合搜索引擎抓取，提高资讯被搜索引擎抓取的几率（请控制250字以内）</p></td>
            </tr>
            <tr>
            	<td>
          	    	选择列表图：
          	    </td>
          	    <td>
          	    <img id="listImage" src='${zixun!=null?zixun.listPicFull:"/images/selectPiuc.jpg"}'  width="60" height="40" onclick="$('#listPicFile').trigger('click')"/>
          	   	列表 图片尺寸为：600*400
          	    <input type="hidden" id="listPic" name="listPic"  value="${zixun.listPic}" />
          	    </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">资讯关键词：</td>
                <td>
                	<input maxlength="50" type="text"  id="keyword" name="keyword" value="${zixun.keyword}" style="width:675px;" />
                 	<p class="font">原创编辑必填</p>
                 </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">资讯标签词：</td>
                <td>
                	<input maxlength="100" type="text"  id="labelWord" name="labelWord" value="${zixun.labelWord}" style="width:675px;" />
                 	<p class="font">优化编辑必填</p>
                 </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">资讯内容：<span style="color:red;">*</span></td>
            	<td>
            		<div style="width:900px;">
	            		<script type="text/plain" id="myEditor">${zixun.content}</script>
	            		<script type="text/javascript">
						     editor = new baidu.editor.ui.Editor({
						        textarea:'content'
						    });
						    editor.render("myEditor");
						</script>
					</div>
            	</td>
            </tr>
            <tr>
            	<td>审核状态:</td>
                <td>
                	<c:choose>
                		<c:when test="${hasDeploy}">
							<input type="radio" name="status" value="1" <c:if test="${zixun.status == 1}"> checked="checked" </c:if> />发布
                		</c:when>
                	</c:choose>
                	<input type="radio" name="status" value="0" <c:if test="${newZixun || zixun.status == 0 ||!hasDeploy}"> checked="checked" </c:if> />待发布
				</td>
            </tr>
            <tr>
            	<td>&nbsp;</td>
            	<td>
            		<span id="errMsg" style="color:red;"></span>
            	</td>
            </tr>
        </table>
	</div>
	<div class="xtnext">
		<c:choose>
			<c:when test="${newZixun}">
				<input id="newZixunBtn" type="button" value="保存" onClick="saveZixunProperties();"/>
				<input id="newZixunBtn" type="button" value="下一步" onClick="checkNewZixunProperties();"/>
			</c:when>
			<c:otherwise>
				<input id="newZixunBtn" type="button" value="保存" onClick="saveZixunProperties();"/>
			</c:otherwise>
		</c:choose>
	</div>
</form>

<div id="menuContent" style="display:none; position: absolute;" >
	<ul id="treeCity" class="ztree"  ></ul>
</div>

<div id="menuContentClass" style="display:none; position: absolute;" >
	<ul id="treeClass" class="ztree"  ></ul>
</div>
<div id="menuContentselectCarStyle" style="display:none; position: absolute;" >
	<ul id="treeselectCarStyle" class="ztree"  ></ul>
</div>

