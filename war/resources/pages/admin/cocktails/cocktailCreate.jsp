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
	function removeItem(id){
		jQuery.ajax({
			type: 'POST',
			url: 'cocktailRemove',
			data: {
				'id' : id
			},
			success: function(res){
				window.location = 'cocktails';
			}
		});
	}
	function addIngredient(){
	var data= $('#addIngredientForm').serialize();
		jQuery.ajax({
			type: 'POST',
			url: 'addIngredient',
			data: data,
			success: function(res){
				loadIngredients();
			}
		});
	}
	
	function loadIngredients(){
		jQuery.ajax({
			type: 'POST',
			url: 'getIngredients',
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
			},
			contentType: 'application/json'
		});
	}
	
	function removeIngredient(id){
		
			jQuery.ajax({
			type: 'GET',
			url: 'removeIngredient',
			data: {'id' : id},
			success: function(res){
				loadIngredients();
			},
			contentType: 'application/json'
		});
	}
	
	function editIngredient(ingredient){
		$('#count').val(ingredient.count);
		$('select[name="element.id"]').val(ingredient.elementId);
	}
	
	function ajaxTest(){
		jQuery.ajax({
			type: 'POST',
			url: 'getIngredients',
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(res){
				console.log(res);
			}
		});
	}
</script>

<div>
	<h2>Dodawanie Drinka</h2>
	<sf:form method="POST" modelAttribute="cocktailData" >
		<table>
			<tr>
				<td>
					<sf:errors path="*" cssClass="errorHeader"/>
				</td>
			</tr>
		
			<tr>
				<td>
					<label for="name"><fmt:message key="labels.cocktail.name"/></label>
					<sf:input  path="name" cssErrorClass="errorText"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="description"><fmt:message key="labels.cocktail.description"/></label>
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
					<input type="button" onclick="window.location='cocktailModify?id=${cocktailData.id}'" value="Modify"/>
					<input type="button" onclick="removeItem(${cocktailData.id});" value="Remove"/>
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
	
		<h3>Lista składników</h3>
		<div id="myGrid" style="width: 500px; height: 100px; "></div>
	<c:if test="${createMode or modifyMode}" >	
	<sf:form id="addIngredientForm" method="POST" modelAttribute="ingredient" >
		<table>
			<tr>
				<td>
					<label for="element"><fmt:message key="labels.cocktail.ingredient.element"/></label>
					<sf:select path="element.id" items="${elements}"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="count"><fmt:message key="labels.cocktail.ingredient.count"/></label>
					<sf:input path="count" />
				</td>
			</tr>
			<tr class="buttons">
				<td>
					<input type="button" onclick="addIngredient();" value="<fmt:message key='buttons.action.add'/>"/>
				</td>
			</tr>
		</table>
	</sf:form>
	</c:if>
	
	<input type="button" onclick="ajaxTest();" value="ajaxTest"/>
</div>
<script>		
		function removeFormatter(rowNum, cellNum, value, columnDef, row){
			return '<img width="20px" src="/resources/images/icons/remove_button.png" onclick="removeIngredient(' + row.id + ')"/>';
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