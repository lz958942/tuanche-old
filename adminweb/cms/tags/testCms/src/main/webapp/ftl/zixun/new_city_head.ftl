<div class="clearfix header-top">
    <div class="wrap1200 mc">
        <div class="fl header-top-tip">
            让买车用车更省钱 tuanche.com 中国第一汽车电商平台
        </div>
        <div class="fr">
            <div id="logined" class="fl logined">               
            </div>
            <div id="login-link" class="fl login-link">
                <a href="http://uc.tuanche.com/login/toLogin" target="_blank">
                    登陆
                </a>
                <span>|</span>
                <a href="http://uc.tuanche.com/register/toRegister" target="_blank">
                    注册
                </a>
            </div>
            <div id="tapicon" class="clearfix fl tapicon">
                <ul>
                    <li id="tapicon-person" class="tapiconli tapicon-person">
                        <div class="tapicon-pop tapicon-person-pop">
                            <em class="tap-jiao"></em>                            
                            <ul>
                                <li>
                                    <a href="http://uc.tuanche.com/activity/applyactivity" target="_blank">我的活动<span id="hovernum" class="hovernum"></span></a>
                                </li>
                                <li>
                                    <a href="http://uc.tuanche.com/account/toAccountcenter" target="_blank">账户设置</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li id="tapicon-mail" class="tapiconli tapicon-mail">
                        <span id="activehovernum" class="activehovernum"></span>
                        <div class="tapicon-pop tapicon-mail-pop">
                            <em class="tap-jiao"></em>
                            <ul id="tapicon-mail-ul">
                                <li class="noactive">您还没有即将开始的活动哦！</li>                               
                            </ul>
                        </div>
                    </li>
                    <li id="tapicon-qi" class="tapiconli tapicon-qi addFavorite"></li>
                    <li id="tapicon-xin" class="tapiconli tapicon-xin">
                        <div class="tapicon-pop tapicon-xin-pop"> 
                            <em class="tap-jiao"></em>                       
                            <p><wb:follow-button uid="2445730697" type="red_2" width="136" height="24" ></wb:follow-button></p>
                        </div>
                    </li>
                    <li id="tapicon-baozhang" class="tapiconli tapicon-baozhang">
                        <div class="tapicon-pop tapicon-baozhang-pop">
                            <em class="tap-jiao"></em> 
                            <div class="yiyi-tit">一亿元诚信保障金</div>
                            <div class="yiyi-tit-con">
                                <a href="http://tuanche.com/zx-wh/xw/558046.html" target="_blank"><img src="http://static.tuanche.com/public/images/yiyi.png"></a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div> 
            <div class="fl header-top-tel">
                4006969123
            </div>         
        </div>   
    </div>    
</div>
<!--头部-->
<div class="clearfix header">    
    <div class="wrap1200 mc">
        <a href="${city.url!'http://tuanche.com'}" class="fl logo">
            团车网
        </a>
        <div id="citychoi" class="fl city">
           <span id="choiceCity" class="choiceCity"><em class="icon city-icon"></em><a href="http://${city.py}.tuanche.com/" data-cur="${city.id}">${city.dname}</a></span>
            <div class="city-con">
                <em class="jiao"></em>
                <ul>
                <#list changeCity?keys as key>
                <#assign citys=changeCity[key]>
                   <li class="clearfix">
                        <h4>${key}</h4>
                        <div class="clearfix s_ul">
                        	<#list citys as city>
                        		<#if city.openStatus != 3>
		                        <a title="${city.name}汽车团购"  href="http://${city.py!""}.tuanche.com/">   ${city.name}</a>
		                        </#if>
		                   </#list>
                        </div>
                        <div style="clear:both"></div>
                    </li>
                </#list>
                </ul>
            </div>            
        </div> 
        <div class="fl search">
            <div id="bdcs">
            </div> 
            <div class="hotlink">
                <label>热门车型：</label>
                <#if hotSearch?exists>
                		<#list hotSearch as search>
                	 	 <a  pl="pg=cityIndex&pl=hotCarstyle" href="http://${city.py}.tuanche.com/c${search.id?c}/tuan/" target="_blank">${search.name}</a>
                	 	</#list>
                	</#if>
            </div>       
        </div> 
        <div class="fr feature">
            <dl class="clearfix">
                <em class="featureIcon1"></em>
                <div class="fl">
                    <dt>低价</dt>
                    <dd>同期本地最低</dd>
                </div>            
            </dl>
            <dl>
                <em class="featureIcon2"></em>
                <div class="fl">
                    <dt>安全</dt>
                    <dd>杜绝欺诈陷阱</dd>
                </div>
            </dl>
            <dl>
                <em class="featureIcon3"></em>
                <div class="fl">
                    <dt>免费</dt>
                    <dd>参团全程免费</dd>
                </div>
            </dl>
        </div> 
    </div>    
</div>
<!--头部-->
<!--导航-->
<div class="wrap1200 mc clearfix nav">
	<#if dian_say_city?exists>
	    <div class="fl nav-t">
	        <em></em>按品牌车型团购
	    </div>
    </#if>
    <div class="clearfix fl nav-link <#if !dian_say_city?exists>nav-link1200</#if>">
        <div class="nav-link-a"><a class="<#if dian_say_city?exists>cur</#if> nav-link-a" href="http://${city.py}.tuanche.com/" target="_blank">首页</a></div>
        <div class="nav-link-a"><a class="nav-link-a"  href="http://${city.py}.tuanche.com/brand/" title="新车团购" target="_blank">新车团购</a></div>
        <#if city.id==10>
        	<div class="nav-link-a"><a class="nav-link-a" href="http://${city.py}.tuanche.com/tjc/" target="_blank">特价车<em class="nav-new">新</em></a></div>
        </#if>
        <#if isSecond >
        	<div class="nav-link-a"><a class="nav-link-a" href="http://bj.tuanche.com/usedcar/?v=1101" target="_blank">旧车竞卖</a></div>
        </#if>
       <#if have_zhs?exists>
        	<div class="nav-link-a"> <a class="nav-link-a"  href="http://${city.py}.tuanche.com/zhuangshi/">团车养车</a></div>
        </#if>
        <#if city.id==10>
         <div class="nav-link-a"> <a class="nav-link-a" href="http://zhidao.tuanche.com/" target="_blank">团车知道</a></div>
          <#else>
          	<div class="nav-link-a"> <a class="nav-link-a"href="http://www.tuanche.com/baozhang/" target="_blank">团购保障</a></div>
        	<div class="nav-link-a"><a class="nav-link-a" href="http://zhidao.tuanche.com/" target="_blank">团车知道</a></div>
          </#if>
        <div id="nav-link-a-xuan" class="nav-link-a nav-link-a-xuan">
        <a class="xuan" href="http://tuanche.com/che/" target="_blank">选车<em></em></a>
        <div id="header_sub_nav" class="header_sub_nav">
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
        </div>
        <#if have_zixun?exists>
        	<div class="nav-link-a">  <a class="nav-link-a <#if !dian_say_city?exists>cur</#if>" href="http://${city.py}.tuanche.com/zx/">团购资讯</a></div>
        </#if>
    </div>   
</div>
<!--导航-->