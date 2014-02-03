<%@include file="/resources/pages/admin/layout/tags.jsp" %>
<%@page pageEncoding="utf-8" %>

<script type="text/javascript">
function switchLanguage(lngCode){
	jQuery.ajax({
		type: 'POST',
		url: '/switchLanguage',
		data: {
			'lngCode': lngCode
		},
		success: function(res){
			console.log('ok');
			window.location = window.location;
		},
		failure: function(){}
	});
} 
</script>

<h2>Cocktails</h2>
<div id="language">
	<a href="javascript:switchLanguage('pl');" title="Polski" >
		<img src="/resources/images/icons/pl.png" alt="Polski" />
	</a>
	<a href="javascript:switchLanguage('en');" title="English">
		<img src="/resources/images/icons/en.png" alt="English" />
	</a>
</div>