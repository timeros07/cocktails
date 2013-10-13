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
<c:set var="_tooltip">
	<fmt:message key="${label}"/>
</c:set>
<c:if test="${!hidden}">
	<label for="${property}"><fmt:message key="${label}"/></label>
</c:if>
<sf:input
 	path="${property}"
  	disabled="${disabled}"
  	id="_${property}"
  	cssErrorClass="errorText"
  	maxLength="${maxLength}"
  	cssStyle="${!empty pageScope.width ? _width : 'width:auto;'}
  			${ hidden ? 'display:none' : 'display:inline;'}"
  	title="${_tooltip}"
  	onKeyDown="onKeyDown_${property}()"
/>
<script>
$(function() {
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
	 
	 
  });
  
function onKeyDown_${property}(){
	if($('#_${property}').attr('class') == 'errorText'){
		$('#_${property}').removeClass('errorText');
	} 
}
</script>
