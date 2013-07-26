package aaarrgh.controllers;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.List;


import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.TweetService;

@Controller 
@RequestMapping("/tweet")
public class TweetController extends HttpServlet {


	private static final long serialVersionUID = 1763031863968854468L;
	TweetService tweetService = new TweetService(); 
	
	@RequestMapping("/postear")
	public ModelAndView insertarTweet(@RequestParam("comentario") String comentario, HttpServletRequest request) throws PersistenceException {
		
		ModelAndView dispatch = null;
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");
		
		// cargo el objeto tweet
		Tweet tweet = new Tweet();
		tweet.setTweet(comentario);
		tweet.setIduser( usuarioSession.getId());
		
		if (tweetService.insertTweet(tweet)) {
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + usuarioSession.getUser()); 
		} else {
			dispatch = new ModelAndView("welcome", "message", "Ingreso incorrecto" + usuarioSession.getUser());
		}

		return dispatch;

	}

	@RequestMapping("/listar")
	public ModelAndView verTweets(HttpServletRequest request) throws PersistenceException {
		
		HttpSession session = request.getSession(true);
		Usuario usuarioSession = new Usuario();
		usuarioSession = (Usuario) session.getAttribute("userObject");

		
		// traigo todos los tweets de el user y de los que sigue
		
		List<Tweet> tweets = tweetService.getImproperios(usuarioSession.getId());
		String generaLista = null;
		for (Tweet tweet : tweets) {
			generaLista += tweet.getTweet()+"<br />";
		}
		
		ModelAndView dispatch = null;
		if (tweets.isEmpty()) {
		dispatch = new ModelAndView("welcome", "listadoTweet", "No hay tweets."); 
	} else {
			dispatch = new ModelAndView("welcome", "listadoTweet", generaLista);
		}

		return dispatch;

	}

}
