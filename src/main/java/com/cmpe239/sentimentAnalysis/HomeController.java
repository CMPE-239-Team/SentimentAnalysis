package com.cmpe239.sentimentAnalysis;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmpe239.sentimentAnalysis.Manager.TwitterManager;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody String home(Locale locale, Model model, String query, String creationId) {
		 if(query != null && creationId != null){
			 /*System.out.print("Please choose your Keyword:\t");
			 Scanner input = new Scanner(System.in);
	         String keyword = input.nextLine();*/
	         boolean isSucceed = TwitterManager.getSearchResults(query, creationId);
	         /*input.close();*/
	         
	         if(isSucceed)
	        	 return "SUCCESS";
	         else
	        	 return "FAILURE";	
		 }else{
			 return "FAILURE";
		 }
	}
	
}
