<div id="content_menus_folders" class="table-users display-menu-active">
	<div class="text-center">
		<button type="button" id="createFolderBtn" class="btn btn-primary btn-ok" data-toggle="modal" data-target="#create_folder_modal"><i class="fa fa-folder-open" aria-hidden="true"></i> TAMBAH FOLDER</button>
		<button type="button" id="uploadsButton" class="btn btn-success btn-ok" data-toggle="modal" data-target="#upload_file_folder"><i class="fa fa-cloud-upload" aria-hidden="true"></i> UNGGAH FILE</button>
	</div>
	<br>
	<div id="box-folder">
		<div class="folder-tools row">
			<div class="tools col-md-12 col-sm-12 col-xs-12">
				<div class="col-md-2 col-sm-2 col-xs-12">
					<!-- <a href="javascript:;"><i class="fa fa-arrow-circle-left fa-2x" style="color:green;" aria-hidden="true"> </i></a>
					<a href="javascript:;"><i class="fa fa-arrow-circle-right fa-2x" style="color:green;" aria-hidden="true"> </i></a> -->
					<a id="upHref" href="javascript:;get_list_file_and_directory('');"><i class="fa fa-arrow-circle-up fa-2x" style="color:green;" aria-hidden="true"> </i></a>
				</div>
				<div class="col-md-6 col-sm-10 col-xs-12">
					<input type="text" id="urlInput" class="col-md-12 col-sm-12 col-xs-12" value="ROOT" disabled/>
				</div>
				<div class="col-md-4 col-sm-8 col-xs-12">
					<input type="text" class="col-md-10 col-sm-10 col-xs-10" id="fileFolderSearch" placeholder="Search">&nbsp;
					<a href="javascript:;" class="col-md-1 col-sm-1 col-xs-1" onClick="search_file_folder();"><i class="fa fa-search fa-lg"  aria-hidden="true"> </i></a>
				</div>
			</div>
		</div>
		<input type="hidden" id="oldParent" value="root"/>
		<input type="hidden" id="parentName" value="root"/>
		<input type="hidden" id="parent" value="root"/>
		<div id="box-file" class="row">
			<div id="data-items" class="col-sm-12 col-md-12 col-xs-12">
			</div>
		</div>
		<div class="over-folder">
	    	<div id="overlay-folder"><img src="${pageContext.request.contextPath}/resources/img/loading.svg"></div>
	    </div>
	</div>
</div>