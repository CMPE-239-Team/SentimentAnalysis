package com.cmpe239.sentimentAnalysis.Model;

import java.util.Date;

import twitter4j.GeoLocation;

public class Tweet {
	private Date createdAt;
	private int favouriteCount;
	private int retweetCount;
	private long tweetId;
	private TweetUser user;
	private String tweetText;
	private GeoLocation location;
	private boolean originalTweet;
	private String sentimentTtype;
	private  double sentimentScore;
	
	public String getSentimentTtype() {
		return sentimentTtype;
	}
	public void setSentimentTtype(String sentimentTtype) {
		this.sentimentTtype = sentimentTtype;
	}
	public double getSentimentScore() {
		return sentimentScore;
	}
	public void setSentimentScore(double sentimentScore) {
		this.sentimentScore = sentimentScore;
	}
	public boolean isOriginalTweet() {
		return originalTweet;
	}
	public void setOriginalTweet(boolean originalTweet) {
		this.originalTweet = originalTweet;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getFavouriteCount() {
		return favouriteCount;
	}
	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}
	public int getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	public long getTweetId() {
		return tweetId;
	}
	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}
	public String getTweetText() {
		return tweetText;
	}
	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
	public GeoLocation getLocation() {
		return location;
	}
	public void setLocation(GeoLocation location) {
		this.location = location;
	}
	public TweetUser getUser() {
		return user;
	}
	public void setUser(TweetUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Tweet [createdAt=" + createdAt + ", favouriteCount="
				+ favouriteCount + ", retweetCount=" + retweetCount
				+ ", tweetId=" + tweetId + ", user=" + user + ", tweetText="
				+ tweetText + ", location=" + location + ", originalTweet="
				+ originalTweet + ", sentimentTtype=" + sentimentTtype
				+ ", sentimentScore=" + sentimentScore + "]";
	}
}
