package com.zzj.it.util;

import java.io.UnsupportedEncodingException;

public class StringUtils extends org.apache.commons.lang3.StringUtils{

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
	
	public static boolean isBank(String str) {
		if (str == null||"".equals(str)||"".equals(str.trim()))
			return true;
		return false;
	}
	
	public static boolean isNotBank(String str) {
		if (str == null||"".equals(str)||"".equals(str.trim()))
			return false;
		return true;
	}
	
	public static byte[] getBytes(String str) {
		if(str!=null) {
			try {
				return str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		return null;
	}
	
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		
		return null;
	}
}
