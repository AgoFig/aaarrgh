package aaarrgh.model;

public class Usuario {

	private Integer id_user;
	private String nombre;
	private String apellido;
	private String mail;
	private String user;
	private Integer password;

	public Usuario() {
		super();
	}

	public String getFullName() {
		return nombre + " " + apellido;
	}
	
	public Integer getId() {
		return id_user;
	}
	public void setId(Integer id_user) {
		this.id_user = id_user;
	}
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
	public Integer getPassword() {
		return password;
	}
	public void setPassword(Integer password) {
		this.password = password;
	}
	
}
