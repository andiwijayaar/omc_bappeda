<div id="beranda_modal_terima" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="errorMsgApproval">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div>
				<h4 class="modal-title" id="titleApprovalUser">Konfirmasi User </h4>
			</div>
			<div class="form-edit_user">
				<div class="modal-body form-horizontal">
					<div class="form-group">
					    <label for="username" class="col-sm-4 control-label">Username </label>
					    <div class="col-sm-6">
						    <input type="hidden" class="form-control right" id="IDApprovalUserModal" required disabled="disabled" autocomplete="off">
					    	<input type="text" class="form-control right" id="usernameApprovalUserModal" disabled="disabled" placeholder="username" disabled="disabled" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
					    <label class="col-sm-4 control-label" for="nama_lengkap">Nama Lengkap</label>
					    <div class="col-sm-6">
					    	<input type="text" class="form-control" id="nama_lengkapApprovalUserModal" disabled="disabled" placeholder="Nama" required autocomplete="off">
					    </div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="jk">Jenis Kelamin</label>
						<div class="col-sm-6">
							<select class="form-control" id="jenis_kelaminApprovalUserModal" disabled="disabled" required>
								<option value="" selected> - Pilih Jenis Kelamin - </option>
								<option value="L"> Laki-laki </option>
								<option value="P"> Perempuan </option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="jabatan">Jabatan</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="jabatanApprovalUserModal" disabled="disabled" placeholder="Jabatan" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="department">Departemen</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="departmentApprovalUserModal" disabled="disabled" placeholder="Departement" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="email">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="emailApprovalUserModal" disabled="disabled" placeholder="Email" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="nomor_hp">Nomor HP</label>
						<div class="col-sm-6">
							<input type="tel" min="0" step="1" class="form-control" pattern="^\d{4}\d{3}\d{4}$" disabled="disabled" id="nomor_hpApprovalUserModal" placeholder="08xxxxxxxxx" required autocomplete="off">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="cancelUserModal" class="btn btn-default" data-dismiss="modal">Batal</button>
					<button type="submit" id="approveUserModal" class="btn btn-primary">Terima</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="beranda_modal_tolak" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="errorMsgRejectApproval">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertApprove" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div>
				<h4 class="modal-title" id="titleApprovalRejectUser"><img src="${pageContext.request.contextPath}/resources/img/warning.svg"/> Tolak User </h4>
			</div>
			<div class="modal-body" id="preReject">
                Apakah anda yakin akan menolak user
            </div>
            <input type="hidden" id="IDApprovalUserRejectModal" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="usernameApprovalUserRejectModal" required disabled="disabled" autocomplete="off">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="cancelRejectUserModal" data-dismiss="modal">Batal</button>
                <button type="submit" id="rejectUserModal" class="btn btn-danger btn-ok">Tolak</button>
            </div>
		</div>
	</div>
</div>