package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Tweet;

public interface TweetDao {

	public void insert(Tweet tweet) throws PersistenceException;

	public List<Tweet> findAll() throws PersistenceException;

	public List<Tweet> findAllFromUser(Integer idUser)
			throws PersistenceException;

}
