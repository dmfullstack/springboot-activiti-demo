package com.zzj.it.bean;

import java.io.Serializable;
/**
 * 公共返回参数实体
 * @author zhouzj
 *
 * @param <T>
 */
public class ApplicationResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer state;
	
	private String message;
	
	private T date;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getDate() {
		return date;
	}

	public void setDate(T date) {
		this.date = date;
	}
	
	
	

}
