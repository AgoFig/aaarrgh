package aaarrgh.model;

public class Tweet {
	private Integer id_tweet;
	private String tweets;
	private Integer id_user;

	public Tweet() {
		super();
	}

	public Integer getId() {
		return id_tweet;
	}

	public void setId(Integer id_tweet) {
		this.id_tweet = id_tweet;
	}

	public String getTweet() {
		return tweets;
	}

	public void setTweet(String tweets) {
		this.tweets = tweets;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

}
