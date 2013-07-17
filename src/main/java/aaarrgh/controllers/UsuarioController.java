package aaarrgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.UserService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	UserService usuarioService = new UserService();

	@RequestMapping("/perfil")
	public ModelAndView getPerfil(Usuario user) throws PersistenceException {

		ModelAndView dispatch = null;

		
		String perfil = user.getFullName()+user.getMail();
		
		
			dispatch = new ModelAndView("../../index", "perfil", perfil);
		
		return dispatch;

	}

	public ModelAndView logout() {
		ModelAndView dispatch = null;
		
		dispatch = new ModelAndView("../../index", "message", "Logout exitoso.");
		
		return dispatch;
	}

}
