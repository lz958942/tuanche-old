 <#assign plate_num = 0>
<#macro hot_c_g contentList num>
<#if contentList?size gt 0>
		<ul  <#if plate_num ==0> class="tab-con clearfix hotCarList" <#else>  class="tab-con clearfix hotCarList tab-con-hide"</#if> >
			<#list contentList as content>
                <li>
                    <em <#if content_index lt 3> class="top top${content_index+1}"  <#else></#if> ></em>
                    <div class="hotCarList-img">
                      <a pl="pg=cityIndex&pl=hot" target="_blank" href="${content.buyUrl!''}"><img src="${staticFile}/public/images/blank.gif" alt="" width="300" height="200" data-url="${content.spic!''}" class="scrollLoading" /></a>
                    </div>
                    <div class="hotCarList-info"> 
                       <p><a pl="pg=cityIndex&pl=hot" target="_blank" href="${content.buyUrl!''}"> ${content.allTitle}</a></p>
                        <p class="hot_num">本期报名<span class="red f14">${content.manNum!''}</span>人<ins></ins>
                    </div>
                </li>
            </#list>
        </ul>
      <#assign plate_num = 1>
</#if>

</#macro>
  <div class="tab-wrap">
                <div class="clearfix title-index title-index1">
                    <h2 class="fl"><em></em>热门汽车团购</h2>
                    <div class="fr title-index-link">
                    	<#list 1..5 as num>
                    		<#if num gt 1>
                    			<span>|</span>
                    		</#if>
			        		<#assign plate_ = 'hot_car_tem'+num+'_city'>
			        		<#assign plate = plate_?eval!"">
			        		<#if plate!="">
			        			 <a href="javascript:void(0);"  <#if num == 1>class="cur"</#if>>${plate.title}</a>  
			        		</#if>
			        	</#list>
                    </div>
                </div>
         <@hot_c_g contentList=hot_car_tem1_city_contentList![] num=1></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem2_city_contentList![] num=2></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem3_city_contentList![] num=3></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem4_city_contentList![] num=4></@hot_c_g>
        <@hot_c_g contentList=hot_car_tem5_city_contentList![] num=5></@hot_c_g>
               
            </div>