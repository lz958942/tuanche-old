<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
 <script type="text/javascript" src="/js/jquery.validate.js"></script>
 <script type="text/javascript">
//上传图片
 function upLoadImage(){
	 var fileId = $(this).val();
	 upload(fileId,"imgFile","imageShow",3);
 }
$().ready(function() {
	 $("#contentForm").validate();
	});
</script>
<form action="/content/add" method="post" style="padding:0 10px 0 10px; margin-top:0px;background-color:#fff"  id="contentForm">
	<div class="borderDiv">
		<input type="hidden" id="contentPlateId" name = "plateId">
		<input type="hidden" id="contentId" name="id" />
            <table>
            <tr>
            	<td>标题：</td>
                <td> 
					<input class="required" id="contentTitle" type="text" name="title" maxlength="50"/>
                </td>
            </tr>
            <tr>
            	<td>描述：</td>
                <td>
						<input class="required" id="contentDesc" type="text" name="descriction" maxlength="50"/>
                </td>
            </tr>
            <tr id="img_type">
            	<td>上传图片：</td>
                <td>	
						<input style="display: none" type="file" name="imgFile" id="imgFile"  onchange="upload('imgFile','imagUrl','imageShow','3')">
						<img id="imageShow" width="150px" alt="" src='/images/upload.jpg'  onclick="$('#imgFile').trigger('click')">
						<input  type="hidden" name="imagUrl" id="imagUrl"/>
                </td>
            </tr>
            <tr>
            	<td>超链接：</td>
                <td>
						<input class="required"  id="contentHyper"  type="text" name="hyperlink"   value="http://" maxlength="150" />
                </td>
            </tr>
             <tr id="carStyle">
            	<td>车型ID：</td>
                <td>	
                		<input id="styleId" name="carTypeId" type="hidden" />
						<input id="selectCarStyle" readonly="readonly"  type="text"  onclick="selectCarStyleshow()">
                </td>
            </tr>
            <tr id="groupLeader">
            	<td>团长ID：</td>
                <td>
						<input id="groupLeaderId"  type="text"  name="groupLeaderId" class="number" maxlength="5"/>
                </td>
            </tr>
            <tr>
            	<td>内容说明：</td>
                <td>
                	<textarea id="expand" name="expand" rows="5" cols="10" maxlength="300"></textarea>
                </td>
            </tr>
            <tr>
            	<td><button type="submit">保存</button></td>
                <td><button type="button"  onclick="javascript:closeDiv();">取消</button></td>
            </tr>
        </table>
	</div>
</form>

