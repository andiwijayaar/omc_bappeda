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
	
    $('#datetimepicker-start').datetimepicker({
    	format: 'YYYY-MM-DD HH:mm:ss',
    	locale: moment.locale('id'),
    	useCurrent: false,
    	defaultDate: newDateBefore,
        sideBySide: false,
        keepOpen: false
    });
    $('#datetimepicker-end').datetimepicker({
    	format: 'YYYY-MM-DD HH:mm:ss',
    	locale: moment.locale('id'),
    	defaultDate: newDate,
    	useCurrent: false,
        sideBySide: false,
        keepOpen: false//Important! See issue #1075
    });
    
    $("#datetimepicker-start").on("dp.change", function (e) {
        $('#datetimepicker-end').data("DateTimePicker").minDate(e.date);
        $('#datetimepicker-start').data("DateTimePicker").hide();
        get_list_my_history();
    });
    
    $("#datetimepicker-end").on("dp.change", function (e) {
        $('#datetimepicker-start').data("DateTimePicker").maxDate(e.date);
        $('#datetimepicker-end').data("DateTimePicker").hide();
        get_list_my_history();
    });
    
    $('#btnRefreshHistoryAdmin').on('click', function() {
    	$('.over-table-history-admin').show();
        get_list_my_history();
    });

    $('#btnSearchHistoryAdmin').on('click', function() {
        $('#curpageHistoryAdmin').val('1');
        get_list_my_history();
    });

    $('#perpageHistoryAdmin').on('change', function() {
    	get_list_my_history();
    });

    $('#btnfirstHistoryAdmin').on('click', function() {
        $('#curpageHistoryAdmin').val('1');
        get_list_my_history();
    });
    $('#btnlastHistoryAdmin').on('click', function() {
        $('#curpageHistoryAdmin').val($('#totalpageHistoryAdmin').html());
        get_list_my_history();
    });

    $('#btnprevHistoryAdmin').on('click', function() {
        var page = $('#curpageHistoryAdmin').val();
        var next = parseInt(page) - 1;
        $('#curpageHistoryAdmin').val(next);
        get_list_my_history();
    });

    $('#btnnextHistoryAdmin').on('click', function() {
        var page = $('#curpageHistoryAdmin').val();
        var next = parseInt(page) + 1;
        $('#curpageHistoryAdmin').val(next);
        get_list_my_history();
    });

    window.get_list_my_history = function(){
        var sd = $('#startDate').val();
        var ed = $('#endDate').val();
        var lim = $('#perpageHistoryAdmin').val();
        var pag = $('#curpageHistoryAdmin').val();
        $('.over-table-history-admin').show();
        $.ajax({
            type: "GET",
            url: "getDataTableActivityLogByPaging",
            data: {
            	fromDate:sd,
            	toDate:ed,
                perPage: lim,
                page: pag,
                id:idxID,
                trash: trash
            },
            dataType: 'json',
            success : function(res) {
            	$('#item-list-history-admin').html('');
    			$('.over-table-history-admin').hide();
    			if(res.items.length > 0)
    				$('#startHistoryAdmin').html(parseInt(res.start)+1);
    			else
    				$('#startHistoryAdmin').html(parseInt(res.start));
    			$('#endHistoryAdmin').html(parseInt(res.startIndex) + parseInt(res.items.length));
    			$('#totalsHistoryAdmin').html(res.totalItems);
    			$('#curpageHistoryAdmin').val(res.pageIndex);
    			$('#totalpageHistoryAdmin').html(res.totalPage);
    			set_pagination_history_admin();
            	for (var i = 0; i < res.items.length; i++) {
            		$('#item-list-history-admin').append(
            			"<tr id=\"item-\">" +
            			"<td class=\"item-code\">"+res.items[i].CreatedDate +"</td>" +
            			"<td class=\"item-code\">"+res.items[i].activity +"</td>" +
                        "<td class=\"item-code\">" +
                        "<a href='javascript:;' onClick=\"showMyHistoryModal('"+ res.items[i].CreatedDate +"','"+ res.items[i].activity +"','"+ res.items[i].Ip +"','"+ res.items[i].userAgent +"');\" style='text-decoration:none;'><span class='label label-primary'><i class='fa fa-bars' aria-hidden='true'></i></span></a></td>"
            		);
            	}
            }
        });
    }

    function set_pagination_history_admin(){
        var page = $('#curpageHistoryAdmin').val();
        var total = $('#totalpageHistoryAdmin').html();
        $('#btnfirstHistoryAdmin').removeAttr('disabled');
        $('#btnprevHistoryAdmin').removeAttr('disabled');
        $('#btnnextHistoryAdmin').removeAttr('disabled');
        $('#btnlastHistoryAdmin').removeAttr('disabled');
        if (page <= 1){
            $('#btnfirstHistoryAdmin').attr('disabled','disabled');
            $('#btnprevHistoryAdmin').attr('disabled','disabled');
        }
        if(parseInt(page) >= parseInt(total)){
            $('#btnnextHistoryAdmin').attr('disabled','disabled');
            $('#btnlastHistoryAdmin').attr('disabled','disabled');
        }
    }
  
    function truncateModalMyHistory(){
    	$('#my-history-title').html('');
    	$('#tglMyHistory').html('');
    	$('#pkl').html('');
    	$('#activityMyHistory').html('');
    	$('#ipMyHistory').html('');
    	$('#browserMyHistory').html('');
    }
    
    window.showMyHistoryModal = function(date, activity, ip, browser){
    	truncateModalMyHistory();
    	var newDate = parseDate(date);
    	$("#my_history_modal").modal('show');
    	$('#my-history-title').html('History Saya, ' + newDate);
    	$('#tglMyHistory').html(newDate);
    	$('#pkl').html(parseTime(date));
    	$('#activityMyHistory').html(activity);
    	$('#ipMyHistory').html(ip);
    	$('#browserMyHistory').html(browser);
    }
});

