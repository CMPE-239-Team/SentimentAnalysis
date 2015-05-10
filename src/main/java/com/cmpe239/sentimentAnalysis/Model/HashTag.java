package com.cmpe239.sentimentAnalysis.Model;

public class HashTag {
	
	private String tagName;
	private int count;
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "HashTag [tagName=" + tagName + ", count=" + count + "]";
	}
}
