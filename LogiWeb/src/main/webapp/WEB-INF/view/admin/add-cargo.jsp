<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добавить груз</title>
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
</style>
<body>

<div class="main">
    <div class="col-md-6 col-sm-12">
        <form:form action="saveCargo" modelAttribute="cargo" cssClass="login-form">
            <form:hidden path="id"/>
            <form:hidden path="status"/>
            <form:hidden path="number"/>


            <h2>Груз № ${cargo.number}</h2>
            <div class="form-group">
                <label for="name" class="form-label">Наименование</label>
                <form:input path="name" cssClass="form-control form-control-lg"
                            placeholder="Название груза"/>
                <br/>
                <form:errors cssClass="alert alert-danger" path="name"/>

                <c:if test="${!uniqueErrorMsg.equals('no error')}">
                    <div class="alert alert-danger" role="alert">
                            ${uniqueErrorMsg}
                    </div>
                </c:if>

            </div>

            <br/>
            <br/>

            <div class="form-group">
                <label for="weight" class="form-label">Масса (в кг.)</label>
                <form:input path="weight" cssClass="form-control form-control-lg"/>
                <br/>
                <form:errors path="weight" cssClass="alert alert-danger"/>
            </div>
            <br/><br/>
            <button type="submit" name="submit" class="btn btn-black">Добавить груз</button>
        </form:form>
    </div>
</div>

</body>
</html>