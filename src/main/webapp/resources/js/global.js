$(document).ready(function(){
	window.onbeforeunload = function() {
		
		/**
		 * NOTED :
		 * 
		 * in Firefox and Netscape 7.2+ the setTimeout or setInterval do not wait
		   to be executed until after the user clicks one of the buttons in the
		   confirm()-like box.

		   setTimeout("alert('hi from setTimeout()');",500);
		   setTimeout() and setInterval() aren't called when ok is clicked in
		   IE5-6/Win, but is called in IE7 when the time is short, but not when
		   it's longer, like 500 (a half second).
		 * 
		 * */
		   
		  window.unloadTimer = setInterval(500);
		  window.onunload = function() {
			  clearInterval(window.unloadTimer);
		  }
		  return 'onbeforeunload testing';
		}
});

//console.log(''+ ${userSession}+'');