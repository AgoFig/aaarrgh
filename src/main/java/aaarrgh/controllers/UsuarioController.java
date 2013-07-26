package aaarrgh.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.UserService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	UserService usuarioService = new UserService();

	@RequestMapping("/perfil")
	public ModelAndView getPerfil(HttpServletRequest request) throws PersistenceException {

		ModelAndView dispatch = null;
		
		/* BEGIN PAU */
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");	
		Usuario user = usuarioService.getUsuarioByName(usuarioSession.getUser());
		/* END PAU */
		
		String perfil = null;
		perfil = user.getFullName() + user.getMail();

		dispatch = new ModelAndView("perfil", "perfil", perfil);

		return dispatch;

	}

	@RequestMapping("/seguidores")
	public ModelAndView mostrarSeguidores(Usuario user)
			throws PersistenceException {

		ModelAndView dispatch = null;

		List<Usuario> meSigue = user.getSeguidores();

		String listaSeguidores = null;
		for (Usuario usuario : meSigue) {
			listaSeguidores += " @" + usuario.getUser();
		}

		dispatch = new ModelAndView("seguidores", "listaSeguidores",
				listaSeguidores);

		return dispatch;

	}

	@RequestMapping("/siguiendo")
	public ModelAndView mostrarSiguiendo(Usuario user)
			throws PersistenceException {

		ModelAndView dispatch = null;

		List<Usuario> estoySiguiendo = user.getSigue();

		String listaSiguiendo = null;
		for (Usuario usuario : estoySiguiendo) {
			listaSiguiendo += "@" + usuario.getUser();
		}

		dispatch = new ModelAndView("siguiendo", "listaSiguiendo",
				listaSiguiendo);

		return dispatch;

	}

	@RequestMapping("/seguir")
	public ModelAndView seguir(@RequestParam("seguidor") String seguidor,
			@RequestParam("seguido") String seguido)
			throws PersistenceException {

		ModelAndView dispatch = null;

		usuarioService.seguirUsuario(usuarioService.getUsuarioByName(seguidor),
				usuarioService.getUsuarioByName(seguido));

		dispatch = new ModelAndView("siguiendo", "mensajeSiguiendo",
				"Ahroa esta siguiendo a " + seguido);

		return dispatch;

	}
	
	// Dejar de seguir:
	@RequestMapping("/DejarDeSeguir")
	public ModelAndView dejarDeSeguir(
			@RequestParam("seguidor") String seguidor,
			@RequestParam("seguido") String seguido)
			throws PersistenceException {
		ModelAndView dispatch = null;
		usuarioService.dejarDeSeguirUsuario(
				usuarioService.getUsuarioByName(seguidor),
				usuarioService.getUsuarioByName(seguido));
		dispatch = new ModelAndView("siguiendo", "mensaje", "Dejo de seguir a"
				+ seguido);
		return dispatch;
	}

}
