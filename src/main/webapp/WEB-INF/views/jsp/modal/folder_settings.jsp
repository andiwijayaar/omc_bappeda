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
			            	<a href="javascript:;" onclick="openBrowse('filesUploads');" id="btnUploadFileNav" class="btn btn-default"><h4>BROWSE FILE</h4></a>
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

<div id="create_folder_modal" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <div id="errorUploadFile">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div> -->
				<h4 class="modal-title">Tambah Folder</h4>
			</div>
			<div class="form-edit_user">
				<form id="addFolderForm">
					<div class="modal-body form-horizontal">
						<div class="form-group">
						    <label for="username" class="col-sm-4 control-label">Nama Folder </label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control right" id="folderNameInput" placeholder="Nama Folder" required autocomplete="off">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" id="createFolderButton" class="btn btn-info"><i class="fa fa-plus" aria-hidden="true"></i> Tambah</button>
						<button type="button" id="closeCreateFolderModal" class="btn btn-default" data-dismiss="modal">Tutup</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="edit_folder_modal" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <div id="errorUploadFile">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div> -->
				<h4 id="folderNameLabelEdit" class="modal-title">Edit Folder {}</h4>
			</div>
			<div class="form-edit_user">
					<div class="modal-body form-horizontal">
						<div class="form-group">
						    <label for="folderNameLabel" class="col-sm-4 control-label">Nama Folder </label>
						    <div class="col-sm-6">
						    	<input type="hidden" class="form-control right" id="IDfolderNameInputEdit" required disabled autocomplete="off">
						    	<input type="text" class="form-control right" id="folderNameInputEdit" placeholder="Nama Folder" required autocomplete="off">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" id="editFolderButton" class="btn btn-info"><i class="fa fa-edit" aria-hidden="true"></i> Edit</button>
						<button type="button" id="closeEditFolderModal" class="btn btn-default" data-dismiss="modal">Tutup</button>
					</div>
			</div>
		</div>
	</div>
</div>

<div id="edit_file_modal" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <div id="errorUploadFile">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div> -->
				<h4 id="fileNameLabelEdit" class="modal-title">Edit File {}</h4>
			</div>
			<div class="form-edit_user">
					<div class="modal-body form-horizontal">
						<div class="form-group">
						    <label for="folderNameLabel" class="col-sm-4 control-label">Nama File </label>
						    <div class="col-sm-6">
						    	<input type="hidden" class="form-control right" id="IDFileNameInputEdit" required disabled autocomplete="off">
						    	<input type="text" class="form-control right" id="fileNameInputEdit" placeholder="Nama File" required autocomplete="off">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" id="editFileButton" class="btn btn-info"><i class="fa fa-edit" aria-hidden="true"></i> Edit</button>
						<button type="button" id="closeEditFileModal" class="btn btn-default" data-dismiss="modal">Tutup</button>
					</div>
			</div>
		</div>
	</div>
</div>

<div id="delete_modal_setting_folder" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="titleDeleteModalSettingsFolder"><img src="${pageContext.request.contextPath}/resources/img/warning.svg"/> Hapus Folder </h4>
			</div>
			<div class="modal-body" id="preDeleteModalSettingsFolder">
                Apakah anda yakin akan menghapus folder
            </div>
            <input type="hidden" id="IDFolderDeleteModalSettingsFolder" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="nameDeleteModalSettingsFolder" required disabled="disabled" autocomplete="off">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="cancelDeleteModalSettingsFolder" data-dismiss="modal">Batal</button>
                <button type="submit" id="deleteModalSettingsFolder" class="btn btn-danger btn-ok">Hapus</button>
            </div>
		</div>
	</div>
</div>

<div id="delete_modal_file" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="titleDeleteModalFile"><img src="${pageContext.request.contextPath}/resources/img/warning.svg"/> Hapus File </h4>
			</div>
			<div class="modal-body" id="preDeleteModalFile">
                Apakah anda yakin akan menghapus file
            </div>
            <input type="hidden" id="IDFileDeleteModal" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="nameFileDeleteModal" required disabled="disabled" autocomplete="off">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="cancelDeleteModalFile" data-dismiss="modal">Batal</button>
                <button type="submit" id="deleteModalFile" class="btn btn-danger btn-ok">Hapus</button>
            </div>
		</div>
	</div>
</div>

<div id="move_modal_file_directory" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="titleMoveModalFileDirectory"><i class="fa fa-exchange" aria-hidden="true"></i> Pindahkan </h4>
			</div>
			<div class="modal-body" id="preMoveModalFileDirectory">
				<a href="javascript:;" id="upHrefModal" class="btn btn-success"><i class="fa fa-reply" aria-hidden="true"></i> Up</a>
				<input type="hidden" id="oldParentModal" value="root"/>
				<input type="hidden" id="parentModal" value="root"/>
				<input type="hidden" id="extModal" value=""/>
				<input type="hidden" id="idModal" value=""/>
                <div id="list-folder" class="list-group">
				</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="cancelMoveModalFileDirectory" data-dismiss="modal">Batal</button>
                <button type="submit" id="moveModalFileDirectory" class="btn btn-primary btn-ok">Pindahkan</button>
            </div>
		</div>
	</div>
</div>

<div id="properties_modal" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="properties-title">Properties</h4>
			</div>
			<div class="form-my-history">
				<form>
					<div class="modal-body form-horizontal">
						<div class="form-group">
						    <label for="tanggal" class="col-sm-4 control-label">Tanggal Upload</label>
						    <div class="col-sm-6">
						    	<label for="tanggal" id="tglProperties" class="control-label"></label>
							</div>
						</div>
						<div class="form-group">
						    <label for="pkl" class="col-sm-4 control-label">Pukul </label>
						    <div class="col-sm-6">
						    	<label for="tanggal" id="pklProperties" class="control-label"></label>
							</div>
						</div>
						<div class="form-group">
						    <label for="ip" class="col-sm-4 control-label">Dibuat Oleh </label>
						    <div class="col-sm-6">
						    	<label for="tanggal" id="nameProperties" class="control-label"></label>
							</div>
						</div>
						<div class="form-group">
						    <label for="ip" class="col-sm-4 control-label">Hak Akses </label>
						    <div class="col-sm-6">
						    	<textarea class="form-control" rows="6" cols="20" disabled id="actionProperties"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeProperties" class="btn btn-default" data-dismiss="modal">Tutup</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="properties_file_modal" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="properties-file-title">Properties</h4>
			</div>
			<div class="form-my-history">
				<form>
					<div class="modal-body form-horizontal">
						<div class="form-group">
						    <label for="tanggal" class="col-sm-4 control-label">Tanggal Upload</label>
						    <div class="col-sm-6">
						    	<label for="tanggal" id="tglFileProperties" class="control-label"></label>
							</div>
						</div>
						<div class="form-group">
						    <label for="pkl" class="col-sm-4 control-label">Pukul </label>
						    <div class="col-sm-6">
						    	<label for="tanggal" id="pklFileProperties" class="control-label"></label>
							</div>
						</div>
						<div class="form-group">
						    <label for="location" class="col-sm-4 control-label">Lokasi </label>
						    <div class="col-sm-6">
						    	<label for="location" data-toggle="tooltip" id="locationFileProperties" class="control-label"></label>
							</div>
						</div>
						<div class="form-group">
						    <label for="ip" class="col-sm-4 control-label">Dibuat Oleh </label>
						    <div class="col-sm-6">
						    	<label for="tanggal" id="nameFileProperties" class="control-label"></label>
							</div>
						</div>
						<div class="form-group">
						    <label for="ip" class="col-sm-4 control-label">Hak Akses </label>
						    <div class="col-sm-6">
						    	<textarea class="form-control" rows="6" cols="20" disabled id="actionFileProperties"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeFileProperties" class="btn btn-default" data-dismiss="modal">Tutup</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>