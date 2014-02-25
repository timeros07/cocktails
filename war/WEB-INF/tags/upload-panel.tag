<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="property" required="true" rtexprvalue="true" %>
<%@ attribute name="propertyValue" required="true" rtexprvalue="true" %>
<%@ attribute name="form" required="true" rtexprvalue="true" %>
<%@ attribute name="successPath" required="true" rtexprvalue="true" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="width" required="false" rtexprvalue="true" %>
<%@ attribute name="height" required="false" rtexprvalue="true" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="id" value="${empty pageScope.id ? fn:replace(pageScope.property, '.', '_') : pageScope.id}"/>
<script>
	var uploadUrl;
	
	$( document ).ready(function() {
		getUploadUrl();
	});


/*disabling upload panel when file was ubloaded to blobstore*/
	function ${id}_disableUploadPanel(){
		$('.fileinput .btn').attr('disabled', true);
		$('#uploadButton').attr('disabled', true);
		$('#uploadFile').attr('disabled', true);
	}
	
	function getUploadUrl(){
		$.ajax({
			type: 'POST',
			data:{
				'successPath' : '${pageScope.successPath}'
				},
			url: 'getUploadUrl',
			success: function(res){
				uploadUrl = res;
			}
		});
	}
	
	function ${id}_uploadImage(){
		
		var formData = new FormData($('#${pageScope.form}')[0]);
		$('body').mask("<fmt:message key='bodyMask.loading'/>");
		$.ajax({
		url: uploadUrl,  //server script to process data
			type: 'POST',
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(res){
				if(res.success){
					$('#${pageScope.id}_image').attr('src', res.imageUrl);
					$('#${pageScope.id}_image').show();
					$('#_blobKey').val(res.blobKey);
					showSuccessMsg(res.message);
					${id}_disableUploadPanel();
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
				getUploadUrl();
			},
			error: function(res){
				message = "<fmt:message key='error.unexpected'/>";
				showErrorMsg(message);
				$('body').unmask();
				getUploadUrl();
			},
			cache: false,
			contentType: false,
			processData: false
		});
	}
</script>
<sf:hidden name="${pageScope.property}" id="_blobKey" path="${pageScope.property}"/>
<div class="col-md-4">
<div class="panel panel-default" >
	<div class="panel-heading"><fmt:message key="${pageScope.label}"/></div>
		<div class="panel-body text-center">
				<div class="fileinput fileinput-new" data-provides="fileinput">
					<div class="col-sm-12">
						<div class="fileinput-preview thumbnail" data-trigger="fileinput" 
							style="width: ${!empty pageScope.width ? pageScope.width : '200'}px; height: 135px;">
							<c:if test="${not empty pageScope.propertyValue }">
								<img src="/serve?blob-key=${pageScope.propertyValue}">
							</c:if>
						</div>
						<c:if test="${!disabled }">
						<div class="col-md-12">
							<span class="btn btn-default btn-sm btn-file">
								<span class="fileinput-new"><fmt:message key='labels.image.select'/></span>
								<span class="fileinput-exists"><fmt:message key='labels.image.change'/></span>
								<input type="file" name="uploadFile" id="uplaodFile">
							</span>
							<a href="#" class="btn btn-default btn-sm fileinput-exists" data-dismiss="fileinput"><fmt:message key='labels.image.remove'/></a>
							<button class="btn btn-primary btn-sm" type="button" onclick="${pageScope.id}_uploadImage();" id="uploadButton">
								<fmt:message key='buttons.action.upload'/>
							</button>
						</div>	
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
			

