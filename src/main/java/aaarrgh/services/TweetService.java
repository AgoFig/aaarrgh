package aaarrgh.services;

import aaarrgh.model.Usuario;

public class TweetService {
	private static TweetService instance = new TweetService();

	private TweetService() {

	}

	public static TweetService getInstance() {
		return instance;
	}

	public char[] getCantidadImproperios(Usuario miUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

}

