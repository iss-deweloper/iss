/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tobo.ISS.dao.ContentDao;
import pl.tobo.ISS.entities.Content;
import pl.tobo.ISS.entities.ContentType;
import pl.tobo.ISS.entities.User;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class AddNewContentServlet
 */
@WebServlet("/addNewContent")
public class AddNewContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.entering("AddNewContentServlet","doGet");
		//if (ServletUtils.userLogged(request,response)) {
			
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"addContent.jsp").forward(
					request, response);
		//}
			logger.exiting("AddNewContentServlet","doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.entering("AddNewContentServlet","doPost");
		//if (ServletUtils.userLogged(request,response)) {
			
			User user = (User) request.getSession().getAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER);
			ContentDao dao = (ContentDao) request.getAttribute(StringConstants.REQUEST_ATTR_CONTENT_DAO);
			
			String contentTitle = request.getParameter("pictureTitle");
			logger.finer("contentTitle: "+ contentTitle);
			String contentAddress = request.getParameter("pictureAddress");
			logger.finer("contentAddress: "+ contentAddress);
			String contentValidFrom = request.getParameter("validFrom");
			logger.finer("contentValidFrom: "+ contentValidFrom);
			String contentValidTo = request.getParameter("validTo");
			logger.finer("contentValidTo: "+ contentValidTo);
			
			if(contentAddress != null && !"".equals(contentAddress)){
				Content c = new Content();
				c.setContentAddress(contentAddress);
				if(contentTitle!=null && !"".equals(contentTitle))
					c.setTitle(contentTitle);
				
				Timestamp tFrom;
				if(contentValidFrom!=null && !"".equals(contentValidFrom)){
					contentValidFrom += " 00:00:00.000";
					System.out.println("From: "+contentValidFrom);
					tFrom = Timestamp.valueOf(contentValidFrom);
				}else{
					tFrom = new Timestamp(new Date().getTime());
				}
				logger.finer("Valid from: "+ tFrom);
				c.setValidFrom(tFrom);

				Timestamp tTo;
				if(contentValidTo!=null && !"".equals(contentValidTo)){
					contentValidTo += " 23:59:59.999";
					tTo = Timestamp.valueOf(contentValidTo);
				}
				else{
					tTo = Timestamp.valueOf("2099-12-21 23:59:59.999");
				}
				logger.finer("Valid to: "+ tTo);
				c.setValidTo(tTo);

				c.setUser(user);
				c.setContentType(ContentType.IMG);
				if(dao.addContent(c)){
					logger.info("Content created: "+c.getId()+": "+c);
					response.sendRedirect(request.getContextPath() + "/picture?id="+ c.getId());
				}else{
					doGet(request, response);					
				}
			}else{
				doGet(request, response);
			}
		}
	//}

}
