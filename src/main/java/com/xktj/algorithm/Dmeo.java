package com.xktj.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Dmeo {
	//@Test
	public void a() {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i < 100000; i++) {
			list.add(i);
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			int val = list.getFirst();
			if (val == 1) {
				list.removeFirst();
				list.addLast(val);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end + "-" + start + "=" + (end - start));
	}
	
	@Test
	public void b() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 100000; i++) {
			list.add(i);
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			int val = list.get(0);
			if (val == 1) {
				list.remove(0);
				list.add(val);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end + "-" + start + "=" + (end - start));
	}
}
