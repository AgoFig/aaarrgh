package aaarrgh.model;

import org.junit.Assert;

import org.junit.Test;

import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.TweetService;
import aaarrgh.services.UserService;

public class TweetTests {

	@Test
	public void getCantidadTweetsForUser() throws PersistenceException {

		UserService miUsuarioService = UserService.getInstance();
		Usuario miUsuario = miUsuarioService.getUsuarioByName("jacksparrow");
		TweetService miTweet = TweetService.getInstance();

		Assert.assertEquals(0, miTweet.getCantidadImproperios(miUsuario));// mientras
																			// jack
																			// no
																			// escriba
																			// nada

	}

}
