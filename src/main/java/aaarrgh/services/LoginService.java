package aaarrgh.services;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.UsuarioDao;

public class LoginService {

	public Usuario authenticate(String username, String password)
			throws PersistenceException {

		UsuarioDao dao = DaoFactory.getUsuarioDao();

		Usuario correcto = new Usuario();
		correcto = dao.findByUser(username);

		if (password.equals(correcto.getPassword())) {
			correcto.setValido(true);
		} else {
			correcto.setValido(false);
		}

		return correcto;

	}
}
