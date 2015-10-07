/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.entering("LogoutServlet","doGet");
		doPost(request, response);
		logger.exiting("LogoutServlet","doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.entering("LogoutServlet","doPost");
		request.getSession().removeAttribute(
				StringConstants.REQUEST_ATTR_LOGGED_USER);
		request.getSession().removeAttribute(
				StringConstants.REQUEST_ATTR_LOGGED_USER_LOGIN);
		
		request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"login.jsp").forward(
				request, response);
		logger.exiting("LogoutServlet","doPost");
	}

}
