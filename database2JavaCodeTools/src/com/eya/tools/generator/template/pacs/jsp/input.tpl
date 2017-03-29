<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<div class="pageContent">
	<form method="post" action="${folderName}/<c:if test="<#noparse>${</#noparse>operation == 'add' <#noparse>}</#noparse>">save</c:if><c:if test="<#noparse>${</#noparse>operation == 'update' <#noparse>}</#noparse>">update/<#noparse>${</#noparse>${classObjectName}.${pkRecords[0].property}<#noparse>}</#noparse></c:if>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="50">
			<#list records as rec>
				<#if "${rec.fieldName}"=="${pkRecords[0].fieldName }">
				<#else>
					<p>
						<label>${rec.comment}：</label>
						<input  type="text" name="${rec.property}" class="required"  id="${rec.fieldName }_id" value="${'${'}${classObjectName}.${rec.property }${'}'}"/>
					</p>
				</#if>
			</#list> 
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
