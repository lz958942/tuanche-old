<!-- 头部 -->
<div class="tc_container_1 tc_header_ls">
<div class="tc_container_2">
	<div class="tc_header_main clearfix">
    	<div class="tc_logo_wrap">
        	<h1><a href="http://tuanche.com/" class="tc_logo" title="团车网">团车网</a></h1>
            <span>买车！就上团车网</span>
        </div>
        <div class="tc_search_wrap">
        	<div class="tc_search">
        		<div id="bdcs">
        		</div>
        		<!-- 
        		<script type="text/javascript">document.write(unescape('%3Cdiv id="bdcs"%3E%3C/div%3E%3Cscript charset="utf-8" src="http://znsv.baidu.com/customer_search/api/js?sid=13628016066063889893') + '&plate_url=' + (encodeURIComponent(window.location.href)) + '&t=' + (Math.ceil(new Date()/3600000)) + unescape('"%3E%3C/script%3E'));</script>
            	<form action="http://${city.py}.tuanche.com/tuan/search/"  method="post">
                	<input type="text" class="sear_inpt" placeholder="想团车，先搜一下" value="" name="keyword" autocomplete="off">
                    <input type="submit" class="sear_btn" value="搜索">
                </form> -->
            </div>
            <div class="tc_hotsearch">
            	<dl class="clearfix">
                	<dt>热门车型：</dt>
                	<#if hotSearch?exists>
	            	<#list hotSearch as search>
	            		<dd><a  pl="pg=cityIndex&pl=hotCarstyle" href="http://${city.py}.tuanche.com/c${search.id?c}/tuan/" target="_blank">${search.name}</a></dd>
	            	</#list>
            		</#if>
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
	<div class="tc_container_2">
    	<dl class="clearfix">
        	<dt class="nav_a"><span>按品牌车型团购</span></dt>
            <dd class="cur"><a href="http://${city.py}.tuanche.com/" target="_blank">首页</a></dd>
            <dd><a href="http://${city.py}.tuanche.com/brand/" title="新车团购" target="_blank">新车团购</a></dd>
          	 <#if city.id==10>
         		 <dd><a href="http://${city.py}.tuanche.com/tjc/" target="_blank">特价车</a></dd>
         	 </#if>
             <#if isSecond?exists>
         	  <dd><a href="http://bj.tuanche.com/usedcar/?v=1101" target="_blank">旧车竞卖</a></dd>
            </#if>
            <#if have_zhs?exists>
              <dd><a href="http://${city.py}.tuanche.com/zhuangshi/">团车养车</a></dd>
            </#if>
             <#if city.id==10>
            	<dd><a  href="http://zhidao.tuanche.com/" target="_blank">团车知道</a></dd>
            <#else>
            	<dd><a href="http://www.tuanche.com/baozhang/" target="_blank">团购保障</a></dd>
            	<dd><a href="http://zhidao.tuanche.com/" target="_blank">团车知道</a></dd>
            </#if>
            <dd class="sub_nav">
            	<a href="##" class="a_transition">选车<i class="ico ico_down_nav"></i></a>
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
                        <dd><a href="http://zhidao.tuanche.com/list_22/" target="_blank">汽车销量</a></dd>
                        <dd><a href="http://zhidao.tuanche.com/list_13/" target="_blank">汽车导购</a></dd>
                        <dd><a href="http://tuanche.com/koubei/" target="_blank">汽车口碑</a></dd>
                        <dd><a href="http://tuanche.com/photo/" target="_blank">汽车图片</a></dd>
                        <dd><a href="http://zhidao.tuanche.com/list_12/" target="_blank">汽车油耗</a></dd>
                        <dd><a href="http://zhidao.tuanche.com/list_20/" target="_blank">汽车试驾</a></dd>
                        <dd><a href="http://tuanche.com/dealer/" target="_blank">汽车经销商</a></dd>
                        <dd><a href="http://zhidao.tuanche.com/list_100/" target="_blank">汽车排行榜</a></dd>
                        <dd><a target="_blank" href="http://zc.tuanche.com/">异地购车政策</a></dd>
                    </dl>
                </div>
            </dd>
            <#if have_zixun?exists>
          	  <dd><a href="http://${city.py}.tuanche.com/zx/">团购资讯</a></dd>
            </#if>
        </dl>
    </div>
</div>