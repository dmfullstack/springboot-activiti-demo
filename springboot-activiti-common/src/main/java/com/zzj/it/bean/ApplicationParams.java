package com.zzj.it.bean;

import java.io.Serializable;
/**
 * 公共传参实体
 * @author zhouzj
 *
 * @param <T>
 */
public class ApplicationParams<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pageNum = 1;

	private Integer pageSize = 10;

	private T data;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
