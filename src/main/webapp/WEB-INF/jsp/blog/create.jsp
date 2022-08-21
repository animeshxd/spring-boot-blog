<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Blog Post</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" defer></script>

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
	<div class="container">
		<form action="create" method="post">
			<input type="text" name="id" value="${id}" hidden/>
			<label class="form-label">Title: </label> <input class="form-control" type="text" name="title" required="required" value="${blog.title}"/>
			<label class="form-label">Content</label>: 
			<textarea class="form-control h-25" rows="10" cols="70" name="content" required="required">${blog.content}</textarea>
			<button class="btn mt-3">Submit Post</button>
		</form>
	</div>
	
</body>
</html>