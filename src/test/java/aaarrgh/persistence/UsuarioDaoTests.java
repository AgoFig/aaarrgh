package aaarrgh.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aaarrgh.model.Usuario;
import aaarrgh.services.UserService;

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
		jackSparrow=buildUsuario("jacksparrows","jack","Jack","Sparrows","jack@sparrows.com");
		dao.insert(jackSparrow);

		capitanBarbosa = buildUsuario("capitanbarbosa", "barbosa","Capitan","Barbosa",
				"cap@barbosa.com");
		dao.insert(capitanBarbosa);

		

	}

	private Usuario buildUsuario(String user,
			String password, String nombre, String apellido, String mail) {
		Usuario usuario = new Usuario();
		//usuario.setId(iduser);
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
/*
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
*/
	@Test
	public void testQueSePuedeBuscarUnUsuarioPorUsuario() throws PersistenceException {

		Usuario usuarioEncontrado = dao.findByUser(jackSparrow.getUser());

		assertNotNull("El usuario  debe existir", usuarioEncontrado);
		assertEquals("El usuario es: jacksparrows", "jacksparrows",
				usuarioEncontrado.getUser());

	}
	

	@Test
	public void testQueSePuedeInsertarUnUsuario() throws PersistenceException {

		Usuario barbaNegra = buildUsuario( "barbanegra", "negra","Barba","Negra", "barbanegra@gmail.com");
		assertEquals("antes de insertar hay 2 usuario", 2, dao.findAll().size());

		dao.insert(barbaNegra);
		assertEquals("luego de insertar hay 3 usuarios", 3, dao.findAll()
				.size());
		
	}

	@Test
	public void testQueSePuedenBuscarTodosLosUsuarios()
			throws PersistenceException {

		List<Usuario> todosLosUsuarios = dao.findAll();
		assertEquals("se espera que haya 2 usuarios en la base", 2,
				todosLosUsuarios.size());

	}
	
	
//Punto 5: visualizar el perfil de otros para observar detalles de otros usuarios-Incompleto
	
	@Test
	public void testQueSePuedaVerPerfilDeotrosUsuarios() throws PersistenceException {
		UserService usuario  =  UserService.getInstance();
		usuario.getUsuarioByName("barbanegra");//quiero visualizar el perfil de barbanegra
	

	}
//Punto 6 - No anda
	@Test
	public void testQuePuedaSeguirAOtros() throws PersistenceException {

		UserService usuario  =  UserService.getInstance();
		Usuario miUsuario 	 	 = usuario.getUsuarioByName("barbanegra");
		Usuario miUsuarioAdd	 = usuario.getUsuarioByName("jacksparrow");
		
		System.out.println("Cantidad:" + miUsuario.getSigue().size());
		
		miUsuario.seguirUser(miUsuarioAdd);
		
		System.out.println("Cantidad:" + miUsuarioAdd.getSigue().size());


	}

//Punto 7 - Modificado
	@Test
	public void testQueSePuedaDejarDeSeguirAUnUser() throws PersistenceException {

	jackSparrow.seguirUser(capitanBarbosa);
	jackSparrow.dejarDeSeguir(capitanBarbosa);

	//List <Usuario> seguidores = jackSparrow.getSeguidores();
	
//No deberia estar siguiendo a Barbosa pero si puede estar siguiendo a otros usuarios, entonces?
	//assertEquals("Sparrow no deberia estar siguiendo a Barbosa.", 0, seguidores.size());

	}
}
