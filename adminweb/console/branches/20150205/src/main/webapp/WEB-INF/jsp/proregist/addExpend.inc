<style type="text/css">
	.a-form .error{float:none; padding-left:0;}
</style>
<div id="addExpend" style="position:fixed;top:20%;left:0;width:100%;display: none">
	<form action="/decorate/addDecPlate" method="post" style="padding:0 10px 0 10px; margin-top:0px" id="addPlateForm">
		<div class="b-con a-form" style="width: 600px;height:200px;overflow:scroll; background-color:  #f0f6f0;border: 1px dashed;margin: auto;z-index: 100">
			<div>
			<div align="center">
				<input type="hidden" id="idDiv" name="tempid" value="${decorateTemp.id }"/>
				<input type="hidden" id="baseId" name="baseId" value="${decorateTemp.baseId }"/>
				<div style="width: 45%">
					<label>版块名称:<select id="plateIdDiv" name="plateId" class="{required:true}">
						<option value="">请选择</option>
						<c:forEach items="${plateList }" var="plateList">
							<option value="${plateList.code }" ${decorateTemp.plateId==plateList.code?'selected':'' }>${plateList.name }</option>
						</c:forEach>
						</select>
						<span class="help-inline"></span>
					</label>
				</div>
			</div>
          	<div id="div_num1" style="width:450px;margin:0 auto" >
          		<div style="width:49%;display:inline-block;">
	          		<label>小标题名:<input id="titleDiv" type="text" style="width: 150px"  name="title" value="${decorateTemp.title }" />
	          		</label>
          		</div>
          		<div style="width:49%;display:inline-block;">
          			<label>标题样式:<select id="showStyleDiv" name="titleShowStyle">
						<option value="">请选择</option>
						<option value="1" style="color: #383838;font-size: 16px">样式一</option>
						<option value="2" style="color: #2457a6;font-size: 16px">样式二</option>
					</select>
					</label>
          		</div>
          		<div style="width:49%;display:inline-block;">
          			<label>内容样式:<select id="contentStyleDiv" name="titleContentStyle">
						<option value="">请选择</option>
						<option value="1">文字</option>
						<option value="2">图片</option>
						<option value="3">图文</option>
					</select>
					</label>
          		</div>
          		<div style="width:36%;display:inline-block;">
          			<label>排&#12288;&#12288;序:<input id="sortDiv" class="{required:true}" type="text" style="width: 20px;" name="sort" value="${decorateTemp.sort }" />
          			<span class="help-inline" style="color:red;">*</span>
          			</label>
          		</div>
          		<input id="addordel_num1" type="button" value="添加" class="btn btn-info" onclick="addExpHang();"/>
          	</div>
          	</div>
			<div align="center">
				<div class="pd5">
					<input type="button" value="保存" class="btn btn-info" onclick="savePlate();" />
	          		<input type="button" value="取消" class="btn btn-info" onclick="hideDiv();"/>
	          	</div>
		    </div>
		</div>
	</form>
</div>