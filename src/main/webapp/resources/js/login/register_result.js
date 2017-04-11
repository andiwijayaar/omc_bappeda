
$(document).ready(function(){
	var loginUrl = '/omc-bappeda/loginRequest';

	$("#btnDone").click(function () {
		$('#loginForm').attr('action', loginUrl);
		document.forms[0].submit();
	});
});
