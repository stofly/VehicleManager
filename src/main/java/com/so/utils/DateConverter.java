package com.so.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	private static final String FORMATDATE = "yyyy-MM-dd";
	private static final String FORMATTIME = "yyyy-MM-dd HH:mm:ss";

	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println(5464564);
		if (values == null || values.length == 0) {
			return null;
		}
		// 有时分秒的要先转换
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATTIME);
		Date date = null;
		String dateString = values[0];
		if (dateString != null) {
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				date = null;
			}
			if (date == null) {
				sdf = new SimpleDateFormat(FORMATDATE);
				try {
					date = sdf.parse(dateString);
				} catch (ParseException e) {
					date = null;
				}
			}
		}
		return date;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMATTIME);
			return sdf.format((Date) o);
		}
		return "";
	}
}
