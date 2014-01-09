<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>

<div class="pageActions">
	<button class="btn btn-primary btn-sm" type="button" onclick="window.location='ingredientCreate'" ><fmt:message key='buttons.action.create'/></button>
</div>
<table class="table table-striped table-bordered table-hover">
  <thead>
    <tr>
		<th width="200px"><fmt:message key="labels.ingredient.name"/></th>
		<th width="400px"><fmt:message key="labels.ingredient.description"/></th>
		<th width="200px"><fmt:message key="labels.ingredient.image"/></th>
	</tr>
  </thead>
  <tbody data-link="row" class="rowlink">
	<c:forEach items="${ingredients}" var="ingredient" varStatus="loop">
	<tr height="110px" >
		<td>
			<a href="ingredientDetails?id=${ingredient.id}"><c:out value="${ingredient.name}"/></a>
		</td>
		<td><c:out value="${ingredient.description}"/></td>
		<td>
			<c:choose>
				<c:when test="${not empty ingredient.blobKey}">
					<img class="img-thumbnail" src="/serve?blob-key=${ingredient.blobKey}" style="max-height: 100px;max-width:100px;"/>
				</c:when>
				 <c:otherwise>
				 	<img src="/resources/images/icons/unkown.png" style="max-height: 100px;max-width:100px;"/>
				 </c:otherwise>
			</c:choose>
		</td>
	</tr>
	</c:forEach>
  </tbody>
</table>
	
