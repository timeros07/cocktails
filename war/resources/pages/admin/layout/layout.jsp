<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" >
	<tiles:getAsString name='title' />
</c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/resources/css/common/stylesAll.css" rel="stylesheet" type="text/css" />
    <link href="/resources/css/common/cssLayout.css" rel="stylesheet" type="text/css" />
	<title><fmt:message key="${title}"/></title>
	
	<link rel="stylesheet" href="/resources/css/ui-lightness/jquery-ui-1.9.2.custom.css">
	<link rel="stylesheet" href="/resources/scripts/jquery-ui-plugins/css/jquery-ui-plugins-0.0.16.css" type="text/css"/>
	<link rel="stylesheet" href="/resources/scripts/jquery-loadmask/jquery.loadmask.css" type="text/css"/>
	<script src="/resources/scripts/jquery-1.8.3.js"></script>
	<script src="/resources/scripts/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-0.0.16.js"></script> 

	<!-- Load mask files files-->
	<link rel="stylesheet" href="/resources/scripts/jquery-loadmask/jquery.loadmask.css" type="text/css"/>
	<script type="text/javascript" src="/resources/scripts/jquery-loadmask/jquery.loadmask.min.js"></script>  

	<!-- Bootstrap files-->
	<link rel="stylesheet" href="/resources/themes/bootstrap/css/bootstrap.css" type="text/css"/>
	<script type="text/javascript" src="/resources/themes/bootstrap/js/bootstrap.js"></script>
	
	
</head>
<body>
	<script>
		function showSuccessMsg(message){
			
			$( "#successMessageBox").html('<div class="alert alert-success alert-dismissable">'
										+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'
										+ '<span>' + message + '</span></div>');
			$( "#successMessageBox" ).slideDown('slow',function(){
				setTimeout(function() {	$( "#successMessageBox" ).slideUp('slow', function(){$('body').unmask();}); }, 1000 );}
			);
		}
		
		function showErrorMsg(message){
			console.log(message);
			$( "#errorMessageBox").html('<div class="alert alert-danger alert-dismissable">'
										+ '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'
											+ '<strong>Błąd</strong></br>'
											+ '<span>' + message + '</span></div>');
			$( "#errorMessageBox" ).slideDown('slow');
		}
	</script>
	<div class="container">
		<div id="top">
			<tiles:insertAttribute name="header" />
		</div>
		
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		
		<div id="body" class="center_content">
		
			<div id="successMessageBox"></div>
			<div id="errorMessageBox" style="display:none"></div>
		
			<div><h2 class="title"><fmt:message key="${title}"/></h2></div>
			<tiles:insertAttribute name="body" />
		</div>

		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>
