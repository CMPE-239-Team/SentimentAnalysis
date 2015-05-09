package com.cmpe239.sentimentAnalysis.Model;

import java.util.Date;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.URLEntity;

public class Tweet {
	private Date createdAt;
	private int favouriteCount;
	private int retweetCount;
	private long tweetId;
	private TweetUser user;
	private List<String> hashTags;
	private List<URLEntity> urls;
	private List<TweetUser> mentionedUsers;
	private String tweetText;
	private GeoLocation location;
	private boolean originalTweet;
	
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
	public List<String> getHashTags() {
		return hashTags;
	}
	public void setHashTags(List<String> hashTags) {
		this.hashTags = hashTags;
	}
	public List<URLEntity> getUrls() {
		return urls;
	}
	public void setUrls(List<URLEntity> urls) {
		this.urls = urls;
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
	public List<TweetUser> getMentionedUsers() {
		return mentionedUsers;
	}
	public void setMentionedUsers(List<TweetUser> mentionedUsers) {
		this.mentionedUsers = mentionedUsers;
	}
	
	@Override
	public String toString() {
		return "Tweet [createdAt=" + createdAt + ", favouriteCount="
				+ favouriteCount + ", retweetCount=" + retweetCount
				+ ", tweetId=" + tweetId + ", user=" + user.getScreenName() + ", hashTags="
				+ hashTags.size() + ", urls=" + urls.size() + ", mentionedUsers="
				+ mentionedUsers.size() + ", tweetText=" + tweetText + ", location="
				+ location + ", originalTweet=" + originalTweet + "]";
	}
}
