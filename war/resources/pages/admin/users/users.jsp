<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>

<div class="pageActions">
	<button class="btn btn-primary btn-sm" type="button" onclick="window.location='userCreate'" ><fmt:message key='buttons.action.create'/></button>
</div>
<table class="table table-striped table-bordered table-hover">
  <thead>
    <tr>
		<th width="200px"><fmt:message key="labels.user.login"/></th>
		<th width="200px"><fmt:message key="labels.user.email"/></th>
		<th width="200px"><fmt:message key="labels.user.firstLoginDate"/></th>
		<th width="200px"><fmt:message key="labels.user.status"/></th>
	</tr>
  </thead>
  <tbody data-link="row" class="rowlink">
	<c:forEach items="${users}" var="user" varStatus="loop">
	<tr height="40px" >
		<td>
			<a href="userDetails?id=${user.id}"><c:out value="${user.login}"/></a>
		</td>
		<td><c:out value="${user.email}"/></td>
		<td><c:out value="${user.firstLoginDate}"/></td>
		<td><c:out value="${user.status}"/></td>
	</tr>
	</c:forEach>
  </tbody>
</table>
	