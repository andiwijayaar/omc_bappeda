<div id="edit_modal_setting_user" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="titleSettingUser">Edit User {nama_user}</h4>
			</div>
			<div class="form-edit_user">
				<div class="modal-body form-horizontal">
					<div class="form-group">
					    <label for="username" class="col-sm-4 control-label">Username </label>
					    <div class="col-sm-6">
						    <input type="hidden" class="form-control right" id="IDSettingUserModal" required disabled="disabled" autocomplete="off">
					    	<input type="text" class="form-control right" id="usernameSettingUserModal" placeholder="username" disabled="disabled" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
					    <label for="password" class="col-sm-4 control-label">Password </label>
					    <div class="col-sm-6">
					    	<input type="password" class="form-control right" id="passwordSettingUserModal" placeholder="Password" required  autocomplete="off">
						</div>
					</div>
					<div class="form-group">
					    <label for="password" class="col-sm-4 control-label">Ulangi Password </label>
					    <div class="col-sm-6">
					    	<input type="password" class="form-control right" id="conpasswordSettingUserModal" oninput="check(this)" placeholder="Ulangi Password" required  autocomplete="off">
						</div>
					</div>
					<div class="form-group">
					    <label class="col-sm-4 control-label" for="nama_lengkap">Nama Lengkap</label>
					    <div class="col-sm-6">
					    	<input type="text" class="form-control" id="nama_lengkapSettingUserModal" placeholder="Nama" required autocomplete="off">
					    </div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="jk">Jenis Kelamin</label>
						<div class="col-sm-6">
							<select class="form-control" id="jenis_kelaminSettingUserModal" required>
								<option value="" selected> - Pilih Jenis Kelamin - </option>
								<option value="L"> Laki-laki </option>
								<option value="P"> Perempuan </option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="jabatan">Jabatan</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="jabatanSettingUserModal" placeholder="Jabatan" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="department">Departemen</label>
						<div class="col-sm-6">
							<select class="form-control" id="departmentSettingUserModal" required>
								<option value="" selected> - Pilih Departemen - </option>
								<c:forEach items="${departementListAll}" var="departementMapAll">
									<option value="${departementMapAll.key}">${departementMapAll.key} - ${departementMapAll.value} </option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="email">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="emailSettingUserModal" placeholder="Email" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="nomor_hp">Nomor HP</label>
						<div class="col-sm-6">
							<input type="tel" min="0" step="1" class="form-control" pattern="^\d{4}\d{3}\d{4}$" id="nomor_hpSettingUserModal" placeholder="08xxxxxxxxx" required autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="department">Jadikan Admin</label>
						<div class="col-sm-6">
							<select class="form-control" id="isAdminSettingUserModal" required>
								<option value=""> - Pilih Departemen - </option>
								<option value="N">Tidak</option>
								<option value="Y">Ya</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="cancelSettingUserModal" class="btn btn-default" data-dismiss="modal">Batal</button>
					<button type="submit" id="saveSettingUserModal" class="btn btn-primary">Simpan</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="delete_modal_setting_user" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="errorMsgDeleteModalSettingsUsers">
					<div class="alert alert-danger">
						<a href="#" id="closeAlertDeleteModalSettingsUsers" class="alert alert-danger" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Error</strong> Ulangi beberapa saat lagi
					</div>
				</div>
				<h4 class="modal-title" id="titleDeleteModalSettingsUsers"><img src="${pageContext.request.contextPath}/resources/img/warning.svg"/> Hapus User </h4>
			</div>
			<div class="modal-body" id="preDeleteModalSettingsUsers">
                Apakah anda yakin akan menghapus user
            </div>
            <input type="hidden" id="IDUserDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="usernameDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="gender_usersDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="email_usersDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="hp_usersDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="jabatan_usersDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="deptart_usersDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="role_usersDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <input type="hidden" id="pass_usersDeleteModalSettingsUser" required disabled="disabled" autocomplete="off">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="cancelDeleteModalSettingsUser" data-dismiss="modal">Batal</button>
                <button type="submit" id="deleteModalSettingsUser" class="btn btn-danger btn-ok">Hapus</button>
            </div>
		</div>
	</div>
</div>