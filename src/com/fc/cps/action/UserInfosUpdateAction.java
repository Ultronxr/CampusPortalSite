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
import com.fc.cps.entity.UserLoginEntity;


public class UserInfosUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDaoImpl();
	
    public UserInfosUpdateAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String []array = new String[13];
		int cnt = 0;
		array[cnt++] = request.getParameter("name");
		array[cnt++] = request.getParameter("sex");
		array[cnt++] = request.getParameter("age");
		array[cnt++] = request.getParameter("id_id");
		array[cnt++] = request.getParameter("politics_status");
		array[cnt++] = request.getParameter("institute");
		array[cnt++] = request.getParameter("department");
		array[cnt++] = request.getParameter("classs");
		array[cnt++] = request.getParameter("phone_number");
		array[cnt++] = request.getParameter("qq_number");
		array[cnt++] = request.getParameter("email");
		array[cnt++] = request.getParameter("blog");
		
		cnt = 0;
		UserLoginEntity userLoginEntity = (UserLoginEntity)request.getSession().getAttribute("UserLoginEntity");
		userLoginEntity.getUserEntity().setName(array[cnt++]);
		userLoginEntity.getUserEntity().setSex(array[cnt++]);
		userLoginEntity.getUserEntity().setAge(Integer.valueOf(array[cnt++]));
		userLoginEntity.getUserEntity().setId_id(array[cnt++]);
		userLoginEntity.getUserEntity().setPolitics_status(array[cnt++]);
		userLoginEntity.getUserEntity().setInstitute(array[cnt++]);
		userLoginEntity.getUserEntity().setDepartment(array[cnt++]);
		userLoginEntity.getUserEntity().setClasss(array[cnt++]);
		userLoginEntity.getUserEntity().setPhone_number(array[cnt++]);
		userLoginEntity.getUserEntity().setQq_number(array[cnt++]);
		userLoginEntity.getUserEntity().setEmail(array[cnt++]);
		userLoginEntity.getUserEntity().setBlog(array[cnt++]);
		
		//刷新session中的用户实体类
		request.getSession().invalidate();
		request.getSession().setAttribute("UserLoginEntity", userLoginEntity);
		
		//覆盖写入数据库
		UserEntity userEntity = userLoginEntity.getUserEntity();
		userDao.replaceUserEntityToMysql(userEntity);
		
		PrintWriter pw = response.getWriter();
		pw.write("{\"result\":\"1\"}");
		pw.flush();
		pw.close();
		
	}

}
