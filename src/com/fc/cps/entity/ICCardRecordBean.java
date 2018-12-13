package com.fc.cps.entity;

import java.util.Date;

/**
 * 某个IC卡的一条消费记录
 * 
 */
public class ICCardRecordBean {
	
	private String recordJudge;
	private float recordMoney;
	private Date recordDate;
	
	public String getRecordJudge() {
		return recordJudge;
	}
	public void setRecordJudge(String recordJudge) {
		this.recordJudge = recordJudge;
	}
	public float getRecordMoney() {
		return recordMoney;
	}
	public void setRecordMoney(float recordMoney) {
		this.recordMoney = recordMoney;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
}
