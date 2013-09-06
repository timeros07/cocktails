<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>
<div>
	<h2>Dodawanie składnika</h2>
	
	<sf:form method="POST" modelAttribute="elementData" >

			<label for="name">Nazwa</label>
			<sf:input  path="name"/>		
	
			<label for="description">Opis</label>
			<sf:input path="description"/>	
			
		<input type="submit" value="Add"/>

	</sf:form>
</div>