<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>

<sf:select class="multiselect form-control"  path="searchCriteria.id" multiple="true">
	<sf:options items="${elements}"/>
</sf:select>

<script type="text/javascript">
  $(document).ready(function() {
    $('.multiselect').multiselect({
    	enableFiltering: true
    });
  });
</script>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th><fmt:message key="labels.name"/></th>
			<th><fmt:message key="labels.description"/></th>
			<th><fmt:message key="labels.cocktail.image"/></th>
		</tr>
	</thead>
	<tbody data-link="row" class="rowlink">
	 <c:forEach var="cocktail" items="${cocktails}">
	 	<tr onclick="window.location = 'cocktailDetails?id=${cocktail.id}'">
	 		<td>${cocktail.name}</td>
	 		<td>${cocktail.description}</td>
	 		<td>
	 			<c:choose>
	 				<c:when test="${not empty cocktail.blobKey}">
	 					<img src="/serve?blob-key=${cocktail.blobKey}" style="max-height: 150px;max-width:250px;"/>
	 				</c:when>
	 				<c:otherwise>
	 					<img src="/resources/images/icons/unkown.png" style="max-height: 150px;max-width:250px;"/>
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
				<li class="previous"><a href="/cocktails?p=${paging.currentPageNr-1}">&laquo; <fmt:message key="labels.previous"/></a></li>
			</c:if>
			<c:forEach begin="1" end="${paging.pagesCount}" varStatus="index">
				<c:choose>
					<c:when test="${index.index == paging.currentPageNr}">
						<li class="active"><a href="/cocktails?p=${index.index}">${index.index }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/cocktails?p=${index.index}">${index.index }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>	
			<c:if test="${paging.currentPageNr < paging.pagesCount }">
				<li class="next"><a href="/cocktails?p=${paging.currentPageNr+1}"><fmt:message key="labels.next"/> &raquo;</a></li>
			</c:if>
		  
		</ul>
	</div>


