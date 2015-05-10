package com.cmpe239.sentimentAnalysis.DAO;

import com.cmpe239.sentimentAnalysis.Model.HashCount;
import com.cmpe239.sentimentAnalysis.Utility.MongoConfig;

public class HashCountDAO {
	
	public static void saveHashTags(HashCount tags)throws Exception{
		MongoConfig.getMongoOperationsObj().save(tags);
	}
}
