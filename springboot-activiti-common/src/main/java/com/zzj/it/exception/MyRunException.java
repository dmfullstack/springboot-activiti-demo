package com.zzj.it.exception;
/**
 * 自定义异常
 * @author zhouzj
 *
 */
public class MyRunException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code = 500;

	private boolean propertiesKey = false;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean isPropertiesKey() {
		return propertiesKey;
	}

	public void setPropertiesKey(boolean propertiesKey) {
		this.propertiesKey = propertiesKey;
	}

	public MyRunException(String msg) {
		super(msg);
	}

	public MyRunException(Throwable throwable) {
		super(throwable);
	}

	public MyRunException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public MyRunException(Integer code, String msg) {
		this(code, msg, true);
	}

	public MyRunException(Integer code, String msg, boolean key) {
		super(msg);
		this.code = code;
		this.propertiesKey = key;
	}

	public MyRunException(Integer code, String msg, Throwable throwable) {
		this(code, msg, throwable, true);
	}

	public MyRunException(Integer code, String msg, Throwable throwable, boolean key) {

		super(msg, throwable);
		this.code = code;
		this.propertiesKey = key;
	}

}
