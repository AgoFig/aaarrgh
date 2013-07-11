package aaarrgh.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aaarrgh.model.Usuario;

public class UsuarioDaoTests {

	UsuarioDao dao = DaoFactory.getUsuarioDao();

	Usuario jackSparrows;
	Usuario barbaNegra;
	Usuario capitanBarbosa;

	@Before
	public void setUp() throws PersistenceException {
		// se borran todas los usuarios, para iniciar con base vacía
		for (Usuario cadaUsuario : dao.findAll()) {
			dao.delete(cadaUsuario);
		}

		// se inserta
		jackSparrows = buildUsuario(1, "Jack", "Sparrows", "jack@sparrows.com",
				"@jack", "jack");
		dao.insert(jackSparrows);

		capitanBarbosa = buildUsuario(2, "Capitan", "Barbosa",
				"cap@barbosa.com", "@barb", "barba");
		dao.insert(capitanBarbosa);

	}

	private Usuario buildUsuario(Integer iduser, String nombre,
			String apellido, String mail, String user, String password) {
		Usuario usuario = new Usuario();
		usuario.setId(iduser);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setUser(user);
		usuario.setPassword(password);
		usuario.setMail(mail);

		return usuario;
	}

	@After
	public void tearDown() throws PersistenceException {
		// se borra todos los usuarios

		dao.delete(jackSparrows);

		dao.delete(capitanBarbosa);

	}

	@Test
	public void testQueSePuedeBuscarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(jackSparrows.getId());

		assertNotNull("El usuario con id 1 debe existir", usuarioEncontrado);
		assertEquals("El usuario 1 tiene nombre: Jack", "Jack",
				usuarioEncontrado.getNombre());
		assertEquals("El usuario 1 tiene apellido: Sparrows", "Sparrows",
				usuarioEncontrado.getApellido());
		assertEquals("El usuario 1 tiene mail: jack@sparrows.com",
				"jack@sparrows.com", usuarioEncontrado.getMail());

	}

	@Test
	public void testQueSePuedeInsertarUnUsuario() throws PersistenceException {

		barbaNegra = buildUsuario(3, "Barba", "Negra", "barbanegra@gmail.com",
				"@bnegra", "barba");
		assertEquals("antes de insertar hay 2 usuario", 2, dao.findAll().size());

		dao.insert(barbaNegra);
		assertEquals("luego de insertar hay 3 usuarios", 3, dao.findAll()
				.size());
		assertNotNull("puede encontrarse al usuario con id 3",
				dao.findById(barbaNegra.getId()));

	}

	@Test
	public void testQueSePuedeBorrarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(barbaNegra.getId());
		dao.delete(usuarioEncontrado);

		usuarioEncontrado = dao.findById(3);
		assertNull("El usuario con id 3 no debe existir", usuarioEncontrado);

	}

	@Test
	public void testQueSePuedeActualizarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(jackSparrows.getId());
		assertEquals("El usuario con id 1 se llama Jack", "Jack",
				usuarioEncontrado.getNombre());
		usuarioEncontrado.setNombre("Jack");
		dao.update(usuarioEncontrado);
		assertEquals("el usuario con id 1 ahora se llama JackS",
				"JackS", usuarioEncontrado.getNombre());

	}

	@Test
	public void testQueSePuedenBuscarTodosLosUsuarios()
			throws PersistenceException {

		List<Usuario> todosLosUsuarios = dao.findAll();
		assertEquals("se espera que haya 2 usuarios en la base", 2,
				todosLosUsuarios.size());

	}

	@Test
	public void testQueSePuedaSeguirAOtroUsuario() throws PersistenceException {

//Esta insertado mas arriba

    jackSparrow.seguir(capitanBarbosa);

    <List> genteAQuienJackSigue = jackSparrow.getSigue();

    <List> tweetsDeGenteAQuienJackSigue = dao.traerTweetsDeQuienesSigo(jackSparrow.get_Id());

	assertEquals("Jack debe estar siguiendo a un usuario llamado Barbosa", 1, genteAQuienJackSigue.size());

	}

	@Test
	public void testQueUnUserPuedaSerSeguido() throws PersistenceException {

   jackSparrow.seguir(capitanBarbosa);

   <List> seguidores = capitanBarbosa.getSeguidores();

	assertEquals("Barbosa debe estar siendo seguido por un usuario que es Jack", 1, seguidores.size());

	}

	@Test
	public void testQueSePuedaDejarDeSeguirAUnUser() throws PersistenceException {

	jackSparrow.seguir(capitanBarbosa);
	jackSparrow.dejarDeSeguir(capitanBarbosa);

    <List> seguidores = capitanBarbosa.getSeguidores();

	assertEquals("Barbosa no deberia estar siguiendo a nadie.", 0, seguidores.size());

	}
}
