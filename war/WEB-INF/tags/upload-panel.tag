<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="uploadUrl" required="true" rtexprvalue="true" %>
<%@ attribute name="id" required="true" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script>
	function ${id}_uploadImage(){
	
		var formData = new FormData($('#${pageScope.id}')[0]);
		$('body').mask("<fmt:message key='bodyMask.loading'/>");
		$.ajax({
		url: $('#${pageScope.id}').attr('action'),  //server script to process data
			type: 'POST',
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(res){
				if(res.success){
					$('#${pageScope.id}_image').attr('src', res.imageUrl);
					$('#${pageScope.id}_image').show();
					console.log(res.blobKey);
					$('#_blobKey').val(res.blobKey);
					console.log($('#_blobKey').val());
					showSuccessMsg(res.message);
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
			error: function(res){
				message = "<fmt:message key='error.unexpected'/>";
				showErrorMsg(message);
				$('body').unmask();
			},
			cache: false,
			contentType: false,
			processData: false
		});
	}
</script>
<table>
	<tr>
		<td>
			<span><fmt:message key="${pageScope.label}"/></span>
			<form id="${pageScope.id}" action="${pageScope.uploadUrl}" method="post" enctype="multipart/form-data">
				<input type="file" name="uploadFile">
				<input class="submitButton" type="button" onclick="${pageScope.id}_uploadImage();" value="<fmt:message key='buttons.action.upload'/>"/>
			</form>
		</td>
	</tr>
	<tr>
		<td>
			<img style="display:none; max-height: 150px;max-width:250px;" id="${pageScope.id}_image"/>
		</td>
	</tr>
</table>