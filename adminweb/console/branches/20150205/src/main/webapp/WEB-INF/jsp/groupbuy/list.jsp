<%@page import="com.tuanche.console.web.AuthUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ taglib prefix="npage" uri="/WEB-INF/pagetag.tld"%>
<jsp:include page="/inc/header.jsp" flush="false"></jsp:include>
<title>团购列表</title>
</head>


<body>
<%
	boolean isBdel=AuthUtil.checkAuth(request,"/groupbuy/bdel");
	boolean isCdel=AuthUtil.checkAuth(request,"/groupbuy/cdel");
	boolean isBadd=AuthUtil.checkAuth(request,"/groupbuy/badd");
%>
<div id="man_zone">
	<div>
		<div class="b-con a-form">
		<input type="hidden" id="carStyleSta" value="${search.carstyleId }">
          <form method='post' action='/groupbuy/list' id="form4" >
          	<div class="pd5">
          		<label class="pr15">选择城市:
          			<select id="cityId" name="cityId">
						<option value="">请选择</option>
						<c:forEach var="city" items="${citys}">
							<option value="${city.value.id}" ${search.cityId==city.value.id?"selected":"" }>${city.value.orderName}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">选择品牌:
          			<select id="bid" name="brandId">
						<option value="">请选择</option>
						<c:forEach var="brand" items="${brands}">
							<option value="${brand.key}" ${search.brandId==brand.key?"selected":""}>${brand.value.orderName}</option>
						</c:forEach>
					</select>
          		</label>
          		<label class="pr15">选择车型:
          			<select  id="carstyleId" name="carstyleId">
						<option value="">请选择</option>
					</select>
          		</label>
          		<label class="pr15">团购状态:
          			<select name="stateName" id="stateName">
						<option  value="">请选择</option>
						<option <c:if test="${search.stateName!=null && search.stateName==1 }"> selected='selected'</c:if> value="1">开团</option>
						<option  <c:if test="${search.stateName!=null && search.stateName==0 }"> selected='selected'</c:if> value="0">未开团</option>
					</select>
          		</label>
          		<input type="submit" value="查询" class="btn btn-info"/>&nbsp;
          		<% if(isBadd) { %>
          		<a id="badd" href="/groupbuy/badd" class="btn btn-info">添加品牌团购</a>&nbsp;
          		<a id="cadd" href="/groupbuy/cadd" class="btn btn-info">添加车型团购</a>
          		<a id="caddbach" href="#" class="btn btn-info">批量修改亮点</a>
          		<% } %>
          	</div>
          </form>
         </div>
	</div>
	<div class="rb-con">
		<div class="over-auto">
			<table class="table table-bordered chargeTable">
				<thead>
					<th>编号</th>
					<th>标题</th>
					<th>城市</th>
					<th>品牌</th>
					<th>团长</th>
					<th>团购时间</th>
					<th>团购状态</th>
					<th>状态</th>
					<th>操作</th>
	          	</thead>
	          	<tbody>
	          		<c:forEach var="data" items="${groupbuyList}">
	          			<tr>
	          				<td>${data.id}</td>
	          				<td>
	          					<c:if test="${search.carstyleId==null}">
	          						<img src="/images/11.gif" id="show_list" data-value="${data.cityId}-${data.brandId}" />&nbsp;&nbsp;
	          					</c:if>
	          					${data.title}
	          				</td>
	          				<td>${func:getDistrictName(data.cityId)}</td>
	          				<td>${func:getBrandName(data.brandId)}</td>
	          				<td>${data.salerName}</td>
	          				<td><fmt:formatDate value="${data.groupbuyDate}" pattern="yyyy-MM-dd" /></td>
	          				<td>${data.stateName}</td>
	          				<td>${data.delName}</td>
	          				<td>
	          				<% if(isBadd){ %>
	          				<a href="#"  tmpHref="${addAction}?id=${data.id}&token=${data.upToken}" onclick="brandUpdate(this,1)">修改</a>
	          				<% } %>
	          				<% if(isBdel){ %>
	          				&nbsp;<a class="del" href="${delAction}?cityId=${data.cityId}&brandId=${data.brandId}&id=${data.id}&state=${data.isdel==0?-1:0}&token=${data.upToken}">${data.isdel==0?"删除":"恢复"}</a>
	          				<% } %>
	          				</td>
	          				
	          			</tr>
	          		</c:forEach>
	          	</tbody>
	        </table>
	    </div>
	    <div class="table-page">
          			<div class="page tcenter">
            			<div class="pageWrap">
              				<npage:npage pager="${pb}" />
            			</div>
          			</div>
      			</div>
	</div>
</div>
	<div  id="kevinDiv1"  style="position:fixed;display:none;margin-left: auto;margin-right: auto;z-index:9;height:;width:500px;border:1px solid #ddd;left:210px;top:110px;border-bottom: none !important;background-color:#8F8FBD;">
				<input type="hidden" id="replyID">
				<center><font>批量修改亮点</font></center><br>
				<font>城市：</font>
				<select   name="cityIdd" onchange="kevinAjax()">
						<option   value="">请选择</option>
						<c:forEach var="city" items="${citys}">
							<option value="${city.value.id}">${city.value.orderName}</option>
						</c:forEach>
					</select>
					<font>团购状态：</font>
					<select onchange="kevinAjax()" name="stateNamee">
						<option  value="">请选择</option>
						<option  value="1">开团</option>
						<option  value="0">未开团</option>
					</select>
					<center><textarea id="replyContent" name="content" style="width: 450px;height: 150px" rows="3" cols="31" maxlength="250"></textarea><br> <input id="kevinButton" type="button" value="批量修改" onclick="reply()"> <input id="kevinButtonClose" onclick="clossKevinDic1()" type="button" value="关闭"></center>
			</div>
<script type="text/javascript" charset="utf-8">
	$('select[name=brandId]').live('change',function(){
		$('select[name=carstyleId] option:gt(0)').remove();
		if($(this).val()){
			$.post('/common/getCarstyleOk',{"brandId":$(this).val()},function(data){
				for(var i=0;i< data.length;i++){
					$('select[name=carstyleId]').append('<option value="'+data[i].id+'" '+('${search.carstyleId}'==data[i].id?"selected":"")+'>'+data[i].style+'</option>');
				}
				/* $.each(data,function(m,n){
					$('select[name=carstyleId]').append('<option value="'+m+'" '+('${search.carstyleId}'==m.id?"selected":"")+'>'+n.style+'</option>');
				}); */
			},'json');
		}
		
	});
	if('${search.carstyleId}' || '${search.brandId}'){
		$('select[name=brandId]').change();
	}
	$('#badd').live('click',function(e){
		e= e || window.event;
		e.preventDefault();
		var token;
		if(!$('select[name=cityId]').val()){
			alert('请选择城市!');
			$('select[name=cityId]').focus();
			return false;
		}
		if(!$('select[name=brandId]').val()){
			alert('请选择品牌!');
			$('select[name=brandId]').focus();
			return false;
		}
		$.ajax({async:false,type:'post',url:'/common/checkgroupbuy',dataType:'json',data:{cityId:$('select[name=cityId]').val(),brandId:$('select[name=brandId]').val(),carstyleId:0},success:function(data){
			token=data;
		}});
		if(!token){
			alert('该城市下该品牌团购已经存在');
			return false;
		}
		window.location.href=$(this).attr('href')+'?cityId='+$('select[name=cityId]').val()+'&brandId='+$('select[name=brandId]').val()+'&token='+token;
		
	});
	
	
	$('#cadd').live('click',function(e){
		e= e || window.event;
		e.preventDefault();
		var token;
		if(!$('select[name=cityId]').val()){
			alert('请选择城市!');
			$('select[name=cityId]').focus();
			return false;
		}
		if(!$('select[name=brandId]').val()){
			alert('请选择品牌!');
			$('select[name=brandId]').focus();
			return false;
		}
		if(!$('select[name=carstyleId]').val()){
			alert('请选择车型!');
			$('select[name=carstyleId]').focus();
			return false;
		}
		$.ajax({async:false,type:'post',url:'/common/checkgroupbuy',dataType:'json',data:{cityId:$('select[name=cityId]').val(),carstyleId:$('select[name=carstyleId]').val(),brandId:$('select[name=brandId]').val()},success:function(data){
			token=data;
		}});
		if(!token){
			alert('该城市下该车型团购已经存在或者品牌团购不存在');
			return false;
		}
		window.location.href=$(this).attr('href')+'?cityId='+$('select[name=cityId]').val()+'&carstyleId='+$('select[name=carstyleId]').val()+'&token='+token+"&brandId="+$('select[name=brandId]').val();
	});
	$("[name='vv']").click(function(){
		alert(1);
	});
	$('#show_list').live('click',function(){
		$(this).attr("src",$(this).attr("src")=="/images/11.gif"?"/images/12.gif":"/images/11.gif");
		var cla=$('.'+$(this).attr("data-value"));
		if(cla.size()>0){
			cla.css('display')=="none"?cla.show():cla.hide();
			return false;
		}
		var that=this;
		$.post("/common/getCarstyleGroupbuy",{data:$(this).attr("data-value")},function(result){
			var str='';
			$.each(result,function(m,n){
				str+='<tr  class="'+$(that).attr("data-value")+'"><td>'+result[m].id+'</td><td><em><img src="/images/tree.gif" /></em>&nbsp;'+result[m].title+'</td><td>'+result[m].cityName+'</td><td>'+result[m].carstyleName+'</td><td>'+result[m].salerName+'</td><td>'+result[m].groupbuyDateStr+'</td><td>'+result[m].stateName+'</td><td>'+result[m].delName+'</td><td><% if(isBadd){ %><a  onclick= brandUpdate(this,2) tmpHref="/groupbuy/cadd?id='+result[m].id+'&token='+result[m].upToken+'">修改</a><% } %><% if(isCdel){ %>&nbsp;<a  class="del" href="cdel?cityId='+result[m].cityId+'&brandId='+result[m].brandId+'&id='+result[m].id+'&state='+(result[m].isdel==-1?0:-1)+'&token='+result[m].upToken+'">'+(result[m].isdel==-1?"恢复":"删除")+'</a><% } %></td></tr>';
			});
			$(that).closest('tr').after(str);
		},'json');
	});
	$('.del').live('click',function(e){
		e= e || window.event;
		e.preventDefault();
		if(window.confirm('你确定要删除该记录！')){
		$.ajax({async:false,type:'get',url:$(this).attr('href'),success:function(data){
			$('#pageForm').submit();
		}});
		}
	});
	
	$('#caddbach').live('click',function(e){
		e= e || window.event;
		e.preventDefault();
		var token;
		if(!$('select[name=cityId]').val()){
			alert('请选择城市!');
			$('select[name=cityId]').focus();
			return false;
		}
		if(!$('select[name=stateName]').val()){
			alert('请选团购状态!');
			$('select[name=stateName]').focus();
			return false;
		}
		if($('select[name=carstyleId]').val() || $('select[name=brandId]').val() ){
			alert("批量修改不能选择品牌以及车型！");
			return false;
		}
		var $kevincity=$('select[name=cityId]').val();
		var $kevinStateName=$('select[name=stateName]').val();
		$('select[name=cityIdd] option').each(function(){
			if($(this).val()==$kevincity){
				$(this).attr("selected","selected");
			}
		});
		$('select[name=stateNamee] option').each(function(){
			if($(this).val()==$kevinStateName){
				$(this).attr("selected","selected");
			}
		});
		kevinAjax();
		$("#kevinDiv1").show();
	});
	function clossKevinDic1(){
		$("#kevinDiv1").hide();
	}
	function reply(){
		  var $content= $.trim($("#replyContent").val());
		  if(!$('select[name=cityIdd]').val()){
				alert('请选择城市!');
				$('select[name=cityIdd]').focus();
				return false;
			}
		  if(!$('select[name=stateNamee]').val()){
				alert('请选择团购状态!');
				$('select[name=stateNamee]').focus();
				return false;
			}
			  if($content==null || ""==$content){
				  alert("请填写亮点内容！");
				  return;
			  }
			  $("#kevinButton").attr('disabled','disabled');
			  $("#kevinButtonClose").attr('disabled','disabled');
			  $.ajax({
				 	type: "POST",
				    url: "/groupbuy/list.do",
				    data:{cityIdd:$('select[name=cityIdd]').val(),stateName:$('select[name=stateNamee]').val(),content:$content},
				    success: function(data){
				    	 $("#kevinButton").removeAttr('disabled');
						 $("#kevinButtonClose").removeAttr('disabled');
				    	alert("批量修改成功，请关闭!");
				    	$("#form4").submit();
				    	$("#kevinDiv1").hide();
				   }
				 });
		  }
	function kevinAjax(){
		if(!$('select[name=cityIdd]').val() || !$('select[name=stateNamee]').val()){
			alert("请选择城市或者开团状态！");
			return false;
		}
	$.post("/common/getContentByCityId",{cityId:$('select[name=cityIdd]').val(),stateName:$('select[name=stateNamee]').val()},function(result){
			$("#replyContent").text(result);
		},'json');
	}
	function brandUpdate(type,sta){
		var cityId=$("#cityId").val(); 
		var bid=$("#bid").val(); 
		var carstyleId=$("#carstyleId").val();
		var stateName=$("#stateName").val();
		var tmeUrl=$(type).attr("tmpHref");
		var url="";
		var tmpCpage=$(".current").text();
		
		if(sta==1){
			//品牌
		url="/groupbuy/"+tmeUrl;
		}else{
			url=tmeUrl;
		}
		if(cityId){
			url+="&tmcCityId="+cityId;	
			}
		if(bid){
			url+="&tmcBrandId="+bid;	
			}
		if(carstyleId){
			url+="&tmcCarstyleId="+carstyleId;	
			}
		if(stateName){
			url+="&tmcStateName="+stateName;	
			}
		if(tmpCpage){
			url+="&tmpCpage="+tmpCpage;
		}
		window.location.href=url;
	}
	$(function(){
		var carStyleSta=$("#carStyleSta").val();
		var bid=$("#bid").val();
		if(carStyleSta && bid){
			$.post('/common/getCarstyle',{"brandId":bid},function(data){
				for(var i=0;i< data.length;i++){
					/* if(carStyleSta=data[i].id){
						$('select[name=carstyleId]').append('<option selected=selected value="'+data[i].id+'" '+('${search.carstyleId}'==data[i].id?"selected":"")+'>'+data[i].style+'</option>');	
					}else{
						
					} */
					$('select[name=carstyleId]').append('<option value="'+data[i].id+'" '+('${search.carstyleId}'==data[i].id?"selected":"")+'>'+data[i].style+'</option>');	
				}
			},'json');	
		}
	});
</script>
</body>
</html>