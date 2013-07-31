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
		
		dispatch = new ModelAndView("perfil", "usuario", user);

		return dispatch;
		/* END PAU */

	}

	// Obtener sus seguidores:
	@RequestMapping("/seguidores")
	public ModelAndView mostrarSeguidores(HttpServletRequest request)
			throws PersistenceException {
		
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		List<Usuario> meSiguen = usuarioService.getSeguidores(usuarioSession.getId());//le mando el id del usuario registrado para que traiga seguidores 
		List<Usuario> estoySiguiendo = usuarioService.getSigue(usuarioSession.getId());
		
		
		
		String listaSeguidores = "Mis seguidores son:<br />";
		for (Usuario usuario : meSiguen) {
			
			/* No se esta entrando en este if y no se porque. -Ago */
		if (estoySiguiendo.contains(usuario)) {
			listaSeguidores += "<div class='user clear'><span class='float-left'>@" + usuario.getUser()+" "+"</span><a href='../usuario/dejardeseguir.do?seguidor="+usuarioSession.getUser()+"&seguido="+usuario.getUser()+"'><span class='ui-icon ui-icon-minusthick float-left'>Dejar de seguir</span></a></div>"; /*Ago*///el getUser contiene los user`s de los seguidores
			
		}
		else {
			listaSeguidores += "<div class='user clear'><span class='float-left'>@" + usuario.getUser()+" "+"</span><a href='../usuario/seguir.do?seguidor="+usuarioSession.getUser()+"&seguido="+usuario.getUser()+"'><span class='ui-icon ui-icon-plusthick float-left'></span>Seguir</a></div>"; /*Ago*///el getUser contiene los user`s de los seguidores
			
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
				listaSiguiendo += "<div class='user clear'><span class='float-left'>@" + sigue.getUser()+" </span><a href='../usuario/dejardeseguir.do?seguidor="+usuarioSession.getUser()+"&seguido="+sigue.getUser()+"'><span class='ui-icon ui-icon-minusthick float-left'></span>Dejar de seguir</a></div>"; /*Ago*/
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

	//perfil ajeno - cecilia
	@RequestMapping("/ajeno")
	public ModelAndView perfilAjeno(HttpServletRequest request)
			throws PersistenceException {
		
		ModelAndView dispatch = null;
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		List<Usuario> similares = usuarioService.getSimilares(usuarioSession.getId());
		String listaSimilares = "Usuarios registrados:<br />";
		for (Usuario similar : similares) {
			
			listaSimilares +="<div class='user clear'><span class='float-left'> @" + similar.getUser()+" "+"</span><a href='../usuario/verperfil.do?perfila="+similar.getUser()+"'><span class='ui-icon ui-icon-minusthick float-left'></span>Ver perfil</a></div>"+ "<br />"; 
			}
		if (similares.isEmpty()) {
			dispatch = new ModelAndView("ajeno", "ajeno",
					"No hay usuarios registrados");
		} else {
			dispatch = new ModelAndView("ajeno", "ajeno",
					listaSimilares);
		}
		return dispatch;
	}
	
	
	// Mostrar perfil de un unico usuario 
	@RequestMapping("/verperfil")
	public ModelAndView mostrarperfil(@RequestParam("perfila") String perfila)
			throws PersistenceException {

		ModelAndView dispatch = null;
		Usuario usuario = usuarioService.getUsuarioByName(perfila);
		String perfilajeno = "Perfil del usuario seleccionado:<br />";
		perfilajeno = "<br />" + "<b>Nombre de usuario:</b>" + "@" + usuario.getUser()
				+ "<br /><br/>" + "<b>Nombre y Apellido:</b>" + usuario.getFullName()
				+ " <br /><br/> " + "<b>E-Mail:</b>" + usuario.getMail() + "<br />";

		dispatch = new ModelAndView("ajeno", "ajeno", perfilajeno);
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
