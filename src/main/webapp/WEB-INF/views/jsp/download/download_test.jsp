<%@include file="../layout/login_header.jsp" %>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring 4 MVC File Download Example</title>
</head>
<body>
    <div class="form-container">
        <h1>Welcome to FileDownloader Example</h1>
         
        Click on below links to see FileDownload in action.<br/><br/>
         
         
        <a href="<c:url value='/download/internal' />">Download This File (located inside project)</a>  
        <br/>
        <a href="<c:url value='/download/external' />">Download This File (located outside project, on file system)</a>
         
    </div> 
</body>
</html>