package aaarrgh.services;

import aaarrgh.model.Usuario;

public class UserService {
	private static UserService instance = new UserService();

	private UserService() {

	}

	public static UserService getInstance() {
		return instance;

	}

	public Usuario getUsuarioByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
	
