<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="property" required="true" rtexprvalue="true" %>
<%@ attribute name="required" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>

	<label for="${property}">${label}</label>
	<sf:input path="${property}"/>	

<script>
	$('${property}').textinput({
	'create': function(event, data) {
		<c:if test="${disabled}">
			$('${property}').textinput('disable');
		</c:if>
		}
	});
</script>
