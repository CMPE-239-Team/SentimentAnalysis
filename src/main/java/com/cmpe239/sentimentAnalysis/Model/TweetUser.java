package com.cmpe239.sentimentAnalysis.Model;


public class TweetUser {
	private long userId;
	private String userName;
	private String screenName;
	private int followers;
	private int friends;
	private int favourites;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public int getFriends() {
		return friends;
	}
	public void setFriends(int friends) {
		this.friends = friends;
	}
	public int getFavourites() {
		return favourites;
	}
	public void setFavourites(int favourites) {
		this.favourites = favourites;
	}
}
