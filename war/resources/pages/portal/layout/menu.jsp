<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<script type="text/javascript">
	jQuery(document).ready(function($){
    // Get current url
    // Select an a element that has the matching href and apply a class of 'active'. Also prepend a - to the content of the link
    var url = window.location.href;
	url = url.substr(url.lastIndexOf('/')+1);
	if($('li[name="'+url+'"]').length == 0){
		$('li[name="home"]').addClass('active');
	}else{
		$('li[name="'+url+'"]').addClass('active');
	}
});
</script>

<nav class="navbar navbar-default" role="navigation">
	<ul class="nav nav-tabs nav-pills">
		<li name="home"><a href="/home"><fmt:message key="labels.menu.portal.link.home"/></a></li>
		<li name="cocktails"><a href="/cocktails"><fmt:message key="labels.menu.portal.link.cocktails"/></a></li>
		<li name="ingredients"><a href="#"><fmt:message key="labels.menu.portal.link.ingredients"/></a></li>
		<c:choose>
			<c:when test="${empty UserContext.user}">
				<li class="navbar-right">
					<button onclick="window.location='${UserContext.loginUrl}'" type="button" class="btn btn-default navbar-btn">
						<img src="/resources/images/icons/google-blue-icon.png" width="25px"/>
						<fmt:message key="labels.header.portal.login"/>
					</button>
				</li>
			</c:when>	
			<c:otherwise>
				<li class="navbar-right">
					<button onclick="window.location='${UserContext.logoutUrl}'" type="button" class="btn btn-default navbar-btn">
						<fmt:message key="labels.header.portal.logout"/>
					</button>
				</li>
				<p class="navbar-text navbar-right">Zalogowany jako ${UserContext.user.email}</p>
			</c:otherwise>
		</c:choose>
		
	</ul>
</nav>