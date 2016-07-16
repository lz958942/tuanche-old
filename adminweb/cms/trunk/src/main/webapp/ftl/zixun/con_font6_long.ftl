<!--内容  纯文字 6条 （例：购买新车，初步海选）  -->
<#macro con_font6_long plate contentList>
<#if plate?exists >
	<div class="container_1">
    	<div class="title_wrap relative clearfix">
            <span class="border_l_a"></span>
           <#if plate.hyperlink?exists>
         	  <a target="_blank"  href="${plate.hyperlink}">
           </#if>
            <h3 class="title_d fl">${plate.title}</h3>
             <#if plate.hyperlink?exists>
         	  </a>
           </#if>
            <!--<a target="_blank" href="##" class="fr">更多>></a>-->
        </div>
        <div class="container_1">
            <ul class="content_list">
            <#if contentList?exists>
            	<#list contentList as content>
          			<#if content_index < 6>
          				<li>
	          			<!--<span>有<i>1253</i>人参与</span>-->
          				<a target="_blank" href="${content.hyperlink}">${content.title}</a>
          				</li>
          			</#if>
          		</#list>
            </#if>
            </ul>
        </div>
    </div>
</#if>
</#macro>