package aaarrgh.model;

import java.util.List;

public class Usuario {

	//private Integer iduser;
	private String nombre;
	private String apellido;
	private String mail;
	private String user;
	private String password;

	public Usuario() {
		super();
	}

	public String getFullName() {
		return nombre + " " + apellido;
	}
	
	/*
	public Integer getId() {
		return iduser;
	}
	public void setId(Integer iduser) {
		this.iduser = iduser;
	}
	
	*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public List<Usuario> getSigue() {
		
		return null;
	}

	public List<Usuario> getSeguidores() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean seguirOtros(Usuario usuarioSeguidor) {
		// TODO Auto-generated method stub
		return false;
	}

	public void dejarDeSeguir(Usuario capitanBarbosa) {
		// TODO Auto-generated method stub
		
	}

	
	
}