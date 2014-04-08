<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<div class="row">
	<sf:form id="formularz" method="POST" modelAttribute="message" >
		<div class="col-md-8 row">
			<div class="panel panel-default">
				<div class="panel-body form-horizontal">
		  			<tags:text property="topic" maxLength="50" disabled="true" label="labels.contact.topic"/>
		  			<tags:textarea label="labels.contact.content" property="content" maxLength="200" disabled="true"/>
		  			<tags:text property="email" maxLength="50" disabled="true" label="labels.contact.email"/>
				</div>
			</div>
		</div>
	</sf:form>
</div>
		
<div class="buttons">
	<tags:remove-handler params="id: ${message.id}" url="contactMessageDetails" label="buttons.action.remove" />
</div>
