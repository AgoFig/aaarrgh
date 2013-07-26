package aaarrgh.services;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.UsuarioDao;

//import javax.servlet.http.HttpSession;


public class LoginService {

	public Usuario authenticate(String username, String password)
			throws PersistenceException {

		UsuarioDao dao = DaoFactory.getUsuarioDao();		
				
		Usuario correcto = new Usuario();
		correcto = dao.findByUser(username);
		
		if(password.equals(correcto.getPassword())){ // Ac� no se deber�a corroborar si existe el usuario o lo hace desde el UsuarioDao?
			correcto.setValido(true); 
		}else{
			correcto.setValido(false);
		}
		
		return correcto;
		/*
		 * Aca hay que borrar esto de arriba y consultar al dao para comparar
		 * los parametros recibidos contra los que se encuentran en la bdd
		 */
	}

}
