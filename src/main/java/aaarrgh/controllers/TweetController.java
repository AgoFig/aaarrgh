package aaarrgh.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.TweetService;

@Controller
@RequestMapping("/tweet")
public class TweetController {

	TweetService tweetService = new TweetService(); 
	
	@RequestMapping("/postear")
	public ModelAndView insertarTweet(@RequestParam("comentario") String comentario) throws PersistenceException {
		
		// cargo el objeto tweet
		
		Tweet tweet = new Tweet();
		tweet.setTweet(comentario);
		tweet.setIduser(9);
		
		ModelAndView dispatch = null;
		
		tweetService.insertTweet(tweet);
		
		//if (tweetService.insertTweet(tweet)) {
//			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + user); 
//		} else {
//			dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
		//}

		return dispatch;

	}

	@RequestMapping("/listar")
	public ModelAndView verTweets() throws PersistenceException {
		
		//Reviso la sesion
		//Usuario sesion = (Usuario)request.getSession().getAttribute("usuario");
		
		// traigo todos los tweets de el user y de los qeu sigue
		
		List<Tweet> tweets = tweetService.getImproperios("paulitta");
		String generaLista = null;
		for (Tweet tweet : tweets) {
			generaLista += tweet.getTweet()+"<br />";
		}
		
		ModelAndView dispatch = null;
		//if (tweets.isEmpty()) {
		dispatch = new ModelAndView("welcome", "listadoTweet", "No hay tweets."); 
	//} else {
	//		dispatch = new ModelAndView("welcome", "listadoTweet", generaLista);
	//	}

		return dispatch;

	}

}
