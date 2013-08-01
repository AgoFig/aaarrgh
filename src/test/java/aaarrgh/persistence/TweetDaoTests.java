package aaarrgh.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import aaarrgh.model.Tweet;

public class TweetDaoTests {
	TweetDao dao = DaoFactory.getTweetDao();

	@Test
	public void testQueSePuedenBuscarTodosLosTweets()
			throws PersistenceException {

		List<Tweet> todosLosTweets = dao.findAll();
		assertEquals("se espera que haya ... tweets en la base", 2,
				todosLosTweets.size()); // no se puede calcular la cantidad de
										// tweet en la base, siempre hay uno
										// nuevo

	}

}
