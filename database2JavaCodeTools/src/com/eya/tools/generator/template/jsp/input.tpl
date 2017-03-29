<%@page  contentType="text/html;charset=UTF-8"  isELIgnored="false" pageEncoding="UTF-8" %>
<%@include file="../../common/head.jsp" %>
 
 <style>
<!--
.input_table{
	width: 100%;
}
.input_table tr{
	line-height: 35px;
	height: 35px;
}
.list_ua{
	list-style: none;
}
.list_ua li{
	float: left;
	padding-left: 5px;
}
-->
</style>
  <div class="pageContent">
	
	<form method="post" action="${'${'}ctx${'}'}admin/${folderName}/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<input type="hidden" name="opera" value="${"${"}opera${"}"}" />
		<input type="hidden" name="${pkRecords[0].property}" value="${"${"}${classObjectName}.${pkRecords[0].property}${"}"}" />
		<div class="pageFormContent" layoutH="59">
		<table class="input_table">
		<#list records as rec>
			<tr>
				<td>${rec.comment}：</td>
				<td>
					<input  type="text" name="${rec.property}" class="required"  id="${rec.fieldName }_id" value="${'${'}${classObjectName}.${rec.property }${'}'}"/>
				</td>
			</tr>
		</#list> 
		 </table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >提交 </button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">  取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
