<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fc.cps.entity.*"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CPS校园门户网站-主页</title>
	
	<script src="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-3.3.1.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-ui.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/js/index_login_register.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/js/index_page_search.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/js/md5.js" charset="utf-8"></script>
	
	<link rel="shortcut icon" href="img_cache/favicon.ico">
	<link rel="stylesheet" href="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-ui.css" />
	<link rel="stylesheet" href="/CampusPortalSite/css/index.css" />
</head>
<body>
    <div id="header">
        <div id="logo">
            <a href="/CampusPortalSite/index.jhtml"><img src="img_cache/icon_icon.png" width="200" height="60"></a>
        </div>
        <div id="title">
            <p>CPS校园门户网站</p>
        </div>
        <div class="drop-menu">
        	<c:if test="${UserLoginEntity==null}">
			    <div class="hover-btn">[登录/注册]</div>
			    <div class="drop-content">
			        <a href="javascript:void(0);" onclick="user_login_dialog()">学生账户登录</a>
			        <a href="javascript:void(0);" onclick="user_register_dialog()">学生账户注册</a>
			    </div>
			</c:if>
			<c:if test="${UserLoginEntity!=null}">
			    <div class="hover-btn">[${UserLoginEntity.userEntity.school_id}]</div>
			    <div class="drop-content">
			        <a href="userInfos.jhtml" onclick="">&nbsp;&nbsp;个人资料</a>
			        <a href="icCard.jhtml" onclick="">&nbsp;&nbsp;IC卡查询</a>
			        <a href="userLogout.jhtml">&nbsp;&nbsp;&nbsp;&nbsp;注销</a>
			    </div>
		    </c:if>
		</div>
    </div>
    <div id="container">
        <div id="container_left">
        	<div style="height: 20px;"></div>
            <div id="user_infos">
	            <c:if test="${UserLoginEntity==null}">
	                <img>
	                <div class="basic_infos">
	                    <strong><p>&nbsp;&nbsp;&nbsp;&nbsp;请登录</p></strong>
	                </div>
	            </c:if>
	            <c:if test="${UserLoginEntity!=null}">
	            	<img src="img_cache/user_pic/${UserLoginEntity.userEntity.pic_url}">
	                <div class="basic_infos">
	                    <strong><p>${UserLoginEntity.userEntity.name}</p></strong>
	                    <p>${UserLoginEntity.userEntity.institute} 学院</p>
	                    <p>${UserLoginEntity.userEntity.department} 系</p>
	                    <p>${UserLoginEntity.userEntity.classs} 班</p>
	                </div>
	                <div class="basic_links">
	                    <a href="userInfos.jhtml">[个人资料]</a><span>&nbsp;</span> 
	                    <a href="icCard.jhtml">[IC卡查询]</a><span>&nbsp;</span>
	                </div>
	            </c:if>
            </div>
            <div id="weather">
                <p>宁波天气预报</p>
                <iframe width="420" scrolling="no" height="60" frameborder="0" allowtransparency="true" src="//i.tianqi.com/index.php?c=code&id=12&icon=1&py=zhenhai&num=2&site=12"></iframe>
            </div>
            <!-- <div id="last_ip">

            </div> -->
        </div>
        <div id="container_right">
	        <div id="container_right_top">
	        	<div class="search_bar">
	        		<input class="search_keyword" type="text" value="${Keyword}">
	        		<button class="search_submit" onclick="doSearch()">搜索</button>
	        	</div>
	        	<div class="page_bar">
	        		<button class="page_button_right_final" onclick="nextFinalPage('${Keyword}','${TotalPageNum}')">尾页</button>
	        		<button class="page_button_right" onclick="nextPage('${Keyword}','${PageNum}','${TotalPageNum}')">→</button>
	        		<button class="page_button_left" onclick="lastPage('${Keyword}','${PageNum}')">←</button>
	        		<button class="page_button_left_final" onclick="lastFinalPage('${Keyword}')">首页</button>
	        		<p class="page_number">页号：${PageNum}/${TotalPageNum}</p>
	        	</div>
	        </div>
        	
            <ul id="news_ul">
            	<c:forEach items="${IndexNewsList}" var="indexNews">
					<li>
	                    <img src="img_cache/index_news/${indexNews.img_url}">
	                    <div class="news_title">
	                        <a href="/CampusPortalSite/indexNews.jhtml?nid=${indexNews.nid}" target="_blank">${indexNews.raw_title}</a>
	                    </div>
	                    <div class="news_date">
	                        <p>时间：${indexNews.date}  来源：${indexNews.source}  作者：${indexNews.author}</p>
	                    </div>
	                    <div class="news_content">
	                        <p>${indexNews.content}</p>
	                    </div>
	                </li>
				</c:forEach>
            </ul>
        </div>
    </div>
    <div id="up_button">
    	<a href="#header"><img src="img_cache/arrow_up.ico"></a>
    </div>
    
    <!-- 登录弹框 -->
    
    <div id="user_login_div">
    	<form id="user_login_form">
    		<ul>
    			<li style="margin-bottom: 5px;"><p id="user_login_infos">每个选项都是必填的。</p></li>
    			<li><label>账户名</label><input type="text" id="user_login_username" name="user_login_username"></li>
    			<li><label>密码</label><input type="password" id="user_login_password" name="user_login_password"></li>
    			<li><label>验证码 &nbsp;&nbsp;</label>
    				<img src="" onclick="refresh_verify_code()" width="100px" height="50px" id="user_login_code_img" name="user_login_code_img"><br/>
    				<input type="text" id="user_login_code" name="user_login_code"></li>
    		</ul>
    	</form>
    	
    </div>
    <div id="user_register_div">
    	<form id="user_register_form">
    		<ul>
    			<li style="margin-bottom: 5px;"><p id="user_register_infos">每个选项都是必填的。</p></li>
    			<li><label>输入学号&nbsp;(11位的纯数字)</label><input type="text" id="user_register_username" name="user_register_username"></li>
    			<li><label>输入密码&nbsp;(3-18位的字母数字下划线组合)</label><input type="password" id="user_register_password" name="user_register_password"></li>
    			<li><label>重复密码</label><input type="password" id="user_register_password_repeat" name="user_register_password_repeat"></li>
    			<li><label>验证码 &nbsp;&nbsp;</label>
    				<img src="" onclick="refresh_verify_code()" width="100px" height="50px" id="user_register_code_img" name="user_register_code_img"><br/>
    				<input type="text" id="user_register_code" name="user_register_code"></li>
    		</ul>
    	</form>
    </div>
    
    <div style="height:40px;"></div>
    <footer>
    	<p>CampusPortalSite-校园门户网站 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Copyright © 方材</p>
    </footer>
</body>
</html>