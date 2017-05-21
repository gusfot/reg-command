package com.builton.command.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.builton.command.collection.NCollection;
import com.builton.command.helper.RegxHelper;

public class GusfotCommand extends AbstractCommand {

	private String from;
	private String to;
	private String source;
	
	NCollection nColl = null;
			
	@Override
	public Map<String, String> execute() {

		String[] strArgs = ((String[])this.args);
		from = strArgs[0];
		to = strArgs[1];
		source = (String)data;
		

		System.out.println("parse from...");
		
		
		// TODO : 각 글단마다 \\를 붙여준다. ex) \\N\\매\\x\\N\\팩\\+\\N매 
//		System.out.println( from.);
		String fromPattern = RegxHelper.toPattern(from); 
		fromPattern = fromPattern.replace("N", "d*");
//		fromPattern = fromPattern.replace("\\x", "[x]");	// java에서 x는 hexadecimal로 판단..
		JSONArray rest = RegxHelper.regex(fromPattern, source);
		
		JSONArray nList = new JSONArray();

		for (Object obj : rest) {
			JSONObject jsonObj = ((JSONObject) obj);
			String group = (String)jsonObj.get("group");
			
			JSONArray nArray = RegxHelper.regex("\\d+", group);
			nList.add(nArray);
		}
		
		// 'N' 문자열이 있는 콜렉션 
		nColl = new NCollection(nList);
		
		System.out.println("parse to...");
		
		System.out.println("to : " + to);
		JSONArray list = RegxHelper.regex("#[\\d|se]+", to);
		
		for (Object object : list) {
			JSONObject jObj = (JSONObject) object;
			String sharpKey = (String)jObj.get("group");
//			System.out.println(sharpKey +":" +nColl.get(sharpKey));
			to = to.replace(sharpKey, (String)nColl.get(sharpKey));
		}
		
		System.out.println(to);
		
		
		String replacement = to;
		
		source = RegxHelper.replace(fromPattern, replacement, source);
		System.out.println(source);
		
		
		
		
		
		System.out.println("executed!");
		
		return null;
	}





	/**
	 * 숫자를 지시하는 특정문자:'N'
	 * from문자열에서 "N"있을 경우 일반 정규식패턴으로 변경한다.
	 * 그리고 N에 해당하는 값들을 저장한다.
	 * @return
	 */
	private NCollection parseFrom() {
		
		System.out.println("parse from...");
		
		NCollection list = null;
		
		String fromPattern = from.replace("N", "\\d*");
		
		String regex = fromPattern;
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		
		
		while (matcher.find()) {

			list = getMatchedList("\\d*", matcher.group());
			break;
		}
		
		return list;
	}

	private NCollection getMatchedList(String regex, String text) {
		
		NCollection list = new NCollection();
		
		// 숫자만 추출
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			if(!"".equals(matcher.group())) {
//				System.out.println("group: "+matcher.group() + "start: "+ matcher.start() + "end: " +matcher.end());
//				list.add(matcher.group());
			}
//			break;
		}
		
		
		return list;
	}
	
	
	


}

