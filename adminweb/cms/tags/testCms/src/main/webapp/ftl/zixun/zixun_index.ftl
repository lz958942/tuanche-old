<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  xmlns:wb="http://open.weibo.com/wb">
<head>
<title>汽车资讯|团购资讯|车市动态_团车网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="汽车资讯,团购资讯,车市动态" name="keywords"/>
<meta content="团车网为您提供最全面的汽车资讯信息，享受最专业的汽车资讯资讯!买车就上团车网，团车网-中国最大的汽车互联网交易和服务平台。咨询热线：400-6969-123" name="description"/>

<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/flexslider.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/common.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/news/css/news.css${static_version}" />
<link type="image/x-icon" rel="icon" href="${staticFile }/imgcommon/favicon.ico"/>
<link type="image/x-icon" rel="shortcut icon" href="${staticFile }/imgcommon/favicon.ico"/>
<link rel="canonical" href="http://tuanche.com/zx/"/> 
</head>
<body class="country">
<!-- 头部顶上部分 全站通用 -->
<#include  "header_top.ftl"/>

<!-- 顶部广告位 -->
<#if city_all_1_top?exists>
	<div class="container_2 ad_place_a"  style="width: 1000px;height: 80px" >
		<#if city_all_1_top.adLink?exists>
			<a target="_blank" href="${city_all_1_top.adLink}">
		</#if>
			<img alt="${city_all_1_top.adTitle}" src="${city_all_1_top.picName}" width="1000px" height="80px" />
		<#if city_all_1_top.adLink?exists>
			</a>
		</#if>
	</div>
</#if>

<!-- 头部样式  除城市列表页面 通用 -->
<!--头部-->
<#include  "header.ftl"/>
<!-- 主要 -->
<div class="container_2 news_wrap">
    <!-- 大图切换及推荐文章 -->
    <div class="container_2 clearfix">
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
    
     <!-- 内容 -->
    <div class="container_2 clearfix">
    	<div class="content_l">
    		<#include  "con_font6.ftl"/>
			<!-- 团购预告 -->
    		<#if groupbuy_notice?exists && groupbuy_notice_contentList?exists>
    			<@con_font6 plate=groupbuy_notice contentList=groupbuy_notice_contentList></@con_font6>
    		</#if>
			<!-- 团车访谈 -->
        	<#if tuanche_interview?exists && tuanche_interview_contentList?exists>
			 <@con_font6 plate=tuanche_interview contentList=tuanche_interview_contentList></@con_font6>
			</#if>
        </div>
        <div class="content_l">
			<!-- 往期团购 -->
			<#if groupbuy_former?exists && groupbuy_former_contentList?exists>
				<@con_font6 plate=groupbuy_former contentList=groupbuy_former_contentList></@con_font6>
			</#if>
	        	<!-- 车市行情 -->
			<#if car_market?exists && car_market_contentList?exists>
				<@con_font6 plate=car_market contentList=car_market_contentList></@con_font6>
			</#if>
        </div>
        <div class="content_r">
		     <!-- 团购买车入口 广告位 -->
		     <#include  "registration.ftl"/>
	         <!-- 小马哥说事 -->
		    <#if groupbuy_former?exists && groupbuy_former_contentList?exists>
				<#include  "xiaoma_say_thing.ftl"/>
		    </#if>
        </div>
    </div>
    
    <!-- 通栏广告位 --> 
	<#if city_all_2_center?exists>
		<div class="container_2 ad_place_a">
		<#if city_all_2_center.adLink?exists>
			<a target="_blank" href="${city_all_2_center.adLink}">
		</#if>
				<img alt="${city_all_2_center.adTitle}" src="${city_all_2_center.picName}" width="1000px" height="90px" />
		<#if city_all_2_center.adLink?exists>
			</a>
		</#if>		
		</div>
	</#if>
    
    
    <!-- 购买新车 -->
    <div class="container_2">
    	<div class="container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">购买</span>新车</div>
            </div>
        </div>
        <#include  "con_font6_long.ftl"/>
        <div class="container_1 clearfix">
        	<div class="content_ll clearfix">
            	<div class="content_ll clearfix">
                    <div class="content_half">
	                     <!-- 初步海选 -->
	            		<#if initial_select?exists && initial_select_contentList?exists>
	            			<@con_font6_long plate=initial_select contentList=initial_select_contentList></@con_font6_long>
	            		</#if>
                    </div>
                    <div class="content_half half_r">
                         <!-- 团车易卖车 -->
	            		<#if group_car_and_sell_car?exists && group_car_and_sell_car?exists>
	            			<@con_font6_long plate=group_car_and_sell_car contentList=group_car_and_sell_car_contentList></@con_font6_long>
	            		</#if>
                    </div>
                 </div>
                   <div class="content_ll clearfix">
                    <div class="content_half">
                       <!-- 对比 导购 -->
	            		<#if compare_guide?exists && compare_guide_contentList?exists>
	            			<@con_font6_long plate=compare_guide contentList=compare_guide_contentList></@con_font6_long>
	            		</#if> 
                    </div>
                    <#include  "center_image.ftl"/>
                     <div class="content_half half_r">
                         <!-- 团车易周刊 -->
	            		<#if car_zixun_weekly?exists && car_zixun_weekly_contentList?exists>
	            			<@con_font6_long plate=car_zixun_weekly contentList=car_zixun_weekly_contentList></@con_font6_long>
	            		</#if>
                    </div>
                </div>
            </div>
            <div class="content_r">
            	<!-- 新车速递 -->
            	<#if new_car_express?exists && new_car_express_contentList?exists>
            		<#include  "new_car_express.ftl"/>
            	</#if>
                <!-- 广告位 -->
                <#if city_all_4_right?exists>
			 		<div class="container_1 ad_place_b">
			 		<#if city_all_4_right.adLink?exists>
						<a target="_blank" href="${city_all_4_right.adLink}">
					</#if>		
			 			<img alt="${city_all_4_right.adTitle}" src="${city_all_4_right.picName}" width="221px" height="147px" />
              		<#if city_all_4_right.adLink?exists>
						</a>
					</#if>	
              		 </div>
		 		</#if>
                
            	<#include  "right_short_font6.ftl"/>
            	<!-- 购车指南 -->
            	<#if buy_Car_guide?exists && buy_Car_guide_contentList?exists>
            		<@right_short_font6 plate=buy_Car_guide contentList=buy_Car_guide_contentList></@right_short_font6>
            	</#if>
            </div>
        </div>
    </div>
    
    <!-- 用车车品 全国页 -->
    <div class="container_2">
    	<div class="container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">用车</span>车品</div>
            </div>
        </div>
        <div class="container_1 clearfix">
        	<div class="content_ll clearfix">
            	<div class="content_left">
            		<!-- 独家策划 -->
            		<#if exclusive_plan?exists && exclusive_plan_contentList?exists>
            			<@con_font6_long plate=exclusive_plan contentList=exclusive_plan_contentList></@con_font6_long>
            		</#if>
                	<!-- 养车用车 -->
            		<#if attend_use_car?exists && attend_use_car_contentList?exists>
            			<@con_font6_long plate=attend_use_car contentList=attend_use_car_contentList></@con_font6_long>
            		</#if>
                </div>
                <div class="content_rimg">
                	<!-- 中间图3 （独家策划右） -->
            		<#if center_image_tem3?exists && center_image_tem3_contentList?exists>
            			<@center_image  plate=center_image_tem3 contentList=center_image_tem3_contentList></@center_image>
            		</#if>
                	<!-- 中间图4 （养车用车右） -->
            		<#if center_image_tem4?exists && center_image_tem4_contentList?exists>
            			<@center_image  plate=center_image_tem4 contentList=center_image_tem4_contentList></@center_image>
            		</#if>
                </div>
            </div>
            <div class="content_r">
            	<!-- 汽车用品 -->
            	<#if car_goods?exists && car_goods_contentList?exists>
            		<@right_short_font6  plate=car_goods!"" contentList=car_goods_contentList!""></@right_short_font6>
            	</#if>
                <!-- 汽车改装 -->
                <#if car_modify?exists && car_modify_contentList?exists>
              		 <#include  "car_modify.ftl"/>
                </#if>
            </div>
        </div>
    </div>
    <!-- 业界新闻 全国页 -->
    <div class="container_2">
    	<div class="container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">业界</span>新闻</div>
            </div>
        </div>
        <div class="container_1 clearfix">
        	<dl class="car_news clearfix">
        		<#include  "trade_news.ftl"/>
        		<#if car_market_news?exists && car_market_news_contentList?exists>
               		 <@trade_news plate=car_market_news contentList=car_market_news_contentList></@trade_news>
                </#if>
                <#if local_groupbuy?exists && local_groupbuy_contentList?exists>	
                	 <@trade_news  plate=local_groupbuy contentList=local_groupbuy_contentList></@trade_news>
                </#if>
                <#if car_4s_interview?exists && car_4s_interview_contentList?exists>
                	<@trade_news  plate=car_4s_interview contentList=car_4s_interview_contentList></@trade_news>
                </#if>
                <#if company_news?exists && company_news_contentList?exists>
                	<@trade_news  plate=company_news contentList=company_news_contentList></@trade_news>
                </#if>
            </dl>
        </div>
    </div>
    
   <!-- 友情链接-->
	<#include  "friendLink.ftl"/> 
</div>

  <!-- 底部广告位 -->
    <#if city_all_3_buttom?exists>
    	<div class="container_2 ad_place_a">
    		<#if city_all_3_buttom.adLink?exists>
				<a target="_blank" href="${city_all_3_buttom.adLink}">
			</#if>	
    		<img alt="${city_all_3_buttom.adTitle}" src="${city_all_3_buttom.picName}" width="1000px" height="90px" />
    		<#if city_all_3_buttom.adLink?exists>
				</a>
			</#if>	
  		 </div>
    </#if>

<!-- 尾部-->
<#include  "footer.ftl"/>

<!-- 广告 扩展代码-->
<#if adCodeMap?exists>
	<#list adCodeMap?keys as key>
		<div><img src='${adCodeMap[key]}' border='0' width='0' height='0'/></div>
	</#list>
</#if>
</body>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.min.js"></script>

<script type="text/ecmascript" src="${staticFile}/public/js/jquery.flexslider-min.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.validate.metadata.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/common.js${static_version}"></script>
<script type="text/ecmascript" src="${staticFile}/news/js/news.js${static_version}"></script>
<!-- 统计 -->
<script type="text/javascript" src="http://static.tuanche.com/public/js/tongji/tuanchecom_tongji.js"></script>

</html>
