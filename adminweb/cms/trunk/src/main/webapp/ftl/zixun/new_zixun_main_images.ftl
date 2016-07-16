<!-- 大图切换 -->
<div id="banner" class="banner">
    <div id="banner-flexslider" class="flexslider">
      <ul class="slides">
		<#if big_image_switch_contentList?exists>
			<#list big_image_switch_contentList as big_image>
				<li>
                  <a pl="pg=zixuIndex&pl=tu" href="${big_image.hyperlink!'javascript:void(0);'}" target="_blank">
                      <img src="${big_image.imagUrl!''}" data-url="${big_image.imagUrl!''}" class="ban-scrollLoading"/>
                  </a>
                  <p class="banner-txt">${big_image.title}</p>
                  <div class="banner-txt-bg"></div>
                </li>             
			</#list>
		</#if>
      </ul>
    </div>
</div>