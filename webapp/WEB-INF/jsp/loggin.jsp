<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script src="../js/gen_validatorv4.js" type="text/javascript"></script>
</head>
<body>
	<div id="login-wrapper">
		<div class="container">
			<div class="barra-top">
				<div class="color rojo"></div>
				<div class="color naranja"></div>
				<div class="color gris"></div>
				<div class="color negro"></div>
				<div class="color blanco"></div>
			</div>
			<div class="login float-left grid50">
				<div class="logo">
					<img alt="logo" src="../img/logo.png">
				</div>
				<form name="login1" id="login1" action="../login/auth.do"
					class="form-horizontal">
					
					<div class="control-group">
						<label class="control-label" for="inputUsername">Nombre de
							usuario</label>
						<div class="controls">
							<input type="text" name="user" id="inputUsername">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">Contraseņa</label>
						<div class="controls">
							<input type="password" name="password" id="inputPassword">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn">Ingresar</button>
						</div>
					</div>
					<div class="alert"><h4>${message}</h4></div><br/>
				</form>
				<script type="text/javascript">
					var frmvalidator = new Validator("login1");
					frmvalidator.addValidation("user", "req",
							"Ingrese un nombre de usuario.");
					frmvalidator.addValidation("password", "req",
							"Ingrese un password.");
				</script>
			</div>
			<div class="pirata float-left grid50">
				<img alt="pirata aaarrrgh" src="../img/pirata.png">
			</div>

			<div class="footer">
				<p>2013 | Aaarrgh</p>
			</div>

		</div>
	</div>
</body>
</html>