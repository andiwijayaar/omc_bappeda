var monthsToString = ['Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
var myDaysToString = ['Minggu', 'Senin', 'Selasa', 'Rabu', 'Kamis', 'Jum&#39;at', 'Sabtu'];

function parseDate(newDate){
	var repDate = newDate.replace(/\-/g,'\/');
	var dateToParsing = new Date(repDate);
	var day = dateToParsing.getDate();
	var month = dateToParsing.getMonth();
	var thisDay = dateToParsing.getDay(),
	    thisDay = myDaysToString[thisDay];
	var yy = dateToParsing.getYear();
	var year = (yy < 1000) ? yy + 1900 : yy;
	
	var res = thisDay + ', ' + day + ' ' + monthsToString[month] + ' ' + year;
	return res;
}

function parseTime(newDate){
	var repDate = newDate.replace(/\-/g,'\/');
	var dateToParsing = new Date(repDate);
	var curr_hour = dateToParsing.getHours();
	var curr_min = dateToParsing.getMinutes();
	var curr_sec = dateToParsing.getSeconds();
	curr_min = curr_min + "";
	if (curr_min.length == 1) {
	    curr_min = "0" + curr_min;
	}
	
	curr_sec = curr_sec + "";
	if (curr_sec.length == 1) {
		curr_sec = "0" + curr_sec;
	}

	var res = curr_hour + " : " + curr_min + " : " + curr_sec; 
	return res;
}