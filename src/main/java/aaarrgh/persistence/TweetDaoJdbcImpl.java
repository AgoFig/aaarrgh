package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Tweet;

public class TweetDaoJdbcImpl implements TweetDao {
	private static TweetDao instance = new TweetDaoJdbcImpl();

	public static TweetDao getInstance() {
		return instance;
	}

	// Inserta los tweets
	@Override
	public void insert(Tweet tweet) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into mensaje (tweet, id_user) values (?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, tweet.getTweet());
			statement.setInt(2, tweet.getIduser());

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

	// Trae todos los tweets
	public List<Tweet> findAll() throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			String query = "select * from mensaje";
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

	
	// Convierte los resultados y los setea
	private Tweet convertOne(ResultSet resultSet) throws SQLException {
		Tweet retorno = new Tweet();

		retorno.setUserName(resultSet.getString("user"));
		retorno.setTweet(resultSet.getString("tweet"));

		return retorno;
	}

	// Encuentra todos los tweets del usuario en sesion y de los usuarios que sigue
	@Override
	public List<Tweet> findAllFromUser(Integer idUser)
			throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select u.user, m.tweet from usuario as u, mensaje as m "
					+ "where u.id_user in (?, (select id_seguido from sigue where id_seguidor = ?)) "
					+ "and m.id_user = u.id_user " + "order by m.id_tweet DESC";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idUser);
			statement.setInt(2, idUser);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

}
