package aaarrgh.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LoginService loginService = new LoginService();

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

		if (loginService.authenticate(user, password)) {
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + user); 
		} else {
			dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
		}

		return dispatch;

	}

	public ModelAndView logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
