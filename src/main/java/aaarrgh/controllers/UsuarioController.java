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

	// Obtener sus seguidores:
	@RequestMapping("/seguidores")
	public ModelAndView mostrarSeguidores(HttpServletRequest request)
			throws PersistenceException {
		
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		List<Usuario> meSiguen = usuarioService.getSeguidores(usuarioSession.getId());//le mando el id del usuario registrado para que traiga seguidores 
		List<Usuario> estoySiguiendo =usuarioService.getSigue(usuarioSession.getId());
		
		
		
		String listaSeguidores = "Mis seguidores son:<br />";
		for (Usuario usuario : meSiguen) {
			
			/* No se esta entrando en este if y no se porque. -Ago */
		if (estoySiguiendo.contains(usuario)) {
			listaSeguidores += " @" + usuario.getUser()+" "+"<a href='../usuario/dejardeseguir.do?seguidor="+usuarioSession.getUser()+"&seguido="+usuario.getUser()+"'>Dejar de Seguir</a><br />"; /*Ago*///el getUser contiene los user`s de los seguidores
			
		}
		else {
			listaSeguidores += " @" + usuario.getUser()+" "+"<a href='../usuario/seguir.do?seguidor="+usuarioSession.getUser()+"&seguido="+usuario.getUser()+"'>Seguir</a><br />"; /*Ago*///el getUser contiene los user`s de los seguidores
			
		}
		
		}

		ModelAndView dispatch = null;
		
		if (meSiguen.isEmpty()) {
			dispatch = new ModelAndView("seguidores", "seguidores",
					"No tiene seguidores");
		} else {
			dispatch = new ModelAndView("seguidores", "seguidores",
					listaSeguidores);
		}

		return dispatch;
	}
	
	
	//Obtener a los que esta siguiendo:
		@RequestMapping("/siguiendo")
		public ModelAndView mostrarSiguiendo(HttpServletRequest request)
				throws PersistenceException {

			HttpSession session = request.getSession(true);
			Usuario usuarioSession = new Usuario();
			usuarioSession = (Usuario) session.getAttribute("userObject");

			List<Usuario> siguiendo = usuarioService.getSigue(usuarioSession
					.getId());
			String listaSiguiendo = "Estoy siguiendo a:<br />";
			for (Usuario sigue : siguiendo) {
				listaSiguiendo += "@" + sigue.getUser()+"  <a href='../usuario/dejardeseguir.do?seguidor="+usuarioSession.getUser()+"&seguido="+sigue.getUser()+"'>Dejar de Seguir</a><br />"; /*Ago*/
			}

			ModelAndView dispatch = null;
			if (siguiendo.isEmpty()) {
				dispatch = new ModelAndView("siguiendo", "siguiendo",
						"No esta siguiendo a nadie");
			} else {
				dispatch = new ModelAndView("siguiendo", "siguiendo",
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
		Usuario usuarioSeguidor = usuarioService.getUsuarioByName(seguidor);
		Usuario usuarioSeguido = usuarioService.getUsuarioByName(seguido);
		
		usuarioService.seguirUsuario(usuarioSeguidor,usuarioSeguido);

		dispatch = new ModelAndView("siguiendo", "mensajeSiguiendo",
				"Ahora esta siguiendo a " + seguido);

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
		dispatch = new ModelAndView("siguiendo", "mensaje", "Dejo de seguir a "
				+ seguido);
		return dispatch;
	}

//perfil ajeno - modificar
	@RequestMapping("/perfilajeno")
	public ModelAndView perfilAjeno(String user)
			
			throws PersistenceException {
		ModelAndView dispatch = null;
		usuarioService.verPerfilAjeno(
				usuarioService.getUsuarioByName(user));
		dispatch = new ModelAndView("perfilajeno", "mensaje", "Ver perfil ajeno de"
				+ user);
		return dispatch;
	}
	
//	@RequestMapping("/volverWelcome")
//	public ModelAndView authenticate(
//		HttpServletRequest request) throws PersistenceException {
//			
//	ModelAndView dispatch = null;
//	HttpSession session = request.getSession(true);
//	
//	if (usuario.getValido()) {
//		
//		Usuario usuarioSession = new Usuario();
//		usuarioSession = (Usuario) session.getAttribute("userObject");
//		dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + usuarioSession.getUser()); 
//	} else {
//		dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
//	}
//
//	return dispatch;
//
//}
	
}
