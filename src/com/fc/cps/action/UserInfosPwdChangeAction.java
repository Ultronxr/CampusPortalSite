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
import com.fc.cps.entity.UserLoginEntity;

public class UserInfosPwdChangeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao = new UserDaoImpl();
       
    public UserInfosPwdChangeAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		
		UserLoginEntity userLoginEntity = (UserLoginEntity)request.getSession().getAttribute("UserLoginEntity");
		String md5_pwd_old = request.getParameter("md5_pwd_old");
		String md5_pwd_new = request.getParameter("md5_pwd_new");
		
		int flag = 0;
		
 		if(!userLoginEntity.getUserEntity().getPassword().equals(md5_pwd_old)) { //原密码错误
 			System.out.println("修改密码失败：原密码错误！");
			flag = -1;
		}else {
			flag = 1;
		}
 		
 		if(flag == 1) {
 			//把新密码写入数据库
 			userDao.updateUserEntityInfos("password", md5_pwd_new, "school_id", userLoginEntity.getSchool_id());
 			//清空登录状态
 			request.getSession().invalidate();
 			System.out.println("修改密码成功。");
 		}
		
 		String res = "{\"result\":\""+flag+"\"}";
		pw.write(res);
		pw.flush();
		pw.close();
	}

}
