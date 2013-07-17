<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bienvenido</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
<div class="container">
	<div class="header">
	<div class="barra-top">
			<div class="color rojo"></div>
			<div class="color naranja"></div>
			<div class="color gris"></div>
			<div class="color negro"></div>
			<div class="color blanco"></div>
		</div>
		<div class="logo">
			<img alt="logo" src="../img/logo.png">
		</div>
	</div>
	<p>${message}</p>
	<div>
		<form action="tweet/postear.do">
			<input type="text" value="Que hay de nuevo marinero?" /> <input
				type="submit" class="btn" />
		</form>
	</div>
	<div class="barra-lateral">
		<a href="usuario/perfil.do" class="miperfil">Mi Perfil</a> <a
			href="usuario/perfil.do" class="sigo">A quienes sigo</a> <a
			href="usuario/perfil.do" class="seguidores">Mis seguidores</a>
	</div>
	<div class="lista-feed">${listadoTweet}</div>
	</div>
</body>
</html>
