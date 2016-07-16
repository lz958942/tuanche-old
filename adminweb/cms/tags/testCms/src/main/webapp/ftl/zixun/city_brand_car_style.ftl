  <!-- 品牌 -->
 <div id="feature_tab" class="feature_tab">           
        <div id="quickSelect_id" class="tab_content">
       	 <#if series_brand?exists>
			<#list series_brand?keys as key>
				<#assign lists=series_brand[key]![]>
				<#if key_index < 5>
	            <dl class="quickSelect_brand <#if lists?size!=0>quickSelect</#if> <#if lists?size==0>quick_soon</#if>">
	                <dt>${key}</dt>
	                <dd>
	                	<#if lists?exists>
	                		<ul class="clearfix">
	                			<#list lists as cstyle>
	                				<li><a  pl="pg=cityIndex&pl=left" href="http://${city.py}.tuanche.com/b${cstyle.id?c}/tuan/" target="_blank">${cstyle.name}</a></li>
	                			</#list>
	                			<li>
	                				<#if lists?size==0>
	                					开团筹备中，敬请期待！
	      	   						</#if>
	                			</li>
	                    	</ul>
	                	</#if>
	                </dd>
	                <span></span>
	            </dl>
	      	    </#if>
	    	</#list>
	      </#if>
	      <!-- 热门搜索 -->
	      <dl class="quickSelect quickSelect_last quick_none">
                <dd>
                    <ul class="clearfix">	
                    	<#if hotSearch?exists>
		            	<#list hotSearch as search>
		            		<li><a pl="pg=cityIndex&pl=left" href="http://${city.py}.tuanche.com/c${search.id?c}/tuan/" target="_blank">${search.name}</a></li>
		            	</#list>
	            		</#if>							
                    </ul>
                </dd>
            </dl>
        </div>
        <div id="feature_brand" class="feature_brand">
		    <#if series_brand?exists && brand_style?exists>
			  <#list series_brand?keys as key>
				<#assign lists=series_brand[key]![]>
					<#if key_index < 5>
			        	<div class="feature_brand_div feature_brand_hide clearfix">    
			        		<#list lists as cstyle>
			        			 <#if cstyle?exists>
				        			<dl>
							            <dt>
							                <span class="lightblue">
							                	<a pl="pg=cityIndex&pl=leftIn" href="http://${city.py}.tuanche.com/b${cstyle.id?c}/tuan/" target="_blank">${cstyle.name}</a>
							                </span>
							            </dt>
							            <dd class="clearfix">
							               <#if brand_style[cstyle.id+'_']?exists>
								               <#assign styleList=brand_style[cstyle.id+'_']>
										       <#list styleList as sty>
										        	 <a pl="pg=cityIndex&pl=leftIn" href="http://${city.py}.tuanche.com/c${sty.id?c}/tuan/" target="_blank">${sty.name}</a>
										       </#list>
									       </#if>
							            </dd>
							        </dl>
						         </#if>
			    			</#list>
			        	</div>
			  	    </#if>
				</#list>
			</#if>
		</div>
</div>




























