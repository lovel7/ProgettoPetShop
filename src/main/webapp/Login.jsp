<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<p> Esegui il login</p>

<form action ="http://localhost:8080/GestionalePetShop/Login" method= "post">
Username: <input type="text" name="username" /><br /><br />
Password: <input type="password" name="password" /><br /><br />
<input type="submit" value="Accedi" />

<p> Se non ti sei ancora registrato clicca qui: <a href="registrazione.jsp">Registrati</a> </p>


</form>
</body>
</html>