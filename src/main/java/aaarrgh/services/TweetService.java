package aaarrgh.services;

import java.awt.List;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.TweetDao;
import aaarrgh.persistence.UsuarioDao;

public class TweetService {
	private static TweetService instance = new TweetService();

	public TweetService() {

	}

	public static TweetService getInstance() {
		return instance;
	}

	public char[] getCantidadImproperios(Usuario miUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public <Tweet>List getImproperios(String user) {
		
		UsuarioDao daoUser = DaoFactory.getUsuarioDao();		
		Boolean result = false;
				
		Usuario correcto = new Usuario();
		List <Tweet> lista = daoUser.traerTweetsDeQuienesSigo(correcto.getId());
				
		
		return lista;
	}
}

