<!-- If you want to put more then one tag on the page you must provide id attribute -->

<%@ attribute name="url" required="false" rtexprvalue="true" %>
<%@ attribute name="job" required="false" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="width" required="false" rtexprvalue="true" %>
<%@ attribute name="value" required="false" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="id" value="${empty pageScope.id ? 'star' : pageScope.id}"/>
<div id="container_${pageScope.id}" class="form-group">
	<label class="col-sm-5 control-label">
		<fmt:message key="${pageScope.label}"/>&nbsp
	</label>
	<label style="float: right;margin-right: 100px;" id="${pageScope.id}_hint"/>
	</label>
	<div style="display:inline" id="${pageScope.id}"></div>
</div>

<script>

$( document ).ready(function() {
	$('#${pageScope.id}').raty({
	    cancelOff     : 'resources/themes/star-rating/images/cancel-off.png',
	    cancelOn      : 'resources/themes/star-rating/images/cancel-on.png',
	    starHalf      : 'resources/themes/star-rating/images/star-half.png',
	    starOff       : 'resources/themes/star-rating/images/star-off.png',
	    starOn        : 'resources/themes/star-rating/images/star-on.png',
	    hints         : [
	                     '<fmt:message key="labels.cocktail.rank.level1"/>',
	                     '<fmt:message key="labels.cocktail.rank.level2"/>',
	                     '<fmt:message key="labels.cocktail.rank.level3"/>',
	                     '<fmt:message key="labels.cocktail.rank.level4"/>',
	                     '<fmt:message key="labels.cocktail.rank.level5"/>'],
	    cancel : true,
	    cancelHint: '<fmt:message key="labels.cocktail.rank.cancel" />',
		half   : false,
		size   : 44,
		target : '#${pageScope.id}_hint',
		targetKeep : true,
		<c:if test="${not empty pageScope.value}">
			score: '${pageScope.value}',
		</c:if>
		readOnly: '${pageScope.disabled}',
		click: function(score, evt) {
			$('#star').mask();
			jQuery.ajax({
					type: 'POST',
					url: '${pageScope.url}',
					data: {
						'job' : '${pageScope.job}',
						'rate' : score,
						'userId': '${UserContext.user.id}'
					},
					success: function(res){
						 $('#${pageScope.id}').unmask();
					},
					failure: function(){
						$('#${pageScope.id}').unmask();
					}
					
				});
			  }
		});
});

	
</script>
