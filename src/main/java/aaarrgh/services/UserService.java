package aaarrgh.services;

import java.util.List;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.UsuarioDao;

public class UserService {
	private static UserService instance = new UserService();

	public UserService() {
	}

	public static UserService getInstance() {
		return instance;
	}

	public Usuario getUsuarioByName(String user) throws PersistenceException {
		UsuarioDao dao = DaoFactory.getUsuarioDao();
		Usuario usuario = null;
		usuario = dao.findByUser(user);
		return usuario;
	}
	
	public boolean seguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) {
		
		return miUsuario.seguirUser(usuarioSeguidor);

	}
	
	public boolean dejarDeSeguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) {
		
		List<Usuario> siguiendo = miUsuario.getSigue();
		
		if (siguiendo.contains(usuarioSeguidor)) {
			siguiendo.remove(usuarioSeguidor);
			return true;
		} 
		return false;

	}

}
	
