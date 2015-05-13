package com.cmpe239.sentimentAnalysis;

import java.util.Date;
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
	public @ResponseBody String home(Locale locale, Model model, String query) {
		System.out.println("Hello");
		 if(query != null){
			 /*System.out.print("Please choose your Keyword:\t");
			 Scanner input = new Scanner(System.in);
	         String keyword = input.nextLine();*/
	         String result = TwitterManager.getSearchResults(query);
	         /*input.close();*/
        	 return result;	
		 }else{
			 return "FAILURE";
		 }
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("serverTime", new Date());
		return "home";
	}
}
