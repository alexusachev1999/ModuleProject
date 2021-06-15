<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Меню водителя</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
    .main-div{
        text-align: center;
        margin: auto;
        padding-top: 1%;
        align-self: center;
        color: #000000;
        width: 100%;
        height: auto;
        border-bottom: 4px solid #000000;
        font-size: xxx-large;
    }

    .center{
        display: grid;
        margin-bottom: 20px;
        align-self: center;
        justify-self: center;
    }

    .green{
        background-color: green;
    }

    .name-of-driver{
        background-color: #050505;
        color: #ffffff;
    }

    .other-one-line-div{
        background-color: #f3f3f3;
        color: #000000;
    }

    .unordered-list{
        margin: auto;
        width: 30%;
        font-size: 22px;
        text-align: start;
    }


    a.aStyle {
        align-self: center;
        justify-self: center;
        margin: auto;
        text-align: center;
        color: #828282 !important;
        text-decoration: none;
        width: 30%;
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
        width: 30%;
        height: auto;
        border: 4px solid black;
        border-radius: 10px;
        font-size: xxx-large;
    }

    .buttonStyle{
        margin: auto;
        align-self: center;
        background-color: #000000;
        color: #ffffff;
        width: auto;
        height: auto;
        border: 4px solid #6d6c6c;
        border-radius: 10px;
        font-size: 20px;
    }
    .buttonStyle:hover{
        margin: auto;
        align-self: center;
        background-color: #6d6b6b;
        color: #000000;
        width: auto;
        height: auto;
        border: 4px solid black;
        border-radius: 10px;
        font-size: 20px;
    }

    .buttonStyleGreen{
        margin-bottom: 20px;
        margin-left: auto;
        margin-right: auto;
        align-self: center;
        background-color: #02bf0a;
        color: #000000;
        width: auto;
        height: auto;
        border: 4px solid #000000;
        border-radius: 10px;
        font-size: 30px;
    }

    .buttonStyleGreen:hover{
        margin-bottom: 20px;
        margin-left: auto;
        margin-right: auto;
        align-self: center;
        background-color: #a5e262;
        color: #000000;
        width: auto;
        height: auto;
        border: 4px solid #000000;
        border-radius: 10px;
        font-size: 30px;
    }
</style>
<body>

<c:url var="orderComplete" value="/driver/orderComplete">
    <c:param name="orderId" value="${order.id}"/>
</c:url>

<div class="main-div name-of-driver">
    <h1>${driver.name} ${driver.surname}</h1>
</div>

<div class="main-div other-one-line-div">
    <h2>Личный номер: ${driver.phoneNumber}</h2>
</div>

<c:if test="${isDriverListEmpty == true}">
    <div class="main-div other-one-line-div">
        <h2>Нет соводителей</h2>
    </div>
</c:if>

<c:if test="${isDriverListEmpty == false}">
    <div class="main-div other-one-line-div">
        <table>
            <caption>Со-водители</caption>
            <tr>
                <th>Имя водителя</th>
                <th>Личный номер</th>
            </tr>
            <c:forEach var="driver" items="${drivers}">
                <tr>
                    <td>${driver.name} ${driver.surname}</td>
                    <td>${driver.phoneNumber}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${isDriverHasOrder == false}">
    <div class="main-div other-one-line-div">
        <h2>Нет текущего заказа</h2>
    </div>
</c:if>

<c:if test="${isDriverHasOrder == true}">
    <div class="main-div other-one-line-div">
        <h2>Рег. номер фуры: ${order.truck}</h2>
    </div>

    <div class="main-div other-one-line-div">
        <h2>Номер заказа: ${order.number}</h2>
    </div>

    <div class="main-div other-one-line-div">
        <h2>Список маршрутных точек:</h2>
        <div class="unordered-list">
            <ul class="list-group">
                <c:forEach var="waypoint" items="${order.waypoints}">

                    <c:url var="changeCargoButton" value="/driver/changeCargoStatus">
                        <c:param name="waypointId" value="${waypoint.id}"/>
                    </c:url>

                        <li class="list-group-item list-group-item-dark">
                            Груз: ${waypoint.cargo}
                            <br/>
                            Статус груза: ${waypoint.cargoStatus}
                            <c:if test="${!waypoint.cargoStatus.equals('Доставлен')}">
                                <input class="buttonStyle" type="button" value="Изменить статус груза"
                                   onclick="window.location.href = '${changeCargoButton}'">
                            </c:if>
                            <br/>
                            Город погрузки: ${waypoint.cityLoading}
                            <br/>
                            Город выгрузки: ${waypoint.cityUnloading}
                        </li>
                        <br/>
                </c:forEach>
            </ul>

            <c:if test="${isOrderCompleted == true}">
                <input class="center buttonStyleGreen" type="button" value="Заказ выполнен"
                       onclick="window.location.href = '${orderComplete}'">
            </c:if>
        </div>
    </div>

    <div class="main-div other-one-line-div">
        <h3>
            Текущий рабочий статус:
            <c:if test="${driver.workType == true}">
                Заступил в смену
            </c:if>
            <c:if test="${driver.workType == false}">
                Окончил смену
            </c:if>
        </h3>
        <input class="buttonStyle" type="button" value="Изменить рабочий статус" onclick="window.location.href = '/logiweb/driver/changeWorkTimeStatus'">
    </div>
    <br/>
    <br/>
    <div class="main-div other-one-line-div">
        <h3>
            Текущий тип работ: ${driver.status}
        </h3>

        <input class="buttonStyle" type="button" value="Изменить тип работы" onclick="window.location.href = '/logiweb/driver/changeWorkType'">
    </div>
</c:if>



<br/>
<br/>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<sec:authorize access="hasRole('ROLE_DRIVER')">
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