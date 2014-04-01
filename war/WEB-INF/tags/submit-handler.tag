<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="job" required="false" rtexprvalue="true" %>
<%@ attribute name="url" required="true" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="customSuccess" required="false" rtexprvalue="true" %>
<%@ attribute name="customFailure" required="false" rtexprvalue="true" %>
<%@ attribute name="form" required="true" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
	
	function ${pageScope.form}_handler(){
		var form = jQuery('#${pageScope.form}');
		var formData = form.serialize() + '&job=' + '${pageScope.job}';
		$('body').mask("<fmt:message key='bodyMask.loading'/>");
		jQuery.ajax({
			type: 'POST',
			url: '${pageScope.url}',
			data:	formData,
			success: function(res){
				if(res.success){

					showSuccessMsg(res.messages[0].message);
					setTimeout(function(){
						if(res.redirect != null){
							window.location = res.redirect;
						}
					}, 3000);
				}else{
					var message = '';
					if(res.messages != undefined){
						for(i in res.messages){
							if(res.messages[i].field != null){
								//replacement for escape dot in jquery
								field = $( ('#container_'+ res.messages[i].field).replace('.', '\\.'));
								field.addClass('has-error');
							}
							message = message + res.messages[i].message + '</br>';
						}
					}else{
						message = "<fmt:message key='error.unexpected'/>";
					}
					$('body').unmask();
					showErrorMsg(message);
				}

				${pageScope.customSuccess}
				
			},
			failure: function(){
				$('body').unmask();
			}
		});
	}
</script>

<button class="btn btn-primary btn" type="button" onclick="${pageScope.form}_handler();">
	<fmt:message key='${pageScope.label}'/>
</button>

