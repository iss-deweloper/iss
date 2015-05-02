<!-- 
Copyright (C) 2014,2015 Tomasz Bosak.
-->
<%@page import="pl.tobo.ISS.utils.StringConstants"%>
<%@page import="com.sun.jndi.toolkit.url.Uri"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/Resources/css/top_menu.css" />"
	rel="stylesheet">
<title>ISS - add new screen</title>
</head>
<body>

	<h1>NEW SCREEN</h1>

	<p>
		Welcome
		<%= request.getAttribute(StringConstants.REQUEST_PARAM_SCREEN_IP_ADDRESS) %>,
		you don't have an account here.
	</p>
	<p>
		Please log into system at: <a href="./login"> this page</a> and create
		screen with Screen Name =
		<%= request.getAttribute(StringConstants.REQUEST_PARAM_SCREEN_IP_ADDRESS) %>
	</p>

</body>
</html>