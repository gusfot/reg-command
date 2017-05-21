package com.builton.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.builton.command.impl.GusfotCommand;

public class GusfotCommandTest {

	
	
	@Test
	public void testExecuteReduce() {
	
		Command command = new GusfotCommand();
		
		String text = "하기스 기저귀 20매x3팩+60매";
		String args[] = {"N매xN팩+N매", "#1=(#3/#2)=(20)=(60/3) 매 * (#2+(#3/#1)) 팩"};
		
		command.setData(text);
		command.setArgs(args);
		command.execute();
	}
	
	
	@Test
	public void testExecuteExpand() {
		
		String text = "하기스 기저귀 1~5단계";
		String args[] = {"N~N단계", "#s~#e단계"};
		
		Command command = new GusfotCommand();
		command.setData(text);
		command.setArgs(args);
		
		Object result = command.execute();
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
