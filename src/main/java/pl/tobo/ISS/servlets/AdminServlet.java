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

import pl.tobo.ISS.dao.SettingDao;
import pl.tobo.ISS.entities.GlobalSetting;
import pl.tobo.ISS.entities.RoleDescription;
import pl.tobo.ISS.entities.SettingType;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RoleDescription rd = (RoleDescription) request.getSession()
				.getAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER_ROLE);

		if (RoleDescription.ADMIN != rd) {

			request.setAttribute(StringConstants.REQUEST_ATTR_ERROR, "You are not admin. You have no permissions to use this page.");
			request.getRequestDispatcher(
					StringConstants.ISS_VIEW_PATH + "error.jsp").forward(
					request, response);

		} else {
			SettingDao settings = (SettingDao) request
					.getAttribute(StringConstants.REQUEST_ATTR_SETTING_DAO);

			if (settings != null) {
				List<GlobalSetting> settingList = settings
						.getAllGlobalSettings();
				request.setAttribute("settingList", settingList);
			} else {
				System.out.println("No settings DAO");
			}

			request.getRequestDispatcher(
					StringConstants.ISS_VIEW_PATH + "admin.jsp").forward(
					request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RoleDescription rd = (RoleDescription) request.getSession()
				.getAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER_ROLE);

		if (RoleDescription.ADMIN == rd) {

			String action = request.getParameter("action");

			System.out.println("TRACE: action: " + action);

			if ("addsetting".equals(action)) {
				SettingDao settings = (SettingDao) request
						.getAttribute(StringConstants.REQUEST_ATTR_SETTING_DAO);
				if (settings != null) {
					String key = request.getParameter("key");
					String value = request.getParameter("value");

					GlobalSetting gs = new GlobalSetting();
					gs.setKey(key);
					gs.setValue(value);
					gs.setType(SettingType.STRING);
					settings.addSetting(gs, true);
				} else {
					System.out.println("No settings DAO");
				}
			}
		}
		doGet(request, response);
	}

}
