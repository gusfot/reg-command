package com.builton.command.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RegxHelper {

	public static JSONArray regex(String regex, String text) {
		
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

	public static String replace(String regex, String replacement, String source) {
		return source.replaceAll(regex, replacement);
		
	}

	public static String toPattern(String text) {
		
		String result = "";
		
		char[] chars = text.toCharArray();
		StringBuilder builder = new StringBuilder();
		
		for (char ch : chars) {
			String patternization = "\\";
			builder.append(patternization+ch + "\\s*");
//			builder.append(patternization+ch);
		}
		
		result = builder.toString().replace("\\x", "[x]"); // java에서 \\x는 hexadecimal 을 표현 
		return result;
	}
	 
}
