package com.cmpe239.sentimentAnalysis.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.UserMentionEntity;

import com.cmpe239.sentimentAnalysis.Model.Tweet;
import com.cmpe239.sentimentAnalysis.Model.TweetUser;


public class TwitterManager {
	
	public static List<Tweet> converToTweets(List<Status> tweets){
		List<Tweet> tweetList = new ArrayList<Tweet>();
		for(Status originalTweet: tweets){
			Tweet tweet = new Tweet();
			tweet.setTweetId(originalTweet.getId());
			tweet.setTweetText(originalTweet.getText());
			tweet.setCreatedAt(originalTweet.getCreatedAt());
			tweet.setHashTags(getHashTagList(originalTweet.getHashtagEntities()));
			tweet.setUser(getUser(originalTweet.getUser()));
			tweet.setOriginalTweet(isOriginalTweet(originalTweet));
			tweet.setMentionedUsers(getMentionedUsers(originalTweet.getUserMentionEntities()));
			tweet.setFavouriteCount(originalTweet.getFavoriteCount());
			tweet.setRetweetCount(originalTweet.getRetweetCount());
			tweet.setLocation(originalTweet.getGeoLocation());
			tweet.setUrls(Arrays.asList(originalTweet.getURLEntities()));
			tweetList.add(tweet);
		}
		return tweetList;
	}
	
	public static List<String> getHashTagList(HashtagEntity[] tags){
		List<String> hashTags = new ArrayList<String>();
		for(HashtagEntity tag: tags){
			hashTags.add(tag.getText());
		}
		return hashTags;
	}
	
	public static TweetUser getUser(twitter4j.User user){
		TweetUser userTweeted = new TweetUser();
		userTweeted.setUserId(user.getId());
		userTweeted.setUserName(user.getName());
		userTweeted.setScreenName(user.getScreenName());
		userTweeted.setFavourites(user.getFavouritesCount());
		userTweeted.setFollowers(user.getFollowersCount());
		userTweeted.setFriends(user.getFriendsCount());
		return userTweeted;
	}
	
	public static boolean isOriginalTweet(Status tweet){
		if(tweet.getInReplyToScreenName() != null){
			return true;
		}
		return false;
	}
	
	public static List<TweetUser> getMentionedUsers(UserMentionEntity[] users){
		List<TweetUser> userList = new ArrayList<TweetUser>();
		for(UserMentionEntity user : users){
			TweetUser userTweeted = new TweetUser();
			userTweeted.setUserId(user.getId());
			userTweeted.setUserName(user.getName());
			userTweeted.setScreenName(user.getScreenName());
			userList.add(userTweeted);
		}
		return userList;
	}
	
	public static HashMap<String, Integer> getUniqueHashTags(List<Tweet> tweets){
		HashMap<String, Integer> tagMap = new HashMap<String, Integer>();
		for(Tweet tweet: tweets){
			List<String> hashTags = tweet.getHashTags();
			for (String hashTag : hashTags) {
				if(tagMap.containsKey(hashTag.trim())){
					tagMap.put(hashTag.trim(), tagMap.get(hashTag)+1);
				}else{
					tagMap.put(hashTag.trim(), 1);
				}
			}
		}
		return tagMap;
	}
}
