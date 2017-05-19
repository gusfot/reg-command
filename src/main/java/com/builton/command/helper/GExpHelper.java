package com.builton.command.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.builton.command.collection.NCollection;

public class GExpHelper {

	public static JSONArray help(String text) {
		
		Pattern pattern = Pattern.compile("#[\\d]+");
		Matcher matcher = pattern.matcher(text);
		JSONArray resultList = new JSONArray();
		
		while(matcher.find()) {
			
//			System.out.println("group: " + matcher.group() + ", start:" + matcher.start() + ", end:"  + matcher.end());
			
			JSONObject result = new JSONObject();
			result.put("group", matcher.group());
			result.put("start", matcher.start());
			result.put("end", matcher.end());

			resultList.add(result);
		}
		
		return resultList;
		
	}

	public static String help(String text, NCollection collection) {
		
		JSONArray resultList = help(text);
		
		for (Object object : resultList) {
			
			String search = (String) ((JSONObject)object).get("group");
			String replacement = String.valueOf(collection.get(search));
			text = text.replace(search, replacement);
			
		}
		
		return text;
	}

}
