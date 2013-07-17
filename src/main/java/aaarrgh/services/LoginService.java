package aaarrgh.services;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.UsuarioDao;

public class LoginService {

	public Boolean authenticate(String username, String password)
			throws PersistenceException {
/*
		UsuarioDao dao = DaoFactory.getUsuarioDao();		
		Boolean result = false;
				
		Usuario correcto = new Usuario();
		correcto = dao.findByUser(username); // Esto da error hay que ver el dao...
		
		if(correcto.getPassword()== password) { 
		
					Session sesion;
			
			 session.setAttribute("name", correcto.getId());
			 Object value = session.getAttribute("name");
		result = true;
		 }
		

		*/
		
		return username.equals(password);
		/*
		 * Aca hay que borrar esto de arriba y consultar al dao para comparar
		 * los parametros recibidos contra los que se encuentran en la bdd
		 */
	}

}
