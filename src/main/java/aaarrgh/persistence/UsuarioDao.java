package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Usuario;

public interface UsuarioDao {

	public void insert(Usuario usuario) throws PersistenceException;

	public List<Usuario> traerSeguidoresDeUnUsuario(Integer iduser)
			throws PersistenceException;

	public List<Usuario> traerLosQueEstoySiguiendo(Integer iduser)
			throws PersistenceException;

	public Usuario findByUser(String name) throws PersistenceException;

	void seguir(Usuario fan, Usuario idolo) throws PersistenceException;

	void dejarDeSeguir(Usuario fan, Usuario idolo) throws PersistenceException;

	public List<Usuario> findByUsuarios(Integer iduser)
			throws PersistenceException;

}
