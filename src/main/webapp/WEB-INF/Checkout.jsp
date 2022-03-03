<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
</head>
<body>
	<div id="navbar">
		<a href="/home">Home</a>
		<a href="/order">Order</a>
		<a href="/logout">Logout</a>
		<a href="/account/${userId}">Account</a>
	</div>
	
	<h2>Your Order</h2>
	<p>METHOD: <c:out value="${currentOrder.deliveryMethod}" /></p>
	<p>QTY: <c:out value="${currentOrder.qty}" /></p>
	<p>Size: <c:out value="${currentOrder.pizzaSize}" /></p>
	<p>Crust: <c:out value="${currentOrder.crustType}" /></p>
	<p>Toppings: 
		<c:forEach var="topping" items="${currentOrder.toppings}">
			<c:out value=" -${topping} " />
		</c:forEach>
	</p>
	<form action="/checkout/${currentOrder.id}/delete" method="post">
		<input type="hidden" name="_method" value="delete">
		<input type="submit" value="Start Over">
	</form>
	<form action="/purchase/${currentOrder.id}" method="post">
		<input type="submit" class="btn btn-danger" value="Purchase">
	</form>
	
	<p>Price: $<c:out value="${price}" /></p>
	
</body>
</html>