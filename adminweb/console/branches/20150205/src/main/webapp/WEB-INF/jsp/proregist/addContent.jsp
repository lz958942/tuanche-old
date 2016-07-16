<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/decorate/decorate.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
	.uploadCss{
		max-width: 350px;
		max-height: 92px;
	}
</style>
<title>内容修改</title>
</head>
<body>
	<form action="/decorate/addContent" method="post"  id="addContentForm">
		<div class="b-con a-form">
			<div>
				 <div align="center">
						<input type="hidden" id="id" name="id" value=""/>
						<input type="hidden" id="titleType" name="titleType" value="${titleType }"/>
						<input type="hidden" id="baseId" name="baseId" value="${baseId }"/>
						<input type="hidden" id="tempId" name="tempId" value="${tempId }"/>
		         </div>
		       	 <c:if test="${wenziList!=null&&wenziList.size()>0 }">
		       		 <div id="dctitleDiv" align="center">
		       			<input type="hidden" id="tsize" name="tsize" value="${wenziList.size() }"/> 
		       			<input type="hidden" id="htype" value="1"/> 
		          		<c:forEach items="${wenziList }" var="wenziList" varStatus="var">
		          			<input type="hidden" id="tid" name="tid" value="${wenziList.id }"/>
				     		<div id="dctitle_num${var.index+1 }">
				          		<label class="pr15">标题(有则填):
				          			<input id="dcTitle" type="text" style="width: 35%" maxlength="100" name="dctitle" value="${wenziList.dctitle }" />
				          		</label>
				          		<label class="pr15">排序：
				          			<input id="dcSort" type="text" style="width: 15px;" maxlength="4" name="dcSort" value="${wenziList.dcSort }" />
				          			<span style="color:red;">*</span>
				          		</label>
				          		<label class="pr15">文字内容:
				          			<textarea id="content" name="content" rows="4" cols="5" style="width: 350px" maxlength="999">${wenziList.content }</textarea>
				          		</label>
				          		<input id="dcTitleBtn_num${var.index+1 }" type="button" 
				          		<c:if test="${var.index+1>1 }">value="删除 "</c:if><c:if test="${var.index+1==1 }">value="添加 "</c:if>
				          		 class="btn btn-info" 
				          		 <c:if test="${var.index+1>1 }">onclick="deleteContent(${wenziList.id },this.id,${wenziList.baseId });"</c:if><c:if test="${var.index+1==1 }">onclick="addTitleHang();"</c:if> />
				     		</div>
				   	  </c:forEach>
				   	</div>
		          </c:if>
		        <c:if test="${wenziList==null||wenziList.size()==0 }">
		       	<c:if test="${titleType=='1' }">
		          	<div id="dctitleDiv" align="center">
		          		<input type="hidden" id="tsize" name="tsize" value="0"/>  
		          		<input type="hidden" id="htype" value="1"/> 
		          		<div id="dctitle_num1">
				          		<label class="pr15">标题(有则填):
				          			<input type="hidden" id="tid" name="tid" value=""/>
				          			<input id="dcTitle" type="text" style="width: 35%" maxlength="100" name="dctitle" value="" />
				          		</label>
				          		<label class="pr15">排序：
				          			<input id="dcSort" type="text" style="width: 15px;" maxlength="4" name="dcSort" value="" />
				          			<span style="color:red;">*</span>
				          		</label>
				          		<label class="pr15">文字内容:
				          			<textarea id="content" name="content" rows="4" cols="5" style="width: 350px" maxlength="999"></textarea>
				          		</label>
				          		<input id="dcTitleBtn_num1" type="button" value="添加 " class="btn btn-info" onclick="addTitleHang();"/>
				     	</div>
				      </div>
				  </c:if>
		          </c:if> 
		          <c:if test="${picList!=null&&picList.size()>0 }">
		         	 <div id="dcpicDiv" align="center">
		         	  	  <input type="hidden" id="psize" name="psize" value="${picList.size() }"/>   
		         	  	  <input type="hidden" id="htype" value="2"/>    
				          <c:forEach items="${picList }" var="picList" varStatus="var">		
				          	  <input type="hidden" id="pid" name="pid" value="${picList.id }"/>  
					          <div id="dcpic_num${var.index+1 }">
					  		            图片:
					          	<label class="pr15" style="vertical-align: middle;">
					          		 <img id="dcPic_num${var.index+1 }" <c:if test="${picList.picUrl!=null }">src='${picHost }${picList.picUrl }' class='uploadCss'</c:if> 
							  		<c:if test="${picList.picUrl==null }">src='/images/upload.jpg'</c:if> onclick="$('#picFile_num${var.index+1 }').trigger('click')"/>
								  	 <input type="hidden" id="picUrl_num${var.index+1 }" name="picUrls"  value="${picList.picUrl }" />
					          	</label>
					          	<label class="pr15">排序：
					          			<input id="picSort" type="text" style="width: 15px;" maxlength="4" name="picSort" value="${picList.dcSort }" />
					          			<span style="color:red;">*</span>
					          	</label>
					          	<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
								<div style="display: none">
									<input id="picFile_num${var.index+1 }" name="picFile_num${var.index+1 }" type="file"  onchange="upload('picFile_num${var.index+1 }','picUrl_num${var.index+1 }','dcPic_num${var.index+1 }','4')"/>
								</div>
					          		 <input id="dcPicBtn_num${var.index+1 }" type="button" 
					          			 <c:if test="${var.index+1>1 }">value="删除 "</c:if><c:if test="${var.index+1==1 }">value="添加 "</c:if> 
					          			 class="btn btn-info" 
					          			  <c:if test="${var.index+1>1 }">onclick="deleteContent(${picList.id },this.id,${picList.baseId });"</c:if><c:if test="${var.index+1==1 }">onclick="addPicHang();"</c:if> />
					          </div>
				          </c:forEach>
				   		</div>
				    </c:if>
				    <c:if test="${picList==null||picList.size()==0 }">
				    <c:if test="${titleType=='2' }">
				       <div id="dcpicDiv" align="center">
				        	<input type="hidden" id="psize" name="psize" value="0"/>    
				        	<input type="hidden" id="htype" value="2"/> 
				      		<div id="dcpic_num1">
					  		            图片:
					          	<label class="pr15" style="vertical-align: middle;">
					                 <input type="hidden" id="pid" name="pid" value=""/> 
					          		 <img id="dcPic_num1" src='/images/upload.jpg' onclick="$('#picFile_num1').trigger('click')"/>
								  	 <input type="hidden" id="picUrl_num1" name="picUrls"  value="" />
					          	</label>
					          	<label class="pr15">排序：
					          			<input id="picSort" type="text" style="width: 15px;" maxlength="4" name="picSort" value="" />
					          			<span style="color:red;">*</span>
					          	</label>
					          	<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
								<div style="display: none">
									<input id="picFile_num1" name="picFile_num1" type="file"  onchange="upload('picFile_num1','picUrl_num1','dcPic_num1','4')"/>
								</div>
					          		 <input id="dcPicBtn_num1" type="button" value="添加 " class="btn btn-info" onclick="addPicHang();"/>
					          </div>
				    	</div>
				   	</c:if>
				    </c:if>
				    <c:if test="${tuwenList!=null&&tuwenList.size()>0 }">
					  	<div align="center" id="tuwenDiv">
					  		<input type="hidden" id="tpsize" name="tpsize" value="${tuwenList.size() }"/>
					  		<input type="hidden" id="htype" value="3"/>    
					  		<c:forEach items="${tuwenList }" var="tuwenList" varStatus="var">
					  			<input type="hidden" id="wid" name="wid" value="${tuwenList.id }"/>
						  		<div  id="titAndPic_num${var.index+1 } ">
					          		<label class="pr15" style="vertical-align: middle;">
						          		 <img id="tpPic_num${var.index+1 }" <c:if test="${tuwenList.picUrl!=null }">src='${picHost }${tuwenList.picUrl }' class='uploadCss'</c:if> 
							  		<c:if test="${tuwenList.picUrl==null }">src='/images/upload.jpg'</c:if> onclick="$('#tpFile_num${var.index+1 }').trigger('click')"/>
									  	 <input type="hidden" id="tpicUrl_num${var.index+1 }" name="tpicUrl"  value="${tuwenList.picUrl }" />
					          		</label>
					          		<label class="pr15">排序：
					          			<input id="tpSort" type="text" style="width: 15px;" maxlength="4" name="tpSort" value="${tuwenList.dcSort }" />
					          			<span style="color:red;">*</span>
					          		</label>
					          		<label class="pr15">文字内容:
					          			 <textarea id="contents_num${var.index+1 }" name="contents" rows="4" cols="5" style="width: 350px" maxlength="999">${tuwenList.content }</textarea>
					          		</label>
					          	    <input id="titAndPicBtn_num${var.index+1 }" type="button" 
					          	    <c:if test="${var.index+1>1 }">value="删除 "</c:if><c:if test="${var.index+1==1 }">value="添加 "</c:if>
					          	    class="btn btn-info" 
					          	    <c:if test="${var.index+1>1 }">onclick="deleteContent(${tuwenList.id },this.id,${tuwenList.baseId });"</c:if><c:if test="${var.index+1==1 }">onclick="addTitAndPicHang();"</c:if> />
					          	    <span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
										<div style="display: none">
											<input id="tpFile_num${var.index+1 }" name="tpFile_num${var.index+1 }" type="file"  onchange="upload('tpFile_num${var.index+1 }','tpicUrl_num${var.index+1 }','tpPic_num${var.index+1 }','4')"/>
										</div>
				          		</div>
			          		</c:forEach>
					  	</div>
				  	</c:if>
				  	<c:if test="${tuwenList==null||tuwenList.size()==0 }">
				  	<c:if test="${titleType=='3' }">
				  		<div align="center" id="tuwenDiv">
				  			<input type="hidden" id="tpsize" name="tpsize" value="0"/>
				  			<input type="hidden" id="htype" value="3"/>    
				  			<div id="titAndPic_num1">
				  					<input type="hidden" id="wid" name="wid" value=""/> 
					          		<label class="pr15" style="vertical-align: middle;">
						          		 <img id="tpPic_num1" src='/images/upload.jpg' onclick="$('#tpFile_num1').trigger('click')"/>
									  	 <input type="hidden" id="tpicUrl_num1" name="tpicUrl"  value="" />
					          		</label>
					          		<label class="pr15">排序
					          			<input id="tpSort" type="text" style="width: 15px;" maxlength="4" name="tpSort" value="" />
					          			<span style="color:red;">*</span>
					          		</label>
					          		<label class="pr15">文字内容:
					          			 <textarea id="contents_num1" name="contents" rows="4" cols="5" style="width: 350px" maxlength="999"></textarea>
					          		</label>
					          	    <input id="titAndPicBtn_num1" type="button" value="添加 " class="btn btn-info" onclick="addTitAndPicHang();"/>
					          	    <span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"></span>
										<div style="display: none">
											<input id="tpFile_num1" name="tpFile_num1" type="file"  onchange="upload('tpFile_num1','tpicUrl_num1','tpPic_num1','4')"/>
										</div>
				          	</div>
				  		</div>
				  	</c:if>
				  	</c:if>
	          	</div>
				<div align="center">
					<div class="pd5">
						<input type="button" value="保存" class="btn btn-info" onclick="saveContent();"/>
		          		<input type="button" value="取消" class="btn btn-info" onclick="hideConDiv(${baseId});"/>
		          	</div>
			    </div>
		</div>
	</form>
</body>
</html>