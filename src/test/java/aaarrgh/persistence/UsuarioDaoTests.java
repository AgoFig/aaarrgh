package aaarrgh.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;

public class UsuarioDaoTests {

	UsuarioDao dao = DaoFactory.getUsuarioDao();

	Usuario jackSparrow;
	Usuario capitanBarbosa;
	
	@Before
	public void setUp() throws PersistenceException {
		// se borran todas los usuarios, para iniciar con base vacía
		for (Usuario cadaUsuario : dao.findAll()) {
			dao.delete(cadaUsuario);
		}

		// se insertan:
		jackSparrow=buildUsuario(1,"jacksparrows","jack","Jack","Sparrows","jack@sparrows.com");
		dao.insert(jackSparrow);

		capitanBarbosa = buildUsuario(2, "capitanbarbosa", "barbosa","Capitan","Barbosa",
				"cap@barbosa.com");
		dao.insert(capitanBarbosa);

		

	}

	private Usuario buildUsuario(Integer iduser, String user,
			String password, String nombre, String apellido, String mail) {
		Usuario usuario = new Usuario();
		usuario.setId(iduser);
		usuario.setUser(user);
		usuario.setPassword(password);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setMail(mail);

		return usuario;
	}

	@After
	public void tearDown() throws PersistenceException {
		// se borra todos los usuarios

		dao.delete(jackSparrow);
		dao.delete(capitanBarbosa);

	}

	@Test
	public void testQueSePuedeBuscarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(jackSparrow.getId());

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

		Usuario barbaNegra = buildUsuario(3, "barbanegra", "negra","Barba","Negra", "barbanegra@gmail.com");
		assertEquals("antes de insertar hay 2 usuario", 2, dao.findAll().size());

		dao.insert(barbaNegra);
		assertEquals("luego de insertar hay 3 usuarios", 3, dao.findAll()
				.size());
		assertNotNull("puede encontrarse al usuario con id 3",
				dao.findById(barbaNegra.getId()));

	}

	@Test
	public void testQueSePuedeBorrarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(capitanBarbosa.getId());
		dao.delete(usuarioEncontrado);

		usuarioEncontrado = dao.findById(2);
		assertNull("El usuario con id 2 no debe existir", usuarioEncontrado);

	}

	@Test
	public void testQueSePuedeActualizarUnUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findById(jackSparrow.getId());
		assertEquals("El nombre del usuario con id 1 es Jack", "Jack",
				usuarioEncontrado.getNombre());
		usuarioEncontrado.setNombre("Jack");
		dao.update(usuarioEncontrado);
		assertEquals("el usuario con id 1 ahora tiene como nombre: Jacksp",
				"Jacksp", usuarioEncontrado.getNombre());

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


    jackSparrow.seguir(capitanBarbosa);
    
    List <Usuario> genteAQuienJackSigue = jackSparrow.getSigue();

    List <Tweet> tweetsDeGenteAQuienJackSigue = dao.traerTweetsDeQuienesSigo(jackSparrow.getId());

	assertEquals("Jack debe estar siguiendo a un usuario llamado Barbosa", 1, genteAQuienJackSigue.size());

	}

	@Test
	public void testQueUnUserPuedaSerSeguido() throws PersistenceException {

   jackSparrow.seguir(capitanBarbosa);

   List <Usuario> seguidores = capitanBarbosa.getSeguidores();

	assertEquals("Barbosa debe estar siendo seguido por un usuario que es Jack", 1, seguidores.size());

	}

	@Test
	public void testQueSePuedaDejarDeSeguirAUnUser() throws PersistenceException {

	jackSparrow.seguir(capitanBarbosa);
	jackSparrow.dejarDeSeguir(capitanBarbosa);

	List <Usuario> seguidores = capitanBarbosa.getSeguidores();

	assertEquals("Barbosa no deberia estar siguiendo a nadie.", 0, seguidores.size());

	}
}
