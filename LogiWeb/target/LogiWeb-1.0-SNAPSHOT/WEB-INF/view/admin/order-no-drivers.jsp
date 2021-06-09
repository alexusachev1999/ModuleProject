<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Нет водителей</title>
</head>
<body>
<c:url var="addDriverButton" value="/admin/order/drivers/add">
    <c:param name="orderId" value="${order.id}"/>
</c:url>
<h2>У заказа №${order.number} нет водителей</h2>
<input type="button" value="Добавить водителей для выполнения заказа" onclick="window.location.href='${addDriverButton}'"/>
</body>
</html>