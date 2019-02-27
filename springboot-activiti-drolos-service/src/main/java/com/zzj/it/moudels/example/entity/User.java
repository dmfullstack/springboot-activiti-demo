package com.zzj.it.moudels.example.entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private int amount;
	//优惠后金额
	private int afterDiscount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAfterDiscount() {
		return afterDiscount;
	}

	public void setAfterDiscount(int afterDiscount) {
		this.afterDiscount = afterDiscount;
	}
	
	

}
