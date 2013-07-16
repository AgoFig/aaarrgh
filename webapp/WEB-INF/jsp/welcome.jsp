<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bienvenido</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div class="header">
		<h1>Aaarrgh</h1>
		<h3>Version 2.0</h3>
	</div>
	<p>${message}</p>
	<div>
		<form
			action="tweet/postear.do" >
			<input type="text" value="Que hay de nuevo marinero?" />
			<input type="submit" class="btn" />
		</form>
	</div>
	<div class="barra-lateral" >
		<a href="usuario/perfil.do">Mi Perfil</a>
		<a href="usuario/perfil.do">A quienes sigo</a>
		<a href="usuario/perfil.do">Mis seguidores</a>
	</div>
	<div class="lista-feed">
	${listadoTweet}
	</div>
</body>
</html>
