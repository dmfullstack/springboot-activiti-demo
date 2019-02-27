package com.zzj.it.moudels.example.entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String identity;

	private String userName;

	private int amount;
	// 优惠后金额
	private int afterDiscount;
	// 折扣后金额
	private double result;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

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

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = amount * result;
	}

}
