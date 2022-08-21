<xd>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page isELIgnored="false" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
</xd>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" defer></script>
	<title>Register</title>

	<style type="text/css">
		body {
			background-color: cornsilk;
			margin-top: 1em;
		}

		.btn {
			background-color: teal;
			color: white;
		}

		.alert {
			border-radius: 0%;
		}
	</style>
</head>

<body>

	<div class="container">
		<c:if test="${not empty error}">
			<div class="alert alert-danger p-2" role="alert">
				${error}
			</div>
		</c:if>
		<form method="post" action="register" enctype="multipart/form-data">
			
			<div class="form-group mb-3">
				<label class="form-label">Username</label>
				<input class="form-control" name="username" placeholder="username" maxlength="32" type="text" value="${author.username}" required/>
				<div class="text-danger small">
					<form:errors path="author.username"></form:errors>
				</div>
			</div>
			<div class="form-group mb-3">
				<label class="form-label">Email</label>
				<input class="form-control" name="email" placeholder="email" type="email" value="${author.email}" required/>
				<div class="text-danger small">
					<form:errors path="author.email"></form:errors>
				</div>
			</div>
			<div class="form-group mb-3">
				<label class="form-label">Password</label>
				<input class="form-control" name="password" placeholder="password" type="password" required/>
				<div class="text-danger small">
					<form:errors path="author.password"></form:errors>
				</div>
			</div>
			<div class="form-group mb-3">
				<label class="form-label">Your Name</label>
				<input class="form-control" name="name" placeholder="your name" type="text" value="${author.name}" required/>
				<div class="text-danger small">
					<form:errors path="author.name"></form:errors>
				</div>
			</div>
			<div class="form-group mb-3">
				<label class="form-label">upload file</label>
				<input type="file" name="file" class="form-control">
				<div class="text-danger small">
					<form:errors path="author.file"></form:errors>
				</div>
			</div>
			<button class="btn">register</button>
		</form>
	</div>
</body>

</html>