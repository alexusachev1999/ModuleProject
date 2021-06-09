<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список водителей данного заказа</title>
</head>
<body>
<table>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Статус</th>
        <th>Удалить</th>
    </tr>
    <C:forEach var="driver" items="${drivers}">

        <c:url var="deleteButton" value="/admin/deleteOrder">
            <c:param name="orderId" value="${driver.id}"/>
        </c:url>

        <tr>
            <td>${driver.name}</td>
            <td>${driver.surname}</td>
            <td>${driver.status}</td>
            <td><input type="button" value="Удалить" onclick="window.location.href='${deleteButton}'"/></td>
        </tr>
    </C:forEach>
</table>
<input type="button" value="Добавить заказ" onclick="window.location.href='addOrder'">
</body>
</html>
