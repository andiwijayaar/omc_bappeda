<%@include file="../layout/login_header.jsp" %>
<div class="content-data">
	<div class="register">
		<div class="text-center">
			<h2>Silahkan isi form untuk registrasi</h2>
			<c:if test="${not empty loginForm.errorMessage}">
				<div class="alert alert-danger" id="errorMsgRegister">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Login Fail !</strong> <c:out value="${loginForm.errorMessage}"></c:out>
				</div>
			 </c:if>
		</div>
		
		<form:form commandName="loginForm" id="loginForm" class="form-login" method="post">
			<div class="form-register">
				
				<h4>Untuk Login</h4>
				<div class="box">
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="username">Username</label>
							<form:input type="text" class="form-control" path="userId" placeholder="username" required="required" autocomplete="off" />
						</div>
					</div>
					
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="password">Password</label>
							<form:input type="password" class="form-control" path="password" placeholder="password" required="required" autocomplete="off" />
						</div>
					</div>
					
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="confirm_password">Confirm Password</label>
							<input type="password" class="form-control" id="confirmPassword" placeholder="confirm password" required="required" autocomplete="off">
						</div>
					</div>
				</div>
				
				<h4>Informasi Diri</h4>
				<div class="box">
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="nama_lengkap">Nama Lengkap</label>
							<form:input type="text" class="form-control" path="name" placeholder="Nama" required="required" autocomplete="off" />
						</div>
					</div>
					
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="jk">Jenis Kelamin</label>
							<select class="form-control" id="jenis_kelamin" required>
								<option value="" selected> - Pilih Jenis Kelamin - </option>
								<c:forEach items="${genderMap}" var="gender">
									<option id="gender" value="${gender.key}"> ${gender.value} </option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="jabatan">Jabatan</label>
							<form:input type="text" class="form-control" path="jabatan" placeholder="Jabatan" required="required" autocomplete="off" />
						</div>
					</div>
					
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="department">Departemen</label>
							<select class="form-control" id="department" required>
								<option value="" selected> - Pilih Departemen - </option>
								<c:forEach items="${departementList}" var="departementMap">
									<c:forEach items="${departementMap}" var="departement">
										<option id="dept" value="${departement.key}">${departement.key} - ${departement.value} </option>
									</c:forEach>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="email">Email</label>
							<form:input type="email" class="form-control" path="email" placeholder="Email" required="required" autocomplete="off" />
						</div>
					</div>
					
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="nomor_hp">Nomor HP</label>
							<form:input type="tel" min="0" step="1" pattern="\d{11,12}" class="form-control" path="phone" placeholder="08xxxxxxxxx" required="required" autocomplete="off" />
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div class="btn_register">
				<button type="submit" id="btnRegister" onclick="submitForm()" class="btn btn-default">Register</button>
			</div>
		
		<form:hidden path="jenisKelamin"/>
		<form:hidden path="departement"/>
		</form:form>
		
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/login/register_view.js"></script>
<%@include file="../layout/footer.jsp" %>