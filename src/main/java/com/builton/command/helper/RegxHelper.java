package com.builton.command.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RegxHelper {

	public static JSONArray help(String regex, String text) {
		
		JSONArray result = new JSONArray();
		
		Pattern p = Pattern.compile(regex);
		Matcher m =p.matcher(text);
//		 System.out.println(m.find());
		
		 while(m.find()) {
			 
			 JSONObject obj = new JSONObject();
//			 System.out.println(m.group() + " : " + m.start() + " : " + m.end());
			 
			 obj.put("group", m.group());
			 obj.put("start", m.start());
			 obj.put("end", m.end());
			 
			 
			 result.add(obj);
		 }
		 
		return result;	
	}
	 
}
