<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>

<div class="pageActions">
	<button class="btn btn-primary btn-sm" type="button" onclick="window.location='ingredientCreate'" ><span class="glyphicon glyphicon-plus"></span>&nbsp;<fmt:message key='buttons.action.create'/></button>
</div>
<div class="panel panel-default">
	<div class="panel-heading"><fmt:message key="title.ingredients.list"/></div>
	<table class="table table-striped table-bordered table-hover">
	  <thead>
	    <tr>
			<th ><fmt:message key="labels.name"/></th>
			<th ><fmt:message key="labels.description"/></th>
			<th ><fmt:message key="labels.ingredient.image"/></th>
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
	<div class="text-center">
		<ul class="pagination pagination">
			<c:if test="${paging.currentPageNr > 1 }">
				<li class="previous"><a href="/admin/ingredients?p=${paging.currentPageNr-1}">&laquo; <fmt:message key="labels.previous"/></a></li>
			</c:if>
			<c:forEach begin="1" end="${paging.pagesCount}" varStatus="index">
				<c:choose>
					<c:when test="${index.index == paging.currentPageNr}">
						<li class="active"><a href="/admin/ingredients?p=${index.index}">${index.index }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/admin/ingredients?p=${index.index}">${index.index }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>	
			<c:if test="${paging.currentPageNr < paging.pagesCount }">
				<li class="next"><a href="/admin/ingredients?p=${paging.currentPageNr+1}"><fmt:message key="labels.next"/> &raquo;</a></li>
			</c:if>
		  
		</ul>
	</div>
</div>
	
