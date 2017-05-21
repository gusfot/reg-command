package com.builton.command.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.builton.command.collection.NCollection;

public class GExpHelperTest {

	@Test
	public void testHelp() {
		
		
//		NCollection collection = new NCollection();
//		
//		collection.add(10);
//		collection.add(20);
//		collection.add(30);
//		collection.add(40);
//		collection.add(50);
		
		
//		String text = "#1 #2 #3 #4 + #5";
		String text = "#1=(#3/#2)=(20)=(60/3) 매 * (#2+(#3/#1)) 팩";
		
		JSONArray resultList = GExpHelper.help(text);
		
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
		
		String text = "#1=(#3/#2)=(20)=(60/3) 매 * (#2+(#3/#1)) 팩";
		
		String result = GExpHelper.help(text, collection);
		
		System.out.println("original text: " + text);
		System.out.println("result text: " + result);
	}
	
}
