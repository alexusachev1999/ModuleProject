<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
	<meta charset="UTF-8">
	<title>Грузоперевозки</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
	body {
		font-family: "Lato", sans-serif;
	}

	.main{
		display: grid;
	}

	.top-of-page{
		padding-top: 50px;
		padding-bottom: 50px;
		display: grid;
		width: 100%;
		background-color: #bb0000;
		color: #FFFFFF;
		text-align: center;
		font-size: xxx-large;
	}
	my-h1{
		font-size: xxx-large;
	}

	.dividing-div{
		grid-template-columns: 50% 30% 20%;
	}

	.left-side{
		margin: auto;
		padding-top: 150px;
		padding-bottom: 150px;
		align-self: center;
		justify-self: center;
		background-color: #FFFFFF;
	}

	.right-side{
		margin: auto;
		padding-top: 150px;
		padding-bottom: 150px;
		align-self: center;
		justify-self: center;
		background-color: #000000;
	}

	.buttonStyleBlack{
		margin: auto;
		align-self: center;
		justify-self: center;
		background-color: #000000;
		color: #ffffff;
		width: 40%;
		height: 100px;
		border: 4px solid #ffffff;
		border-radius: 20px;
		font-size: 30px;
	}
	.buttonStyleBlack:hover{
		margin: auto;
		justify-self: center;
		align-self: center;
		background-color: #ffffff;
		color: #000000;
		width: 40%;
		height: 100px;
		border: 4px solid black;
		border-radius: 20px;
		font-size: 30px;
	}
	.center{
		display: grid;
		align-self: center;
		justify-self: center;
	}

	.buttonStyleWhite{
		margin: auto;
		align-self: center;
		background-color: #ffffff;
		color: #050505;
		width: 40%;
		height: 100px;
		border: 4px solid #000000;
		border-radius: 20px;
		font-size: 30px;
	}
	.buttonStyleWhite:hover{
		margin: auto;
		align-self: center;
		background-color: #000000;
		color: #ffffff;
		width: 40%;
		height: 100px;
		border: 4px solid #ffffff;
		border-radius: 20px;
		font-size: 30px;
	}

</style>
<body>
<div class="main">

	<div class="top-of-page">
		<h1 class="my-h1">${title}</h1>
		<h1 class="my-h1">${message}</h1>
	</div>


	<div class="dividing-div">
		<div class="left-side">
			<div class="center">
				<input class="buttonStyleBlack" type="button" value="Для администраторов" onclick="window.location.href = '/logiweb/admin/'">
			</div>
		</div>

		<div class="right-side">
			<div class="center">
				<input class="buttonStyleWhite" type="button" value="Для водителей" onclick="window.location.href = '/logiweb/driver/'">
			</div>
		</div>
	</div>
</div>

</body>
</html>