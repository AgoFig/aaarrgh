<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Seguidores</title>
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
					<img alt="logo" src="../img/logo.png">
				</div>
				<div class="mini-pirata float-right">
					<img alt="mini pirata" src="../img/mini-pirata.png">
				</div>
			</div>
			<div class="contenido clear">
				<div class="barra-postear rojo">
					<form class="hidden" action="../tweet/postear.do">
						<input id="postear-twit" type="text" value="Que hay de nuevo marinero?" name="comentario" maxlength="140" /> <input
							type="submit" class="btn" value="Aaarrgh!"/>
						<p class="float-right">${message}</p>
					</form>
				</div>
				<div class="tweets float-left">
					<div class="lista-feed">${seguidores}</div>
				</div>
				<div class="barra-lateral float-right rojo">
					
					<div>
						<a href="../login/auth.do" class="miperfil">Ver Tweets</a>
					</div>
					<div>
						<a href="../usuario/perfil.do" class="miperfil">Mi Perfil</a>
					</div>
					<div>
						<a href="../usuario/siguiendo.do" class="sigo">A quienes sigo</a>
					</div>
					<div>
						<a href="../usuario/seguidores.do" class="seguidores">Mis seguidores</a>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>
