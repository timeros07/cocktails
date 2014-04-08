<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<!-- editable grid-->
<link href="/resources/themes/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
<script src="/resources/themes/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script type="text/javascript" src="/resources/themes/bootstrap3-editable/editable.js"></script>

<table class="table table-striped table-bordered">
  <thead>
    <tr>
		<th width="200px"><fmt:message key="labels.user.login"/></th>
		<th width="200px"><fmt:message key="labels.user.email"/></th>
		<th width="200px"><fmt:message key="labels.user.firstLoginDate"/></th>
		<th width="200px"><fmt:message key="labels.user.status"/></th>
	</tr>
  </thead>
  <tbody>
	<c:forEach items="${users}" var="user" varStatus="loop">
	<tr height="40px" class="editableGrid" >
		<td><c:out value="${user.login}"/></td>
		<td><c:out value="${user.email}"/></td>
		<td><fmt:formatDate type="date" value="${user.firstLoginDate}" /></td>
		<td>
			<a data-source="[{value: 'A', text: 'Aktywny'}, {value: 'I', text: 'Nieaktywny'}]" data-name="status" data-url="/admin/users?job=MODIFY" title="<fmt:message key="labels.status"/>" data-pk="${user.id}" data-type="select" href="#" data-value="<c:out value="${user.status}"/>"></a>
		</td>
	</tr>
	</c:forEach>
  </tbody>
</table>
	