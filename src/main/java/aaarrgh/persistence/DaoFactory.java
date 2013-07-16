package aaarrgh.persistence;

public class DaoFactory {

	public static UsuarioDao getUsuarioDao(){
		return UsuarioDaoJdbcImpl.getInstance();
	}

	public static TweetDao getTweetDao() {
		return TweetDaoJdbcImpl.getInstance();
		
	}

	public static PersonaDao getPersonaDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
