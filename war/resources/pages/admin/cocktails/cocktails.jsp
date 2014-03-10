<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<div class="pageActions">
		<button class="btn btn-primary btn-sm" type="button" onclick="window.location='cocktailCreate'" ><span class="glyphicon glyphicon-plus"></span>&nbsp;<fmt:message key='buttons.action.create'/></button>
</div>
<div class="panel panel-default">
	<div class="panel-heading"><fmt:message key="title.cocktails.list"/></div>
		<table class="table table-striped table-bordered table-hover">
		  <thead>
			<tr>
				<th width="200px"><fmt:message key="labels.name"/></th>
				<th width="400px"><fmt:message key="labels.description"/></th>
				<th width="200px"><fmt:message key="labels.cocktail.image"/></th>
			</tr>
		  </thead>
		  <tbody data-link="row" class="rowlink">
			<c:forEach items="${cocktails}" var="cocktail" varStatus="loop">
			<tr height="110px" >
				<td>
					<a href="cocktailDetails?id=${cocktail.id}"><c:out value="${cocktail.name}"/></a>
				</td>
				<td><c:out value="${cocktail.description}"/></td>
				<td>
					<c:choose>
						<c:when test="${not empty cocktail.blobKey}">
							<img class="img-thumbnail" src="/serve?blob-key=${cocktail.blobKey}" style="max-height: 100px;max-width:100px;"/>
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
	</div>
	<div class="text-center">
		<ul class="pagination pagination">
			<c:if test="${paging.currentPageNr > 1 }">
				<li class="previous"><a href="/admin/cocktails?p=${paging.currentPageNr-1}">&laquo; <fmt:message key="labels.previous"/></a></li>
			</c:if>
			<c:forEach begin="1" end="${paging.pagesCount}" varStatus="index">
				<c:choose>
					<c:when test="${index.index == paging.currentPageNr}">
						<li class="active"><a href="/admin/cocktails?p=${index.index}">${index.index }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/admin/cocktails?p=${index.index}">${index.index }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>	
			<c:if test="${paging.currentPageNr < paging.pagesCount }">
				<li class="next"><a href="/admin/cocktails?p=${paging.currentPageNr+1}"><fmt:message key="labels.next"/> &raquo;</a></li>
			</c:if>
		  
		</ul>
	</div>
</div>