package com.cmpe239.sentimentAnalysis.Manager;

import java.util.Scanner;


public class TwitterTest {
	
	public static void main(String[] args) {
		TwitterTest twitter = new TwitterTest();
		try {
			twitter.loadTweets();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTweets() throws InterruptedException {
		Scanner input = new Scanner(System.in);
        String keyword = input.nextLine();
        TwitterManager.getSearchResults(keyword);
        input.close();
	}
	 
}
