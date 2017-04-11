<div id="content_menus_my_setting" class="table-users display-menu-active">
	<div class="text-center">
		<h2>Pengaturan Akun</h2>
	</div>
	<div id="box-departement">
		<form id="formSettingAccount" onsubmit="return false">
			<div class="form-department">
				<h4>Informasi Login</h4>
				<div class="box">
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="username">Username</label>
							<input type="text" class="form-control" id="usernameSettingAccount" placeholder="Username" value="${userForm.userId}" disabled required autocomplete="off">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="password">Password</label>
							<input type="password" class="form-control" id="passwordSettingAccount" placeholder="Password" autocomplete="off">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="conf-password">Konfirmasi Password</label>
							<input type="password" class="form-control" id="confSettingAccount" placeholder="Konfirmasi Password" autocomplete="off">
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div class="form-department">
				<h4>Informasi Diri</h4>
				<div class="box">
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="full-name">Nama Lengkap</label>
							<input type="text" class="form-control" id="fullnameSettingAccount" placeholder="Nama Lengkap" value="${userForm.name}" required autocomplete="off">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="jabatan">Jabatan</label>
							<input type="text" class="form-control" id="jabatanSettingAccount" placeholder="Jabatan" value="${userForm.jabatan}" required autocomplete="off">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="department">Departemen</label>
							<select class="form-control" style="min-width: none;max-width: none;" id="departmentSettingAccount" required>
								<option value="" selected> - Pilih Departemen - </option>
								<c:forEach items="${departementListAll}" var="departementMapAll">
									<option value="${departementMapAll.key}" <c:if test="${fn:containsIgnoreCase(departementMapAll.key, deptCode)}">selected</c:if>>${departementMapAll.key} - ${departementMapAll.value} </option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="email">Email</label>
							<input type="email" class="form-control" id="emailSettingAccount" placeholder="Email" value="${userForm.email}" required autocomplete="off">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="email">Nomor Hp</label>
							<input type="tel" min="0" step="1" pattern="\d{11,12}" class="form-control" id="hpSettingAccount" placeholder="Nomor HP" value="${userForm.phone}" required autocomplete="off">
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div class="btn_addDepartment">
				<button type="submit" id="saveSettingAccount" class="btn btn-default">Simpan</button>
			</div>
		</form>
	</div>
</div>