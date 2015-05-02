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

import pl.tobo.ISS.dao.ContentDao;
import pl.tobo.ISS.dao.TagDao;
import pl.tobo.ISS.entities.Content;
import pl.tobo.ISS.entities.Tag;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class AddScreenToTagServlet
 */
@WebServlet("/removeTagFromContent")
public class RemoveTagFromContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contentId = Integer.parseInt(request.getParameter("contentId"));
		
		System.out.println("DEBUG: in doGet for content: "+ contentId);
		
		response.sendRedirect(request.getContextPath() + "/picture?id="+ contentId);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		int contentId = Integer.parseInt(request.getParameter("contentId"));
		System.out.println("DEBUG: in doPost for tag: "+ tagId +" and content: "+ contentId);
		
		TagDao dao = (TagDao) request
				.getAttribute(StringConstants.REQUEST_ATTR_TAG_DAO);
		Tag tag = dao.getById(tagId);
		
		ContentDao cDao = (ContentDao) request
				.getAttribute(StringConstants.REQUEST_ATTR_CONTENT_DAO);

		Content content = cDao.getById(contentId);

		if(tag.getContents().contains(content))
		{
			tag.getContents().remove(content);
			System.out.println("INFO: Update success: "+cDao.removeTagFromContent(contentId, tag));
		}
		
		doGet(request, response);
		
	}

}
