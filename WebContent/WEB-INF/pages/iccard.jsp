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
	<title>CPS校园门户网站-IC卡查询页面</title>
	
	<script src="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-3.3.1.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/DataTables-1.10.15/media/js/jquery.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/DataTables-1.10.15/media/js/jquery.dataTables.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-ui.js" charset="utf-8"></script>
	<script src="/CampusPortalSite/js/iccard.js" charset="utf-8"></script>
	
	<link rel="shortcut icon" href="img_cache/favicon.ico">
	<link rel="stylesheet" href="/CampusPortalSite/DataTables-1.10.15/media/css/jquery.dataTables.css" />
	<link rel="stylesheet" href="/CampusPortalSite/jquery3.3.1-jqueryui1.12.1/jquery-ui.css" />
	<link rel="stylesheet" href="/CampusPortalSite/css/iccard.css" />
</head>
<body>
	<div id="header">
        <div id="logo">
            <a href="/CampusPortalSite/index.jhtml"><img src="img_cache/icon_icon.png" width="200" height="60"></a>
        </div>
        <div id="title">
            <p>IC卡查询页面</p>
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
			        <a href="index.jhtml" onclick="">&nbsp;&nbsp;&nbsp;&nbsp;首页</a>
			        <a href="userInfos.jhtml" onclick="">&nbsp;&nbsp;个人资料</a>
			        <a href="userLogout.jhtml">&nbsp;&nbsp;&nbsp;&nbsp;注销</a>
			    </div>
		    </c:if>
		</div>
    </div>
    <div id="container">
    	<div id="container_left">
    		<div id="iccard_infos_div">
    			<div><label>IC卡ID：</label><p>3-16401010222</p></div>
    			<div><label>IC卡余额：</label><p>58.3&nbsp;元</p></div>
    		</div>
    	</div>
    	<div id="container_right">
    		<table id="iccard_records_table" class="display">
			    <thead>
			        <tr>
			            <th>Column 1</th>
			            <th>Column 2</th>
			        </tr>
			    </thead>
			    <tbody>
			        <tr>
			            <td>Row 1 Data 1</td>
			            <td>Row 1 Data 2</td>
			        </tr>
			        <tr>
			            <td>Row 2 Data 1</td>
			            <td>Row 2 Data 2</td>
			        </tr>
			    </tbody>
			</table>
    	</div>
    </div>

</body>
</html>