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
	


	//devuelve los seguidores de ese usuario:
	public List<Usuario> getSeguidores(Integer iduser) throws PersistenceException { 
			
		    UsuarioDao dao = DaoFactory.getUsuarioDao();		
			List<Usuario> seguidores = dao.traerSeguidoresDeUnUsuario(iduser); 
			
			return seguidores;		
		}
		
	//devuelve los que estoy siguiendo:
	public List <Usuario> getSigue(Integer iduser) throws PersistenceException{
		UsuarioDao dao = DaoFactory.getUsuarioDao();
		List<Usuario> siguiendo = dao.traerLosQueEstoySiguiendo(iduser);
		return siguiendo;
	}
	
	//seguir usuario:
	public boolean seguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) {
		
		return miUsuario.seguirUser(usuarioSeguidor);

	}
	
	//dejar de seguir:
	public boolean dejarDeSeguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) {
		
		List<Usuario> siguiendo = miUsuario.getSigue();
		
		if (siguiendo.contains(usuarioSeguidor)) {
			siguiendo.remove(usuarioSeguidor);
			return true;
		} 
		return false;
	}

//ver pefil ajeno:
	public void verPerfilAjeno(Usuario usuarioByName) {
		// TODO Auto-generated method stub
		
	}

   
}
	
