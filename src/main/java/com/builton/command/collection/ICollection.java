package com.builton.command.collection;

import java.util.List;

public interface ICollection<E> extends List{

	Object get(String key);
	
}
