package aaarrgh.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import aaarrgh.model.Usuario;

public class UsuarioDaoTests {

	UsuarioDao dao = DaoFactory.getUsuarioDao();
	Usuario jackSparrow;

	@Before
	public void setUp() throws PersistenceException {
		jackSparrow = buildUsuario("jacksparrow", "jack", "Jack", "Sparrow",
				"jack@sparrows.com");
		dao.insert(jackSparrow);

	}

	private Usuario buildUsuario(String user, String password, String nombre,
			String apellido, String mail) {
		Usuario usuario = new Usuario();
		usuario.setUser(user);
		usuario.setPassword(password);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setMail(mail);

		return usuario;
	}

	@Test
	public void testQueSePuedeBuscarUnUsuarioPorUsuario()
			throws PersistenceException {

		Usuario usuarioEncontrado = dao.findByUser(jackSparrow.getUser());

		assertNotNull("El usuario  debe existir", usuarioEncontrado);
		assertEquals("El usuario es: jacksparrow", "jacksparrow",
				usuarioEncontrado.getUser());

	}

	@Test
	public void testQueSePuedeBorrarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findByUser(jackSparrow.getUser());
		dao.delete(usuarioEncontrado);

		usuarioEncontrado = dao.findByUser("jacksparrow");
		assertNull("El usuario Jack no debe existir", usuarioEncontrado);

	}

	@Test
	public void testQueSePuedenBuscarTodosLosUsuarios()
			throws PersistenceException {

		List<Usuario> todosLosUsuarios = dao.findAll();
		assertEquals("se espera que haya 4 usuarios en la base", 4,
				todosLosUsuarios.size());
	}

}