package com.cmpe239.sentimentAnalysis.DAO;

import com.cmpe239.sentimentAnalysis.Model.TweetResult;
import com.cmpe239.sentimentAnalysis.Utility.MongoConfig;

public class TweetResultDAO {

	public static void saveTweetResults(TweetResult result)throws Exception{
		MongoConfig.getMongoOperationsObj().save(result);
	}
}
