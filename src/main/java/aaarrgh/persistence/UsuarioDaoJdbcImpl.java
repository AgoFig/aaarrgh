package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Tweet;
import aaarrgh.model.Usuario;
public class UsuarioDaoJdbcImpl implements UsuarioDao {

	private static UsuarioDao instance = new UsuarioDaoJdbcImpl();

	public static UsuarioDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Usuario usuario) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into usuario (id_user, user, password, nombre,apellido,mail) values (?, ?, ?, ?,?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, usuario.getId());
			statement.setString(2, usuario.getUser());
			statement.setString(3, usuario.getPassword());
			statement.setString(4, usuario.getNombre());
			statement.setString(5,usuario.getApellido());
			statement.setString(6,usuario.getMail());	
			
			

			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void delete(Usuario usuario) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from usuario where id_user = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, usuario.getId());
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void update(Usuario usuario) throws PersistenceException {
		try {
			String query = "update usuario set user = ?, password = ?,  nombre= ?, apellido=?,mail=? where id_user = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, usuario.getUser());
			statement.setString(2, usuario.getPassword());
			statement.setString(3, usuario.getNombre());
			statement.setString(4,usuario.getApellido());
			statement.setString(5,usuario.getMail());
			statement.setInt(6, usuario.getId());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}

	public List<Usuario> findAll() throws PersistenceException {
		List<Usuario> lista = new LinkedList<Usuario>();
		try {
			String query = "select * from usuario";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

	@Override
	public Usuario findById(Integer iduser) throws PersistenceException {
		if (iduser == null) {
			throw new IllegalArgumentException(
					"El id de persona no debe ser nulo");
		}
		Usuario usuario = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from usuario where id_user = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, iduser);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usuario = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return usuario;
	}

	@Override
	public Usuario findByUser(String name) throws PersistenceException {
		if (name == null) {
			throw new IllegalArgumentException(
					"El id de persona no debe ser nulo");
		}
		Usuario usuario = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from usuario where user = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usuario = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return usuario;
	}

	
	private Usuario convertOne(ResultSet resultSet) throws SQLException {
		Usuario retorno = new Usuario();

		retorno.setId(resultSet.getInt("id_user"));
		retorno.setNombre(resultSet.getString("nombre"));
		retorno.setApellido(resultSet.getString("apellido"));
		retorno.setMail(resultSet.getString("mail"));

		return retorno;
	}

	@Override
	public List<Tweet> traerTweetsDeQuienesSigo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
