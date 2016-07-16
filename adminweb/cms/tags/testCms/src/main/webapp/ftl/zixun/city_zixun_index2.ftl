<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:wb="http://open.weibo.com/wb">
<head>
<title>${city.dname}团购资讯|${city.dname}团购报道_${city.dname}团车网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="${city.dname}团购资讯,${city.dname}团购报道" name="keywords"/>
<meta content="${city.dname}团车网为您提供最全面的团购资讯信息，享受最专业的团购资讯信息!买车就上${city.dname}团车网，团车网-中国最大的汽车互联网交易和服务平台。咨询热线：400-6969-123" name="description"/>
<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/flexslider.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/public/css/common.css${static_version}" />
<link type="text/css" rel="stylesheet" href="${staticFile}/news/css/news.css${static_version}" />
<link type="image/x-icon" rel="icon" href="${staticFile }/imgcommon/favicon.ico${static_version}"/>
<link type="image/x-icon" rel="shortcut icon" href="${staticFile }/imgcommon/favicon.ico${static_version}"/>
<link rel="canonical" href="http://${city.py}.tuanche.com/zx/"/> 

</head>
<body>
<!-- 头部顶上部分 全站通用 -->
<#include  "header_top.ftl"/>

 <!-- 顶部广告位 -->
 	<#if city_zixun_1_top?exists>
 		<#if city_zixun_1_top.adLink?exists>
 		<a target="_blank" href="${city_zixun_1_top.adLink!''}">
 		</#if>
 		<div class="container_2 ad_place_a"  style="width: 1000px;height: 80px" >
 			<img alt="${city_zixun_1_top.adTitle}" src="${city_zixun_1_top.picName}" width="1000px" height="80px" />
   		</div>
   		<#if city_zixun_1_top.adLink?exists>
 		</a>
 		</#if>
 	</#if>
    
    
<!-- 头部样式  除城市列表页面 通用 -->
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
            <!-- 汽车摇号 -->
            <#include  "car_yaohao.ftl"/>
            <!-- 地方政策 -->
            <#include  "local_policy.ftl"/>
        </div>
        <div class="content_r">
        	<!-- 团购买车入口 -->
            <#include  "registration.ftl"/>
            
            <!-- 地区团车会 -->
            <#if local_groupbuy?exists && local_groupbuy_contentList?exists>
            	<#include  "local_groupbuy.ftl"/>
            </#if>
            <!-- 广告位     （作废）
	            <#if city_zixun_2_right?exists>
	            	<#if city_zixun_2_right.adLink?exists>
	 				<a target="_blank" href="${city_zixun_2_right.adLink}">
	 				</#if>
			 		<div class="container_1 ad_place_c">  
			 			<img alt="${city_zixun_2_right.adTitle}" src="${city_zixun_2_right.picName}" width="221px" height="200px" />
			   		</div>
			   		 <#if city_zixun_2_right.adLink?exists>
		 			</a>
		 			</#if>
			 	</#if>
		 	-->
        </div>
    </div>
    <!-- 购买新车 -->
    <div class="container_2">
    	<div class="container_1">
        	<div class="big_title border_b_b">
            	<div class="title_text title_c border_b_c"><span class="sepa_l">买车</span></div>
            </div>
        </div>
        <#include  "con_font6_long.ftl"/>
        <#include  "center_image.ftl"/>
        <div class="container_1 clearfix">
        	<div class="content_ll clearfix">
            	<div class="content_ll clearfix">
                    <div class="content_half">
                    <#if new_car_setIn?exists && new_car_setIn_contentList?exists>
                  	  <@con_font6_long plate=new_car_setIn contentList=new_car_setIn_contentList></@con_font6_long>
                    </#if>
                    </div>
                     <!-- 团车易卖车 -->
            		<#if city.id!=10>
	            		<div class="content_half half_m">
		            		 <#if center_image_tem1?exists && center_image_tem1_contentList?exists>
	                    		<@center_image plate=center_image_tem1 contentList=center_image_tem1_contentList></@center_image>
	                    	</#if>
                    	</div>
            		</#if>
            		<#if city.id==10>
            			<div class="content_half half_r">
		            		<#if group_car_and_sell_car?exists && group_car_and_sell_car?exists>
		            			<@con_font6_long plate=group_car_and_sell_car contentList=group_car_and_sell_car_contentList></@con_font6_long>
		            		</#if>
	            		</div>
            		</#if>
                </div>
            	<div class="content_ll clearfix">
                    <div class="content_half">
	                    <!-- 本市行情 -->
	            		<#include  "local_quotation.ftl"/>
	            		<!-- 商家资讯 -->
	            		<#include  "seller_info.ftl"/>
                    </div>
                    <div class="content_half half_r">
                         <!--  团车易周刊 -->
	            		<#if car_zixun_weekly?exists && car_zixun_weekly_contentList?exists>
	            			<@con_font6_long plate=car_zixun_weekly contentList=car_zixun_weekly_contentList></@con_font6_long>
	            		</#if>
                    </div>
                </div>
            </div>
            <div class="content_r">
            	<!-- 小马哥说事 -->
           		<#include  "xiaoma_say_thing.ftl"/>
                <!-- 如果没有地区团车会    显示广告--> 
                <#if !local_groupbuy_contentList?exists && city_zixun_3_right?exists>
                	<#if city_zixun_3_right.adLink?exists>
		 			<a target="_blank" href="${city_zixun_3_right.adLink}">
		 			</#if>
                	<#if city_zixun_3_right?exists>
                		<div class="container_1 ad_place_b">
				 			<img alt="${city_zixun_3_right.adTitle}" src="${city_zixun_3_right.picName}" width="221px" height="147px" />
				   		</div>
                	</#if>
                	<#if city_zixun_3_right.adLink?exists>
		 			</a>
		 			</#if>
            	 </#if>
            </div>
        </div>
    </div>
    
    <!-- 热门团购 城市页 -->
	<#include  "hot_groupbuy.ftl"/>
     
    
    <!-- 车展欣赏 城市页 -->
    <#if car_show_contentList?exists>
	    <div class="container_2">
	    	<div class="container_1">
	        	<div class="big_title border_b_b">
	            	<div class="title_text title_c border_b_c"><span class="sepa_l">车展</span>欣赏</div>
	            </div>
	        </div>
	        <div class="container_1 clearfix">
	        <#assign width = [290,350,350,175,345,175]> 
	   	    <#assign hhh =   [355,175,175,175,175,175]> 
	        	<ul class="car_appreciate">
		        		 <#list car_show_contentList as content>
		        		 	<#if content?exists && content_index < 6>
		        		 	 <#assign pic_copunt = content_index+1> 
			            	<li class="pic_${pic_copunt}">
			                	<img src="${content.imagUrl!""}" alt="" width="${width[content_index] }px" height="${hhh[content_index]}px" data-url="${content.imagUrl!""}" class="scrollLoading" />
	                    		<a target="_blank" href="${content.hyperlink}" class="img_tit">${content.title}</a>
			                </li>
							</#if>
		               	 </#list>
	            </ul>
	        </div>
	    </div>
    </#if>
</div>

  
    <!-- 底部广告位 -->
    <#if city_zixun_4_buttom ?exists>
    	<#if city_zixun_4_buttom.adLink?exists>
		<a target="_blank" href="${city_zixun_4_buttom.adLink}">
		</#if>
    	<div class="container_2 ad_place_a">
    		<img alt="${city_zixun_4_buttom.adTitle}" src="${city_zixun_4_buttom.picName}" width="1000px" height="90px" />
  		 </div>
  		 <#if city_zixun_4_buttom.adLink?exists>
		</a>
		</#if>
    </#if>
    
    
<!-- 尾部-->
<#include  "footer_sjz.ftl"/>

<!-- 广告 扩展代码-->
<#if adCodeMap?exists>
	<#list adCodeMap?keys as key>
		<div><img src='${adCodeMap[key]}' border='0' width='0' height='0'/></div>
	</#list>
</#if>

<!-- 统计 -->
<script type="text/javascript">
var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cspan id='cnzz_stat_icon_1000508293'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1000508293' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type='text/javascript'>	
	var _fxcmd = _fxcmd || [];	
	_fxcmd.sid = '6499fc66beed49ed52dc6657c971465c';	
	(function () {	
	var _pzfx = document['createElement']('script');	
	_pzfx.type = 'text/javascript';	
	_pzfx.async = true;
	_pzfx.src = 'http://static.w3t.cn/fx/1/1/fx.js';	
	var sc = document.getElementsByTagName('script')[0];	
	sc.parentNode.insertBefore(_pzfx, sc);
	})();
</script>
</body>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.min.js"></script>

<script type="text/ecmascript" src="${staticFile}/public/js/jquery.flexslider-min.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/jquery.validate.metadata.js"></script>
<script type="text/ecmascript" src="${staticFile}/public/js/common.js${static_version}"></script>
<script type="text/ecmascript" src="${staticFile}/news/js/news.js${static_version}"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script> 

<!-- 统计  -->
<script type="text/javascript" src="http://static.tuanche.com/public/js/tongji/${city.py}_tongji.js"></script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-50202854-1', 'tuanche.com');
  ga('send', 'pageview');
  
  //设置cookies
  setCookie("city_code",${city.cityCode});
  setCookie("city_id",${city.id});
  
</script>
</html>