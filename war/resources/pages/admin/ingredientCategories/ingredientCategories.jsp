<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>

<div class="pageActions">
	<button class="btn btn-primary btn-sm" type="button" onclick="window.location='ingredientCategoryCreate'" ><span class="glyphicon glyphicon-plus"></span>&nbsp;<fmt:message key='buttons.action.create'/></button>
</div>
<div class="panel panel-default">
	<div class="panel-heading"><fmt:message key="title.ingredientCategories.list"/></div>
	<table class="table table-striped table-bordered table-hover">
	  <thead>
	    <tr>
			<th width="200px"><fmt:message key="labels.name"/></th>
			<th width="400px"><fmt:message key="labels.description"/></th>
		</tr>
	  </thead>
	  <tbody data-link="row" class="rowlink">
		<c:forEach items="${categories}" var="category" varStatus="loop">
		<tr height="110px" >
			<td>
				<a href="ingredientCategoryDetails?id=${category.id}"><c:out value="${category.name}"/></a>
			</td>
			<td><c:out value="${category.description}"/></td>
		</tr>
		</c:forEach>
	  </tbody>
	</table>
</div>
	
