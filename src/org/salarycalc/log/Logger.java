package org.salarycalc.log;

import static org.salarycalc.util.ClassName.getClassName;

public class Logger {

	public static void log(String message) {
		System.out.println("[" + getClassName() + "]: "  + message);
	}
}
