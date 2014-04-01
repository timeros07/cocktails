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


<c:set var="_width" value="width:${pageScope.width}px;"/>
<div id="container_${property}" class="form-group">
	<c:if test="${!pageScope.hidden}">
		<label for="${pageScope.property}" class="col-sm-4 control-label"><fmt:message key="${pageScope.label}"/></label>
	</c:if>
	<div class="col-sm-8">
		<sf:textarea
		 	path="${pageScope.property}"
		  	disabled="${pageScope.disabled}"
		  	id="_${pageScope.property}"
		  	maxLength="${pageScope.maxLength}"
		  	cssStyle="${!empty pageScope.width ? _width : ''}
		  			${ pageScope.hidden ? 'display:none;' : ''}
		  			resize:none;"
		  	rows="5"
			onKeyDown="onKeyDown_${pageScope.id}()"
			cssClass="form-control" 
		/>
	</div>
</div>
<script>
function onKeyDown_${pageScope.id}(){
	var container = $(('#container_${property}').replace('.', '\\.'));
	if(container.hasClass('has-error')){
		container.removeClass('has-error');
	} 
}
</script>
