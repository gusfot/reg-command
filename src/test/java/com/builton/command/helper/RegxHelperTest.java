package com.builton.command.helper;

import org.json.simple.JSONArray;
import org.junit.Test;

public class RegxHelperTest {

	/**
	 * 숫자추출 테스트 
	 */
	@Test
	public void help() {
		
		String regex = "\\d+";
		String text = "12가3";
		
		JSONArray result = RegxHelper.regex(regex, text);
		System.out.println(result.toJSONString());
		 
	}
	 
	/**
	 * 비교 패턴 추출 테스트 
	 */
	@Test
	public void test() {
		
		String regex="\\d+(\\=+[(]?(\\d+([+*xX]\\d+)?)[)]?)+";
		String text ="10=10=10 * 4매";
		
		JSONArray result = RegxHelper.regex(regex, text);
		
		System.out.println(result);
	}
	
	
	
	@Test
	public void test2() {
		
		String regex="\\d*\\매\\x\\d*\\팩[+]\\d*\\매";
		regex = regex.replace("\\x", "[x]");	// java에서 x는 hexadecimal로 판단..
		String text ="하기스 기저귀 20매x3팩+60매";
		
		JSONArray result = RegxHelper.regex(regex, text);
		
		System.out.println(result);
	}
	
	@Test
	public void testToPattern() {
		
		String text="N매xN팩+N매";
//		regex = regex.replace("\\x", "[x]");	// java에서 x는 hexadecimal로 판단..
//		String text ="하기스 기저귀 20매x3팩+60매";
		
		String result = RegxHelper.toPattern(text);
		
		System.out.println(result);
	}
}
