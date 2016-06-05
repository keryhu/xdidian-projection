'use strict';

$(document).ready(function() {

	blur();
	
	$('#loginForm input').on('keyup blur', function() {
		//所有的输入框为非空，且错误提示为0
		if(allValidate()){
			console.log(' validate ');
			$('button.btn').prop('disabled', false);
		}
	    else {
	    	console.log(' invalidate ');
			$('button.btn').prop('disabled', 'disabled');
		}
				
	})
});


function blur() {	
	$("#username").on("focus change keyup keydown blur", usernameValidate);
	$("#password").on("focus change keyup keydown blur", passwordValidate);
};


