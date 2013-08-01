package aaarrgh.model;

public class Tweet {
	private Integer idtweet;
	private String tweet;
	private Integer iduser;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

}
