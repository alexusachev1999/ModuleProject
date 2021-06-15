<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список заказов</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
    .center{
        display: grid;
        margin-top: 50px;
        margin-bottom: 20px;
        align-self: center;
        justify-self: center;
    }

    .my-h1{
        padding-top: 30px;
        text-align: center;
        font-size: xx-large;
        background-color: #b40000;
        color: #ffffff;
        width: 100%;
        height: 70%;
    }

    .else-my-h2{
        padding-top: 30px;
        text-align: center;
        font-size: xxx-large;
        background-color: #242222;
        color: #ffffff;
        width: 100%;
        height: 70%;
    }

    .buttonStyle{
        margin: auto;
        align-self: center;
        background-color: #000000;
        color: #ffffff;
        width: 50%;
        height: auto;
        border-radius: 10px;
        font-size: xx-large;
    }
    .buttonStyle:hover{
        margin: auto;
        align-self: center;
        background-color: #ffffff;
        color: #000000;
        width: 50%;
        height: auto;
        border: 4px solid black;
        border-radius: 10px;
        font-size: xx-large;
    }
</style>
<body>
<table class="table table-bordered table-dark">
    <thead>
    <tr>
        <th scope="col">Номер заказа</th>
        <th scope="col">Статус</th>
        <th scope="col">Список маршрутных точек</th>
        <th scope="col">Фура</th>
        <th scope="col">Список водителей</th>
        <th scope="col">Удалить</th>
    </tr>
    </thead>
    <tbody>
    <C:forEach var="order" items="${orders}">

        <c:url var="updateButton" value="/admin/updateOrder">
            <c:param name="orderId" value="${order.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/admin/deleteOrder">
            <c:param name="orderId" value="${order.id}"/>
        </c:url>

        <tr>
            <td>${order.number}</td>
            <td><c:if test="${order.status == false}">❌ Невыполнен</c:if>
                <c:if test="${order.status == true}">✅ Выполнен</c:if></td>
            <td>
                <ol>
                    <c:forEach var="waypoint" items="${order.waypoints}">
                        <li>${waypoint.cargo} ${waypoint.cityLoading}</li>
                        <li>${waypoint.cargo} ${waypoint.cityUnloading}</li>
                    </c:forEach>
                </ol>
            </td>
            <td>${order.truck}</td>
            <td>
                <ol>
                    <c:forEach var="driver" items="${order.drivers}">
                        <li>${driver.name} ${driver.surname}</li>
                    </c:forEach>
                </ol>

            </td>
            <td><input type="button" value="Удалить" onclick="window.location.href='${deleteButton}'"/></td>
        </tr>
    </C:forEach>
    </tbody>
</table>
<div class="center">
    <input class="center buttonStyle" type="button" value="Добавить заказ" onclick="window.location.href='addOrder'">
</div>

<div class="center">
    <input class="center buttonStyle" type="button" value="Вернуться в главное меню" onclick="window.location.href='/logiweb/admin'">
</div>
</body>
</html>