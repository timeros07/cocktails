<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="job" required="false" rtexprvalue="true" %>
<%@ attribute name="url" required="true" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>
<%@ attribute name="customSuccess" required="false" rtexprvalue="true" %>
<%@ attribute name="customFailure" required="false" rtexprvalue="true" %>
<%@ attribute name="question" required="false" rtexprvalue="true" %>
<%@ attribute name="questionTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="params" required="false" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
	var question = "<fmt:message key='${question}'/>";
	var title = "<fmt:message key='${questionTitle}'/>";
	
	function handler(){
		$('body').mask("<fmt:message key='bodyMask.loading'/>");
		jQuery.ajax({
			type: 'POST',
			url: '${pageScope.url}',
			data: {
				'job': '${pageScope.job}'
				<c:if test="${pageScope.params != null}">,</c:if>
				<c:if test="${pageScope.params != null}">
					${pageScope.params}
				</c:if>
			},
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
						message = res.messages[0].message;
					}else{
						message = "<fmt:message key='error.unexpected'/>";
					}
					showErrorMsg(message);
				}
				$('body').unmask();
			},
			failure: function(){
				$('body').unmask();
			}
		});
	}
function confirm(question){
	$('#dialog-confirm').attr('title',title);
	$('#dialog-confirm').show();
	 $( "#dialog-confirm" ).dialog({
      resizable: false,
      height:140,
      modal: true,
      buttons: {
        "Tak": function() {
          $( this ).dialog( "close" );
			handler();
		},
        Cancel: function() {
          $( this ).dialog( "close" );
		   return false;
        }
      }
    });
	}
	
</script>

<c:if test="${! empty pageScope.question}">
	<div id="dialog-confirm" style="height:auto; display:none; text-align:left">
	  <p><span class="ui-icon ui-icon-alert" style="float: left; "></span><fmt:message key='${pageScope.question}'/></p>
	</div>
</c:if>

<button class="btn btn-default btn-sm" type="button" onclick="${! empty pageScope.question ? 'confirm();' : 'handler();'}">
	<fmt:message key='${pageScope.label}'/>
</button>

