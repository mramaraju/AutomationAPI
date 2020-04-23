
package com.fw.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.fw.framework.infra.TestContext;

/**
 * 
 * 
 *
 */
public class UtilsFramework {

	/**
	 * Writes Print StackTrace on console and log file as per chosen option
	 * 
	 * @param context Test context
	 * @param e Exception
	 */
	public static void writePrintStackTrace(TestContext context, Exception e) {
		context.getLogger().error(getPrintStackTraceAsString(e));
	}

	/**
	 * Writes Print StackTrace on console and log file as per chosen option
	 * 
	 * @param context Test context
	 * @param e Throwable
	 */
	public static void writePrintStackTrace(TestContext context, Throwable e) {
		context.getLogger().error(getPrintStackTraceAsString(e));
	}

	/**
	 * Gets Print StackTrace as a string
	 * 
	 * @param e Throwable
	 * @return String transformed stack trace
	 */
	public static String getPrintStackTraceAsString(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

}
