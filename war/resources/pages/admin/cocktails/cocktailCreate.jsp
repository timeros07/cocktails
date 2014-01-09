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
	<sf:form id="formularz" style="width: 500px" method="POST" modelAttribute="cocktailData" >
		<table class="table table-no-border">
			<tr>
				<td>
					<tags:text width="350" property="name" maxLength="50" disabled="${detailsMode}" label="labels.ingredient.name"/>
				</td>
			</tr>
			<tr>
				<td>
					<tags:textarea width="350" property="description" maxLength="150" disabled="${detailsMode}" label="labels.ingredient.description"/>
				</td>
			</tr>
			<sf:hidden name="blobKey" id="_blobKey" path="blobKey"/>
			<c:if test="${createMode or modifyMode}">
			<tr>
				<td>
					<tags:upload-panel width="200" form="formularz" label="labels.cocktail.image" successPath="/admin/cocktailUpload" id="uploadImg" />
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
		</sf:form>

	
		<h3>Składniki</h3>
		<div id="myGrid" style="width: 500px; height: 100px; "></div>
	<c:if test="${createMode or modifyMode}" >	
	<sf:form id="addIngredientForm" method="POST" modelAttribute="ingredient" style="width: 500px">
		<table class="table table-no-border">
			<tr>
				<td>
					<label for="element.id"><fmt:message key="labels.cocktail.ingredient.element"/></label>
					<sf:select style="margin-right:80px; display:inline; width: 150px" class="form-control" id="element_id" name="element.id" path="${element.id}" >
						<sf:options items="${elements}"/>
						<option value=""></option>
					</sf:select>
					<tags:text width="100" property="count" maxLength="3" disabled="${detailsMode}" label="labels.cocktail.ingredient.count"/>
				</td>
			</tr>
					</table>
			<div class="buttons">
				<tags:submit-handler customSuccess="loadIngredients();"  url="cocktailCreate" label="buttons.action.add" job="ADD_INGREDIENT" form="addIngredientForm" />
			</div>

	</sf:form>
	</c:if>
	<div class="buttons">
		<c:if test="${createMode}">
			<tags:submit-handler  url="cocktailCreate" label="buttons.action.create" job="CREATE" form="formularz" />
			<button class="btn btn-default btn-sm" type="button" onclick="window.location='cocktails'">
				<fmt:message key='buttons.action.cancel'/>
			</button>
		</c:if>
		<c:if test="${detailsMode}">
			<button type="button" class="btn btn-default btn-sm" onclick="window.location='cocktailModify?id=${cocktailData.id}'" >
				<fmt:message key='buttons.action.modify'/>
			</button>
			<tags:remove-handler params="id: ${cocktailData.id}" url="cocktailDetails" label="buttons.action.remove" />
		</c:if>
		<c:if test="${modifyMode}">
			<tags:submit-handler  url="cocktailModify" label="buttons.action.save" job="SAVE" form="formularz" />
			<button class="btn btn-default btn-sm" type="button" onclick="window.location='cocktails'">
				<fmt:message key='buttons.action.cancel'/>
			</button>
		</c:if>
	</div>
	
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