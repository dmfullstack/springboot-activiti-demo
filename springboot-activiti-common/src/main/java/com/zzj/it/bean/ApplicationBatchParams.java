package com.zzj.it.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 公共批处理入参实体
 * @author zhouzj
 *
 * @param <T>
 */
public class ApplicationBatchParams<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<T> insertList;
	
	private List<T> updateList;
	
	private List<T> deleteList;

	public List<T> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<T> insertList) {
		this.insertList = insertList;
	}

	public List<T> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<T> updateList) {
		this.updateList = updateList;
	}

	public List<T> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<T> deleteList) {
		this.deleteList = deleteList;
	}
	
	

}
