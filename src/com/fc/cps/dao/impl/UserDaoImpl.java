package com.fc.cps.dao.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fc.cps.dao.UserDao;
import com.fc.cps.entity.UserEntity;
import com.fc.cps.entity.UserLoginEntity;

import sun.misc.BASE64Decoder;

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
				userEntity.setInstitute(rs.getString("institute"));
				userEntity.setDepartment(rs.getString("department"));
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
		String sql = "INSERT INTO users values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
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
			ps.setString(8, userEntity.getInstitute());
			ps.setString(9, userEntity.getDepartment());
			ps.setString(10, userEntity.getClasss());
			ps.setString(11, userEntity.getPolitics_status());
			ps.setString(12, userEntity.getPhone_number());
			ps.setString(13, userEntity.getQq_number());
			ps.setString(14, userEntity.getEmail());
			ps.setString(15, userEntity.getBlog());
			flag = ps.executeUpdate(); //返回执行语句后受影响的行数
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(null, ps, con);
		}
		if(flag == 1) return true;
		else return false;
	}
	
	@Override
	public boolean updateUserEntityInfos(String param, String paramCon, String condition, String conditionCon) {
		String sql = "UPDATE users SET "+param+"=? WHERE "+condition+"=?";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int flag = -1;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paramCon);
			ps.setString(2, conditionCon);
			flag = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(null, ps, con);
		}
		if(flag == 1) return true;
		else return false;
	}
	
	
	@Override
	public boolean stringToImgFile(String imgString, UserLoginEntity userLoginEntity) {
		
		//获取base64数据头，获取图片后缀名
		String imgStringHeader = imgString.substring(imgString.indexOf("data:image/"), imgString.indexOf(",")+1);
		String imgType = imgString.substring(imgString.indexOf("/")+1, imgString.indexOf(";"));
		//System.out.println(imgStringHeader);
		//System.out.println(imgType);
		
		BASE64Decoder decoder = new BASE64Decoder();
		String imgFileName = userLoginEntity.getSchool_id()+"."+imgType;
		String imgFilePath = "D:\\"+imgFileName;
		try{
            //Base64解码 
            byte[] b = decoder.decodeBuffer(imgString.substring(imgStringHeader.length()));
            for(int i = 0; i < b.length; ++i) {
                if(b[i] < 0) b[i] += 256;
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            
            //更新数据库信息
            updateUserEntityInfos("pic_url", imgFileName, "school_id", userLoginEntity.getSchool_id());
            
            return true;
        }catch(Exception e) {
        	return false;
        }
	}
}
