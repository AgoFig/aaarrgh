package aaarrgh.services;

public class LoginService {

	public Boolean authenticate(String username, String password) {
		return username.equals(password);
		/* Aca hay que borrar esto de arriba y consultar al dao para comparar los parametros recibidos contra los que se encuentran en la bdd */
	}
	
}
