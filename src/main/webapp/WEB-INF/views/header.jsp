<!-- 
Copyright (C) 2014,2015 Tomasz Bosak.
-->
<%@page import="pl.tobo.ISS.utils.StringConstants"%>

<div class="menu_ribbon">
	<a href="content"> <img class="menu_picture" alt="content"
		src="/Resources/img/icons/screen_zoom.png"> CONTENT
	</a> <a href="tags"> <img class="menu_picture" alt="tags"
		src="/Resources/img/icons/tag_double.png"> TAGS
	</a> <a href="screens"> <img class="menu_picture" alt="screens"
		src="/Resources/img/icons/screen_double.png"> SCREENS
	</a> <a href="index"> <img class="menu_picture" alt="index"
		src="/Resources/img/icons/home.png"> HOME
	</a> <a href="help"> <img class="menu_picture" alt="help"
		src="/Resources/img/icons/info.png"> HELP
	</a> <a href="logout"> <img class="menu_picture menu_right" alt=""
		src="/Resources/img/icons/logoff.png"> <span class="menu_right">
			Logout </span>
	</a> <img class="menu_picture menu_right" alt="user"
		src="/Resources/img/icons/user.png"> <span class="menu_right">
		<%=session.getAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER_LOGIN)%>
	</span>
</div>
