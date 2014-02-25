<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>

<sf:form id="formularz" method="POST" modelAttribute="cocktailData" >
		<div class="col-md-8 row">
			<div class="panel panel-default">
				<div class="panel-heading"><fmt:message key="labels.generalInformation"/></div>
				<div class="panel-body form-horizontal">
					<c:if test="${!empty UserContext.user}">
						<tags:rating label="labels.cocktail.rating" url="cocktailDetails" job="RATE"></tags:rating>	
					</c:if>
			  		<tags:text property="name" maxLength="50" disabled="true" label="labels.name"/>
			  		<tags:textarea label="labels.description" property="description" maxLength="200" disabled="true"/>
			  		<tags:select width="200" label="labels.status" disabled="true" property="status" items="${statuses}"/>
				</div>
			</div>
		</div>
		<tags:upload-panel form="formularz" label="labels.ingredient.image" successPath="/admin/ingredientUpload" property="blobKey" propertyValue="${cocktailData.blobKey}" disabled="true"/>
	</sf:form>

<div class="col-md-10 row">
	<div class="panel panel-default">
	<div class="panel-heading">Składniki</div>
		<table class="table">
			<thead>
				<tr>
					<th><fmt:message key="labels.name"/></th>
					<th><fmt:message key="labels.description"/></th>
					<th><fmt:message key="labels.cocktail.ingredient.count"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ingredient" items="${cocktailData.ingredients}">
				<tr>
					<td>${ingredient.element.name}</td>
					<td>${ingredient.element.description}</td>
					<td>${ingredient.count}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

