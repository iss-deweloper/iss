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
	<div style="color: red; text-align: center;" id="login">
		<h1>ERROR</h1>
		<a href="index"> GO BACK </a>
	</div>
	<div id="message">
		<p style="color: red">${error}</p>
	</div>

</body>
</html>


