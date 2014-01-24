<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<script>

$( document ).ready(function() {
	$('.star').raty({
	    cancelOff     : 'resources/themes/star-rating/images/cancel-off.png',
	    cancelOn      : 'resources/themes/star-rating/images/cancel-on.png',
	    starHalf      : 'resources/themes/star-rating/images/star-half.png',
	    starOff       : 'resources/themes/star-rating/images/star-off.png',
	    starOn        : 'resources/themes/star-rating/images/star-on.png',
	    hints         : ['trucizna', 'szkoda pić', 'ujdzie', 'dobry', 'arcydzieło!!!'],
	    cancel : true,
		half   : false,
		size   : 44,
		target : '#hint',
		 targetKeep : true,
		click: function(score, evt) {
			$('.star').mask();
			jQuery.ajax({
					type: 'POST',
					url: 'cocktailDetails',
					data: {'job' : 'RATE', 'rate' : score},
					success: function(res){
						 $('.star').unmask();
					},
					failure: function(){
						$('.star').unmask();
					}
					
				});
			  }
		});
});

	
</script>

<div>
	<sf:form id="formularz" style="width: 600px" method="POST" modelAttribute="cocktailData" >
		<table class="table table-no-border">
			<c:if test="${!empty UserContext.user}">
				<tr>
					<td>
						
						<label>Próbowałem, moja ocena to:</label>
						<div style="display:inline" class="star"></div>
						 <div id="hint"></div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>
					<tags:text width="350" property="name" maxLength="50" disabled="true" label="labels.ingredient.name"/>
				</td>
			</tr>
			<tr>
				<td>
					<tags:textarea width="350" property="description" maxLength="150" disabled="true" label="labels.ingredient.description"/>
				</td>
			</tr>
			<sf:hidden name="blobKey" id="_blobKey" path="blobKey"/>
				<c:if test="${not empty cocktailData.blobKey}">
				<tr>
					<td>
						<label><fmt:message key="labels.cocktail.image"/></label>
						<img src="/serve?blob-key=${cocktailData.blobKey}" style="margin-left: 20px;max-height: 150px;max-width:250px;"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${empty cocktailData.blobKey}">
					<tr>
						<td><span><fmt:message key="labels.cocktail.image"/></span>
						<span><fmt:message key="labels.cocktail.image.noImage"/></span></td>
					</tr>
				</c:if>
		</table>
	</sf:form>
	<h3>Składniki</h3>
<table class="table">
	<thead>
		<tr>
			<th><fmt:message key="labels.ingredient.name"/></th>
			<th><fmt:message key="labels.ingredient.description"/></th>
			<th><fmt:message key="labels.cocktail.ingredient.count"/></th>
		</tr>
	</thead>
	<tbody>
		 <c:forEach var="ingredient" items="${cocktailData.ingredients}">
			 <tr>
			 	<td>${ingredient.element.name}</td>
			 	<td>${ingredient.element.description}</td>
			 	<td>${ingredient.count}</td>
			 </tr>
		 </c:forEach>
	 </tbody>
 </table>
		
</div>
