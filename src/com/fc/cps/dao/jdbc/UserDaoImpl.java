package com.fc.cps.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fc.cps.dao.UserDao;
import com.fc.cps.entity.UserEntity;

public class UserDaoImpl extends JDBCBase implements UserDao{
	
	@Override
	public UserEntity getUserEntityBySchoolId(String schoolId) {
		String sql = "SELECT * FROM users WHERE school_id=?";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UserEntity userEntity = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, schoolId);
			rs = ps.executeQuery();
			while(rs.next()){
				userEntity = new UserEntity();
				userEntity.setSchool_id(rs.getString("school_id"));
				userEntity.setPassword(rs.getString("password"));
				userEntity.setPic_url(rs.getString("pic_url"));
				userEntity.setName(rs.getString("name"));
				userEntity.setSex(rs.getString("sex"));
				userEntity.setAge(rs.getInt("age"));
				userEntity.setId_id(rs.getString("id_id"));
				userEntity.setGrade(rs.getString("grade"));
				userEntity.setClasss(rs.getString("classs"));
				userEntity.setPolitics_status(rs.getString("politics_status"));
				userEntity.setPhone_number(rs.getString("phone_number"));
				userEntity.setQq_number(rs.getString("qq_number"));
				userEntity.setEmail(rs.getString("email"));
				userEntity.setBlog(rs.getString("blog"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(rs, ps, con);
		}
		
		return userEntity;
	}
	
	
	@Override
	public boolean insertUserEntityToMysql(UserEntity userEntity) {
		String sql = "INSERT INTO users values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		int flag = -1;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userEntity.getSchool_id());
			ps.setString(2, userEntity.getPassword());
			ps.setString(3, userEntity.getPic_url());
			ps.setString(4, userEntity.getName());
			ps.setString(5, userEntity.getSex());
			ps.setInt(6, userEntity.getAge());
			ps.setString(7, userEntity.getId_id());
			ps.setString(8, userEntity.getGrade());
			ps.setString(9, userEntity.getClasss());
			ps.setString(10, userEntity.getPolitics_status());
			ps.setString(11, userEntity.getPhone_number());
			ps.setString(12, userEntity.getQq_number());
			ps.setString(13, userEntity.getEmail());
			ps.setString(14, userEntity.getBlog());
			flag = ps.executeUpdate(); //返回执行语句后受影响的行数
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(null, ps, con);
		}
		if(flag == 1) return true;
		else return false;
	}
}
