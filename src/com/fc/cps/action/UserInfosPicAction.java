package com.fc.cps.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;

import com.fc.cps.dao.UserDao;
import com.fc.cps.dao.impl.UserDaoImpl;
import com.fc.cps.entity.UserEntity;
import com.fc.cps.entity.UserLoginEntity;
import com.mysql.cj.Session;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class UserInfosPicAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao = new UserDaoImpl();
       
    public UserInfosPicAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		UserLoginEntity userLoginEntity = (UserLoginEntity)request.getSession().getAttribute("UserLoginEntity");
		String path = request.getSession().getServletContext().getRealPath("")+"\\img_cache\\user_pic\\";
		String errMsg = "{\"result\":\"-1\"}", okMsg = "{\"result\":\"1\"}";
		
		String imgString = request.getParameter("img_data");
		if(imgString == null || imgString == "") {
			System.out.println("imgString为空！未收到图片数据！");
			pw.write(errMsg);
			pw.flush();
			pw.close();
			return;
		}
		
		boolean flag = userDao.stringToImgFile(imgString, path, userLoginEntity);
		
		if(flag) {
			System.out.println("保存为图片文件完成。");
			
			//刷新session中存在的用户类
			UserEntity userEntity = userDao.getUserEntityBySchoolId(userLoginEntity.getSchool_id());
			userLoginEntity.setUserEntity(userEntity);
			request.getSession().invalidate();
			request.getSession().setAttribute("UserLoginEntity", userLoginEntity);
			
			pw.write(okMsg);
		}
		else {
			System.out.println("保存为图片文件失败！");
			pw.write(errMsg);
		}
		pw.flush();
		pw.close();
	}

}
