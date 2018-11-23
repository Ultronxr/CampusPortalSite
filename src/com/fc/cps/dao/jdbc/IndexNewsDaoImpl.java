package com.fc.cps.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.test.context.jdbc.Sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fc.cps.dao.IndexNewsDao;
import com.fc.cps.entity.IndexNewsEntity;

public class IndexNewsDaoImpl extends JDBCBase implements IndexNewsDao{
	@Override
	public List<IndexNewsEntity> getIndexNewsList(){
		String sql = "SELECT * FROM index_news";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		IndexNewsEntity indexNewsEntity = null;
		List<IndexNewsEntity> indexNewsList = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				indexNewsEntity = new IndexNewsEntity();
				indexNewsEntity.setNid(rs.getInt("nid"));
				indexNewsEntity.setImg_url(rs.getString("img_url"));
				indexNewsEntity.setRaw_title(rs.getString("raw_title"));
				indexNewsEntity.setDate(rs.getString("date"));
				indexNewsEntity.setSource(rs.getString("source"));
				indexNewsEntity.setAuthor(rs.getString("author"));
				indexNewsEntity.setContent(rs.getString("content"));
				indexNewsEntity.setDetail_content(rs.getString("detail_content"));
				indexNewsList.add(indexNewsEntity);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(rs, ps, con);
		}
		
		return indexNewsList;
	}
	
	
	@Override
	public IndexNewsEntity getIndexNewsByNid(int nid) {
		String sql = "SELECT * FROM index_news WHERE nid=?";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		IndexNewsEntity indexNewsEntity = new IndexNewsEntity();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, nid);
			rs = ps.executeQuery();
			while(rs.next()){
				indexNewsEntity.setNid(rs.getInt("nid"));
				indexNewsEntity.setImg_url(rs.getString("img_url"));
				indexNewsEntity.setRaw_title(rs.getString("raw_title"));
				indexNewsEntity.setDate(rs.getString("date"));
				indexNewsEntity.setSource(rs.getString("source"));
				indexNewsEntity.setAuthor(rs.getString("author"));
				indexNewsEntity.setContent(rs.getString("content"));
				indexNewsEntity.setDetail_content(rs.getString("detail_content"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(rs, ps, con);
		}
		return indexNewsEntity;
	}
	
	
	
	@Override
	public List<IndexNewsEntity> getIndexNewsListByKeyword(String keyword){
		String sql = "SELECT * FROM index_news WHERE raw_title like ? OR date like ? OR source like ? OR author like ? OR content like ?";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		IndexNewsEntity indexNewsEntity = null;
		List<IndexNewsEntity> indexNewsListByKeyword = new ArrayList<>();
		try {
			keyword = "%"+keyword+"%";
			ps = con.prepareStatement(sql);
			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setString(3, keyword);
			ps.setString(4, keyword);
			ps.setString(5, keyword);
			rs = ps.executeQuery();
			while(rs.next()){
				indexNewsEntity = new IndexNewsEntity();
				indexNewsEntity.setNid(rs.getInt("nid"));
				indexNewsEntity.setImg_url(rs.getString("img_url"));
				indexNewsEntity.setRaw_title(rs.getString("raw_title"));
				indexNewsEntity.setDate(rs.getString("date"));
				indexNewsEntity.setSource(rs.getString("source"));
				indexNewsEntity.setAuthor(rs.getString("author"));
				indexNewsEntity.setContent(rs.getString("content"));
				indexNewsEntity.setDetail_content(rs.getString("detail_content"));
				indexNewsListByKeyword.add(indexNewsEntity);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(rs, ps, con);
		}
		return indexNewsListByKeyword;
	}
	
	
	@Override
	public int getNewsCount() {
		String sql = "SELECT COUNT(*) FROM index_news";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = -1;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(rs, ps, con);
		}
		return count;
	}
	

	
}
