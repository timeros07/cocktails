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
		$('body').mask("Wczytywanie...");
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
								field = $('#_'+ res.messages[i].field);
								if(field.is('textarea')){
									field.addClass('errorTextArea');
								}else if(field.is('input')){
									field.addClass('errorText');
								}
							}
							message = message + res.messages[i].message + '</br>';
						}
					}else{
						message = "<fmt:message key='error.unexpected'/>";
					}
					
					
					showErrorMsg(message);

				}
				$('body').unmask();
				${pageScope.customSuccess}
				
			},
			failure: function(){
				$('body').unmask();
			}
		});
	}

	function showSuccessMsg(message){
		
		$( "#successMessageBoxInner").html('<h4>' + message + '</h4>');
		$( "#successMessageBox" ).slideDown('slow',function(){
			setTimeout(function() {	$( "#successMessageBox" ).slideUp('slow'); }, 2000 );}
		);
	}
	
	function showErrorMsg(message){
		
		$( "#errorMessageBoxInner").html('<h4>' + message + '</h4>');
		$( "#errorMessageBox" ).slideDown('slow');
	}
	
</script>

<input class="submitButton" type="button" 
	onclick="${pageScope.form}_handler();"
	value="<fmt:message key='${pageScope.label}'/>"
/>

