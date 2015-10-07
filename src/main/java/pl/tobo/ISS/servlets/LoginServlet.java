/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tobo.ISS.dao.SettingDao;
import pl.tobo.ISS.dao.UserDao;
import pl.tobo.ISS.entities.GlobalSetting;
import pl.tobo.ISS.entities.User;
import pl.tobo.ISS.utils.StringConstants;
import pl.tobo.ISS.utils.Utils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO: move this into session (?) performance issues
		logger.entering("LoginServlet","doGet");
		SettingDao settings = (SettingDao) request.getAttribute(StringConstants.REQUEST_ATTR_SETTING_DAO);
		if(request.getAttribute("registrationDisabled") == null && settings != null){
					GlobalSetting registrationDisabled = settings.getGlobalSettingByKey(StringConstants.SETTING_REGISTRATION_DISABLED);
					if (registrationDisabled != null){
						
						logger.log(Level.INFO, "Registration disabled: "+registrationDisabled.getValue());
						if("TRUE".equals(registrationDisabled.getValue())){
							request.setAttribute("registrationDisabled", "TRUE");
						}
					}
				}
		request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"login.jsp").forward(request, response);
		logger.exiting("LoginServlet","doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.entering("LoginServlet","doPost");
		String username = request.getParameter(StringConstants.REQUEST_PARAM_LOGIN_USERNAME);
		String password = request.getParameter(StringConstants.REQUEST_PARAM_LOGIN_PASSWORD);
		logger.info("Username: "+username);
				
		SettingDao settings = (SettingDao) request.getAttribute(StringConstants.REQUEST_ATTR_SETTING_DAO);
		if( settings != null){
			GlobalSetting LDAPEnabled = settings.getGlobalSettingByKey(StringConstants.SETTING_LDAP_ENABLED);
			
			if (LDAPEnabled != null && "TRUE".equalsIgnoreCase(LDAPEnabled.getValue()));
				logger.finer("Logged by LDAP: "+Utils.logByLdap(username, password, null));
		}
		if(username!=null && ""!= username && password!=null && ""!=password){
			UserDao userDAO = (UserDao) request.getAttribute(StringConstants.REQUEST_ATTR_USER_DAO);
			if (userDAO!=null){
				password = Utils.getMD5(password);
				logger.info("MD5(password): "+password);
				User u = userDAO.getByLogin(username);
				if(u == null){
					logger.log(Level.WARNING,"Incorrect username: "+ username);
					request.setAttribute(StringConstants.REQUEST_ATTR_ERROR, "No such user");
					doGet(request, response);
					
				} else if(!u.getPassword().equals(password)){
					logger.log(Level.WARNING,"Incorrect password for: "+ username);
				
					request.setAttribute(StringConstants.REQUEST_ATTR_ERROR, "Incorrect password");
					doGet(request, response);
					
				} else {
					logger.info("Everything OK");
					request.getSession().setAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER, u);
					request.getSession().setAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER_LOGIN, u.getLogin());
					request.getSession().setAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER_ROLE, u.getRole().getDescription());
					response.sendRedirect(request.getContextPath()+"/index");
				}
			}
		}else{
			request.setAttribute(StringConstants.REQUEST_ATTR_ERROR, "Missing login or password");
			doGet(request, response);
		}
		logger.exiting("LoginServlet","doPost");
	}

}
