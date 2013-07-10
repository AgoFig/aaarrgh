package aaarrgh.model;

import org.junit.Test;

import aaarrgh.services.TweetService;
import aaarrgh.services.UserService;

public class TweetTests {

	@Test
	public void getCantidadTweetsForUser() {

		UserService miUsuarioService = UserService.getInstance();
		Usuario miUsuario = miUsuarioService.getUsuarioByName("JackSparrows");
		TweetService miTweet = TweetService.getInstance();

		System.out.println(miTweet.getCantidadImproperios(miUsuario));

	}

}
