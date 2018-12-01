package com.fc.cps.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fc.cps.entity.UserLoginEntity;

//@WebServlet("/UserLogoutAction")
public class UserLogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession();
		UserLoginEntity userLoginEntity = (UserLoginEntity)httpSession.getAttribute("UserLoginEntity");
		String tempId = userLoginEntity.getUserEntity().getSchool_id();
		request.getSession().invalidate(); //清空session
		response.sendRedirect("index.jhtml");
		//request.getRequestDispatcher("index.jhtml").forward(request, response);
		
		System.out.println("用户注销成功：学号："+tempId);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
