package com.cmpe239.sentimentAnalysis.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="HashCount")
public class HashCount {
	
	@Id
	private String id;
	private List<HashTag> hashTags;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<HashTag> getHashTags() {
		return hashTags;
	}
	public void setHashTags(List<HashTag> hashTags) {
		this.hashTags = hashTags;
	}
	@Override
	public String toString() {
		return "HashCount [id=" + id + ", hashTags=" + hashTags + "]";
	}
}
