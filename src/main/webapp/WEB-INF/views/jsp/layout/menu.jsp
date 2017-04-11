<script src="${pageContext.request.contextPath}/resources/js/menu-controller/menu-controller.js" type="text/javascript"></script>
<div class="menu">
	<div class="box-border-profile">
		<div class="info-area">
			<div class="menu-nav">
				<div class="other-menu">
					<div class="line1">
						<c:if test="${userForm.isAdmin eq 'Y'}">
							<a href="javascript:;" id="menus_users_md" onClick="menu_controller(this.id, 'menus_users');get_list_setting_users();" class="btn btn-default"><i class="fa fa-users"></i></a>
						</c:if>
						<a href="javascript:;" id="menus_my_setting_md" onClick="menu_controller(this.id, 'menus_my_setting');" class="btn btn-default"><i class="fa fa-credit-card" aria-hidden="true"></i></a>
					</div>
					<div class="line2">
						<c:if test="${userForm.isAdmin eq 'Y'}">
							<a href="javascript:;" id="menus_folder_md" onClick="menu_controller(this.id, 'menus_folder');get_list_file_and_directory('');" class="btn btn-default"><i class="fa fa-folder-open"></i></a>
							<a href="javascript:;" id="menus_department_md" onClick="menu_controller(this.id, 'menus_department');get_list_setting_departement();" class="btn btn-default"><i class="fa fa-university"></i></a>
						</c:if>
						<!-- <a href="javascript:;" id="menus_access_md" onClick="menu_controller(this.id, 'menus_access');" class="btn btn-default"><i class="fa fa-user-secret"></i></a> -->
					</div>
					<div class="line3">
						<c:if test="${userForm.isAdmin eq 'Y'}">
							<a href="javascript:;" id="menus_history_users_md" onClick="menu_controller(this.id, 'menus_history_users');get_list_history_users();" class="btn btn-default"><i class="fa fa-history"></i><i class="fa fa-user"></i></a>
						</c:if>
						<a href="javascript:;" id="menus_history_admin_md" onClick="menu_controller(this.id, 'menus_history_admin');get_list_my_history();" class="btn btn-default"><i class="fa fa-history"></i><i class="fa fa-user-md"></i></a>
					</div>
				</div>
				<div class="other-menu-nav">
					<div class="line1">
						<c:if test="${userForm.isAdmin eq 'Y'}">
							<a href="javascript:;" id="menus_home_sm" onClick="menu_controller(this.id, 'menus_home');get_list();" class="btn btn-default active-menu"><i class="fa fa-home"></i></a>
						</c:if>
						<c:if test="${userForm.isAdmin eq 'N'}">
							<a href="javascript:;" id="menus_home_sm" onClick="menu_controller(this.id, 'menus_home');get_list_file_and_directory('');" class="btn btn-default active-menu"><i class="fa fa-home"></i></a>
						</c:if>
						<a href="javascript:;" onclick="openFile('theFileNav');" id="btnUploadFileNav" class="btn btn-default"><i class="fa fa-camera"></i></a>
						<input type="file" style="display:none" data-url="save-profile" id="theFileNav" class="btn btn-default" accept=".jpg, .jpeg|images/*" capture="camera"/>
						<c:if test="${userForm.isAdmin eq 'Y'}">
							<a href="javascript:;" id="menus_users_sm" onClick="menu_controller(this.id, 'menus_users');get_list_setting_users();" class="btn btn-default"><i class="fa fa-users"></i></a>
						</c:if>
						<a href="javascript:;" id="menus_my_setting_sm" onClick="menu_controller(this.id, 'menus_my_setting');" class="btn btn-default"><i class="fa fa-credit-card" aria-hidden="true"></i></a>
						<c:if test="${userForm.isAdmin eq 'Y'}">
							<a href="javascript:;" id="menus_folder_sm" onClick="menu_controller(this.id, 'menus_folder');get_list_file_and_directory('');" class="btn btn-default"><i class="fa fa-folder-open"></i></a>
							<a href="javascript:;" id="menus_department_sm" onClick="menu_controller(this.id, 'menus_department');get_list_setting_departement();" class="btn btn-default"><i class="fa fa-university"></i></a>
						</c:if>
						<!-- <a href="javascript:;" id="menus_access_sm" onClick="menu_controller(this.id, 'menus_access');" class="btn btn-default"><i class="fa fa-user-secret"></i></a> -->
						<c:if test="${userForm.isAdmin eq 'Y'}">
							<a href="javascript:;" id="menus_history_users_sm" onClick="menu_controller(this.id, 'menus_history_users');get_list_history_users();" class="btn btn-default"><i class="fa fa-history"></i><i class="fa fa-user"></i></a>
						</c:if>
						<a href="javascript:;" id="menus_history_admin_sm" onClick="menu_controller(this.id, 'menus_history_admin');get_list_my_history();" class="btn btn-default"><i class="fa fa-history"></i><i class="fa fa-user-md"></i></a>
						<a href="${pageContext.request.contextPath}" id="btn_logoutNav" class="btn btn-default btn-danger"><i class="fa fa-power-off" aria-hidden="true"></i></a>
					</div>
				</div>
			</div>
			<div class="box-img">
				<img id="profile-picture" src="${pageContext.request.contextPath}<c:out value='${imgPicture}'/>" class="profile-img">
			</div>
			<div class="profile-identity">
				<h3><c:out value="${userForm.name}"></c:out></h3>
				<h4 class="dept-h4"><c:out value="${userForm.departement}"></c:out></h3>
				<h4><span class="label label-default"><c:out value="${userForm.jabatan}"></c:out></span></h4>
				<c:if test="${userForm.isAdmin eq 'Y'}">
					<span class="label label-primary">Administrator</span>
				</c:if>
				
				<c:if test="${userForm.isAdmin eq 'N'}">
					<span class="label label-primary">User</span>
				</c:if>
			</div>
			
		</div>
		<div class="btn-profile">
			<c:if test="${userForm.isAdmin eq 'Y'}">
				<a href="javascript:;" id="menus_home" onClick="menu_controller(this.id, 'menus_home');get_list();" class="btn btn-default active-menu"><i class="fa fa-home"></i> Beranda</a>
			</c:if>
			<c:if test="${userForm.isAdmin eq 'N'}">
				<a href="javascript:;" id="menus_home" onClick="menu_controller(this.id, 'menus_home');get_list_file_and_directory('');" class="btn btn-default active-menu"><i class="fa fa-home"></i> Beranda</a>
			</c:if>
			<a href="javascript:;" onclick="openFile('theFile');" id="btnUploadFile"  class="btn btn-default"><i class="fa fa-camera"></i> Ubah Foto</a>
			<input type="file" style="display:none" id="theFile" data-url="save-profile" class="btn btn-default" accept=".jpg, .jpeg|images/*" capture="camera"/>
			<a href="/omc-bappeda/logOut" id="btn_logout" class="btn btn-default btn-danger"><i class="fa fa-power-off" aria-hidden="true"></i> Logout</a>
		</div>
	</div>
	<div class="box-border-menu">
		<div class="other-menu">
			<div class="line1">
				<c:if test="${userForm.isAdmin eq 'Y'}">
					<a href="javascript:;" id="menus_users" onClick="menu_controller(this.id, 'menus_users');get_list_setting_users();" class="btn btn-default"><i class="fa fa-users"></i> Pengaturan User</a>
				</c:if>
				<a href="javascript:;" id="menus_my_setting" onClick="menu_controller(this.id, 'menus_my_setting');" class="btn btn-default"><i class="fa fa-credit-card" aria-hidden="true"></i> Pengaturan Akun</a>
			</div>
			<div class="line2">
				<c:if test="${userForm.isAdmin eq 'Y'}">
					<a href="javascript:;" id="menus_folder" onClick="menu_controller(this.id, 'menus_folder');get_list_file_and_directory('');" class="btn btn-default"><i class="fa fa-folder-open"></i> Pengaturan Folder</a>
					<a href="javascript:;" id="menus_department" onClick="menu_controller(this.id, 'menus_department');get_list_setting_departement();" class="btn btn-default"><i class="fa fa-university"></i> Pengaturan Departemen</a>
				</c:if>
				<!-- <a href="javascript:;" id="menus_access" onClick="menu_controller(this.id, 'menus_access');" class="btn btn-default"><i class="fa fa-user-secret"></i> Pengaturan Akses</a> -->
			</div>
			<div class="line3">
				<c:if test="${userForm.isAdmin eq 'Y'}">
					<a href="javascript:;" id="menus_history_users" onClick="menu_controller(this.id, 'menus_history_users');get_list_history_users();" class="btn btn-default"><i class="fa fa-history"></i><i class="fa fa-user"></i> History User</a>
				</c:if>
				<a href="javascript:;" id="menus_history_admin" onClick="menu_controller(this.id, 'menus_history_admin');get_list_my_history();" class="btn btn-default">
					<i class="fa fa-history"></i><i class="fa fa-user-md"></i> History Saya</a>
			</div>
		</div>
	</div>
</div>
