package com.fc.cps.dao;

import com.fc.cps.entity.ICCardInfosEntity;

public interface ICCardDao {
	
	/**
	 * 通过学生学号获取IC卡信息实体类
	 * @param studentId
	 * @return
	 */
	ICCardInfosEntity getICCardInfosEntityByStudentId(String studentId);
	
}
