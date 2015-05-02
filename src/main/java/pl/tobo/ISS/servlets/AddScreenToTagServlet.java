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

import pl.tobo.ISS.dao.ScreenDao;
import pl.tobo.ISS.dao.TagDao;
import pl.tobo.ISS.entities.Screen;
import pl.tobo.ISS.entities.Tag;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class AddScreenToTagServlet
 */
@WebServlet("/addScreenToTag")
public class AddScreenToTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		System.out.println("DEBUG: in doGet for tag: "+ tagId);
		
		response.sendRedirect(request.getContextPath() + "/tag?id="+ tagId);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		int screenId = Integer.parseInt(request.getParameter("screenId"));
		System.out.println("DEBUG: in doPost for tag: "+ tagId +" and screen: "+ screenId);
		
		TagDao dao = (TagDao) request
				.getAttribute(StringConstants.REQUEST_ATTR_TAG_DAO);
		Tag tag = dao.getById(tagId);
		
		ScreenDao sDao = (ScreenDao) request
				.getAttribute(StringConstants.REQUEST_ATTR_SCREEN_DAO);
		
		Screen screen = sDao.getById(screenId);
		
		tag.getScreens().add(screen);
		
		System.out.println("INFO: Update success: "+sDao.addTagToScreen(screenId, tag));
		
		doGet(request, response);
		
	}

}
