package com.builton.command.collection;

import java.util.ArrayList;

public class NCollection<E> extends ArrayList<E>  {

	private static final long serialVersionUID = 1L;
	
	private static final String START_KEY = "s";
	private static final String END_KEY = "e";

	/**
	 * #1, #2, #s, #e 로 인덱스 조회
	 * @param key
	 * @return
	 */
	public E get(String key) {
		
		if(!key.contains("#")) {
			return null;
		}
		
		key = key.replace("#", "");
		
		if(START_KEY.equals(key)){
			return first();
		}else if(END_KEY.equals(key)) {
			return last();
		}

		return super.get(Integer.valueOf(key));
	}

	public E first() {
		return get(0);
	}

	public E last() {
		int lastIndex = this.size() > 0 ? this.size()-1 : 0;
		return get(lastIndex);
	}



}
