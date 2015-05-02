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

import pl.tobo.ISS.dao.TagDao;
import pl.tobo.ISS.entities.Tag;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class TagsServlet
 */
@WebServlet("/tags")
public class TagsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		TagDao dao = (TagDao) request
				.getAttribute(StringConstants.REQUEST_ATTR_TAG_DAO);
		List<Tag> tags = dao.getAllTags();
		request.setAttribute("tags", tags);
		request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"tags.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tagName = request.getParameter("tagName");
		String tagDescription = request.getParameter("tagDescription");
		if ("" != tagName && "" != tagDescription) {
			TagDao dao = (TagDao) request
					.getAttribute(StringConstants.REQUEST_ATTR_TAG_DAO);
			Tag t = new Tag();
			t.setName(tagName);
			t.setDescription(tagDescription);

			if (dao.addTag(t)) {
				System.out.println("INFO: Tag created: "+tagName);
			}
			doGet(request, response);
		}
	}

}
