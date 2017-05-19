package com.builton.command.helper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CalculatorHelperTest {

	@Test
	public void testCalculate() {
		
//		String text = "(60/3)";
//		String text = "(30+(40/20))";
		String text = "20==(40/30)==(20)==(60/3)";
		Object result = CalculatorHelper.calculate(text);
		
		System.out.println(result);
		
		
	}
	
	@Test
	public void testCalculate1() {
		
//		String text = "(60/3)";
//		String text = "(30+(40/20))";
		String text = "(20)=(20)=(60/3)=(2*10)";
//		List<String> resultList = new ArrayList<>();
//		String[] splits = text.split("=");
		
		text= text.replace("=","==");
		System.out.println(text);
		String result =  CalculatorHelper.calculate(text);
		
//		for (String string : splits) {
//			String result =  CalculatorHelper.calculate(string);
//			resultList.add(result);
//		}
		
		System.out.println(result);
		
		
	}
	
	
}
