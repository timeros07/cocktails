<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<!-- editable grid-->
<link href="/resources/themes/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
<script src="/resources/themes/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script type="text/javascript" src="/resources/themes/bootstrap3-editable/editable.js"></script>

<div class="panel panel-default">
	<div class="panel-heading"><fmt:message key="title.cocktailCategories.list"/></div>
	<table id="cocktailCategoriesTable" class="table table-striped table-bordered">
	  <thead>
	    <tr>
			<th width="200px"><fmt:message key="labels.name"/></th>
			<th width="400px"><fmt:message key="labels.description"/></th>
			<th width="200px"><fmt:message key="labels.status"/></th>
			<th width="50px">#</th>
		</tr>
	  </thead>
	  <tbody>
		<c:forEach items="${categories}" var="cat" varStatus="loop">
		<tr height="50px" class="editableGrid" >
			<td>
				<a data-name="name" data-url="/admin/cocktailCategories?job=MODIFY" data-type="text" data-title="<fmt:message key="labels.name"/>" data-pk="${cat.id}" href="#"><c:out value="${cat.name}"/></a>
			</td>
			<td>
				<a data-name="description" data-url="/admin/cocktailCategories?job=MODIFY" data-title="<fmt:message key="labels.description"/>" data-type="textarea" data-pk="${cat.id}" href="#"><c:out value="${cat.description}"/></a>
			</td>
			<td>
				<a data-source="[{value: 'A', text: 'Aktywny'}, {value: 'I', text: 'Nieaktywny'}]" data-name="status" data-url="/admin/cocktailCategories?job=MODIFY" title="<fmt:message key="labels.status"/>" data-pk="${cat.id}" data-type="select" href="#" data-value="<c:out value="${cat.status}"/>"></a>
			</td>
			<td>
				<tags:remove-handler id="${cat.id}" params="id: ${cat.id}" url="cocktailCategories" label="buttons.action.remove" />
			</td>
		</tr>
		</c:forEach>
		<tr height="50px" class="editableFooter">
			<td>
				<a data-name="name" data-url="#" data-type="text" data-title="<fmt:message key="labels.name"/>"  href="#"><c:out value="${newCategory.name}"/></a>
			</td>
			<td>
				<a data-name="description" data-url="#" data-title="<fmt:message key="labels.description"/>" data-type="textarea"  href="#"><c:out value="${newCategory.description}"/></a>
			</td>
			<td>
				<a data-source="[{value: 'A', text: 'Aktywny'}, {value: 'I', text: 'Nieaktywny'}]" data-name="status" data-url="#" title="<fmt:message key="labels.status"/>" data-type="select" href="#" data-value="<c:out value="${newCategory.status}"/>"></a>
			</td>
			<td>
				<button class="btn btn-primary btn-sm" type="button" onclick="addItemToGrid('/admin/cocktailCategories?job=CREATE');">
					<fmt:message key='buttons.action.add'/>
				</button>
			</td>
		</tr>
	 	</tbody>
	</table>
</div>

	
