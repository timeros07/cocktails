<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
 <script type="text/javascript">
 var RecaptchaOptions = {
    theme : 'clean'
 };
 </script>
<div class="row">
	<sf:form id="formularz" method="POST" modelAttribute="message" >
		<div class="col-md-8 row">
			<div class="panel panel-default">
				<div class="panel-heading"><fmt:message key="labels.generalInformation"/></div>
				<div class="panel-body form-horizontal">
		  			<tags:text property="topic" maxLength="50"  label="labels.contact.topic"/>
		  			<tags:textarea label="labels.contact.content" property="content" maxLength="200" />
		  			<tags:text property="email" maxLength="50"  label="labels.contact.email"/>
					<div style="margin-right: 10px;float: right;">
						${captchaHtml}
					</div>
				</div>
			</div>
		</div>
	</sf:form>
</div>
<div class="buttons">
	<tags:submit-handler url="contact" label="buttons.action.send" job="SEND" form="formularz" />
</div>