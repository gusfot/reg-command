package com.builton.command.helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.builton.command.collection.NCollection;

public class GusfotExpUtil {

	static Map<String, String> specificChar = new HashMap<>();
	static {
		
		specificChar.put("N", "d*");	// "N"은 숫자를 의미
	}
	
	public static JSONArray getMatchedSharpKey(String text) {
		
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

	public static String replaceSharpKey(String text, NCollection collection) {
		
		JSONArray resultList = getMatchedSharpKey(text);
		
		for (Object object : resultList) {
			
			String search = (String) ((JSONObject)object).get("group");
			String replacement = String.valueOf(collection.get(search));
			text = text.replace(search, replacement);
			
		}
		
		return text;
	}
	
	/**
	 * "=" 비교문이 있는 경우, 모든 항이 같으면, 그 값으로 변경, 다르면 처리하지 않는다.
	 * @param regex
	 * @param text
	 * @return
	 */
	public static String compare(String gGegexp) {
		
		String regex="\\d+(\\=+[(]?(\\d+([+*xX]\\d+)?)[)]?)+";
		String replacement = "";
		
		JSONArray matchResults = RegexUtil.regex(regex, gGegexp);
		
//		System.out.println(matchResults);
		  
		for (Object object : matchResults) {
			JSONObject jObj = (JSONObject)object;
			String group = (String)jObj.get("group");
			
			String[] splts = group.split("=");
			Set<String> set = new HashSet<>();
			
			for (String spltStr : splts) {
				String calResult = CalculatorUtil.calculate(spltStr);
				set.add(calResult);
			}
			
			// 비교구문이 참일 때, 
			if(set.size() ==1) {
				Iterator<String> it = set.iterator();
				while(it.hasNext()) {
					gGegexp = gGegexp.replace(group, it.next());
					break;
				}
				
				
				
			}else {
				gGegexp = replacement;
			}
		}
		
//		System.out.println(text);
		return gGegexp;
	}

	/**
	 * 원본데이터에서 text 수식을 모두 계산 한다.
	 * @param regex
	 * @param text
	 * @return
	 */
	public static String calculate(String text) {
		
		String regex="\\(*\\d+[-+xX|*]?(\\(*\\d+([-+xX|/|*]?\\d+\\)*)*\\)*){1,}+\\)*";
		
		JSONArray matchResults = RegexUtil.regex(regex, text);
		
		System.out.println("[[계산식 매칭 결과]]====================================================");
		System.out.println(matchResults);
		System.out.println("===================================================================");
		
		for (Object object : matchResults) {
			JSONObject jObj = (JSONObject)object;
			String group = (String)jObj.get("group");
			
			String calResult = CalculatorUtil.calculate(group);
			text = text.replace(group, calResult);
		}
		
		System.out.println("[[계산식 결과]]=======================================================");
		System.out.println(text);
		System.out.println("===================================================================");
		
		return text;
	}

	/**
	 * Command에서 사용하는 특정 문자(N,..)을 치환한다.
	 * @param fromPattern
	 * @return
	 */
	public static String replaceSpecificChar(String fromPattern) {
		
		Set<String> keySet = specificChar.keySet();
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			
			String key = it.next();
			String value = specificChar.get(key);
			fromPattern = fromPattern.replace(key, value);
		}
		
		return fromPattern;
	}

	public static String compare(String gGegexp, String source) {
		
		String regex="\\d+(\\=+[(]?(\\d+([+*xX]\\d+)?)[)]?)+";
		String replacement = "";
		
		JSONArray matchResults = RegexUtil.regex(regex, gGegexp);
		
		System.out.println("[[비교연산식 매칭 결과]]=================================================");
		System.out.println(matchResults);
		System.out.println("===================================================================");
		
//		System.out.println(matchResults);
		
		  
		for (Object object : matchResults) {
			JSONObject jObj = (JSONObject)object;
			String group = (String)jObj.get("group");
			
			String[] splts = group.split("=");
			Set<String> set = new HashSet<>();
			
			for (String spltStr : splts) {
				String calResult = CalculatorUtil.calculate(spltStr);
				set.add(calResult);
			}
			
			// 비교구문이 참일 때, 
			if(set.size() ==1) {
				Iterator<String> it = set.iterator();
				while(it.hasNext()) {
					gGegexp = gGegexp.replace(group, it.next());
					break;
				}
				
				
				
			}else {
				gGegexp = replacement;
			}
		}
		
		System.out.println("[[비교연산식 결과]]====================================================");
		System.out.println(gGegexp);
		System.out.println("===================================================================");
		return gGegexp;
	}


}
