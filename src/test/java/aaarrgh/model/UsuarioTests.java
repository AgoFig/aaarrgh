package aaarrgh.model;

import org.junit.Assert;
import org.junit.Test;

public class UsuarioTests {

	@Test
	public void testThatGivesFullName() {
		Usuario person = new Usuario();
		person.setApellido("sparrow");
		person.setNombre("jack");
		
		Assert.assertEquals("a person named 'jack' and with surname 'sparrow' has 'jack sparrow' as full name",
				"jack sparrow", person.getFullName());
		
	}
	
}
