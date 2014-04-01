
<link href="/resources/themes/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
<script src="/resources/themes/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script>

$(document).ready(function () {
	$('#users a').editable({
	    type: 'text',
	    name: 'username',
	    url: '/post',
	    title: 'Enter username'
	    //mode: 'inline'
	});

	//ajax emulation
	$.mockjax({
	    url: '/post',
	    responseTime: 200
	}); 
});

</script>

   <table id="users" class="table table-bordered table-condensed">
        <tr><th>#</th><th>name</th><th>age</th></tr>
        <tr>
            <td>1</td>
            <td><a href="#" data-pk="1">Mike</a></td>
            <td>21</td>       
        </tr>
        
        <tr>
            <td>2</td>
            <td><a href="#" data-pk="2">John</a></td>
            <td>28</td>       
        </tr>        
        
        <tr>
            <td>3</td>
            <td><a href="#" data-pk="3">Mary</a></td>
            <td>24</td>       
        </tr>        
        
    </table>  