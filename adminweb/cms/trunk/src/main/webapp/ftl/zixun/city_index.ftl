<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${city.dname}汽车团购|最专业的${city.dname}汽车团购网_团车网</title>
<meta property="wb:webmaster" content="02bdb133a3f3de35" />
<meta name="keywords" content="${city.dname}汽车团购，汽车团购，${city.dname}汽车团购网，${city.dname}汽车网" />
<meta name="description" content="${city.dname}汽车团购，永远免费为${city.dname}车友提供专业的汽车团购服务，团购最低价格，团车网中国最大的汽车互联网交易和服务平台。热线：400-6969-123." />
<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/common.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/flexslider.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/citywebnew/css/index.css${static_version}" />
<link type="image/x-icon" rel="icon" href="${staticFile }/imgcommon/favicon.ico"/>
<link type="image/x-icon" rel="shortcut icon" href="${staticFile }/imgcommon/favicon.ico"/>
<link rel="canonical" href="http://${city.py}.${oldHost}/"/> 
</head>

<body>
<!-- 引入URLEncoder 函数 -->
<#assign urlEncoder= "com.tuanche.cms.util.FreeURLEncoder"?new() >
<!-- 头部顶上部分 全站通用 -->
<#include  "city_header_top.ftl"/>

<!--头部通栏广告位-->
<#if index_top_ad?exists>
	<div class="tc_container_1 banner_place_top" id="banner_place_1414652774471">
	    <div class="tc_container_2">
	        <a href="javascript:;" class="close_banner_btn" title="关闭">&times;</a>
	        <a href="${index_top_ad.adLink!""}"><img src="${index_top_ad.picName}" width="1200" alt="${index_top_ad.adTitle}" /></a>
	    </div>
	</div>
</#if>

<!-- 头部 -->
<#include  "city_header.ftl"/>
<!-- 主要 -->
<div class="tc_container_2 mb20">
	<!-- 内容 -->
    <div class="tc_container_1 clearfix mb20">
    	<!-- 品牌 -->
        <#include  "city_brand_car_style.ftl"/>
        <!-- 内容中间 -->
        <#include  "city_conter_image_ad.ftl"/>
    </div>
    
    <!-- 购车流程 -->
    <div class="tc_container_1 ad_place_a mb20 car_flow">
       <img src="${staticFile}/citywebnew/images/lc.png" alt="" width="1200" height="94" />
    </div>
    
    <!-- 热门 -->
    <div class="tc_container_1 clearfix mb20">
        <!-- 热门团购 -->
        <#include  "city_hot_car_groupbuy.ftl"/>
        <!-- right -->
        <div class="right_slide">
        	<!-- 团购报名 -->
            <#include  "city_groupbuy_enroll.ftl"/>
            <!-- 团购车型排行榜 -->
            <#include  "city_groupbuy_carStyle_top.ftl"/>
        </div>
    </div>
    <!-- 广告位 -->
    <#include  "city_conter_ad.ftl"/>
    <@ad adContent=content_banner_ad1!""></@ad>
    <!-- 他说 -->
    
    <#if dian_say_city_contentList?exists && groupfriend_say_city_contentList?exists>
	    <div class="tc_container_1 mb20">
	        <div class="tc_title_wrap clearfix">
	            <h2 class="tc_title tc_t_bb" style="margin-right:40px;">"他"&#12288;说</h2>
	            <dl class="clearfix tc_nav tc_tit_nav fl tc_nav_tab ">
	            	<i class="current" style="left:53px;"></i>
	            	<#if groupfriend_say_city?exists>
	                	<dd class="cur"><a href="javascript:void(0);" data-con="tc_nav_tab_con">团友说</a></dd>
	                </#if>
	                <#if dian_say_city?exists>
	                	<dd><a href="javascript:void(0);" data-con="tc_nav_tab_con">4S店说</a></dd>
	                </#if>
	            </dl>
	        </div>
	        <div class="tc_container_1 tc_nav_tab_con">
	           <!-- 他说 -->
	           <#include  "city_ta_say.ftl"/>
	           <!-- 4s店说 -->
	           <#include  "city_dian_say.ftl"/>
	        </div>
	    </div>
    </#if>
    
    <!-- 广告位 -->
    <@ad adContent=content_banner_ad2!""></@ad>
    <!-- 为团友省钱的团长们 -->
    <#include  "city_groupbuy_leader.ftl"/>
    <!-- 广告位 -->
    <@ad adContent=content_banner_ad3!""></@ad>
    <!-- 团车资讯 -->
    <#include  "city_groupbuy_zixun.ftl"/>
</div>



<!--右侧报名滑动-->
<#include  "right_slide.ftl"/>
<!-- 底部 -->
<#include  "city_footer.ftl"/>

<!-- 广告 扩展代码-->
<#if adCodeMap?exists>
	<#list adCodeMap?keys as key>
		<div><img src='${adCodeMap[key]}' border='0' width='0' height='0'/></div>
	</#list>
</#if>
<!-- 团购地址查询 -->
<a href="http://${oldHost}/sms/" target="_blank" class="smsQueryBtn" >团购地址查询</a>
<!--信誉-->
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fff738914d6f4927f5a06b4f96ce8d0b7' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.min.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.plugin.lib.js"></script>
<script type="text/ecmascript" src="${staticFile}/citywebnew/js/index_city.js${static_version}"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/common.js${static_version}"></script>
<script type="text/ecmascript" src="${staticFile}/citywebnew/js/index.js${static_version}"></script>
<!-- 统计  -->
<script type="text/javascript" src="${staticFile}/public/js/tongji/${city.py}_tongji.js${static_version}"></script>
</html>
