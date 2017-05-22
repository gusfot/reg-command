package com.builton.command.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RegexUtil {

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
		return source = source.replaceAll(regex, replacement);
	}
	
	public static String replaceCalculateAndCompare(String regex, String replacement, String source) {

		// 치환하기전 계산식이 있으면 계산한다.
		replacement = GusfotExpUtil.calculate(replacement);
		
		// 비교연산식이 있으면 연산한다.
		replacement = GusfotExpUtil.compare(replacement, source);
		
		if(!"".equals(replacement)) {
			source = source.replaceAll(regex, replacement);
		}
		
		
		return source;
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

	public static String replace(String to, JSONArray fromMatchedResults) {

		for (Object matchedResult : fromMatchedResults) {
			JSONObject obj = (JSONObject)matchedResult;
			to.replace((CharSequence) obj.get("group"), to);
		}
		System.out.println("to : " + to);
		return null;
	}
	 
}
