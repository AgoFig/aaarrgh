<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="aaarrgh.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Siguiendo</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />

<!-- jQuery -->
<link href="../css/blitzer/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<script src="../js/jquery-1.9.1.js"></script>
<script src="../js/jquery-ui-1.10.3.custom.js"></script>

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
					<a href="../tweet/listar.do>"><img alt="logo"
						src="../img/logo.png"></a>
				</div>
				<div class="mini-pirata float-right">
					<img alt="mini pirata" src="../img/mini-pirata.png">
				</div>
			</div>
			<div class="contenido clear rojo">
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
					<%
						Usuario user = (Usuario) session.getAttribute("userObject");
					%>
					<div class="lista-feed">${mensaje}</div>
					<c:choose>
						<c:when test="${ not empty siguiendo}">
							<h2>Estoy siguiendo a:</h2>
							<c:forEach var="siguiendo" items="${siguiendo}">
								<div class="user clear">
									<span class="float-left">@ ${siguiendo.user}</span> <a
										href="../usuario/dejardeseguir.do?seguidor=<%= user.getUser()%>&seguido=${siguiendo.user}">
										<span class='ui-icon ui-icon-minusthick float-left'></span>Dejar
										de seguir
									</a>
								</div>
								<div class="user clear">
									<a href="../usuario/verperfil.do?perfila=${siguiendo.user}">
										<span class='ui-icon ui-icon-person float-left'></span>Ver
										perfil
									</a>
								</div>

							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:if test="${not empty mensajeSugeridos}">
								<p class="float-left">${mensajeSugeridos}</p>
							</c:if>
							<c:if test="${not empty mensajeSiguiendo}">
								<p class="float-left">${mensajeSiguiendo}</p>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="barra-lateral float-right rojo">

					<div>
						<a href="../tweet/listar.do" class="miperfil">Ver Tweets</a>
					</div>
					<div>
						<a href="../usuario/perfil.do" class="miperfil">Mi Perfil</a>
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
