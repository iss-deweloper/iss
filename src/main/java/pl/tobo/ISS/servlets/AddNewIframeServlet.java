/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
@WebServlet("/addNewIframe")
public class AddNewIframeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletUtils.userLogged(request,response)) {
			// do something
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"addIframe.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletUtils.userLogged(request,response)) {
			
			User user = (User) request.getSession().getAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER);
			ContentDao dao = (ContentDao) request.getAttribute(StringConstants.REQUEST_ATTR_CONTENT_DAO);
			
			String contentTitle = request.getParameter("pictureTitle");
			String contentAddress = request.getParameter("pictureAddress");
			String contentValidFrom = request.getParameter("validFrom");
			String contentValidTo = request.getParameter("validTo");
			
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
				System.out.println("Valid from: "+ tFrom);
				c.setValidFrom(tFrom);

				Timestamp tTo;
				if(contentValidTo!=null && !"".equals(contentValidTo)){
					contentValidTo += " 23:59:59.999";
					System.out.println("To: "+contentValidTo);
					 tTo = Timestamp.valueOf(contentValidTo);
				}
				else{
					tTo = Timestamp.valueOf("2099-12-21 23:59:59.999");
				}
				System.out.println("Valid to: "+ tTo);
				c.setValidTo(tTo);

				c.setUser(user);
				c.setContentType(ContentType.IFRAME);
				
				if(dao.addContent(c)){
					System.out.println("Content created: "+c.getId());
					response.sendRedirect(request.getContextPath() + "/picture?id="+ c.getId());
					
				}else{
					doGet(request, response);					
				}
			}else{
				doGet(request, response);
			}
		}
	}

}
