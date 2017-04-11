<%@include file="../layout/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Page</title>
</head>
<body>
	<!-- <form name="form1" id="form1" action="test" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="hiddenfield1" value="ok"> -->
	
	<form:form method="POST" commandName="loginForm" enctype="multipart/form-data">
	Files to upload:
	<br/>
	<input type="file" size="50" name="file1">
	<br/>
	<input type="file" size="50" name="file2">
	<br/>
	<input type="file" size="50" name="file3">
	<br/>
	<button type="submit" onclick="submitForm()" class="btn btn-default">Upload</button>
	<!-- <a href="/omc-bappeda/upload2" type="submit">Upload</a> -->
	</form:form>
</body>

<script src="${pageContext.request.contextPath}/resources/js/login/upload.js"></script>
</html>