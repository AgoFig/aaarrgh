package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
			String query = "insert into usuario (id_user, nombre, apellido, mail,user,password) values (?, ?, ?, ?,?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, usuario.getId());
			statement.setString(2, usuario.getNombre());
			statement.setString(3, usuario.getApellido());
			statement.setString(4, usuario.getMail());
			statement.setString(5,usuario.getUser());
			statement.setInt(6,usuario.getPassword());	
			
			

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
			String query = "update usuario set nombre = ?, apellido = ?, mail = ?, user=?,password=?, where id_user = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getApellido());
			statement.setString(3, usuario.getMail());
			statement.setString(4,usuario.getUser());
			statement.setInt(5,usuario.getPassword());
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
	public Usuario findById(Integer id_user) throws PersistenceException {
		if (id_user == null) {
			throw new IllegalArgumentException(
					"El id de persona no debe ser nulo");
		}
		Usuario usuario = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from usuario where id_user = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, id_user);
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

}
