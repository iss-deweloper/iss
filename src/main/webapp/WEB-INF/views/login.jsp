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
<title>ISS - login</title>
</head>
<body>

	<div id="login">
		<form method="post" action="login">
			<span class="description">YOUR LOGIN</span> <input type="text"
				name="<%=StringConstants.REQUEST_PARAM_LOGIN_USERNAME%>" /> <span
				class="description">PASSWORD</span> <input type="password"
				name="<%=StringConstants.REQUEST_PARAM_LOGIN_PASSWORD%>" /><br />
			<input type="submit" value="Login" />
		</form>
	</div>
	<div id="message">
		<p style="color: red">${error}</p>
	</div>
	<c:if test="${registrationDisabled != 'TRUE'}">
		<div id="register">
			<a href="register">New User</a>
		</div>
	</c:if>
</body>
</html>


