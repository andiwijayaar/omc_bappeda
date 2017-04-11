<script>
    var totalFileLength, totalUploaded, fileCount;
    var filesUploaded = 0;
    var fileSizeVar = [];
    var fileLengthVar = [];
    var tempFileHis = 0;
    var percentComplete = 0;
	var percentTotalComplete = 0;
 
    //To log everything on console
    function debug(s) {
        //var debug = document.getElementById('debug');
        //if (debug) {
        //    debug.innerHTML = debug.innerHTML + '<br/>' + s;
        //}
    }
 
    //Will be called when upload is completed
    function onUploadComplete(e) {
        totalUploaded += document.getElementById('filesUploads').files[filesUploaded].size;
        fileSizeVar[filesUploaded] = document.getElementById('filesUploads').files[filesUploaded].size;
        filesUploaded++;
        debug('complete ' + filesUploaded + " of " + fileCount);
        debug('totalUploaded: ' + totalUploaded);
        if (filesUploaded < fileCount) {
            uploadNext("files");
        } else {
        	console.log("res", percentTotalComplete);
        	if(percentTotalComplete >=100){
	        	tempFileHis = tempFileHis+fileCount;
	        	$("#filesUploads").val("");
	        	document.getElementById('filesUploads').removeAttribute("disabled", false);
	        	document.getElementById('uploadButton').removeAttribute("disabled", false);
	        	get_list_file_and_directory($('#parent').val());
        	}
            //var bar = document.getElementById('bar');
            //bar.style.width = '100%';
            //bar.innerHTML = '100% complete';
            //alert('Finished uploading file(s)');
        }
    }
 
    //Will be called when user select the files in file control
    function onFileSelect(e) {
    	$("#collapse").html("");
    	showSticky();
        var files = e.target.files; // FileList object
        var output = [];
        fileCount = files.length;
        totalFileLength = 0;
        console.log("fileuploaded", filesUploaded);
        for (var i = 0; i < fileCount; i++) {
            var file = files[i];
            /* output.push(file.name, ' (', file.size, ' bytes, ', file.lastModifiedDate.toLocaleDateString(), ')');
            output.push('<br/>'); */
            debug('add ' + file.size);
            totalFileLength += file.size;
            fileLengthVar[i] = file.size;
            
            var objDiv = document.getElementById("collapse");
            objDiv.scrollTop = objDiv.scrollHeight;
            
            $("#collapse").append("<div class='list' id='items-"+ (i) +"'>" +
            		"<div class='progress'>"+
            			"<div class='progress-bar progress-bar-info progress-bar-striped active' id='items-progress-bar-"+(i)+"' role='progressbar' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100' style='width:0%'></div>" +
            				"<span>"+file.name+"</span>" +
            			"</div>" +
            			"<div class='status-upload' id='status-upload-items-"+(i)+"'>" +
	            		"</div>"+
	            	"</div>"
            );
        }
        //document.getElementById('selectedFiles').innerHTML = output.join('');
        debug('totalFileLength:' + totalFileLength);
    }
 
    //This will continueously update the progress bar
    function onUploadProgress(e) {
        if (e.lengthComputable) {
        	console.log("i", filesUploaded);
        	percentComplete = parseInt(e.loaded * 100 / fileLengthVar[filesUploaded]);
        	percentTotalComplete = parseInt((e.loaded + totalUploaded) * 100 / totalFileLength);
            /* var bar = document.getElementById('bar');
            bar.style.width = percentComplete + '%';
            bar.innerHTML = percentComplete + ' % complete'; */
            
            var bar = document.getElementById('items-progress-bar-'+(filesUploaded));
            bar.setAttribute("aria-valuenow", (filesUploaded+tempFileHis));
            bar.style.width = percentComplete + '%';
            document.getElementById('filesUploads').setAttribute("disabled", false);
            document.getElementById('uploadButton').setAttribute("disabled", false);
            
            
            if(percentComplete >= 100){
            	$('#status-upload-items-'+(filesUploaded+tempFileHis)).html("");
                $('#status-upload-items-'+(filesUploaded+tempFileHis)).append("<a href='#'><img src='${pageContext.request.contextPath}/resources/img/tick.svg'></a>");
                $("#items-progress-bar-"+(filesUploaded+tempFileHis)).toggleClass('progress-bar-info progress-bar-success');
                
                get_list_file_and_directory($('#parent').val());
            }
            
            if(percentTotalComplete === 100){
            	tempFileHis = tempFileHis+fileCount;
            	$("#filesUploads").val("");
            	document.getElementById('filesUploads').removeAttribute("disabled", false);
            	document.getElementById('uploadButton').removeAttribute("disabled", false);
            	get_list_file_and_directory($('#parent').val());
            }
        } else {
            debug('unable to compute');
        }
    }
 
    //the Ouchhh !! moments will be captured here
    function onUploadFailed(e) {
        alert("Error uploading file");
    }
 
    //Pick the next file in queue and upload it to remote server
    function uploadNext(flag) {
        var xhr = new XMLHttpRequest();
        var fd = new FormData();
        fd.append("trash", trash);
        fd.append("idFolder", $('#parent').val());
        fd.append("idUser", idxID);
        if(flag === "files"){
	        var file = document.getElementById('filesUploads').files[filesUploaded];
	        fd.append("multipartFile", file);
	        xhr.upload.addEventListener("progress", onUploadProgress, false);
	        xhr.addEventListener("load", onUploadComplete, false);
	        xhr.addEventListener("error", onUploadFailed, false);
	        xhr.open("POST", "save-product");
	        debug('uploading ' + file.name);
        }else if(flag === "image"){
        	var file = document.getElementById('theFile').files[0];
        	fd.append("multipartFile", file);
        	xhr.upload.addEventListener("progress", onUploadImageProgress, false);
            xhr.addEventListener("load", onUploadImageComplete, true);
            xhr.addEventListener("error", onUploadImageFailed, false);
            xhr.open("POST", "save-profile");
        }else if(flag === "imageNav"){
        	var file = document.getElementById('theFileNav').files[0];
        	fd.append("multipartFile", file);
        	xhr.upload.addEventListener("progress", onUploadImageProgress, false);
            xhr.addEventListener("load", onUploadImageComplete, true);
            xhr.addEventListener("error", onUploadImageFailed, false);
            xhr.open("POST", "save-profile");
        }
        
        xhr.send(fd);
    }
 
    //Let's begin the upload process
    function startUpload() {
    	$("#upload_file_folder").modal('hide');
    	totalUploaded = filesUploaded = 0;
    	percentComplete = 0;
    	percentTotalComplete = 0;
    	
        uploadNext("files");
    }
    
    /* PROFILE PICTURE */
    
    function onUploadImageComplete(e) {
    	var d = new Date();
		$("#profile-picture").removeClass("loading");
		$("#btnUploadFile").removeAttr( "disabled", "disabled" );
		$("#btnUploadFile").attr( "onclick", "openFile('theFile');" );
		$("#btnUploadFileNav").removeAttr( "disabled", "disabled" );
		$("#btnUploadFileNav").attr( "onclick", "openFile('theFile');" );
		$("#profile-picture").attr("src", pp+"?timestamp="+d.getTime());
	}
	
	function onUploadImageProgress(e) {
		if (e.lengthComputable) {
			$("#btnUploadFile").attr( "disabled", "disabled" );
			$("#btnUploadFile").removeAttr( "onclick", "openFile('theFile');" );
			$("#btnUploadFileNav").attr( "disabled", "disabled" );
			$("#btnUploadFileNav").removeAttr( "onclick", "openFile('theFile');" );
			$("#profile-picture").addClass("loading");
		}
	}
	
	function onUploadImageFailed(e) {
	    alert("Error uploading file");
	}
 	
	/* END PROFILE PICTURE */
    //Event listeners for button clicks
    window.onload = function() {
        document.getElementById('filesUploads').addEventListener('change', onFileSelect, false);
        document.getElementById('uploadButton').addEventListener('click', startUpload, false);
    }
	
    $('#theFile').on('change', function() {
    	uploadNext("image");
    });
    
    $('#theFileNav').on('change', function() {
    	uploadNext("imageNav");
    });
    
    
</script>
<div id="upload_file_folder" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <div id="errorUploadFile">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div> -->
				<h4 class="modal-title" id="titleApprovalUser">Unggah File</h4>
			</div>
			<div class="form-edit_user">
				<div class="modal-body form-horizontal">
			        <form>
			        	<input type="file" id="filesUploads" style="display:none" class="btn btn-default" multiple />
			            <div class="text-center">
			            	<a href="#" onclick="openBrowse('filesUploads');" id="btnUploadFileNav" class="btn btn-default"><h4>BROWSE FILE</h4></a>
			        	</div>
			        </form>
			        <!-- <div id='debug' style='height: 100px; border: 2px solid #ccc; overflow: auto'></div> -->
				</div>
				<div class="modal-footer">
					<button type="button" id="uploadButton" class="btn btn-info"><img style="width:20px;height:20px;" src='${pageContext.request.contextPath}/resources/img/upload.svg'> Upload</button>
					<button type="button" id="closeUploadModal" class="btn btn-default" data-dismiss="modal">Tutup</button>
				</div>
			</div>
		</div>
	</div>
</div>