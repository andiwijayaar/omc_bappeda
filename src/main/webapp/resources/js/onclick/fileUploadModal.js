$('#closeUploadModal').on('click', function() {

});

$('#addFolderForm').submit(function () {
//	sendContactForm();
	return false;
});

function openBrowse(elemId) {
   var elem = document.getElementById(elemId);
   if(elem && document.createEvent) {
      var evt = document.createEvent("MouseEvents");
      evt.initEvent("click", true, false);
      elem.dispatchEvent(evt);
   }
}

function showSticky(){
	$("#upload_bar").show();
}