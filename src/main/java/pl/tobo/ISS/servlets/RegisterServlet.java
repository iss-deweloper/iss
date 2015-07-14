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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
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
		
		if ("TRUE".equals(request.getAttribute("registrationDisabled"))) {
			request.setAttribute(StringConstants.REQUEST_ATTR_ERROR,
					"Registration is disabled");
			request.getRequestDispatcher(
					StringConstants.ISS_VIEW_PATH + "error.jsp").forward(
					request, response);
		} else {
			request.getRequestDispatcher(
					StringConstants.ISS_VIEW_PATH + "register.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request
				.getParameter(StringConstants.REQUEST_PARAM_LOGIN_USERNAME);
		String password = request
				.getParameter(StringConstants.REQUEST_PARAM_LOGIN_PASSWORD);
		String password2 = request
				.getParameter(StringConstants.REQUEST_PARAM_LOGIN_PASSWORD2);

		System.out.println("TRACE: username: " + username);
		//System.out.println("TRACE: password: " + password);
		//System.out.println("TRACE: password2: " + password2);

		// check all fields
		if (username != null && "" != username && password != null
				&& "" != password && password.equals(password2)) {

			UserDao userDAO = (UserDao) request
					.getAttribute(StringConstants.REQUEST_ATTR_USER_DAO);
			User u = userDAO.getByLogin(username);
			if (u != null) {
				// user already exists.
				request.setAttribute(StringConstants.REQUEST_ATTR_ERROR,
						"Such login already exists");
				doGet(request, response);
			} else {
				System.out.println("OK: Username not exists");
				// create new user

				User user = new User();
				user.setLogin(username);
				user.setPassword(password);

				if (userDAO.addUser(user)) {
					System.out.println("User created: " + user.getId());
					response.sendRedirect(request.getContextPath() + "/login");
				} else {
					System.out.println("ERROR: User not created");
					request.setAttribute(StringConstants.REQUEST_ATTR_ERROR,
							"Something went wrong, try again.");
					doGet(request, response);
				}

			}
		} else {
			request.setAttribute(StringConstants.REQUEST_ATTR_ERROR,
					"Incorrect or missing data. Try again.");
			doGet(request, response);
		}

	}

}
