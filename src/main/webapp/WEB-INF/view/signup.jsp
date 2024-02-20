<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
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

.button-position {
	align-content: center;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 150px;
}
</style>
</head>
<body>
<h1> Sign Up Here!!</h1>
<br>
<br>
	<div class="card" style="width: 600px; height: 350px">
		<div class="card-body">
			<form action="/security/api/v3/signup" method="post">
				<div class="form-floating mb-3">
					<input type="text" class="form-control" id="userName"
						name="userName" placeholder="Username"> <label
						for="userName">Username</label>
				</div>
				<div class="form-floating mb-3">
					<input type="text" class="form-control" id="role" name="role"
						placeholder="Role"> <label for="role">Role</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Password"> <label
						for="password">Password</label>
				</div>
				<div class="button-position">
					<input class="btn btn-primary" type="submit" value="Submit">
				</div>
			</form>
		</div>
	</div>
</body>
</html>