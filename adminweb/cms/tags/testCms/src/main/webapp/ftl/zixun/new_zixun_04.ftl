 <#macro groupbuy_zixun plate contentList num>
	<#if plate!="">
 <div <#if num==1> class='clearfix tab-con zixuIndex' <#else> class='clearfix tab-con zixuIndex tab-con-hide' </#if>>
            <div class="clearfix zixuIndex-img" style="width:1180px;">
                <ul>
                   <#list contentList as content>
                   	<#if content_index gt 6 ><#break></#if>
                   <#if content_index==0>
                    <#if content.imagUrl?exists>
                     <li>
                        <a target="_blank" href="${content.hyperlink}"><img width="368" height="286" src="${staticFile}/public/images/blank.gif"  data-url="${content.imagUrl}" class="scrollLoading" /></a>
                        </#if>
                        <div class="zixuIndex-mark zixuIndex-mark-first"></div>
                        <p class="zixuIndex-p"> <a target="_blank" href="${content.hyperlink}">${content.title}</a></p>
                    </li>
                    <#else>   
                    <li class="img1 <#if content_index gt 0 && content_index lt 4>img2</#if>">
                    <#if content.imagUrl?exists>
                         <a target="_blank" href="${content.hyperlink}"><img width="265" height="140" src="${staticFile}/public/images/blank.gif"  data-url="${content.imagUrl}" class="scrollLoading" /></a>
                       </#if>
                        <div class="zixuIndex-mark"></div>
                        <p class="zixuIndex-p"><a target="_blank" href="${content.hyperlink}">${content.title}</a></p>
                    </li>
                    </#if>
                    </#list>
                </ul>
                
            </div>
        </div>
        </#if>
 </#macro>       
         <div class="tab-wrap">
        <div class="clearfix title-index title-index4">
            <h2 class="fl"><em></em>团车资讯</h2> 
            <div class="fr title-index-link">
               <#if groupbuy_former_city?exists>
               <a target="_blank"  href="${groupbuy_former_city.hyperlink!''}" class='cur'>${groupbuy_former_city.title}</a>
                <span>|</span>
                </#if>
                <#if tuanche_interview_city?exists >
               <a target="_blank"  href="${tuanche_interview_city.hyperlink!''}" >${tuanche_interview_city.title}</a>  
                <span>|</span>
                 </#if>
                  <#if car_style_guide_city?exists>
               <a target="_blank"  href="${car_style_guide_city.hyperlink!''}" >${car_style_guide_city.title}</a>
                <span>|</span>
                 </#if>
                  <#if groupbuy_notice_city?exists>
                <a target="_blank"  href="${groupbuy_notice_city.hyperlink!''}" >${groupbuy_notice_city.title}</a>  
                 </#if>                   
            </div>       
        </div>
            		<@groupbuy_zixun plate=groupbuy_former_city!"" contentList=groupbuy_former_city_contentList!""  num=1></@groupbuy_zixun>
		        	<@groupbuy_zixun plate=tuanche_interview_city!"" contentList=tuanche_interview_city_contentList!""  num=2></@groupbuy_zixun>
		        	<@groupbuy_zixun plate=car_style_guide_city!"" contentList=car_style_guide_city_contentList!""  num=3></@groupbuy_zixun>
		        	<@groupbuy_zixun plate=groupbuy_notice_city!"" contentList=groupbuy_notice_city_contentList!""  num=4></@groupbuy_zixun>
    </div>