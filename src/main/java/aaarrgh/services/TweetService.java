package aaarrgh.services;

import java.util.ArrayList;
import java.util.List;

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

	public int getCantidadImproperios(Usuario miUsuario) throws PersistenceException {
		TweetDao dao = DaoFactory.getTweetDao();	
		
		 List<Tweet> tweets = dao.findAllFromUser(miUsuario.getId()); // esto cambia por el id del usuario
		int cantidad = tweets.size();
		return cantidad;	
	}
	public List<Tweet> getImproperios(Integer idUser) throws PersistenceException { // esto cambia el String por un int ya que el metodo requiere in INT
		
		TweetDao dao = DaoFactory.getTweetDao();		
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		 tweets= dao.findAllFromUser(idUser); 
		
		return tweets;		
	}

}

