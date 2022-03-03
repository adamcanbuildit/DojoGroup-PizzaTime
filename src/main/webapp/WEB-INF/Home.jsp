<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<div id="navbar">
		<a href="/order">Home</a>
		<a href="/order">Order</a>
		<a href="/logout">Logout</a>
		<a href="/account/${userId}">Account</a>
	</div>
	
	<h2>Quick Options</h2>
	<a href="/order">Order</a>
	<a href="/order">Re-Order My Fave</a>
	<a href="/order">Surprise Me</a>
	
</body>
</html>