<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/flowStat/flowStat.js"></script>
<script type="text/javascript" src="/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="/js/fusioncharts/themes/fusioncharts.theme.zune.js"></script>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<style type="text/css">
	.uploadCss{
		max-width: 350px;
		max-height: 92px;
	}
</style>
<title>流量统计管理</title>

</head>
<body>
<form action="/flowStatManage/flowStatView" method="post" id="flowStatSearchForm" name="flowParam">
	<div id="man_zone">
		<div>
			<div class="b-con a-form">
	          	<div class="pd5">
	         		<label class="pr15">
	         		
	       				日期:
	       				<div class="input-prepend">
	                   		<span class="add-on"><i class="icon-calendar"></i></span>
	                   		<input type='text' id="startDate" name='startDate' class="inptime span2" value="${flowParam.startDate }" autocomplete="off" />
	                   	</div>-
	                  	<div class="input-prepend">
	                    	<span class="add-on"><i class="icon-calendar"></i></span>
	                    	<input type='text' id="endDate" name='endDate' class="inptime span2" value="${flowParam.endDate }" autocomplete="off" />
	                   	</div>
	          		</label>
	          		<label class="pr15">
	                   	<input id="zuotian" name='riqi' type="radio"  onchange='zuotian1()'/>昨天
	                </label>
	         			<input id="qiantian" name='riqi' type="radio"  onchange='qiantian1()'/>前天
	                   	<input id="qitian" name='riqi' type="radio"  onchange='qitian1()'/>最近7天
	                   	<input id="sanshitian" name='riqi' type="radio"  onchange='sanshitian1()'/>最近30天
	          		
	          	
					<label class="pr15">当前网站：
						<select id="domain" name="domain">
						<option value=''>--请选择--</option>
						<c:forEach items="${domains}" var="domain">
						<option value='${domain.domain}' <c:if test="${domain.domain==flowParam.domain}">selected='selected'</c:if>>${domain.domain}</option>
						</c:forEach>
						</select>
						
					</label>
					<label class="pr15">数据来源：
					
							<select id="from" name="from">
								<option value="" selected>请选择</option>
								<option value="百度统计" <c:if test="${flowParam.from == '百度统计' }">selected</c:if>>百度统计</option>
								<option value="网页日志" <c:if test="${flowParam.from == '网页日志' }">selected</c:if>>网页日志</option>
							</select>
					</label><br>任意月：
					<label class="pr15" >
					<select id='nian' onchange="yuefen1()">
					<option value=''>--请选择--</option>
					<option value='2013'>2013</option>
					<option value='2014'>2014</option>
					<option value='2015'>2015</option>
					<option value='2016'>2016</option>
					<option value='2017'>2017</option>
					<option value='2018'>2018</option>
					<option value='2019'>2019</option>
					<option value='2020'>2020</option>
					</select>
							</label>
					<select id='yuefen' onchange="yuefen1()">
					<option value=''>--请选择--</option>
					<option value='1'>1</option>
					<option value='2'>2</option>
					<option value='3'>3</option>
					<option value='4'>4</option>
					<option value='5'>5</option>
					<option value='6'>6</option>
					<option value='7'>7</option>
					<option value='8'>8</option>
					<option value='9'>9</option>
					<option value='10'>10</option>
					<option value='11'>11</option>
					<option value='12'>12</option>
					</select>
			
					<label class="pr15" >数据类型：
		          			<select id="dataType" name="dataType">
								<option value="" selected>请选择</option>
								<option value="seo" <c:if test="${flowParam.dataType == 'seo'||flowParam.dataType == '1' }">selected</c:if>>SEO</option>
								<option value="sem" <c:if test="${flowParam.dataType == 'sem'||flowParam.dataType == '2' }">selected</c:if>>SEM</option>
							</select>
							</label>
							<select id="type" name="type">
								<option value="" selected>请选择</option>
								<option value="pv" <c:if test="${flowParam.type == 'pv' }">selected</c:if>>PV</option>
								<option value="uv" <c:if test="${flowParam.type == 'uv' }">selected</c:if>>UV</option>
							</select>
		          	
	          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;&nbsp;
	          		<a href="/flowStatManage/download" class="btn btn-info download" id="download">下载</a>
	          		<input id="resetFlowCondition" type="button" value="清空" class="btn btn-info"/>
	          	</div>
	         </div>
	         <div style="height: 10px"></div>
	         <div>
				<div class="b-con a-form" style="height: 30px">
					<div class="pd5">
	         		<label class="pr15">
	       				日期:
	       				<div class="input-prepend">
	                   		<span class="add-on"><i class="icon-calendar"></i></span>
	                   		<input type='text' id="startDateExcel" name="startDateExcel" class="inptime span2" value="" autocomplete="off" />
	                   	</div>-
	                  	<div class="input-prepend">
	                    	<span class="add-on"><i class="icon-calendar"></i></span>
	                    	<input type='text' id="endDateExcel" name="endDateExcel" class="inptime span2" value="" autocomplete="off" />
	                   	</div>
	          		</label>
                     	<input id="uploadFile" name="file" type="file" class="exFile"/>
                     	<input class="btn btn-info" id="uploadExcel" type="button" value="导入" />&nbsp;&nbsp;
		          		<input id="addFlowStat" type="button" value="添加" class="btn btn-info"/>
                     </div>
		         </div>
			</div>
		</div>
		<div class="rb-con">
	         <div id="chartContainer"></div>
				<div class="over-auto">
							总数据来源：<c:if test="${flowParam.from == '百度统计'}">百度统计</c:if>
									<c:if test="${flowParam.from == '网页日志'}">网页日志</c:if>
									<c:if test="${flowParam.from == ''||flowParam.from==null}">全部数据</c:if>
					<table class="table table-bordered chargeTable">
						<tr>
							<td style="text-align: center;">日期</td>
							<c:if test="${flowParam.dataType==null||flowParam.dataType==''}">
							<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
							<td style="text-align: center;">总PV</td>
							</c:if>
							<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
							<td style="text-align: center;">总UV</td>
							</c:if>
							</c:if>
							<c:if test="${flowParam.dataType==null||flowParam.dataType==''||flowParam.dataType=='seo'}">
							   <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								<td style="text-align: center;">SEOPV</td>
								</c:if>
								 <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
								     <td style="text-align: center;">SEOUV</td>
							     </c:if>
							
							</c:if>
							<c:if test="${flowParam.dataType==null||flowParam.dataType==''||flowParam.dataType=='sem'}">
							   <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								  <td style="text-align: center;">SEMPV</td>
								</c:if>
								 <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">     
							          <td style="text-align: center;">SEMUV</td>
							     </c:if>
							
							</c:if>
							
							<td style="text-align: center;">数据来源</td>
							<!-- <td style="text-align: center;">操作</td> -->
						</tr>
						<c:forEach items="${flowStatList}" var="bean">
							<tr>
								<td style="text-align: center;" width="50px">${bean.date}</td>
								<c:if test="${flowParam.dataType==null||flowParam.dataType==''}">
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								<td style="text-align: center;" width="200px">${bean.pv}</td>
								</c:if>
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
								<td style="text-align: center;" width="100px">${bean.uv}</td>
								</c:if>
								</c:if>
								
								<c:if test="${flowParam.dataType==null||flowParam.dataType==''||flowParam.dataType=='seo'}">
								  <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								<td style="text-align: center;" width="100px">${bean.tpv}</td>
								</c:if>
								  <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
								<td style="text-align: center;" width="100px">${bean.tuv}</td>
								</c:if>
								</c:if>
								
								<c:if test="${flowParam.dataType==null||flowParam.dataType==''||flowParam.dataType=='sem'}">
								 <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								<td style="text-align: center;" width="100px">${bean.mpv}</td>
								</c:if>
								 <c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
								<td style="text-align: center;" width="100px">${bean.muv}</td>
								</c:if>
								</c:if>
								<td style="text-align: center;" width="100px">${bean.from}</td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(flowStatList) == 0}">
							<tr>
								<td colspan="10">没有数据</td>
							</tr>
						</c:if>
			          </table>
			          	<table class="table table-bordered chargeTable">
			          	<c:if test="${flow!=null}">
							<tr>
								<td style="text-align: center;" width="50px">日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;均</td>
								<c:if test="${flowParam.dataType==null||flowParam.dataType==''}">
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								<td style="text-align: center;" width="200px">${flow.pv}</td>
								</c:if>
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
								<td style="text-align: center;" width="100px">${flow.uv}</td>
								</c:if>
								</c:if>
								<c:if test="${flowParam.dataType==null||flowParam.dataType==''||flowParam.dataType=='seo'}">
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								<td style="text-align: center;" width="100px">${flow.tpv}</td>
								</c:if>
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
								<td style="text-align: center;" width="100px">${flow.tuv}</td>
								</c:if>
								</c:if>
								<c:if test="${flowParam.dataType==null||flowParam.dataType==''||flowParam.dataType=='sem'}">
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='pv'}">
								<td style="text-align: center;" width="100px">${flow.mpv}</td>
								</c:if>
								<c:if  test="${flowParam.type==null||flowParam.type==''||flowParam.type=='uv'}">
								<td style="text-align: center;" width="100px">${flow.muv}</td>
								</c:if>
								
								</c:if>
								
								<td style="text-align: center;" width="100px">
									<c:if test="${flowParam.from == '百度统计'}">百度统计</c:if>
									<c:if test="${flowParam.from == '网页日志'}">网页日志</c:if>
									<c:if test="${flowParam.from == ''||flowParam.from==null}">全部数据</c:if>
								</td>
							</tr>
						</c:if>
			          	</table>
			     </div>
		</div>
	</div>
	<div class="page_and_btn" style="text-align:center;">
	   	<jsp:include page="/WEB-INF/snippets/page.jsp" />
	</div>
</form>

</body>
<script type="text/javascript">
$('.download').bind('click',function(e){
	e= e || window.event;
	e.preventDefault();
	var href=$(this).attr('href')+'?1=1'+
			($("#startDate").val()?'&startDate='+$("#startDate").val():"")+
			($("#endDate").val()?'&endDate='+$("#endDate").val():"")+
			($('select[name=domain]').val()?'&domain='+$('select[name=domain]').val():"")+
			($('select[name=from]').val()?'&from='+$('select[name=from]').val():"")+
			($('select[name=dataType]').val()?'&dataType='+$('select[name=dataType]').val():"")+
			($('select[name=type]').val()?'&type='+$('select[name=type]').val():"");
	window.location.href=href;
});

function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}
function zuotian1(){
	$("#startDate").attr("value",""+GetDateStr(-1));+GetDateStr(-2)
	$("#endDate").attr("value",""+GetDateStr(-1));
}
function qiantian1(){
	$("#startDate").attr("value",""+GetDateStr(-2));
	$("#endDate").attr("value",""+GetDateStr(-2));
}
function qitian1(){
	$("#startDate").attr("value",""+GetDateStr(-8));
	$("#endDate").attr("value",""+GetDateStr(-1));	
}
function sanshitian1(){
	$("#startDate").attr("value",""+GetDateStr(-31));
	$("#endDate").attr("value",""+GetDateStr(-1));	
}
function yuefen1(){
	var year=$("#nian").val();
	var mounth=$("#yuefen").val();
	if(year==''||year==null){
		year=2014;
	}
	if(mounth==''||mounth==null){
		mounth=1;
	}
	$("#startDate").attr("value",year+"-"+mounth+"-"+"01");
	$("#endDate").attr("value",year+"-"+mounth+"-"+DayNumOfMonth(year,mounth));
}
function DayNumOfMonth(Year,Month)
{
    Month--;
    var d = new Date(Year,Month,1);
    d.setDate(d.getDate()+32-d.getDate());
    return (32-d.getDate());
}
</script>
</html>