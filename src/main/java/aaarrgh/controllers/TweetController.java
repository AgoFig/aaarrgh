package aaarrgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Tweet;
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

	public ModelAndView logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
