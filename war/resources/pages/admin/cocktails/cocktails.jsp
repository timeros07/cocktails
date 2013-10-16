<%@page pageEncoding="utf-8" %>
<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<!-- to avoid all these imports you can just include jquery-ui-plugins-0.0.12.js -->		
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/jquery.event.drag-2.0.min.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/jquery.event.drop-2.0.min.js"></script>

	<script src="/resources/scripts/jquery-ui-plugins/js/lib/date.min.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/slick.core.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/slick.grid.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/lib/slick.dataview.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-core.js"></script>	
	<script src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-grid.js"></script>
	<script src="/resources/scripts/jquery-ui-plugins/js/jquery-ui-plugins-textinput.js"></script>
<div class="pageActions">
		<input class="pageAction" type="button" onclick="window.location='cocktailCreate'" value="<fmt:message key='buttons.action.create'/>" />
</div>
<div id="myGrid" style="width: 500px; height: 300px; margin:50px;"></div>
	<script>		
		
		var cols = [ 
		{
			id : 'name',
			name : 'Name',
			field : 'name',
			filter: 'contains',
			formatter: urlFormatter
		}, 		
		{
			id : 'description',
			name : 'Description',
			field : 'description',
			filter: 'contains',
			formatter: urlFormatter
		}
		];		

			var data = [];
			<c:forEach items="${cocktails}" var="cocktail" varStatus="loop">
			data[${loop.index}] = {
				id : '${cocktail.id}',
				name : '${cocktail.name}',
				description : '${cocktail.description}'
			}
        </c:forEach>;
			
			var $grid = $('#myGrid').grid({
				'data': data,
				'columns': cols,
				'forceFitColumns': true,
				'enableColumnReorder': false,
				'enableCellNavigation' : true
			});						
		
		function urlFormatter(rowNum, cellNum, value, columnDef, row){
			return '<a href="cocktailDetails?id=' + row.id + '">' + value + '</a>'
		}
		
		
	</script>