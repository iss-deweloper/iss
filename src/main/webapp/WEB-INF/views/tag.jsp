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

<title>Edit tag</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="column_1">
		<div class="column_2_center">
			<div class="column_1_box">
				<div class="column_3_third">
					<h1>Edit Tag</h1>
					<img src="<c:url value="/Resources/img/icons/tag_double.png"/>"><br>
				</div>
				<div class="column_3_third">
					<p class="description">Tag name:</p>
					<p class="title">${tag.name}</p>
					<p class="description">Tag description:</p>
					<p class="title">${tag.description}</p>
				</div>
			</div>

			<div class="column_1_box">
				<div class="column_3_half">
					<div class="tagbox">
						<p class="name">Screens:</p>
						<c:forEach var="screen" items="${tag.screens}">
							<form method="post" action="removeScreenFromTag">
								<input type="hidden" name="screenId" value="${screen.id}" /> <input
									type="hidden" name="tagId" value="${tag.id}" />
								<div class="tagpicture">
									<img src="<c:url value="/Resources/img/icons/tag_head_screen_small.png"/>" /><span
										class="tag_body">${screen.name}</span><input type="image"
										src="<c:url value="/Resources/img/icons/tag_tail_red_small.png"/>">
								</div>
							</form>
						</c:forEach>
					</div>
				</div>
				<div class="column_3_half">
					<div class="tagbox">
						<p class="name">Available screens:</p>
						<c:forEach var="screen" items="${screens}">
							<form method="post" action="addScreenToTag">
								<input type="hidden" name="tagId" value="${tag.id}" /> <input
									type="hidden" name="screenId" value="${screen.id}" />
								<div class="tagpicture">
									<img src="<c:url value="/Resources/img/icons/tag_head_screen_small.png"/>" /><span
										class="tag_body">${screen.name}</span><input type="image"
										src="<c:url value="/Resources/img/icons/tag_tail_green_small.png"/>">
								</div>
							</form>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>