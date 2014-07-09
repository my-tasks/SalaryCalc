package org.salarycalc.util;

public class ClassName {
	
	public static String getClassName() {
		try {
			throw new RuntimeException();
		} catch (RuntimeException ex) {
			return ex.getStackTrace()[1].getClass().getSimpleName();
		}
	}
}
