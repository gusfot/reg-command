package com.builton.command.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.builton.command.collection.NCollection;

public class GusfotExpUtilTest {

	@Test
	public void testHelp() {
		
		
//		String text = "#1 #2 #3 #4 + #5";
		String text = "#1=(#3/#2)=(20)=(60/3) 매 * (#2+(#3/#1)) 팩 + #2묶음";
		
		JSONArray resultList = GusfotExpUtil.getMatchedSharpKey(text);
		
		System.out.println(resultList.toJSONString());
	}
	
	@Test
	public void testHelp1() {
		
		NCollection collection = new NCollection();
//		
//		collection.add(10);
//		collection.add(20);
//		collection.add(30);
//		collection.add(40);
//		collection.add(50);
		
		String text = "#1=(#3/#2)=(20)=(60/3) * 매 ";
		
		String result = GusfotExpUtil.replaceSharpKey(text, collection);
		
		System.out.println("original text: " + text);
		System.out.println("result text: " + result);
	}
	
	
	@Test
	public void testCompare() {
		
//		String text ="10=(10*1)=10=(5+5) * 4매 ";
		String text ="20=20=20=30 매 * 12 팩";
		String result = GusfotExpUtil.compare(text);
		
		System.out.println("result:" + result + ".");
	}
	
	
	@Test
	public void testCalculate() {
		
//		String text ="10=(10*1)=10=(5+5) * 4매 ";
		String text = "20=(60/3)=(20)=(60/3) 매 * (3+(60/20))*2 팩";
		
		String result = GusfotExpUtil.calculate(text);
		
		System.out.println("result:" + result + ".");
	}
	
}
