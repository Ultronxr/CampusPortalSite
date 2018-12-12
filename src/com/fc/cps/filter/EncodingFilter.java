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

public class EncodingFilter implements Filter {
	
	private String encode = "";

    public EncodingFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String requestURL = httpServletRequest.getRequestURI();
		//System.out.println("EncodingFilter获得请求："+requestURL);
		
		if(this.encode != null && this.encode != "") {
			request.setCharacterEncoding(this.encode);
		}else {
			request.setCharacterEncoding("utf-8");
		}
		
		chain.doFilter(httpServletRequest, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("EncodingFilter被创建");
		this.encode = fConfig.getInitParameter("encoding");
	}

}
