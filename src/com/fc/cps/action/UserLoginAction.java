package com.fc.cps.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fc.cps.dao.UserDao;
import com.fc.cps.dao.jdbc.UserDaoImpl;
import com.fc.cps.entity.UserEntity;
import com.fc.cps.entity.UserLoginEntity;
import com.fc.cps.global.VerifyCodeConstants;


//@WebServlet("/LoginAction")
public class UserLoginAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao = new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter printWriter = response.getWriter();
		int flag = -1; //代表验证码验证不通过 且 账户密码验证不通过
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		
		String real_code = VerifyCodeConstants.getCodeNumber();
		if(real_code.toLowerCase().equals(code.toLowerCase()))
			flag = 0; //代表验证码验证通过 且 账户密码验证不通过
		
		UserEntity userEntity = userDao.getUserEntityBySchoolId(username);
		if(flag==0 && userEntity != null && userEntity.getPassword().equals(password)) {
			flag = 1; //代表验证码验证通过 且 账户密码验证通过
		}
		
		printWriter.print(flag);
		//printWriter.flush();
		//printWriter.close();
		
		if(flag == 1) {
			UserLoginEntity userLoginEntity = new UserLoginEntity();
			userLoginEntity.setSchool_id(userEntity.getSchool_id());
			userLoginEntity.setIp(request.getRemoteAddr());
			userLoginEntity.setLogin_time(new Date());
			userLoginEntity.setUserEntity(userEntity);
			
			request.getSession().invalidate();
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("UserLoginEntity", userLoginEntity);

			//String sessionId = httpSession.getId();
			//Cookie cookie = new Cookie("JSESSIONID", sessionId);
			//cookie.setPath(request.getContextPath());
			//response.addCookie(cookie);
			
			request.getRequestDispatcher("index.jhtml").forward(request, response);			
		}
		
		System.out.println("获取用户登录请求：code="+code+" username="+username+" password="+password+" 登录是否成功："+flag + " 登录学号："+userEntity.getSchool_id());
			
	}

}
