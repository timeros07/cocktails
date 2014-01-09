<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<c:set var="mode"><tiles:insertAttribute name="mode" /></c:set>
<c:set var="detailsMode" value="${mode == 'D'}"/>
<c:set var="modifyMode" value="${mode == 'M'}"/>
<c:set var="createMode" value="${mode == 'C'}"/>

<div>
	<sf:form style="width: 500px" id="formularz" method="POST" modelAttribute="elementData" >
		<table class="table table-no-border">
			<tr>
				<td>
					<tags:text width="350" property="name" maxLength="50" disabled="${detailsMode}" label="labels.ingredient.name"/>
				</td>
			</tr>
			<tr>
				<td>
					<tags:textarea width="350" property="description" maxLength="150" disabled="${detailsMode}" label="labels.ingredient.description"/>
				</td>
			</tr>
			<sf:hidden name="blobKey" id="_blobKey" path="blobKey"/>
			<c:if test="${createMode or modifyMode}">
			<tr>
				<td>
					<tags:upload-panel form="formularz" label="labels.ingredient.image" successPath="/admin/ingredientUpload" id="uploadImg" />
				</td>
			</tr>
			</c:if>
			<c:if test="${detailsMode}">
				<c:if test="${not empty elementData.blobKey}">
				<tr>
					<td>
						<label><fmt:message key="labels.ingredient.image"/></label>
						<img class="img-thumbnail" src="/serve?blob-key=${elementData.blobKey}" style="margin-left: 20px;max-height: 150px;max-width:250px;"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${empty elementData.blobKey}">
					<tr>
						<td><label><fmt:message key="labels.ingredient.image"/></label>
						<span><fmt:message key="labels.ingreient.image.noImage"/></span></td>
					</tr>
				</c:if>
			</c:if>
			</table>
		</sf:form>
		<div class="buttons">
			<c:if test="${createMode}">
				<tags:submit-handler  url="ingredientCreate" label="buttons.action.create" job="CREATE" form="formularz" />
				<input class="btn btn-default btn-sm" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredients'"/>
			</c:if>
			<c:if test="${detailsMode}">
				<input class="btn btn-default btn-sm" type="button" onclick="window.location='ingredientModify?id=${elementData.id}'" value="<fmt:message key='buttons.action.modify'/>"/>
				<tags:remove-handler params="id: ${elementData.id}" url="ingredientDetails" label="buttons.action.remove" />
			</c:if>
			<c:if test="${modifyMode}">
				<tags:submit-handler  url="ingredientModify" label="buttons.action.save" job="SAVE" form="formularz" />
				<input class="btn btn-default btn-sm" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredients'"/>
			</c:if>
		</div>

</div>