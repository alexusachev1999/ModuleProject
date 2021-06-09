<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!doctype html>
<html>
<head>
	<title>Добро пожаловать</title>
</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
	body {
		font-family: "Lato", sans-serif;
	}



	.main-head{
		height: 150px;
		background: #FFF;

	}

	.sidenav {
		height: 100%;
		background-color: #000;
		overflow-x: hidden;
		padding-top: 20px;
	}


	.main {
		padding: 0px 10px;
	}

	@media screen and (max-height: 450px) {
		.sidenav {padding-top: 15px;}
	}

	@media screen and (max-width: 450px) {
		.login-form{
			margin-top: 10%;
		}

		.register-form{
			margin-top: 10%;
		}
	}

	@media screen and (min-width: 768px){
		.main{
			margin-left: 40%;
		}

		.sidenav{
			width: 40%;
			position: fixed;
			z-index: 1;
			top: 0;
			left: 0;
		}

		.login-form{
			margin-top: 80%;
		}

		.register-form{
			margin-top: 20%;
		}
	}


	.login-main-text{
		margin-top: 20%;
		padding: 60px;
		color: #fff;
	}

	.login-main-text h2{
		font-weight: 300;
	}

	.btn-black{
		background-color: #000 !important;
		color: #fff;
	}
</style>

<body onload='document.loginForm.username.focus();'>

<div class="sidenav">
	<div class="login-main-text">
		<h2>Грузоперевозки "Logi-Web"<br> Стартовая страница</h2>
		<p>Войдите в систему, чтобы получить доступ</p>
	</div>
</div>
<div class="main">
	<div class="col-md-6 col-sm-12">
		<div class="login-form">

			<c:if test="${not empty error}">
				<div class="alert alert-danger" role="alert">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-success" role="alert">${msg}</div>
			</c:if>

			<form name='loginForm' action="<c:url value='/login' />" method='POST'>
				<div class="form-group">
					<label>Логин</label>
					<input type="text" name="username" class="form-control" placeholder="Логин">
				</div>
				<div class="form-group">
					<label>Пароль</label>
					<input type="password" name="password" class="form-control" placeholder="Пароль">
				</div>
				<br/>
				<button type="submit" name="submit" class="btn btn-black">Войти</button>

				<input type="hidden" name="${_csrf.parameterName}"
					   value="${_csrf.token}" />
			</form>
		</div>
	</div>
</div>

</body>
</html>