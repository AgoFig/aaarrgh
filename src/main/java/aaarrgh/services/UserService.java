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
	public void seguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) throws PersistenceException {
		UsuarioDao dao = DaoFactory.getUsuarioDao();
		dao.seguir(miUsuario, usuarioSeguidor);		

	}
	
	//dejar de seguir:
	public void dejarDeSeguirUsuario(Usuario miUsuario,Usuario usuarioSeguidor) throws PersistenceException {
		UsuarioDao dao = DaoFactory.getUsuarioDao();
		dao.dejarDeSeguir(miUsuario, usuarioSeguidor);
	}
	
	//ver pefil ajeno
	public List <Usuario> getSimilares(Integer iduser) throws PersistenceException{
		UsuarioDao dao = DaoFactory.getUsuarioDao();
		List<Usuario> similares = dao.findByUsuarios(iduser);
		return similares;
	}
	
	/*
	public boolean seguirUser(Usuario usuarioSeguidor) { // Ago
		boolean resultado = true;
		for (Usuario user : siguiendo) {
			if (usuarioSeguidor.equals(user)) {
				resultado = false;
				break;
			}
		}
		this.siguiendo.add(usuarioSeguidor);
		return resultado;
	}

	public boolean dejarDeSeguirUser(Usuario idolo) { // Ago
		return this.siguiendo.remove(idolo);
	}
*/
   
}
	
