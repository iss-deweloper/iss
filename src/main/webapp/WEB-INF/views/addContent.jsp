<!-- 
Copyright (C) 2014,2015 Tomasz Bosak.
-->
<%@page import="com.sun.jndi.toolkit.url.Uri"%>
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
<link href="<c:url value="/Resources/css/tags.css" />" rel="stylesheet">
<link href="<c:url value="/Resources/css/top_menu.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/layer_content.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/content_styles.css" />"
	rel="stylesheet">

<link href="<c:url value="/Resources/js/calendar/tcal.css" />"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/Resources/js/calendar/tcal.js"></script>

<title>Add Content</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="column_1_box">
		<div class="column_2_center">
			<form method="post" accept-charset="UTF-8">
				<div class="column_3_third align_middle">
					<p class="name">Title:</p>
					<input type="text" name="pictureTitle" maxlength="31" />
					<p class="name">Address:</p>
					<input type="text" name="pictureAddress" maxlength="255" />
				</div>
				<div class="column_3_third align_middle">
					<p class="name">Valid from</p>
					<input type="text" class="tcal" name="validFrom" />
					<p class="name">Valid to</p>
					<input type="text" class="tcal" name="validTo" />
				</div>
				<div class="column_3_third align_center align_middle">
					<input type="image" src="/Resources/img/icons/add.png" /> <br />
					<br /> ADD NEW PICTURE
				</div>
			</form>
		</div>
	</div>
</body>
</html>