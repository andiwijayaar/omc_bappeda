<div id="edit_modal_dept" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="titleSettingDepartment">Edit Departemen {nama_departement}</h4>
			</div>
			<form class="form-edit_user">
				<div class="modal-body form-horizontal">
					<div class="form-group">
					    <label for="username" class="col-sm-4 control-label">Nama Departemen </label>
					    <div class="col-sm-6">
					    	<input type="hidden" class="form-control right" id="IDSettingDepartmentModal" required disabled="disabled" autocomplete="off">
					    	<input type="text" class="form-control right" id="edit_departement_name" placeholder="Nama Departemen" required autocomplete="off">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="cancelSettingDepartmentModal" class="btn btn-default" data-dismiss="modal">Batal</button>
					<button type="button" id="saveSettingDepartmentModal" class="btn btn-primary">Simpan</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="delete_modal_setting_department" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="errorMsgDeleteModalSettingsDepartment">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertDeleteModalSettingsDepartment" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div>
				<h4 class="modal-title" id="titleDeleteModalSettingsDepartment"><img src="${pageContext.request.contextPath}/resources/img/warning.svg"/> Hapus Departemen </h4>
			</div>
			<div class="modal-body" id="preDeleteModalSettingsDepartment">
                Apakah anda yakin akan menghapus departmen
            </div>
            <input type="hidden" id="codeDeleteModalSettingsDepartment" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="nameDeleteModalSettingsDepartment" required disabled="disabled" autocomplete="off">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="cancelDeleteModalSettingsDepartment" data-dismiss="modal">Batal</button>
                <button type="submit" id="deleteModalSettingsDepartment" class="btn btn-danger btn-ok">Hapus</button>
            </div>
		</div>
	</div>
</div>