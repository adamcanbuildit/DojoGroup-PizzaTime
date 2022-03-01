<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login or Register</title>
</head>
<body>
	<div>
		<h1>Register</h1>
		<p><c:out value="${register_error}" /></p>
		<form:form method="POST" action="/registration" modelAttribute="user" id="registration-form">
			<p>
				<form:label path="firstName">First Name:</form:label>
				<form:input type="text" path="firstName" />
				<form:errors path="firstName" />
			</p>
			<p>
				<form:label path="lastName">Last Name:</form:label>
				<form:input type="text" path="lastName" />
				<form:errors path="lastName" />
			</p>
			<p>
				<form:label path="email">Email:</form:label>
				<form:input type="email" path="email" />
				<form:errors path="email" />
			</p>
			<p>
				<form:label path="address">Address:</form:label>
				<form:input type="text" path="address" />
				<form:errors path="address" />
			</p>
			<p>
				<form:label path="city">City:</form:label>
				<form:input type="text" path="city" />
				<form:errors path="city" />
			</p>
			<p>
				<form:label path="state">State:</form:label>
				<form:input type="text" path="state" />
				<form:errors path="state" />
			</p>
			<p>
				<form:label path="password" >Password:</form:label>
				<form:password path="password"  />
				<form:errors path="password" />
			</p>
			<p>
				<form:label path="passwordConfirmation" >Password Confirmation:</form:label>
				<form:password path="passwordConfirmation" />
				<form:errors path="passwordConfirmation" />
			</p>
			<input type="submit" form="registration-form" value="Register!" />
		</form:form>
	</div>
	<div>
		<h1>Login</h1>
		<p>
			<c:out value="${login_error}" />
		</p>
		<form method="post" action="/login" id="login-form">
			<p>
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" />
			</p>
			<p>
				<label for="password" >Password:</label>
				<input type="password" id="password" name="password" />
			</p>
			<input type="submit" form="login-form" value="Login!" />	
		</form>
	</div>
</body>
</html>