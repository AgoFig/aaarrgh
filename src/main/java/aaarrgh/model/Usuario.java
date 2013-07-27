package aaarrgh.model;

public class Usuario {

	private Integer iduser;
	private String nombre;
	private String apellido;
	private String mail;
	private String user;
	private String password;
	private Boolean valido;

	public Usuario() {
		super();
	}

	public String getFullName() {
		return nombre + " " + apellido;
	}
	
	
	public Integer getId() {
		return iduser;
	}
	public void setId(Integer iduser) {
		this.iduser = iduser;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}


	
	public void dejarDeSeguir(Usuario usuarioSeguidor) {
		// TODO Auto-generated method stub
		
	}

	public boolean seguirUsuario(Usuario miUsuario, Usuario usuarioSeguidor) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean seguirUser(Usuario usuarioSeguidor) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}