<!-- 
Copyright (C) 2014,2015 Tomasz Bosak.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="120">
<c:if test="${screen.format == 'STANDARD'}">
<link
	href="<c:url value="/Resources/css/main.css" />"
	rel="stylesheet">
</c:if>
<c:if test="${screen.format == 'FULLSCREEN'}">
<link
	href="<c:url value="/Resources/css/full_screen.css" />"
	rel="stylesheet">
</c:if>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>

<script
	src="<c:url value="/Resources/js/change_slides.js"/>"></script>
<script
	src="<c:url value="/Resources/js/date_time.js"/>"></script>

<title>ISS Slideshow</title>
</head>

<body>
	<div id="top">
		<header id="NAGLOWEK">
			<span id="date_time"></span>
			<script type="text/javascript">window.onload = date_time('date_time');</script>
		</header>
		<nav id="MENU"></nav>
		<aside id="INFORMACJE"></aside>
		<article id="TRESC">
			<div id="slideshow">
				<c:forEach var="image" items="${contents}">
					<div>
						<h1>${image.title}</h1>
						<span class="smalltext">Added by ${image.user.login}</span><br />
						<br />
						<!--  Check if content is a picture or iframe -->
						<c:if test="${image.contentType == 'IFRAME'}">
							<iframe class="iframe" src="${image.contentAddress}"></iframe>
						</c:if>
						<c:if test="${image.contentType == 'IMG'}">
							<img class="image" src="${image.contentAddress}"
								title="${content.contentAddress}">
						</c:if>

					</div>
				</c:forEach>
			</div>
		</article>
	</div>
</body>
</html>