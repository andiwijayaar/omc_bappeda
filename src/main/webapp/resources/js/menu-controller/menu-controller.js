function menu_controller(id, selector){
	var elements = document.querySelectorAll('a[id^="menus_"]');
	var select = document.querySelectorAll('a[id*="'+selector+'"]');
	for(var i=0; i< elements.length; i++) {
		$('#'+ elements[i].id).removeClass('active-menu');
	}

	for(var i=0; i< select.length; i++) {
		$('#'+ select[i].id).addClass('active-menu');
	}
	
	if(adv === "Y"){
		show_hidden_content_menu(selector);
	}else{
		if(select[0].id === "menus_home_sm" && select[1].id === "menus_home"){
			selector = "menus_folders";
			show_hidden_content_menu(selector);
		}else{
			show_hidden_content_menu(selector);
		}
		
	}
}

function show_hidden_content_menu(e){
	var select = document.querySelectorAll('div[id^="content_'+e+'"]');
	var selectHidden = document.querySelectorAll('div[id^="content_menus_"]');

	for(var i=0; i< selectHidden.length; i++) {
		$('#'+ selectHidden[i].id).addClass('display-menu-active');
	}

	for(var i=0; i< select.length; i++) {
		$('#'+ select[i].id).removeClass('display-menu-active');
	}
}