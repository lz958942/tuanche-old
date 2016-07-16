<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${city.dname}汽车团购|最专业的${city.dname}汽车团购网_团车网</title>
<meta name="keywords" content="${city.dname}汽车团购，汽车团购，${city.dname}汽车团购网，${city.dname}汽车网" />
<meta name="description" content="${city.dname}汽车团购，永远免费为${city.dname}车友提供专业的汽车团购服务，团购最低价格，团车网中国最大的汽车互联网交易和服务平台。热线：400-6969-123." />
<link type="text/css" rel="stylesheet" href="http://static.tuanche.com/tuanchepc/public/css/common.css" />
<link type="text/css" rel="stylesheet" href="http://static.tuanche.com/tuanchepc/public/css/flexslider-1-8.css" />
<link type="text/css" rel="stylesheet" href="http://static.tuanche.com/tuanchepc/default/css/index.css?v=150617" />
<link type="image/x-icon" rel="icon" href="http://static.tuanche.com/tuanchepc/public/imgcommon/favicon.ico"/>
<link type="image/x-icon" rel="shortcut icon" href="http://static.tuanche.com/tuanchepc/public/imgcommon/favicon.ico"/>
</head>
<body>
<#include  "new_city_head.ftl"/>
<#include  "new_brand_00.ftl"/>
        <!--选择车型品牌-->
        <div class="clearfix fr index-img">
            <!--焦点图-->
            <div id="banner" class="banner">
                <div id="banner-flexslider" class="flexslider">
                  <ul class="slides">
                   <#list 1..5 as num>
        			<#assign ad_='inrurn_image_ad'+num+''>
    				<#assign ad=ad_?eval!''>
    				<#if ad!=''>
					<li>
                      <a  href='${ad.adLink!""}' target="_blank">
                          <img src="${ad.picName}" data-url='${ad.picName}'  alt="${ad.adTitle}"/>
                      </a>
                    </li>
                       </#if>
    			</#list>            
                  </ul>
                </div>
            </div>       
            <!--焦点图-->                     
            <ul class="clearfix index-img-ul">
			<#list 1..3 as num>
    			<#assign ad_='small_image_ad'+num+''>
    			<#assign ad=ad_?eval!''>
    			<#if ad!=''>
                <li class="clearfix">
                  <a href="${ad.adLink!''}" target="_blank">
	    				<img src="${ad.picName}" alt="${ad.adTitle}" width="250" height="161" class="scrollLoading" data-url='${ad.picName}' />
	    				</a>
                </li>
				</#if>
    		</#list>
            </ul>            
        </div>
        
    </div>     
    <div class="clearfix">
        <!--热门团购-->
        <div class="indexIn-left">
          
          <#include  "new_brand_buy01.ftl"/>            
        </div>
        <!--热门团购-->
        <!--报名-->
        <div class="indexIn-right">
           <!--快速报名-->
            <div class="signUpSideIndex">
                <h3 class="sideBox-tit signUpSide-tit">团购报名</h3>
                <div class="clearfix"><a href="javascript:void(0);" ><img style="width:100%" class="signUpSideIndex-banner" src="http://static.tuanche.com/tuanchepc/default/images/stadeby.jpg" /></a></div>
                <div class="clearfix sideBox-con signUpSide-con">
                    <form id="indexForm" action="http://tuanche.com/service/signup/formapply" method="post">
						<input type="hidden" name="city" id="cityId" class="cityVal" value="${city.id?c}">
                        <div style="*z-index:4" class="signUp-control">
                            <select id="factoryVal" class="selectForm factoryVal" name="brand">
                                <option value="">请选择品牌</option>
                            </select>
                        </div>
                        <div style="*z-index:3" class="signUp-control">
                            <select disabled="disabled" class="selectForm carstyleVal" name="carstyle">
                                <option value="">请选择车型</option>
                            </select>
                        </div>
                        <div style="*z-index:2" class="signUp-control">
                            <i class="inputBoxTip">姓名</i>
                            <input class="inputBox inputtext-sign username" name="user" type="text" value="" />
                            <span class="help-inline"></span>
                        </div>
                        <div style="*z-index:1" class="signUp-control">
                            <i class="inputBoxTip">手机</i>
                            <input class="inputBox inputtext-sign phoneb" name="phone" type="text" value="" />
                            <span class="help-inline"></span>
                        </div> 
                        <div class="signUp-control">
						<#assign cityStr = 'http://'+city.py+'.tuanche.com/'>
							<#assign urlEncoder= "com.tuanche.cms.util.FreeURLEncoder"?new() >
            	    <input  type="hidden" id="url" name="url"  value="${urlEncoder(cityStr)}"/>
            	    <input  type="hidden" name="bmtype"  value="index"/>
            	    <input type="hidden" name="position" value="4"/>
                            <input class="inputsubmit-sign" type="button" value="提交报名" />
                        </div>   
                    </form>             
                </div>
            </div>
            <!--快速报名-->
            <!--排行榜-->
            <div class="rank">
                <div class="rank-tit"><em class="rank-tit-icon"></em>团购车型排行榜</div>
               <#if car_style_top?exists>
	    		<#list car_style_top?sort_by('passNum')?reverse as carStyle>
	    			<#if carStyle_index == 0>
				<div class="rank01">
                    <a href="${carStyle.url}" target="_blank"><p class="rank01-1"><span class="red">0${carStyle_index+1}</span>${carStyle.title}</p>
                    <p class="rank01-2">FOCUS<span class="red">${carStyle.passNum}</span></p></a>
                </div>
				</#if>
				</#list>	
                <ul class="rank-ul">
				<#list car_style_top?sort_by('passNum')?reverse as carStyle>
				<#if carStyle_index gt 0>
                    <li class="clearfix">                       
                        <span class="fl"><em   <#if carStyle_index+1 <=3 >class="rank-ul-icon rank-ul-icon-red" <#else> class= "rank-ul-icon rank-ul-icon-black"  </#if>>0${carStyle_index+1}</em><a href="${carStyle.url}" target="_blank">${carStyle.title}</a></span>
                        <span class="fr red">${carStyle.passNum}</span>                        
                    </li>    
				</#if>
			</#list>				
                </ul>
				    
	             
            </#if>
            </div>
            <!--排行榜-->
        </div>
        <!--报名-->
    </div>
	
    <!--他说-->
    <div class="tab-wrap"> 
        <div class="clearfix title-index title-index2">
            <h2 class="fl"><em></em>他说</h2>
            <div class="fr title-index-link">
			<#if groupfriend_say_city?exists>
							<a href="javascript:void(0);" class='cur' data-con="tc_nav_tab_con">团友说</a>
			 </#if> 
					<span>|</span>
			<#if dian_say_city?exists>
						<a href="javascript:void(0);" data-con="tc_nav_tab_con">4S店说</a>
			</#if>
			</div>        
    </div>
	<div class="tab-con tashuoList">
		<ul class="clearfix">
		<#if groupfriend_say_city_contentList?exists>
		<#list groupfriend_say_city_contentList as content>
			
			<li class="clearfix">
				<div class="fl tashuoList-img">
				 
						 <#if content_index lt groupfriend_say_city_contentList?size -2>
						 <a target="_blank" href="${content.hyperlink}"> <img src="${staticFile}/public/images/blank.gif" alt="" width="60" height="60" data-url="${content.imagUrl!''}" class="scrollLoading" /></a>
						 </#if>
					 <#if content_index gte groupfriend_say_city_contentList?size -2>
							<a target="_blank" href="${content.hyperlink}"><img src="${content.imagUrl!''}" alt="" width="60" height="60" data-url="${content.imagUrl!''}" class="scrollLoading" /></a>
						 </#if>
				</div>
				<div class="fl tashuoList-info">
					<em class="tashuoList-info-icon"></em>
					<a target="_blank" href="${content.hyperlink}">
							<#list content.title2?split("-") as conName>
								<#if conName_index==0>
									<p class="tashuoList-info-name">${conName}</p>
								</#if>
								<#if conName_index==1>
									<p class="gray">${conName}</p>
								</#if>
							</#list>
							<#if "${content.expand}"?length gt 89>
								<p>${content.expand[0..30]}...</p>
							</#if>
							<#if "${content.expand}"?length lt 89>
								<p>${content.expand}</p>
							</#if>
						</a>
				   
				</div>
			</li>
				
		</#list>
	</#if>
			
		</ul>
		
	</div>
	<!--4s-->
	<div class="tab-con tab-con-hide ssList">
		<ul class="clearfix">
			<#if dian_say_city_contentList?exists>
			<#list dian_say_city_contentList[0..3] as content>
			<#assign first = content.expand?index_of('&')>
		        <#assign last = content.expand?last_index_of('&')>
		        <#if first!=-1>
		        	<#assign dian_cont = content.expand?substring(0,first)>
		        	<#if dian_cont?length gt 95>
		        		<#assign dian_cont = dian_cont?substring(0,95)>
		        	</#if>
		        </#if>
		        <#if last!=-1 && first != last>
		        	<#assign dian_name = content.expand?substring(first+1,last)>
		        	<#assign per_name = content.expand?substring(last+1)>
		        </#if>
			<li class="clearfix">
				<div class="clearfix ssList-top">
					<div class="fl ssList-img">
						 <img src="${staticFile}/public/images/blank.gif" alt="" width="60" height="60" data-url="${content.imagUrl!''}" class="scrollLoading" />
					</div>
					<div class="fl ssList-info">                      
						<p class="ssList-info-name"> ${per_name!''}</p>
						<!--<p>市场经理</p>-->
						<#list dian_name?split("-") as conName>
							<#if conName_index==0>
								<p class="gray ssList-info-address">${conName}</p>
							</#if>
							<#if conName_index==1>
								<p class="gray ">${conName}</p>
							</#if>
						</#list>
					</div>
				</div>
				<div class="ssList-bottom">
					<p class="f14">${content.title}</p>
					<p class="detail">${dian_cont!''}</p>
				</div>                    
			</li>
			</#list>
		  </#if>
		</ul>            
	</div>
	<!--4s-->
    <!--他说-->
    <!--团长们-->
    <div class="clearfix title-index title-index3">
            <h2><em></em>团长们</h2>            
     </div>
    <div class="carousel">
		<a href="javascript:void(0);" class="prev derict" id="prev-02"> </a>
		<div id="colonel" class="clearfix jCarouselLite colonel">
			<ul id="colonel-ul" class="fl clearfix colonel-ul">
				<#if groupbuy_chiefs_city_contentList?exists>
					 <#list groupbuy_chiefs_city_contentList as user>
						<#if user?exists>
				<li class="clearfix colonel-li">
                    <div class="colonelDetail">
                        <p>带团期数：</p>
                        <p class="colonelDetail-num">${user.getGroup}</p>                    
                        <p>购车总数：</p>
                        <p class="colonelDetail-num">${user.sellNum}</p>                    
                        <p>累计人数：</p>
                        <p class="colonelDetail-num">${user.activityNum}</p>
                    </div>            	
                    <div class="colonel-img">
                       <img src="${user.headUrl}" alt="" width="118px" height="118px" />
                    </div>
					<#assign userbrands=user.brands![]>
                    <div class="colonel-info">
                        <p class="colonel-info-name">${user.empName?substring(0,1)}团</p>
                        <p>团长</p>
                        <div class="colonel-info-ul">
							<#list userbrands as brand>
							<#if brand_index <4 >
						   <p class=" clearfix colonel-info-li">
								<span><a pl="pg=cityIndex&pl=leader&empNo=${user.empNo}" target="_blank" href="http://${city.py}.tuanche.com/b${brand.id}/tuan/">${brand.name}</a></a></span>
                            </p>
							</#if>
                            </#list>
                                                  
                        </div>
                    </div>                 
                </li>
               </#if>
				 </#list>
                </#if>
			</ul>
		</div>
		<a href="javascript:void(0);" class="next derict derict-next" id="next-02"> </a>
		<div class="clear"></div>   
	</div>    
    <!--团长们--> 
    <!--团车资讯-->
       
	<#include  "new_zixun_04.ftl"/> 
    <!--团车资讯-->
</div>
</div>
<!-- 底部 -->
<#include  "new_city_footer_05.ftl"/>
<!-- 底部 -->

<div id="menuside-small" class="menuside-small">
    <a id="show-menuside" class="show-menuside" href="javascript:void(0);">快捷通道</a>
    <a id="menuside-link-top" class="menuside-link-top show-menuside-top" href="javascript:void(0);">回到顶部</a>
</div>



<!--侧边栏-->
<div id="menuside" class="menuside">
    <div id="menuside_close" class="menuside_close"></div>
    <div class="menusideForm-wrap">
        <a id="menusideForm-closeF" class="closeF" href="javascript:void(0)">×</a>       
        <form id="menusideForm" action="http://tuanche.com/service/signup/formapply" method="post">
			<input type="hidden" name="city" class="cityVal" value="${city.id?c}">
            <div style="*z-index:4" class="signUp-control">
                <select class="selectForm factoryVal" name="brand">
                    <option value="">请选择品牌</option>
                </select>
            </div>
            <div style="*z-index:3" class="signUp-control">
                <select disabled="disabled" class="selectForm carstyleVal" name="carstyle">
                    <option value="">请选择车型</option>
                </select>
            </div>
            <div style="*z-index:2" class="signUp-control">
                <i class="inputBoxTip">姓名</i>
                <input class="inputBox inputtext-sign username" name="name" type="text" value="" />
                <span class="help-inline"></span>
            </div>
            <div style="*z-index:1" class="signUp-control">
                <i class="inputBoxTip">手机</i>
                <input class="inputBox inputtext-sign phoneb" name="phone" type="text" value="" />
                <span class="help-inline"></span>
            </div> 
            <div class="signUp-control">


							<#assign cityStr = 'http://'+city.py+'.tuanche.com/'>
							<#assign urlEncoder= "com.tuanche.cms.util.FreeURLEncoder"?new() >
            	    <input  type="hidden" id="url" name="url"  value="${urlEncoder(cityStr)}"/>
            	    <input  type="hidden" name="bmtype"  value="index"/>
            	    <input type="hidden" name="position" value="4"/>
                <input class="inputsubmit-sign" type="button" value="提交报名" />
            </div>   
        </form> 
    </div>    
	<div class="menuside-lc"></div>
    <div class="menuside-link">
        <div class="kuang"></div>
    	<a id="menuside-link-tuan" class="menuside-link-tuan" href="##" target="_blank">团购报名</a>
        <a id="menuside-link-line" class="menuside-link-line" href="tencent://message/?Menu=yes&uin=938060437&Service=58&SigT=A7F6FEA02730C98844DD5F7A4313382BD14C7A3F7C080101A2A2BE924F16760A3F52A75FD78516CE7CEFDCFF87A5B45CF6A328D95ABF7FE936AA4C42F8D4D22C0E547206EDFF8A8461342209D70013EF166E434F52F756BEB6099E8699F412D157EC3B1AD7694B87EA23BA52D70B134ECB9CA9C7ACCD2419&SigU=30E5D5233A443AB200557F2F7B70D32561266139A081E88BA549E55D56CCDE1B0F0A3518D48D7ABCC6E9AEF2042E41B2C8D9B4A7A3EF179E11803C1A5C3261FE8D33E574EC9E8794">在线客服</a>
        <a id="menuside-link-top" class="menuside-link-top" href="javascript:void(0);">回到顶部</a>
    </div>
</div>
<!--侧边栏-->
<a href="http://tuanche.com/sms/" target="_blank" class="smsQueryBtn">团购地址查询</a>
<!--弹出窗口-->
<div id="popIndex-mark" class="popIndex-mark"></div>
<div id="popIndex" class="popIndex">     
    <div>
        <a id="popIndex-close" class="popIndex-close" href="javascript:void(0);">关闭</a>
        <img src="http://static.tuanche.com/tuanchepc/default/images/indexPopTop.png" width="900" height="367"/>
    </div>
    <div>
        <img src="http://static.tuanche.com/tuanchepc/default/images/indexPopBottom.png" width="900" height="132"/>
    </div>
</div>
<!--弹出窗口-->
<script type="text/ecmascript" src="http://static.tuanche.com/tuanchepc/public/js/jquery.min.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/tuanchepc/public/js/stat.js"></script>
<script type="text/javascript" src="http://static.tuanche.com/tuanchepc/public/js/jquery.flexslider-min-1-8.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/tuanchepc/public/js/jquery.plugin.lib.js"></script>
<script type="text/javascript" src="http://static.tuanche.com/public/js/tongji/${city.py}_tongji.js${static_version}"></script>
<script type="text/javascript" src="http://static.tuanche.com/tuanchepc/public/js/jcarousellite.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/tuanchepc/public/js/common.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/citywebnew/js/index_city.js${static_version}"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/tuanchepc/default/js/index.js${static_version}"></script>
<script type="text/javascript">
    document.write(unescape('%3Cscript charset="utf-8" src="http://znsv.baidu.com/customer_search/api/js?sid=13628016066063889893') + '&plate_url=' + (encodeURIComponent("www.tuanche.com")) + '&t=' + (Math.ceil(new Date()/3600000)) + unescape('"%3E%3C/script%3E'));
</script>

<!--信誉-->
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fff738914d6f4927f5a06b4f96ce8d0b7' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>