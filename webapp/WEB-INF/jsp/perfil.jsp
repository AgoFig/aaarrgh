<%@page import="java.io.PrintStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Mi Perfil</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
	<div id="welcome-wrapper ">
		<div class="container ">
			<div class="header clear">
				<div class="barra-top clear">
					<div class="color rojo"></div>
					<div class="color naranja"></div>
					<div class="color gris"></div>
					<div class="color negro"></div>
					<div class="color blanco"></div>
				</div>
				<div class="logo float-left">
					<a href="../tweet/listar.do"> <img alt="logo"
						src="../img/logo.png">
					</a>
				</div>
				<div class="mini-pirata float-right">
					<img alt="mini pirata" src="../img/mini-pirata.png">
				</div>
			</div>
			<div class="contenido clear">
				<div class="barra-postear rojo">
					<form class="hidden" action="../tweet/postear.do">
						<input id="postear-twit" type="text"
							value="Que hay de nuevo marinero?" name="comentario"
							maxlength="140" /> <input type="submit" class="btn"
							value="Aaarrgh!" />
						<p class="float-right">${message}</p>
					</form>
				</div>
				<div class="tweets float-left">
					<div class="perfil-data">
						<label class="label">Nombre y Apellido: </label> ${usuario.getFullName()}
					</div>
					<div class="perfil-data">
						<label class="label">E-mail: </label> ${usuario.mail}
					</div>
				</div>
				<div class="barra-lateral float-right rojo">

					<div>
						<a href="../tweet/listar.do">Ver Tweets</a>
					</div>
					<div>
						<a href="../usuario/siguiendo.do" class="sigo">A quienes sigo</a>
					</div>
					<div>
						<a href="../usuario/seguidores.do" class="seguidores">Mis
							seguidores</a>
					</div>
					<div>
						<a href="../usuario/ajeno.do" class="ajeno">Usuarios sugeridos</a>
					</div>
					<div>
						<a href="../index.jsp" class="seguidores">Logout</a>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
