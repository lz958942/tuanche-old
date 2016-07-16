<!-- 精选讯息 -->
<div class="border-e">
	<p class="odd-fff"> <i class='tit-ico select-ico'></i>
		精选讯息
	</p>
	<dl class="odd-fff">
		<#if select_zixun_contentList?exists>
			<#list select_zixun_contentList as select_zixun>
				<dd>
					<a pl="pg=zixuIndex&pl=xunxi" href="${select_zixun.hyperlink!'javascript:void(0);'}" target="_blank">
						<#assign titIndOf = select_zixun.title?index_of('&&') >
						<#if titIndOf==-1>
							${select_zixun.title}
							<#else>
							${select_zixun.title?substring(0,titIndOf)}	
						</#if>
					</a>
				</dd>
			</#list>
		</#if>
	</dl>
</div>