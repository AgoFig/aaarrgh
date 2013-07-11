package aaarrgh.model;

public class Tweet {
	private Integer idtweet;
	private String tweets;
	private Integer iduser;

	public Tweet() {
		super();
	}

	public Integer getId() {
		return idtweet;
	}

	public void setId(Integer idtweet) {
		this.idtweet = idtweet;
	}

	public String getTweet() {
		return tweets;
	}

	public void setTweet(String tweets) {
		this.tweets = tweets;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

}
