/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER) == null) {
			System.out.println("User not logged.");
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"login.jsp").forward(
					request, response);
		} else {
			
			String ipAddress = request.getHeader("X-FORWARDED-FOR");  
			if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
			}
			System.out.println("Request from: "+ipAddress);
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"index.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
