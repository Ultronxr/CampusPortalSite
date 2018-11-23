<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fc.cps.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新闻详情页</title>
	<link rel="stylesheet" href="/CampusPortalSite/css/index_news.css" />
</head>
<body>
	<div id="title" >
		<p>${IndexNewsEntity.raw_title }</p>
	</div>
	<div id="date">
          <p style="text-align: center;">时间：${IndexNewsEntity.date}  来源：${IndexNewsEntity.source}  作者：${IndexNewsEntity.author}</p>
      </div>
      <div id="detail_content">
          <p>${IndexNewsEntity.detail_content}</p>
      </div>
</body>
</html>

