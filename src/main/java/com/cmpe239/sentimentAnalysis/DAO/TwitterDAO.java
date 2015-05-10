package com.cmpe239.sentimentAnalysis.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.cmpe239.sentimentAnalysis.Model.TwitterAuth;
import com.cmpe239.sentimentAnalysis.Utility.ApplicationConstants;

public class TwitterDAO {
	
	private static Query query;
	private static QueryResult result;
	
	public static List<Status> getTweetByQuery(String keyword) throws InterruptedException {
		ConfigurationBuilder cb = authenticateApp();
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
	
	    List<Status> tweets = new ArrayList<Status>();
	
	    if (cb != null) {
	        try {
	            query = new Query(keyword);
	            query.lang("en");
	            query.setCount(ApplicationConstants.MAX_PER_PAGE);
	            
	            result = twitter.search(query);
	            System.out.println("Getting Tweets...");
	            do{
	            	if(tweets.size()+ ApplicationConstants.MAX_PER_PAGE <= ApplicationConstants.MAX_TWEETS){
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
	    } 
	    return tweets;
	}
	
	private static ConfigurationBuilder authenticateApp(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		Random random = new Random();
		int newNumber = random.nextInt(3)+1;
		
		TwitterAuth auth = ApplicationConstants.getConnectionSettings(newNumber);
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(auth.getConsumerKey());
        cb.setOAuthConsumerSecret(auth.getConsumerSecret());
        cb.setOAuthAccessToken(auth.getAccessToken());
        cb.setOAuthAccessTokenSecret(auth.getAccessSecret());
        
        return cb;
 	}
	
	private static void printTweetDetails(List<Status> tweets) {
		System.out.println("\n SIZE>>>>>>>>>>>>>>>>>>>>>>>>>"+tweets.size());
 		System.out.println("\nWelcome to Tweets"+tweets.size());
// 		List<Tweet> newTweets = TwitterManager.converToTweets(tweets);
// 		System.out.println("New Size"+newTweets.size());
 		
 		/*HashMap<String, Integer> tagMap = TwitterManager.getUniqueHashTags(newTweets);
 		for(String key : tagMap.keySet()){
 			System.out.println("[ "+key+" , "+tagMap.get(key)+" ]");
 		}*/
 		/*for (Tweet tweet : newTweets) {
			System.out.println(tweet.toString());
		}*/
	}
	
}
