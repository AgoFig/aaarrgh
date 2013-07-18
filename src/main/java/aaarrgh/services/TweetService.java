package aaarrgh.services;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.TweetDao;

public class TweetService {
	private static TweetService instance = new TweetService();

	public Boolean insertTweet(Tweet tweet)
			throws PersistenceException {
		
		TweetDao dao = DaoFactory.getTweetDao();		
		Boolean result = true;
		
		 dao.insert(tweet); 
		
		return result;
	}
	public TweetService() {

	}

	public static TweetService getInstance() {
		return instance;
	}

	public char[] getCantidadImproperios(Usuario miUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

}

