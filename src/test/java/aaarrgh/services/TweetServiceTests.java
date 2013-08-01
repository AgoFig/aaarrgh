package aaarrgh.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import aaarrgh.model.Tweet;
import aaarrgh.persistence.PersistenceException;

public class TweetServiceTests {

	TweetService service = new TweetService();

	@Test
	public void queTraigaLosImproperios() throws PersistenceException {
		List<Tweet> improperios = service.getImproperios(11);
		
		Assert.assertEquals(14,improperios.size());
	}
	
}
