package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Usuario;

public interface UsuarioDao {

    public void insert(Usuario usuario) throws PersistenceException;
    
    public void delete(Usuario usuario) throws PersistenceException;
    
//    public void update(Usuario usuario) throws PersistenceException;
    
//    public Usuario findById(Integer iduser) throws PersistenceException;
    
    public List<Usuario> findAll() throws PersistenceException;

//	public List<Tweet> traerTweetsDeQuienesSigo(Integer id);

    //me tiene que traer los seguidores de un user:
    public List<Usuario> traerSeguidoresDeUnUsuario(Integer iduser) throws PersistenceException;
    
    //me tiene que traer los usuarios que estoy siguiendo:
    public List <Usuario> traerLosQueEstoySiguiendo(Integer iduser) throws PersistenceException;
    
	public Usuario findByUser(String name) throws PersistenceException;
    
}
