<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить водителя(ей) к заказу</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
    body {
        font-family: "Lato", sans-serif;
    }

    .main {
        padding: 0px 10px;
    }

    @media screen and (max-width: 550px) {
        .login-form{
            margin-top: 10%;
        }
    }

    @media screen and (min-width: 768px) {
        .main {
            margin-left: 30%;
        }

        .login-form {
            background-color: #ffffff;
            margin-top: 25%;
            padding: 12%;
            border: 4px solid black;
            border-radius: 50px;
            box-shadow: 10px 5px 5px #7fe5e2;
        }
    }

    .login-main-text h2{
        font-weight: 300;
    }

    .btn-black{
        font-size: 20px;
        background-color: #000 !important;
        color: #fff;
        border: 4px solid white;
    }
    .btn-black:hover{
        font-size: 20px;
        background-color: #ffffff !important;
        color: #000000;
        border: 4px solid black;
    }

    .my-h2{
        padding: 5%;
        font-size: 30px;
        text-align: center;
        border-radius: 15px;
        background-color: #000 !important;
        color: #fff;
    }
</style>
<body>

<div class="main">
    <div class="col-md-6 col-sm-12">
        <form:form action="/logiweb/admin/order/saveDrivers" modelAttribute="order" cssClass="login-form">

            <form:hidden path="number"/>
            <form:hidden path="truck"/>
            <form:hidden path="waypoints"/>
            <form:hidden path="id"/>
            <form:hidden path="status"/>

            <h2 class="my-h2">Заказ №${order.number}</h2>
            <br/>

            <div class="form-group">
                <label class="col-form-label-lg">Список доступных водителей</label>
                <br/>
                <c:forEach var="driver" items="${drivers}">
                    <form:checkbox path="drivers" value="${driver.id}" label="${driver.name}"/>
                    <br/>
                </c:forEach>
                <form:errors path="drivers"/>
            </div>
            <br/>

            <button type="submit" name="submit" class="btn btn-black">Выбрать водителя(ей)</button>
        </form:form>
    </div>
</div>
</body>
</html>