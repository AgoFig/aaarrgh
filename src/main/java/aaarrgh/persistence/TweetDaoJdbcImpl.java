package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Tweet;

public class TweetDaoJdbcImpl implements TweetDao{
	private static TweetDao instance = new TweetDaoJdbcImpl();
	public static TweetDao getInstance() {
		return instance;
	}
	@Override
	public void insert(Tweet tweet) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into tweet (id_tweet, tweets, id_user) values (?,?,?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, tweet.getId());
			statement.setString(2, tweet.getTweet());
			statement.setInt(3, tweet.getId_user());//verificar 
				
			
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
	public void delete(Tweet tweet) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from tweet where id_tweet = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, tweet.getId());
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
	public void update(Tweet tweet) throws PersistenceException {
		try {
			String query = "update tweet set tweets = ?, id_user = ?, where id_tweet = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, tweet.getTweet());
			statement.setInt(2,tweet.getId_user());//verificar
			statement.setInt(3, tweet.getId());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}

	public List<Tweet> findAll() throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			String query = "select * from tweet";
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
	public Tweet findById(Integer id_tweet) throws PersistenceException {
		if (id_tweet == null) {
			throw new IllegalArgumentException(
					"El id del tweet no debe ser nulo");
		}
		Tweet tweet = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from tweet where id_user = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, id_tweet);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				tweet = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return tweet;
	}

	private Tweet convertOne(ResultSet resultSet) throws SQLException {
		Tweet retorno = new Tweet();

		retorno.setId(resultSet.getInt("id_tweet"));
		retorno.setTweet(resultSet.getString("tweets"));
		retorno.setId_user(resultSet.getInt("id_user"));

		return retorno;
	}

}
