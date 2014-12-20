<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<!-- editable grid-->
<link href="/resources/themes/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
<script src="/resources/themes/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script type="text/javascript" src="/resources/themes/bootstrap3-editable/editable.js"></script>
<c:set var="mode"><tiles:insertAttribute name="mode" /></c:set>
<c:set var="detailsMode" value="${mode == 'D'}"/>
<c:set var="modifyMode" value="${mode == 'M'}"/>
<c:set var="createMode" value="${mode == 'C'}"/>

<div class="row">
<sf:form id="formularz" method="POST" modelAttribute="cocktailData" >
	<div class="col-md-8 row">
		<div class="panel panel-default">
			<div class="panel-heading"><fmt:message key="labels.generalInformation"/></div>
			<div class="panel-body form-horizontal">
	  			<tags:text property="name" maxLength="50" disabled="${detailsMode}" label="labels.name"/>
	  			<tags:textarea label="labels.description" property="description" maxLength="200" disabled="${detailsMode}"/>
	  			<tags:select width="200" label="labels.status" disabled="${detailsMode}" property="status" items="${statuses}"/>
			</div>
		</div>
	</div>
	<tags:upload-panel form="formularz" label="labels.ingredient.image" successPath="/admin/ingredientUpload" property="blobKey" propertyValue="${cocktailData.blobKey}" disabled="${detailsMode}"/>
</sf:form>
<div class="panel panel-default">
	<div class="panel-heading"><fmt:message key="title.ingredientCategories.list"/></div>
	<table id="ingredientCategoriesTable" class="table table-striped table-bordered">
	  	<thead>
			<tr>
				<th><fmt:message key="labels.name"/></th>
				<th><fmt:message key="labels.description"/></th>
				<th><fmt:message key="labels.cocktail.ingredient.count"/></th>
			</tr>
	  </thead>
	  <tbody>
		<c:forEach items="${ingredients}" var="ingredient" varStatus="loop">
		<tr height="50px" class="editableGrid" >
			<td>
				<a data-name="name" data-url="/admin/cocktailCreate?job=MODIFY_INGREDIENT" data-type="text" data-title="<fmt:message key="labels.name"/>" data-pk="${ingredient.element.id}" href="#"><c:out value="${ingredient.element.name}"/></a>
			</td>
			<td>
				<a data-name="description" data-url="/admin/cocktailCreate?job=MODIFY_INGREDIENT" data-title="<fmt:message key="labels.description"/>" data-type="textarea" data-pk="${cat.id}" href="#"><c:out value="${cat.description}"/></a>
			</td>
			<td>
				<a data-source="[{value: 'A', text: 'Aktywny'}, {value: 'I', text: 'Nieaktywny'}]" data-name="status" data-url="/admin/cocktailCreate?job=MODIFY_INGREDIENT" title="<fmt:message key="labels.status"/>" data-pk="${cat.id}" data-type="select" href="#" data-value="<c:out value="${cat.status}"/>"></a>
			</td>
			<td>
				<tags:remove-handler id="${cat.id}" params="id: ${cat.id}" url="ingredientCategories" label="buttons.action.remove" />
			</td>
		</tr>
		</c:forEach>
		<tr height="50px" class="editableFooter">
			<td>
				<a data-name="name" data-url="#" data-type="text" data-title="<fmt:message key="labels.name"/>"  href="#"><c:out value="${newCategory.name}"/></a>
			</td>
			<td>
				<a data-name="description" data-url="#" data-title="<fmt:message key="labels.description"/>" data-type="textarea"  href="#"><c:out value="${newCategory.description}"/></a>
			</td>
			<td>
				<a data-source="[{value: 'A', text: 'Aktywny'}, {value: 'I', text: 'Nieaktywny'}]" data-name="status" data-url="#" title="<fmt:message key="labels.status"/>" data-type="select" href="#" data-value="<c:out value="${newCategory.status}"/>"></a>
			</td>
			<td>
				<button class="btn btn-primary btn-sm" type="button" onclick="addItemToGrid('/admin/cocktailCreate?job=ADD_INGREDIENT');">
					<fmt:message key='buttons.action.add'/>
				</button>
			</td>
		</tr>
	 	</tbody>
	</table>
</div>
</div>
	<div class="buttons">
		<c:if test="${createMode}">
			<tags:submit-handler  url="cocktailCreate" label="buttons.action.create" job="CREATE" form="formularz" />
			<button class="btn btn-default btn" type="button" onclick="window.location='cocktails'">
				<fmt:message key='buttons.action.cancel'/>
			</button>
		</c:if>
		<c:if test="${detailsMode}">
			<button type="button" class="btn btn-default btn" onclick="window.location='cocktailModify?id=${cocktailData.id}'" >
				<fmt:message key='buttons.action.modify'/>
			</button>
			<tags:remove-handler params="id: ${cocktailData.id}" url="cocktailDetails" label="buttons.action.remove" />
		</c:if>
		<c:if test="${modifyMode}">
			<tags:submit-handler  url="cocktailModify" label="buttons.action.save" job="SAVE" form="formularz" />
			<button class="btn btn-default btn" type="button" onclick="window.location='cocktails'">
				<fmt:message key='buttons.action.cancel'/>
			</button>
		</c:if>
	</div>
