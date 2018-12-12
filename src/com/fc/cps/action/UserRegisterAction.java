package com.fc.cps.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.cps.dao.UserDao;
import com.fc.cps.dao.impl.UserDaoImpl;
import com.fc.cps.entity.UserEntity;
import com.fc.cps.global.VerifyCodeConstants;


//@WebServlet("/UserRegisterAction")
public class UserRegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao = new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter printWriter = response.getWriter();
		int flag = -1;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		
		String real_code = VerifyCodeConstants.getCodeNumber();
		if(real_code.toLowerCase().equals(code.toLowerCase())) { //验证码正确
			flag = 0;
		}
		
		UserEntity userEntity = userDao.getUserEntityBySchoolId(username);
		if(userEntity == null) { //用户名未冲突
			flag = 1;
		}
		if(flag == 1) {
			UserEntity userEntityNew = new UserEntity();
			userEntityNew.setSchool_id(username);
			userEntityNew.setPassword(password);
			userDao.insertUserEntityToMysql(userEntityNew);
		}
		System.out.println("获取用户注册请求： username="+username+" 注册是否成功："+flag);
		printWriter.print("{\"result\":\""+flag+"\"}");
		printWriter.flush();
		printWriter.close();

	}

}
