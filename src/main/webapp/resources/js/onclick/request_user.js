get_list();
	
$('#btnRefreshHome').on('click', function() {
	$('.over-table').show();
    get_list();
});
$('#btnSearchHome').on('click', function() {
    $('#curpageHome').val('1');
    get_list();
});
$('#perpageHome').on('change', function() {
    get_list();
});
$('#state_list').on('change', function() {
    get_list();
});
$('#btnfirstHome').on('click', function() {
    $('#curpageHome').val('1');
    get_list();
});
$('#btnlastHome').on('click', function() {
    $('#curpageHome').val($('#totalpageHome').html());
    get_list();
});
$('#btnprevHome').on('click', function() {
    var page = $('#curpageHome').val();
    var next = parseInt(page) - 1;
    $('#curpageHome').val(next);
    get_list();
});
$('#btnnextHome').on('click', function() {
    var page = $('#curpageHome').val();
    var next = parseInt(page) + 1;
    $('#curpageHome').val(next);
    get_list();
});
$('#approveUserModal').on('click', function() {
    approvalRequest();
});
$('#rejectUserModal').on('click', function() {
	approvalRequestReject();
});

function get_list() {
    var lim = $('#perpageHome').val();
    var pag = $('#curpageHome').val();
    var ky = $("#keywordHome").val();
    $('#start').html('');
    var th = trash;
    var content = '';
    $('.over-table').show();
    $.ajax({
        type: "GET",
        url: "getUserByPaging",
        data: {
            perPage: lim,
            page: pag,
            key: ky,
            trash: th,
            approval: "request"
        },
        dataType: 'json',
        success : function(res) {
        	if(res.access==="denied"){
        		location.reload();
        	}else{
        		$('#item-list').html('');
    			$('.over-table').hide();
    			if(res.items.length > 0)
    				$('#start').html(parseInt(res.start)+1);
    			else
    				$('#start').html(parseInt(res.start));
    			$('#end').html(parseInt(res.startIndex) + parseInt(res.items.length));
    			$('#totalpageHome').html(res.totalPage);
    			$('#curpage').val(res.pageIndex);
    			$('#totals').html(res.totalItems);
    			set_pagination();
            	for (var i = 0; i < res.items.length; i++) {
            		$('#item-list').append(
            			"<tr id=\"item-\">" +
            			"<td class=\"item-code\">"+ res.items[i].Username +"</td>" +
            			"<td class=\"item-code\">"+ res.items[i].Nama +"</td>" +
                        "<td class=\"item-code\">"+ res.items[i].CreatedDate +"</td>" +
                        "<td class=\"item-code\">"+ res.items[i].Email +"</td>" +
                        "<td class=\"item-code\">"+ res.items[i].Departement +"</td>" +
                        "<td class=\"item-code\">"+ res.items[i].Jabatan +"</td>" +
                        "<td class=\"item-code\">" +
                        "<a href='javascript:;' onClick=\"showApprovalModal('"+ res.items[i].ID +"','"+ res.items[i].Departement +"');\" style='text-decoration:none;'><span class='label label-primary'> Terima </span></a>&nbsp;<a href='javascript:;' onClick=\"showApprovalRejectModal('"+ res.items[i].ID +"','"+ res.items[i].Nama +"');\"  style='text-decoration:none;'><span class='label label-danger'>Tolak</span></a></td>"
            		);
            	}
        	}
        	
        },
        error: function(xhr) {
        	location.reload();
        }
    });
}

function truncateApprovalModal(){
	$('#titleApprovalUser').html('');
	$('#IDApprovalUserModal').val('');
	$('#usernameApprovalUserModal').val('');
	$('#nama_lengkapApprovalUserModal').val('');
	$('#nama_lengkapApprovalUserModal').val('');
	$('#jenis_kelaminApprovalUserModal').val('');
	$('#jabatanApprovalUserModal').val('');
	$('#departmentApprovalUserModal').val('');
	$('#emailApprovalUserModal').val('');
	$('#nomor_hpApprovalUserModal').val('');
	$('#IDApprovalUserRejectModal').val('');
}

function truncateApprovalRejectModal(){
	$('#titleApprovalRejectUser').html('');
	$('#IDApprovalUserRejectModal').val('');
	$('#usernameApprovalUserRejectModal').val('');
	$('#preReject').html('');
}

function set_pagination(){
    var page = $('#curpageHome').val();
    var total = $('#totalpageHome').html();
    $('#btnfirstHome').removeAttr('disabled');
    $('#btnprevHome').removeAttr('disabled');
    $('#btnnextHome').removeAttr('disabled');
    $('#btnlastHome').removeAttr('disabled');
    if (page <= 1){
        $('#btnfirstHome').attr('disabled','disabled');
        $('#btnprevHome').attr('disabled','disabled');
    }
    if(page >= total){
        $('#btnnextHome').attr('disabled','disabled');
        $('#btnlastHome').attr('disabled','disabled');
    }
} 

function showApprovalModal(ids, dept){
	truncateApprovalModal();
	var th = trash;
	$('.over-table').show();
	$('#errorMsgApproval').html('');
	$.ajax({
        type: "GET",
        url: "getUserById",
        data: {
            id: ids,
            trash: th
        },
        dataType: 'json',
        success : function(res) {
        	$('#titleApprovalUser').append("Konfirmasi User " + res.items.Nama);
        	$('.over-table').hide();
        	$("#beranda_modal_terima").modal('show');
        	$('#IDApprovalUserModal').val(ids);
        	$('#usernameApprovalUserModal').val(res.items.Username);
        	$('#nama_lengkapApprovalUserModal').val(res.items.Nama);
        	$('#jenis_kelaminApprovalUserModal').val(res.items.Gender).change();;
        	$('#jabatanApprovalUserModal').val(res.items.Jabatan);
        	$('#departmentApprovalUserModal').val(dept);
        	$('#emailApprovalUserModal').val(res.items.Email);
        	$('#nomor_hpApprovalUserModal').val(res.items.Nomor_HP);
        }
    });
}

$('#cancelUserModal').on('click', function(){
	truncateApprovalModal();
	$('#beranda_modal_terima').modal('hide');
	$('#cancelUserModal').removeAttr('disabled','disabled');
	$('#approveUserModal').removeAttr('disabled','disabled');
});

function showApprovalRejectModal(ids, name){
	truncateApprovalRejectModal();
	$('#titleApprovalRejectUser').append("<img src='"+bsURL+"/resources/img/warning.svg'/> Tolak User " + name);
	$('.over-table').hide();
	$("#beranda_modal_tolak").modal('show');
	$('#IDApprovalUserRejectModal').val(ids);
	$('#usernameApprovalUserRejectModal').val(name);
	$('#errorMsgRejectApproval').html('');
	$('#preReject').append("Apakah anda yakin akan menolak user <b>" + name +"</b>?");
}

$('#cancelRejectUserModal').on('click', function(){
	truncateApprovalRejectModal();
	$('#beranda_modal_tolak').modal('hide');
	$('#cancelRejectUserModal').removeAttr('disabled','disabled');
	$('#rejectUserModal').removeAttr('disabled','disabled');
	
});

function approvalRequest(){
	var th = trash;
	var appID = $('#IDApprovalUserModal').val();
	var user = $('#usernameApprovalUserModal').val();
	
	$('#cancelUserModal').attr('disabled','disabled');
	$('#approveUserModal').attr('disabled','disabled');
	$.ajax({
        type: "GET",
        url: "updateUserById",
        data: {
        	adminId: idxID,
        	ID: appID,
            Approval:"approve",
            trash:th
            
        },
        dataType: 'json',
        success : function(res) {
        	if(res.message === "200"){
        		get_list();
            	$('#beranda_modal_terima').modal('hide');
            	$('#cancelUserModal').removeAttr('disabled','disabled');
            	$('#approveUserModal').removeAttr('disabled','disabled');
            	truncateApprovalModal();
            	effectSuccess();
        	}else{
        		$('#errorMsgApproval').append('<div class="alert alert-danger"><a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a><strong>Error</strong> Ulangi beberapa saat lagi</div>');
        		$('#cancelUserModal').removeAttr('disabled','disabled');
        		effectError();
        	}
        }
    });
}

function approvalRequestReject(){
	var th = trash;
	var appID = $('#IDApprovalUserRejectModal').val();
	var user = $('#usernameApprovalUserRejectModal').val();
	$('#cancelRejectUserModal').attr('disabled','disabled');
	$('#rejectUserModal').attr('disabled','disabled');
	$.ajax({
        type: "GET",
        url: "updateUserById",
        data: {
        	adminId: idxID,
        	ID: appID,
            Delete:"delete",
            Approval:"reject",
            trash:th
        },
        dataType: 'json',
        success : function(res) {
        	if(res.message === "200"){
        		get_list();
            	$('#beranda_modal_tolak').modal('hide');
            	$('#cancelRejectUserModal').removeAttr('disabled','disabled');
            	$('#rejectUserModal').removeAttr('disabled','disabled');
            	truncateApprovalRejectModal();
            	effectSuccess();
        	}else{
        		$('#errorMsgRejectApproval').append('<div class="alert alert-danger"><a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a><strong>Error</strong> Ulangi beberapa saat lagi</div>');
        		$('#cancelRejectUserModal').removeAttr('disabled','disabled');
        		effectError();
        	}
        }
    });
}

