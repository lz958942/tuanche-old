
<div class="tc_container_2">
    <div class="tc_link_wrap">
    	<div class="tc_link_tit">
        	<dl class="clearfix">
            	<c:if test="${friendLinks!=null&&friendLinks.size()>0}">
            	    <dd class="cur first_dd"><i>|</i><a href="##friendlink">友情链接</a></dd>
            	</c:if>
            	<c:if test="${businesses!=null&&businesses.size()>0}">
            	    <dd ${friendLinks==null||friendLinks.size()==0?'class="cur first_dd"':''}>${friendLinks!=null&&friendLinks.size()>0?'<i>|</i>':''}<a href="##businesses">商务合作</a></dd>
            	</c:if>
            	
            	<c:if test="${hotNewBuys!=null&&hotNewBuys.size()>0}">
            		<dd ${(friendLinks==null||friendLinks.size()==0)&&(businesses==null||businesses.size()==0)?'class="cur first_dd"':''}>
            		${(businesses!=null&&businesses.size()>0)||(friendLinks!=null&&friendLinks.size()>0)?'<i>|</i>':''}
            		<a href="##hotnewcar">热门新车</a>
            		</dd>
            	</c:if>
                
                <c:if test="${keywordList!=null&&keywordList.size()>0}">
            	    <dd ${(friendLinks==null||friendLinks.size()==0)&&(hotNewBuys==null||hotNewBuys.size()==0)&&(businesses==null||businesses.size()==0)?'class="cur first_dd"':''}>
            	    ${(businesses!=null&&businesses.size()>0)||(friendLinks!=null&&friendLinks.size()>0)||(hotNewBuys!=null&&hotNewBuys.size()>0)?'<i>|</i>':''}
            	    <a href="##keywordList">车型汇总</a>
            	    </dd>
            	</c:if>
            </dl>
        </div>
        <div class="tc_link_con">
            <c:if test="${friendLinks!=null&&friendLinks.size()>0}">
        	<div class="tc_link_con_tab" style="display:block;">
                <ul class="clearfix">
                    <c:forEach  var="item" items="${friendLinks}">
                      <li><a href="${item.flUrl}" target="_blank">${item.flName}</a></li>
                       <li><span>|</span></li>
                    </c:forEach>
                    <li><a href="tencent://message/?Menu=yes&uin=1729190726&Site=tuanche&Service=300&sigT=45a1e5847943b64c6ff3990f8a9e644d2b31356cb0b4ac6b24663a3c8dd0f8aa12a595b1714f9d45">友情链接QQ</a></li>
                 </ul>
             </div>
            </c:if>
            <c:if test="${businesses!=null&&businesses.size()>0}">
        	<div class="tc_link_con_tab" ${friendLinks==null||friendLinks.size()==0?'style="display:block;"':''}>
                <ul class="clearfix">
                    <c:forEach  var="item" items="${businesses}">
                      <li><a href="${item.flUrl}" target="_blank">${item.flName}</a></li>
                      <li><span>|</span></li>
                    </c:forEach>
                    <li><a href="tencent://message/?Menu=yes&uin=1729190726&Site=tuanche&Service=300&sigT=45a1e5847943b64c6ff3990f8a9e644d2b31356cb0b4ac6b24663a3c8dd0f8aa12a595b1714f9d45">商务合作QQ</a></li>
                 </ul>
            </div>
            </c:if>
            <c:if test="${hotNewBuys!=null&&hotNewBuys.size()>0}">
           	  <div class="tc_link_con_tab" ${(friendLinks==null||friendLinks.size()==0)&&(businesses==null||businesses.size()==0)?'style="display:block;"':''}>
                <ul class="clearfix">
                      <c:forEach  var="item" items="${hotNewBuys}">
                        <li><a href="${item.url}">${item.carstyle}</a></li>
                        <li><span>|</span></li>
                       </c:forEach>
                </ul>
              </div>
            </c:if>
          	<c:if test="${keywordList!=null&&keywordList.size()>0}">
        	<div class="tc_link_con_tab" ${(friendLinks==null||friendLinks.size()==0)&&(businesses==null||businesses.size()==0)&&(hotNewBuys==null||hotNewBuys.size()==0)?'style="display:block;"':''}>
                <ul class="clearfix">
                    <c:forEach  var="item" items="${keywordList}">
                      <li><a href="${item.url}" target="_blank">${item.keyword}</a></li>
                       <li><span>|</span></li>
                    </c:forEach>
                 </ul>
            </div>
            </c:if>
        </div>
    </div>
</div>
</div>

<!-- 底部 -->
<div class="tc_container_1">
	<div class="tc_container_1 tc_footer_p">
    	<div class="tc_container_3">
        	<dl class="clearfix">
            	<dd class="first_p">
                	<i class="footer_ico footer_ico_1"></i>
                    <h3>车源保证</h3>
                    <p>甄选本地优质正规4S店</p>
                </dd>
                <dd>
                	<i class="footer_ico footer_ico_2"></i>
                    <h3>低价保证</h3>
                    <p>4S店挑最低价格合作</p>
                </dd>
                <dd>
                	<i class="footer_ico footer_ico_3"></i>
                    <h3>订金可退</h3>
                    <p>规定的时间内可退还订金</p>
                </dd>
                <dd>
                	<i class="footer_ico footer_ico_4"></i>
                    <h3>优先提车</h3>
                    <p>享有优先提车权</p>
                </dd>
                <dd>
                	<i class="footer_ico footer_ico_5"></i>
                    <h3>售后维权</h3>
                    <p>质量问题，团车网协助解决</p>
                </dd>
                <dd>
                	<i class="footer_ico footer_ico_6"></i>
                    <h3>全国联保</h3>
                    <p>正规4S店均可维修保养</p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="tc_container_1 tc_footer_l">
        <div class="tc_container_1 clearfix">
            <div class="footer_link interior_link">
                <ul class="clearfix">
                    <li><a href="${cityUrl}/">${cityName}团车网</a></li>
                    <li><span>|</span></li>
                    <li><a href="http://tuanche.com/about/" target="_blank">关于团车网</a></li>
                    <li><span>|</span></li>
                    <li><a href="http://tuanche.com/about_3.shtml" target="_blank">联系我们</a></li>
                       <li><span>|</span></li>
                    <li><a href="http://tuanche.com/about_zhaopin.html" target="_blank">人才招聘</a>
                    	<i class="hot_ico"></i>
                    </li>
                    <li><span>|</span></li>
                    <li><a href="http://tuanche.com/huizong/" target="_blank">车型汇总</a></li>
                    <li><span>|</span></li>
                    <li><a href="javascript:void(0);" onclick="AddFavorite('${cityUrl}','${cityName}团车网')">收藏本站</a></li>
                    <li><span>|</span></li>
                    <li><a href="http://tuanche.com/about_tousu.shtml" target="_blank">我要投诉</a></li>
                    <li><span>|</span></li>
                    <li><a href="http://tuanche.com/about_weiquan.html" target="_blank">维权声明 </a></li>
                </ul>         
            </div>
        </div>
        <p class="tc_copyright">团车网电话：400-6969-123团车互联网信息服务（北京）有限公司 &copy;2014 翻版必究 | 京ICP备12044574号-2</p>
         <p class="icpDetailBottom"><a href="http://www.tuanche.com/jingyingxuke.html" target="_blank">电信与信息服务业务经营许可证：京ICP证130329</a></p>    
        
    </div>
</div>