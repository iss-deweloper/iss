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

<title>Tags</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="column_1">
		<div class="column_2_center">
			<div class="column_2_half_box">
				<h2>ADD NEW TAG</h2>
				<div class="column_3_half align_middle">
					<form method="post" accept-charset="UTF-8">
						<p class="name">Tag name:</p>
						<input type="text" name="tagName" maxlength="31" />
						<p class="name">Tag description:</p>
						<input type="text" name="tagDescription" maxlength="255" />
				</div>
				<div class="column_3_half align_center align_middle">
					<input type="image" src="/Resources/img/icons/add_newtag.png" /> <br />
					<br /> ADD NEW TAG
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="column_2_center">
		<c:forEach var="tag" items="${tags}">
			<div class="column_2_half_box">
				<div class="column_3_half">
					<img src="/Resources/img/icons/tag_double.png"><br>
					<p class="description">Tag name:</p>
					<p class="title">${tag.name}</p>
					<p class="description">Tag description:</p>
					<p class="title">${tag.description}</p>
					<c:url var="url" scope="page" value="/tag">
						<c:param name="id" value="${tag.id}"></c:param>
					</c:url>
					<div class="column_3_half">
						<a href="${url}"><img src="/Resources/img/icons/settings.png"
							height="64px" width="64px"><br />EDIT</a>
					</div>
				</div>
				<div class="column_3_half tagbox">
					<p class="name">Screens:</p>
					<c:forEach var="screen" items="${tag.screens}">
						<div class="tagpicture">
							<img src="/Resources/img/icons/tag_head_screen_small.png"><span
								class="tag_body">${screen.name}</span><img
								src="/Resources/img/icons/tag_tail_small.png">
						</div>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>