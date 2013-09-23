<%@ attribute name="property" required="true" rtexprvalue="true" %>
<%@ attribute name="required" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="hidden" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="minLength" required="false" rtexprvalue="true" %>
<%@ attribute name="maxLength" required="false" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<label for="${property}">${label}</label>
	<input path="${property}"/>	

<script>
	$('${property}').textinput({
	'create': function(event, data) {
		<c:if test="${disabled}">
			$('${property}').textinput('disable');
		</c:if>
		}
	});
</script>
