/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tobo.ISS.dao.ScreenDao;
import pl.tobo.ISS.entities.Content;
import pl.tobo.ISS.entities.Screen;
import pl.tobo.ISS.entities.Tag;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class SlideshowServlet
 */
@WebServlet("/slideshow")
public class SlideshowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String screenId = request
				.getParameter(StringConstants.REQUEST_PARAM_SCREENID);

		if (screenId == null || "".equals(screenId)) {

			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			System.out.println("No id. Creating new one.");

			System.out.println("Request from: " + ipAddress);
			if(ipAddress != null && !"".equals(ipAddress)){
				screenId = ipAddress;
				request.setAttribute(
					StringConstants.REQUEST_PARAM_SCREEN_IP_ADDRESS, ipAddress);
			}
			// request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"newScreen.jsp")
			// .forward(request, response);
		}
		ScreenDao screenDao = (ScreenDao) request
				.getAttribute(StringConstants.REQUEST_ATTR_SCREEN_DAO);
		Screen screen = screenDao.getByName(screenId);

		if (screen == null) {
			System.out.println("No such id. Please create new one.");
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"newScreen.jsp")
					.forward(request, response);
		} else {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			Set<Tag> tags = screen.getTags();
			Set<Content> contents = new HashSet<Content>();
			for (Tag t : tags) {
				for(Content c: t.getContents()){
					if(c.getValidFrom().before(ts) && c.getValidTo().after(ts))
						contents.add(c);
					else
						System.out.println("Content not valid: "+ts+", "+c);
				}
			}

			System.out.println("Have: " + contents.size() + " content items.");

			request.setAttribute(StringConstants.REQUEST_ATTR_CONTENTS,
					contents);
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"slideshow.jsp")
					.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
