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
	public ModelAndView getPerfil(HttpServletRequest request)
			throws PersistenceException {

		ModelAndView dispatch = null;

		/* BEGIN PAU */
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		Usuario user = usuarioService
				.getUsuarioByName(usuarioSession.getUser());
		/* END PAU */

		String perfil = null;
		perfil = user.getFullName() + user.getMail();

		dispatch = new ModelAndView("perfil", "perfil", perfil);

		return dispatch;

	}

	// Obtener sus seguidores - cecilia
	@RequestMapping("/seguidores")
	public ModelAndView mostrarSeguidores(HttpServletRequest request)
			throws PersistenceException {
		
		ModelAndView dispatch = null;
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		Usuario user = usuarioService.getUsuarioByName(usuarioSession.getUser());//busco al usuario que inicio sesion segun su user
		
		List<Usuario> meSiguen = usuarioService.getSeguidores(user.getId());//le mando el id de ese user para que traiga seguidores a una lista

		String listaSeguidores = null;
		for (Usuario usuario : meSiguen) {
			listaSeguidores += " @" + usuario.getUser();//de getSeguidores obtengo los id's y aca estoy pidiendo los user's de los seguidores...¿?
		}

		if (meSiguen.isEmpty()) {
			dispatch = new ModelAndView("seguidores", "listadoSeguidores",
					"No tiene seguidores");
		} else {
			dispatch = new ModelAndView("seguidores", "listadoSeguidores",
					listaSeguidores);
		}

		return dispatch;

	}
//Siguiendo (falta terminar) - cecilia
	@RequestMapping("/siguiendo")
	public ModelAndView mostrarSiguiendo(Usuario user)
			throws PersistenceException {

		List<Usuario> estoySiguiendo = user.getSigue();

		String listaSiguiendo = null;
		for (Usuario usuario : estoySiguiendo) {
			listaSiguiendo += "@" + usuario.getUser();
		}

		ModelAndView dispatch = null;
		if (estoySiguiendo.isEmpty()) {
			dispatch = new ModelAndView("seguiendo", "listadoSiguiendo",
					"No esta siguiendo a nadie");
		} else {
			dispatch = new ModelAndView("seguiendo", "listadoSiguiendo",
					listaSiguiendo);
		}

		return dispatch;

	}
//seguir - modificar
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

	// Dejar de seguir - modificar
	@RequestMapping("/dejardeseguir")
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

//perfil ajeno - modificar
	@RequestMapping("/perfilajeno")
	public ModelAndView perfilAjeno(String user)
			
			throws PersistenceException {
		ModelAndView dispatch = null;
		usuarioService.verPerfilAjeno(
				usuarioService.getUsuarioByName(user);
		dispatch = new ModelAndView("perfilajeno", "mensaje", "Ver perfil ajeno de"
				+ user);
		return dispatch;
	}
	
}
