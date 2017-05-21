package com.builton.command.collection;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NCollection implements List {

	private static final String START_KEY = "s";
	private static final String END_KEY = "e";
	
	private JSONArray list;

	public NCollection() {
		
	}
	
	public NCollection(JSONArray nlist) {
		this.setList(nlist);
	}
	
	/**
	 * #1, #2, #s, #e 로 인덱스 조회
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		
		if(!key.contains("#")) {
			
			try {
				throw new Exception("index key is invalid..");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		key = key.replace("#", "");
		
		if(START_KEY.equals(key)){
			return first();
		}else if(END_KEY.equals(key)) {
			return last();
		}

		return resultList.get(Integer.valueOf(key)-1);
	}

	public Object first() {
		return resultList.get(0);
	}

	public Object last() {
		int lastIndex = this.resultList != null && this.resultList.size() > 0 ? this.resultList.size()-1 : 0;
		return this.resultList.get(lastIndex);
	}

	private JSONArray resultList;

	public void setList(JSONArray nList) {
		resultList = new JSONArray();
		for (Object oarray : nList) {
			JSONArray tmpList = (JSONArray)oarray;
			for (Object object : tmpList) {
					JSONObject jObj=(JSONObject) object;
					if(jObj.containsKey("group")) {
						resultList.add(jObj.get("group"));
					}
				
			}
		}
		this.list = nList;
		
	}

	public JSONArray getList() {
		return this.list;
	}

	public void writeJSONString(Writer out) throws IOException {
		resultList.writeJSONString(out);
	}

	public String toJSONString() {
		return resultList.toJSONString();
	}

	public String toString() {
		return resultList.toString();
	}

	public void trimToSize() {
		resultList.trimToSize();
	}

	public void ensureCapacity(int minCapacity) {
		resultList.ensureCapacity(minCapacity);
	}

	public int size() {
		return resultList.size();
	}

	public boolean containsAll(Collection c) {
		return resultList.containsAll(c);
	}

	public boolean isEmpty() {
		return resultList.isEmpty();
	}

	public boolean contains(Object o) {
		return resultList.contains(o);
	}

	public int indexOf(Object o) {
		return resultList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return resultList.lastIndexOf(o);
	}

	public Object clone() {
		return resultList.clone();
	}

	public Object[] toArray() {
		return resultList.toArray();
	}

	public Object[] toArray(Object[] a) {
		return resultList.toArray(a);
	}

	public Object get(int index) {
		return resultList.get(index);
	}

	public Object set(int index, Object element) {
		return resultList.set(index, element);
	}

	public boolean add(Object e) {
		return resultList.add(e);
	}

	public boolean equals(Object o) {
		return resultList.equals(o);
	}

	public void add(int index, Object element) {
		resultList.add(index, element);
	}

	public Object remove(int index) {
		return resultList.remove(index);
	}

	public boolean remove(Object o) {
		return resultList.remove(o);
	}

	public int hashCode() {
		return resultList.hashCode();
	}

	public void clear() {
		resultList.clear();
	}

	public boolean addAll(Collection c) {
		return resultList.addAll(c);
	}

	public boolean addAll(int index, Collection c) {
		return resultList.addAll(index, c);
	}

	public boolean removeAll(Collection c) {
		return resultList.removeAll(c);
	}

	public boolean retainAll(Collection c) {
		return resultList.retainAll(c);
	}

	public ListIterator listIterator(int index) {
		return resultList.listIterator(index);
	}

	public ListIterator listIterator() {
		return resultList.listIterator();
	}

	public Iterator iterator() {
		return resultList.iterator();
	}

	public List subList(int fromIndex, int toIndex) {
		return resultList.subList(fromIndex, toIndex);
	}

	public void forEach(Consumer action) {
		resultList.forEach(action);
	}

	public Spliterator spliterator() {
		return resultList.spliterator();
	}

	public boolean removeIf(Predicate filter) {
		return resultList.removeIf(filter);
	}

	public void replaceAll(UnaryOperator operator) {
		resultList.replaceAll(operator);
	}

	public void sort(Comparator c) {
		resultList.sort(c);
	}



}
