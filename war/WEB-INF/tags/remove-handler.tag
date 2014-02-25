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
	var question = "<fmt:message key='question.remove'/>";
	var title = "<fmt:message key='question.remove.title'/>";
	
	function remove_handler(){
		$('body').mask("<fmt:message key='bodyMask.loading'/>");
		jQuery.ajax({
			type: 'POST',
			url: '${pageScope.url}',
			data: {
				'job': 'REMOVE'
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
				$('#removeModal').modal('hide');
			},
			failure: function(){
				$('body').unmask();
				$('#removeModal').modal('hide');
			}
		});
	}

</script>

<c:if test="${! empty pageScope.question}">
	<div id="dialog-confirm" style="height:auto; display:none;">
	  <p><span class="ui-icon ui-icon-alert" style="float: left; "></span><fmt:message key='${pageScope.question}'/></p>
	</div>
</c:if>
<button class="btn btn-danger btn" data-toggle="modal" data-target="#removeModal">
  <fmt:message key='${pageScope.label}'/>
</button>

<!-- Modal -->
<div class="modal fade" id="removeModal" tabindex="-1" role="dialog" aria-labelledby="removeModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="text-align: left;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="removeModalLabel"><fmt:message key='question.remove.title'/></h4>
      </div>
      <div class="modal-body">
        <fmt:message key='question.remove'/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key='question.remove.cancel'/></button>
        <button type="button" class="btn btn-primary" onclick="remove_handler();"><fmt:message key='question.remove.confirm'/></button>
      </div>
    </div>
  </div>
</div>



