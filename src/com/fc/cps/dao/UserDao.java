package com.fc.cps.dao;

import com.fc.cps.entity.UserEntity;

public interface UserDao {
	
	/**
	 * 按照学号获取用户实体类
	 * 
	 */
	UserEntity getUserEntityBySchoolId(String schoolId);
	
	/**
	 * 把用户实体类加入数据库
	 * 
	 */
	boolean insertUserEntityToMysql(UserEntity userEntity);
	
}
