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

	<h1 class="index_page">ISS - Information Screens System - HELP</h1>

	<div class="column_1">
		<div class="column_2_center">
			<div class="column_2_half_box">
			Welcome on ISS Help page.<br /> ISS is an Open Source application
			licensed under MIT license.<br /> You can find source code here: <a
				href="https://github.com/iss-deweloper/iss">github</a><br /> All
			comments, issues, bugs and improvements please add <a
				href="https://github.com/iss-deweloper/iss/issues">here</a><br/>	
				In case of reporting problems - please specify version of ISS: <%=StringConstants.ISS_VERSION%>
			</div>
		</div>
	</div>

</body>
</html>