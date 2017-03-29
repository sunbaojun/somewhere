<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);"
		action="${folderName}/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<label>
							我的客户：
						</label>
						<input type="text" name="keywords" />
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>

		<div class="hidden">
			<input type="hidden" name="pageNum" value="<#noparse>${</#noparse>pageData.pageNum<#noparse>}</#noparse>" />
			<input type="hidden" name="numPerPage" value="<#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>" />
			<input type="hidden" name="orderField" value="<#noparse>${</#noparse>pageData.orderField<#noparse>}</#noparse>" />
			<input type="hidden" name="orderDirection" value="<#noparse>${</#noparse>pageData.orderDirection<#noparse>}</#noparse>" />
		</div>
	</form>
</div>

<div class="pageContent"  >
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="<#noparse>${</#noparse>basePath<#noparse>}</#noparse>${folderName}/tonew"  target="dialog" mask="true" width="500" height="200"><span>添加</span></a>
			</li>
			<li>
				<a class="delete" href="<#noparse>${</#noparse>basePath<#noparse>}</#noparse>${folderName}/delete" rel="ids" target="selectedTodo" title="确定要删除吗？" warn="请选择至少一个需要删除的行！"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="<#noparse>${</#noparse>basePath <#noparse>}</#noparse>${folderName}/toupdate/<#noparse>{</#noparse>${classObjectName}_${pkRecords[0].property}<#noparse>}</#noparse>" target="dialog"><span>修改</span></a>
			</li>
			<li class="line">
				line
			</li>
			<li>
				<a class="icon" href="demo/common/dwz-team.xls" target="dwzExport"
					targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
			<li>
				<a class="icon" href="javascript:<#noparse>$</#noparse>.printBox('w_list_print')"><span>打印</span>
				</a>
			</li>
		</ul>
	</div>

	<div id="w_list_print">
		<table class="list" width="98%" targetType="navTab" asc="asc"
			desc="desc" layoutH="90">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="40" >
						序号
					</th>
					<#list records as rec>
						<th width="80" orderField="<#noparse>${</#noparse>pageData.orderField<#noparse>}</#noparse> class="asc">
							${rec.comment}
						</th>
					</#list> 
				</tr>
			</thead>
			<tbody>
				<c:forEach items="<#noparse>${</#noparse>pageData.result <#noparse>}</#noparse>" var="entity" varStatus="status" >
					<tr target="${classObjectName}_${pkRecords[0].property}" rel="<#noparse>${</#noparse>entity.${pkRecords[0].property} <#noparse>}</#noparse>">
						<td  style="text-align: center;"><input  name="ids"   value="<#noparse>${</#noparse>entity.${pkRecords[0].property} <#noparse>}</#noparse>" type="checkbox"/></td>
						<td>
							<#noparse>${</#noparse>status.index+1 <#noparse>}</#noparse>
						</td>
						<#list records as rec>
							<td>
								<#noparse>${</#noparse>entity.${rec.property } <#noparse>}</#noparse>
							</td>
						</#list> 
					</tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>

	<div class="panelBar">
			<div class="pages">
				<span>每页显示</span>
		<span><#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>条，共<#noparse>${</#noparse>pageData.totalCount<#noparse>}</#noparse>条，每页<#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>条</span>
			</div>
			<div class="pagination" targetType="navTab" totalCount="<#noparse>${</#noparse>pageData.totalCount <#noparse>}</#noparse>" numPerPage="<#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>" pageNumShown="10" currentPage="<#noparse>${</#noparse>pageData.pageNum <#noparse>}</#noparse>">
			</div>
	</div>

</div>

