package aaarrgh.controllers;

import java.util.List;

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
	
	@RequestMapping("/seguidores")
	public ModelAndView mostrarSeguidores(Usuario user) throws PersistenceException {

		ModelAndView dispatch = null;

		
		List<Usuario> meSigue = user.getSeguidores();
		
		String listaSeguidores = null;
		for (Usuario usuario : meSigue) {
			listaSeguidores += "@"+usuario.getUser();
		}
		
			dispatch = new ModelAndView("../../index", "listaSeguidores", listaSeguidores);
		
		return dispatch;

	}

	@RequestMapping("/siguiendo")
	public ModelAndView mostrarSiguiendo(Usuario user) throws PersistenceException {

		ModelAndView dispatch = null;

		
		List<Usuario> estoySiguiendo = user.getSigue();
		
		String listaSiguiendo = null;
		for (Usuario usuario : estoySiguiendo) {
			listaSiguiendo += "@"+usuario.getUser();
		}
		
			dispatch = new ModelAndView("../../index", "listaSiguiendo", listaSiguiendo);
		
		return dispatch;

	}

}
