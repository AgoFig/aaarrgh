package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Usuario;

public interface UsuarioDao {

    public void insert(Usuario usuario) throws PersistenceException;
    
    public void delete(Usuario usuario) throws PersistenceException;
    
    public void update(Usuario usuario) throws PersistenceException;
    
    public Usuario findById(Integer idPersona) throws PersistenceException;
    
    public List<Usuario> findAll() throws PersistenceException;
    
}
