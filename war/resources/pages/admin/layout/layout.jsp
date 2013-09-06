<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/resources/css/default.css" rel="stylesheet" type="text/css" />
    <link href="/resources/css/cssLayout.css" rel="stylesheet" type="text/css" />
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/redmond/jquery-ui.css">
	<link rel="stylesheet" href="/resources/scripts/jquery-ui-plugins/css/jquery-ui-plugins-0.0.16.css" type="text/css"/>
	<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
	<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
	<script type="text/javascript" src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-0.0.16.js"></script>  
	
</head>
<body>
	<div id="top">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div id="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	
	<div id="body">
		<tiles:insertAttribute name="body" />
	</div>

	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>

</body>
</html>