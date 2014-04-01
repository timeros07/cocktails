<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>

<link href="/resources/themes/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
<script src="/resources/themes/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script>
$(document).ready(function () {
	$('#ingredientCategoriesTable .editableText').editable({
	    type: 'text',
	    name: 'name',
	    url: '/admin/ingredientCategories?job=MODIFY',
	    emptytext: '<fmt:message key="labels.empty"/>',
	    success: function(res, newValue) {
	    	if(!res.success){
	    		console.log('zle');
		    	var message = '';
		    	if(res.messages != undefined){
					for(i in res.messages){
						message = message + res.messages[i].message;
					}
				}else{
					message = "<fmt:message key='error.unexpected'/>";
				}
		    	return message;
	    	}
	    }
	});
	$('#ingredientCategoriesTable .editableTextarea').editable({
	    type: 'textarea',
	    name: 'description',
	    url: '/admin/ingredientCategories?job=MODIFY',
	    emptytext: '<fmt:message key="labels.empty"/>'
	});

	$('#ingredientCategoriesTable .editableSelect').editable({
	    type: 'select',
	    name: 'status',
	    url: '/admin/ingredientCategories?job=MODIFY',
	    emptytext: '<fmt:message key="labels.empty"/>',
        source: [
              {value: 'A', text: 'Aktywny'},
              {value: 'I', text: 'Nieaktywny'}
           ]
	});
});
</script>
<div class="pageActions">
	<button class="btn btn-primary btn-sm" type="button" onclick="window.location='ingredientCategoryCreate'" ><span class="glyphicon glyphicon-plus"></span>&nbsp;<fmt:message key='buttons.action.create'/></button>
</div>
<div class="panel panel-default">
	<div class="panel-heading"><fmt:message key="title.ingredientCategories.list"/></div>
	<table id="ingredientCategoriesTable" class="table table-striped table-bordered">
	  <thead>
	    <tr>
			<th width="200px"><fmt:message key="labels.name"/></th>
			<th width="400px"><fmt:message key="labels.description"/></th>
			<th width="200px"><fmt:message key="labels.status"/></th>
		</tr>
	  </thead>
	  <tbody>
		<c:forEach items="${categories}" var="category" varStatus="loop">
		<tr height="50px" >
			<td>
				<a class="editableText" data-title="<fmt:message key="labels.name"/>" data-pk="${category.id}" href="#"><c:out value="${category.name}"/></a>
			</td>
			<td>
				<a class="editableTextarea" style="width: 400px;" data-title="<fmt:message key="labels.description"/>" data-type="textarea" data-pk="${category.id}" href="#"><c:out value="${category.description}"/></a>
			</td>
			<td>
				<a class="editableSelect" title="<fmt:message key="labels.status"/>" data-pk="${category.id}" data-type="select" href="#" data-value="<c:out value="${category.status}"/>"></a>
				<a href="#" onclick="alert('usuniete');"><span class="glyphicon glyphicon-remove" /></a>
			</td>
		</tr>
		</c:forEach>
	  </tbody>
	</table>
</div>

	
