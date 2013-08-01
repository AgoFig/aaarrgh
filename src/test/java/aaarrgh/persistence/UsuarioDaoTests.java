package aaarrgh.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import aaarrgh.model.Usuario;
import aaarrgh.services.UserService;

public class UsuarioDaoTests {

	UsuarioDao dao = DaoFactory.getUsuarioDao();
	Usuario jackSparrow;

	Usuario capitanBarbosa;

	/*
	 * @Before
	 * 
	 * 
	 * @Before
	 * 
	 * public void setUp() throws PersistenceException { jackSparrow =
	 * buildUsuario("jacksparrow", "jack", "Jack", "Sparrow",
	 * "jack@sparrows.com"); dao.insert(jackSparrow);
	 * 
	 * }
	 * 
	 * private Usuario buildUsuario(String user, String password, String nombre,
	 * String apellido, String mail) { Usuario usuario = new Usuario();
	 * usuario.setUser(user); usuario.setPassword(password);
	 * usuario.setNombre(nombre); usuario.setApellido(apellido);
	 * usuario.setMail(mail);
	 * 
	 * return usuario; }
	 * 
	 * @After public void tearDown() throws PersistenceException { // se borra
	 * todos los usuarios
	 * 
	 * dao.delete(jackSparrow); dao.delete(capitanBarbosa);
	 * 
	 * }
	 */
	/*
	 * @Test public void testQueSePuedeBuscarUnUsuario() throws
	 * PersistenceException {
	 * 
	 * Usuario usuarioEncontrado = dao.findById(jackSparrow.getId());
	 * 
	 * assertNotNull("El usuario con id 1 debe existir", usuarioEncontrado);
	 * assertEquals("El usuario 1 tiene nombre: Jack", "Jack",
	 * usuarioEncontrado.getNombre());
	 * assertEquals("El usuario 1 tiene apellido: Sparrows", "Sparrows",
	 * usuarioEncontrado.getApellido());
	 * assertEquals("El usuario 1 tiene mail: jack@sparrows.com",
	 * "jack@sparrows.com", usuarioEncontrado.getMail());
	 * 
	 * }
	 */

	@Test
	public void testQueSePuedeBuscarUnUsuarioPorUsuario()
			throws PersistenceException {

		Usuario usuarioEncontrado = dao.findByUser(jackSparrow.getUser());

		assertNotNull("El usuario  debe existir", usuarioEncontrado);
		assertEquals("El usuario es: jacksparrow", "jacksparrow",
				usuarioEncontrado.getUser());

	}

	/*
	 * @Test public void testQueSePuedeInsertarUnUsuario() throws
	 * PersistenceException {
	 * 
	 * @Test public void testQueSePuedeBorrarUnUsuario() throws
	 * PersistenceException {
	 * 
	 * 
	 * Usuario usuarioEncontrado = dao.findByUser(jackSparrow.getUser());
	 * dao.delete(usuarioEncontrado);
	 * 
	 * usuarioEncontrado = dao.findByUser("jacksparrow");
	 * assertNull("El usuario Jack no debe existir", usuarioEncontrado);
	 * 
	 * 
	 * dao.insert(barbaNegra); assertEquals("luego de insertar hay 3 usuarios",
	 * 3, dao.findAll() .size());
	 * 
	 * }
	 */

	/*
	 * @Test public void testQueSePuedenBuscarTodosLosUsuarios() throws
	 * PersistenceException {
	 * 
	 * List<Usuario> todosLosUsuarios = dao.findAll();
	 * assertEquals("se espera que haya 4 usuarios en la base", 4,
	 * todosLosUsuarios.size());
	 * 
	 * 
	 * }
	 */

	// Punto 5: visualizar el perfil de otros para observar detalles de otros
	// usuarios-Incompleto

	@Test
	public void testQueSePuedaVerPerfilDeotrosUsuarios()
			throws PersistenceException {
		UserService usuario = UserService.getInstance();
		usuario.getUsuarioByName("barbanegra");// quiero visualizar el perfil de
												// barbanegra

	}
	// Punto 6 - No anda
	/*
	 * @Test public void testQuePuedaSeguirAOtros() throws PersistenceException
	 * {
	 * 
	 * UserService usuario = UserService.getInstance(); Usuario miUsuario =
	 * usuario.getUsuarioByName("barbanegra"); Usuario miUsuarioAdd =
	 * usuario.getUsuarioByName("jacksparrow");
	 * 
	 * System.out.println("Cantidad:" + miUsuario.getSigue().size());
	 * 
	 * miUsuario.seguirUser(miUsuarioAdd);
	 * 
	 * System.out.println("Cantidad:" + miUsuarioAdd.getSigue().size());
	 * 
	 * 
	 * }
	 */

	// Punto 7 - Modificado
	/*
	 * @Test public void testQueSePuedaDejarDeSeguirAUnUser() throws
	 * PersistenceException {
	 * 
	 * jackSparrow.seguirUser(capitanBarbosa);
	 * jackSparrow.dejarDeSeguir(capitanBarbosa);
	 * 
	 * //List <Usuario> seguidores = jackSparrow.getSeguidores();
	 * 
	 * //No deberia estar siguiendo a Barbosa pero si puede estar siguiendo a
	 * otros usuarios, entonces?
	 * //assertEquals("Sparrow no deberia estar siguiendo a Barbosa.", 0,
	 * seguidores.size());
	 * 
	 * }
	 */

}
