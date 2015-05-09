package com.cmpe239.sentimentAnalysis.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.cmpe239.sentimentAnalysis.Model.Tweet;


public class TwitterTest {
	
	private static final String CONSUMER_KEY = "0xPoRgArfxxLkmCrkMiIGfa8v";
	private static final String CONSUMER_SECRET= "4wMfly96g5KExX6CoQq12nsHm2ssCRHB2HzO8xPwZ7ZCsfhAqd";
	private static final String ACCESS_TOKEN = "404332231-UMGNcK1N2RwMWyyEnbQhX8Ie2IzoLeTvY5nUk32V";
	private static final String ACCESS_TOKEN_SECRET = "CcKKPexKhoYVy2TsTw7MokpdyaSJd94Uia7iWd66WFfYo";
	private static final int MAX_TWEETS = 100;
	private static final int MAX_PER_PAGE = 100;
	private ConfigurationBuilder cb;
	private Query query;
	private QueryResult result;
	private Scanner input;
	
	public static void main(String[] args) {
		TwitterTest twitter = new TwitterTest();
		try {
			twitter.loadTweets();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTweets() throws InterruptedException {
	        System.out.print("Please choose your Keyword:\t");
	        input = new Scanner(System.in);
	        String keyword = input.nextLine();
	        
	        authenticateApp();
            getTweetByQuery(keyword);
            input.close();
	}
	 
	public void getTweetByQuery(String keyword) throws InterruptedException {
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
	
	    List<Status> tweets = new ArrayList<Status>();
	
	    if (cb != null) {
	        try {
	            query = new Query(keyword);
	            query.lang("en");
	            query.setCount(MAX_PER_PAGE);
	            
	            result = twitter.search(query);
	            System.out.println("Getting Tweets...");
	            do{
	            	if(tweets.size()+ MAX_PER_PAGE <= MAX_TWEETS){
	            		tweets.addAll(result.getTweets());
	                	 query = result.nextQuery();
	                     if(query != null){
	                         result = twitter.search(query);
	                     }
	            	}else{
	            		break;
	            	}
	            }while(query !=null);
	            
	        	printTweetDetails(tweets);
	        	System.out.println("\n SIZE>>>>>>>>>>>>>>>>>>>>>>>>>"+tweets.size());
	        } catch (TwitterException te) {
	            System.out.println("te.getErrorCode() " + te.getErrorCode());
	            System.out.println("te.getExceptionCode() " + te.getExceptionCode());
	            System.out.println("te.getStatusCode() " + te.getStatusCode());
	            if (te.getStatusCode() == 401) {
	                System.out.println("Twitter Error : \nAuthentication credentials (https://dev.twitter.com/pages/auth) were missing or incorrect.\nEnsure that you have set valid consumer key/secret, access token/secret, and the system clock is in sync.");
	            } else {
	                System.out.println("Twitter Error : " + te.getMessage());
	            }
	        }
	    } else {
	        System.out.println("Some Error is there");
	    }
	}
	 
 	private void printTweetDetails(List<Status> tweets) {
 		System.out.println("\nWelcome to Tweets"+tweets.size());
 		List<Tweet> newTweets = TwitterManager.converToTweets(tweets);
 		System.out.println("New Size"+newTweets.size());
 		
 	/*	HashMap<String, Integer> tagMap = TwitterManager.getUniqueHashTags(newTweets);
 		for(String key : tagMap.keySet()){
 			System.out.println("[ "+key+" , "+tagMap.get(key)+" ]");
 		}*/
 		for (Tweet tweet : newTweets) {
//			System.out.println(tweet.getUser().getUserName());
			System.out.println(tweet.toString());
		}
 		
 		/*for (Status tweet : tweets) {
 			twitter4j.User u = tweet.getUser();
 			tweet.get
 			HashtagEntity[] tags =   tweet.getHashtagEntities();
 			tags[0].
 	        UserMentionEntity[] mentioned = tweet.getUserMentionEntities();
 	 		System.out.println("User Name"+tweet.getUser().g
 	 				+"\n Retweet Count"+tweet.getRetweetCount()
 	 				+"\n Followers Count"+tweet.getUser().getFollowersCount()
 	 				+"\n Source"+tweet.getSource()
 	 				+"\n Tweet Id"+tweet.getId()
 	 				+"\n Tweet_Text"+tweet.getText()
 	 				+"\n Mentioned Count"+mentioned.length);
 		}*/
	}
 	
 	
 	
 	private void authenticateApp(){
 		cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(CONSUMER_KEY);
        cb.setOAuthConsumerSecret(CONSUMER_SECRET);
        cb.setOAuthAccessToken(ACCESS_TOKEN);
        cb.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
 	}
}
