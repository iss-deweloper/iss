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
<link href="<c:url value="/Resources/css/content_styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/layer_content.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/top_menu.css" />"
	rel="stylesheet">
<link href="<c:url value="/Resources/css/tags.css" />" rel="stylesheet">

<title>Content</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="column_1">
		<div class="column_2_center">
			<!-- START buttons to publish new content -->
			<div class="column_1_box">
				<a class="button_link" href="addNewContent"> <img
					class="menu_picture" alt=""
					src="${pageContext.request.contextPath}/Resources/img/icons/add_from_web.png"> Add from web
				</a> &nbsp; <a class="button_link" href="addNewLocalContent"> <img
					class="menu_picture" alt=""
					src="${pageContext.request.contextPath}/Resources/img/icons/add_from_disc.png"> Add from disc
				</a>&nbsp; <a class="button_link" href="addNewIframe"> <img
					class="menu_picture" alt=""
					src="${pageContext.request.contextPath}/Resources/img/icons/add_iframe.png"> Add iframe
				</a>
			</div>
			<!-- END buttons to publish new content -->

			<!--  CONTENT BOX -->
			<c:forEach var="picture" items="${pictures}">
				<div class="column_1_box">
					<div class="contentbox column_3_quarter">
						<p class="description">Title:</p>
						<p class="title">${picture.title}</p>
						<p class="description">Valid from:</p>
						<jsp:useBean id="now" class="java.util.Date" />

						<c:choose>
							<c:when test="${picture.validFrom le now}">
								<c:choose>
									<c:when test="${picture.validTo ge now}">
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


						<p class="date ${valid}">
							<fmt:formatDate value="${picture.validFrom}"
								pattern="yyyy-MM-dd HH:mm" />
						</p>
						<p class="description">Valid to:</p>
						<p class="date ${valid}">
							<fmt:formatDate value="${picture.validTo}"
								pattern="yyyy-MM-dd HH:mm" />
						</p>
						<p class="description">Added by:</p>
						<p class="author">${picture.user.login}</p>
					</div>
					<div class="contentbox column_3_quarter">
						<!--  Check if content is a picture or iframe -->
						<c:if test="${picture.contentType == 'IFRAME'}">
							<iframe class="iframe_small" src="${picture.contentAddress}"></iframe>
						</c:if>
						<c:if test="${picture.contentType == 'IMG'}">
							<img class="content_img" src="${picture.contentAddress}"
								title="${content.contentAddress}">
						</c:if>
					</div>
					<div class="tagbox column_3_third">
						Tags:<br>
						<c:forEach var="tag" items="${picture.tags}">
							<div class="tagpicture">
								<img alt="h"
									src="${pageContext.request.contextPath}/Resources/img/icons/tag_head_orange_small.png"><span
									class="tag_body">${tag.name}</span><img alt="t"
									src="${pageContext.request.contextPath}/Resources/img/icons/tag_tail_small.png">
							</div>
						</c:forEach>
					</div>
					<div class="toolbox column_3_tenth">
						<c:url var="url" scope="page" value="/picture">
							<c:param name="id" value="${picture.id}"></c:param>
						</c:url>
						<a href="${url}"> <img class="menu_picture" alt=""
							src="${pageContext.request.contextPath}/Resources/img/icons/settings.png"> <br>Edit
						</a><br>
						<c:url var="url" scope="page" value="/deletePicture">
							<c:param name="id" value="${picture.id}"></c:param>
						</c:url>
						<a href="${url}"> <img class="menu_picture" alt=""
							src="${pageContext.request.contextPath}/Resources/img/icons/trash.png"> <br>Delete
						</a>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>

</body>
</html>