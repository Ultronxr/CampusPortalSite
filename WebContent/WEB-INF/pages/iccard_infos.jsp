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
	<link rel="stylesheet" href="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-ui.css" />
	<script src="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-3.3.1.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-ui.js" charset="utf-8"></script>
	<link rel="shortcut icon" href="img_cache/favicon.ico">
	
	<title>CPS校园门户网站-用户个人信息页面</title>
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
			        <a href="javascript:void(0);" onclick="">详细资料</a>
			        <a href="javascript:void(0);" onclick="">IC卡查询</a>
			        <!-- <a href="javascript:void(0);" onclick="user_logout()">&nbsp;&nbsp;&nbsp;&nbsp;注销</a> -->
			        <a href="userLogout.jhtml">&nbsp;&nbsp;&nbsp;&nbsp;注销</a>
			    </div>
		    </c:if>
		</div>
        <!-- <ul id="nav">
            <li><a href="#" onclick="">func1</a></li>
            <li><a href="#" onclick="">func2</a></li>
        </ul> -->
    </div>

</body>
</html>