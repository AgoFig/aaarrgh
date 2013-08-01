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
		Usuario miUsuario = miUsuarioService.getUsuarioByName("paulitta");
		TweetService miTweet = TweetService.getInstance();		
		
		Assert.assertEquals(16, miTweet.getCantidadImproperios(miUsuario));

	}

}
