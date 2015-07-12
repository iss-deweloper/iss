<!-- 
Copyright (C) 2014,2015 Tomasz Bosak.
-->
<%@page import="pl.tobo.ISS.utils.StringConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/Resources/css/all_style.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/main_styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/top_menu.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/layer_content.css" />"
	rel="stylesheet">

<title>ISS</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<h1 class="index_page">ISS - Information Screens System</h1>

	<div class="column_1">
		<div class="column_2_center">

			Technical info:<br /> request.getScheme() =
			<%=request.getScheme()%>
			<br /> request.getServerName() =
			<%=request.getServerName()%>
			<br /> request.getServerPort() =
			<%=request.getServerPort()%>
			<br /> request.getContextPath() =
			<%=request.getContextPath()%>
			<br /> request.getLocalAddr() =
			<%=request.getLocalAddr()%>
			<br /> request.getRequestURI() =
			<%=request.getRequestURI()%>
			<br /> request.getRequestURL() =
			<%=request.getRequestURL()%>
			<br /> request.getServletPath() =
			<%=request.getServletPath()%>
			<br /> request.getPathInfo() =
			<%=request.getPathInfo()%>
			<br /> <br /> request.getServletContext().getServletContextName() =
			<%=request.getServletContext().getServletContextName()%>
			<br />

		</div>
	</div>
	<div class="column_1">
		<div class="column_2_center">
			APPLICATION SETTINGS:<br />
			<table>
				<tr>
					<th>KEY</th>
					<th>VALUE</th>
				</tr>
				<c:forEach var="setting" items="${settingList}">
					<tr>
						<td>${setting.key}</td>
						<td>${setting.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="column_2_center">
			<form method="post" accept-charset="UTF-8">
				<p class="name">Key:</p>
				<input type="hidden" name="action" value="addsetting" /> <input
					type="text" name="key" maxlength="31" />
				<p class="name">Value:</p>
				<br /> <input type="text" name="value" maxlength="255" /> <input
					type="image" src="<c:url value="/Resources/img/icons/add.png"/>" />
			</form>
		</div>
	</div>
</body>
</html>