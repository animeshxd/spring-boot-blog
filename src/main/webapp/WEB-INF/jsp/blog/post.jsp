<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" defer></script>

<title>${blog.title}</title>
<style type="text/css">
	body {
		background-color: cornsilk;
		margin: 0px;

	}
	/* .nav a {
		text-decoration: none;
		display: inline-block;
		background-color: teal;
		color: white;
		padding: .5em .3em;
		margin-right: .5em;
	}
	
	.nav {
		padding: .5em;
		background-color: NavajoWhite;

		
	} */
	.post h4 {
		color: maroon;
	}
	
	.post {
		margin: 12px;
	}
	
	.post a.custom {
		font-size: 14px;
		text-decoration: none;
		color: maroon;
	}
	
	.success {
		font-size: 14px;
		padding: 12px;
		background-color: MediumAquaMarine;
		border: 12px;
	}
	.navbar{
		background-color: NavajoWhite;
		font-weight: bold;
		color: black;
		
	}
	.dropdown-menu > li:hover {
		color: black;
		background-color: bisque;

	}
	.dropdown-menu > li{
		font-weight: bold;
	}
	.dropdown-menu {
		background-color: cornsilk;
	}
	.dropdown-item {
		padding: 0px 8px 0px 8px;
	}
	
</style>
</head>
<body>
	<nav class="navbar navbar-expand-sm pt-1 pb-0 p-2 shadow-sm">
		<ul class="nav navbar-nav">
			<li class="active"><a href="./" class="nav-link">Home</a></li>
			<li><a href="create" class="nav-link">Create Blog Post</a></li>
			<c:if test="${empty author}">
			<li><a href="../author/login" class="nav-link">Sign in</a></li>
			</c:if>
			<c:if test="${not empty author}">

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Account
					  </a>
					<ul class="dropdown-menu">
						<li class="dropdown-item-text">${author.name}</li>
						<li class="dropdown-item"><a href="../author/logout" class="nav-link">Sign out</a></li>
					</ul>
				</li>
				
			</c:if>
		</ul>
	</nav>
	<div class="post">
		<c:if test="${success}">
			<div class="success">Successfully created post</div>
		</c:if>
		
		<h4>${fn:escapeXml(blog.title)}</h4>
		<p>${fn:escapeXml(blog.content)}</p>
		<span>
			<span class="">${blog.author} </span>
			<a class="custom" href="delete?id=${blog.id}">delete</a>
			<a class="custom" href="edit?id=${blog.id}">edit</a>
		</span>
		<hr>
	</div>
</body>
</html>
