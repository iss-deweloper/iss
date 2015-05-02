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
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class EditContentServlet
 */
@WebServlet("/deletePicture")
public class DeleteContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			int id = Integer.parseInt(request.getParameter("id"));
			ContentDao dao = (ContentDao) request.getAttribute(StringConstants.REQUEST_ATTR_CONTENT_DAO);
			dao.removeById(id);
			
			/*
			 * TODO: remove file from disk (if it was local file)
			 * https://github.com/iss-deweloper/iss/issues/12
			 */
			response.sendRedirect(request.getContextPath()+"/content");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
