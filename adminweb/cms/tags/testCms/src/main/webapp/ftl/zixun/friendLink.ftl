 <!-- 友链 -->
 <#if flLink?exists || hotNewCars?exists>
    <div class="tc_link_wrap">
    	<div class="tc_link_tit">
        	<dl class="clearfix">
            	<!-- 第一个选项要加 first_dd 这个类 -->
                <#if flLink?exists>
            	<dd class="cur first_dd"><i>|</i><a href="javascript:;">友情链接</a></dd>
            	</#if>
            	<#if hotNewCars?exists>
                <dd ><i>|</i><a href="javascript:;">热门新车</a></dd>
                </#if>
            </dl>
        </div>
        <div class="tc_link_con">
            <div class="tc_link_con_tab" style="display:block;">
                <ul class="clearfix">
                	<#if flLink?exists>
	                     <#list flLink as link> 
	                     	 <#if link_index != 0>
	                     	 	<li><span>|</span></li>
	                     	 </#if>
	                     	 <li><a  href="${link.flUrl}" target="_blank">${link.flName}</a></li>
		                 </#list>
		            </#if>
                </ul>
            </div>
             <div class="tc_link_con_tab">
                <ul class="clearfix">
                     <#if hotNewCars?exists>
	                     <#list hotNewCars as cars> 
	                     	 <#if cars_index != 0>
	                     	 	<li><span>|</span></li>
	                     	 </#if>
	                     	 <li><a  href="${cars.url}" target="_blank">${cars.carstyle}</a></li>
		                 </#list>
		            </#if>
               </ul>
            </div>
        </div>
    </div>
</#if>