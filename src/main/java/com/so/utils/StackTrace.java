package com.so.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class StackTrace {
	public static String getStackTrace(Throwable throwable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		throwable.printStackTrace(printWriter);
		return result.toString();
	}
}
