$(function() {
	$.contextMenu({
		selector : '.context-menu-folder',
		items : {
			"open" : {
				name : "Open",
				callback: function(key, opt){
					var urlInput = $('#urlInput').val();
					$('#urlInput').val(urlInput+'/'+opt.$trigger.context.innerText);
					$('#parent').val($(this).attr('id'));
					$('#parentName').val(opt.$trigger.context.innerText);
					get_list_file_and_directory('');
					get_parent_directory($('#parent').val());
                }
			},
			"sep1" : "---------",
			"move" : {
				name : "Pindahkan",
				callback: function(key, opt){
					truncateMovesModal();
					$('#move_modal_file_directory').modal('show');
					$('#extModal').val('folder');
					$('#idModal').val($(this).attr('id'));
					get_list_directory_modal('');
                },
				disabled: function(key, opt){        
		            if(adv !== 'Y' && opt.$trigger.context.lastElementChild.id !== idxID){
		                return true;
		            }       
		        }
			},
			"rename" : {
				name : "Rename",
				callback: function(key, opt){
					$('#edit_folder_modal').modal('show');
					$('#folderNameLabelEdit').html('');
					$('#folderNameLabelEdit').append('Edit Folder '+opt.$trigger.context.innerText);
					$('#IDfolderNameInputEdit').val($(this).attr('id'));
					$('#folderNameInputEdit').val(opt.$trigger.context.innerText);
                }
			},
			"delete" : {
				name : "Delete",
				callback: function(key, opt){
					$("#delete_modal_setting_folder").modal('show');
					$('#IDFolderDeleteModalSettingsFolder').val($(this).attr('id'));
					$('#nameDeleteModalSettingsFolder').val(opt.$trigger.context.innerText);
					$('#titleDeleteModalSettingsFolder').html('');
					$('#titleDeleteModalSettingsFolder').append("<img src=\"resources/img/warning.svg\"/> Hapus Folder \""+opt.$trigger.context.innerText+"\"");
					$('#preDeleteModalSettingsFolder').html('');
					$('#preDeleteModalSettingsFolder').append('Apakah anda yakin akan menghapus folder <strong>"'+opt.$trigger.context.innerText + "\"</strong> ?");
                },
				disabled: function(key, opt){        
					if(adv !== 'Y' && opt.$trigger.context.lastElementChild.id !== idxID){
		                return true;
		            }            
		        }
			},
			"sep2" : "---------",
			"properties" : {
				name : "Properties",
				callback: function(key, opt){
					if(adv !== 'Y' && opt.$trigger.context.lastElementChild.id !== idxID){
						get_directory_properties($(this).attr('id'), false);
					}else{
						get_directory_properties($(this).attr('id'), true);
					}
                }
			}
		}
	});
	
	$.contextMenu({
		selector : '.context-menu-file',
		items : {
			"download" : {
				name : "Download",
				callback: function(key, opt){
					window.open(bsURL +'/download/' + $(this).attr('id') +"/" + trash, '_blank');
                }
			},
			"sep1" : "---------",
			"move" : {
				name : "Pindahkan",
				callback: function(key, opt){
					truncateMovesModal();
					$('#move_modal_file_directory').modal('show');
					$('#extModal').val('file');
					$('#idModal').val($(this).attr('id'));
					get_list_directory_modal('');
                },
				disabled: function(key, opt){        
		            if(adv !== 'Y' && opt.$trigger.context.lastElementChild.id !== idxID){
		                return true;
		            }       
		        }
			},
			"rename" : {
				name : "Rename",
				callback: function(key, opt){
					$('#edit_file_modal').modal('show');
					$('#fileNameLabelEdit').html('');
					$('#fileNameLabelEdit').append('Edit File '+opt.$trigger.context.innerText);
					$('#IDFileNameInputEdit').val($(this).attr('id'));
					$('#fileNameInputEdit').val(opt.$trigger.context.innerText);
                }
			},	
			"delete" : {
				name : "Delete",
				callback: function(key, opt){
					$("#delete_modal_file").modal('show');
					$('#IDFileDeleteModal').val($(this).attr('id'));
					$('#nameFileDeleteModal').val(opt.$trigger.context.innerText);
					$('#titleDeleteModalFile').html('');
					$('#titleDeleteModalFile').append("<img src=\"resources/img/warning.svg\"/> Hapus File \""+opt.$trigger.context.innerText+"\"");
					$('#preDeleteModalFile').html('');
					$('#preDeleteModalFile').append('Apakah anda yakin akan menghapus file <strong>"'+opt.$trigger.context.innerText + "\"</strong> ?");
                },
				disabled: function(key, opt){        
		            if(adv !== 'Y' && opt.$trigger.context.lastElementChild.id !== idxID){
		                return true;
		            }       
		        }
			},
			"sep2" : "---------",
			"properties" : {
				name : "Properties",
				callback: function(key, opt){
					if(adv !== 'Y' && opt.$trigger.context.lastElementChild.id !== idxID){
						get_file_properties($(this).attr('id'), false);
		            }  else{
		            	get_file_properties($(this).attr('id'), true);
		            }
                }
			}
		}
	});
});

function getDoubleClick(id, name) {
	var urlInput = $('#urlInput').val();
	$('#urlInput').val(urlInput+'/'+name);
	$('#parent').val(id);
	$('#parentName').val(name);
	get_list_file_and_directory('');
	get_parent_directory($('#parent').val());
}

function backURLInput(name){
	get_parent_directory($('#oldParent').val());
	var urlInput = $('#urlInput').val();
	var res = urlInput.split("/"); 
	var newUrl = "ROOT";
	for(var i=1; i< (res.length-1); i++){
		newUrl += "/"+res[i];
	}
	$('#urlInput').val(newUrl);
}

function get_parent_directory(id) {
    var th = trash;
    var key = '';
    $.ajax({
        type: "GET",
        url: "getDirById",
        data: {
        	id: id,
            trash: th,
            key: key
        },
        dataType: 'json',
        success : function(res) {
        	if(res.access==="denied"){
        	}else{
        		if(res.msg === "200"){
        			$('#oldParent').val(res.items.Kode);
        			$('#upHref').removeAttr("href");
					$('#upHref').attr("href","javascript:;get_list_file_and_directory('"+res.items.Kode+"');backURLInput();");
        		}
        	}
        	
        },
        error: function(xhr) {
        	console.log("error", xhr);
        }
    });
}

function backURLInputModal(){
	get_parent_directory_modal($('#oldParentModal').val());
}

function getDoubleClickModal(id) {
	$('#parentModal').val(id);
	get_parent_directory_modal($('#parentModal').val());
	get_list_directory_modal('');
}

function get_parent_directory_modal(id) {
    var th = trash;
    var key = '';
    $.ajax({
        type: "GET",
        url: "getDirById",
        data: {
        	id: id,
            trash: th,
            key: key
        },
        dataType: 'json',
        success : function(res) {
        	if(res.access==="denied"){
        	}else{
        		if(res.msg === "200"){
        			$('#oldParentModal').val(res.items.Kode);
        			$('#upHrefModal').removeAttr("href");
					$('#upHrefModal').attr("href","javascript:get_list_directory_modal('"+res.items.Kode+"');backURLInputModal();");
        		}
        	}
        	
        },
        error: function(xhr) {
        	console.log("error", xhr);
        }
    });
}

function get_directory_properties(id, bool) {
	$('.over-folder').show();
    var th = trash;
    var key = '';
    $.ajax({
        type: "GET",
        url: "getDirByIdProperties",
        data: {
        	id: id,
            trash: th,
            key: key
        },
        dataType: 'json',
        success : function(res) {
        	if(res.access==="denied"){
        	}else{
        		if(res.msg === "200"){
        			truncateProperties();
        			$("#properties_modal").modal('show');
        			$("#properties-title").append('"'+res.items.Nama+'" Properties');
        			$("#tglProperties").append(parseDate(res.items.CreatedDate));
        			$("#pklProperties").append(parseTime(res.items.CreatedDate));
        			$("#nameProperties").append(res.items.CreatedBy);
        			if(bool){
        				$("#actionProperties").append('Full Akses : &#13;&#10;- Dapat membuka Folder &#13;&#10;- Dapat upload File dalam Folder &#13;&#10;- Dapat mengganti nama Folder &#13;&#10;- Dapat memindahkan Folder &#13;&#10;- Dapat mengapus Folder');
        			}else{
        				$("#actionProperties").append('Limited Akses : &#13;&#10;- Dapat membuka Folder  &#13;&#10;- Dapat upload File dalam Folder');
        			}
        			
        		}else{
        			$('.over-folder').hide();
        		}
        	}
        	
        },
        error: function(xhr) {
        	console.log("error", xhr);
        }
    });
}

function get_file_properties(id, bool) {
	$('.over-folder').show();
    var th = trash;
    var key = '';
    $.ajax({
        type: "GET",
        url: "getFileByIdProperties",
        data: {
        	id: id,
            trash: th,
            key: key
        },
        dataType: 'json',
        success : function(res) {
        	if(res.access==="denied"){
        	}else{
        		if(res.msg === "200"){
        			truncateFileProperties();
        			$("#properties_file_modal").modal('show');
        			$("#properties-file-title").append('"'+res.items.Nama+'" Properties');
        			$("#tglFileProperties").append(parseDate(res.items.CreatedDate));
        			$("#pklFileProperties").append(parseTime(res.items.CreatedDate));
        			$("#nameFileProperties").append(res.items.CreatedBy);
        			if(res.items.pathName.length > 30){
        				$("#locationFileProperties").append(res.items.pathName.substring(0, 31) +"...");
        			}else{
        				$("#locationFileProperties").append(res.items.pathName);
        			}
        			
        			$("#locationFileProperties").attr('title',res.items.pathName);
        			if(bool){
        				$("#actionFileProperties").append('Full Akses : &#13;&#10;- Dapat mengunduh File &#13;&#10;- Dapat mengganti nama File &#13;&#10;- Dapat memindahkan File &#13;&#10;- Dapat mengapus File');
        			}else{
        				$("#actionFileProperties").append('Limited Akses : &#13;&#10;- Dapat mengunduh File');
        			}
        			
        		}else{
        			$('.over-folder').hide();
        		}
        	}
        	
        },
        error: function(xhr) {
        	console.log("error", xhr);
        }
    });
}

function truncateProperties(){
	$("#properties-title").html('');
	$("#tglProperties").html('');
	$("#pklProperties").html('');
	$("#nameProperties").html('');
	$("#actionProperties").html('');
}

function truncateFileProperties(){
	$("#properties-file-title").html('');
	$("#tglFileProperties").html('');
	$("#pklFileProperties").html('');
	$("#nameFileProperties").html('');
	$("#actionFileProperties").html('');
	$("#locationFileProperties").html('');
	$("#locationFileProperties").removeAttr('title');
}

function truncateMovesModal(){
	$('#extModal').val('file');
	$('#idModal').val('');
}


