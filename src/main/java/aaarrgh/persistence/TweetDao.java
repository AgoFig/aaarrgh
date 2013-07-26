package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Tweet;


public interface TweetDao {

    public void insert(Tweet tweet) throws PersistenceException;
    
    public void delete(Tweet tweet) throws PersistenceException;
    
    public void update(Tweet tweet) throws PersistenceException;
    
    public Tweet findById(Integer idtweet) throws PersistenceException;
    
    public List<Tweet> findAll() throws PersistenceException;

	public List<Tweet> findAllFromUser(Integer idUser) throws PersistenceException; //public List<Tweet> findAllFromUser(String string) throws PersistenceException; esta mal 
																					// en la tabla mensaje no tiene el monbre del usuario sino el id, ya puse Integer

    
}
