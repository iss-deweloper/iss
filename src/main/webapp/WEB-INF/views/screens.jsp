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
<link href="<c:url value="/Resources/css/tags.css" />" rel="stylesheet">
<link href="<c:url value="/Resources/css/top_menu.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/layer_content.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/content_styles.css" />"
	rel="stylesheet">

<title>Screens</title>
</head>
<body>
	<jsp:include page="header.jsp"/>

	<div class="column_1">
		<div class="column_2_center">
			<div class="column_2_half_box">
				<h2>ADD NEW SCREEN</h2>
				<div class="column_3_half align_middle">
					<form method="post" accept-charset="UTF-8">
						<p class="name">Screen name:</p>
						<input type="text" name="screenName" maxlength="31" />
						<p class="name">Screen description:</p>
						<input type="text" name="screenDescription" maxlength="255" />
				</div>
				<div class="column_3_half align_center align_middle">
					<input type="image" src="<c:url value="/Resources/img/icons/screen_green.png"/>" />
					<br /> <br /> ADD NEW SCREEN
					</form>
				</div>
			</div>
		</div>
	</div>


	<div class="column_2_center">
		<c:forEach var="screen" items="${screens}">
			<div class="column_2_half_box">
				<div class="column_3_half">
					<img src="<c:url value="/Resources/img/icons/screen.png"/>"><br>
					<p class="description">Screen name:</p>
					<p class="title">${screen.name}</p>
					<p class="description">Screen description:</p>
					<p class="title">${screen.description}</p>
					<c:url var="url" scope="page" value="/screen">
						<c:param name="id" value="${screen.id}"></c:param>
					</c:url>
					<div class="column_3_half">
						<a href="${url}"><img src="<c:url value="/Resources/img/icons/settings.png"/>"
							height="64px" width="64px"><br />EDIT</a>
					</div>
					<c:url var="url_preview" scope="page" value="/slideshow">
						<c:param name="id" value="${screen.name}"></c:param>
					</c:url>
					<div class="column_3_half">
						<a href="${url_preview}"><img
							src="<c:url value="/Resources/img/icons/zoom.png"/>" height="64px" width="64px"><br />VIEW</a>
					</div>
				</div>
				<div class="column_3_half tagbox">
					<p class="name">Tags:</p>
					<c:forEach var="tag" items="${screen.tags}">
						<div class="tagpicture">
							<img src="<c:url value="/Resources/img/icons/tag_head_orange_small.png"/>"><span
								class="tag_body">${tag.name}</span><img
								src="<c:url value="/Resources/img/icons/tag_tail_small.png"/>">
						</div>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>