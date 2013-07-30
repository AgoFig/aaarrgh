package aaarrgh.controllers;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.LoginService;
import aaarrgh.services.TweetService;

/* BEGIN PAU */

@Controller
@RequestMapping("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	LoginService loginService = new LoginService();
	TweetService tweetService = new TweetService(); 

		@RequestMapping("/auth")
		public ModelAndView authenticate(
			@RequestParam("user") String user,
			@RequestParam("password") String password,
			HttpServletRequest request) throws PersistenceException {
				
		ModelAndView dispatch = null;
		
		Usuario usuario = new Usuario();
		usuario = loginService.authenticate(user, password);
		if (usuario.getValido()) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("userObject", usuario);

			Usuario usuarioSession = new Usuario();
			usuarioSession = (Usuario) session.getAttribute("userObject");
		
			List<Tweet> tweets = tweetService.getImproperios(usuarioSession.getId());
			
			if(!tweets.isEmpty()){
				dispatch = new ModelAndView("welcome", "listadoTweet", tweets); 
			}else{
				
				dispatch = new ModelAndView("welcome", "message", "No hay tweets!"); 
			}
			
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

/* END PAU */
