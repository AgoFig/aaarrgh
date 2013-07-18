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
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		HttpSession session = request.getSession(true);
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		session.setAttribute("user", user);
		session.setAttribute("password", password);
		
		try {
			authenticate(user, password);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		@RequestMapping("/auth")
		public ModelAndView authenticate(
			@RequestParam("user") String user,
			@RequestParam("password") String password) throws PersistenceException {
		
//		HttpSession session = null;
//		
//		session.setAttribute("user", user);
//		
//		String usuario = (String) session.getAttribute("user"); 
//		
		ModelAndView dispatch = null;
		
		Usuario usuario = new Usuario();
		usuario = loginService.authenticate(user, password);
		if (usuario.getValido()) {
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + user); 
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
