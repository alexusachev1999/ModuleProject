<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить водителя</title>
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
            margin-top: 15%;
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
        <form:form action="saveDriver" modelAttribute="driver" cssClass="login-form">

            <form:hidden path="id"/>
            <form:hidden path="user"/>
            <form:hidden path="status"/>
            <form:hidden path="workType"/>

            <h2 class="my-h2">Введите данные водителя</h2>
            <br/>

            <div class="form-group">
                <label class="col-form-label-lg" for="name">Имя</label>
                    <form:input path="name" cssClass="form-control form-control-lg" id="name" placeholder="Имя водителя"/>
                    <form:errors path="name"/>
            </div>
            <br/><br/>

            <div class="form-group">
                <label class="col-form-label-lg">Фамилия</label>
                <form:input path="surname" cssClass="form-control form-control-lg" placeholder="Фамилия"/>
                <form:errors path="surname"/>
            </div>
            <br/><br/>

            <div class="form-group">
                <label class="col-form-label-lg">Номер телефона</label>
                <form:input path="phoneNumber" cssClass="form-control form-control-lg" placeholder="+7-953-146-23-60"/>
                <br/>

                <c:if test="${!uniqueDriverError.equals('no error')}">
                    <div class="alert alert-danger" role="alert">
                            ${uniqueDriverError}
                    </div>
                    <form:errors path="phoneNumber"/>
                </c:if>

            </div>
            <br/><br/>

            <div class="form-group">
                <label class="col-form-label-lg">Текущий город</label>
                <form:select path="city" cssClass="form-select-lg">
                    <c:forEach var="city" items="${cityList}">
                        <form:option value="${city.name}"/>
                    </c:forEach>
                </form:select>
                <form:errors path="city"/>
            </div>
            <br/><br/>

            <input type="submit" class="btn btn-black" value="Создать водителя">
        </form:form>
    </div>
</div>
</body>
</html>