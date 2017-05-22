package com.builton.command.helper;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class CalculatorUtilTest {

	@Test
	public void testCalculate() {
		
//		String text = "(60/3)";
//		String text = "(30+(40/20))";
		String text = "20==(40/2)==(20)";
		Boolean result = Boolean.valueOf(CalculatorUtil.calculate(text));
		
		System.out.println(result);
		
		assertFalse(result);
		
		
	}
	
	@Test
	public void testCalculate1() {
		
//		String text = "(60/3)";
//		String text = "(30+(40/20))";
		String text = "(20)=(20)=(60/3)=(2*10)";
		
		text= text.replace("=","==");
		System.out.println(text);
		String result =  CalculatorUtil.calculate(text);
		
		System.out.println(result);
		
		
		
	}
	
	
}
