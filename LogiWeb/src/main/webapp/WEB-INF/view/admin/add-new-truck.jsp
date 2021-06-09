<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить фуру</title>
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
            margin-top: 5%;
        }
    }

    @media screen and (min-width: 768px) {
        .main {
            margin-left: 30%;
        }

        .login-form {
            background-color: #ffffff;
            margin-top: 10%;
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
        align-self: center;
        justify-self: center;
        text-align: center;
        font-size: 20px;
        background-color: #000 !important;
        color: #fff;
        border: 4px solid white;
    }
    .btn-black:hover{
        align-self: center;
        justify-self: center;
        text-align: center;
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
        <form:form action="saveTruck" modelAttribute="truck" cssClass="login-form">

            <h2 class="my-h2">Введите данные фуры</h2>

            <form:hidden path="id"/>

            <div class="form-group">
                <label for="registrationNumber" class="form-label">Регистрационный номер</label>
                <form:input path="registrationNumber" cssClass="form-control form-control-lg"
                            placeholder="АА12345"/>
                <br/>

                <c:if test="${!uniqueError.equals('no error')}">
                    <div class="alert alert-danger" role="alert">
                            ${uniqueError}
                    </div>
                </c:if>

                <form:errors path="registrationNumber" cssClass="alert alert-danger"/>
            </div>
            <br/>

            <div class="form-group">
                <label for="registrationNumber" class="form-label">Смена водителя (ч.)</label>
                <form:input path="driverShiftDuration" cssClass="form-control form-control-lg"
                            placeholder="9"/>
                <br/>
                <form:errors path="driverShiftDuration" cssClass="alert alert-danger"/>
            </div>
            <br/>

            <div class="form-group">
                <label for="registrationNumber" class="form-label">Вместимость (т.) </label>
                <form:input path="capacity" cssClass="form-control form-control-lg"
                            placeholder="20"/>
                <br/>
                <form:errors path="capacity" cssClass="alert alert-danger"/>
            </div>
            <br/>

            <div class="form-group">
                <label class="col-form-label-lg">Текущий город</label>
                <br/>
                <form:select path="city" cssClass="form-select-lg">
                    <c:forEach var="city" items="${cityList}">
                        <form:option value="${city.name}"/>
                    </c:forEach>
                </form:select>
                <br/>
                <form:errors path="city" cssClass="alert alert-danger"/>
            </div>
            <br/>

            <div class="form-group">
                <label class="col-form-label-lg">Состояние</label>
                <br/>
                <form:select path="state" cssClass="form-select-lg">
                    <form:option value= "true" label="Исправна"/>
                    <form:option value="false" label="Неисправна"/>
                </form:select>
                <br/>
                <form:errors path="city" cssClass="alert alert-danger"/>
            </div>
            <br/>

            <button type="submit" name="submit" class="btn btn-black">Добавить фуру</button>
        </form:form>
    </div>
</div>
</body>
</html>