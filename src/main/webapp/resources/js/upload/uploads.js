var fileCount = 0;
var filesUploaded;
var percentComplete;
var fileLengthVar = [];

function onUploadComplete(e) {
	if (filesUploaded < fileCount-1) {
		percentComplete = 0;
		if(e.currentTarget.response == 200){
			$('#status-upload-items-' + filesUploaded)
					.append(
							"<a href='#'><img src='"+bsURL+"/resources/img/tick.svg'></a>");
			$("#items-progress-bar-" + filesUploaded)
					.toggleClass('progress-bar-info progress-bar-success');
		}
		filesUploaded++;
		uploadNext("files");
	}else{
		if(e.currentTarget.response == 200){
			$('#status-upload-items-' + filesUploaded)
					.append(
							"<a href='#'><img src='"+bsURL+"/resources/img/tick.svg'></a>");
			$("#items-progress-bar-" + filesUploaded)
					.toggleClass('progress-bar-info progress-bar-success');
		
			get_list_file_and_directory($('#parent').val());
			
			$("#filesUploads").val("");
        	document.getElementById('filesUploads').removeAttribute("disabled", false);
        	document.getElementById('uploadButton').removeAttribute("disabled", false);
        	document.getElementById('btnUploadFileNav').removeAttribute("disabled", false);
		}
	}
}

function onFileSelect(e) {
	$("#collapse").html("");
	showSticky();
	var files = e.target.files; // FileList object
	var output = [];
	fileCount = files.length;
	
	for (var i = 0; i < fileCount; i++) {
		var file = files[i];
		fileLengthVar[i] = file.size;
		
		var objDiv = document.getElementById("collapse");
		objDiv.scrollTop = objDiv.scrollHeight;

		$("#collapse").append(
			"<div class='list' id='items-"+ (i)+ "'>"
				+ "<div class='progress'>"
					+ "<div class='progress-bar progress-bar-info progress-bar-striped active' id='items-progress-bar-"+ (i)+ "' role='progressbar' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100' style='width:0%'></div>"
						+ "<span>"+ file.name+ "</span>"
					+ "</div>"
					+ "<div class='status-upload' id='status-upload-items-"+ (i) + "'></div>" 
				+ "</div>"
		);
		
	}
}

function onUploadFailed(e) {
	alert("Error uploading file");
}

function onUploadProgress(e) {
	if (e.lengthComputable) {
		percentComplete = parseInt(e.loaded * 100
				/ fileLengthVar[filesUploaded]);
		
		var bar = document.getElementById('items-progress-bar-'
				+ (filesUploaded));
		bar.setAttribute("aria-valuenow", filesUploaded);
		bar.style.width = percentComplete + '%';
		document.getElementById('btnUploadFileNav').setAttribute("disabled", false);
		document.getElementById('filesUploads').setAttribute("disabled", false);
		document.getElementById('uploadButton').setAttribute("disabled", false);
	} else {
		alert("Error uploading file");
	}
}

function uploadNext(flag) {
	var xhr = new XMLHttpRequest();
	var fd = new FormData();
	fd.append("trash", trash);
	fd.append("idFolder", $('#parent').val());
	fd.append("idUser", idxID);
	if (flag === "files") {
		var file = document.getElementById('filesUploads').files[filesUploaded];
		fd.append("multipartFile", file);
		xhr.upload.addEventListener("progress", onUploadProgress, false);
		xhr.addEventListener("load", onUploadComplete, false);
		xhr.addEventListener("error", onUploadFailed, false);
		xhr.open("POST", "save-product");
	} else if (flag === "image") {
		var file = document.getElementById('theFile').files[0];
		fd.append("multipartFile", file);
		xhr.upload.addEventListener("progress", onUploadImageProgress, false);
		xhr.addEventListener("load", onUploadImageComplete, true);
		xhr.addEventListener("error", onUploadImageFailed, false);
		xhr.open("POST", "save-profile");
	} else if (flag === "imageNav") {
		var file = document.getElementById('theFileNav').files[0];
		fd.append("multipartFile", file);
		xhr.upload.addEventListener("progress", onUploadImageProgress, false);
		xhr.addEventListener("load", onUploadImageComplete, true);
		xhr.addEventListener("error", onUploadImageFailed, false);
		xhr.open("POST", "save-profile");
	}

	xhr.send(fd);
}

//// Let's begin the upload process
function startUpload() {
	$("#upload_file_folder").modal('hide');
	filesUploaded = 0;
	percentComplete = 0;

	uploadNext("files");
}

///* PROFILE PICTURE */
function onUploadImageComplete(e) {
	var d = new Date();
	$("#profile-picture").removeClass("loading");
	$("#btnUploadFile").removeAttr("disabled", "disabled");
	$("#btnUploadFile").attr("onclick", "openFile('theFile');");
	$("#btnUploadFileNav").removeAttr("disabled", "disabled");
	$("#btnUploadFileNav").attr("onclick", "openFile('theFile');");
	$("#profile-picture").attr("src", pp + "?timestamp=" + d.getTime());
}

function onUploadImageProgress(e) {
	if (e.lengthComputable) {
		$("#btnUploadFile").attr("disabled", "disabled");
		$("#btnUploadFile").removeAttr("onclick", "openFile('theFile');");
		$("#btnUploadFileNav").attr("disabled", "disabled");
		$("#btnUploadFileNav").removeAttr("onclick", "openFile('theFile');");
		$("#profile-picture").addClass("loading");
	}
}

function onUploadImageFailed(e) {
	alert("Error uploading file");
}

window.onload = function() {
	document.getElementById('filesUploads').addEventListener('change',
			onFileSelect, false);
	document.getElementById('uploadButton').addEventListener('click',
			startUpload, false);
}

$('#theFile').on('change', function() {
	uploadNext("image");
});

$('#theFileNav').on('change', function() {
	uploadNext("imageNav");
});