<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%>
<%@ page isELIgnored="flase" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${specialSubject.spName}_团车网</title>
<meta name="keywords" content="${specialSubject.keywords}" />
<meta name="description" content="${specialSubject.spDesc}" />
<link type="image/x-icon" rel="icon" href="http://static.tuanche.com/imgcommon/favicon.ico"/>
<link type="image/x-icon" rel="shortcut icon" href="http://static.tuanche.com/imgcommon/favicon.ico"/>
<link type="text/css" rel="stylesheet" href="http://static.tuanche.com/public/css/common.css" />
<link type="text/css" rel="stylesheet" href="http://static.tuanche.com/public/css/flexslider.css" />
<link type="text/css" rel="stylesheet" href="http://static.tuanche.com/zt/css/zt.css" />
<script type="text/ecmascript" src="http://static.tuanche.com/public/js/jquery.min.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/public/js/jquery.flexslider-min.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/public/js/jquery.validate.metadata.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/public/js/jquery.masonry.min.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/public/js/common.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/zt/js/zt.js"></script>
<script type="text/ecmascript" src="http://static.tuanche.com/web/js/base.js"></script>
</head>

<body>
<!-- 头部顶上部分 全站通用 -->
<div class="tc_header">
	<div class="tc_container_3 clearfix">
        <div class="fl city_wrap clearfix">
            <div class="current_city cur_city fl"><a href="${cityUrl}/" data-cur="${cityId }">${cityName}</a></div>
            <div class="switch_city fl" id="switch_city">[<a href="javascript:;">选择城市</a>]
            	<div class="switch_city_wrap" id="moreCity">
                	<div class="s_city_con">
                        <ul>
                         <c:forEach items="${changeCitys}" var="item">
						               <li>
						                    <h4>${item.key }</h4>
						                    <div class="s_ul">
						                      <c:forEach  var="city" items="${item.value }">
						                            <a href="${city.url}" ${city.openStatus==3?'class="ready"':''} title="${city.name}汽车团购">${city.name }</a>
						                        </c:forEach>
						                    </div>
						                    <div style="clear:both"></div>
						                </li>
						        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="fr login_wrap">
        	<!-- 
        	<a href="##">注册</a>
            <span>|</span>
            <a href="##">登录</a>
            -->
             <div class="tc_focus">
            	<a href="##" class="a_transition">关注<i class="ico ico_down"></i></a>
                <div class="tc_focus_wrap">
                	<div class="tc_focus_con">
                		<p class="bold">团车网官方微博</p>
                        <p><wb:follow-button uid="2445730697" type="red_2" width="136" height="24" ></wb:follow-button></p>
                        <p class="line"></p>
                        <p class="bold">团车网官方微信</p>
                        <p><img src="http://static.tuanche.com/public/images/wechat_s.png" alt="" width="90" height="90" /></p>
                    </div>
                </div>
            </div>
            <span>|</span>
       	    <a href="##" id="addFavorite">收藏</a>
        </div>
    </div>
</div><!-- 头部 -->
<!-- 头部 -->
<div class="tc_header_ls tc_container_1">
<div class="tc_container_3">
	<div class="tc_header_main clearfix">
    	<div class="tc_logo_wrap">
        	<a href="http://tuanche.com/" class="tc_logo" title="团车网">团车网</a>
            <span>买车！就上团车网</span>
        </div>
        <div class="tc_search_wrap tc_search_middle">
        	<div class="tc_search">
            	<script type="text/javascript">document.write(unescape('%3Cdiv id="bdcs"%3E%3C/div%3E%3Cscript charset="utf-8" src="http://znsv.baidu.com/customer_search/api/js?sid=13628016066063889893') + '&plate_url=' + (encodeURIComponent(window.location.href)) + '&t=' + (Math.ceil(new Date()/3600000)) + unescape('"%3E%3C/script%3E'));</script>
            </div>
            <div class="tc_hotsearch">
            	<dl class="clearfix">
                	<dt>热门车型：</dt>
                	  <c:forEach  var="item" items="${cityHotStyle}"  begin="0" end="6">
                        <dd><a href="${cityUrl}/c${item.id}/tuan/" target="_blank">${item.name}</a></dd>
                     </c:forEach>
                </dl>
            </div>
        </div>
        <div class="tc_tel_wrap">
        	<span class="tc_tel">400-6969-123</span>
        </div>
    </div>
</div>
</div>
<!-- 导航 -->
<div class="tc_container_1 tc_nav tc_nav_bb">
	<div class="tc_container_3">
    	<dl class="clearfix">
    	    ${bizType=='index'?'<dt class="nav_a"><span>按品牌车型团购</span></dt>':''}
            <dd ${bizType=='index'?' class="cur"':''}><a href="${cityUrl}/" target="_blank">首页</a></dd>
            <dd ${bizType=='searchBuy'?' class="cur"':''}><a href="${cityUrl}/brand/" title="新车团购" target="_blank">新车团购</a></dd>
         	<c:if test="${isSecond}">
         	<dd ${bizType=='second'?' class="cur"':''}><a href="http://www.51runhuazhi.cn/pc/html/home.html?v=1" target="_blank">旧车竞卖</a></dd>
            </c:if>
            <c:if test="${Havezhuangshi}">
               <dd ${bizType=='zhuangshi'?' class="cur"':'' }><a href="http://bj.tuanche.com/zhuangshi/" target="_blank">装饰团购</a></dd>
            </c:if>
           <c:choose>
            	<c:when test="${cityId==10}">
            		<dd ${bizType=='zhidao'?' class=cur':''}><a href="http://zhidao.tuanche.com/" target="_blank">团车知道</a></dd>
            	</c:when>
            	<c:otherwise>
            		<dd ${bizType=='guard'?' class=cur':''}><a href="http://www.tuanche.com/baozhang/" target="_blank">团购保障</a></dd>
            		<dd ${bizType=='zhidao'?' class=cur':''}><a href="http://zhidao.tuanche.com/" target="_blank">团车知道</a></dd>
            	</c:otherwise>
            </c:choose>
            <dd class="sub_nav">
            	<a href="http://tuanche.com/che/" class="a_transition">选车<i class="ico ico_down_nav"></i></a>
            	<div class="header_sub_nav">
                	<dl class="sub_nav_left clearfix">
                        <dd><a href="http://tuanche.com/tuan/" target="_blank">选团</a></dd>
                        <dd><a href="http://tuanche.com/che/" target="_blank">选车</a></dd>
                        <dd><a href="http://tuanche.com/cars/" target="_blank">买车</a></dd>
                        <dd><a href="http://tuanche.com/zx/" target="_blank">资讯</a></dd>
                    </dl>
                    <dl class="sub_nav_right clearfix">
                        <dd><a href="http://tuanche.com/pinpai/" target="_blank">品牌大全</a></dd>
                        <dd><a href="http://tuanche.com/price/" target="_blank">汽车报价</a></dd>
                        <dd><a href="http://tuanche.com/xiaoliang/" target="_blank">汽车销量</a></dd>
                        <dd><a href="http://tuanche.com/daogou/" target="_blank">汽车导购</a></dd>
                        <dd><a href="http://tuanche.com/koubei/" target="_blank">汽车口碑</a></dd>
                        <dd><a href="http://tuanche.com/photo/" target="_blank">汽车图片</a></dd>
                        <dd><a href="http://tuanche.com/youhao/" target="_blank">汽车油耗</a></dd>
                        <dd><a href="http://tuanche.com/shijia/" target="_blank">汽车试驾</a></dd>
                        <dd><a href="http://tuanche.com/dealer/" target="_blank">汽车经销商</a></dd>
                        <dd><a href="http://www.tuanche.com/top/" target="_blank">汽车排行榜</a></dd>
                        <dd><a target="_blank" href="http://zc.tuanche.com/">异地购车政策</a></dd>
                    </dl>
                </div>
            </dd>
            <c:if test="${isHaveNews}">
                       <dd><a href="${cityUrl}/zx/" target="_blank">团购资讯</a></dd>
             </c:if>
        </dl>
    </div>
</div>
<%-- <div class="header_top">
	<div class="ht_wrap clearfix">
    	<div class="fl">您好，欢迎来到团车网--中国第一汽车交易平台</div>
        <div class="fr">
        	<a href="http://e.weibo.com/tuanchewang" class="weibo_ico sina" target="_blank">新浪微博</a>
			<span> </span>
			<a href="http://t.qq.com/tuanche" class="weibo_ico tencent" target="_blank">腾讯微博</a>
            <span>|</span>
            <a href="javascript:;" id="addFavorite">收藏团车</a>
        </div>
    </div>
</div>
<div class="header">
	<div class="container header_wrap clearfix">
    	<div class="fl hw_a">
        	<h1 class="logo"><a href="http://www.tuanche.com/">团车网</a></h1>
            <p class="slogan">买车就上团车网</p>
            <div class="header_citys">
            	<div class="fl cur_city"><a href="${cityUrl}/" data-cur="${cityId}">${cityName}</a></div>
                <div id="switch_city" class="fl">
                    <a class="qiea" href="javascript:;">切换城市<i></i></a>
                    <div id="moreCity" class="search_city">
                        <div class="s_city_con">
                            <ul>
                                <c:forEach items="${changeCitys}" var="item">
						               <li>
						                    <h4>${item.key }</h4>
						                    <div class="s_ul">
						                      <c:forEach  var="city" items="${item.value }">
						                        <a href="${city.url}">${city.name }</a>
						                        </c:forEach>
						                    </div>
						                    <div style="clear:both"></div>
						                </li>
						        </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="fl hw_b">
        	<div class="search clearfix">
            	<form action="${cityUrl}/tuan/search/"  method="post">
                	<input type="text" class="sear_inpt" placeholder="想团车，先搜一下" value=""  name="keyword" autocomplete="off"/>
                    <input type="submit" class="sear_btn" value="搜团购" />
                </form>
            </div>
            <div class="search_hot clearfix">
            	<span>热门搜索:</span>
            	  <c:forEach  var="item" items="${cityHotStyle}"  begin="0" end="6">
                      <a href="${cityUrl}/c${item.id}/tuan/" target="_blank">${item.name}</a>
                 </c:forEach>
            </div>
        </div>
        <div class="fr hw_c">
        	<ul>
            	<li class="bn">
                	<p class="pro_1"></p>
                    <p>低价</p>
                </li>
                <li>
                	<p class="pro_2"></p>
                    <p>安全</p>
                </li>
                <li>
                	<p class="pro_3"></p>
                    <p>全程免费</p>
                </li>
            </ul>
        </div>
    </div>
    <div class="header_nav">
    	<div class="container clearfix">
        	<ul class="hn_nav fl clearfix">
            	<li class="first_li${bizType=='index'?' cur':''}"><a href="${cityUrl}/" target="_blank">首页</a></li>
                <li${bizType=='searchBuy'?' class="cur""':''}><a href="${cityUrl}/tuan/" title="新车团购" target="_blank">
                ${isSecond?'新车团购':'汽车团购'}
                </a></li>
                <c:if test="${isSecond}">
                <li ${bizType=='second'?' class=cur':''}><a href="${cityUrl}/usedcar/" target="_blank">旧车竞卖</a></li>
                </c:if>
                <li ${bizType=='guard'?' class=cur':''}><a href="http://www.tuanche.com/baozhang/" target="_blank">团购保障</a></li>
                <li class="sub_nav">
                	<a href="http://tuanche.com/che/" target="_blank">选车<i class="sub_nav_ico"></i></a>
                	<div class="header_sub_nav">
                        <dl class="fl sub_nav_left">
                            <dd><a href="http://tuanche.com/tuan/" target="_blank">选团</a></dd>
                            <dd><a href="http://tuanche.com/che/" target="_blank">选车</a></dd>
                             <dd><a href="http://tuanche.com/cars/" target="_blank">买车</a></dd>
                             <dd><a href="http://tuanche.com/zx/" target="_blank">资讯</a></dd>
                        </dl>
                        <dl class="fl sub_nav_right">
                            <dd><a href="http://tuanche.com/pinpai/" target="_blank">品牌大全</a></dd>
                            <dd><a href="http://tuanche.com/price/" target="_blank">汽车报价</a></dd>
                            <dd><a href="http://tuanche.com/xiaoliang/" target="_blank">汽车销量</a></dd>
                            <dd><a href="http://tuanche.com/daogou/" target="_blank">汽车导购</a></dd>
                            <dd><a href="http://tuanche.com/koubei/" target="_blank">汽车口碑</a></dd>
                            <dd><a href="http://tuanche.com/photo/" target="_blank">汽车图片</a></dd>
                            <dd><a href="http://tuanche.com/youhao/" target="_blank">汽车油耗</a></dd>
                            <dd><a href="http://tuanche.com/shijia/" target="_blank">汽车试驾</a></dd>
                            <dd><a href="http://tuanche.com/dealer/" target="_blank">汽车经销商</a></dd>
                            <dd><a href="http://www.tuanche.com/top/" target="_blank">汽车排行榜</a></dd>
                        </dl>
                    </div>
                </li>
               <c:if test="${cityCodetj!=null&&cityCodetj!='' }">
               		 <li><a href="${cityUrl }/zx/" target="_blank">团购资讯</a></li>
               </c:if>
            </ul>
            <div class="fr hot_tel">团购报名：<em>400-6969-123</em></div>
        </div>
    </div>
</div> --%>
<!-- 头部图片 -->
<c:if test="${spOnePicList.listPics!=null}">
<div class="header_pic">
    <div class="slider_wrap">
        <ul class="slides clearfix">
	        <li>
	           <a href="##"><img src="${spOnePicList.listPics}" width="1200" ></a>
	        </li>
        </ul>
    </div>
</div>
</c:if>
<!-- 主要 -->
<div class="main">
	<!-- 新车导语 -->
	<c:if test="${specialSubject.spAbstract!=null}">
    <div class="new_car_say clearfix">
    	<h2 class="say_h2">新闻导语</h2>
        <p class="say_con">${specialSubject.spAbstract}</p>
    </div>
    </c:if>
    <div class="content clearfix">
        <div class="slide_left marb10">
            <div class="news_wrap clearfix">
                <div class="news_left fl">
                    <c:if test="${specialSubject.content!=null}">
                    <div class="nl_wrap marb10">
                    	<div class="new_tit">
                        	<h3>往期专题回顾</h3>
                        </div>
                        <div class="news_con">
                        	<div class="new_con_a">
                                <p>${specialSubject.content}</p>
                            </div>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${spConList!=null&&spConList.size()>0}">
                    <div class="nl_wrap">
                    	<div class="new_tit">
                        	<h3>事件回顾</h3>
                        </div>
                        <div class="news_con">
                        	<div class="new_con_b">
                        	    <c:forEach var="sc"  items="${spConList}">
		                               <p class="ncb_tit">${sc.stTitles}</p>
		                               <p>${sc.stContents}</p>
                        		</c:forEach>
                            </div>
                        </div>
                    </div>
                    </c:if>
               	</div>
                <!-- 新闻右侧 -->
                <div class="news_right fr">
                	<div class="news_list_wrap">
                	     <c:forEach var="news"  items="${newsList}" begin="0" end="6">
	                			<div class="news_list clearfix">
		                        	<div class="news_img fl">
		                            	<a target="_blank" href="${news.url}"><img src="${news.pic==null||news.pic==''?'http://static.tuanche.com/web/images/pic_default.jpg':news.pic}" class="scrollLoading border_img" width="180" height="121" /></a>
		                            </div>
		                            <div class="news_abstract">
		                            	<h3 class="abstract_tit"><a target="_blank" href="${news.url}">${news.title}</a></h3>
		                                <p class="abstract_con"><a target="_blank" href="${news.url}">${news.simpleInfo}</a></p>
		                            </div>
		                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- 图片策划 -->
           <c:if test="${spPicsList!=null&&spPicsList.size()>0 }">
            <div class="pic_scheme">
            	<div class="scheme_tit">
                	<h2>图片策划</h2>
                </div>
                <div class="scheme_wrap">
                	<div class="scheme_slide" id="scheme_slide">
                    	<ul class="slides clearfix">
                    	    <c:forEach var="pics"  items="${spPicsList}">
		                        <li>
		                            <div class="scheme_img" style="background:#0CF"><img src="${pics.listPics}" width="700" height="352" alt="" /></div>
		                                <div class="scheme_title">
		                                	${pics.stTitles}
		                                </div>
		                         </li>
		                     </c:forEach>
                        </ul>
                    </div>                
                </div>
            </div>
            </c:if>
        </div>
        <!-- 右侧 -->
        <div class="slide_right">
        	<!-- 快捷报名 -->
            <div class="tg_a_con marb10 border_top ">
                <div class="tg_r_wrap tg_guard_wrap">
                    <div class="qb_tit clearfix">
                        <div class="qbt_a"><i></i>快捷报名</div>
                    </div>
                    <div class="qb_con">
                      <form id="indexForm" class="form_1" action="http://tuanche.com/service/signup/formapply/" method="post">
				             <div class="form_control">
				                	    <label class="f_label">城市</label>
				                        <div class="controls">
					                        <input type="hidden" name="bmtype" value="zt_detail" /> 
					                        <input type="hidden" name="position" value="5" />
					                        <input  type="hidden" name="url" id="url" value="${specialSubject.encodeSpUrl}"/>
				                        	<input type="text" name="city"  id="cityId" class="inpt required hide" autocomplete="off"/>
				                            <span class="help-inline"></span>
				                            <div class="selects inpt click_selects">
				                            	<span class="select_val">请选择城市</span>
				                            	<i class="down_arr"></i>
				                                <div class="select_con select_brand" id="city_1" style="width:600px" ></div>
				                            </div>
				                            
				                        </div>
				                    </div>
				                <div class="form_control">
				                    <label class="f_label">品牌</label>
				                    <div class="controls">
				                        <input type="text" name="brand"  id="brandId" class="inpt required hide" />
				                        <span class="help-inline"></span>
				                        <div class="selects inpt click_selects">
				                            <span class="select_val">请选择品牌</span>
				                            <i class="down_arr"></i>
				                            <div class="select_con select_brand" >   
				                                <div  id="brand_1"></div>     
				                            </div>
				                        </div>
				                        
				                    </div>
				                </div>
				                <div class="form_control">
				                    <label class="f_label">车型</label>
				                    <div class="controls">
				                        <input type="text" name="carstyle" id="styleId" class="inpt required hide"/>
				                        <span class="help-inline"></span>
				                        <div class="selects inpt click_selects">
				                            <span class="select_val">请选择车型</span>
				                            <i class="down_arr"></i>
				                            <div class="select_con select_brand" id="style_1"></div>
				                        </div>
				                    </div>
				                </div>
				                <div class="form_control">
				                    <label class="f_label">手机</label>
				                    <div class="controls">
				                        <input type="text" name="phone" id="userTel" class="inpt required phonec" placeholder="请输入购车人手机号" />
				                        <span class="help-inline"></span>
				                    </div>
				                </div>
				                <div class="form_control">
				                    <div class="controls">
				                           <input  type="hidden" name="format" id="url" value="json"/>
				                        <input type="submit" name="" class="app_btn" value="团购报名"  />
				                    </div>
				                </div>
            			</form>
                    </div>
                </div>
            </div>
            
           <c:if test="${groupList!=null&&groupList.size()>0}">
            <div class="about_tuan">
            	<div class="about_tit">
                	<h2>相关车型团购</h2>
                </div>
                <div class="about_con">
                	 <c:forEach var="group"  items="${groupList}" >
		                	<div class="about_list">
		                    	<div class="about_carimg">
		                        	<a target="_blank" href="${group.url}" pl="pg=ztdetail&pl=buy"><img src="${group.spic}" class="scrollLoading border_img" width="226" height="149" /></a>
		                        </div>
		                        <div class="about_tuantit"><a target="_blank" href="${group.url}" pl="pg=ztdetail&pl=buy">${group.title}</a>-<a target="_blank" href="${group.url}" pl="pg=ztdetail&pl=buy">${group.dateStr}</a></div>
		                        <div class="about_info clearfix">
		                        	<div class="about_apply fl">本期已报名<span class="red">${group.manNum}</span>人</div>
		                            <div class="about_see fr">
		                            	<a target="_blank" href="${group.url}" class="g-btn g-btn-s g-btn-sure"><span>去看看</span></a>
		                            </div>
		                        </div>
		                    </div>
                    </c:forEach>
                </div>
            </div>
            </c:if>
           <c:if test="${newsList!=null&&newsList.size()>0}">
            <div class="hot_topic">
            	<div class="topic_tit">
                	<h2>热门话题</h2>
                </div>
                <div class="topic_con">
                	<ul class="news_list_item">
                			 <c:forEach var="news"  items="${newsList}"  begin="0" end="5">
                    		    <li><a target="_blank" href="${news.url}">·${news.title}</a></li>
                    		    </c:forEach>
                    </ul>
                </div>
            </div>
            </c:if>
        </div>
    </div>
    
     <c:if test="${oldNewsList!=null&&oldNewsList.size()>0}">
    <div class="zt_review">
    	<div class="review_tit clearfix">
        	<h2>往期回顾</h2>
            <a href="http://${cityCode}.tuanche.com/zx-tg/wq/"  rel="nofollow">更多>></a>
        </div>
    	<ul class="review_list clearfix">
    			<c:forEach var="oldnews"  items="${oldNewsList}"  >
    				<li>
		            	<p class="rl_img"><a target="_blank" href="${oldnews.url}"><img src="${oldnews.pic}" class="scrollLoading" width="185" /></a></p>
		            	<p class="rl_tit"><a target="_blank" href="${oldnews.url}">${oldnews.title}</a></p>
                   </li>
                   </c:forEach>
        </ul>
    </div>
    </c:if>
    
    <!-- 热门团购 -->
    <div class="car_tuan">
        <div class="car_list clearfix">
             <c:if test="${hotGroupList!=null&&hotGroupList.size()>0}">
        
            <dl class="hot_tuan clearfix">
                <dt>北京热门品牌汽车团购</dt>
                	<c:forEach var="hotGroup"  items="${hotGroupList}"  >
                		<dd><a target="_blank" href="${hotGroup.buyUrl}" pl="pg=ztdetail&pl=brandbuy">${hotGroup.name}</a></dd>
                	</c:forEach>
            </dl>
            </c:if>
             <!-- 热门团购
            <dl class="hot_tuan clearfix border_top">
                <dt>热门城市汽车团购</dt>
                <dd><a href="#">北京高尔夫团购</a></dd>
                <dd><a href="#">天津高尔夫团购</a></dd>
                <dd><a href="#">上海高尔夫团购</a></dd>
                <dd><a href="#">重庆高尔夫团购</a></dd>
                <dd><a href="#">哈尔滨高尔夫团购</a></dd>
                <dd><a href="#">沈阳高尔夫团购</a></dd>
                <dd><a href="#">北京高尔夫团购</a></dd>
                <dd><a href="#">天津高尔夫团购</a></dd>
                <dd><a href="#">上海高尔夫团购</a></dd>
                <dd><a href="#">重庆高尔夫团购</a></dd>
                <dd><a href="#">哈尔滨高尔夫团购</a></dd>
                <dd><a href="#">沈阳高尔夫团购</a></dd>
            </dl>
             -->
        </div>
    </div>
    <%@include file="/inc/footer_O.inc" %>
<!-- 汽车网站大全 -->

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-50202854-1', 'tuanche.com');
  ga('send', 'pageview');
  
  //设置cookies
  setCookie("city_code",000800090010);
  setCookie("city_id",10);
  
</script>
<script type="text/javascript" src="http://static.tuanche.com/public/js/tongji/tuanchecom_tongji.js"></script>
<script type="text/javascript" charset="UTF-8" src="http://s1.kutongji.com/stat.php?site=2014"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>