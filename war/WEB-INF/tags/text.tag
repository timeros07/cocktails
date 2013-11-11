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
<c:if test="${!pageScope.hidden}">
	<label for="${pageScope.property}"><fmt:message key="${pageScope.label}"/></label>
</c:if>
<sf:input
 	path="${pageScope.property}"
  	disabled="${pageScope.disabled}"
  	id="_${id}"
  	cssErrorClass="errorText"
  	maxLength="${pageScope.maxLength}"
  	cssStyle="${!empty pageScope.width ? _width : 'width:auto;'}
  			${pageScope.hidden ? 'display:none' : 'display:inline;'}"
  	title="${_tooltip}"
  	onKeyDown="onKeyDown_${pageScope.property}()"
/>
<script>
/*$(function() {
	 $('#_${property}').tooltip({
	      position: {
	        my: "center bottom-20",
	        at: "center top",
	        using: function( position, feedback ) {
	          $( this ).css( position );
	          $( "<div>" )
	            .addClass( "arrow" )
	            .addClass( feedback.vertical )
	            .addClass( feedback.horizontal )
	            .appendTo( this );
	        }
	      }
	    });
	 
	 
  });*/
  
function onKeyDown_${pageScope.property}(){
	if($('#_${id}').attr('class') == 'errorText'){
		$('#_${id}').removeClass('errorText');
	} 
}
</script>
