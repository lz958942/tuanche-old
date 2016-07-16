<div class="border-e">
	<p class="odd-fff"> <i class='tit-ico news-ico'></i>
		要闻资讯
	</p>
	<ul>
		<#if vital_news_zixun_contentList?exists>
			<#list vital_news_zixun_contentList as vital_news>
				<li class="odd-fff">
					<a pl="pg=zixuIndex&pl=yaowen" href="${vital_news.hyperlink!'javascript:void(0);'}" target="_blank">
						<em></em>
						<#assign titIndOf = vital_news.title?index_of('&&') >
						<#if titIndOf==-1>
							${vital_news.title}
							<#else>
							${vital_news.title?substring(0,titIndOf)}	
						</#if>
					</a>
				</li>
			</#list>
		</#if>
	</ul>
</div>