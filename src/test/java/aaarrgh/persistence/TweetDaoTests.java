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
	TweetDao dao = DaoFactory.getTweetDao();

	Tweet tweetuno;

	@Before
	public void setUp() throws PersistenceException {
		// se borran todas las personas, para iniciar con base vacía
		for (Tweet cadaTweet: dao.findAll()) {
			dao.delete(cadaTweet);
		}

		// se inserta
		tweetuno = buildTweet(1, "Mi primer tweet",1);
		dao.insert(tweetuno);

	
	}

	private Tweet buildTweet(Integer id_user, String tweets, Integer Id_user) {
		Tweet tweet = new Tweet();
		tweet.setId(id_user);
		tweet.setTweet(tweets);
		tweet.setId_user(id_user);
		

		return tweet;
	}

	@After
	public void tearDown() throws PersistenceException {
		// se borran todas las personas

		dao.delete(tweetuno);
	

	}

	@Test
	public void testQueSePuedeBuscarUnTweet() throws PersistenceException {

		Tweet tweetEncontrado = dao.findById(tweetuno.getId());

		assertNotNull("El tweet con id 1 debe existir", tweetEncontrado);
		assertEquals("El tweet 1 es: mi primer tweet", "mi primer tweet", tweetEncontrado.getTweet());
		assertEquals("El tweet 1 pertence al usuario: Sparrows", 1, (int)tweetEncontrado.getId_user());//modificar
	}

	@Test
	public void testQueSePuedeInsertarUnTweet() throws PersistenceException {

		Tweet tweetdos = buildTweet(2, "Mi segundo tweet",1);
		assertEquals("antes de insertar hay 1 tweet", 1, dao.findAll().size());

		dao.insert(tweetdos);
		assertEquals("luego de insertar hay 2 tweet", 2, dao.findAll().size());
		assertNotNull("puede encontrarse al tweet con id 2", dao.findById(tweetdos.getId()));

	}

	@Test
	public void testQueSePuedeBorrarUnTweet() throws PersistenceException {

		Tweet tweetEncontrado = dao.findById(tweetuno.getId());
		dao.delete(tweetEncontrado);

		tweetEncontrado = dao.findById(1);
		assertNull("El tweet con id 1 no debe existir", tweetEncontrado);

	}

	
//Modificar la cantidad de tweets que hay en la base
	@Test
	public void testQueSePuedenBuscarTodosLosTweets() throws PersistenceException {

		List<Tweet> todosLosTweets = dao.findAll();
		assertEquals("se espera que haya 2 tweets en la base", 2, todosLosTweets.size());

	}
	
		@Test
	public void testQueSePuedeBuscarTodosLosTweetsDeCiertoUsuario() throws PersistenceException {

		Integer idUser = 1;
		tweetdos = buildTweet(2, "Mi segundo tweet",1);
		dao.insert(tweetdos);

		List<Tweet> todosLosTweetsdeSparrow = dao.findAllFromUser(idUser);
		
		assertEquals("Los tweets deberian ser dos", 2, (int)todosLosTweetsdeSparrow.size());

		assertEquals("El tweet 2 es: mi segundo tweet", "Mi segundo tweet", todosLosTweetsdeSparrow.select(2).getTweet();
		assertEquals("El tweet 2 pertence al usuario: Sparrows", 1, (int)todosLosTweetsdeSparrow.select(2).getId_user());//modificar
	}

}
