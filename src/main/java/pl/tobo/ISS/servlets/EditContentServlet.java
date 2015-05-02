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

import pl.tobo.ISS.dao.ContentDao;
import pl.tobo.ISS.dao.TagDao;
import pl.tobo.ISS.entities.Content;
import pl.tobo.ISS.entities.Tag;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class EditContentServlet
 */
@WebServlet("/picture")
public class EditContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletUtils.userLogged(request, response)){
		
			int id = Integer.parseInt(request.getParameter("id"));
		
			ContentDao dao = (ContentDao) request.getAttribute(StringConstants.REQUEST_ATTR_CONTENT_DAO);
			
			Content c = dao.getById(id);
			if(c!=null){
				request.setAttribute("content", c);
				TagDao tagDao = (TagDao) request.getAttribute(StringConstants.REQUEST_ATTR_TAG_DAO);
				List<Tag> tags = tagDao.getAllTags();
				tags.removeAll(c.getTags());
				request.setAttribute("tags", tags);

				request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"picture.jsp").forward(
						request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
