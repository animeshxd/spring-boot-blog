<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" defer></script>
    <title>Login Page</title>
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

        a.help{
            text-decoration: none;
        }
	</style>
</head>

<body>
    <div class="container mt-4 w-auto">
        <c:if test="${not empty error}">
			<div class="alert alert-danger p-2" role="alert">
				${error}
			</div>
		</c:if>

        <form action="login" method="post">
            <div class="form-group mb-3">
                <div class="d-flex justify-content-between">
                    <label class="form-label">Username</label><a class="text-success help" href="register">sign up</a>
                </div>
                <input type="text" class="form-control" name="username" value="${author.username}${username}" required>
            </div>
            <div class="form-group mb-3">
                <label class="form-label">Password</label>
                <input type="password" class="form-control" name="password">
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
    </div>

</body>

</html>