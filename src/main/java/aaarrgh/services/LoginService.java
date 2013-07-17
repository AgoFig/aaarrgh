package aaarrgh.services;

import java.awt.List;
import java.util.LinkedList;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.UsuarioDao;

public class LoginService {

	public Boolean authenticate(String username, String password) {

		UsuarioDao dao = DaoFactory.getUsuarioDao();

		List<Usuario> todosLosUsuarios = new LinkedList<Usuario>();
		todosLosUsuarios = dao.findAll();
		for (Usuario user : todosLosUsuarios) {
if (username.equals(user.getNombre()) && password.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
		}
		
		// return username.equals(password);
		/*
		 * Aca hay que borrar esto de arriba y consultar al dao para comparar
		 * los parametros recibidos contra los que se encuentran en la bdd
		 */
	}

}
