package com.fc.cps.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fc.cps.dao.ICCardDao;
import com.fc.cps.entity.ICCardInfosEntity;
import com.fc.cps.entity.ICCardRecordBean;;

public class ICCardDaoImpl implements ICCardDao{

	@Override
	public ICCardInfosEntity getICCardInfosEntityByStudentId(String studentId) {
		String sql_infos = "SELECT * FROM card_infos WHERE studentId=?"; //IC卡基础信息
		String sql_records = "SELECT * FROM card_record WHERE studentId=?"; //IC卡消费记录
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ICCardInfosEntity icCardInfosEntity = null;
		try {
			ps = con.prepareStatement(sql_infos);
			ps.setString(1, studentId);
			rs = ps.executeQuery();
			while(rs.next()){
				icCardInfosEntity = new ICCardInfosEntity();
				icCardInfosEntity.setCardId(rs.getInt("cardId"));
				icCardInfosEntity.setStudentId(rs.getString("studentId"));
				icCardInfosEntity.setMoney(rs.getFloat("money"));
			}
		}catch(SQLException e) {
			//e.printStackTrace();
			System.out.println("[x] LOCAL_FUNC_ERROR_01 连接IC卡基础信息数据库时出错！");
		}
		//没有查询到相关记录，说明没有该IC卡，直接返回，不用继续寻找消费记录
		if(icCardInfosEntity == null) 	return null;
		
		try {
			ps = con.prepareStatement(sql_records);
			ps.setString(1, studentId);
			rs = ps.executeQuery();
			while(rs.next()){
				ICCardRecordBean icCardRecordBean = new ICCardRecordBean();
				icCardRecordBean.setRecordJudge(rs.getString("recordJudge"));
				icCardRecordBean.setRecordMoney(rs.getFloat("recordMoney"));
				icCardRecordBean.setRecordDate(rs.getDate("recordDate"));
				icCardInfosEntity.getRecordList().add(icCardRecordBean);
			}
		}catch(SQLException e) {
			//e.printStackTrace();
			System.out.println("[x] LOCAL_FUNC_ERROR_02 连接IC卡消费记录数据库时出错！");
		}finally {
			JDBCUtil.closeConnection(rs, ps, con);
		}
		
		return icCardInfosEntity;
	}
	
}
