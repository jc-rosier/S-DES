package com.jeanchristophe.sdes;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.BitSet;

import org.junit.Test;

import com.jeanchristophe.sdes.SDES;

public class SDESTest {
	public final static String SDES_CLASSNAME = "com.jeanchristophe.sdes.SDES";
	public static Class<?> sdesClass;
	
	static {
		try {
			sdesClass = Class.forName(SDES_CLASSNAME);
		} catch (ClassNotFoundException e) {
			System.err.println("Class to test not founded");
			System.exit(1);
		}
	}
	
	@Test
	public void testP10(){
		SDES sdes = new SDES("1010000010");
		BitSet key = sdes.getKey();
		
		Method method = null;
		BitSet p10 = null;
		try {
			method = sdesClass.getDeclaredMethod("p10", BitSet.class);
			method.setAccessible(true);
			p10 = (BitSet) method.invoke(sdes, key);
			if (p10 == null) throw new NullPointerException("Invoked p10 method returned null");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("Problem during the test of the permutation p10 function");
			System.exit(1);
		}
		// 10100 00010 -> p10 -> 10000 01100
		assertEquals(true,  p10.get(0));
		assertEquals(false, p10.get(1));
		assertEquals(false, p10.get(2));
		assertEquals(false, p10.get(3));
		assertEquals(false, p10.get(4));
		assertEquals(false, p10.get(5));
		assertEquals(true,  p10.get(6));
		assertEquals(true,  p10.get(7));
		assertEquals(false, p10.get(8));
		assertEquals(false, p10.get(9));
	}
	
	@Test
	public void testCircularLeftShift(){
		SDES sdes = new SDES("1000001100");
		BitSet key = sdes.getKey();
		
		Method method = null;
		BitSet cs1 = null;
		try {
			method = sdesClass.getDeclaredMethod("circularLeftShift", BitSet.class, int.class);
			method.setAccessible(true);
			cs1 = (BitSet) method.invoke(sdes, key, 1);
			if (cs1 == null) throw new NullPointerException("Invoked circularLeftShift method returned null");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("Problem during the test of the circularLeftShift function");
			System.exit(1);
		}
		// 10000 01100 -> cshift 1 -> 00001 11000
		assertEquals(false, cs1.get(0));
		assertEquals(false, cs1.get(1));
		assertEquals(false, cs1.get(2));
		assertEquals(false, cs1.get(3));
		assertEquals(true,  cs1.get(4));
		assertEquals(true,  cs1.get(5));
		assertEquals(true,  cs1.get(6));
		assertEquals(false, cs1.get(7));
		assertEquals(false, cs1.get(8));
		assertEquals(false, cs1.get(9));
		
		BitSet cs2 = null;
		try {
			cs2 = (BitSet) method.invoke(sdes, key, 2);
			if (cs2 == null) throw new NullPointerException("Invoked circularLeftShift method returned null");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("Problem during the test of the circularLeftShift function");
			System.exit(1);
		}
		
		// Using the global KEY defined above
		// 10000 01100 -> cshift 2 -> 00010 10001
		assertEquals(false, cs2.get(0));
		assertEquals(false, cs2.get(1));
		assertEquals(false, cs2.get(2));
		assertEquals(true,  cs2.get(3));
		assertEquals(false, cs2.get(4));
		assertEquals(true,  cs2.get(5));
		assertEquals(false, cs2.get(6));
		assertEquals(false, cs2.get(7));
		assertEquals(false, cs2.get(8));
		assertEquals(true,  cs2.get(9));
	}
}
