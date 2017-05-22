package com.builton.command;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.builton.command.impl.GusfotCommand;

public class GusfotCommandTest {

	
	/**
	 * 비교식이 있을 경우, 조건에 맞으면 replace
	 */
	@Test
	public void testExecuteReduceSuccess() {
	
		Command command = new GusfotCommand();
		
		String text = "하기스 기저귀 20매x3팩+60매";
		String args[] = {"N매xN팩+N매", "#1=(#3/#2)=(20)=(60/3)=21 매 * (#2+(#3/#1)) 팩"};
		
		command.setData(text);
		command.setArgs(args);
		String result = (String) command.execute();
		
		assertTrue("하기스 기저귀 20 매 * 6 팩".equals(result));
	}
	
	/**
	 * 비교식이 있을 경우, 조건에 맞지 않으면 변화없음
	 */
	@Test
	public void testExecuteReduceFail() {
	
		Command command = new GusfotCommand();
		
		String text = "하기스 기저귀 20 매 x 3     팩  +   60           매             " ;
		String args[] = {"N매xN팩+N매", "#1=(#3/#2)=(60/3) 매 * (30)) 팩"};
		
		command.setData(text);
		command.setArgs(args);
		String result = (String) command.execute();
		
		assertTrue("하기스 기저귀 20매x3팩+60매".equals(result));
	}
	
	/**
	 * 오름차순
	 */
	@Test
	public void testExecuteExpandAcs() {
		
		String text = "하기스 기저귀 1 ~ 5 단       계    ";
		String args[] = {"N~N단계", "#s~#e단계"};
		
		Command command = new GusfotCommand();
		command.setData(text);
		command.setArgs(args);
		String result = (String) command.execute();
		
		assertTrue("하기스 기저귀 1단계 2단계 3단계 4단계 5단계 ".equals(result));
	}
	
	/**
	 * 내림차순
	 */
	@Test
	public void testExecuteExpandDesc() {
		
		String text = "하기스 기저귀 1~5단계";
		String args[] = {"N~N단계", "#e ~ #s 단계"};
		
		Command command = new GusfotCommand();
		command.setData(text);
		command.setArgs(args);
		String result = (String) command.execute();
		
		assertTrue("하기스 기저귀 5단계 4단계 3단계 2단계 1단계 ".equals(result));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testIndexPattern() {
		
		String regex = "(\\#*(\\d*))+[=]*";
		String text = "fdf #1=#2=#3=#4 dfdfdf";
		
		 Pattern p = Pattern.compile(regex);
		 Matcher m =p.matcher(text);
		 System.out.println(m.find());
		
//		System.out.println(splits);
		 while(m.find()) {
			 System.out.println(m.group() + " : " + m.start() + " : " + m.end());
		 }
		
	}
	
	
	@Test
	public void testSplitPatter() {
		
		String text = "#1=#2=#3=#4";
		String[] splits = text.split("=");
		System.out.println(splits);
		
	}

}
