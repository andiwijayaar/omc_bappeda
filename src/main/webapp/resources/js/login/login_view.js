window.submitForm = function submitForm() {
	var doLoginUrl1 = '/omc-bappeda/login';
	if($("#login_form")[0].checkValidity()) { 
		$('#errorMsg').show();
		$('#login_form').attr('action', doLoginUrl1);
		document.getElementById('login_form').submit();
	}
};