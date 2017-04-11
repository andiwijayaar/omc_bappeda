		<div class="footer">
			<div class="pos-right">Proudly present by Brogrammers IT Support.</div>
			<br/>
			<div class="pos-left">Copyright &copy; 2016 Bappeda Provinsi Sumatera Selatan</div>
			<br/>
			<div class="pos-left">Jl. Kapten A. Rivai No.23 Palembang</div>
			<br/>
			<div class="pos-left">Telp. (0711) 30129, 356118, 356018 Fax. (0711) 356118</div>
			<br/>
			<div class="pos-left">Email : <a href="mailto:info@bappeda.sumselprov.go.id?Subject=" target="_top">info@bappeda.sumselprov.go.id</a></div>
		</div>
	</body>
	 <script>
		function effectHide(){
			$("#globalAlertSuccess" ).hide();
			$("#globalAlertError" ).hide();
		}
		
		function effectSuccess(){
			$("#globalAlertSuccess").fadeIn();
			callback();
		}
		
		function effectError(){
			$("#globalAlertError").fadeIn();
			callbackError();
		}

		function callback() {
		    setTimeout(function() {
	          $( "#globalAlertSuccess" ).fadeOut();
	        }, 3000 );
		};
		
		function callbackError() {
		    setTimeout(function() {
	          $( "#globalAlertError" ).fadeOut();
	        }, 3000 );
		};
		
	 	jQuery(document).ready(function () {
	 		$.reject({  
	 	        reject: {  
	 	            safari: false, // Apple Safari  
	 	            chrome: false, // Google Chrome  
	 	            firefox: false, // Mozilla Firefox  
	 	            msie: true, // Microsoft Internet Explorer  
	 	            opera: false, // Opera  
	 	            konqueror: false, // Konqueror (Linux)  
	 	            unknown: true // Everything else  
	 	        }  
	 	    }); // Customized Browsers  
	 	  
	 	    return false;  
	 	});
	 </script>
</html>