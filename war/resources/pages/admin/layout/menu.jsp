<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<nav class="navbar navbar-default" role="navigation">
	<ul class="nav nav-tabs nav-stacked nav-pills">
		<li name="cocktails"><a href="/admin/cocktails"><fmt:message key="labels.menu.admin.link.cocktails"/></a></li>
		<li name="ingredients"><a href="/admin/ingredients"><fmt:message key="labels.menu.admin.link.ingredients"/></a></li>
		<li name="users"><a href="/admin/users"><fmt:message key="labels.menu.admin.link.users"/></a></li>
		<li name="applicationInfo"><a href="/admin/applicationInfo"><fmt:message key="labels.menu.admin.link.applicationInfo"/></a></li>
		<c:choose>
			<c:when test="${empty UserContext.user}">
				<li><button onclick="window.location='${UserContext.loginUrl}'" type="button" class="btn btn-default navbar-btn"><fmt:message key="labels.login"/></button></li>
			</c:when>	
			<c:otherwise>
				<li class="navbar-right">
					<button onclick="window.location='${UserContext.logoutUrl}'" type="button" class="btn btn-default navbar-btn">
						<fmt:message key="labels.logout"/>
					</button>
				</li>
				<p class="navbar-text navbar-right"><fmt:message key="labels.loggedAs"/> ${UserContext.user.email}</p>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>