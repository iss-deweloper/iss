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
			<div class="column_1_box">
				<div class="column_3_third main_section">
					<a href="content"> <img class="img"
						src="<c:url value="/Resources/img/icons/publish.png"/>"> <br />Publish
					</a>
				</div>
				<div class="column_3_third main_section">
					<a href="screens"> <img class="img"
						src="<c:url value="/Resources/img/icons/screen_double.png"/>"> <br />Screens
					</a>
				</div>
				<div class="column_3_third main_section">
					<a href="tags"> <img class="img"
						src="<c:url value="/Resources/img/icons/tag_double.png"/>"> <br />Tags
					</a>
				</div>
			</div>
		</div>
		<div class="column_2_center">
			<div class="column_1">
				<div class="column_3_third help_text">
					<p>
						In the <b>publish</b> panel you can see already published content.
					</p>
					<p>You can also:
					<p>
					<ul>
						<li>add new picture from internet</li>
						<li>add new picture from computer</li>
						<li>assign and remove tags from pictures</li>
					</ul>

				</div>
				<div class="column_3_third help_text">
					<p>
						In the <b>screens</b> section you can see existing screens.
					<p>You can also:
					<p>
					<ul>
						<li>add new screen</li>
						<li>assign or remove tags from screens</li>
					</ul>
				</div>
				<div class="column_3_third help_text">
					<p>
						In the <b>tags</b> section you can see existing tags.
					<p>You can also:
					<p>
					<ul>
						<li>add new tags</li>
						<li>assign or remove tags from screens</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>