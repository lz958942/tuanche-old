<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${city.dname}购车摇号政策|摇号结果查询|${city.dname}摇号结果-团车网</title>
<meta name="keywords" content="${city.dname}购车摇号，摇号结果查询，${city.dname}摇号结果" />
<meta name="description" content="团车网购车摇号专题为您介绍最新的${city.dname}购车摇号政策，您还可以通过本专题查询${city.dname}购车摇号结果及最新的汽车团购信息，咨询电话：400-6969-123。" />
<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/common.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/flexslider.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/citywebnew/css/index.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/citywebnew/css/yaohao.css${static_version}" />
<link type="image/x-icon" rel="icon" href="${staticFile }/imgcommon/favicon.ico"/>
<link type="image/x-icon" rel="shortcut icon" href="${staticFile }/imgcommon/favicon.ico"/>
<link rel="canonical" href="http://${city.py}.${oldHost}/yh/"/> 
</head>

<body>
<!-- 头部顶上部分 全站通用 -->
<#include  "header_top.ftl"/>
<!-- 头部 -->
<#include  "header.ftl"/>

<!-- 主要 -->
<div class="tc_container_3 news_wrap">
<#include  "yaohao_content5.ftl"/>

	<!-- 大图切换及推荐文章 -->
    <div class="tc_container_1 clearfix">
    	<!-- 大图切换 -->
        <#include  "big_image_switch.ftl"/>
        <!-- 推荐文章 -->
        <div class="recommend_article">
        	<dl>
            	<!-- 特殊 1~ 5 -->
        		<#include  "te_shu.ftl"/>  
        		<#if te_shu_tem1_contentList?exists && te_shu_tem1_contentList?exists>            
            		<@te_shu plate=te_shu_tem1 contentList=te_shu_tem1_contentList></@te_shu>
            	</#if>
            	<#if te_shu_tem2_contentList?exists && te_shu_tem2_contentList?exists> 
	        		<@te_shu plate=te_shu_tem1 contentList=te_shu_tem2_contentList></@te_shu>
	        	</#if>
	        	<#if te_shu_tem3_contentList?exists && te_shu_tem3_contentList?exists> 
	        		<@te_shu plate=te_shu_tem1 contentList=te_shu_tem3_contentList></@te_shu>
	        	</#if>
	        	<#if te_shu_tem4_contentList?exists && te_shu_tem4_contentList?exists> 
	        		<@te_shu plate=te_shu_tem1 contentList=te_shu_tem4_contentList></@te_shu>
	        	</#if>
	        	<#if te_shu_tem5_contentList?exists && te_shu_tem5_contentList?exists> 
	        		<@te_shu plate=te_shu_tem1 contentList=te_shu_tem5_contentList></@te_shu>
	        	</#if>
            </dl>
        </div>
    </div>
    
    <!-- 摇号查询 -->
    <#include  "yaohao_query.ftl"/>
    
    <!-- 用车车品 -->
    <div class="tc_container_1">
    	<div class="tc_container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">流程</span>说明</div>
            </div>
        </div>
        <div class="tc_container_1 clearfix">
        	<#include  "yaohao_haveNumber.ftl"/>
        	<!--新车申请教程-->
	    	<@havaNumber plate = user_car_tem1_yh!"" contentList=user_car_tem1_yh_contentList![] flay=0></@havaNumber>
	    	<!--网络申请教程-->
	        <@havaNumber plate = user_car_tem2_yh!"" contentList=user_car_tem2_yh_contentList![] flay=0></@havaNumber>
        </div>
    </div>
    
    <!-- 资格申请 -->
    <div class="tc_container_1">
    	<div class="tc_container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">资格</span>申请</div>
            </div>
        </div>
        <div class="tc_container_1 clearfix">
        	<#include  "yaohao_LongContent.ftl"/>
        	<!--个人资格申请-->
	    	<@LongContent plate = qualific_apply_tem1_yh!"" contentList=qualific_apply_tem1_yh_contentList![]></@LongContent>
	    	<!--单位资格申请-->
	        <@LongContent plate = qualific_apply_tem2_yh!"" contentList=qualific_apply_tem2_yh_contentList![]></@LongContent>
        </div>
    </div>
    
    <!-- 二手车摇号 -->
    <div class="tc_container_1">
    	<div class="tc_container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">二手车</span>摇号</div>
            </div>
        </div>
        <div class="tc_container_1 clearfix">
        	<!--购买二手车仍需摇号-->
	    	<@yh_content5 plate = used_car_yaohao1_yh!"" contentList=used_car_yaohao1_yh_contentList![]></@yh_content5>
	    	<!--置换新车基本流程-->
	        <@yh_content5 plate = used_car_yaohao2_yh!"" contentList=used_car_yaohao2_yh_contentList![]></@yh_content5>
        </div>
    </div>
    
    <!-- 摇号购车 -->
    <div class="tc_container_1">
    	<div class="tc_container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">摇号</span>购车</div>
            </div>
        </div>
        <div class="tc_container_1 clearfix">
        	 <!-- 汽车团购 -->
        	 <#include  "yaohao_content6.ftl"/>
	         <!-- 快捷报名 -->
	         <#include  "yaohao_registration.ftl"/>
        </div>
    </div>
    
    <!-- 排行优惠 -->
    <div class="tc_container_1">
    	<div class="tc_container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">排行</span>优惠</div>
            </div>
        </div>
        <div class="tc_container_1 clearfix">
        <!--本月团车排行-->
    	<@LongContent plate = ranking_favorable1_yh!"" contentList=ranking_favorable1_yh_contentList![]></@LongContent>
    	<!--本月优惠排行-->
        <@LongContent plate = ranking_favorable2_yh!"" contentList=ranking_favorable2_yh_contentList![]></@LongContent>
        </div>
    </div>
   
	<!-- 友情链接 热门品牌 -->
    <#include  "friendLink.ftl"/> 
</div>

<!-- 底部 -->
<#include  "footer.ftl"/>
<!-- 摇号查询结果弹出层及遮罩层 -->
<div class="yh_result_layer" id="yh_result_layer">
	<div class="close_wrap"><a href="javascript:;">&times;</a></div>
    <div class="result_layer_wrap">
    	<p class="result_con_ico"></p>
        <p class="result_con_show" id="result_show_isOk"></p>
    </div>
    <div class="result_user_info">
    	<dl>
        	<dt></dt>
        	<dd class="clearfix">
            	<p class="r_info_code" id="infoCode"></p>
                <p class="r_info_name" id="infoName"></p>
            </dd>
            <dd>温馨提示：下次摇号我们会免费为您发送摇号查询短信！</dd>
        </dl>
    </div>
    <div class="result_layer_wrap">
        <p class="result_con_show result_con_show_m" id="result_con_show_m"></p>
    </div>
    <div class="result_con_btn_wrap">
    	<a href="http://${city.py}.${oldHost}/tuan/" class="result_con_btn" id="result_con_btn"></a>
    </div>
</div>
<div class="yh_result_masklayer" id="yh_result_masklayer"></div>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.min.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.masonry.min.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.flexslider-min.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.validate.metadata.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/common.js${static_version}"></script>
<script type="text/ecmascript" src="${staticFile}/citywebnew/js/index.js${static_version}"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
<!-- 统计  -->
<script type="text/javascript" src="${staticFile}/public/js/tongji/${city.py}_tongji.js"></script>
</body>
</html>