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
		<li name="home"><a href="/home"><fmt:message key="title.home"/></a></li>
		<li name="cocktails"><a href="/cocktails"><fmt:message key="title.cocktails"/></a></li>
		<li name="ingredients"><a href="#"><fmt:message key="title.ingredients"/></a></li>
		<li name="ranking"><a href="#"><fmt:message key="title.ranking"/></a></li>
		<c:choose>
			<c:when test="${empty UserContext.user}">
				<li class="navbar-right">
					<button onclick="window.location='${UserContext.loginUrl}'" type="button" class="btn btn-default navbar-btn">
						<img src="/resources/images/icons/google-blue-icon.png" width="25px"/>
						<fmt:message key="labels.login"/>
					</button>
				</li>
			</c:when>	
			<c:otherwise>
				<li class="dropdown navbar-right">
			    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">${UserContext.user.email}<b class="caret"></b></a>
			    	<ul class="dropdown-menu">
			     		<li><a onclick="window.location='${UserContext.logoutUrl}'" href="#"><span class="glyphicon glyphicon-log-out"></span>&nbsp;<fmt:message key="labels.logout"/></a></li>
			        </ul>
		    	</li>
			</c:otherwise>
		</c:choose>
		
	</ul>
</nav>