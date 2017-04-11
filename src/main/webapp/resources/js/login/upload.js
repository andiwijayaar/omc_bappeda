window.submitForm = function submitForm() {
	alert("masuk coy")
	var doUpload = '/omc-bappeda/upload2';
	$('#loginForm').attr('action', doUpload);
	document.getElementById('loginForm').submit();
};