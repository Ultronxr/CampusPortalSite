package com.fc.cps.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fc.cps.entity.UserLoginEntity;

public class UserFilter implements Filter {
	
	private String[] stringArray;

    public UserFilter() {
        
    }

	public void destroy() {
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("UserFilter被创建");
		String temp = fConfig.getInitParameter("checked");
		this.stringArray = temp.split(";");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		if(checkUrlIsPrivate(httpServletRequest)) {
			if(checkIsLogin(httpServletRequest)) {
				chain.doFilter(request, response);
				return;
			}
			//如果用户未登录，则转回index页面
			else {
				httpServletRequest.getRequestDispatcher("index.jhtml").forward(httpServletRequest, httpServletResponse);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	//判断url是否是隐私资源
	public boolean checkUrlIsPrivate(HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getRequestURI();
		String contextPath = httpServletRequest.getContextPath();
		
		if(this.stringArray == null) return false;
		for(String i : stringArray) {
			if(url.contains(i)) return true;
		}
		return false;
	}
	
	//判断用户是否已登录
	public boolean checkIsLogin(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		UserLoginEntity userLoginEntity = (UserLoginEntity)session.getAttribute("UserLoginEntity");
		return (userLoginEntity!=null);
	}

}
