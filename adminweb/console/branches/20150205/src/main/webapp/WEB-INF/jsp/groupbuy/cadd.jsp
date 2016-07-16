<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>添加车型团购</title>
</head>
<body>
<div id="man_zone">
	<form method='post' id='form' action='/groupbuy/cadd.do'>
		<input type="hidden" name="id" value="${groupbuy.id}" />
		<input name="cityId" type="hidden" value="${groupbuy.cityId}" />
		<input name="carstyleId" type="hidden" value="${groupbuy.carstyleId}" />
		<input name="token" type="hidden" value="${groupbuy.token}" />
		<input name="brandId" type="hidden" value="${groupbuy.brandId}" />
		<input name="sort_old" type="hidden" value="${groupbuy.baseSeq}" />
		<div>
			<table class="table table-bordered" style="margin:0 0 10px 0;">
				<thead>
                	<tr>
                    	<th colspan="4" style="text-align:center">${groupbuy.id>0?"修改":"添加"}${func:getDistrictName(groupbuy.cityId)}${groupbuy.carstyleName}团购</th>
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
					<td>开团时间</td>
					<td><input type="text" class="querytime span2" name="groupbuyDate" readonly="readonly" autocomplete="off" value="<fmt:formatDate value="${groupbuy.groupbuyDate}" pattern="yyyy-MM-dd" />" /></td>
				</tr>
				<tr>
					<td><font class='text-error'>*</font>本期基数</td>
					<td><input maxlength="4" type="text" name="manBaseNum" class="span2 {required:true,number:true}" value="${groupbuy.manBaseNum}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " /></td>
					<td><font class='text-error'>*</font>历史基数</td>
					<td><input maxlength="4"  type="text" name="passBaseNum" class="span2 {required:true,number:true}" value="${groupbuy.passBaseNum}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "/></td>
				</tr>
				<tr>
					<td><font class='text-error'>*</font>卖出基数</td>
					<td><input maxlength="5"  type="text" name="sellBaseNum" class="span2 {required:true,number:true}" value="${groupbuy.sellBaseNum}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "/></td>
					<td><font class='text-error'>*</font>节省钱数</td>
					<td><input  maxlength="10" type="text" name="saveMoney" class="span2 {required:true}" value="${groupbuy.saveMoney}"  onkeyup="this.value=this.value.replace(/[^\d]/g,'') "/></td>
				</tr>
				
					<tr>	
					
					<td><font class='text-error'>*</font>展示顺序</td>
					<td><input type="text" name="baseSeq" class="span2 {required:true,number:true}" value="${groupbuy.baseSeq}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " maxlength="5" /> (决定品牌页面车型展示顺序)</td>
					<td>推广车型名</td>
					<td><input type="text" name="carNice" class="span2 {required:true,number:false}" value="${empty groupbuy.carNice?groupbuy.carstyleName:groupbuy.carNice}" maxlength="20" /></td>
					
				</tr>
				
				<%-- <tr>
					<td>是否确认开团</td>
					<td>
						<label class="radio inline"><input type="radio" name="groupbuyState" value="0" class="{required:true}" checked="checked" ${groupbuy.groupbuyState==0?"checked":""} />否</label>
						<label class="radio inline"><input type="radio" name="groupbuyState" value="1" class="{required:true}" ${groupbuy.groupbuyState==1?"checked":""} />是</label>
					</td>
				</tr> --%>
				<tr>
					<td colspan="4" >
        				<div class="text-center"><input type='submit' value='提交' class="btn-info btn" /></div>
        			</td>
				</tr>
				</tbody>
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