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

<title>Edit screen</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="column_1">
		<div class="column_2_center">
			<div class="column_1_box">
				<c:url var="url_preview" scope="page" value="/slideshow">
					<c:param name="id" value="${screen.name}"></c:param>
				</c:url>
				<div class="column_3_third">
					<h1>Edit Screen</h1>
					<img src="/Resources/img/icons/screen.png"><br>
				</div>
				<div class="column_3_third">
					<p class="description">Screen name:</p>
					<p class="title">${screen.name}</p>
					<p class="description">Screen description:</p>
					<p class="title">${screen.description}</p>
					<p class="description">Screen address:</p>
					<p class="title"><%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>${url_preview}</p>
				</div>
				<div class="column_3_third">

					<a href="${url_preview}"><img
						src="/Resources/img/icons/zoom.png" height="64px" width="64px"></a><br />
				</div>
			</div>

			<div class="column_1_box">
				<div class="column_3_half">
					<div class="tagbox">
						<p class="name">Tags:</p>
						<c:forEach var="tag" items="${screen.tags}">
							<form method="post" action="removeTagFromScreen">
								<input type="hidden" name="tagId" value="${tag.id}" /> <input
									type="hidden" name="screenId" value="${screen.id}" />
								<div class="tagpicture">
									<input type="image"
										src="/Resources/img/icons/tag_head_red_small.png" /><span
										class="tag_body">${tag.name}</span><img
										src="/Resources/img/icons/tag_tail_small.png">
								</div>
							</form>
						</c:forEach>
					</div>
				</div>
				<div class="column_3_half">
					<div class="tagbox">
						<p class="name">Available tags:</p>
						<c:forEach var="tag" items="${tags}">
							<form method="post" action="addTagToScreen">
								<input type="hidden" name="tagId" value="${tag.id}" /> <input
									type="hidden" name="screenId" value="${screen.id}" />
								<div class="tagpicture">
									<input type="image"
										src="/Resources/img/icons/tag_head_green_small.png" /><span
										class="tag_body">${tag.name}</span><img
										src="/Resources/img/icons/tag_tail_small.png">
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