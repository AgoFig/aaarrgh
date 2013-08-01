<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="aaarrgh.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bienvenido</title>
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
					<a href="../tweet/listar.do"><img alt="logo"
						src="../img/logo.png"></a>
				</div>
				<div class="mini-pirata float-right">
					<img alt="mini pirata" src="../img/mini-pirata.png">
				</div>
			</div>

			<div class="contenido clear rojo">
				<div class="barra-postear rojo">
					<form action="../tweet/postear.do">

						<textarea id="postear-twit" name="comentario"
							onfocus="if(this.value == '¿Que hay de nuevo marinero?') { this.value = ''; }"
							onblur="if (this.value == '') { this.value='¿Que hay de nuevo marinero?'; }"
							maxlength="140" style="resize: none;">¿Que hay de nuevo marinero?</textarea>

						<input type="submit" class="btn" value="Aaarrgh!" />



						<%
							Usuario user = (Usuario) session.getAttribute("userObject");
						%>
						<p class="float-right">
							Bienvenido @<%=user.getUser()%></p>
					</form>
				</div>

				<div id="listado" class="tweets float-left">
					<c:choose>
						<c:when test="${ not empty listadoTweet}">
							<c:forEach var="tweet" items="${listadoTweet}">
								<div class="tweets float-left">
									<p>${tweet.tweet}</p>
									<p class="line">@ ${tweet.userName}</p>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p class="float-left">${message}</p>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="barra-lateral float-right rojo">

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
