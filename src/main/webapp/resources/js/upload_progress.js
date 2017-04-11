function growDiv() {
    var growDiv = document.getElementById('collapse');
    if(document.getElementById("btn-bar").className==='min'){
	    if (growDiv.clientHeight) {
	      growDiv.style.height = 0;
	    }
	    document.getElementById("btn-bar").className = 'max';
    
    	document.getElementById("btn-bar").removeChild(document.getElementById("btn-act"));

    	document.getElementById("btn-bar").innerHTML = '<span  id="btn-act">&duarr;</span>';
    }else{
    	document.getElementById("btn-bar").removeChild(document.getElementById("btn-act"));
    	document.getElementById("btn-bar").innerHTML = '<span  id="btn-act">&mdash;</span>';
    	document.getElementById("btn-bar").className = 'min';
    	growDiv.style.height = "200px";

    }
}

function openFile(elemId) {
   var elem = document.getElementById(elemId);
   if(elem && document.createEvent) {
      var evt = document.createEvent("MouseEvents");
      evt.initEvent("click", true, false);
      elem.dispatchEvent(evt);
   }
}

function closeDiv(){
	var closeDiv = document.getElementById('upload_bar');
	closeDiv.style.display = "none";
}