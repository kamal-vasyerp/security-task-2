<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My App</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
body {
	background-image:
		url("https://images.unsplash.com/photo-1428908728789-d2de25dbd4e2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
	align-content: center;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

a:link, a:visited {
	color: black;
	padding: 14px 25px;
	border: 3px solid black;
	border-radius: 20px;
	text-align: center;
	text-decoration: none;
	margin:5px;
}

.link1:hover, .link1:active {
	background-color: blue;
}

.link2:hover, .link2:active {
	background-color: green;
}
</style>
</head>
<body>
	<h1>My Application </h1><br>
	<br>
			<a href="/security/api/v3/signup-page" class="link1"> Sign up</a> 
			<a href="/login" class="link2"> login </a>
</body>
</html>