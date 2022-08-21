<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Blog Post</title>

<style type="text/css">
	body {
		background-color: cornsilk;
	}
</style>
</head>


<body>
	<form action="create" method="post">
		Title:  <input type="text" name="title" required="required"/><br>
		<p> Content: </p>
		<textarea rows="10" cols="70" name="content" required="required"></textarea>
		<br><br>
		<button>Submit Post</button>
	</form>
	
</body>
</html>