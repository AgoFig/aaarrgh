package aaarrgh.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	LoginService loginService = new LoginService();

		@RequestMapping("/auth")
		public ModelAndView authenticate(
			@RequestParam("user") String user,
			@RequestParam("password") String password,
			HttpServletRequest request) throws PersistenceException {
				
		ModelAndView dispatch = null;
		
		Usuario usuario = new Usuario();
		usuario = loginService.authenticate(user, password);
		if (usuario.getValido()) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("userObject", usuario);
			
			//mapa con todo (HashMap)
			
			Usuario usuarioSession = new Usuario();
			usuarioSession = (Usuario) session.getAttribute("userObject");
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + usuarioSession.getUser()); 
		} else {
			dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
		}

		return dispatch;

	}

	public ModelAndView logout() {
		ModelAndView dispatch = null;
		
		dispatch = new ModelAndView("../../index", "message", "Logout exitoso.");
		
		return dispatch;
	}

}
