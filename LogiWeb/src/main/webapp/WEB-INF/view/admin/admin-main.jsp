<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Меню администратора</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">

    .buttonMain {
        margin-top: 1%;
        display: grid;
        grid-template-columns: 100%;
        grid-gap: 1em;
    }
    .buttonStyle{
        margin: auto;
        align-self: center;
        background-color: #000000;
        color: #ffffff;
        width: 70%;
        height: auto;
        border: 4px solid #ffffff;
        border-radius: 10px;
        font-size: xxx-large;
    }
    .buttonStyle:hover{
        margin: auto;
        align-self: center;
        background-color: #ffffff;
        color: #000000;
        width: 70%;
        height: auto;
        border: 4px solid black;
        border-radius: 10px;
        font-size: xxx-large;
    }

    .center{
        display: grid;
        align-self: center;
        justify-self: center;
    }
    a.aStyle {
        margin: auto;
        text-align: center;
        color: #828282 !important;
        text-decoration: none;
        width: 70%;
        height: auto;
        border: 4px solid black;
        border-radius: 10px;
        font-size: xxx-large;
    }
    a.aStyle:hover {
        margin: auto;
        text-align: center;
        color: #030303 !important;
        text-decoration: none;
        width: 70%;
        height: auto;
        border: 4px solid black;
        border-radius: 10px;
        font-size: xxx-large;
    }
</style>
<body>

<div class="buttonMain">
    <input type="button" class="buttonStyle" value="Водители" onclick="window.location.href='/logiweb/admin/drivers'">
    <br/>
    <input type="button" class="buttonStyle" value="Фуры" onclick="window.location.href='/logiweb/admin/trucks'">
    <br/>
    <input type="button" class="buttonStyle" value="Заказы" onclick="window.location.href='/logiweb/admin/orders'">
    <br/>
    <input type="button" class="buttonStyle" value="Маршрутные точки" onclick="window.location.href='/logiweb/admin/waypoints'">
    <br/>
    <input type="button" class="buttonStyle" value="Грузы" onclick="window.location.href='/logiweb/admin/cargoes'">
    <br/>
    <input type="button" class="buttonStyle" value="Пользователи" onclick="window.location.href='/logiweb/admin/users'">
</div>



<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<br/><br/>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <!-- For login user -->
    <c:url value="/logout" var="logoutUrl" />
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <div class="center">
            <a class="aStyle" href="javascript:formSubmit()">Выйти</a>
        </div>
    </c:if>
</sec:authorize>
</body>
</html>