/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tobo.ISS.entities.User;
import pl.tobo.ISS.utils.StringConstants;

public class ServletUtils {

	public static boolean userLogged(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute(
				StringConstants.REQUEST_ATTR_LOGGED_USER);
		if (u == null) {
			System.out.println("User not logged.");
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"login.jsp").forward(
					request, response);
			return false;
		} else {
			System.out.println("User: "+u.getLogin()+" logged.");
			return true;
		}
	}
}
