package com.fc.cps.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.cps.dao.IndexNewsDao;
import com.fc.cps.dao.impl.IndexNewsDaoImpl;
import com.fc.cps.entity.IndexNewsEntity;


//@WebServlet("/IndexNewsAction")
public class IndexNewsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IndexNewsDao indexNewsDao = new IndexNewsDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nid = Integer.valueOf(request.getParameter("nid"));
		IndexNewsEntity indexNewsEntity = indexNewsDao.getIndexNewsByNid(nid);
		request.setAttribute("IndexNewsEntity", indexNewsEntity);
		request.getRequestDispatcher("/WEB-INF/pages/index_news.jsp").forward(request, response);
		System.out.println("获取新闻实体类完成（nid="+nid+"），已跳转。");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
