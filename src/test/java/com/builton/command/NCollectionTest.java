package com.builton.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.builton.command.collection.NCollection;
import com.builton.command.helper.RegxHelper;

public class NCollectionTest {

	
	@Test
	public void testGetKey() {
		
		String source ="하기스 기저귀 1~5단계";
		String from ="N~N단계";
		
		String fromPattern = from.replace("N", "\\d*");
		JSONArray rest = RegxHelper.regex(fromPattern, source);
		
		JSONArray nList = new JSONArray();
		
		for (Object obj : rest) {
			JSONObject jsonObj = ((JSONObject) obj);
			String group = (String)jsonObj.get("group");
			
			JSONArray nArray = RegxHelper.regex("\\d+", group);
			nList.add(nArray);
		}
		
		NCollection collection = new NCollection(nList);
		
		
		System.out.println(collection.get("#1"));
	}
	
	@Test
	public void testGetFirst() {
//		
//		NCollection list = new NCollection();
//		
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		
//		System.out.println(list.first());
	}
	
	@Test
	public void testGetLast() {
		
//		NCollection list = new NCollection();
//		
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		
//		System.out.println(list.last());
	}
}
