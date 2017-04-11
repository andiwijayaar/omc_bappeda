$('#btnRefreshDept').on('click', function() {
	$('.over-table').show();
    get_list_setting_departement();
});
$('#btnSearchDept').on('click', function() {
    $('#curpageDept').val('1');
    get_list_setting_departement();
});
$('#perpageDept').on('change', function() {
	get_list_setting_departement();
});
$('#state_list').on('change', function() {
    get_list_setting_departement();
});
$('#btnfirstDept').on('click', function() {
    $('#curpageDept').val('1');
    get_list_setting_departement();
});
$('#btnlastDept').on('click', function() {
    $('#curpageDept').val($('#totalpageDept').html());
    get_list_setting_departement();
});
$('#btnprevDept').on('click', function() {
    var page = $('#curpageDept').val();
    var next = parseInt(page) - 1;
    $('#curpageDept').val(next);
    get_list_setting_departement();
});
$('#btnnextDept').on('click', function() {
    var page = $('#curpageDept').val();
    var next = parseInt(page) + 1;
    $('#curpageDept').val(next);
    get_list_setting_departement();
});

$('#addDepartmentForm').submit(function () {
	return false;
});

$('#saveSettingDepartmentModal').on('click', function() {
	var departementKode = $('#IDSettingDepartmentModal').val();
	var departementName = $('#edit_departement_name').val();
	if(departementKode !== "" && departementName !== ""){
		edit_departement(departementKode, departementName);
	}
});


$('#addDepartment').on('click', function() {
	var departementKode = $('#departement_kode').val();
	var departementName = $('#departement_name').val();
	if(departementKode !== "" && departementName !== ""){
		add_departement(departementKode, departementName);
	}
});

$('#deleteModalSettingsDepartment').on('click', function() {
	var departementKode = $('#codeDeleteModalSettingsDepartment').val();
	var departementName = $('#nameDeleteModalSettingsDepartment').val();
	if(departementKode !== "" && departementName !== ""){
		deleteDepartement(departementKode, departementName);
	}
});

function get_list_setting_departement() {
    var lim = $('#perpageDept').val();
    var pag = $('#curpageDept').val();
    var ky = $("#keywordDept").val();
    var th = trash;
    var content = '';
    $('.over-table-dept').show();
    $.ajax({
        type: "GET",
        url: "getDataTableDepartementByPaging",
        data: {
            perPage: lim,
            page: pag,
            key: ky,
            trash: th
        },
        dataType: 'json',
        success : function(res) {
        	console.log();
        	$('#item-list-setting-department').html('');
			$('.over-table-dept').hide();
			
			if(res.items.length > 0)
				$('#startDept').html(parseInt(res.start)+1);
			else
				$('#startDept').html(parseInt(res.start));
			
			$('#endDept').html(parseInt(res.startIndex) + parseInt(res.items.length));
			$('#totalpageDept').html(res.totalPage);
			$('#curpageDept').val(res.pageIndex);
			$('#totalsDept').html(res.totalItems);
			
			set_pagination_departement();
        	for (var i = 0; i < res.items.length; i++) {
        		$('#item-list-setting-department').append(
        			"<tr id=\"item-\">" +
        			"<td class=\"item-code\">"+res.items[i].Kode +"</td>" +
        			"<td class=\"item-code\">"+res.items[i].Nama +"</td>" +
                    "<td class=\"item-code\">" +
                    "<a href='javascript:;' onClick=\"showSettingDepartmentModal('"+ res.items[i].Kode +"', '"+ res.items[i].Nama +"');\" style='text-decoration:none;'><span class='label label-primary'> Edit </span></a>&nbsp;<a href='javascript:;' onClick=\"showDeleteDepartmentModal('"+ res.items[i].Kode +"', '"+ res.items[i].Nama +"');\"  style='text-decoration:none;'><span class='label label-danger'>Delete</span></a></td>"
        		);
        	}
        }
    });
}
function set_pagination_departement(){
    var page = $('#curpageDept').val();
    var total = $('#totalpageDept').html();
    $('#btnfirstDept').removeAttr('disabled');
    $('#btnprevDept').removeAttr('disabled');
    $('#btnnextDept').removeAttr('disabled');
    $('#btnlastDept').removeAttr('disabled');
    if (page <= 1){
        $('#btnfirstDept').attr('disabled','disabled');
        $('#btnprevDept').attr('disabled','disabled');
    }
    if(page >= total){
        $('#btnnextDept').attr('disabled','disabled');
        $('#btnlastDept').attr('disabled','disabled');
    }
} 

function truncateAllFieldEditSettingDepartment(){
	$('#titleSettingDepartment').html('');
	$('#IDSettingDepartmentModal').val('');
	$('#edit_departement_name').val('');
}


function showSettingDepartmentModal(ids, name){
	truncateAllFieldEditSettingDepartment();
	$('#titleSettingDepartment').append("Edit Department " + name);
	$('.over-table').hide();
	$("#edit_modal_dept").modal('show');
	$('#IDSettingDepartmentModal').val(ids);
	$('#edit_departement_name').val(name);
}

function showDeleteDepartmentModal(ids, name){
	truncateAllFieldDeleteSettingDepartment();
	$('#titleDeleteModalSettingsDepartment').append("<img src='"+bsURL+"/resources/img/warning.svg'/> Hapus Departemen " + name);
	$('#errorMsgDeleteModalSettingsDepartment').html('');
	$('#codeDeleteModalSettingsDepartment').val(ids);
	$('#nameDeleteModalSettingsDepartment').val(name);
	$('#preDeleteModalSettingsDepartment').append("Apakah anda yakin akan menghapus departemen <b>"+ids+" - " + name +"</b>?");
	$("#delete_modal_setting_department").modal('show');
}

function truncateAllFieldDeleteSettingDepartment(){
	$('#titleDeleteModalSettingsDepartment').html("");
	$('#errorMsgDeleteModalSettingsDepartment').html('');
	$('#codeDeleteModalSettingsDepartment').val("");
	$('#nameDeleteModalSettingsDepartment').val("");
	$('#preDeleteModalSettingsDepartment').html("");
}

$('#cancelSettingDepartmentModal').on('click', function(){
	truncateAllFieldEditSettingDepartment();
	$('#edit_modal_dept').modal('hide');
});

function add_departement(departementKode, departementName){
	$('.over-table-dept').show();
	$('#addDepartment').attr('disabled','disabled');
    $.ajax({
        type: "GET",
        url: "saveDept",
        data: {
        	Kode:departementKode, 
        	Nama:departementName, 
        	ID_User:idxID, 
            trash: trash
        },
        dataType: 'json',
        success : function(res) {
			$('.over-table-dept').hide();
			if(res.status === "200"){
				effectSuccess();
				$('#departement_kode').val('');
				$('#departement_name').val('');
			}else if(res.status === "201"){
				effectError();
				$('#departement_kode').val('');
				$('#departement_name').val('');
			}else{
				effectError();
			}
			get_list_setting_departement();
			$('#addDepartment').removeAttr('disabled');
			
        }
    });
}

function edit_departement(departementKode, departementName){
    $.ajax({
        type: "GET",
        url: "updateDeptById",
        data: {
        	Kode:departementKode, 
        	Nama:departementName, 
        	ID_User:idxID, 
            trash: trash
        },
        dataType: 'json',
        success : function(res) {
			if(res.status === "200"){
				$('#edit_modal_dept').modal("hide");
				$('.over-table-dept').hide();
				$('#IDSettingDepartmentModal').val('');
				$('#edit_departement_name').val('');
				effectSuccess();
			}else if(res.status === "201"){
				$('#edit_modal_dept').modal("hide");
				$('#IDSettingDepartmentModal').val('');
				$('#edit_departement_name').val('');
				effectError();
			}else{
				$('#edit_modal_dept').modal("hide");
				effectError();
			}
			get_list_setting_departement();
        }
    });
}

function deleteDepartement(departementKode, departementName){
	$.ajax({
        type: "GET",
        url: "updateDeptById",
        data: {
        	Kode:departementKode, 
        	Nama:departementName, 
        	ID_User:idxID, 
        	Delete: "delete",
            trash: trash
        },
        dataType: 'json',
        success : function(res) {
			if(res.status === "200"){
				$('#delete_modal_setting_department').modal("hide");
				$('.over-table-dept').hide();
				$('#codeDeleteModalSettingsDepartment').val('');
				$('#nameDeleteModalSettingsDepartment').val('');
				effectSuccess();
			}else if(res.status === "201"){
				$('#delete_modal_setting_department').modal("hide");
				$('#codeDeleteModalSettingsDepartment').val('');
				$('#nameDeleteModalSettingsDepartment').val('');
				effectError();
			}else{
				$('#delete_modal_setting_department').modal("hide");
				effectError();
			}
			get_list_setting_departement();
        }
    });
}