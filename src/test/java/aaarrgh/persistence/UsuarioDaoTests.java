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

	@Before
	public void setUp() throws PersistenceException {
		// se borran todas las personas, para iniciar con base vacía
		for (Usuario cadaUsuario: dao.findAll()) {
			dao.delete(cadaUsuario);
		}

		// se inserta
		jackSparrows = buildUsuario(1, "Jack", "Sparrows", "jack@sparrows.com","@jack",1234);
		dao.insert(jackSparrows);

	
	}

	private Usuario buildUsuario(Integer id_user, String nombre, String apellido, String mail,String user, Integer password) {
		Usuario usuario = new Usuario();
		usuario.setId(id_user);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setUser(user);
		usuario.setPassword(password);
		usuario.setMail(mail);

		return usuario;
	}

	@After
	public void tearDown() throws PersistenceException {
		// se borran todas las personas

		dao.delete(jackSparrows);
	

	}

	@Test
	public void testQueSePuedeBuscarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(jackSparrows.getId());

		assertNotNull("El usuario con id 1 debe existir", usuarioEncontrado);
		assertEquals("El usuario 1 tiene nombre: Jack", "Jack", usuarioEncontrado.getNombre());
		assertEquals("El usuario 1 tiene apellido: Sparrows", "Sparrows", usuarioEncontrado.getApellido());
		assertEquals("El usuario 1 tiene mail: jack@sparrows.com", "jack@sparrows.com",usuarioEncontrado.getMail());

	}

	@Test
	public void testQueSePuedeInsertarUnUsuario() throws PersistenceException {

		Usuario barbaNegra = buildUsuario(2, "Barba", "Negra", "barbanegra@gmail.com","Barba_Negra",456);
		assertEquals("antes de insertar hay 1 usuario", 1, dao.findAll().size());

		dao.insert(barbaNegra);
		assertEquals("luego de insertar hay 2 usuarios", 2, dao.findAll().size());
		assertNotNull("puede encontrarse al usuario con id 2", dao.findById(barbaNegra.getId()));

	}

	@Test
	public void testQueSePuedeBorrarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(jackSparrows.getId());
		dao.delete(usuarioEncontrado);

		usuarioEncontrado = dao.findById(1);
		assertNull("El usuario con id 1 no debe existir", usuarioEncontrado);

	}

	@Test
	public void testQueSePuedeActualizarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(jackSparrows.getId());
		assertEquals("El usuario con id 1 se llama Jack", "Jack", usuarioEncontrado.getNombre());
		usuarioEncontrado.setNombre("Gertrudis");
		dao.update(usuarioEncontrado);
		assertEquals("el usuario con id 1 ahora se llama Capitan Jack", "Capitan_Jack", usuarioEncontrado.getNombre());

	}
//Modificar la cantidad de usuario que hay en la base
	@Test
	public void testQueSePuedenBuscarTodosLosUsuarios() throws PersistenceException {

		List<Usuario> todosLosUsuarios = dao.findAll();
		assertEquals("se espera que haya 2 usuarios en la base", 2, todosLosUsuarios.size());

	}

@Test
	public void testQueSePuedaSeguirAOtroUsuario() throws PersistenceException {

		Usuario capitanBarbosa;
capitanBarbosa= buildUsuario(2, "Capitan", "Barbosa", "cap@barbosa.com","@barb",1234);
		dao.insert(capitanBarbosa);

jackSparrow.seguir(capitanBarbosa);

<List> genteAQuienJackSigue = jackSparrow.getSigue();

<List> tweetsDeGenteAQuienJackSigue = dao.traerTweetsDeQuienesSigo(jackSparrow.get_Id());

		assertEquals("Jack debe estar siguiendo a un usuario llamado Barbosa", 1, genteAQuienJackSigue.size());

	}

@Test
	public void testQueUnUserPuedaSerSeguido() throws PersistenceException {

		Usuario capitanBarbosa;
capitanBarbosa= buildUsuario(2, "Capitan", "Barbosa", "cap@barbosa.com","@barb",1234);
		dao.insert(capitanBarbosa);

jackSparrow.seguir(capitanBarbosa);

<List> seguidores = capitanBarbosa.getSeguidores();

		assertEquals("Barbosa debe estar siendo seguido por un usuario que es Jack", 1, seguidores.size());

	}
	
	@Test
	public void testQueSePuedaDejarDeSeguirAUnUser() throws PersistenceException {

		Usuario capitanBarbosa;
		capitanBarbosa= buildUsuario(2, "Capitan", "Barbosa", "cap@barbosa.com","@barb",1234);
		dao.insert(capitanBarbosa);

		jackSparrow.seguir(capitanBarbosa);
		jackSparrow.dejarDeSeguir(capitanBarbosa);

<List> seguidores = capitanBarbosa.getSeguidores();

		assertEquals("Barbosa no deberia estar siguiendo a nadie.", 0, seguidores.size());

	}


}

