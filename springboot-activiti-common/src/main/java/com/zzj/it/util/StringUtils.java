package com.zzj.it.util;

public class StringUtils {

	public static boolean isEmpty(String str) {
		if (str == null||"".equals(str))
			return true;
		return false;
	}
	
	public static boolean isNotEmpty(String str) {
		if (str == null||"".equals(str))
			return false;
		return true;
	}
	
	public static boolean isBlank(String str) {
		if (str == null||"".equals(str)||"".equals(str.trim()))
			return true;
		return false;
	}
	
	public static boolean isNotBlank(String str) {
		if (str == null||"".equals(str)||"".equals(str.trim()))
			return false;
		return true;
	}
}
