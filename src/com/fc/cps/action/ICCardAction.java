package com.fc.cps.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.cps.dao.ICCardDao;
import com.fc.cps.dao.impl.ICCardDaoImpl;
import com.fc.cps.entity.ICCardInfosEntity;
import com.fc.cps.entity.UserLoginEntity;

public class ICCardAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ICCardDao icCardDao = new ICCardDaoImpl();
       
    public ICCardAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String school_id =  ((UserLoginEntity)request.getSession().getAttribute("UserLoginEntity")).getSchool_id();
		
		ICCardInfosEntity icCardInfosEntity = icCardDao.getICCardInfosEntityByStudentId(school_id);
		request.setAttribute("ICCardInfosEntity", icCardInfosEntity);
		
		request.getRequestDispatcher("/WEB-INF/pages/iccard.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
