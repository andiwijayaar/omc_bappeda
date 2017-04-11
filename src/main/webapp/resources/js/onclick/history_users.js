$(function() {
	var d = new Date();
	d.setDate(d.getDate() - 7);
	var strDateBefore = d.getFullYear() + "/" + (d.getMonth()+1) + "/" + d.getDate();
	var dateTimeBefore = strDateBefore + " 00:00:00";
	var newDateBefore = new Date(dateTimeBefore);
	
	var n = new Date();
	var strDate = n.getFullYear() + "/" + (n.getMonth()+1) + "/" + n.getDate();
	var dateTime = strDate + " 23:59:59";
	var newDate = new Date(dateTime);
	
    $('#datetimepicker-start-user').datetimepicker({
    	format: 'YYYY-MM-DD HH:mm:ss',
    	locale: moment.locale('id'),
    	useCurrent: false,
    	defaultDate: newDateBefore,
        sideBySide: false,
        keepOpen: false
    });
    $('#datetimepicker-end-user').datetimepicker({
    	format: 'YYYY-MM-DD HH:mm:ss',
    	locale: moment.locale('id'),
    	defaultDate: newDate,
    	useCurrent: false,
        sideBySide: false,
        keepOpen: false//Important! See issue #1075
    });
    
    $("#datetimepicker-start-user").on("dp.change", function (e) {
        $('#datetimepicker-end-user').data("DateTimePicker").minDate(e.date);
        $('#datetimepicker-start-user').data("DateTimePicker").hide();
        get_list_history_users();
    });
    
    $("#datetimepicker-end-user").on("dp.change", function (e) {
        $('#datetimepicker-start-user').data("DateTimePicker").maxDate(e.date);
        $('#datetimepicker-end-user').data("DateTimePicker").hide();
        get_list_history_users();
    });
    
	$('#btnRefreshHistoryUsers').on('click', function() {
		$('.over-table-history-users').show();
	    get_list_history_users();
	});
	
	$('#btnSearchHistoryUsers').on('click', function() {
	    $('#curpageHistoryUsers').val('1');
	    get_list_history_users();
	});
	
	$('#perpageHistoryUsers').on('change', function() {
		get_list_history_users();
	});
	
	$('#btnfirstHistoryUsers').on('click', function() {
	    $('#curpageHistoryUsers').val('1');
	    get_list_history_users();
	});
	$('#btnlastHistoryUsers').on('click', function() {
	    $('#curpageHistoryUsers').val($('#totalsHistoryUsers').html());
	    get_list_history_users();
	});
	
	$('#btnprevHistoryUsers').on('click', function() {
	    var page = $('#curpageHistoryUsers').val();
	    var next = parseInt(page) - 1;
	    $('#curpageHistoryUsers').val(next);
	    get_list_history_users();
	});
	
	$('#btnnextHistoryUsers').on('click', function() {
	    var page = $('#curpageHistoryUsers').val();
	    var next = parseInt(page) + 1;
	    $('#curpageHistoryUsers').val(next);
	    get_list_history_users();
	});
	
	window.get_list_history_users = function () {
		var sd = $('#startDateUser').val();
        var ed = $('#endDateUser').val();
        var lim = $('#perpageHistoryUsers').val();
        var pag = $('#curpageHistoryUsers').val();
        $('.over-table-history-users').show();
	    $.ajax({
	        type: "GET",
	        url: "getDataTableActivityLogByPaging",
	        data: {
	        	fromDate:sd,
            	toDate:ed,
                perPage: lim,
                page: pag,
                isAdmin:"Y",
                id:idxID,
                trash: trash
	        },
	        dataType: 'json',
	        success : function(res) {
	        	console.log(res);
	        	$('#item-list-history-users').html('');
				$('.over-table-history-users').hide();
				
    			if(res.items.length > 0)
    				$('#startHistoryUsers').html(parseInt(res.start)+1);
    			else
    				$('#startHistoryUsers').html(parseInt(res.start));
    			$('#endHistoryUsers').html(parseInt(res.startIndex) + parseInt(res.items.length));
    			$('#totalsHistoryUsers').html(res.totalItems);
    			$('#curpageHistoryUsers').val(res.pageIndex);
    			$('#totalpageHistoryUsers').html(res.totalPage);
				set_pagination_history_user();
				for (var i = 0; i < res.items.length; i++) {
            		$('#item-list-history-users').append(
            			"<tr id=\"item-\">" +
            			"<td class=\"item-code\">"+res.items[i].CreatedDate +"</td>" +
            			"<td class=\"item-code\">"+res.items[i].name +"</td>" +
            			"<td class=\"item-code\">"+res.items[i].activity +"</td>" +
                        "<td class=\"item-code\">" +
                        "<a href='javascript:;' onClick=\"showHistoryUserModal('"+ res.items[i].CreatedDate +"','"+ res.items[i].activity +"','"+ res.items[i].Ip +"','"+ res.items[i].userAgent +"','"+ res.items[i].name +"');\" style='text-decoration:none;'><span class='label label-primary'><i class='fa fa-bars' aria-hidden='true'></i></span></a></td>"
            		);
            	}
	        }
	    });
	}
	
	function set_pagination_history_user(){
        var page = $('#curpageHistoryUsers').val();
        var total = $('#totalpageHistoryUsers').html();
        $('#btnfirstHistoryUsers').removeAttr('disabled');
        $('#btnprevHistoryUsers').removeAttr('disabled');
        $('#btnnextHistoryUsers').removeAttr('disabled');
        $('#btnlastHistoryUsers').removeAttr('disabled');
        if (page <= 1){
            $('#btnfirstHistoryUsers').attr('disabled','disabled');
            $('#btnprevHistoryUsers').attr('disabled','disabled');
        }
        if(parseInt(page) >= parseInt(total)){
            $('#btnnextHistoryUsers').attr('disabled','disabled');
            $('#btnlastHistoryUsers').attr('disabled','disabled');
        }
    }
	
	function truncateModalHistoryUser(){
    	$('#history-user-title').html('');
    	$('#tglHistoryUser').html('');
    	$('#pklHistoryUser').html('');
    	$('#activityHistoryUser').html('');
    	$('#ipHistoryUser').html('');
    	$('#browserHistoryUser').html('');
    	$('#nameHistoryUser').html('');
    }
    
    window.showHistoryUserModal = function(date, activity, ip, browser, by){
    	truncateModalHistoryUser();
    	var newDate = parseDate(date);
    	$("#history_user_modal").modal('show');
    	$('#history-user-title').html('History, ' + newDate);
    	$('#tglHistoryUser').html(newDate);
    	$('#pklHistoryUser').html(parseTime(date));
    	$('#activityHistoryUser').html(activity);
    	$('#ipHistoryUser').html(ip);
    	$('#browserHistoryUser').html(browser);
    	$('#nameHistoryUser').html(by);
    }
});