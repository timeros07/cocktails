<%@ attribute name="property" required="true" rtexprvalue="true" %>
<%@ attribute name="items" required="true" type="java.util.Map" rtexprvalue="true" %>
<%@ attribute name="required" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="width" required="false" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="id" value="${empty pageScope.id ? fn:replace(pageScope.property, '.', '_') : pageScope.id}"/>
<div id="container_${property}" class="form-group">
	<label for="${pageScope.property}" class="col-sm-4 control-label"><fmt:message key="${pageScope.label}"/></label>
	<div class="col-sm-8">
		<sf:select 
			cssClass="form-control" 
			id="_${id}"
			path="${pageScope.property}"
			disabled="${pageScope.disabled}"
			cssStyle="width: ${empty pageScope.width ? '100%' : width }px"
			onchange="onchange_${pageScope.property}()"
		>
			<option value=""></option>
			<sf:options items="${items}"/>
		</sf:select>
	</div>
</div>

<script>
function onchange_${pageScope.property}(){
	if($('#container_${property}').hasClass('has-error')){
		$('#container_${property}').removeClass('has-error');
	} 
}
</script>

