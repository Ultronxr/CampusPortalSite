package com.fc.cps.dao;

import com.fc.cps.entity.UserEntity;
import com.fc.cps.entity.UserLoginEntity;

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
	
	/**
	 * 更新用户的信息
	 * 目的：把param列改成paramCon
	 * 条件：当condition为conditionCon时
	 */
	boolean updateUserEntityInfos(String param, String paramCon, String condition, String conditionCon);
	
	
	/**
	 * 把用户提交的新的图片base64字符串转换为图片文件保存
	 */
	boolean stringToImgFile(String imgString, UserLoginEntity userLoginEntity); 
}
