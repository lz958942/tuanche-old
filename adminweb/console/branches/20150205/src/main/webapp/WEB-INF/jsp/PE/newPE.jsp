<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<html>
<head>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common/jsdate.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/ueditorlang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){ 
		var pid=$("#brind").val();
		if(pid!=null && ""!=pid){
			 $.ajax({
				   type: "POST",
				   url: "/json/carstyle/ajaxStyle",
				   dataType:'json',
				   data: {
					   'brandID':pid
				   },
				   success: function(data){
					 $("#keywordid").val("");
					 $("#keywordid").append("<option selected='selected' value=''>请选择</option>");
					   for(i in data) {
					   		if(data[i].id==$("#carid").val()){
					   			$("#keywordid").append("<option  selected='selected'  value="+data[i].id+">"+data[i].style+"</option>");
					   		}else{
					   			$("#keywordid").append("<option  value="+data[i].id+">"+data[i].style+"</option>");
					   		}
					   }
					   } 
				
				});
		}
		　　}); 
	
	
	function selectCar(pid){
		 $("#keywordid option").remove();
		 if($("#brind").val()!=null&&$("#brind").val()!=""){
			$.ajax({
				   type: "POST",
				   url: "/json/carstyle/ajaxStyle",
				   dataType:'json',
				   data: {
					   'brandID':pid
				   },
				   success: function(data){
					 $("#keywordid").val("");
					 $("#keywordid").append("<option selected='selected' value=''>请选择</option>");
					   for(i in data) {
					   		$("#keywordid").append("<option value="+data[i].id+">"+data[i].style+"</option>");
					   }
					   } 
				});
		 }
	}
	
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
	function upload(id){
		var imagesize=0;
		var delSrc=$("#delSrc").val();
		var url="/json/PE/picUpload?picId="+id;
		var ss=$("#"+id).each(function(){
			if(this.files[0].size>2000000){
				
				imagesize=this.files[0].size
				return;
			}
		});
		if(imagesize>2000000){
			alert("图片过大，超出2M！");
			return;
		}
		if(!checkImg(id)){
			return;
		}
		if(delSrc!=null && delSrc!=""){
			url="/json/PE/picUpload?picId="+id+"&delSrc="+delSrc;
		}
		$.ajaxFileUpload({
			url:url,
			fileElementId:id,
			dataType: 'json',
			success: function (data){
				if(isNaN(data)){//成功
					var args=data.trim();
					$("#listPic").val(args);
					$("#oneImage").attr("src",args);
					var delSrc=args.substring(args.lastIndexOf("/"),args.length);
					if(delSrc!=null && delSrc!=""){
						$("#delSrc").val(delSrc);
					}
					alert("上传成功");
				}else{
					alert("上传失败");
				}
			},

		});
	}
	//检查文件格式
	function checkImg(id){
		var fileName = $("#"+id).val(), //文件名称
		fileType=["jpg","png","gif","bmp"], //图片类型
		fileExt = ""; //图片拓展名
		fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
		for(var i in fileType){
			if(fileExt==fileType[i]){
				return true;
			}
		}
		return false;
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
		return sta;
	}
	</script>
</head>
<body>
<div class="ztsx">
	<label><strong>添加个人案例</strong></label>
</div>
<input type="hidden" id="carid" value="${bean.styleId }">
<form action="/PE/savePE" method="post" style="padding:0 10px 0 10px; margin-top:0px" name="zixun" id="newZixunPropertiesForm">
	<div class="borderDiv">
		<table>
			<tr>
				<td>
				<span style="width:100px;padding-right:16px; vertical-align:top">选择城市：</span>
				</td>
				<td>
				<select name="cityId" id="cityId" style="width: 200px">
						<option value="" selected="selected">请选择城市</option>
						<c:forEach items="${citys }" var="city">
							<option  <c:if test="${(bean!=mull ? bean.cityId:10)==city.value.id }">selected='selected'</c:if>  value="${city.value.id }">${city.value.name }</option>
						</c:forEach>
						</select>
				</td>
				<td>
					<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top">请选择品牌：</span>
				</td>	
				<td>
					<select id="brind" name="brandId" style="width: 200px" onchange="selectCar(this.options[this.options.selectedIndex].value)">
					<option value="">请选择</option>
						<c:forEach items="${brand}" var="b">
							<option <c:if test="${bean.brandId==b.id }">selected='selected'</c:if>  value="${b.id }">${b.reviewInitial}${b.name}</option>
						</c:forEach>
					</select>
				</td>
				</tr>
				<tr>
				<td>
				<span style="width:100px;padding-right:16px; vertical-align:top">请选择车型：</span>
				</td>
				<td>
				<c:if test="${bean!=null }">
					<select name="styleId" id="keywordid" style="width: 200px" >
					</select>
				</c:if>	
				<c:if test="${bean==null }">
					<select name="styleId" id="keywordid" style="width: 200px" >
						<option selected="selected" value="">请选择</option>
						<c:forEach items="${cars }" var="c">
						<option <c:if test="${bean.styleId==b.id }">selected='selected'</c:if> value="${b.id }">${b.style }</option>
						</c:forEach>
					</select>
				</c:if>	
				</td>
				<td>
				<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top">价格给力度：</span>
				</td>
				<td>
					<select name="priceEvaluate" id="source" style="width: 200px">
							<option  selected="selected" value="">请选择</option>
							<option  value="1" <c:if test="${bean.priceEvaluate ==1}">selected="select"</c:if>>一星</option>
							<option  value="2" <c:if test="${bean.priceEvaluate ==2}">selected="select"</c:if> >二星</option>
							<option  value="3" <c:if test="${bean.priceEvaluate ==3}">selected="select"</c:if>>三星</option>
							<option  value="4" <c:if test="${bean.priceEvaluate ==4}">selected="select"</c:if>>四星</option>
							<option  value="5" <c:if test="${bean.priceEvaluate ==5}">selected="select"</c:if>>五星</option>
						</select>
				</td>
				<td>
					<span style="width:100px;padding-right:16px;padding-left:16px;vertical-align:top"><!--选择列表图片：--></span>
				
					<div style="display: none">
					<input id="listPicFile" name="listPicFile" type="file" onchange="upload('listPicFile')"/>
					</div>
				</td>
				<tr>
		</table>
		
	</div>
	<div class="borderDiv">
		<table>
            <tr>
            	<td>
          	    	选择列表图：
          	    </td>
          	    <td>
          	    <img id="oneImage" src="${(bean.picture!=null && bean.picture!='' ) ?bean.picture:'/images/selectPiuc.jpg'}" width="60" height="40" onclick="$('#listPicFile').trigger('click')"/>
          	    <input type="hidden" id="listPic" name="picture" value="${bean.picture }"/>
          	     <input type="hidden" id="delSrc" name="delPic" value="" />
          	    </td>
            </tr>
             <tr>
            	<td>
          	    	添加用户：
          	    </td>
          	    <td>
          	     <input type="text" id="userName" name="userName" value="${bean.userName }"  maxlength="5"/>
          	    </td>
            </tr>
            <tr>
            	<td style="vertical-align:top">案例内容：<span style="color:red;">*</span></td>
            	<td>
            		<div style="width: 850;height: 450px">
	            		<script type="text/plain" id="myEditor">${bean.content}</script>
	            		<script style="width:850px;height: 650" type="text/javascript">
						     editor = new baidu.editor.ui.Editor({
						        textarea:'content',
						    });
						    editor.render("myEditor");
						</script>
						<input id="newZixunBtn" type="submit" value="保存" onClick="save();"/>
						<input type="hidden" name="id" value="${bean.id }">
					</div>
            	</td>
            	<td>
            	</td>
            </tr>
        </table>
	</div>
</form>
</body>
</html>
