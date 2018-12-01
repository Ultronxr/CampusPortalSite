package com.fc.cps.entity;

import java.util.Date;

public class UserLoginEntity {
	
	private String school_id;
	private String ip;
	private Date login_time;
	private UserEntity userEntity;
	
	public UserLoginEntity() {
		userEntity = new UserEntity();
	}	
	
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
	
}
