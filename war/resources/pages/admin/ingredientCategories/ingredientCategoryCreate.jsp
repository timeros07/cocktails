<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<div class="row">
	<sf:form id="formularz" method="POST" modelAttribute="category" >
		<div class="col-md-8 row">
			<div class="panel panel-default">
				<div class="panel-heading"><fmt:message key="labels.generalInformation"/></div>
				<div class="panel-body form-horizontal">
	  				<tags:text property="name" maxLength="50" label="labels.name"/>
	  				<tags:textarea label="labels.description" property="description" maxLength="200" />
	  				<tags:select width="200" label="labels.status" property="status" items="${statuses}"/>
				</div>
			</div>
		</div>
	</sf:form>
</div>
		
<div class="buttons">
	<tags:submit-handler  url="ingredientCategoryCreate" label="buttons.action.create" job="CREATE" form="formularz" />
	<input class="btn btn-default btn" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredientCategories'"/>
</div>
