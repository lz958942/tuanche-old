<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>添加品牌团购</title>
</head>
<body>
<div id="man_zone">
<form method='post' id='form' action='/groupbuy/badd.do'>
	<input type="hidden" name="id" value="${groupbuy.id}" />
	<input name="cityId" type="hidden" value="${groupbuy.cityId}" />
	<input name="brandId" type="hidden" value="${groupbuy.brandId}" />
	<input name="token" type="hidden" value="${groupbuy.token}" />
	<div>
	<table class="table table-bordered" style="margin:0 0 10px 0;">
			<thead>
                	<tr>
                    	<th colspan="4" style="text-align:center">${groupbuy.id>0?"修改":"添加"}${func:getDistrictName(groupbuy.cityId)}${func:getBrandName(groupbuy.brandId)}团购</th>
                    </tr>
                </thead>
                <tbody>
				<tr>
					<td>选择团长</td>
					<td>
						<select name="salerId">
		        			<option value="0">请选择</option>
		        			<c:forEach var="sal" items="${sales}">
		        				<option value="${sal.id}" ${groupbuy.salerId==sal.id?"selected":""}>${sal.name}</option>
		        			</c:forEach>
        				</select>
					</td>
					<td><font class='text-error'>*</font>团购标题</td>
					<td>
						<input type="text" name="title" maxlength="50" class="{required:true}" value="${groupbuy.title}" />
					</td>
				</tr>
				<tr>
					<td>团购亮点</td>
					<td>
						<textarea style="height: 151px;width: 410px" name="groupbuyLight" maxLength="255">${groupbuy.groupbuyLight}</textarea>
					</td>
					<td>团购地点</td>
					<td>
						<textarea maxlength="50" name="groupbuyPlace" maxLength="50">${groupbuy.groupbuyPlace}</textarea>
					</td>
				</tr>
				<tr>
					<td><font class='text-error'>*</font>带团期数基数</td>
					<td><input type="text" name="baseNum" class="span2 {required:true,number:true}" value="${groupbuy.baseNum}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " maxlength="5" /></td>
					<td>开团时间</td>
					<td><input type="text" class="querytime span2" name="groupbuyDate" readonly="readonly" autocomplete="off" value="<fmt:formatDate value="${groupbuy.groupbuyDate}" pattern="yyyy-MM-dd" />" /></td>
				</tr>
			
				<%-- <tr>
					<td>开团时间</td>
					<td><input type="text" class="inptime span2 {required:true}" name="groupbuyDate" readonly="readonly" autocomplete="off" value="<fmt:formatDate value="${groupbuy.groupbuyDate}" pattern="yyyy-MM-dd" />" /></td>
					<td>是否确认开团</td>
					<td>
						<label class="radio inline"><input type="radio" name="groupbuyState" value="0" class="{required:true}" checked="checked" ${groupbuy.groupbuyState==0?"checked":""} />否</label>
						<label class="radio inline"><input type="radio" name="groupbuyState" value="1" class="{required:true}" ${groupbuy.groupbuyState==1?"checked":""} />是</label>
					</td>
				</tr> --%>
				<tr>
				<td>温馨提示：</td>
				<td><input type="text" name="prompt" maxlength="100" style="width: 500px" value="${groupbuy.prompt }"></td>
					<td colspan="4" >
        				<div class="text-center"><input type='submit' value='提交' class="btn-info btn" /></div>
        			</td>
				</tr>
		</table>
	</div>
</form>
</div>
<script type="text/javascript" charset="utf-8">
$("#form").validate({
	errorPlacement: function(error, element) {
		error.addClass('help-inline');
        error.insertAfter(element);
    },
    wrapper: "span",
    submitHandler:function(form){
    	form.submit();
    }
});
$(".querytime").click(function(){
	WdatePicker({
		isShowClear:true,
		qsEnabled:false,
		readOnly:true,
		dateFmt:"yyyy-MM-dd",
		minDate:getToday()
	});
});

function getToday(){
	data=new Date();
	return data.getFullYear()+"-"+(data.getMonth()+1)+"-"+data.getDate();
}
</script>
</body>
</html>