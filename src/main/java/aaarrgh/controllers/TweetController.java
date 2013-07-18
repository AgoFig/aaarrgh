package aaarrgh.controllers;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Tweet;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.TweetService;

@Controller
@SessionAttributes("idUsuario") 
@RequestMapping("/tweet")
public class TweetController extends HttpServlet {


	private static final long serialVersionUID = 1763031863968854468L;
	TweetService tweetService = new TweetService(); 
	
	@RequestMapping("/postear")
	public ModelAndView insertarTweet(@RequestParam("comentario") String comentario) throws PersistenceException {
		
		// cargo el objeto tweet
		 ModelAndView modelAndView = new ModelAndView();
		Tweet tweet = new Tweet();
		tweet.setTweet(comentario);
		//tweet.setIduser( modelAndView.);
		
		ModelAndView dispatch = null;
		
		tweetService.insertTweet(tweet);
		
		if (tweetService.insertTweet(tweet)) {
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" ); 
		} else {
			dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
		}

		return dispatch;

	}

	public ModelAndView logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
