<%@ attribute name="url" required="false" rtexprvalue="true" %>
<%@ attribute name="job" required="false" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="width" required="false" rtexprvalue="true" %>
<%@ attribute name="property" required="false" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="container_${property}" class="form-group">
	<label class="col-sm-5 control-label"><fmt:message key="${pageScope.label}"/>&nbsp</label><label style="float: right;margin-right: 100px;" id="hint"></label>
	<div style="display:inline" id="star"></div>
</div>

<script>

$( document ).ready(function() {
	$('#star').raty({
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
		target : '#hint',
		 targetKeep : true,
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
						 $('#star').unmask();
					},
					failure: function(){
						$('#star').unmask();
					}
					
				});
			  }
		});
});

	
</script>
