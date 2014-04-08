/*Eitable gird script*/
$(document).ready(function () {
	//common
	$('.editableGrid a[data-type="text"]').editable({
		emptytext: 'pusty',
	    success: function(res, newValue) {
	    	if(!res.success){
		    	var message = '';
		    	if(res.messages != undefined){
					for(i in res.messages){
						message = message + res.messages[i].message;
					}
				}else{
					message = "<fmt:message key='error.unexpected'/>";
				}
		    	return message;
	    	}
	    }
	});
	$('.editableGrid a[data-type="textarea"]').editable({
		emptytext: 'pusty',
		inputclass:	'editableTextArea',
		rows: 6,
	    success: function(res, newValue) {
	    	if(!res.success){
		    	var message = '';
		    	if(res.messages != undefined){
					for(i in res.messages){
						message = message + res.messages[i].message;
					}
				}else{
					message = "<fmt:message key='error.unexpected'/>";
				}
		    	return message;
	    	}
	    }
	});
	$('.editableGrid a[data-type="select"]').editable({
		emptytext: 'pusty',
	    success: function(res, newValue) {
	    	if(!res.success){
		    	var message = '';
		    	if(res.messages != undefined){
					for(i in res.messages){
						message = message + res.messages[i].message;
					}
				}else{
					message = "<fmt:message key='error.unexpected'/>";
				}
		    	return message;
	    	}
	    }
	});
	
	//footer
	$('.editableFooter a').editable({
		emptytext: 'pusty'
	});
	
});

function addItemToGrid(){
	$('body').mask("Wczytywanie");
	 $('.editableFooter a').editable('submit', { 
	       url: '/admin/ingredientCategories?job=CREATE', 
			success: function(res){
				if(res.success){

					showSuccessMsg(res.messages[0].message);
					setTimeout(function(){
						if(res.redirect != null){
							window.location = res.redirect;
						}
					}, 3000);
				}else{
					var message = '';
					if(res.messages != undefined){
						for(i in res.messages){
							if(res.messages[i].field != null){
								//replacement for escape dot in jquery
								field = $( ('#container_'+ res.messages[i].field).replace('.', '\\.'));
								field.addClass('has-error');
							}
							message = message + res.messages[i].message + '</br>';
						}
					}else{
						message = "Nieoczekiwany blad";
					}
					$('body').unmask();
					showErrorMsg(message);
				}
				
			},
			failure: function(){
				$('body').unmask();
			}
	 });
}