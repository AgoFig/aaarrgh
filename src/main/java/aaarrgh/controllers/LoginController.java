package aaarrgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	LoginService loginService = new LoginService();

	@RequestMapping("/auth")
	public ModelAndView authenticate(
			@RequestParam("user") String user,
			@RequestParam("password") String password) throws PersistenceException {

		ModelAndView dispatch = null;

		if (loginService.authenticate(user, password)) {
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + user); 
		} else {
			dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
		}

		return dispatch;

	}
	
	@RequestMapping("/out")
	public ModelAndView logout() {
		ModelAndView dispatch = null;
		
		dispatch = new ModelAndView("../../index", "message", "Logout exitoso.");
		
		return dispatch;
	}

}
