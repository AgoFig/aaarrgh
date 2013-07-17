package aaarrgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Tweet;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.LoginService;
import aaarrgh.services.TweetService;

@Controller
@RequestMapping("/tweets")
public class TweetController {

	TweetService tweetService = new TweetService();

	@RequestMapping("/todos")
	public ModelAndView susTweets(@RequestParam("user") String user) throws PersistenceException {

		ModelAndView dispatch = null;
		
		String mensaje = new String();

		for (Tweet tweet : tweetService.getImproperios(user)) {
			mensaje += tweet.getTweet() +" <br />";
		}
		
		
		
			dispatch = new ModelAndView("../../index", "todosTweets", mensaje);
		

		return dispatch;

	}


	

}
