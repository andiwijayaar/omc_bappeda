$('#formSettingAccount').submit(function () {
	if ($("#passwordSettingAccount").val() !== $("#confSettingAccount").val() ) {
		alert("password tidak sama");
		$("#confSettingAccount").val('');
		$("#confSettingAccount").focus();
		return false;
	}else{
		setting_account();
	}
});

//$('#saveSettingAccount').on('click', function() {
//	
//});

function setting_account(){
	var nama_users = $('#fullnameSettingAccount').val();
	var email_users = $('#emailSettingAccount').val();
	var hp_users = $('#hpSettingAccount').val();
	var jabatan_users = $('#jabatanSettingAccount').val();
	var deptart_users = $('#departmentSettingAccount').val();
	var pass_users = $('#passwordSettingAccount').val();
	$.ajax({
        type: "GET",
        url: "updateUserById",
        data: {
        	trash: trash,
            ID: idxID,
            adminId: idxID,
            Nama: nama_users, 
            Email: email_users,
            Nomor_HP: hp_users,
            Jabatan: jabatan_users,
            Departement: deptart_users,
            Approval: "edit",
            password:pass_users
        },
        dataType: 'json',
        success : function(res) {
        	if(res.message === "200"){
        		alert("Login ulang untuk mendapatkan update terbaru data diri anda.");
        		effectSuccess();
        	}else{
        		effectError();
        	}
        }
	});
}