<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="flase" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${specialSubject.spName}</title>
    <meta name="keywords" content="${specialSubject.keywords}" />
    <c:choose>
    	<c:when test="${specialSubject.spDesc!=null }">
    		<meta name="description" content="${specialSubject.spDesc}" />
    	</c:when>
    	<c:otherwise>
    		<meta name="description" content="${specialSubject.spAbstract }" />
    	</c:otherwise>
    </c:choose>
    <link type="image/x-icon" rel="icon" href="http://static.tuanche.com/imgcommon/favicon.ico"/>
	<link type="image/x-icon" rel="shortcut icon" href="http://static.tuanche.com/imgcommon/favicon.ico"/>
	<link type="text/css" rel="stylesheet" href="http://static.tuanche.com/public/css/common.css" />
    <link rel="stylesheet" type="text/css" href="http://static.tuanche.com/zt/css_old/default.css" />
    <link rel="stylesheet" type="text/css" href="http://static.tuanche.com/zt/css_old/index.css" />
    <script type="text/ecmascript" src="http://static.tuanche.com/zt/js_old/jquery.min.js"></script>
    <script type="text/ecmascript" src="http://static.tuanche.com/public/js/jquery.validate.metadata.js"></script>
    <script type="text/ecmascript" src="http://static.tuanche.com/public/js/common.js"></script>
</head>
<script language="javascript">
    function hiddendivID(divid)
    {
        document.getElementById(divid).style.display = 'none';
    }
    function AddFavorite(sURL, sTitle)
    {
        try
        {
            window.external.addFavorite(sURL, sTitle);
        }
        catch (e)
        {
            try
            {
                window.sidebar.addPanel(sTitle, sURL, "");
            }
            catch (e)
            {
                alert("加入收藏失败，请使用Ctrl+D进行添加");
            }
        }
    }

</script>

<!-- 页面修改部分为 --> 
<!-- 右侧添加报名入口，将原来的报名入口改成现在的入口，此部分变动比较大 -->
<!-- 先网友观点右侧报名入口删除，将网友观点宽 -->
<!-- 底部添加新的报名入口 -->
<!-- 2014-07-31 -->

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
               <dd><a ${bizType=='zhuangshi'?' class="cur"':'' } href="http://bj.tuanche.com/zhuangshi/" target="_blank">装饰团购</a></dd>
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
    
    <!-- header start-->
        <div class="header_zt">
        <div class="header_con">
            <img src="${spOnePicList.listPics}" alt="" width="990px" height="271px" />
            <div class="header_txt_block">
                <p class="header_txt f14 white">
                   <!-- 专题导语 -->
                   <c:if test="${specialSubject.spAbstract!=null }">
                   		${specialSubject.spAbstract }
                   </c:if>
                </p>
                <div style=" bottom: 8px;clear: both;float: right;left: 273px;margin-top: 20px;position: absolute;width: 378px;">
                    <!-- Baidu Button BEGIN -->
                    <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
                    <span class="bds_more">分享到：</span>
                    <a class="bds_tsina"></a>
                    <a class="bds_qzone"></a>
                    <a class="bds_tqq"></a>
                    <a class="bds_renren"></a>
                    <a class="bds_t163"></a>
                    <a class="bds_baidu"></a>
                    <a class="shareCount"></a>
                    </div>
                    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6754609" ></script>
                    <script type="text/javascript" id="bdshell_js"></script>
                    <script type="text/javascript">
                    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                    </script>
                    <!-- Baidu Button END -->
                </div>
            </div>
        </div>
    </div>
    <!-- header end-->
    <!-- main start-->
    <div class="main">
        <div class="main_con">
        	<!-- ====== -->
        	<div class="tc_container_1 clearfix">
            	<div class="zt_left_aa fl">
                    <!--box1 start-->
                    <div class="box1 clearfix">
                        <!-- 轮播图 start-->
                        <div class="sj_ban">
                            <c:if test="${oldTemp.oldMap.lunboList!=null&&oldTemp.oldMap.lunboList.size()>0 }">
			                    <div class="clr img_ico">
			                        <div class="prev"></div>
			                        <c:forEach items="${oldTemp.oldMap.lunboList }" var="lunboList" begin="0" end="0">
			                        	<div class="sj_txtcon white">${lunboList.stTitles } </div>
			                        </c:forEach>
			                        <div class="next"></div>
			                        <div class="clr"></div>
			                    </div>
			                    <div class="sj_conlist">
			                        <ul>
			                        	<c:forEach items="${oldTemp.oldMap.lunboList }" var="lunboList" begin="0" end="2">
			                        		<li><a href="${lunboList.titleUrl }"><img width="300" height="210" title="${lunboList.stTitles }" src="${lunboList.listPics }" target="_blank"></a></li>
			                        	</c:forEach>
			                        </ul>
			                    </div>
			                    <div class="btn_ico">
			                        <ul>
				                            <li class="">1</li>
				                            <li class="">2</li>
				                            <li class="current">3</li>
			                        </ul>
			                    </div>
                   			 </c:if>
                        </div>
                        <!-- 轮播图 end-->
                       <c:if test="${oldTemp.oldMap.oldZXList!=null&&oldTemp.oldMap.oldZXList.size()>0 }">
			                <div class="box1_txt fL">
			                	<c:forEach items="${oldTemp.oldMap.oldZXList }" var="oldZXList" begin="0" end="0">
		                			<h2 class="box1_title"><a href="${oldZXList.titleUrl }" target="_blank">${oldZXList.stTitles }</a></h2>
		                			<p class="box1_p f14">
			                         ${oldZXList.stContents }<a target="_blank" href="${oldZXList.titleUrl }" class="f14">[详细]</a>
			                    	</p>
		                		</c:forEach>
			                </div>
                	</c:if>
                    </div>
                    <!--box1 end-->
                    <!--box2 start-->
                    <div class="box2 mn_top clearfix">
                        <div class="box2_sale fL">
                            <div class="clearfix">
                                <span class="tb fL"></span>
                                <c:if test="${spConList!=null&&spConList.size()>0 }">
		                    		<c:forEach items="${spConList }" var="spConList" begin="0" end="0">
		                                <h3 class="sale_title fL">${spConList.stTitles }</h3>
		                    		</c:forEach>
                    			</c:if>
                            </div>
                            <ul class="sale_ul clearfix">
                                <li class="kk white">去看看→ </li>
                                 <c:if test="${oldTemp.oldMap.kkList!=null&&oldTemp.oldMap.kkList.size()>0 }">
		                        	<c:forEach items="${oldTemp.oldMap.kkList }" var="kkList" varStatus="i">
		                        		 <c:if test="${oldTemp.oldMap.kkList.size()==i['index']+1 }">
		                        		 	<li><a href="${kkList.titleUrl }" title="${kkList.stTitles }" target="_blank">${kkList.stTitles }</a></li>
		                        		 </c:if>
		                        		 <c:if test="${oldTemp.oldMap.kkList.size()!=i['index']+1 }">
		                        		 	<li><a href="${kkList.titleUrl }" title="${kkList.stTitles }" target="_blank">${kkList.stTitles }</a> |</li>
		                        		 </c:if>
		                        	</c:forEach>
                       			 </c:if>
                            </ul>
                            <div class="sale_list clearfix">
                                <c:if test="${oldTemp.oldMap.oldZXList!=null&&oldTemp.oldMap.oldZXList.size()>0 }">
		                        	<c:forEach items="${oldTemp.oldMap.oldZXList }" var="oldZX" begin="1" end="3">
		                        		<div class="sale_box fL">
			                                <a target="_blank" href="${oldZX.titleUrl }" class="">
			                                    <img src="${oldZX.listPics }" alt="${oldZX.stTitles }" class="sale_pic">
			                                </a>
			                                <p class="sale_pic_t"><a target="_blank" href="${oldZX.titleUrl }" class="g444">${oldZX.stTitles }</a></p>
			                                <p class="sale_des">
				                                <c:if test="${oldZX.stContents.length()>41 }">${oldZX.stContents.substring(0,42) }...</c:if>
				                                <c:if test="${oldZX.stContents.length()<41 }">${oldZX.stContents }...</c:if>
				                                <a target="_blank" href="${oldZX.titleUrl }">详细</a>
			                                </p>
		                                </div>
		                        	</c:forEach>
                       			 </c:if>
                            </div>
                        </div>
                        <!-- ====== -->
                    </div>
                    <!--box2 end-->
                </div>
                <!-- 新的右侧报名入口 -->
                <div class="zt_right_bb fr">
            		<%@include file="/inc/baoming_plane.inc" %>
                </div>
                <!-- /新的右侧报名入口 -->
			</div>
            <!--box3 start-->
            <div class="box2 box3 mn_top clearfix">
                <div class="box2_sale fL">
                    <div class="clearfix">
                        <span class="tb fL"></span>
                         <c:if test="${spConList!=null&&spConList.size()>0 }">
	                    	<c:forEach items="${spConList }" var="spConList" begin="2" end="2">
	                    	 <h3 class="sale_title fL">${spConList.stTitles }</h3>
	                    	</c:forEach>
                     	</c:if>
                    </div>
                    <ul class="sale_ul clearfix">
                        <c:if test="${oldTemp.oldMap.tgList!=null&&oldTemp.oldMap.tgList.size()>0 }">
                    		<c:forEach items="${oldTemp.oldMap.tgList }" var="tgList">
                    			<li><a href="${tgList.titleUrl }" target="_blank">${tgList.stTitles }</a></li>
                        	    <li><a href="${tgList.titleUrl }" class="bm_btn white" target="_blank">报名</a></li>
                    		</c:forEach>
                        </c:if>
                    </ul>
                    <div class="sale_list clearfix">
                        <!--=========================-->
                         <c:if test="${oldTemp.oldMap.oldZXList!=null&&oldTemp.oldMap.oldZXList.size()>0 }">
                        	<c:forEach items="${oldTemp.oldMap.oldZXList }" var="oldZX" begin="5">
                        		<div class="sm_box2 fL clearfix">
		                            <div class="sm_photo fL">
		                                <a href="${oldZX.titleUrl }" target="_blank"><img src="${oldZX.listPics }" alt="${oldZX.stTitles }" class="sm_pic"></a>
		                            </div>
		                            <div class="sm_list fR">
		                                <h3 class="tcph"><a href="${oldZX.titleUrl }" target="_blank" class="f14">${oldZX.stTitles }</a></h3>
		                                <p>${oldZX.stContents }<a href="${oldZX.titleUrl }" target="_blank">[详细]</a>
		                                </p>
		                            </div>
	                       		 </div>
                        	</c:forEach>
                        </c:if>
                        <!--=========================-->
                    </div>
                </div>
            </div>
            <!--box3 end-->

            <!--box4 start-->
            <div class="box2 box4 mn_top clearfix">
                <div class="box2_sale">
                    <div class="clearfix">
                        <span class="tb fL"></span>
                        <h3 class="sale_title fL">网友观点</h3>
                    </div>
                    <div class="wygd clearfix">
                        <div class="wygd_show fL">
                            <ul>
                               <c:if test="${oldTemp.oldMap.guandianList!=null&&oldTemp.oldMap.guandianList.size()>0 }">
                            		<c:forEach items="${oldTemp.oldMap.guandianList}" var="guandian">
                            			<li>
		                                    <h4>${guandian.stTitles }</h4>
		                                    <p class="g444"><span class="blue_c">${guandian.stContents }</span></p>
                               			 </li>
                            		</c:forEach>
                            	</c:if>
                            </ul>
                        </div>
                        
                    </div>

                </div>
            </div>
            
            <!--box5 start-->
            <div class="box5">
            	
                <div class="box5_con clearfix">
                    <!--<div class="box5_l clearfix fL">
                        <div class="box5_l_pic fL">
                            <img src="images_old/zj_10.jpg" alt="团长名称"/>
                            <p class="f14">姓名：团长名称</p>
                        </div>
                        <div class="box5_l_txt fR">
                            <h4 class="b5_title f14 white">历史战绩</h4>
                            <p class="g444">带团<span class="red_c">100</span>期</p>
                            <p class="g444">购车<span class="red_c">2590</span>辆</p>
                            <p class="g444">累计组织<span class="red_c">3000</span>人次</p>
                        </div>
                    </div>
                    <div class="box5_c fL">-->
                        <div class="box5_l box5_l_s clearfix fL">
                            <span class="box5_l_db"></span>
                        </div>
                        <div class="box5_c fL box5_c_s">
                            <c:if test="${oldTemp.oldMap.dbList!=null&&oldTemp.oldMap.dbList.size()>0 }">
								<c:forEach items="${oldTemp.oldMap.dbList }" var="dbList">
									<span class="b5_h"></span>
	                            	<p class="f14 g444">${dbList.stContents }</p>
	                           		<span class="b5_h2"></span>
								</c:forEach>
                            </c:if>
                        </div>
                        <div class="box5_r fL">
                            <p class="home_dis_link">
                                <a href="http://e.weibo.com/tuanchewang" target="_blank" class="sinapic"></a>
                                <a href="http://t.qq.com/tuanche" target="_blank"  class="txpic"></a>
                            </p>
                        </div>
                    <!--</div>-->
                </div>
				
                <div class="box6">
                    <div class="box6_title"><h3 class="white">买车谍报 往期回顾</h3>
                        <a href="http://${cityCode}.tuanche.com/zx-tg/wq/" class="b6_more">更多</a>
                    </div>
                    <div class="hg_block">
                        <div class=LeftBotton id=left_arr></div>
                        <div class=Cont id=hgscroll>
                            <!-- 图片列表 begin -->
                            <c:if test="${oldTemp.oldMap.WQList!=null&&oldTemp.oldMap.WQList.size()>0 }">
                            	<c:forEach items="${oldTemp.oldMap.WQList }" var="WQList">
                            		 <div class=box>
                               		   <a class=imgBorder href="${WQList.titleUrl }" target=_blank>
                                       <img alt="${WQList.stTitles }" src="${WQList.listPics }"/>
                                	   </a>
                                       <p><a href="${WQList.titleUrl }" target=_blank></a>${WQList.stTitles }</p>
                                    </div>
                            	</c:forEach>
                            </c:if>
                            <!-- 图片列表 end -->
                        </div>
                        <div class=RightBotton id=right_arr></div>
                    </div>
                </div>

            </div>
            
            <!-- 底部报名 -->
            <div class="box2 box4 mn_top clearfix">
             <%@include file="/inc/baoming_vertical.inc" %>
            </div>
            
        </div>

        <div class="seo_content">
					<style type="text/css">
					.seo_content{margin: 10px auto;width: 990px;background: #fff;}
					.seo_content .hot_city{ border: 1px solid #e0e3e8; border-top:2px solid #92CAEB;padding-bottom: 10px; }
					.footer_cityList { width: 963px; padding-top: 13px; padding-left: 25px; font-size: 12px; font-weight: normal; }
					.footer_cityList ul{ position:relative; padding:0 0 10px 120px; height:auto;overflow:hidden; border-bottom:dotted 1px #e3e3e3;}
					.footer_cityList ul li { list-style:none; line-height: 23px; height: 23px; float: left; width: auto; white-space:nowrap; padding: 0 10px; }
					.footer_cityList ul li.lifirst{ position:absolute; top:0; left:0;width:110px; white-space:normal; color:#999;}
					.footer_cityList ul li a { font-size: 12px; display: block; color:#666 }
					.footer_cityList ul li a:hover{text-decoration:underline; color:#666 }
					.clr{clear:both}
					</style>         
					<div class="hot_city">
						<div class="footer_cityList">
		                   <c:if test="${oldTemp.oldMap.XGList!=null&&oldTemp.oldMap.XGList.size()>0 }">
									<c:forEach items="${oldTemp.oldMap.XGList }" var="XGList">
										<div class="footer_cityList">
				                        <ul>
				                            <li class="lifirst">${XGList.stTitles }相关介绍</li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/" target="_blank">${XGList.stTitles }</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/price/" target="_blank">${XGList.stTitles }报价</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/xiaoliang/" target="_blank">${XGList.stTitles }销量</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/ping/" target="_blank">${XGList.stTitles }口碑</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/canshu/" target="_blank">${XGList.stTitles }参数</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/fuel/" target="_blank">${XGList.stTitles }油耗</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/photo/" target="_blank">${XGList.stTitles }图片</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/dealer/" target="_blank">${XGList.stTitles }经销商</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/bbs/" target="_blank">${XGList.stTitles }论坛</a></li>
				                            <li><a href="http://tuanche.com/c${XGList.id }/bt/" target="_blank">${XGList.stTitles }报价及图片</a></li>
				                        </ul>
				                        <div class="clr"></div>
									  </div>
									</c:forEach>
							</c:if>
						</div>
                        <div class="footer_cityList">
           				 <ul>
                             <c:if test="${oldTemp.oldMap.XGendList!=null&&oldTemp.oldMap.XGendList.size()>0 }">
                             	<li class="lifirst">资讯相关介绍</li>
                             	<c:forEach items="${oldTemp.oldMap.XGendList }" var="XGendList">
                             		<c:if test="${specialSubject.brandId!=null&&specialSubject.brandId!='0' }">
                             			<li><a href="http://tuanche.com/cars/${XGendList.titleUrl }/" target="_blank">${XGendList.stTitles }</a></li>
                             		</c:if>
                             		<c:if test="${specialSubject.brandId==null||specialSubject.brandId==''||specialSubject.brandId=='0' }">
                             			<li><a href="http://tuanche.com/${XGendList.titleUrl }/" target="_blank">${XGendList.stTitles }</a></li>
                             		</c:if>
                             	</c:forEach>
                             </c:if>
                          </ul>
           					 <div class="clr"></div>					
           				</div>
					</div>
		</div>
    </div>
    <!-- main end-->
    <div class="friend">
        <div class="friend_title"><div class="friend_tab"><ul><li>合作链接</li></ul></div></div>
        <div>
        	<div class="f_tab_01">
       			 <ul>  
        			<li><a target="_blank" href="http://tuanche.com/zt-mc/10949/">团车网买车便宜吗?什么时候买车最优惠</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10939/">【团车网买车】买车如何按揭</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10907/">团车网买车</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10961/">团车网买车推荐，新手买车攻略必修课</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10908/">团车网靠谱吗</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10938/">团车网买车靠谱吗</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10950/">专家解读：二手车买卖团车网靠谱吗</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10962/">车主谈团车网靠谱吗?团车买车真实体验</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10916/">买车团车网真的便宜吗</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10921/">团购买车,团车网真的便宜吗</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10948/">参加团购买车,团车网真的便宜吗</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10959/">购车会,团车网真的便宜吗</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10928/">团车网怎么样</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10947/">团车网怎么样,小编带您深入剖析</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10960/">买车优惠,团车网怎么样</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10920/">团车网能便宜多少</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10919/">团车网一般可以便宜多少钱下来</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10931/">团车网能便宜多少？买车必问</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10957/">团车网能便宜多少？最新优惠信息展示</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10958/">买好车不买贵车，关注团车网一般可以便宜多少钱下来</a></li>
			        <li><a target="_blank" href="http://tuanche.com/zt-mc/10970/">团车网能便宜多少，有哪些优惠信息</a></li>
				</ul>
			</div>
		</div>
    </div>
    <!-- 底部 -->
 <%@include file="/inc/footer.inc" %>
    <!--返回顶部-->
    <div class="botm_btn">
        <a id="feed_btn_qq" style="CURSOR: pointer"></a>
        <a id="feed_btn" href="javascript:void(0)"></a>
    </div>

    <div id="erwm_box">
        <a href="javascript:void(0)">
            <span>团车网微信</span>
            <img src="http://static.tuanche.com/public/images/wechat_s.png" width="90" height="90"/>
            <span class="wh">手机扫一扫</span>
        </a>
    </div>
        <script type="text/javascript" src="http://static.tuanche.com/zt/js_old/base.js"></script>
        
        <script src="http://static.tuanche.com/zt/js_old/ScrollPic.js" type=text/javascript></script>
        <script language=javascript type=text/javascript>
        <!--//--><![CDATA[//><!--
            var scrollPic_02 = new ScrollPic();
            scrollPic_02.scrollContId = "hgscroll"; //内容容器ID
            scrollPic_02.arrLeftId = "left_arr";//左箭头ID
            scrollPic_02.arrRightId = "right_arr"; //右箭头ID
            scrollPic_02.frameWidth = 848;//显示框宽度
            scrollPic_02.pageWidth = 172; //翻页宽度
            scrollPic_02.speed = 10; //移动速度(单位毫秒，越小越快)
            scrollPic_02.space = 10; //每次移动像素(单位px，越大越快)
            scrollPic_02.autoPlay = true; //自动播放
            scrollPic_02.autoPlayTime = 3; //自动播放间隔时间(秒)
            scrollPic_02.initialize(); //初始化
            //--><!]]>
        </script>
        <script type="text/javascript">
            //收藏本页面
            function add_house(url, title) {
                try {
                    window.external.addFavorite(url, title);
                }
                catch (e) {
                    try {
                        window.sidebar.addPanel(title, url, "");
                    }
                    catch (e)
                    {
                        alert("加入收藏失败，请使用Ctrl+D进行添加");
                    }
                }
            }
        </script>
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
        <script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
        <script type="text/javascript" src="http://static.tuanche.com/public/js/tongji/tuanchecom_tongji.js"></script>
        <script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript">
        	$(function(){
				//在线客服
				BizQQWPA.addCustom({aty: '0', a: '0', nameAccount: 4006969123, selector: 'feed_btn_qq',title:'欢迎咨询我'});
			})
        </script>
</body>
</html>