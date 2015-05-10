package com.cmpe239.sentimentAnalysis.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TweetResult")
public class TweetResult {
	
	@Id
	private String id;
	private String query;
	private int positiveCount;
	private int negativeCount;
	private int neutralCount;
	private double positiveScore;
	private double negativeScore;
	private List<Tweet> tweetList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getPositiveCount() {
		return positiveCount;
	}

	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}

	public int getNegativeCount() {
		return negativeCount;
	}

	public void setNegativeCount(int negativeCount) {
		this.negativeCount = negativeCount;
	}

	public int getNeutralCount() {
		return neutralCount;
	}

	public void setNeutralCount(int neutralCount) {
		this.neutralCount = neutralCount;
	}

	public double getPositiveScore() {
		return positiveScore;
	}

	public void setPositiveScore(double positiveScore) {
		this.positiveScore = positiveScore;
	}

	public double getNegativeScore() {
		return negativeScore;
	}

	public void setNegativeScore(double negativeScore) {
		this.negativeScore = negativeScore;
	}

	public List<Tweet> getTweetList() {
		return tweetList;
	}

	public void setTweetList(List<Tweet> tweetList) {
		this.tweetList = tweetList;
	}

	@Override
	public String toString() {
		return "TweetResult [id=" + id + ", query=" + query + ", tweetList="
				+ tweetList.size() + ", positiveCount=" + positiveCount
				+ ", negativeCount=" + negativeCount + ", neutralCount="
				+ neutralCount + ", positiveScore=" + positiveScore
				+ ", negativeScore=" + negativeScore + "]";
	}
}
