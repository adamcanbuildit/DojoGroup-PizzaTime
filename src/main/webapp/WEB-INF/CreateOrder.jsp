<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Craft A Pizza</title>
</head>
<body>
	<div id="navbar">
		<a href="/home">Home</a>
		<a href="/order">Order</a>
		<a href="/logout">Logout</a>
		<a href="/account/${userId}">Account</a>
	</div>
	<div>
		<h1>Craft-A-Pizza</h1>
		<form:form method="POST" action="/createorder" modelAttribute="order" id="order-form">
			<p>
				<form:label path="deliveryMethod">Delivery Method:</form:label>
				<form:select path="deliveryMethod">
					<form:option value="Deliver">Delivery</form:option>
					<form:option value="Take-Out">Take-Out</form:option>
				</form:select>
				<form:errors path="deliveryMethod" />
			</p>
			<p>
				<form:label path="pizzaSize">Size:</form:label>
				<form:select path="pizzaSize">
					<form:option value="Small">Small</form:option>
					<form:option value="Medium">Medium</form:option>
					<form:option value="Large">Large</form:option>
				</form:select>
				<form:errors path="pizzaSize" />
			</p>
			<p>
				<form:label path="crustType">Crust:</form:label>
				<form:select path="crustType">
					<form:option value="Thin Crust">Thin Crust</form:option>
					<form:option value="Thick Crust">Thick Crust</form:option>
					<form:option value="Deep Dish">Deep Dish</form:option>
				</form:select>
				<form:errors path="crustType" />
			</p>
			<p>
				<form:label path="qty">Qty:</form:label>
				<form:select path="qty">
					<form:option value="1">1</form:option>
					<form:option value="2">2</form:option>
					<form:option value="3">3</form:option>
					<form:option value="4">4</form:option>
					<form:option value="5">5</form:option>
				</form:select>
				<form:errors path="qty" />
			</p>
			
			<form:errors path="toppings" />
			<fieldset>
				<legend>Toppings:</legend>
				Extra Cheese <form:checkbox path="toppings" value="extra cheese"/>
				| Pepperoni <form:checkbox path="toppings" value="pepperoni"/>
				| Mushrooms <form:checkbox path="toppings" value="mushrooms"/>
				| Peppers <form:checkbox path="toppings" value="peppers"/>
			</fieldset>
			<form:input type="hidden" path="user" value="${userId}" />
			<input type="submit" form="order-form" value="Add to Order" />
		</form:form>
	</div>
</body>
</html>