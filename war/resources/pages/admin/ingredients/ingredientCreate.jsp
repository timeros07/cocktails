<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<c:set var="mode"><tiles:insertAttribute name="mode" /></c:set>
<c:set var="detailsMode" value="${mode == 'D'}"/>
<c:set var="modifyMode" value="${mode == 'M'}"/>
<c:set var="createMode" value="${mode == 'C'}"/>
<div id="dialog-confirm" style="height:auto; display:none;">
  <p><span class="ui-icon ui-icon-alert" style="float: left; "></span><fmt:message key='question.remove'/></p>
</div>
<script>
	var question = "<fmt:message key='question.remove'/>";
	var title = "<fmt:message key='confirm.remove.title'/>";
	
	function removeItem(id){
		jQuery.ajax({
			type: 'POST',
			url: 'ingredientRemove',
			data: {
				'id' : id
			},
			success: function(res){
			window.location = 'ingredients';
				//$( "#errorPopup" ).show( 'slide', options, 500, function(){} );
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
			removeItem(${elementData.id});
		},
        Cancel: function() {
          $( this ).dialog( "close" );
		   return false;
        }
      }
    });
	}
	
</script>

<div>
	<sf:form id="formularz" method="POST" modelAttribute="elementData" >
		<sf:errors path="*" cssClass="errorHeader"/>
		<table>
			<tr>
				<td>
					<tags:text width="250" property="name" maxLength="50" disabled="${detailsMode}" label="labels.ingredient.name"/>
				</td>
			</tr>
			<tr>
				<td>
					<tags:textarea width="250" property="description" maxLength="150" disabled="${detailsMode}" label="labels.ingredient.description"/>
				</td>
			</tr>
			<tr class="buttons">
			<c:if test="${createMode}">
				<td>
					<input class="submitButton" type="submit" value="<fmt:message key='buttons.action.add'/>"/>
					<input class="submitButton" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredients'"/>
				</td>
			</c:if>
			<c:if test="${detailsMode}">
				<td>
					<input class="submitButton" type="button" onclick="window.location='ingredientModify?id=${elementData.id}'" value="<fmt:message key='buttons.action.modify'/>"/>
					<input class="submitButton" type="button" onclick="confirm();" value="<fmt:message key='buttons.action.remove'/>"/>
				</td>
			</c:if>
			<c:if test="${modifyMode}">
				<td>
					<input class="submitButton" type="submit" value="<fmt:message key='buttons.action.save'/>"/>
					<input class="submitButton" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='ingredients'"/>
				</td>
			</c:if>
			</tr>
		</table>

	</sf:form>
	
</div>