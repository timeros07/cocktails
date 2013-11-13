<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<script src="/resources/scripts/jquery-ui-plugins/js/lib/jquery.event.drag-2.0.min.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/jquery.event.drop-2.0.min.js"></script>

	<script src="/resources/scripts/jquery-ui-plugins/js/lib/date.min.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/slick.core.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/slick.grid.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/slick.dataview.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-core.js"></script>	
	<script src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-grid.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-textinput.js"></script>
<c:set var="mode"><tiles:insertAttribute name="mode" /></c:set>
<c:set var="detailsMode" value="${mode == 'D'}"/>
<c:set var="modifyMode" value="${mode == 'M'}"/>
<c:set var="createMode" value="${mode == 'C'}"/>
	
<script>
	function uploadImage(){
	
		var formData = new FormData($('#uploadForm')[0]);
		$.ajax({
		url: $('#uploadForm').attr('action'),  //server script to process data
			type: 'POST',
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(res){
				$('#image').attr('src', res.imageUrl);
				$('#image').show();
				$('#_blobKey').val(res.blobkey);
			},
			error: function(res){
				alert("pojawił się nie oczekiwany błąd:" + res);
			},
			cache: false,
			contentType: false,
			processData: false
		});
	}


var $grid;
	function clearAddIngredientPanel(){
		$('#_count').val('');
		$('#element_id').val('');
	}
	
	function loadIngredients(){
		jQuery.ajax({
			type: 'POST',
			url: 'cocktailCreate',
			data: {'job' : 'LOAD_INGREDIENTS'},
			success: function(res){
				var data = [];
				for(i=0;i<res.length;i++){
					data[i] = {
						id : res[i].key.id,
						name : res[i].element.name,
						count : res[i].count,
						elementId : res[i].element.id
					}
				}
				$grid.setData(data);
				$grid.render();
				clearAddIngredientPanel();
			}
		});
	}
	
	function editIngredient(ingredient){
		$('#count').val(ingredient.count);
		$('select[name="element.id"]').val(ingredient.elementId);
	}
</script>

<div>
	<sf:form id="formularz" method="POST" modelAttribute="cocktailData" >
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
			<sf:hidden name="blobKey" id="_blobKey" path="blobKey"/>
		</table>
		</sf:form>
		<table>
			<c:if test="${createMode or modifyMode}">
				<tr>
					<td>
						<span><fmt:message key="labels.cocktail.image"/></span>
						<form id="uploadForm" action="${uploadUrl}" method="post" enctype="multipart/form-data">
							<input type="file" name="uploadFile">
							<input class="submitButton" type="button" onclick="uploadImage();" value="<fmt:message key='buttons.action.upload'/>"/>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<img style="display:none; max-height: 150px;max-width:250px;" id="image"/>
					</td>
				</tr>
			</c:if>
			<c:if test="${detailsMode}">
				<c:if test="${not empty cocktailData.blobKey}">
				<tr>
					<span><fmt:message key="labels.cocktail.image"/></span>
					<td>
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
			</c:if>
		</table>

	
		<h3>Lista składników</h3>
		<div id="myGrid" style="width: 500px; height: 100px; "></div>
	<c:if test="${createMode or modifyMode}" >	
	<sf:form id="addIngredientForm" method="POST" modelAttribute="ingredient" >
		<table>
			<tr>
				<td>
					<sf:select id="element_id" name="element.id" path="${element.id}" >
						<sf:options items="${elements}"/>
						<option value=""></option>
					</sf:select>
				</td>
			</tr>
			<tr>
				<td>
					<tags:text width="100" property="count" maxLength="3" disabled="${detailsMode}" label="labels.cocktail.ingredient.count"/>
				</td>
			</tr>
			<tr class="buttons">
				<td>
					<tags:submit-handler customSuccess="loadIngredients();"  url="cocktailCreate" label="buttons.action.add" job="ADD_INGREDIENT" form="addIngredientForm" />
				</td>
			</tr>
		</table>
	</sf:form>
	</c:if>
	<table>
	<tr class="buttons">
		<c:if test="${createMode}">
			<td>
				<tags:submit-handler  url="cocktailCreate" label="buttons.action.create" job="CREATE" form="formularz" />
				<input class="submitButton" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='cocktails'"/>
			</td>
		</c:if>
		<c:if test="${detailsMode}">
			<td>
				<input type="button" class="submitButton" onclick="window.location='cocktailModify?id=${cocktailData.id}'" value="<fmt:message key='buttons.action.modify'/>"/>
				<tags:simple-handler questionTitle="confirm.remove.title" question="question.remove" params="id: ${cocktailData.id}" url="cocktailDetails" label="buttons.action.remove" job="REMOVE" />
			</td>
		</c:if>
		<c:if test="${modifyMode}">
			<td>
				<tags:submit-handler  url="cocktailModify" label="buttons.action.save" job="SAVE" form="formularz" />
				<input class="submitButton" type="button" value="<fmt:message key='buttons.action.cancel'/>" onclick="window.location='cocktails'"/>
			</td>
		</c:if>
	</tr>
	</table>
	
</div>
<script>		
		function removeFormatter(rowNum, cellNum, value, columnDef, row){
			return '<img style="cursor: pointer;" width="20px" src="/resources/images/icons/remove_button.png" onclick="removeIngredient(' + row.id + ')"/>';
		}
		
		var cols = [ 
		{
			id : 'name',
			name : 'Name',
			field : 'name'
		}, 		
		{
			id : 'count',
			name : 'Count',
			field : 'count'
		},
		{
			id : 'remove',
			name : 'remove',
			field : 'remove',
			width: '10',
			formatter: removeFormatter
		}
		];		

			var data = [];
		var options = {
				forceFitColumns: true,
				enableColumnReorder: false,
				enableCellNavigation : true
			};	
		
		$grid = new Slick.Grid("#myGrid", data, cols, options);
		$grid.onClick.subscribe(function (e){
			var event = $grid.getCellFromEvent(e);
			editIngredient($grid.getDataItem(event.row));
		}) 					
		
		function urlFormatter(rowNum, cellNum, value, columnDef, row){
			return '<a href="cocktailDetails?id=' + row.id + '">' + value + '</a>'
		}
		
		<c:if test="${!createMode}" >
			loadIngredients();
		</c:if>
		
		
	</script>