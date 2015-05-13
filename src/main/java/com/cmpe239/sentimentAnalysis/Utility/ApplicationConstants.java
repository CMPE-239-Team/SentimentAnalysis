package com.cmpe239.sentimentAnalysis.Utility;

import com.cmpe239.sentimentAnalysis.Model.TwitterAuth;

public class ApplicationConstants {
	
	public static final String DATABASE = "tsa_demo";
	public static final String URL = "mongodb://myuser:password@ds031962.mongolab.com:31962/tsa_demo";
	
	public static final String ALCHEMY_API_KEY = "c8269ddb36a3bb1f8e29bfd21574dfcf24b9ea69";
	
	public static final String CONSUMER_KEY = "0xPoRgArfxxLkmCrkMiIGfa8v";
	public static final String CONSUMER_SECRET= "4wMfly96g5KExX6CoQq12nsHm2ssCRHB2HzO8xPwZ7ZCsfhAqd";
	public static final String ACCESS_TOKEN = "404332231-UMGNcK1N2RwMWyyEnbQhX8Ie2IzoLeTvY5nUk32V";
	public static final String ACCESS_TOKEN_SECRET = "CcKKPexKhoYVy2TsTw7MokpdyaSJd94Uia7iWd66WFfYo";
	
	public static final String CONSUMER_KEY2 = "zFnFKihtbKCxjLbDDQhkIgmFK";
	public static final String CONSUMER_SECRET2= "cPIZIz5SmaJ0pV2SWn0c7p81nQYZRUG55PZs92MItkewEM8AKv";
	public static final String ACCESS_TOKEN2 = "404332231-3isFvDCHTYvEYz93P0FyeuPmaiCEz0SrCBBmCTlr";
	public static final String ACCESS_TOKEN_SECRET2 = "zeAf3E1jNB8zMyVMozHIycM0JV5mL2dj86wySvu63mmk1";
	
	public static final String CONSUMER_KEY3 = "Y6xdspbnjzFEUqMOG3UUD9Un5";
	public static final String CONSUMER_SECRET3= "ZtIITjR9GOslwrilaTLW83rWdDk4pBI7ysv2SBOiQbTLMjuyUr";
	public static final String ACCESS_TOKEN3 = "404332231-zhxSXnUOWr8zzCS2pkNTN000WaX5bdq0cCOpE5nI";
	public static final String ACCESS_TOKEN_SECRET3 = "jpt6y2cZh0wpKkP591dgFG6Tn54C3lBTwc356rFj5SyiS";
	
	public static final int MAX_TWEETS = 300;
	public static final int MAX_PER_PAGE = 100;
	
	public static TwitterAuth getConnectionSettings(int configNumber){
		TwitterAuth conn = new TwitterAuth();
		
		switch (configNumber) {
		case 1:
			conn.setConsumerKey(CONSUMER_KEY);
			conn.setConsumerSecret(CONSUMER_SECRET);
			conn.setAccessToken(ACCESS_TOKEN);
			conn.setAccessSecret(ACCESS_TOKEN_SECRET);
			break;
		case 2:
			conn.setConsumerKey(CONSUMER_KEY2);
			conn.setConsumerSecret(CONSUMER_SECRET2);
			conn.setAccessToken(ACCESS_TOKEN2);
			conn.setAccessSecret(ACCESS_TOKEN_SECRET2);
			break;
		case 3:
			conn.setConsumerKey(CONSUMER_KEY3);
			conn.setConsumerSecret(CONSUMER_SECRET3);
			conn.setAccessToken(ACCESS_TOKEN3);
			conn.setAccessSecret(ACCESS_TOKEN_SECRET3);
			break;
		}
		
		return conn;
	}
}
