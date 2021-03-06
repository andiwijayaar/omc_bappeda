<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="id">	
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="application-name" content="OMC-Bappeda SUMSEL">
		<meta name="description" content="OMC-Bappeda SUMSEL (Open Method Coordination) adalah sebuah aplikasi penyimpanan data yang dimiliki Badan Perencanaan Pembangunan Daerah Provinsi Sumatera Selatan. Aplikasi ini hanya dapat diakses oleh pegawai yang sudah memiliki akses.">
		<meta name="owner" content="Badan Perencanaan Pembangunan Daerah Provinsi Sumatera Selatan, www.bappeda.sumselprov.go.id">
		<meta name="copyright" content="Bappeda Provinsi Sumatera Selatan @2016">
		<meta name="author" content="PasarRebo teams (Backend: Gilang Bintang HA & S. A Marivan; FrontEnd: Andi Wijaya AR)">
		<meta name="keywords" content="omc-bappeda, sumatera selatan, bappeda, sumsel, palembang, pembangunan, aplikasi, repository">
		<title>OMC::BAPPEDA SUMSEL</title>
		<!-- <script type="text/javascript">
			console.log(navigator.userAgent.toLowerCase());
		</script> -->
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" >
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/font-awesome/css/font-awesome.min.css">
		<link href="${pageContext.request.contextPath}/resources/plugins/reject/css/jquery.reject.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/resources/plugins/reject/js/jquery.reject.js"></script>
	</head>
	<body>
		<header>
			<div class="main-header">
				<div class="container">
						
					<% String strContextPath = request.getContextPath(); %>
					<div class="logo inline">
						<a href="${pageContext.request.contextPath}" >
					 		<img src="${pageContext.request.contextPath}/resources/img/logo.png" />
						</a>
					</div>
					
					<div class="header-title inline">
						<h1>BAPPEDA SUMATERA SELATAN</h1>
						<h3>Open Methode Coordination (OMC)</h3>
					</div>
				</div>
			</div>
		</header>
		