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

	<style>
		#messageBox{
			top:0;
			right:0;
			position: absolute;
			width: 100%;
			text-align:center;
			display:none;
		}
		#messageBoxInner{
			width: 300px;
			height: 60px;
			background-color: rgb(188, 236, 188);
			display: inline-block;
			background-image: url('/resources/images/icons/ok.png');
			background-repeat: no-repeat;
			background-position: 10px center;
			color: rgb(45, 162, 45);
			font-size: 1.4em;
			border: 1px solid rgb(45, 162, 45);
			font-family: "Lucida Grande";
			border-radius: 6px;
			
		}
		#messageBoxInner h4{
			margin-left: 25px;
			font-weight: lighter;
		}
	</style>
	<div id="messageBox">
		<div id="messageBoxInner">

		</div>
	</div>

<script>
	var question = "<fmt:message key='${question}'/>";
	var title = "<fmt:message key='${questionTitle}'/>";
	
	function handler(){
		$('body').mask("Loading...");
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
					}, 2000);
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

	function showSuccessMsg(message){
		
		$( "#messageBoxInner").html('<h4>' + message + '</h4>');
		$( "#messageBox" ).slideDown('slow',function(){
			setTimeout(function() {	$( "#messageBox" ).slideUp('slow'); }, 2000 );}
		);
	}
	
	
</script>

<c:if test="${! empty pageScope.question}">
	<div id="dialog-confirm" style="height:auto; display:none;">
	  <p><span class="ui-icon ui-icon-alert" style="float: left; "></span><fmt:message key='${pageScope.question}'/></p>
	</div>
</c:if>

<input class="submitButton" type="button" 
	onclick="${! empty pageScope.question ? 'confirm();' : 'handler();'}"
	value="<fmt:message key='${pageScope.label}'/>"/>

