package aaarrgh.services;

import java.util.LinkedList;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.UsuarioDao;

public class LoginService {

	public Boolean authenticate(String username, String password) throws PersistenceException {

		UsuarioDao dao = DaoFactory.getUsuarioDao();
		Boolean result=false;
		LinkedList<Usuario> todosLosUsuarios = new LinkedList<Usuario>();
		todosLosUsuarios = (LinkedList<Usuario>) dao.findAll();
		for (Usuario user : todosLosUsuarios) {
if (username.equals(user.getUser())) {
	if (password.equals(user.getPassword())) {
		result= true;
	}
	
}
		}
		return result;
		// return username.equals(password);
		/*
		 * Aca hay que borrar esto de arriba y consultar al dao para comparar
		 * los parametros recibidos contra los que se encuentran en la bdd
		 */
	}

}
