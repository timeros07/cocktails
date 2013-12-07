<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<style>

.menu_first{
 -webkit-border-bottom-left-radius: 20px; /* prawy górny */
 -webkit-border-top-left-radius: 20px; /* lewy dolny */
 -khtml-border-radius-topleft: 20px;
 -khtml-border-radius-bottomleft: 20px;
 -moz-border-radius-topleft: 20px;
 -moz-border-radius-bottomleft: 20px;
  border-bottom-left-radius: 20px
  border-top-left-radius: 20px;
}

.menu_last{
 -webkit-border-bottom-right-radius: 20px; /* prawy górny */
 -webkit-border-top-right-radius: 20px; /* lewy dolny */
 -khtml-border-radius-topright: 20px;
 -khtml-border-radius-bottomright: 20px;
 -moz-border-radius-topright: 20px;
 -moz-border-radius-bottomright: 20px;
  border-bottom-right-radius: 20px
  border-top-right-radius: 20px;
}

#menu ul li a{
	font-weight: lighter;
	font-size: 1.2em;
}

#menu ul a:link, ul a:visited {
	text-decoration: none;
	display: block;
	width: 80px;
	text-align: center;
	background-color: #ccc;
	color: #000;
	border: 2px outset #ccc;
	padding: 8px;
}

#menu ul a:hover {
	border-style: inset;
	background-color: #ACACAC;
	color: #fff;
}

</style>
<ul>
	<li><a class="menu_first" href="#"><fmt:message key="labels.menu.portal.link.home"/></a></li>
	<li><a href="#"><fmt:message key="labels.menu.portal.link.ingredients"/></a></li>
	<li><a href="#"><fmt:message key="labels.menu.portal.link.cocktails"/></a></li>
	<li><a class="menu_last" href="#"><fmt:message key="labels.menu.portal.link.ranking"/></a></li>
</ul>
