<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<c:set var="mode"><tiles:insertAttribute name="mode" /></c:set>
<c:set var="detailsMode" value="${mode == 'D'}"/>
<c:set var="modifyMode" value="${mode == 'M'}"/>
<c:set var="createMode" value="${mode == 'C'}"/>
<div style="position: absolute;left:50%;top:1px;">
 <div id="errorPopup" style="position: relative; left: -50%; background:Green; top:1px;width:200px; height:60px; display: none;">
            I am some centered shrink-to-fit content! <br />
            tum te tum
        </div>
</div>
<script>
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
</script>
<style>
.errorText{
	background: Red;
}
</style>

<div>
	<h2>Dodawanie składnika</h2>
	<sf:form id="formularz" method="POST" modelAttribute="elementData" >
		<table>
			<tr>
				<td>
					<sf:errors path="*" cssClass="errorHeader"/>
				</td>
			</tr>
		
			<tr>
				<td>
					<label for="name"><fmt:message key="labels.ingredient.name"/></label>
					<sf:input  path="name" disabled="${detailsMode}" cssErrorClass="errorText"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="description"><fmt:message key="labels.ingredient.description"/></label>
					<sf:textarea rows="3" path="description" disabled="${detailsMode}" cssErrorClass="errorTextArea"/>	
				</td>
			</tr>
			<tr class="buttons">
			<c:if test="${createMode}">
				<td>
					<input type="submit" value="Add"/>
				</td>
			</c:if>
			<c:if test="${detailsMode}">
				<td>
					<input type="button" onclick="window.location='ingredientModify?id=${elementData.id}'" value="Modify"/>
					<input type="button" onclick="removeItem(${elementData.id});" value="Remove"/>
				</td>
			</c:if>
			<c:if test="${modifyMode}">
				<td>
					<input type="submit" value="Save"/>
				</td>
			</c:if>
			</tr>
		</table>

	</sf:form>
	
</div>