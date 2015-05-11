package com.cmpe239.sentimentAnalysis.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;

import twitter4j.HashtagEntity;
import twitter4j.Status;

import com.cmpe239.sentimentAnalysis.AlchemyAPI;
import com.cmpe239.sentimentAnalysis.DAO.HashCountDAO;
import com.cmpe239.sentimentAnalysis.DAO.TweetResultDAO;
import com.cmpe239.sentimentAnalysis.DAO.TwitterDAO;
import com.cmpe239.sentimentAnalysis.Model.HashCount;
import com.cmpe239.sentimentAnalysis.Model.HashTag;
import com.cmpe239.sentimentAnalysis.Model.Tweet;
import com.cmpe239.sentimentAnalysis.Model.TweetResult;
import com.cmpe239.sentimentAnalysis.Model.TweetUser;


public class TwitterManager {
	
	public static AlchemyAPI alchemyObj;
	
	public static boolean getSearchResults(String query, String creationId){
		try{
			alchemyObj = AlchemyAPI.GetInstanceFromFile("api_key.txt");
			
			TweetResult result = new TweetResult();
			
			List<Status> originalTweetList = TwitterDAO.getTweetByQuery(query);
			saveHahTags(originalTweetList, creationId);
			
			int positiveCount = 0;
			int negativeCount = 0;
			int neutralCount = 0;
			double totalPositiveScore = 0.0;
			double totalNegativeScore = 0.0;
		
			List<Tweet> tweetList = new ArrayList<Tweet>();
			for(Status originalTweet: originalTweetList){
				Tweet tweet = converToTweet(originalTweet);
				tweetList.add(checkSentiments(tweet));
				
				if(tweet.getSentimentTtype() != null && tweet.getSentimentTtype().equalsIgnoreCase("positive")){
					positiveCount++;
					totalPositiveScore = totalPositiveScore + tweet.getSentimentScore();
				}else if(tweet.getSentimentTtype() != null && tweet.getSentimentTtype().equalsIgnoreCase("negative")){
					negativeCount++;
					totalNegativeScore = totalNegativeScore  + tweet.getSentimentScore();
				}else if(tweet.getSentimentTtype() != null && tweet.getSentimentTtype().equalsIgnoreCase("neutral")){
					neutralCount++;
				}
			}
			
			result.setId(creationId);
			result.setNegativeCount(negativeCount);
			result.setPositiveCount(positiveCount);
			result.setNeutralCount(neutralCount);
			
			if(totalNegativeScore != 0.0 && negativeCount != 0){
				result.setNegativeScore(totalNegativeScore/negativeCount);
			}
			
			if(totalPositiveScore != 0.0 && positiveCount != 0){
				result.setPositiveScore(totalPositiveScore/positiveCount);
			}
				
			result.setQuery(query);
			result.setTweetList(tweetList);
			
			TweetResultDAO.saveTweetResults(result);
			/*System.out.println("Final Result"+result.toString());*/
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static void saveHahTags(List<Status> tweets, String creationId)throws Exception{
		HashMap<String, Integer> tagMap = getUniqueHashTags(tweets);
		List<HashTag> tagList = new ArrayList<HashTag>();
		for(String key: tagMap.keySet()){
			HashTag tag = new HashTag();
			tag.setTagName(key);
			tag.setCount(tagMap.get(key));
			tagList.add(tag);
		}
		HashCount hashtagMain = new HashCount();
		hashtagMain.setId(creationId);
		hashtagMain.setHashTags(tagList);
		HashCountDAO.saveHashTags(hashtagMain); 
	}
	
	public static Tweet checkSentiments(Tweet tweet){
		/*System.out.println("Inner Tweets"+tweet.toString());*/
		Document doc;
		try {
			doc = alchemyObj.TextGetTextSentiment(tweet.getTweetText());
			/*System.out.println("My Data:"+getStringFromDocument(doc));*/
			double sentimentScore = 0.0;

			String sentimentType = doc.getElementsByTagName("type").item(0).getTextContent().trim();
			if(!sentimentType.equalsIgnoreCase("neutral")){
				sentimentScore = Double.valueOf(doc.getElementsByTagName("score").item(0).getTextContent());
			}
			
			tweet.setSentimentScore(sentimentScore);
			tweet.setSentimentTtype(sentimentType);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tweet;
	}
	
	public static Tweet converToTweet(Status originalTweet)throws Exception{
			Tweet tweet = new Tweet();
			tweet.setTweetId(originalTweet.getId());
			tweet.setTweetText(originalTweet.getText());
			tweet.setCreatedAt(originalTweet.getCreatedAt());
			tweet.setUser(getUser(originalTweet.getUser()));
			tweet.setOriginalTweet(isOriginalTweet(originalTweet));
			tweet.setFavouriteCount(originalTweet.getFavoriteCount());
			tweet.setRetweetCount(originalTweet.getRetweetCount());
			tweet.setLocation(originalTweet.getGeoLocation());
		return tweet;
	}
	
	public static TweetUser getUser(twitter4j.User user)throws Exception{
		TweetUser userTweeted = new TweetUser();
		userTweeted.setUserId(user.getId());
		userTweeted.setUserName(user.getName());
		userTweeted.setScreenName(user.getScreenName());
		userTweeted.setFavourites(user.getFavouritesCount());
		userTweeted.setFollowers(user.getFollowersCount());
		userTweeted.setFriends(user.getFriendsCount());
		return userTweeted;
	}
	
	public static boolean isOriginalTweet(Status tweet)throws Exception{
		if(tweet.getInReplyToScreenName() != null){
			return true;
		}
		return false;
	}
	
	public static HashMap<String, Integer> getUniqueHashTags(List<Status> tweets)throws Exception{
		HashMap<String, Integer> tagMap = new HashMap<String, Integer>();
		for(Status tweet: tweets){
			HashtagEntity[] hashTags = tweet.getHashtagEntities();
			for (HashtagEntity hashTag : hashTags) {
				String tag = hashTag.getText().trim();
				if(tagMap.containsKey(tag)){
					tagMap.put(tag, tagMap.get(tag)+1);
				}else{
					tagMap.put(tag, 1);
				}
			}
		}
		return tagMap;
	}
	
/*	private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }*/
}
