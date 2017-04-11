window.submitForm = function submitForm() {
	var doRegister = '/omc-bappeda/saveNewUser';
	if($("#loginForm")[0].checkValidity()) { 
		if ($("#password").val() !== $("#confirmPassword").val() ) {
			alert("password tidak sama");
			$("#confirmPassword").val('');
			$("#confirmPassword").focus();
		} else {
			$('#departement').val($('#department').val());
			$('#jenisKelamin').val($('#jenis_kelamin').val());
			$('#loginForm').attr('action', doRegister);
			document.getElementById('loginForm').submit();
		}
	}
};