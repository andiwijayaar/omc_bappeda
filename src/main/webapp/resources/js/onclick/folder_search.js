function search_file_folder(){
	var key = $('#fileFolderSearch').val();
	get_search_file_folder(key);
}

$("#fileFolderSearch").keyup(function (e) {
    if (e.keyCode == 13) {
    	var key = $('#fileFolderSearch').val();
    	get_search_file_folder(key);
    }
});

function get_search_file_folder(key){
	$('.over-folder').show();
    $.ajax({
        type: "GET",
        url: "getFIleAndDirectoryInside",
        data: {
        	ID: 'root',
            trash: trash,
            key: key
        },
        dataType: 'json',
        success : function(res) {
        	if(res.access==="denied"){
//        		location.reload();
        	}else{
        		$('.over-folder').hide();
    			$('#data-items').html('');
    			for(var i = 0; i < res.items.length; i++){
    				if(res.items[i].isDirectory){
    					$('#parent').val(res.items[i].Parent);
    					$('#data-items').append(
	        			'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	    	                '<a href="javascript:;" ondblclick="getDoubleClick(\''+ res.items[i].ID +'\',\''+ res.items[i].Nama +'\')" id="'+res.items[i].ID+'" class="thumbnail  context-menu-folder">'+
	    	                    '<img src="resources/img/file_asset/folder.png" />'+
	    	                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	    	                       ' <div class="title text-center">'+ res.items[i].Nama +'</div>'+
	    	                    '</div>'+
	    	               ' </a>'+
	    	            '</div>'
	        				);
    				}else{
    					if(res.items[i].Extension === "aac" || res.items[i].Extension === "AAC"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/aac.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "ai" || res.items[i].Extension === "AI"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/ai.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "aiff" || res.items[i].Extension === "AIFF"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/aiff.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "avi" || res.items[i].Extension === "AVI"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/avi.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "bmp" || res.items[i].Extension === "BMP"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/bmp.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "c" || res.items[i].Extension === "C"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/c.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "cpp" || res.items[i].Extension === "CPP"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/cpp.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "css" || res.items[i].Extension === "CSS"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/css.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "dat" || res.items[i].Extension === "DAT"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/css.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "eps" || res.items[i].Extension === "EPS"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/eps.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "exe" || res.items[i].Extension === "EXE"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/exe.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "flv" || res.items[i].Extension === "FLV"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/flv.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "gif" || res.items[i].Extension === "GIF"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/gif.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "html" || res.items[i].Extension === "HTML"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/html.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "iso" || res.items[i].Extension === "ISO"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/iso.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "java" || res.items[i].Extension === "JAVA"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/java.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "js" || res.items[i].Extension === "JS"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/js.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "key" || res.items[i].Extension === "KEY"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/key.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "less" || res.items[i].Extension === "LESS"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/less.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "mid" || res.items[i].Extension === "MID"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/mid.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "mp3" || res.items[i].Extension === "MP3"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/mp3.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "mp4" || res.items[i].Extension === "MP4"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/mp4.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "mpg" || res.items[i].Extension === "MPG"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/mpg.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "php" || res.items[i].Extension === "PHP"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/php.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "png" || res.items[i].Extension === "PNG"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/png.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "psd" || res.items[i].Extension === "PSD"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/psd.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "rar" || res.items[i].Extension === "RAR"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/rar.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "sql" || res.items[i].Extension === "SQL"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/sql.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "tgz" || res.items[i].Extension === "TGZ"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/tgz.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "txt" || res.items[i].Extension === "TXT"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/txt.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "wav" || res.items[i].Extension === "WAV"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/wav.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "xml" || res.items[i].Extension === "XML"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/xml.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "jpg" || res.items[i].Extension === "JPG" || res.items[i].Extension === "jpeg" ||  res.items[i].Extension === "JPEG"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/jpg.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "xls" ||  res.items[i].Extension === "XLS"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/xls.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "xlsx" || res.items[i].Extension === "XLSX"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/xlsx.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
        					}else if(res.items[i].Extension === "doc" || res.items[i].Extension === "DOC" || res.items[i].Extension === "docx" ||  res.items[i].Extension === "DOCX"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/doc.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "ppt" || res.items[i].Extension === "PPT"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/ppt.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "pptx" ||  res.items[i].Extension === "PPTX"){
    						$('#data-items').append(
	        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
	        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
		        		                    '<img src="resources/img/file_asset/pptx.png" />'+
		        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
		        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
		        		                    '</div>'+
	        		                    ' </a>'+
	    		    	            '</div>'
        		                );
    					}else if(res.items[i].Extension === "pdf" || res.items[i].Extension === "PDF"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/pdf.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "zip" || res.items[i].Extension === "ZIP"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/zip.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else if(res.items[i].Extension === "file" || res.items[i].Extension === "FILE"){
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/file.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}else{
    						$('#data-items').append(
        						'<div class="col-sm-2 col-md-2 col-xs-6 items">'+
        		                	'<a href="javascript:;" id="'+res.items[i].ID+'" class="thumbnail context-menu-file">'+
	        		                    '<img src="resources/img/file_asset/_blank.png" />'+
	        		                    '<div id="'+ res.items[i].CreatedBy +'" class="caption">'+
	        		                        '<div class="title text-center">'+ res.items[i].Nama +'</div>'+
	        		                    '</div>'+
        		                    ' </a>'+
    		    	            '</div>'
    		                );
    					}
    				}
    			}
        	}
        }
    });
}

$('#upHref').on('click', function() {
	$('#fileFolderSearch').val('');
});