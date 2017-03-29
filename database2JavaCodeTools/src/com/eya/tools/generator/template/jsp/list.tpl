<%@page  contentType="text/html;charset=UTF-8"  isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="../../common/head.jsp" %> 
	<div class="pageHeader">
		<form id="pagerForm" action="${'${'}ctx${'}'}admin/${folderName}/list.do" method="post" onsubmit="return navTabSearch(this)";>
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						</td>
						<td>
							<div class="buttonActive"><div class="buttonContent"><button type="submit" >检索</button></div></div>
						</td>
					</tr>
				</table>
				 
			</div>
			<div class="hidden">
				<input type="hidden" name="pageNum" id="pageNum" value="<#noparse>${</#noparse>pageData.pageNum<#noparse>}</#noparse>" />
				<input type="hidden" name="numPerPage" id="numPerPage" value="<#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>" />
				<input type="hidden" name="orderField" value="<#noparse>${</#noparse>pageData.orderField<#noparse>}</#noparse>" />
				<input type="hidden" name="orderDirection" value="<#noparse>${</#noparse>pageData.orderDirection<#noparse>}</#noparse>" />
			</div>
		</form>
	</div>
	
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${'${'}ctx${'}'}admin/${folderName}/new.do" target="dialog" mask="true" rel="new_${folderName}_rel"><span>添加</span></a></li>
				<li><a class="edit" href="${'${'}ctx${'}'}admin/${folderName}/new.do?id=${'{'}id_${folderName}${'}'}" target="dialog" warn="请选择一个数据"><span>修改</span></a></li>
				<li><a class="delete" href="${'${'}ctx${'}'}admin/${folderName}/delete.do?id=${'{'}id_${folderName}${'}'}" target="selectedTodo" postType="string" rel="pks" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
				<li class="line">line</li>
				<!--
				<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
				-->
			</ul>
		</div>
		<table class="table" width="100%" layoutH="112" asc="asc" desc="desc">
			<thead>
				<tr>
					<th width="30"  style="text-align: center;"><input type="checkbox" group="pks" class="checkboxCtrl"/></th>
					<th width="40"  style="text-align: center;">序号</th>
					<#list records as rec>
					<y:th headerField="${rec.fieldName}" headerText="${rec.comment}" orderDirection="<#noparse>${</#noparse>pageData.orderDirection<#noparse>}</#noparse>" orderField="<#noparse>${</#noparse>pageData.orderField<#noparse>}</#noparse>"/>	
				    </#list> 
				    <th>操作</th>
				</tr>
			</thead>
			<tbody>
			 
			<c:forEach var="entity" items="${'${'}pageData.result${'}'}"  varStatus="status">
			 
				<tr target="id_${folderName}" rel="${'${'}entity.${pkRecords[0].property}${'}'}">
					<td  style="text-align: center;"><input name="pks" value="${'${'}entity.${pkRecords[0].property}${'}'}" type="checkbox"/></td>
					<td  style="text-align: center;">${'${'}status.index+1${'}'}</td>
					<#list records as rec>
					<td>${'${'}entity.${rec.property}${'}'}</td>
				    </#list>
				    <td>
						<a title="确实要删除这条记录吗?" target="ajaxTodo" href="${'${'}ctx${'}'}admin/${folderName}/delete.do?pk=${'${'}entity.${pkRecords[0].property}${'}'}" class="btnDel">删除</a>
						<a title="编辑表单信息" target="dialog" href="${'${'}ctx${'}'}admin/${folderName}/new.do?id=${'${'}entity.${pkRecords[0].property}${'}'}" class="btnEdit">编辑</a>
					</td>
				</tr>
            </c:forEach>	
            </tbody>			
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>每页显示</span>
				<span><#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>条，共<#noparse>${</#noparse> pageData.totalCount<#noparse>}</#noparse>条，每页<#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>条</span>
			</div>
			<div class="pagination" targetType="navTab" totalCount="<#noparse>${</#noparse>pageData.totalCount <#noparse>}</#noparse>" numPerPage="<#noparse>${</#noparse>pageData.numPerPage<#noparse>}</#noparse>" pageNumShown="10" currentPage="<#noparse>${</#noparse>pageData.pageNum <#noparse>}</#noparse>"></div>
		</div>
    </div>
 