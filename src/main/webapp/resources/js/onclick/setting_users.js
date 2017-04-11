$('#btnRefreshSettingUser').on('click', function() {
	$('.over-table').show();
    get_list_setting_users();
});
$('#btnSearchSettingUser').on('click', function() {
    $('#curpageSettingUser').val('1');
    get_list_setting_users();
});
$('#perpageSettingUser').on('change', function() {
	get_list_setting_users();
});
$('#state_list').on('change', function() {
    get_list_setting_users();
});
$('#btnfirstSettingUser').on('click', function() {
    $('#curpageSettingUser').val('1');
    get_list_setting_users();
});
$('#btnlastSettingUser').on('click', function() {
    $('#curpageSettingUser').val($('#totalpage').html());
    get_list_setting_users();
});
$('#btnprevSettingUser').on('click', function() {
    var page = $('#curpageSettingUser').val();
    var next = parseInt(page) - 1;
    $('#curpageSettingUser').val(next);
    get_list_setting_users();
});
$('#btnnextSettingUser').on('click', function() {
    var page = $('#curpageSettingUser').val();
    var next = parseInt(page) + 1;
    $('#curpageSettingUser').val(next);
    get_list_setting_users();
});

$('#saveSettingUserModal').on('click', function() {
	if ($("#passwordSettingUserModal").val() !== $("#conpasswordSettingUserModal").val() ) {
		alert("password tidak sama");
		$("#conpasswordSettingUserModal").val('');
		$("#conpasswordSettingUserModal").focus();
	} else {
		update_users_setting("");
	}
});



function get_list_setting_users() {
    var lim = $('#perpageSettingUser').val();
    var pag = $('#curpageSettingUser').val();
    var ky = $("#keywordSettingUser").val();
    var th = trash;
    var content = '';
    $('.over-table-user').show();
    $.ajax({
        type: "GET",
        url: "getUserByPaging",
        data: {
            perPage: lim,
            page: pag,
            key: ky,
            trash: th,
            approval: "approve"
        },
        dataType: 'json',
        success : function(res) {
        	$('#item-list-setting-users').html('');
			$('.over-table-user').hide();
			
			if(res.items.length > 0)
				$('#startSettingUser').html(parseInt(res.start)+1);
			else
				$('#startSettingUser').html(parseInt(res.start));
			
			$('#endSettingUser').html(parseInt(res.startIndex) + parseInt(res.items.length));
			$('#totalpageSettingUser').html(res.totalPage);
			$('#curpageSettingUser').val(res.pageIndex);
			$('#totalsSettingUser').html(res.totalItems);
			
			set_pagination_setting_user();
        	for (var i = 0; i < res.items.length; i++) {
        		$('#item-list-setting-users').append(
        			"<tr id=\"item-\">" +
        			"<td class=\"item-code\">"+res.items[i].Username +"</td>" +
        			"<td class=\"item-code\">"+res.items[i].Nama +"</td>" +
        			"<td class=\"item-code\">"+res.items[i].Jabatan +"</td>" +
                    "<td class=\"item-code\">"+res.items[i].Departement +"</td>" +
                    "<td class=\"item-code\">"+res.items[i].Email +"</td>" +
                    "<td class=\"item-code\">"+res.items[i].Nomor_HP +"</td>" +
                    "<td class=\"item-code\">" +
                    "<a href='javascript:;' onClick=\"showSettingUsersModal('"+ res.items[i].ID +"');\" style='text-decoration:none;'><span class='label label-primary'> Edit </span></a>&nbsp;<a href='javascript:;' onClick=\"showDeleteSettingUsersModal('"+ res.items[i].ID +"', '"+ res.items[i].Username +"');\"  style='text-decoration:none;'><span class='label label-danger'>Delete</span></a></td></tr>"
        		);
        	}
        }
    });
}

function set_pagination_setting_user(){
    var page = $('#curpageSettingUser').val();
    var total = $('#totalpageSettingUser').html();
    $('#btnfirstSettingUser').removeAttr('disabled');
    $('#btnprevSettingUser').removeAttr('disabled');
    $('#btnnextSettingUser').removeAttr('disabled');
    $('#btnlastSettingUser').removeAttr('disabled');
    if (page <= 1){
        $('#btnfirstSettingUser').attr('disabled','disabled');
        $('#btnprevSettingUser').attr('disabled','disabled');
    }
    if(page >= total){
        $('#btnnextSettingUser').attr('disabled','disabled');
        $('#btnlastSettingUser').attr('disabled','disabled');
    }
}

function truncateAllFieldEditSettingUsers(){
	$('#titleSettingUser').html('');
	$('#IDSettingUserModal').val('');
	$('#usernameSettingUserModal').val('');
	$('#nama_lengkapSettingUserModal').val('');
	$('#passwordSettingUserModal').val('');
	$('#conpasswordSettingUserModal').val('');
	$('#nama_lengkapSettingUserModal').val('');
	$('#jenis_kelaminSettingUserModal').val('');
	$('#jabatanSettingUserModal').val('');
	$('#departmentSettingUserModal').val('');
	$('#emailSettingUserModal').val('');
	$('#nomor_hpSettingUserModal').val('');
	$('#aksesSettingUserModal').val('');
	
}

function truncateModalDeleteSettingsUsers(){
	$('#titleDeleteModalSettingsUsers').html('');
	$('#IDUserDeleteModalSettingsUser').val('');
	$('#usernameDeleteModalSettingsUser').val('');
	$('#errorMsgDeleteModalSettingsUsers').html('');
	$('#preDeleteModalSettingsUsers').html('');
	$('#gender_usersDeleteModalSettingsUser').val('');
	$('#email_usersDeleteModalSettingsUser').val('');
	$('#hp_usersDeleteModalSettingsUser').val('');
	$('#jabatan_usersDeleteModalSettingsUser').val('');
	$('#deptart_usersDeleteModalSettingsUser').val('');
	$('#role_usersDeleteModalSettingsUser').val('');
	$('#pass_usersDeleteModalSettingsUser').val('');
}

function showDeleteSettingUsersModal(ids, name){
	truncateModalDeleteSettingsUsers();
	$('#titleDeleteModalSettingsUsers').append("<img src='"+bsURL+"/resources/img/warning.svg'/> Hapus User " + name);
	$('#errorMsgDeleteModalSettingsUsers').html('');
	$('#IDUserDeleteModalSettingsUser').val(ids);
	$('#usernameDeleteModalSettingsUser').val(name);
	$('#preDeleteModalSettingsUsers').append("Apakah anda yakin akan menghapus user <b>" + name +"</b>?");
	var th = trash;
	$('.over-table-user').show();
	$.ajax({
        type: "GET",
        url: "getUserById",
        data: {
            id: ids,
            trash: th
        },
        dataType: 'json',
        success : function(res) {
        	$('.over-table-user').hide();
        	$("#delete_modal_setting_user").modal('show');
        	$('#gender_usersDeleteModalSettingsUser').val(res.items.Gender);
        	$('#email_usersDeleteModalSettingsUser').val(res.items.Email);
        	$('#hp_usersDeleteModalSettingsUser').val(res.items.Nomor_HP);
        	$('#jabatan_usersDeleteModalSettingsUser').val(res.items.Jabatan);
        	$('#deptart_usersDeleteModalSettingsUser').val(res.items.Departement);
        	$('#role_usersDeleteModalSettingsUser').val(res.items.Role);
        	$('#pass_usersDeleteModalSettingsUser').val('');
        }
    });
}

function showSettingUsersModal(ids){
	truncateAllFieldEditSettingUsers();
	var th = trash;
	$('.over-table-user').show();
	$.ajax({
        type: "GET",
        url: "getUserById",
        data: {
            id: ids,
            trash: th
        },
        dataType: 'json',
        success : function(res) {
        	$('#titleSettingUser').append("Edit User " + res.items.Nama);
        	$('.over-table-user').hide();
        	$("#edit_modal_setting_user").modal('show');
        	$('#IDSettingUserModal').val(ids);
        	$('#usernameSettingUserModal').val(res.items.Username);
        	$('#nama_lengkapSettingUserModal').val(res.items.Nama);
        	$('#passwordSettingUserModal').val('');
        	$('#conpasswordSettingUserModal').val('');
        	$('#jenis_kelaminSettingUserModal').val(res.items.Gender).change();;
        	$('#jabatanSettingUserModal').val(res.items.Jabatan);
        	$('#departmentSettingUserModal').val(res.items.Departement);
        	$('#emailSettingUserModal').val(res.items.Email);
        	$('#nomor_hpSettingUserModal').val(res.items.Nomor_HP);
        	$('#aksesSettingUserModal').val(res.items.Role);
        	$('#isAdminSettingUserModal').val(res.items.Is_Admin).change();
        }
    });
}

$('#cancelSettingUserModal').on('click', function(){
	truncateAllFieldEditSettingUsers();
	$('#edit_modal_setting_user').modal('hide');
});

$('#cancelDeleteModalSettingsUser').on('click', function(){
	truncateModalDeleteSettingsUsers();
	$('#delete_modal_setting_user').modal('hide');
});

$('#deleteModalSettingsUser').on('click', function(){
	delete_users_setting();
	$('#delete_modal_setting_user').modal('hide');
});

function update_users_setting(){
	var idUsers = $('#IDSettingUserModal').val();
	var nama_users = $('#nama_lengkapSettingUserModal').val();
	var gender_users = $('#jenis_kelaminSettingUserModal').val();
	var email_users = $('#emailSettingUserModal').val();
	var hp_users = $('#nomor_hpSettingUserModal').val();
	var jabatan_users = $('#jabatanSettingUserModal').val();
	var deptart_users = $('#departmentSettingUserModal').val();
	var role_users = $('#aksesSettingUserModal').val();
	var pass_users = $('#passwordSettingUserModal').val();
	var isadmin = $('#isAdminSettingUserModal').val();
	$.ajax({
        type: "GET",
        url: "updateUserById",
        data: {
            trash: trash,
            ID: idUsers,
            adminId: idxID,
            Nama: nama_users, 
            Gender: gender_users,
            Email: email_users,
            Nomor_HP: hp_users,
            Jabatan: jabatan_users,
            Departement: deptart_users,
            Approval: "edit",
            password:pass_users,
            isAdmin: isadmin
        },
        dataType: 'json',
        success : function(res) {
        	if(res.message === "200"){
        		get_list_setting_users();
            	$("#edit_modal_setting_user").modal('hide');
            	effectSuccess();
        	}else{
        		effectError();
        	}
    		
        }
    });
}

function delete_users_setting(){
	var idUsers = $('#IDUserDeleteModalSettingsUser').val();
	var nama_users = $('#usernameDeleteModalSettingsUser').val();
	var gender_users = $('#gender_usersDeleteModalSettingsUser').val();
	var email_users = $('#email_usersDeleteModalSettingsUser').val();
	var hp_users = $('#hp_usersDeleteModalSettingsUser').val();
	var jabatan_users = $('#jabatan_usersDeleteModalSettingsUser').val();
	var deptart_users = $('#deptart_usersDeleteModalSettingsUser').val();
	var role_users = $('#role_usersDeleteModalSettingsUser').val();
	var pass_users = $('#pass_usersDeleteModalSettingsUser').val();
	var activedUser = "N";
	$.ajax({
        type: "GET",
        url: "updateUserById",
        data: {
            trash: trash,
            ID: idUsers,
            adminId: idxID,
            Nama: nama_users, 
            Gender: gender_users,
            Email: email_users,
            Nomor_HP: hp_users,
            Jabatan: jabatan_users,
            Departement: deptart_users,
            password:pass_users ,
            Delete: "delete",
            Approval:"yes"
        },
        dataType: 'json',
        success : function(res) {
        	if(res.message === "200"){
        		get_list_setting_users();
            	$("#delete_modal_setting_user").modal('hide');
            	effectSuccess();
        	}else{
        		get_list_setting_users();
            	$("#delete_modal_setting_user").modal('hide');
        		effectError();
        	}
    		
        }
    });
}