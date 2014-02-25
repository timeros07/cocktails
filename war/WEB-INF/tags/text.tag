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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="_width" value="width:${pageScope.width}px;"/>
<c:set var="_tooltip">
	<fmt:message key="${pageScope.label}"/>
</c:set>
<c:set var="id" value="${empty pageScope.id ? fn:replace(pageScope.property, '.', '_') : pageScope.id}"/>
<div id="container_${property}" class="form-group">
	<c:if test="${!pageScope.hidden}">
		<label for="${pageScope.property}" class="col-sm-4 control-label"><fmt:message key="${pageScope.label}"/></label>
	</c:if>
	<div class="col-sm-8">
		<sf:input
		 	path="${pageScope.property}"
		  	disabled="${pageScope.disabled}"
		  	id="_${id}"
		  	maxLength="${pageScope.maxLength}"
		  	title="${_tooltip}"
		  	onKeyDown="onKeyDown_${pageScope.property}()"
		  	cssClass="form-control"
		  	type="text"
		/>
	</div>
</div>

<script>
  
function onKeyDown_${pageScope.property}(){
	if($('#container_${property}').hasClass('has-error')){
		$('#container_${property}').removeClass('has-error');
	} 
}
</script>
