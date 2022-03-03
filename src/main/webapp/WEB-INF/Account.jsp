<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
</head>
<body>
	<div id="navbar">
		<a href="/home">Home</a>
		<a href="/order">Order</a>
		<a href="/logout">Logout</a>
		<a href="/account/${userId}">Account</a>
	</div>
	
	<h2>Account Info</h2>
	<form:form method="POST" action="/editaccount/${userId}" modelAttribute="user" id="edit-form">
		<input type="hidden" name="_method" value="put">
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
		<input type="submit" form="edit-form" value="UPDATE" />
	</form:form>
	
	<h2>Past Orders</h2>
	<h3>Favorite Orders</h3>
		<c:forEach var="order" items="${favoriteOrders}">
			<p><c:out value="${order.createdAt}" /></p>
			<p>
				<c:out value="${order.crustType} - " />
				<c:forEach var="topping" items="${order.toppings}">
					<c:out value="${topping}, " />
				</c:forEach>
			</p>
			<p><a href="/favorite/${order.id}">unfavorite</a></p>
		</c:forEach>
	<h3>Past Orders</h3>
		<c:forEach var="order" items="${pastOrders}">
			<p><c:out value="${order.createdAt}" /></p>
			<p>
				<c:out value="${order.crustType} - " />
				<c:forEach var="topping" items="${order.toppings}">
					<c:out value="${topping}, " />
				</c:forEach>
			</p>
			<p><a href="/favorite/${order.id}">make favorite</a></p>
		</c:forEach>
	
</body>
</html>