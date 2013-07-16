package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;

public interface UsuarioDao {

    public void insert(Usuario usuario) throws PersistenceException;
    
    public void delete(Usuario usuario) throws PersistenceException;
    
    public void update(Usuario usuario) throws PersistenceException;
    
    public Usuario findById(Integer iduser) throws PersistenceException;
    
    public List<Usuario> findAll() throws PersistenceException;

	public List<Tweet> traerTweetsDeQuienesSigo(Integer id);
    
}
