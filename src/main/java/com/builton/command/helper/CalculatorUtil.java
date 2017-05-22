package com.builton.command.helper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorUtil {

	/**
	 * 문자열 수식 계산
	 * @param text
	 * @return
	 */
	static String calculate(String text) {
		
		String result = "";
		
	    try {
	    	
	    	ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		    result = String.valueOf(engine.eval(text));
//			System.out.println(engine.eval(text));
			
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
