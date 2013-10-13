<%@ attribute name="property" required="true" rtexprvalue="true" %>
<%@ attribute name="required" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="hidden" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="width" required="false" rtexprvalue="true" %>
<%@ attribute name="maxLength" required="false" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<c:set var="_width" value="width:${width}px;"/>
<c:if test="${!hidden}">
	<label for="${property}"><fmt:message key="${label}"/></label>
</c:if>
<sf:textarea
 	path="${property}"
  	disabled="${disabled}"
  	id="_${property}"
  	cssErrorClass="errorTextArea"
  	maxLength="${maxLength}"
  	cssStyle="${!empty pageScope.width ? _width : 'width:auto;'}
  			${ hidden ? 'display:none;' : 'display:inline;'}
  			resize:none;vertical-align: middle;"
  	rows="5"
	onKeyDown="onKeyDown_${property}()"
/>
<script>
function onKeyDown_${property}(){
	if($('#_${property}').attr('class') == 'errorTextArea'){
		$('#_${property}').removeClass('errorTextArea');
	} 
}
</script>
