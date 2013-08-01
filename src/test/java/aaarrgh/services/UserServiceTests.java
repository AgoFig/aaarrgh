package aaarrgh.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import aaarrgh.model.Usuario;
import aaarrgh.persistence.PersistenceException;

public class UserServiceTests {

	UserService service = new UserService();

	@Test
	public void queTraigaUsuarioySusSeguidores() throws PersistenceException {
		Usuario usuario = service.getUsuarioByName("agos");
		List<Usuario> seguidores =	service.getSeguidores(usuario.getId());
		
		Assert.assertEquals("Agostina",usuario.getNombre());
		Assert.assertEquals(2,seguidores.size());
	}
	
}
