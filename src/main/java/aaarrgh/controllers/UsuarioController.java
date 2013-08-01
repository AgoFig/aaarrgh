package aaarrgh.controllers;

import java.util.ArrayList;
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

	// Muestra el perfil del usuario en sesión
	@RequestMapping("/perfil")
	public ModelAndView getPerfil(HttpServletRequest request)
			throws PersistenceException {

		ModelAndView dispatch = null;

		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		Usuario user = usuarioService
				.getUsuarioByName(usuarioSession.getUser());

		dispatch = new ModelAndView("perfil", "usuario", user);

		return dispatch;

	}

	// Obtiene a los seguidores de un usuario en particular
	@RequestMapping("/seguidores")
	public ModelAndView mostrarSeguidores(HttpServletRequest request)
			throws PersistenceException {

		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		List<Usuario> meSiguen = usuarioService.getSeguidores(usuarioSession
				.getId());// le mando el id del usuario registrado para que
							// traiga seguidores
		List<Usuario> estoySiguiendo = usuarioService.getSigue(usuarioSession
				.getId());

		String listaSeguidores = "<h2>Mis seguidores son:</h2>";
		for (Usuario usuario : meSiguen) {
			if (estoySiguiendo.contains(usuario)) {
				listaSeguidores += "<div class='user clear'><span class='seguidor float-left'>"
						+ "<a href='../usuario/verperfil.do?perfila="
						+ usuario.getUser()
						+ "'>@"
						+ usuario.getUser()
						+ "</a>"
						+ "</span><a href='../usuario/dejardeseguir.do?seguidor="
						+ usuarioSession.getUser()
						+ "&seguido="
						+ usuario.getUser()
						+ "'><span class='ui-icon ui-icon-minusthick float-left'></span>Dejar de seguir</a></div>";

			} else {
				listaSeguidores += "<div class='user clear'><span class='seguidor float-left'>"
						+ "<a href='../usuario/verperfil.do?perfila="
						+ usuario.getUser()
						+ "'>@"
						+ usuario.getUser()
						+ "</a>"
						+ "</span><a href='../usuario/seguir.do?seguidor="
						+ usuarioSession.getUser()
						+ "&seguido="
						+ usuario.getUser()
						+ "'><span class='ui-icon ui-icon-plusthick float-left'></span>Seguir</a></div>";
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

	// Obtiene a los usuarios que se estan siguiendo
	@RequestMapping("/siguiendo")
	public ModelAndView mostrarSiguiendo(HttpServletRequest request)
			throws PersistenceException {

		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");

		List<Usuario> siguiendo = usuarioService.getSigue(usuarioSession
				.getId());

		ModelAndView dispatch = null;
		if (!siguiendo.isEmpty()) {
			dispatch = new ModelAndView("siguiendo", "siguiendo", siguiendo);

		} else {
			dispatch = new ModelAndView("siguiendo", "mensajeSiguiendo",
					"No esta siguiendo a nadie");
		}

		return dispatch;

	}

	// Sigue a un usuario en particular
	@RequestMapping("/seguir")
	public ModelAndView seguir(@RequestParam("seguidor") String seguidor,
			@RequestParam("seguido") String seguido)
			throws PersistenceException {

		ModelAndView dispatch = null;
		Usuario usuarioSeguidor = usuarioService.getUsuarioByName(seguidor);
		Usuario usuarioSeguido = usuarioService.getUsuarioByName(seguido);

		usuarioService.seguirUsuario(usuarioSeguidor, usuarioSeguido);

		dispatch = new ModelAndView("siguiendo", "mensajeSiguiendo",
				"Ahora esta siguiendo a " + seguido);

		return dispatch;

	}

	// Deja de seguir a un usuario en particular
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

	// Arma la lista de los usuarios que podría seguir
	@RequestMapping("/ajeno")
	public ModelAndView perfilAjeno(HttpServletRequest request)
			throws PersistenceException {

		ModelAndView dispatch = null;
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");

		List<Usuario> sugeridos = usuarioService.getSimilares(usuarioSession
				.getId());

		List<Usuario> siguiendo = usuarioService.getSigue(usuarioSession
				.getId());

		List<Usuario> listPuedoSeguir = new ArrayList<Usuario>();

		for (Usuario usuario : sugeridos) {

			if (!siguiendo.contains(usuario)) {

				listPuedoSeguir.add(usuario);
			}
		}

		if (listPuedoSeguir.isEmpty()) {
			dispatch = new ModelAndView("ajeno", "mensajeSugeridos",
					"No hay usuarios sugeridos");
		} else {
			dispatch = new ModelAndView("ajeno", "listPuedoSeguir",
					listPuedoSeguir);
		}
		return dispatch;
	}

	// Muestra el perfil de un usuario en particular
	@RequestMapping("/verperfil")
	public ModelAndView mostrarperfil(@RequestParam("perfila") String perfila,
			HttpServletRequest request) throws PersistenceException {

		ModelAndView dispatch = null;
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		Usuario usuario = usuarioService.getUsuarioByName(perfila);

		List<Usuario> estoySiguiendo = usuarioService.getSigue(usuarioSession
				.getId());
		String perfilajeno = "Perfil del usuario seleccionado:<br />";

		if (estoySiguiendo.contains(usuario)) {
			perfilajeno = "<br />"
					+ "<b>Nombre de usuario:</b>"
					+ "@"
					+ usuario.getUser()
					+ "<br /><br/>"
					+ "<b>Nombre y Apellido:</b>"
					+ usuario.getFullName()
					+ " <br /><br/> "
					+ "<b>E-Mail:</b>"
					+ usuario.getMail()
					+ "<br />"
					+ "<div class='user clear'><a href='../usuario/dejardeseguir.do?seguidor="
					+ usuarioSession.getUser()
					+ "&seguido="
					+ usuario.getUser()
					+ "'><span class='ui-icon ui-icon-minusthick float-left'></span>Dejar de Seguir</a></div>";

		} else {
			perfilajeno = "<br />"
					+ "<b>Nombre de usuario:</b>"
					+ "@"
					+ usuario.getUser()
					+ "<br /><br/>"
					+ "<b>Nombre y Apellido:</b>"
					+ usuario.getFullName()
					+ " <br /><br/> "
					+ "<b>E-Mail:</b>"
					+ usuario.getMail()
					+ "<br />"
					+ "<div class='user clear'><a href='../usuario/seguir.do?seguidor="
					+ usuarioSession.getUser()
					+ "&seguido="
					+ usuario.getUser()
					+ "'><span class='ui-icon ui-icon-plusthick float-left'></span>Seguir</a></div>";

		}

		dispatch = new ModelAndView("ajeno", "ajeno", perfilajeno);
		return dispatch;
	}

}
