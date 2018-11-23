package com.fc.cps.dao;

import java.util.List;

import com.fc.cps.entity.IndexNewsEntity;

public interface IndexNewsDao {
	
	
	
	/**
	 * 获取首页新闻列表
	 * 
	 */
	List<IndexNewsEntity> getIndexNewsList();
	
	/**
	 * 通过新闻的nid属性获取实体类
	 */
	IndexNewsEntity getIndexNewsByNid(int nid);
	
	/**
	 * 按搜索关键字获取数据库中的新闻列表
	 */
	List<IndexNewsEntity> getIndexNewsListByKeyword(String keyword);
	
	
	/**
	 * 获取当前数据库中一共的新闻数量
	 */
	int getNewsCount();
	
	
}
