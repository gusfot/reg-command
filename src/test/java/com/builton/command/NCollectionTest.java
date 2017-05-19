package com.builton.command;

import org.junit.Test;

import com.builton.command.collection.NCollection;

public class NCollectionTest {

	
	@Test
	public void testGetKey() {
		
		NCollection list = new NCollection();
		
		list.add(1);
		list.add(2);
		
		System.out.println(list.get("#s"));
	}
	
	@Test
	public void testGetFirst() {
		
		NCollection<Integer> list = new NCollection<>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		System.out.println(list.first());
	}
	
	@Test
	public void testGetLast() {
		
		NCollection<Integer> list = new NCollection<>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		System.out.println(list.last());
	}
}
