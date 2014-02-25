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

<sf:form id="addIngredientForm" method="POST" modelAttribute="ingredient" >
	<div class="col-md-8 row">
		<div class="panel panel-default">
			<div class="panel-heading"><fmt:message key="title.ingredients"/></div>
			<div class="panel-body form-horizontal">
				<div id="myGrid" style="width: 500px; height: 100px; "></div>
			
				<tags:select width="200" label="labels.cocktail.ingredient.element" disabled="${detailsMode}" property="element.id" items="${elements}" id="element_id"/>
				<tags:text width="100" property="count" maxLength="3" disabled="${detailsMode}" label="labels.cocktail.ingredient.count" />
				<tags:submit-handler customSuccess="loadIngredients();"  url="cocktailCreate" label="buttons.action.add" job="ADD_INGREDIENT" form="addIngredientForm" />
			</div>
		</div>
	</div>
</sf:form>
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