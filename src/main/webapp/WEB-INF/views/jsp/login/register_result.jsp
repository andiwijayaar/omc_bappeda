<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../layout/login_header.jsp" %>
<div class="content-data">
	<div class="register">
		<div class="text-center">
			<h2>Registerasi Berhasil. Harap menunggu untuk proses Approval dari Admin</h2>
		</div>
		
		<form:form commandName="loginForm"  class="form-login" method="post">
			<div class="form-register">
				<h4>Informasi Diri</h4>
				<div class="box">
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey">User ID</label>
							<label class="lbl-grey"><c:out value="${loginForm.userId}"></c:out> </label>
						</div>
						
						<div class="form-group">
							<label class="lbl-grey">Nama Lengkap</label>
							<label class="lbl-grey"><c:out value="${loginForm.name}"></c:out> </label>
						</div>
						
						<div class="form-group">
							<label class="lbl-grey">Jenis Kelamin</label>
							<c:if test="${loginForm.jenisKelamin eq 'P'}">
								<label class="lbl-grey">Perempuan</label>
							</c:if>
							
							<c:if test="${loginForm.jenisKelamin ne 'P'}">
								<label class="lbl-grey">Laki - Laki</label>
							</c:if>
						</div>
						
						<div class="form-group">
							<label class="lbl-grey">Jabatan</label>
							<label class="lbl-grey"><c:out value="${loginForm.jabatan}"></c:out> </label>
						</div>
						
						<div class="form-group">
							<label class="lbl-grey">Departemen</label>
							<label class="lbl-grey" ><c:out value="${loginForm.departement}"></c:out> </label>
						</div>
						
						<div class="form-group">
							<label class="lbl-grey">Email</label>
							<label class="lbl-grey"><c:out value="${loginForm.email}"></c:out> </label>
						</div>
						
						<div class="form-group">
							<label class="lbl-grey">Nomor HP</label>
							<label class="lbl-grey"><c:out value="${loginForm.phone}"></c:out> </label>
						</div>
					</div>
					
				</div>
				<div class="clearfix"></div>

				<div class="btn_register">
				<button type="submit" id="btnDone" class="btn btn-default">Done</button>
			</div>
		</form:form>
		
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/login/register_result.js"></script>
<%@include file="../layout/footer.jsp" %>