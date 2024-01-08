<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
	<h5> Registrati compilando il form qui sotto!</h5>
	<form action ="http://localhost:8080/GestionalePetShop/Registrazione" method= "post">
		Nome: <input type="text" name="nome" /><br /><br />
		Cognome: <input type="text" name="cognome" /><br /><br />
		Email: <input type="email" name="email" /><br /><br />
		Cellulare: <input type="tel" name="cellulare" /><br /><br />
		<label for="dataNascita">Data di nascita:</label>
		<input type="date" id="dataNascita" name="dataNascita" required /><br /><br />
		Password: <input type="password" name="password" /><br /><br />
	
		<input type="submit" value="Registrati" />	

	</form>


</body>
</html>