<!-- 
Copyright (C) 2014,2015 Tomasz Bosak.
-->
<%@page import="pl.tobo.ISS.entities.Content"%>
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
<link href="<c:url value="/Resources/css/top_menu.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/picture_styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/layer_content.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/all_style.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/content_styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/tags.css" />" rel="stylesheet">

<title>Edit content</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:useBean id="now" class="java.util.Date" />

	<c:choose>
		<c:when test="${content.validFrom le now}">
			<c:choose>
				<c:when test="${content.validTo ge now}">
					<c:set var="valid" value="valid" />
				</c:when>
				<c:otherwise>
					<c:set var="valid" value="invalid" />
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:set var="valid" value="invalid" />
		</c:otherwise>
	</c:choose>

	<div class="column_1">
		<div class="column_2_center">
			<div class="column_1_box">
				<div class="contentbox column_3_threeFourth">
					<!--  TODO: make a distinction if contentType is IFRAME or Picture	-->
					<c:if test="${content.contentType == 'IFRAME'}">
						<iframe class="iframe_small" src="${content.contentAddress}"></iframe>
					</c:if>
					<c:if test="${content.contentType == 'IMG'}">
						<img class="show_image" src="${content.contentAddress}"
							title="${content.contentAddress}">
					</c:if>
				</div>
				<div class="contentbox column_3_quarter">
					<p class="description">Title:</p>
					<p class="title">${content.title }</p>
					<p class="description">Valid from:</p>
					<p class="date ${valid}">
						<fmt:formatDate value="${content.validFrom}"
							pattern="yyyy-MM-dd HH:mm" />
					</p>
					<p class="description">Valid to:</p>
					<p class="date ${valid}">
						<fmt:formatDate value="${content.validTo}"
							pattern="yyyy-MM-dd HH:mm" />
					</p>
					<p class="description">Added by:</p>
					<p class="author">${content.user.login}<br> <a
							href="deletePicture?id=${content.id}"> <img
							class="menu_picture" alt="delect_content"
							src="/Resources/img/icons/trash.png" style="float: right;"><br>
						</a>
				</div>
			</div>
			<div class="column_1_box">
				<div class="column_3_half">
					<p class="description">Connected tags:</p>
					<c:forEach var="tag" items="${content.tags}">
						<form method="post" action="removeTagFromContent">
							<input name="tagId" type="hidden" value="${tag.id}" /> <input
								name="contentId" type="hidden" value="${content.id}" />
							<div class="tagpicture">
								<input src="/Resources/img/icons/tag_head_red_small.png"
									title="${tag.description}" type="image"><span
									class="tag_body">${tag.name}</span><img
									src="/Resources/img/icons/tag_tail_small.png">
							</div>
						</form>
					</c:forEach>
				</div>
				<div class=" column_3_half">
					<p class="description">Available tags:</p>
					<c:forEach var="tag" items="${tags}">
						<form method="post" action="addTagToContent">
							<input name="tagId" type="hidden" value="${tag.id}" /> <input
								name="contentId" type="hidden" value="${content.id}" />
							<div class="tagpicture">
								<input src="/Resources/img/icons/tag_head_green_small.png"
									title="${tag.description}" type="image"><span
									class="tag_body">${tag.name}</span><img
									src="/Resources/img/icons/tag_tail_small.png">
							</div>
						</form>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
