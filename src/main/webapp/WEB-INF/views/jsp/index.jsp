<%@include file="layout/header.jsp" %>
<%@include file="layout/menu.jsp" %>

<input type="hidden" id="trash" value="${userSession}">
<script type="text/javascript">
	var trash = "<c:out value='${userSession}'/>"; 
	var bsURL = "<c:out value='${pageContext.request.contextPath}'/>";
	var idxID = "<c:out value='${idxID}' />";
	var adv = "<c:out value='${isAdmin}' />";
	var uuCode = "<c:out value='${roles}' />";
	var pp = bsURL+"/public/users/images/new/"+idxID+".jpg";
</script>
<script src="${pageContext.request.contextPath}/resources/js/validation/ModalSettingUserValidation.js" type="text/javascript"></script>
<!-- BERANDA -->

<nav id="globalAlertSuccess" style="display:none" class="navbar navbar-inner navbar-fixed-top">
	<div class="container-fluid">
		<div class="alert alert-info">
			<i class="fa fa-check-circle-o" aria-hidden="true"></i>
			<strong>Berhasil</strong>
		</div>
	</div>
</nav>

<nav id="globalAlertError" style="display:none" class="navbar navbar-inner navbar-fixed-top">
	<div class="container-fluid">
		<div class="alert alert-danger">
			<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
			<strong>Gagal</strong>
		</div>
	</div>
</nav>

<c:if test="${userForm.isAdmin eq 'Y'}">
	<%@include file="content/beranda.jsp" %>
	<!-- END BERANDA -->
	
	<!-- PENGATURAN USER -->
	<%@include file="content/user_settings.jsp" %>
	<!-- END PENGATURAN USER -->
	
	<!-- PENGATURAN AKUN -->
	<%@include file="content/setting_account.jsp" %>
	<!-- END PENGATURAN AKUN -->
	
	<!-- PENGATURAN DEPARTEMENT -->
	<%@include file="content/department_settings.jsp" %>
	<!-- END PENGATURAN DEPARTEMENT -->
	
	<!-- PENGATURAN FOLDER -->
	<%@include file="content/folder_settings.jsp" %>
	<!-- END PENGATURAN FOLDER -->
	
	<!-- PENGATURAN FOLDER -->
	<%@include file="content/roles_settings.jsp" %>
	<!-- END PENGATURAN FOLDER -->
	
	<!-- HISTORY USERS -->
	<%@include file="content/history_users.jsp" %>
	<!-- END HISTORY USERS -->
	
	<!-- HISTORY ADMIN -->
	<%@include file="content/my_history.jsp" %>
	<!-- END HISTORY ADMIN -->
	
	<!-- MODAL -->
	<!-- BERANDA MODAL -->
	<%@include file="modal/beranda.jsp" %>
	<!-- END BERANDA MODAL -->
	
	<!-- PENGATURAN USER -->
	<%@include file="modal/user_settings_edit.jsp" %>
	<!-- END PENGATURAN USER -->
	
	<!-- PENGATURAN FOLDER -->
	<%@include file="modal/folder_settings.jsp" %>
	<!-- END PENGATURAN FOLDER -->
	
	<!-- PENGATURAN DEPARTEMENT -->
	<%@include file="modal/department_settings_edit.jsp" %>
	<!-- END PENGATURAN DEPARTEMENT -->
	
	<!-- PENGATURAN HISTORY USER -->
	<%@include file="modal/history_users.jsp" %>
	<!-- END PENGATURAN HISTORY USER -->
	
	<!-- PENGATURAN HISTORY ADMIN -->
	<%@include file="modal/my_history.jsp" %>
	<!-- END PENGATURAN HISTORY ADMIN -->
	
	<script src="${pageContext.request.contextPath}/resources/js/onclick/request_user.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/fileUploadModal.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/setting_users.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/setting_departement.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/global.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/folder_settings.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/my_history.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/history_users.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/upload/uploads.js" type="text/javascript"></script>
</c:if>
<c:if test="${userForm.isAdmin eq 'N'}">
	<!-- PENGATURAN FOLDER -->
	<%@include file="content/folder_settings.jsp" %>
	<!-- END PENGATURAN FOLDER -->
	
	<!-- PENGATURAN AKUN -->
	<%@include file="content/setting_account.jsp" %>
	<!-- END PENGATURAN AKUN -->
	
	<!-- HISTORY ADMIN -->
	<%@include file="content/my_history.jsp" %>
	<!-- END HISTORY ADMIN -->
	
	<!-- MODAL -->
	<!-- PENGATURAN FOLDER -->
	<%@include file="modal/folder_settings.jsp" %>
	<!-- END PENGATURAN FOLDER -->
	
	<!-- PENGATURAN HISTORY ADMIN -->
	<%@include file="modal/my_history.jsp" %>
	<!-- END PENGATURAN HISTORY ADMIN -->
	
	<script src="${pageContext.request.contextPath}/resources/js/onclick/fileUploadModal.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/global.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/folder_settings.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/onclick/my_history.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/upload/uploads.js" type="text/javascript"></script>
</c:if>
<script src="${pageContext.request.contextPath}/resources/js/onclick/setting_account.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/onclick/folder_search.js" type="text/javascript"></script>
<%@include file="layout/sticky_upload.jsp" %>
<%@include file="layout/footer.jsp" %>