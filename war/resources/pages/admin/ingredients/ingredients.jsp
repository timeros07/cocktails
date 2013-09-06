<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>

<c:forEach var="ingredient" items="${ingredients}">
	<s:url value="/admin/ingredient/{ingredientId}" var="ingredient_url">
		<s:param name="ingredientId" value="${ingredient.id}"/>
	</s:url>
	<li>
		<a href="${ingredient_url}">
			<c:out value="${ingredient.name}"/>
			<c:out value="${ingredient.description}"/>
		</a>
	</li>
</c:forEach>