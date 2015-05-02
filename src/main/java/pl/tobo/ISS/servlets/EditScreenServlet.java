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
import pl.tobo.ISS.dao.TagDao;
import pl.tobo.ISS.entities.Screen;
import pl.tobo.ISS.entities.Tag;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class EditTagServlet
 */
@WebServlet("/screen")
public class EditScreenServlet extends HttpServlet {
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
			int id = Integer.parseInt(request.getParameter("id"));
			ScreenDao dao = (ScreenDao) request
					.getAttribute(StringConstants.REQUEST_ATTR_SCREEN_DAO);
			Screen screen = dao.getById(id);
			
			request.setAttribute("screen", screen);
			
			TagDao tDao = (TagDao) request
					.getAttribute(StringConstants.REQUEST_ATTR_TAG_DAO);
			
			
			List<Tag> tags = (List<Tag>) tDao.getAllTags();
			if(tags!=null && screen.getTags()!=null){
				tags.removeAll(screen.getTags());
			}
			request.setAttribute("tags", tags);
			
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"screen.jsp").forward(
					request, response);
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
