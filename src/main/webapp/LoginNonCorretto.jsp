<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p> Login non corretto. Tentativi rimanenti: <%= request.getParameter("tentativi") %></p>
<a href="Login.jsp">Clicca qui per riprovare</a> 


</body>
</html>