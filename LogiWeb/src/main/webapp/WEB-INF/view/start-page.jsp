<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тег META, атрибут charset</title>
</head>
<body>
<h2>Добро пожаловать!</h2>

<security:authorize access="hasRole('ROLE_ADMIN')">
    <input type="button" value="Для администраторов" onclick="window.location.href = '/logiweb/admin/main'">
</security:authorize>

<security:authorize access="hasRole('ROLE_DRIVER')">
    <input type="button" value="Для водителей" onclick="window.location.href = '/logiweb/driver/main'">
</security:authorize>
</body>
</html>