<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="../resources/css/default.css" rel="stylesheet" type="text/css" />
    <link href="../resources/css/cssLayout.css" rel="stylesheet" type="text/css" />
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
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