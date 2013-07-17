package aaarrgh.services;

import java.util.List;

import aaarrgh.model.Usuario;

public class UserService {
	private static UserService instance = new UserService();

	public UserService() {
	}

	public static UserService getInstance() {
		return instance;
	}

	public Usuario getUsuarioByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean seguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) {
		
		return miUsuario.seguirOtros(usuarioSeguidor);

	}
	
	public boolean dejarDeSeguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) {
		
		List<Usuario> following = miUsuario.getSigue();
		
		if (following.contains(usuarioSeguidor)) {
			following.remove(usuarioSeguidor);
			return true;
		} 
		return false;

	}

}
	
