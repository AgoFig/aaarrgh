package aaarrgh.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aaarrgh.model.Tweet;

public class TweetDaoTests {
//	TweetDao dao = DaoFactory.getTweetDao();
//
//	Tweet tweetuno;
//	Tweet tweetdos;
//
//	@Before
//	public void setUp() throws PersistenceException {
//		// se borran todos los tweets, para iniciar con base vacía
//		for (Tweet cadaTweet : dao.findAll()) {
//			dao.delete(cadaTweet);
//		}
//
//		// se insertan 3 tweets:
//		tweetuno = buildTweet(1, "Mi primer tweet", 1);
//		dao.insert(tweetuno);
//		tweetdos = buildTweet(2, "Mi segundo tweet", 1);
//		dao.insert(tweetdos);
//
//	}
//
//	private Tweet buildTweet(Integer iduser, String tweets, Integer Iduser) {
//		Tweet tweet = new Tweet();
//		tweet.setId(iduser);
//		tweet.setTweet(tweets);
//		tweet.setIduser(iduser);
//
//		return tweet;
//	}
//
//	// Borro los tweets
//	@After
//	public void tearDown() throws PersistenceException {
//		dao.delete(tweetuno);
//		dao.delete(tweetdos);
//
//	}
//
//	@Test
//	public void testQueSePuedeBuscarUnTweet() throws PersistenceException {
//
//		Tweet tweetEncontrado = dao.findById(tweetuno.getId());
//
//		assertNotNull("El tweet con id 1 debe existir", tweetEncontrado);
//		assertEquals("El tweet 1 es: mi primer tweet", "Mi primer tweet",
//				tweetEncontrado.getTweet());
//		assertEquals("El tweet 1 pertence al usuario: Sparrows", 1,
//				(int) tweetEncontrado.getIduser());// modificar
//	}
//
//	@Test
//	public void testQueSePuedeInsertarUnTweet() throws PersistenceException {
//
//		Tweet tweettres = buildTweet(3, "Mi tercer tweet", 1);
//		assertEquals("antes de insertar hay 2 tweet", 2, dao.findAll().size());
//
//		dao.insert(tweettres);
//		assertEquals("luego de insertar hay 3 tweet", 3, dao.findAll().size());
//		assertNotNull("puede encontrarse al tweet con id 3",
//				dao.findById(tweettres.getId()));
//
//	}
//
//	@Test
//	public void testQueSePuedeBorrarUnTweet() throws PersistenceException {
//
//		Tweet tweetEncontrado = dao.findById(tweetdos.getId());
//		dao.delete(tweetEncontrado);
//
//		tweetEncontrado = dao.findById(2);
//		assertNull("El tweet con id 2 no debe existir", tweetEncontrado);
//
//	}
//
//	@Test
//	public void testQueSePuedenBuscarTodosLosTweets()
//			throws PersistenceException {
//
//		List<Tweet> todosLosTweets = dao.findAll();
//		assertEquals("se espera que haya 2 tweets en la base", 2,
//				todosLosTweets.size());
//
//	}
//
//	@Test
//	public void testQueSePuedeBuscarTodosLosTweetsDeCiertoUsuario()
//			throws PersistenceException {
//
//		Integer iduser = 1;
//		Tweet tweetcuatro = buildTweet(4, "Mi cuarto tweet", 1);
//		dao.insert(tweetcuatro);
//
//		List<Tweet> todosLosTweetsdeSparrow = dao.findAllFromUser(iduser);
//
//		Tweet tercerTweet = todosLosTweetsdeSparrow.get(3);
//
//		assertEquals("Los tweets deberian ser tres", 3,
//				(int) todosLosTweetsdeSparrow.size());
//
//		assertEquals(
//				"El tweet 4 es: mi tercer tweet - Anteriormente elimine uno",
//				"Mi cuarto tweet", tercerTweet.getTweet());
//		assertEquals("El tweet 4 pertence al usuario: Sparrows", 1,
//				(int) tercerTweet.getIduser());
//	}

}
