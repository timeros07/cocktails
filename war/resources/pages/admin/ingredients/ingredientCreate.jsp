<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<c:set var="mode"><tiles:insertAttribute name="mode" /></c:set>
<c:set var="detailsMode" value="${mode == 'D'}"/>
<c:set var="modifyMode" value="${mode == 'M'}"/>
<c:set var="createMode" value="${mode == 'C'}"/>

<div>
	<sf:form id="formularz" method="POST" modelAttribute="elementData" >
		<table>
			<tr>
				<td>
					<tags:text width="250" property="name" maxLength="50" disabled="${detailsMode}" label="labels.ingredient.name"/>
				</td>
			</tr>
			<tr>
				<td>
					<tags:textarea width="250" property="description" maxLength="150" disabled="${detailsMode}" label="labels.ingredient.description"/>
				</td>
			</tr>
			<sf:hidden name="blobKey" id="_blobKey" path="blobKey"/>
		</table>
	</sf:form>
		<table>
			<c:if test="${createMode or modifyMode}">
				<tags:upload-panel label="labels.ingredient.image" uploadUrl="${uploadUrl}" id="uploadImg" />
			</c:if>
			<c:if test="${detailsMode}">
				<c:if test="${not empty elementData.blobKey}">
				<tr>
					<span><fmt:message key="labels.ingredient.image"/></span>
					<td>
						<img src="/serve?blob-key=${elementData.blobKey}" style="margin-left: 20px;max-height: 150px;max-width:250px;"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${empty elementData.blobKey}">
					<tr>
						<td><span><fmt:message key="labels.ingredient.image"/></span>
						<span><fmt:message key="labels.ingreient.image.noImage"/></span></td>
					</tr>
				</c:if>
			</c:if>
		
			<tr class="buttons">
			<c:if test="${createMode}">
				<td>
					<tags:submit-handler  url="ingredientCreate" label="buttons.action.create" job="CREATE" form="formularz" />
					<input class="submitButton" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredients'"/>
				</td>
			</c:if>
			<c:if test="${detailsMode}">
				<td>
					<input class="submitButton" type="button" onclick="window.location='ingredientModify?id=${elementData.id}'" value="<fmt:message key='buttons.action.modify'/>"/>
					<tags:simple-handler questionTitle="confirm.remove.title" question="question.remove" params="id: ${elementData.id}" url="ingredientDetails" label="buttons.action.remove" job="REMOVE" />
				</td>
			</c:if>
			<c:if test="${modifyMode}">
				<td>
					<tags:submit-handler  url="ingredientModify" label="buttons.action.save" job="SAVE" form="formularz" />
					<input class="submitButton" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredients'"/>
				</td>
			</c:if>
			</tr>
		</table>


	
</div>