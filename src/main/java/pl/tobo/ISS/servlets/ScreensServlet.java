/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tobo.ISS.dao.ScreenDao;
import pl.tobo.ISS.entities.Screen;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class TagsServlet
 */
@WebServlet("/screens")
public class ScreensServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute(
				StringConstants.REQUEST_ATTR_LOGGED_USER) == null) {
			System.out.println("User not logged.");
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"login.jsp").forward(
					request, response);
		} else {

			ScreenDao dao = (ScreenDao) request
					.getAttribute(StringConstants.REQUEST_ATTR_SCREEN_DAO);
			List<Screen> screens = dao.getAllScreens();
			System.out.println("Read: " + screens.size() + " tags.");
			request.setAttribute("screens", screens);
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"screens.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute(
				StringConstants.REQUEST_ATTR_LOGGED_USER) == null) {
			System.out.println("User not logged.");
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"login.jsp").forward(
					request, response);
		} else {
			String screenName = request.getParameter("screenName");
			String screenDescription = request.getParameter("screenDescription");
			if ("" != screenName && "" != screenDescription) {
				ScreenDao dao = (ScreenDao) request
						.getAttribute(StringConstants.REQUEST_ATTR_SCREEN_DAO);

				Screen s = new Screen();
				System.out.println("DEBUG: Screen name: "+screenName);
				s.setName(screenName);
				System.out.println("DEBUG: Screen description: "+screenDescription);
				s.setDescription(screenDescription);
				s.setFormat("STANDARD");
				if (dao.addScreen(s)) {
					System.out.println("INFO: Screen created.");
				}
				doGet(request, response);
			}
		}
	}

}
