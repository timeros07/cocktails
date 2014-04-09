<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<nav class="navbar navbar-default" role="navigation">
	<ul class="nav nav-tabs nav-pills">
		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="title.cocktails"/><b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="/admin/cocktails"><span class="glyphicon glyphicon-list"></span>&nbsp;<fmt:message key="title.cocktails.list"/></a></li>
            <li><a href="/admin/cocktailCreate"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;<fmt:message key="title.cocktail.add"/></a></li>
            <li class="divider"></li>
            <li><a href="/admin/cocktailCategories"><span class="glyphicon glyphicon-list"></span>&nbsp;<fmt:message key="title.cocktailCategories.list"/></a></li>
          </ul>
        </li>
		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="title.ingredients"/><b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="/admin/ingredients"><span class="glyphicon glyphicon-list"></span>&nbsp;<fmt:message key="title.ingredients.list"/></a></li>
            <li><a href="/admin/ingredientCreate"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;<fmt:message key="title.ingredient.add"/></a></li>
            <li class="divider"></li>
            <li><a href="/admin/ingredientCategories"><span class="glyphicon glyphicon-list"></span>&nbsp;<fmt:message key="title.ingredientCategories.list"/></a></li>
          </ul>
        </li>
		<li name="users"><a href="/admin/users"><fmt:message key="labels.menu.admin.link.users"/></a></li>
		<li name="contactMessages"><a href="/admin/contactMessages"><fmt:message key="title.contactMessages.list"/></a></li>
		<li name="applicationInfo"><a href="/admin/applicationInfo"><fmt:message key="labels.menu.admin.link.applicationInfo"/></a></li>
		<c:choose>
			<c:when test="${empty UserContext.user}">
				<li><button onclick="window.location='${UserContext.loginUrl}'" type="button" class="btn btn-default navbar-btn"><fmt:message key="labels.login"/></button></li>
			</c:when>	
			<c:otherwise>
				<li class="dropdown navbar-right">
			    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">${UserContext.user.email}<b class="caret"></b></a>
			    	<ul class="dropdown-menu">
			    		<c:if test="${UserContext.isAdmin}" >
			    			<li><a href="/"><span class="glyphicon glyphicon-globe"></span>&nbsp;<fmt:message key="title.switchToPortal"/></a></li>
			    		</c:if>
			    	
			     		<li><a onclick="window.location='${UserContext.logoutUrl}'" href="#"><span class="glyphicon glyphicon-log-out"></span>&nbsp;<fmt:message key="labels.logout"/></a></li>
			        </ul>
		    	</li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>