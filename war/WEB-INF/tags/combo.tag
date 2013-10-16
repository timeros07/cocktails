<%@ attribute name="property" required="true" rtexprvalue="true" %>
<%@ attribute name="collection" required="true" type="java.util.HashMap" rtexprvalue="true" %>
<%@ attribute name="required" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="hidden" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="width" required="false" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="/resources/css/ui-lightness/jquery-ui-1.9.2.custom.css">
<link rel="stylesheet" href="/resources/scripts/jquery-ui-plugins/css/jquery-ui-plugins-combobox.css">
<script src="/resources/scripts/jquery-1.8.3.js"></script>
<script src="/resources/scripts/jquery-ui-1.9.2.custom.js"></script>
<script src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-combobox.js"></script>

<c:set var="_width" value="width:${width-34}px !important;"/>
<style type="text/css">

  input.ui-combobox-input{
  	${_width} !important;
  }
</style>

<c:set var="id" value="${empty pageScope.id ? fn:replace(pageScope.property, '.', '_') : pageScope.id}"/>

<c:if test="${!pageScope.hidden}">
	<label for="${pageScope.property}"><fmt:message key="${pageScope.label}"/></label>
</c:if>
<sf:select
 	path="${pageScope.property}"
 	items="${pageScope.collection}"
  	disabled="${disabled}"
	id="${id}"
  	cssErrorClass="errorCombo"
  	cssStyle="
  			${ pageScope.hidden ? 'display:none;' : 'display:inline;'}"
/>
<script>
$(function() {				
	$('#${id}').combobox();	
});
</script>
