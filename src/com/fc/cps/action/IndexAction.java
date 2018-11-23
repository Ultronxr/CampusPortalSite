package com.fc.cps.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fc.cps.dao.IndexNewsDao;
import com.fc.cps.dao.jdbc.IndexNewsDaoImpl;
import com.fc.cps.entity.IndexNewsEntity;

/**
 * 首页新闻的Servlet，调用时分为以下几种情况：
 * 		首次进入首页时（初始化），没有page和keyword参数，设置默认page为1，默认keyword为空字符串（但不是null）；
 * 		点击搜索按钮，page参数设置为1，并传入keyword参数和实际内容；
 * 		点击页号跳转按钮，page参数按照实际页号变化传入，keyword参数继承点击按钮前的情况传入；
 * 		
 * 		最后使用getIndexNewsListByKeyword方法获取相应新闻列表，然后按照页号切分该列表，传给jsp页面显示。
 *
 */
//@WebServlet("/IndexAction")
public class IndexAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IndexNewsDao indexNewsDao = new IndexNewsDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String keyword_temp = request.getParameter("keyword");
		//对应第一次访问servlet（初始化）的情况，没有keyword参数，则设置为空字符串（不是null）
		if(keyword_temp == null)  keyword_temp = "";
		String keyword = new String(keyword_temp.getBytes("iso-8859-1"), "utf-8");
		
		//根据关键字获取新闻列表，并进行反序处理（新闻按时间从新到旧排列）
		List<IndexNewsEntity> indexNewsList = indexNewsDao.getIndexNewsListByKeyword(keyword);
		Collections.reverse(indexNewsList); 
		
		//每页的新闻数量=4，默认页号=1， 默认总页数=1，总新闻数量            
		int numPerPage = 4, pageNum = 1, totalPageNum = 1, totalNewsNum = indexNewsList.size();  
		
		String page_str = request.getParameter("page");
		if(page_str != null) pageNum = Integer.valueOf(page_str); 
		//计算总页数
		totalPageNum = (totalNewsNum/numPerPage) + ((totalNewsNum%numPerPage==0)?0:1);
		totalPageNum = (totalNewsNum==0) ? 1:totalPageNum;
		//异常处理：页号越界，直接重新设置为第一页
		if(pageNum > totalPageNum || pageNum <= 0) {
			pageNum = 1;
			System.out.print("访问的页号越界， 跳转回第一页！ ");
		}
		
		//判断最后一页是否有新闻多余的特殊情况（若存在多余，则新增一页）
		int lastIndex = (pageNum*numPerPage>totalNewsNum)?(totalNewsNum):(pageNum*numPerPage);
		//把符合相应页号的新闻加入新的列表
		List<IndexNewsEntity> indexNewsListSelected = new ArrayList<>();
		for(int i = (pageNum-1)*numPerPage; i < lastIndex; i++)
			indexNewsListSelected.add(indexNewsList.get(i));
		
		request.setAttribute("IndexNewsList", indexNewsListSelected);
		request.setAttribute("PageNum", pageNum);
		request.setAttribute("Keyword", keyword);
		request.setAttribute("TotalPageNum", totalPageNum);
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
		
		System.out.println("index页面——搜索内容：\""+keyword+"\" 当前页号："+pageNum+" 该页新闻数量："+indexNewsListSelected.size());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
