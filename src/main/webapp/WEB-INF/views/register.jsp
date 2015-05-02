<!-- 
Copyright (C) 2014,2015 Tomasz Bosak.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="pl.tobo.ISS.utils.StringConstants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/Resources/css/test.css" />" rel="stylesheet">
<title>ISS - register</title>
</head>
<body>
	<div id="login">
		<form method="post" action="register">
			<span class="description">Your new login</span> <input type="text"
				name="<%=StringConstants.REQUEST_PARAM_LOGIN_USERNAME%>" /> <span
				class="description">Password</span> <input type="password"
				name="<%=StringConstants.REQUEST_PARAM_LOGIN_PASSWORD%>" /> <span
				class="description">Password check</span> <input type="password"
				name="<%=StringConstants.REQUEST_PARAM_LOGIN_PASSWORD2%>" /> <input
				type="submit" value="Register" />
		</form>
	</div>
	<div id="message">
		<p style="color: red">${error}</p>
	</div>
	<div id="register">
		<a href="login">Login</a>
	</div>

</body>
</html>