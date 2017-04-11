<%@include file="../layout/login_header.jsp" %>
<div class="content-data">
	<div class="login">
		<div class="text-center">
			<h2>Silahkan Log In</h2>
		</div>
		
		<form:form commandName="loginForm" id="login_form" class="form-login" method="post">
			<div class="form-horizontal text-center">
				
				<c:if test="${not empty loginForm.errorMessage}">
					<div class="alert alert-danger" id="errorMsg">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Login Fail !</strong> <c:out value="${loginForm.errorMessage}"></c:out>
					</div>
				</c:if>
				
				<div class="form-group">
					<label for="username" class="col-sm-4 control-label">USERNAME </label>
					<div class="col-sm-6">
				    	<form:input type="text" class="form-control right" path="userId" placeholder="username" required="required" autocomplete="off" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">PASSWORD </label>
					<div class="col-sm-6">
						<form:input type="password" class="form-control right" path="password" placeholder="password" required="required" autocomplete="off" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-3">
						<button type="submit" id="btn_login" onclick="submitForm()" class="btn btn-default">Login</button>
					</div>
					
					<div class="col-sm-offset-1 col-sm-3">
						<a href="/omc-bappeda/register" type="submit" id="btn_register" class="btn btn-default">Register</a>
					</div>
				</div>
			</div>
		
		</form:form>
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/login/login_view.js" type="text/javascript"></script>
<%@include file="../layout/footer.jsp" %>
