package com.builton.command.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.builton.command.collection.NCollection;

public class GusfotCommand extends AbstractCommand{

	private String from;
	private String to;
	private String source;
	
	@Override
	public Map<String, String> execute() {

		String[] strArgs = ((String[])this.args);
		from = strArgs[0];
		to = strArgs[1];
		source = (String)data;
		

		System.out.println("parse from...");
		
		NCollection fromResults = null;
		
		String fromPattern = from.replace("N", "\\d*");
		String regex = fromPattern;
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		
		
		while (matcher.find()) {
			fromResults = getMatchedList("\\d*", matcher.group());
			break;
		}
		

		System.out.println("parse to...");
		
		NCollection<String> list = (NCollection<String>)getMatchedList("#[\\d|se]*", to);
		
		if(list.size() > 0) {
			System.out.println(list.first());
		}
		
		
		System.out.println("executed!");
		
		return null;
	}

	private List parseTo(NCollection<Integer> fromResults) {
		
		System.out.println("parse to...");
		
		NCollection<String> list = (NCollection<String>)getMatchedList("#[\\d|se]*", to);
		
		
		System.out.println("first : "  + fromResults.get((String) list.first()));
		System.out.println("last : " + fromResults.get((String) list.last()));
		
		int first = fromResults.get((String) list.first());
		int last = fromResults.get((String) list.first());
		if(fromResults.get(list.first()) >= fromResults.get( list.last()) ) {
			System.out.println("처음부터");
		}else {
			System.out.println("뒤에부터");
		}
		
		return list;
		
	}



	/**
	 * 숫자를 지시하는 특정문자:'N'
	 * from문자열에서 "N"있을 경우 일반 정규식패턴으로 변경한다.
	 * 그리고 N에 해당하는 값들을 저장한다.
	 * @return
	 */
	private NCollection<Integer> parseFrom() {
		
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

	private NCollection<String> getMatchedList(String regex, String text) {
		
		NCollection<String> list = new NCollection<>();
		
		// 숫자만 추출
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			if(!"".equals(matcher.group())) {
//				System.out.println("group: "+matcher.group() + "start: "+ matcher.start() + "end: " +matcher.end());
				list.add(matcher.group());
			}
//			break;
		}
		
		
		return list;
	}
	
	
	


}

