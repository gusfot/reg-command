package com.builton.command.helper;

import org.json.simple.JSONArray;
import org.junit.Test;

public class RegxHelperTest {

	@Test
	public void help() {
		
		String regex = "\\d+";
		String text = "12가3";
		
		JSONArray result = RegxHelper.help(regex, text);
		System.out.println(result.toJSONString());
		 
	}
	 
}
