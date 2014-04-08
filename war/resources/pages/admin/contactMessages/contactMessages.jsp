<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>

<table class="table table-striped table-bordered table-hover">
  <thead>
    <tr>
		<th width="200px"><fmt:message key="labels.contact.topic"/></th>
		<th width="200px"><fmt:message key="labels.contact.email"/></th>
	</tr>
  </thead>
  <tbody data-link="row" class="rowlink">
	<c:forEach items="${messages}" var="message" varStatus="loop">
	<tr height="40px" >
		<td>
			<a href="contactMessageDetails?id=${message.id}"><c:out value="${message.topic}"/></a>
		</td>
		<td><c:out value="${message.email}"/></td>
	</tr>
	</c:forEach>
  </tbody>
</table>
	