package com.cmpe239.sentimentAnalysis.Model;

public class TwitterAuth {
	
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessSecret;
	
	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessSecret() {
		return accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}

	@Override
	public String toString() {
		return "TwitterAuth [consumerKey=" + consumerKey + ", consumerSecret="
				+ consumerSecret + ", accessToken=" + accessToken
				+ ", accessSecret=" + accessSecret + "]";
	}
}
