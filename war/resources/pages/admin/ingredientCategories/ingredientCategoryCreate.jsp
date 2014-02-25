<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<c:set var="mode"><tiles:insertAttribute name="mode" /></c:set>
<c:set var="detailsMode" value="${mode == 'D'}"/>
<c:set var="modifyMode" value="${mode == 'M'}"/>
<c:set var="createMode" value="${mode == 'C'}"/>
<div class="row">
	<sf:form id="formularz" method="POST" modelAttribute="category" >
		<div class="col-md-8 row">
			<div class="panel panel-default">
				<div class="panel-heading"><fmt:message key="labels.generalInformation"/></div>
				<div class="panel-body form-horizontal">
	  				<tags:text property="name" maxLength="50" disabled="${detailsMode}" label="labels.name"/>
	  				<tags:textarea label="labels.description" property="description" maxLength="200" disabled="${detailsMode}"/>
	  				<tags:select width="200" label="labels.status" disabled="${detailsMode}" property="status" items="${statuses}"/>
				</div>
			</div>
		</div>
	</sf:form>
</div>
		
<div class="buttons">
	<c:if test="${createMode}">
		<tags:submit-handler  url="ingredientCategoryCreate" label="buttons.action.create" job="CREATE" form="formularz" />
		<input class="btn btn-default btn" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredientCategories'"/>
	</c:if>
	<c:if test="${detailsMode}">
		<input class="btn btn-default btn" type="button" onclick="window.location='ingredientCategoryModify?id=${category.id}'" value="<fmt:message key='buttons.action.modify'/>"/>
		<tags:remove-handler params="id: ${category.id}" url="ingredientCategoryDetails" label="buttons.action.remove" />
	</c:if>
	<c:if test="${modifyMode}">
		<tags:submit-handler  url="ingredientCategoryModify" label="buttons.action.save" job="SAVE" form="formularz" />
		<input class="btn btn-default btn" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredientCategories'"/>
	</c:if>
</div>
