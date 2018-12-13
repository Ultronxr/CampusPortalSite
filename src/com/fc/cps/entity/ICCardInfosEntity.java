package com.fc.cps.entity;

import java.util.ArrayList;

/**
 * IC卡的信息
 * 包括IC卡id，学号，余额，保存消费记录的列表
 * 
 */
public class ICCardInfosEntity {
	private int cardId;
	private String studentId;
	private float money;
	private ArrayList<ICCardRecordBean> recordList;
	
	public ICCardInfosEntity() {
		recordList = new ArrayList<>();
	}
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public ArrayList<ICCardRecordBean> getRecordList() {
		return recordList;
	}
	public void setRecordList(ArrayList<ICCardRecordBean> recordList) {
		this.recordList = recordList;
	}
	
}
