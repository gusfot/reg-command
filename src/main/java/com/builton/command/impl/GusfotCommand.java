package com.builton.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.builton.command.collection.NCollection;
import com.builton.command.helper.GusfotExpUtil;
import com.builton.command.helper.RegexUtil;

public class GusfotCommand extends AbstractCommand {

	private String from;
	private String to;
	private String source;
	
	NCollection nColl = null;
			
	@Override
	public Object execute() {

		String[] strArgs = ((String[])this.args);
		from = strArgs[0];
		to = strArgs[1];
		source = (String)data;
		String result = "";
		
		System.out.println("[[원본 데이터]]========================================================");
		System.out.println("-source: " + source);
		System.out.println("-to: " + to);
		System.out.println("-from: " + from);
		System.out.println("===================================================================");
		
		// 각 글단마다 \\를 붙여준다. ex) \\N\\매\\x\\N\\팩\\+\\N매 
		String fromPattern = RegexUtil.toPattern(from);
		
		// 특정문자열 'N'은 숫자를 의미하므로, d*로 치환해준다.
		fromPattern = GusfotExpUtil.replaceSpecificChar(fromPattern);
		 
		System.out.println("[[찾을 패턴]]=========================================================");
		System.out.println("-pattern: " + fromPattern);
		System.out.println("===================================================================");
		
		// 원본 문자열에서 찾고자하는 문자열 추출
		JSONArray fromMatchResults = RegexUtil.regex(fromPattern, source);
		
		System.out.println("[[매칭 결과]]=========================================================");
		System.out.println("-pattern result: " + fromMatchResults);
		System.out.println("===================================================================");		
		
		if(fromMatchResults.size() > 0) {
			
			// 'N' 문자열 리스트
			JSONArray nList = new JSONArray();
	
			for (Object matchResult : fromMatchResults) {
				JSONObject jsonObj = ((JSONObject) matchResult);
				String group = (String)jsonObj.get("group");
				
				JSONArray nArray = RegexUtil.regex("\\d+", group);	// 숫자만  추출..
				nList.add(nArray);
			}
			
			// 'N' 문자열이 있는 콜렉션 
			nColl = new NCollection(nList);

			to = GusfotExpUtil.replaceSharpKey(to, nColl);									// to 의 #1, #2 등의 #key의 값으로 변경처리
			result = excuteExpands(to);														// #s~#e 패턴일때만 동작
			result = RegexUtil.replaceCalculateAndCompare(fromPattern, result, source);		// #인덱스의 원본값으로 치환
			
			System.out.println("[[최종 결과]]=========================================================");
			System.out.println("- result: " + result);
			System.out.println("===================================================================");		
		
		}else {
			
			result = source;
		}
		
		return result;
	}

	private String excuteExpands(String replacement) {
		
		JSONArray expandMatchedResults = RegexUtil.regex("#[\\\\d|se]+\\~#[\\\\d|se]+", to); 	// #s~#e, #e~#s 추출
		StringBuilder sb = new StringBuilder();
		
		if(expandMatchedResults.size() > 0 ) {
			
			String extra = RegexUtil.replace("#[\\\\d|se]+\\~#[\\\\d|se]+", "", to);	 
			JSONArray sharpKeyMatchedResults = RegexUtil.regex("#[\\d|se]+", to);

			List<Integer> numbers = new ArrayList<>();
			
			for (Object sharpKeyResult : sharpKeyMatchedResults) {
				
				JSONObject obj = (JSONObject) sharpKeyResult;
				String sharpKey = (String)obj.get("group");
				int number = Integer.parseInt((String) nColl.get(sharpKey));
				numbers.add(number);
			}
			
			
			if(numbers.get(0) > numbers.get(1)) {
				
				int begin = numbers.get(0);
				int end = numbers.get(1);
				
				for(int i = begin; i>=end; i--) {
					sb.append(i+extra +" ");
				}
			}else {
				int begin = numbers.get(0);
				int end = numbers.get(1);
				
				for(int i = begin; i<=end; i++) {
					sb.append(i+extra +" ");
				}
			}
			
			replacement = sb.toString();
		}
		
		return replacement;
	}
}

