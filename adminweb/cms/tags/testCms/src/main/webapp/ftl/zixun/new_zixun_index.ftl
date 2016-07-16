<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:wb="http://open.weibo.com/wb">
<head>
<title>${city.dname}团购资讯|${city.dname}团购报道_${city.dname}团车网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="${city.dname}团购资讯,${city.dname}团购报道" name="keywords"/>
<meta content="${city.dname}团车网为您提供最全面的团购资讯信息，享受最专业的团购资讯信息!买车就上${city.dname}团车网，团车网-中国最大的汽车互联网交易和服务平台。咨询热线：400-6969-123" name="description"/>
<link type="text/css" rel="stylesheet" href="${staticFile}/tuanchepc/public/css/flexslider-1-8.css?v=20150616" />
<link type="text/css" rel="stylesheet" href="${staticFile}/tuanchepc/public/css/common.css?v=20150616" />
<link type="text/css" rel="stylesheet" href="${staticFile}/tuanchepc/news/css/news.css?v=20150616" />	
<link type="image/x-icon" rel="icon" href="http://static.tuanche.com/tuanchepc/public/imgcommon/favicon.ico"/>
<link type="image/x-icon" rel="shortcut icon" href="http://static.tuanche.com/tuanchepc/public/imgcommon/favicon.ico"/>


</head>
<body>
<!-- 顶部广告位 -->
<#if city_all_1_top?exists>
	<div class="container_2 ad_place_a"  style="width: 1200px;height: 90px" >
		<#if city_all_1_top.adLink?exists>
			<a target="_blank" href="${city_all_1_top.adLink}">
		</#if>
			<img alt="${city_all_1_top.adTitle}" src="${city_all_1_top.picName}" width="1200px" height="90px" />
		<#if city_all_1_top.adLink?exists>
			</a>
		</#if>
	</div>
</#if>
<#include  "new_city_head.ftl"/>
<!-- 主要 -->
<div class="container_2 news_wrap">
	<!-- 大图切换及推荐文章 -->
	<div class="container_2 clearfix zixun_top">
		<!-- 大图切换 -->
		<#include  "new_zixun_main_images.ftl"/>
		<!-- 推荐文章 -->
		<div class="recommend_article">
			<!-- 要闻资讯 -->
			<#include  "new_zixun_vital_news_zixun.ftl"/>
			<!-- 精选讯息 -->
			<#include  "new_zixun_select_zixun.ftl"/>
		</div>
	</div>

	<!-- 内容 -->
	<#include  "new_zixun_contentList.ftl"/>
	<div class="container_2 clearfix">
		<div  class="fl" style="overflow:hidden;width:730px;background-color:#fff;padding: 10px 25px 49px 25px;">
			<div class="content_l">
				<!-- 团购预告 -->
	    		<#if groupbuy_notice?exists && groupbuy_notice_contentList?exists>
	    			<@newZixunContents plate=groupbuy_notice contentList=groupbuy_notice_contentList></@newZixunContents>
	    		</#if>
	    		<!-- 团车访谈 -->
	        	<#if tuanche_interview?exists && tuanche_interview_contentList?exists>
				 <@newZixunContents plate=tuanche_interview contentList=tuanche_interview_contentList></@newZixunContents>
				</#if>
			</div>
			<div class="content_l ml70">
				<!--往期团购-->
				<#if groupbuy_former?exists && groupbuy_former_contentList?exists>
					<@newZixunContents plate=groupbuy_former contentList=groupbuy_former_contentList></@newZixunContents>
				</#if>
				<div class="container_1 content_space">
					<!-- 汽车摇号-->
					<#if car_yaohao?exists && car_yaohao_contentList?exists>
						<@newZixunContents plate=car_yaohao contentList=car_yaohao_contentList></@newZixunContents>
					</#if>
					<!-- 地区政策-->
					<#if local_policy?exists && local_policy_contentList?exists>
						<@newZixunContents plate=local_policy contentList=local_policy_contentList></@newZixunContents>
					</#if>
				</div>
			</div>
		</div>
		<div class="content_r">
			<!-- 团购买车入口 -->
			<div class="container_1">
				<div class="buy_car_access access_big">
					<#if  ad_zx_grouped_rigth?exists>
						<a href="${ad_zx_grouped_rigth.adLink!"##"}" target="_blank">
						<img src="${ad_zx_grouped_rigth.picName}" alt="${ad_zx_grouped_rigth.adTitle}" width="399px" height="262px"></a>
					</#if>
				</div>
			</div>
			<div class="container_1 ad_place_c">
				<#if  ad_zx_car_yh_rigth?exists>
					<a target="_blank" href="${ad_zx_car_yh_rigth.adLink!"##"}">
						<img id="adClick_city_zixun_2_right" alt="${ad_zx_car_yh_rigth.adTitle!""}" src="${ad_zx_car_yh_rigth.picName!"##"}" width="399px" height="276px"></a>
				</#if>
			</div>
		</div>
	</div>

	<!-- 团长说事 -->
	<#include  "new_zixun_says.ftl"/>

	<!-- 购买新车 -->
	<div class="container_2">
		<div class="container_1">
			<div class="title_wrap relative clearfix">
				<span class="border_l_a"></span>
				<h3 class="title_d fl">买车</h3>
			</div>
		</div>
		<div class="buyNew">
            <div class="clearfix buyNew-top">
            	 <#if new_car_setIn?exists>
	                <div class="container_1 content_space buyNew-top-div">
	                    <div class="container_1 border_b_a">
	                        <h2 class="title_b title_other">
	                            <span class="sepa_l">
	                            	<#if new_car_setIn.hyperlink?exists>
							    		<a target="_blank"  href="${new_car_setIn.hyperlink}">
								    </#if>
								   	${new_car_setIn.title}
								  	<#if new_car_setIn.hyperlink?exists>
								    	</a>
							    	</#if>	
	                            </span>
	                        </h2>
	                    </div>
	                    <div class="container_1">
	                        <ul class="content_list">
	                           <#list new_car_setIn_contentList as content>
				        			<#if content_index < 6>
				        				<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
				        			</#if>
				        		</#list>
	                        </ul>
	                    </div>
	                </div>
               	</#if>
               	<#if car_zixun_weekly?exists>
	                <div class="container_1 content_space buyNew-top-div">
	                    <div class="container_1 border_b_a">
	                        <h2 class="title_b title_other">
	                            <span class="sepa_l">
	                           		<#if car_zixun_weekly.hyperlink?exists>
							    		<a target="_blank"  href="${car_zixun_weekly.hyperlink}">
								    </#if>
								   	${car_zixun_weekly.title}
								  	<#if new_car_setIn.hyperlink?exists>
								    	</a>
							    	</#if>	 	
	                           </span>
	                        </h2>
	                    </div>
	                    <div class="container_1">
	                        <ul class="content_list">
	                           <#list car_zixun_weekly_contentList as content>
				        			<#if content_index < 6>
				        				<li><a target="_blank" href="${content.hyperlink}">${content.title}</a></li>
				        			</#if>
				        		</#list>
	                        </ul>
	                    </div>
	                </div>
                </#if>
                <#if local_groupbuy?exists>
	                <div class="container_1 content_space buyNew-top-div-no">
	                    <div class="container_1 border_b_a">
	                        <h2 class="title_b title_other">
	                            <span class="sepa_l">
	                            	<#if local_groupbuy.hyperlink?exists>
							    		<a target="_blank"  href="${local_groupbuy.hyperlink}">
								    </#if>
								   	${local_groupbuy.title}
								  	<#if local_groupbuy.hyperlink?exists>
								    	</a>
							    	</#if>
	                            </span>
	                        </h2>
	                    </div>
	                    <div class="container_1">
	                        <ul class="content_list">
	                            <#list local_groupbuy_contentList as content>
				        			<#if content_index < 6>
				        				<li><a target="_blank" href="${content.hyperlink!''}">${content.title!''}</a></li>
				        			</#if>
				        		</#list>
	                        </ul>
	                    </div>
	                </div>
                </#if>
            </div>
            <div class="clearfix buyNew-bottom">
             	<#include  "new_zixun_usedCar.ftl"/>
                <div class="fr buyCarTxt">
                    <div class="container_1 content_space">
                    	<#if local_quotation?exists>
	                        <div>
	                            <div class="container_1 border_b_a">
	                                <h2 class="title_b title_other">
	                                    <span class="sepa_l">
	                                    	<#if local_quotation.hyperlink?exists>
									    		<a target="_blank"  href="${local_quotation.hyperlink}">
										    </#if>
										   	${local_quotation.title}
										  	<#if local_quotation.hyperlink?exists>
										    	</a>
									    	</#if>	
	                                    </span>
	                                </h2>
	                            </div>
	                            <div class="container_1">
	                                <ul class="content_list">
	                                    <#list local_quotation_contentList as content>
						        			<#if content_index < 6>
						        				<li><a target="_blank" href="${content.hyperlink!''}">${content.title!''}</a></li>
						        			</#if>
						        		</#list>
	                                </ul>
	                            </div>
	                        </div>
                        </#if>
                        <#if seller_info?exists>
	                        <div>
	                            <div class="container_1 border_b_a">
	                                <h2 class="title_b title_other">
	                                    <span class="sepa_l">
	                                    	<#if seller_info.hyperlink?exists>
									    		<a target="_blank"  href="${seller_info.hyperlink}">
										    </#if>
										   	${seller_info.title}
										  	<#if seller_info.hyperlink?exists>
										    	</a>
									    	</#if>
	                                    </span>
	                                </h2>
	                            </div>
	                            <div class="container_1">
	                                <ul class="content_list">
	                                     <#list seller_info_contentList as content>
						        			<#if content_index < 6>
						        				<li><a target="_blank" href="${content.hyperlink!''}">${content.title!''}</a></li>
						        			</#if>
						        		</#list>                                 
	                                </ul>
	                            </div>
	                        </div>
                         </#if>
                    </div>
                </div>
            </div>
            
            
		</div>
	</div>

	<!--热门团购-->
	<#include  "new_zixun_hot_group.ftl"/>
</div>
<#include  "new_city_footer_05.ftl"/>
<!-- 广告 扩展代码-->
<#if adCodeMap?exists>
	<#list adCodeMap?keys as key>
		<div><img src='${adCodeMap[key]}' border='0' width='0' height='0'/></div>
	</#list>
</#if>

</body>
</html>

<script type="text/ecmascript" src="${staticFile}/tuanchepc/public/js/jquery.min.js?v=20150616"></script>
<script type="text/ecmascript" src="${staticFile}/tuanchepc/public/js/stat.js"></script>
<script type="text/ecmascript" src="${staticFile}/tuanchepc/public/js/jquery.flexslider-min-1-8.js?v=20150616"></script>
<script type="text/ecmascript" src="${staticFile}/tuanchepc/public/js/jquery.validate.metadata.js?v=20150616"></script>
<script type="text/ecmascript" src="${staticFile}/tuanchepc/public/js/common.js?v=20150616"></script>
<script type="text/ecmascript" src="${staticFile}/tuanchepc/news/js/news.js?v=20150616"></script>
<!-- 统计  -->
<script type="text/javascript" src="http://static.tuanche.com/public/js/tongji/${city.py}_tongji.js"></script>
<!-- 搜索框 -->
<script type="text/javascript">
    document.write(unescape('%3Cscript charset="utf-8" src="http://znsv.baidu.com/customer_search/api/js?sid=13628016066063889893') + '&plate_url=' + (encodeURIComponent("www.tuanche.com")) + '&t=' + (Math.ceil(new Date()/3600000)) + unescape('"%3E%3C/script%3E'));
</script>